import json
import re
import sys
from pathlib import Path

SOURCE_ROOT = Path("core/src/main/java/pl/wmsdev/usos4j")
POM = Path("core/pom.xml")
DEFAULT_OUTPUT = Path("build/openapi/openapi.json")
APIREF_SNAPSHOT = Path(__file__).with_name("apiref-snapshot.json")
ERROR_SCHEMA = "UsosError"

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
RECORD_START_PATTERN = re.compile(r"\brecord\s+(\w+)\s*\(")
ALIAS_PATTERN = re.compile(r'@JsonAlias\(\s*(?:\{\s*)?"([^"]+)"')
FIELD_SELECTOR_PATTERN = re.compile(r"FieldSelector\.fromRequest\(\s*(\w+)\.class\s*\)")
FIELDS_LITERAL_PATTERN = re.compile(r'"fields"\s*,\s*List\.of\(([^)]*)\)')
WIRE_VALUE_PATTERN = re.compile(r'"(\w+)"\s*,\s*(?:List\.of|Collections\.singletonList|String\.valueOf)\(([^)]*)\)')
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


def find_records(text):
    for match in RECORD_START_PATTERN.finditer(text):
        depth, end = 1, match.end()
        while end < len(text) and depth:
            depth += {"(": 1, ")": -1}.get(text[end], 0)
            end += 1
        header_end = text.find("{", end)
        implements = text[end:header_end] if header_end != -1 else ""
        yield match.group(1), text[match.end():end - 1], implements


def parse_component(declaration):
    type_name, name = split_type_and_name(strip_annotations(declaration))
    alias = ALIAS_PATTERN.search(declaration)
    return {
        "type": type_name,
        "name": name,
        "alias": alias.group(1) if alias else None,
        "excluded": "@ExcludeField" in declaration,
    }


def collect_models(model_files):
    records, params, enums = {}, {}, {}
    for original in model_files.values():
        text = strip_annotations(original)
        for name, raw_components, implements in find_records(original):
            components = [parse_component(part) for part in split_top_level(raw_components)]
            if "UsosParams" in implements or name.endswith("Params") or name.endswith("Param"):
                params[name] = parse_params([(item["type"], item["name"]) for item in components], text)
            else:
                records[name] = {"components": components, "common": "UsosCommonObject" in implements}
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


def property_name(component):
    return component["alias"] or camel_to_snake(component["name"])


def build_schemas(records, enums):
    schemas = {}
    for name, record in records.items():
        properties = {
            property_name(component): type_to_schema(component["type"], records, enums)
            for component in record["components"]
        }
        schemas[name] = {"type": "object", "properties": properties}
    schemas[ERROR_SCHEMA] = {"type": "object", "properties": {"message": {"type": "string"}}}
    return schemas


def referenced_schemas(node, schemas, found=None):
    found = set() if found is None else found
    if isinstance(node, dict):
        reference = node.get("$ref")
        if reference and reference.rsplit("/", 1)[-1] not in found:
            name = reference.rsplit("/", 1)[-1]
            found.add(name)
            referenced_schemas(schemas[name], schemas, found)
        for value in node.values():
            referenced_schemas(value, schemas, found)
    elif isinstance(node, list):
        for value in node:
            referenced_schemas(value, schemas, found)
    return found


def prune_schemas(paths, schemas):
    used = referenced_schemas(paths, schemas)
    return {name: schema for name, schema in schemas.items() if name in used}


def element_type(type_name):
    generic = re.match(r"(\w+)<(.+)>$", type_name.strip())
    if generic and generic.group(1) in LIST_TYPES:
        return generic.group(2).strip()
    return type_name.strip()


def response_selector(name, records, visiting=()):
    keys = []
    for component in records[name]["components"]:
        if component["excluded"]:
            continue
        key = property_name(component)
        nested = element_type(component["type"])
        if nested in records and records[nested]["common"] and nested not in (*visiting, name):
            key += "[" + response_selector(nested, records, (*visiting, name)) + "]"
        keys.append(key)
    return "|".join(keys)


