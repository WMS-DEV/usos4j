import json
import re
import sys
import time
import urllib.error
import urllib.parse
import urllib.request
from html.parser import HTMLParser
from pathlib import Path

import generate_openapi

SNAPSHOT = Path(__file__).with_name("apiref-snapshot.json")
ALLOWED_PREFIX = "services/apiref/"
CALL_LIMIT = 400
CALL_DELAY_SECONDS = 0.35
METHOD_FIELDS = "name|brief_description|description|ref_url|auth_options|arguments|returns|result_fields|beta|deprecated"
STANDARD_ARGUMENTS = {"fields", "format", "callback"}
DOCS_URL ="https://apps.usos.edu.pl"
DEFAULT_BASE_URL = "https://apps.usos.pw.edu.pl/"


class ApiRefClient:
    def __init__(self, base_url):
        self.base_url = base_url.rstrip("/") + "/"
        self.calls = 0

    def get(self, path, query=None):
        if not path.startswith(ALLOWED_PREFIX):
            raise ValueError(f"only {ALLOWED_PREFIX}* may be fetched, not {path}")
        if self.calls >= CALL_LIMIT:
            raise RuntimeError(f"call limit of {CALL_LIMIT} reached")
        url = self.base_url + path + ("?" + urllib.parse.urlencode(query, quote_via=urllib.parse.quote) if query else "")
        self.calls += 1
        time.sleep(CALL_DELAY_SECONDS)
        try:
            with urllib.request.urlopen(url, timeout=30) as response:
                return json.load(response)
        except urllib.error.HTTPError as error:
            raise RuntimeError(f"{path} answered HTTP {error.code}") from None


class MarkdownConverter(HTMLParser):
    def __init__(self, base):
        super().__init__(convert_charrefs=True)
        self.base = base
        self.parts = []
        self.links = []
        self.lists = []
        self.in_pre = False

    def handle_starttag(self, tag, attributes):
        attributes = dict(attributes)
        if tag in ("p", "div", "br"):
            self.parts.append("\n\n" if tag != "br" else "\n")
        elif tag in ("b", "strong"):
            self.parts.append("**")
        elif tag in ("i", "em"):
            self.parts.append("*")
        elif tag in ("code", "tt") and not self.in_pre:
            self.parts.append("`")
        elif tag == "pre":
            self.in_pre = True
            self.parts.append("\n\n```\n")
        elif tag in ("ul", "ol"):
            self.lists.append(tag)
            self.parts.append("\n")
        elif tag == "li":
            self.parts.append("\n" + "  " * (len(self.lists) - 1) + ("1. " if self.lists and self.lists[-1] == "ol" else "- "))
        elif tag == "a":
            href = attributes.get("href") or ""
            self.links.append(urllib.parse.urljoin(self.base, href) if href else "")
            self.parts.append("[")

    def handle_endtag(self, tag):
        if tag in ("p", "div"):
            self.parts.append("\n\n")
        elif tag in ("b", "strong"):
            self.parts.append("**")
        elif tag in ("i", "em"):
            self.parts.append("*")
        elif tag in ("code", "tt") and not self.in_pre:
            self.parts.append("`")
        elif tag == "pre":
            self.in_pre = False
            self.parts.append("\n```\n\n")
        elif tag in ("ul", "ol"):
            if self.lists:
                self.lists.pop()
            self.parts.append("\n")
        elif tag == "a" and self.links:
            href = self.links.pop()
            self.parts.append(f"]({href})" if href else "]")

    def handle_data(self, data):
        self.parts.append(data if self.in_pre else re.sub(r"\s+", " ", data))


def to_markdown(text, base=DOCS_URL):
    if not text:
        return None
    converter = MarkdownConverter(base)
    converter.feed(text)
    converter.close()
    markdown = "".join(converter.parts)
    markdown = re.sub(r"\[\s+", "[", markdown)
    markdown = re.sub(r"\s+\]\(", "](", markdown)
    markdown = re.sub(r"\[\]\([^)]*\)|\[\]", "", markdown)
    markdown = re.sub(r"[ \t]+\n", "\n", markdown)
    markdown = re.sub(r"\n[ \t]+(?=\n)", "\n", markdown)
    markdown = re.sub(r"\n{3,}", "\n\n", markdown)
    return markdown.strip() or None


