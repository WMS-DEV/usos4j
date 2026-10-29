import json
import re
import sys
from pathlib import Path

SOURCE_ROOT = Path("core/src/main/java/pl/wmsdev/usos4j")
POM = Path("core/pom.xml")
DEFAULT_OUTPUT = Path("build/openapi/openapi.json")

SIMPLE_TYPES = {
    "String": {"type": "string"},
    "Integer": {"type": "integer"},
    "int": {"type": "integer"},
    "Long": {"type": "integer", "format": "int64"},
    "long": {"type": "integer", "format": "int64"},
    "Double": {"type": "number"},
    "double": {"type": "number"},
    "Float": {"type": "number"},
    "BigDecimal": {"type": "number"},
    "Boolean": {"type": "boolean"},
    "boolean": {"type": "boolean"},
    "LocalDate": {"type": "string", "format": "date"},
    "LocalDateTime": {"type": "string", "format": "date-time"},
    "File": {"type": "string", "format": "binary"},
    "Object": {},
}
LIST_TYPES = {"List", "Set", "Collection"}

METHOD_PATTERN = re.compile(
    r"((?:@\w+(?:\([^)]*\))?\s*)*)public\s+([\w<>\[\], ?]+?)\s+(\w+)\s*\(([^)]*)\)\s*\{"
)
RECORD_PATTERN = re.compile(r"record\s+(\w+)\s*\((.*?)\)\s*(?:implements[^{]*)?\{", re.DOTALL)
ENUM_PATTERN = re.compile(r"enum\s+(\w+)\s*(?:implements[^{]*)?\{(.*?)(?:;|\})", re.DOTALL)
BUILDER_PATTERN = re.compile(r"static\s+\w+\s+builder\s*\(([^)]*)\)")
ENDPOINT_PATTERN = re.compile(r'"(services/[\w/]+)"')


def camel_to_snake(name):
    return re.sub(r"([A-Z])", lambda match: "_" + match.group(1).lower(), name)


def strip_annotations(text):
    return re.sub(r"@\w+(\([^)]*\))?\s*", "", text)


def split_top_level(text):
    parts, depth, current = [], 0, ""
    for char in text:
        if char in "<(":
            depth += 1
        if char in ">)":
            depth -= 1
        if char == "," and depth == 0:
            parts.append(current.strip())
            current = ""
        else:
            current += char
    if current.strip():
        parts.append(current.strip())
    return parts


def split_type_and_name(declaration):
    type_name, _, name = declaration.rpartition(" ")
    return type_name.strip(), name.strip()


def read_java_files(directory):
    return {path: path.read_text(encoding="utf-8") for path in sorted(directory.rglob("*.java"))}


def collect_models(model_files):
    records, params, enums = {}, {}, {}
    for text in model_files.values():
        text = strip_annotations(text)
        for match in RECORD_PATTERN.finditer(text):
            name = match.group(1)
            components = [split_type_and_name(part) for part in split_top_level(match.group(2))]
            if "implements UsosParams" in match.group(0) or name.endswith("Params") or name.endswith("Param"):
                params[name] = parse_params(components, text)
            else:
                records[name] = components
        for match in ENUM_PATTERN.finditer(text):
            enums[match.group(1)] = [value.strip().lower() for value in match.group(2).split(",") if value.strip()]
    return records, params, enums


def parse_params(components, text):
    builder = BUILDER_PATTERN.search(text)
    required, varargs = set(), set()
    if builder:
        for argument in split_top_level(builder.group(1)):
            type_name, name = split_type_and_name(argument)
            required.add(name)
            if type_name.endswith("..."):
                varargs.add(name)
    return [(type_name, name, name in required, name in varargs) for type_name, name in components]


def type_to_schema(type_name, records, enums):
    type_name = type_name.strip()
    generic = re.match(r"(\w+)<(.+)>$", type_name)
    if type_name.endswith("[]"):
        return {"type": "array", "items": type_to_schema(type_name[:-2], records, enums)}
    if generic and generic.group(1) in LIST_TYPES:
        return {"type": "array", "items": type_to_schema(generic.group(2), records, enums)}
    if generic and generic.group(1) == "Map":
        value_type = split_top_level(generic.group(2))[-1]
        return {"type": "object", "additionalProperties": type_to_schema(value_type, records, enums)}
    if type_name in SIMPLE_TYPES:
        return dict(SIMPLE_TYPES[type_name])
    if type_name in enums:
        return {"type": "string", "enum": enums[type_name]}
    if type_name in records:
        return {"$ref": f"#/components/schemas/{type_name}"}
    return {}