def default_fields(body, records):
    selector = FIELD_SELECTOR_PATTERN.search(body)
    if selector and selector.group(1) in records:
        return response_selector(selector.group(1), records)
    literal = FIELDS_LITERAL_PATTERN.search(body)
    if literal:
        return "|".join(re.findall(r'"([^"]*)"', literal.group(1))) or None
    return None


def wire_key(body, name):
    for key, value in WIRE_VALUE_PATTERN.findall(body):
        code = re.sub(r'"[^"]*"', "", value)
        if key != "fields" and re.search(rf"\b{re.escape(name)}\b", code):
            return key
    return camel_to_snake(name)


def is_list_type(type_name):
    return type_name.endswith("[]") or element_type(type_name) != type_name.strip()


def build_plain_parameters(arguments, params, body, records, enums):
    parameters = []
    for argument in split_top_level(arguments):
        type_name, name = split_type_and_name(argument)
        if not name or type_name in params:
            continue
        if is_list_type(type_name):
            parameter = {"schema": {"type": "string"}, "description": "Pipe-separated list of values."}
        else:
            parameter = {"schema": type_to_schema(type_name, records, enums)}
        parameters.append({"name": wire_key(body, name), "in": "query", "required": True, **parameter})
    return parameters


def build_fields_parameter(selector):
    schema = {"type": "string"}
    description = "Pipe-separated selector of the result fields, nested fields go in brackets, e.g. user[id|first_name]."
    if selector:
        schema["default"] = selector
        description += " Prefilled with every field usos4j requests for this endpoint, edit it to ask for fewer or other fields."
    return {"name": "fields", "in": "query", "required": False, "schema": schema, "description": description}


def build_query_parameters(params_record, params, arguments, body, records, enums):
    parameters = []
    for type_name, name, required, is_varargs in params_record:
        schema = type_to_schema(type_name.replace("...", ""), records, enums)
        parameter = {"name": camel_to_snake(name), "in": "query", "required": required, "schema": schema}
        if is_varargs:
            parameter["description"] = "Pipe-separated list of values."
        parameters.append(parameter)
    parameters += build_plain_parameters(arguments, params, body, records, enums)
    parameters = [parameter for parameter in parameters if parameter["name"] != "fields"]
    parameters.append(build_fields_parameter(default_fields(body, records)))
    return parameters


def find_methods(text):
    matches = list(METHOD_PATTERN.finditer(text))
    for index, match in enumerate(matches):
        end = matches[index + 1].start() if index + 1 < len(matches) else len(text)
        yield match, text[match.end():end]


def error_response():
    return {
        "description": "Error returned by USOS",
        "content": {"application/json": {"schema": {"$ref": f"#/components/schemas/{ERROR_SCHEMA}"}}},
    }


def build_operation(class_name, match, body, params, records, enums):
    annotations, return_type, method_name, arguments = match.groups()
    params_type = arguments.strip().split(" ")[0] if arguments.strip() else None
    notes = []
    if "@Beta" in annotations:
        notes.append("Beta endpoint, may change without notice.")
    if "@NotTested" in annotations:
        notes.append("Not tested against a real USOS installation.")
    endpoint = ENDPOINT_PATTERN.search(body).group(1)
    operation = {
        "operationId": operation_id(endpoint),
        "summary": endpoint,
        "tags": [re.sub(r"^services/(\w+)/.*", r"\1", endpoint)],
        "x-usos4j-method": f"{class_name}#{method_name}",
        "parameters": build_query_parameters(params.get(params_type, []), params, arguments, body, records, enums),
        "responses": {"200": {"description": "Successful response"}, "default": error_response()},
        "security": [{"usosUser" if "requestWithAccessToken(" in body else "usosConsumer": []}],
    }
    if notes:
        operation["description"] = " ".join(notes)
    if return_type != "void":
        operation["responses"]["200"]["content"] = {
            "application/json": {"schema": response_schema(return_type, records, enums)}
        }
    return operation


def response_schema(return_type, records, enums):
    schema = type_to_schema(return_type, records, enums)
    if schema.get("format") in ("date", "date-time"):
        return {"type": "string", "description": "ISO 8601 " + schema["format"]}
    return schema