def compact(value):
    if isinstance(value, dict):
        return {key: compact(item) for key, item in sorted(value.items()) if item not in (None, "", [], {})}
    if isinstance(value, list):
        return [compact(item) for item in value]
    return value


def snapshot_method(method):
    auth = method.get("auth_options") or {}
    return compact({
        "brief_description": to_markdown(method.get("brief_description")),
        "description": to_markdown(method.get("description")),
        "ref_url": method.get("ref_url"),
        "beta": bool(method.get("beta")) or None,
        "deprecated": method.get("deprecated") and compact({
            "deprecated_by": method["deprecated"].get("deprecated_by"),
            "reason": to_markdown(method["deprecated"].get("reason")),
            "present_until": method["deprecated"].get("present_until"),
        }),
        "auth": {
            "consumer": auth.get("consumer"),
            "token": auth.get("token"),
            "administrative_only": bool(auth.get("administrative_only")) or None,
            "ssl_required": bool(auth.get("ssl_required")) or None,
            "scopes": auth.get("scopes"),
        },
        "arguments": [
            {
                "name": argument.get("name"),
                "required": bool(argument.get("is_required")),
                "deprecated": bool(argument.get("is_deprecated")) or None,
                "default": argument.get("default_value"),
                "description": to_markdown(argument.get("description")),
                "type": argument.get("type"),
            }
            for argument in method.get("arguments") or []
            if argument.get("name") not in STANDARD_ARGUMENTS
        ],
        "returns": to_markdown(method.get("returns")),
        "result_fields": [
            {"name": field.get("name"), "description": to_markdown(field.get("description")), "primary": bool(field.get("is_primary")) or None}
            for field in method.get("result_fields") or []
        ],
    })


def snapshot_scopes(scopes):
    return {
        scope["key"]: to_markdown(scope.get("developers_description") or scope.get("description")) or scope["key"]
        for scope in scopes
        if scope.get("key")
    }


def implemented_methods():
    specification = generate_openapi.build_specification()
    return [path.lstrip("/") for path in specification["paths"]]


def snapshot_module(module):
    return compact({"title": to_markdown(module.get("title")), "description": to_markdown(module.get("description"))})


def module_of(method):
    return "/".join(method.split("/")[:2])


def refresh(client, methods, existing):
    known = {entry["name"] for entry in client.get("services/apiref/method_index")}
    snapshot = {"methods": dict(existing.get("methods", {})), "modules": {}, "scopes": {}}
    missing = []
    for name in methods:
        if name not in known:
            missing.append(name)
            snapshot["methods"].pop(name, None)
            continue
        snapshot["methods"][name] = snapshot_method(client.get("services/apiref/method", {"name": name, "fields": METHOD_FIELDS}))
    for name in sorted({module_of(method) for method in snapshot["methods"]}):
        snapshot["modules"][name] = snapshot_module(client.get("services/apiref/module", {"name": name}))
    snapshot["scopes"] = snapshot_scopes(client.get("services/apiref/scopes", {"fields": "key|developers_description"}))
    return snapshot, missing


def main():
    arguments = sys.argv[1:]
    base_url = DEFAULT_BASE_URL
    if arguments and arguments[0].startswith("http"):
        base_url = arguments.pop(0)
    existing = json.loads(SNAPSHOT.read_text(encoding="utf-8")) if SNAPSHOT.exists() else {}
    client = ApiRefClient(base_url)
    snapshot, missing = refresh(client, arguments or implemented_methods(), existing)
    snapshot["methods"] = dict(sorted(snapshot["methods"].items()))
    SNAPSHOT.write_text(json.dumps(snapshot, indent=1, ensure_ascii=False, sort_keys=True) + "\n", encoding="utf-8")
    print(f"{len(snapshot['methods'])} methods in the snapshot, {len(missing)} not documented by apiref, {client.calls} calls")
    for name in missing:
        print("  not in apiref:", name)


if __name__ == "__main__":
    main()