def build_schemas(records, enums):
    schemas = {}
    for name, components in records.items():
        properties = {camel_to_snake(field): type_to_schema(type_name, records, enums) for type_name, field in components}
        schemas[name] = {"type": "object", "properties": properties}
    return schemas


def build_query_parameters(params, records, enums):
    parameters = []
    for type_name, name, required, is_varargs in params:
        schema = type_to_schema(type_name.replace("...", ""), records, enums)
        parameter = {"name": camel_to_snake(name), "in": "query", "required": required, "schema": schema}
        if is_varargs:
            parameter["description"] = "Pipe-separated list of values."
        parameters.append(parameter)
    parameters.append({
        "name": "fields",
        "in": "query",
        "required": False,
        "schema": {"type": "string"},
        "description": "Pipe-separated selector of result fields. usos4j always requests every field of the response model.",
    })
    return parameters


def find_methods(text):
    matches = list(METHOD_PATTERN.finditer(text))
    for index, match in enumerate(matches):
        end = matches[index + 1].start() if index + 1 < len(matches) else len(text)
        yield match, text[match.end():end]


def build_operation(class_name, match, body, params, records, enums):
    annotations, return_type, method_name, arguments = match.groups()
    params_type = arguments.strip().split(" ")[0] if arguments.strip() else None
    notes = []
    if "@Beta" in annotations:
        notes.append("Beta endpoint, may change without notice.")
    if "@NotTested" in annotations:
        notes.append("Not tested against a real USOS installation.")
    operation = {
        "summary": f"{class_name}#{method_name}",
        "tags": [re.sub(r"^services/(\w+)/.*", r"\1", ENDPOINT_PATTERN.search(body).group(1))],
        "parameters": build_query_parameters(params.get(params_type, []), records, enums),
        "responses": {"200": {"description": "Successful response"}},
    }
    if notes:
        operation["description"] = " ".join(notes)
    if return_type != "void":
        operation["responses"]["200"]["content"] = {
            "application/json": {"schema": type_to_schema(return_type, records, enums)}
        }
    if "requestWithAccessToken(" in body:
        operation["security"] = [{"usosOAuth1": []}]
    return operation


def merge_overload(operation, overload):
    known = {parameter["name"] for parameter in operation["parameters"]}
    for parameter in overload["parameters"]:
        if parameter["name"] not in known:
            operation["parameters"].append({**parameter, "required": False})
    if "security" in overload:
        operation.setdefault("security", overload["security"])


def build_paths(api_files, params, records, enums):
    paths = {}
    for path, text in api_files.items():
        class_name = path.stem
        for match, body in find_methods(text):
            endpoint = ENDPOINT_PATTERN.search(body)
            if not endpoint:
                continue
            url = "/" + endpoint.group(1)
            operation = build_operation(class_name, match, body, params, records, enums)
            if url in paths:
                merge_overload(paths[url]["get"], operation)
            else:
                paths[url] = {"get": operation}
    return dict(sorted(paths.items()))


def read_version():
    return re.search(r"<version>([^<]+)</version>", POM.read_text(encoding="utf-8")).group(1)


def build_specification():
    model_files = read_java_files(SOURCE_ROOT / "model")
    api_files = read_java_files(SOURCE_ROOT / "api")
    records, params, enums = collect_models(model_files)
    return {
        "openapi": "3.0.3",
        "info": {
            "title": "USOS API as seen by usos4j",
            "version": read_version(),
            "description": "Only endpoints integrated in usos4j are listed. This specification is generated from usos4j's implementation of the USOS API, it is not an official USOS API specification and does not have to be true: the USOS documentation is sometimes inaccurate, response shapes are only as precise as the Java models, and installations differ. It provides structured data on how the USOS API works.",
        },
        "servers": [{
            "url": "{baseUrl}",
            "variables": {"baseUrl": {"default": "https://apps.usos.pw.edu.pl/", "description": "Base URL of a USOS installation."}},
        }],
        "paths": build_paths(api_files, params, records, enums),
        "components": {
            "securitySchemes": {
                "usosOAuth1": {
                    "type": "http",
                    "scheme": "OAuth",
                    "description": "OAuth 1.0a access token obtained for the user. The consumer key is always required.",
                }
            },
            "schemas": build_schemas(records, enums),
        },
    }


def main():
    output = Path(sys.argv[1]) if len(sys.argv) > 1 else DEFAULT_OUTPUT
    specification = build_specification()
    output.parent.mkdir(parents=True, exist_ok=True)
    output.write_text(json.dumps(specification, indent=2, ensure_ascii=False), encoding="utf-8")
    print(f"Wrote {len(specification['paths'])} paths and {len(specification['components']['schemas'])} schemas to {output}")


if __name__ == "__main__":
    main()
