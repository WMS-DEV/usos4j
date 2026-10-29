import unittest

import generate_openapi as openapi

MODELS = {
    "address": 'public record UsosAddress(String street, String cityName) implements UsosCommonObject {}',
    "plain": 'public record UsosPlain(String value) implements UsosObject {}',
    "person": """
        public record UsosPerson(
                String id,
                @JsonAlias("alias_name") String name,
                @ExcludeField String hidden,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate birthDate,
                UsosAddress homeAddress,
                List<UsosAddress> offices,
                UsosPlain plain,
                List<String> tags) implements UsosObject {}
    """,
    "node": "public record UsosNode(String id, UsosNode parent) implements UsosCommonObject {}",
    "tree": "public record UsosTree(String id, UsosNode root) implements UsosObject {}",
    "params": """
        @Builder
        public record UsosThingParams(String thingId, UsosLanguage lang) implements UsosParams {
            public static UsosThingParamsBuilder builder(String thingId) {
                return new UsosThingParamsBuilder().thingId(thingId);
            }
        }
    """,
}


def collect():
    return openapi.collect_models(MODELS)


class ResponseSelectorTest(unittest.TestCase):
    def setUp(self):
        self.records, self.params, self.enums = collect()

    def test_uses_alias_skips_excluded_and_expands_common_objects(self):
        selector = openapi.response_selector("UsosPerson", self.records)
        self.assertEqual(
            "id|alias_name|birth_date|home_address[street|city_name]|offices[street|city_name]|plain|tags",
            selector,
        )

    def test_does_not_expand_objects_that_are_not_common(self):
        self.assertIn("|plain|", openapi.response_selector("UsosPerson", self.records))

    def test_stops_on_self_referencing_records(self):
        self.assertEqual("id|root[id|parent]", openapi.response_selector("UsosTree", self.records))

    def test_parses_records_with_annotations_containing_parentheses(self):
        names = [component["name"] for component in self.records["UsosPerson"]["components"]]
        self.assertEqual(["id", "name", "hidden", "birthDate", "homeAddress", "offices", "plain", "tags"], names)


class ParametersTest(unittest.TestCase):
    def setUp(self):
        self.records, self.params, self.enums = collect()

    def test_params_record_builder_arguments_are_required(self):
        self.assertEqual([("String", "thingId", True, False), ("UsosLanguage", "lang", False, False)], self.params["UsosThingParams"])

    def test_plain_argument_uses_the_key_sent_on_the_wire(self):
        body = 'requestFactory.get("services/calendar/calendar_event", Map.of("fields", List.of("id", "name"), "id", List.of(eventId)))'
        parameters = openapi.build_plain_parameters("String eventId", self.params, body, self.records, self.enums)
        self.assertEqual("id", parameters[0]["name"])
        self.assertTrue(parameters[0]["required"])

    def test_argument_name_is_not_matched_inside_a_string_literal(self):
        body = 'Map.of("fields", List.of("name", "short_name"), "name", List.of(name))'
        self.assertEqual("name", openapi.wire_key(body, "name"))

    def test_list_arguments_become_pipe_separated_strings(self):
        parameters = openapi.build_plain_parameters("List<String> articleIds", self.params, "", self.records, self.enums)
        self.assertEqual({"type": "string"}, parameters[0]["schema"])
        self.assertEqual("article_ids", parameters[0]["name"])

    def test_fields_default_comes_from_the_selector_or_the_literal_list(self):
        selector_body = "FieldSelector.fromRequest(UsosAddress.class)"
        literal_body = 'Map.of("fields", List.of("id", "title"))'
        self.assertEqual("street|city_name", openapi.default_fields(selector_body, self.records))
        self.assertEqual("id|title", openapi.default_fields(literal_body, self.records))
        self.assertIsNone(openapi.default_fields("request()", self.records))

    def test_fields_parameter_is_listed_once_with_the_default(self):
        parameters = openapi.build_query_parameters([], self.params, "String fields", 'Map.of("fields", List.of("id"))', self.records, self.enums)
        fields = [parameter for parameter in parameters if parameter["name"] == "fields"]
        self.assertEqual(1, len(fields))
        self.assertEqual("id", fields[0]["schema"]["default"])


class SpecificationTest(unittest.TestCase):
    def test_operation_ids_are_camel_case_and_unique_per_endpoint(self):
        self.assertEqual("apirefMethod", openapi.operation_id("services/apiref/method"))
        self.assertEqual("crstestsFolderNodeDetails", openapi.operation_id("services/crstests/folder_node_details"))

    def test_bare_date_responses_are_plain_strings_for_client_generators(self):
        records, params, enums = collect()
        self.assertEqual({"type": "string", "description": "ISO 8601 date-time"}, openapi.response_schema("LocalDateTime", records, enums))
        self.assertEqual({"type": "string"}, openapi.response_schema("String", records, enums))

    def test_unused_schemas_are_pruned(self):
        records, params, enums = collect()
        schemas = openapi.build_schemas(records, enums)
        paths = {"/x": {"get": {"responses": {"200": {"content": {"application/json": {"schema": {"$ref": "#/components/schemas/UsosTree"}}}}}}}}
        self.assertEqual({"UsosTree", "UsosNode"}, set(openapi.prune_schemas(paths, schemas)))

    def test_security_alternatives_are_merged_for_overloads(self):
        operation = {"parameters": [], "security": [{"usosConsumer": []}]}
        openapi.merge_overload(operation, {"parameters": [], "security": [{"usosUser": []}]})
        self.assertEqual([{"usosConsumer": []}, {"usosUser": []}], operation["security"])


if __name__ == "__main__":
    unittest.main()