def operation_id(endpoint):
    first, *rest = re.split(r"[/_]", endpoint.removeprefix("services/"))
    return first + "".join(part.capitalize() for part in rest)


def merge_overload(operation, overload):
    known = {parameter["name"]: parameter for parameter in operation["parameters"]}
    for parameter in overload["parameters"]:
        if parameter["name"] not in known:
            operation["parameters"].append({**parameter, "required": False})
        elif parameter["name"] == "fields" and "default" not in known["fields"]["schema"] and "default" in parameter["schema"]:
            known["fields"]["schema"]["default"] = parameter["schema"]["default"]
    for requirement in overload["security"]:
        if requirement not in operation["security"]:
            operation["security"].append(requirement)


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


def load_apiref(path=APIREF_SNAPSHOT):
    snapshot = json.loads(path.read_text(encoding="utf-8")) if path.exists() else {}
    return {key: snapshot.get(key, {}) for key in ("methods", "modules", "scopes")}


def one_line(text):
    return re.sub(r"\s+", " ", text).strip()


def first_sentence(text):
    return re.split(r"(?<=[.!?])\s", one_line(text), maxsplit=1)[0]


def argument_default(argument, schema):
    value = argument.get("default")
    if not value or re.search(r"\s|^\(", value) or "$ref" in schema or "format" in schema:
        return None
    try:
        if schema.get("type") == "integer":
            return int(value)
        if schema.get("type") == "number":
            return float(value)
        if schema.get("type") == "boolean":
            return {"true": True, "false": False}[value.lower()]
    except (ValueError, KeyError):
        return None
    if schema.get("type") == "string" and ("enum" not in schema or value in schema["enum"]):
        return value
    return None


def enrich_parameters(operation, entry):
    arguments = {argument["name"]: argument for argument in entry.get("arguments", [])}
    for parameter in operation["parameters"]:
        argument = arguments.get(parameter["name"])
        if not argument or parameter["name"] == "fields":
            continue
        description = [argument.get("description"), parameter.get("description")]
        parameter["description"] = "\n\n".join(dict.fromkeys(text for text in description if text))
        if argument.get("required"):
            parameter["required"] = True
        if argument.get("deprecated"):
            parameter["deprecated"] = True
        default = argument_default(argument, parameter["schema"])
        if default is not None and not parameter["required"] and "default" not in parameter["schema"]:
            parameter["schema"]["default"] = default


def apiref_security(auth):
    token = auth.get("token")
    if token == "required":
        return [{"usosUser": []}]
    if token == "optional":
        return [{"usosConsumer": []}, {"usosUser": []}]
    return [{"usosConsumer": []}]


def enrich_security(operation, auth):
    for requirement in apiref_security(auth):
        if requirement not in operation["security"]:
            operation["security"].append(requirement)
    if auth.get("scopes"):
        operation["x-usos-scopes"] = auth["scopes"]


def authorization_lines(auth, scopes):
    lines = [f"**Authorization:** consumer key {auth.get('consumer', 'ignored')}, access token {auth.get('token', 'ignored')}."]
    if auth.get("administrative_only"):
        lines.append("Only administrative consumers may call this method.")
    if auth.get("ssl_required"):
        lines.append("Requires HTTPS.")
    if auth.get("scopes"):
        lines.append("**Scopes:** " + ", ".join(
            f"`{scope}`" + (f" ({first_sentence(scopes[scope])})" if scope in scopes else "") for scope in auth["scopes"]
        ))
    return lines


def deprecation_line(deprecated):
    details = [deprecated.get("reason"), deprecated.get("deprecated_by") and f"Use `{deprecated['deprecated_by']}` instead.",
               deprecated.get("present_until") and f"Present until {deprecated['present_until']}."]
    return " ".join(["**Deprecated.**", *[detail for detail in details if detail]])


def operation_description(operation, entry, scopes):
    brief = entry.get("brief_description")
    sections = [operation.get("description")]
    if entry.get("description") and entry["description"] != brief:
        sections.append(entry["description"])
    if entry.get("deprecated"):
        sections.append(deprecation_line(entry["deprecated"]))
    sections.append("\n\n".join(authorization_lines(entry.get("auth", {}), scopes)))
    if entry.get("ref_url"):
        sections.append(f"[Official documentation]({entry['ref_url']})")
    return "\n\n".join(section for section in sections if section)


def response_schema_name(operation):
    schema = operation["responses"]["200"].get("content", {}).get("application/json", {}).get("schema", {})
    reference = schema.get("$ref") or schema.get("items", {}).get("$ref")
    return reference.rsplit("/", 1)[-1] if reference else None


def describe_result_fields(operation, entry, schemas):
    schema = schemas.get(response_schema_name(operation))
    if not schema:
        return
    for field in entry.get("result_fields", []):
        target = schema["properties"].get(field["name"])
        if target is not None and field.get("description") and "description" not in target and "$ref" not in target:
            target["description"] = field["description"]


def enrich_operation(operation, entry, schemas, scopes):
    if entry.get("brief_description"):
        operation["summary"] = one_line(entry["brief_description"])
    operation["description"] = operation_description(operation, entry, scopes)
    if entry.get("ref_url"):
        operation["externalDocs"] = {"description": "Official documentation", "url": entry["ref_url"]}
    if entry.get("deprecated"):
        operation["deprecated"] = True
    if entry.get("returns"):
        operation["responses"]["200"]["description"] = entry["returns"]
    enrich_parameters(operation, entry)
    enrich_security(operation, entry.get("auth", {}))
    describe_result_fields(operation, entry, schemas)


def apply_apiref(paths, schemas, apiref):
    for url, path in paths.items():
        entry = apiref["methods"].get(url.lstrip("/"))
        if entry:
            enrich_operation(path["get"], entry, schemas, apiref["scopes"])


def build_tags(paths, apiref):
    tags = []
    for name in sorted({tag for path in paths.values() for tag in path["get"]["tags"]}):
        module = apiref["modules"].get(f"services/{name}", {})
        description = "\n\n".join(text for text in (module.get("title"), module.get("description")) if text)
        tags.append({"name": name, **({"description": description} if description else {})})
    return tags


def read_version():
    return re.search(r"<version>([^<]+)</version>", POM.read_text(encoding="utf-8")).group(1)


def build_specification():
    model_files = read_java_files(SOURCE_ROOT / "model")
    api_files = read_java_files(SOURCE_ROOT / "api")
    records, params, enums = collect_models(model_files)
    paths = build_paths(api_files, params, records, enums)
    schemas = build_schemas(records, enums)
    apiref = load_apiref()
    apply_apiref(paths, schemas, apiref)
    return {
        "openapi": "3.0.3",
        "info": {
            "title": "USOS API as seen by usos4j",
            "version": read_version(),
            "license": {"name": "MIT", "url": "https://github.com/WMS-DEV/usos4j/blob/main/LICENSE"},
            "description": "USOS API endpoints as implemented in usos4j. Enter your own consumer key and secret (and optionally an access token) in the panel above: requests are signed with OAuth 1.0a in your browser and sent straight to the chosen USOS installation.",
        },
        "servers": [{
            "url": "{baseUrl}",
            "variables": {"baseUrl": {"default": "https://apps.usos.pw.edu.pl/", "description": "Base URL of a USOS installation."}},
        }],
        "externalDocs": {"description": "USOS API documentation", "url": "https://apps.usos.edu.pl/developers/api/"},
        "tags": build_tags(paths, apiref),
        "paths": paths,
        "components": {
            "securitySchemes": {
                "usosConsumer": {
                    "type": "apiKey",
                    "in": "header",
                    "name": "Authorization",
                    "description": "OAuth 1.0a Authorization header signed with the consumer key and secret only (HMAC-SHA1).",
                },
                "usosUser": {
                    "type": "apiKey",
                    "in": "header",
                    "name": "Authorization",
                    "description": "OAuth 1.0a Authorization header signed with the consumer key and secret and the user's access token and secret (HMAC-SHA1).",
                },
            },
            "schemas": prune_schemas(paths, schemas),
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
