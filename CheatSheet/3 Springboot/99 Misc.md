`JsonNode` and `Map<String, Object>` are both good choices for JSON columns, but they serve slightly different purposes.

##1. Map<String, Object>
@JdbcTypeCode(SqlTypes.JSON)
private Map<String, Object> settings;

JSON:

{
  "launchUrl": "https://example.com",
  "deepLinking": true,
  "retryCount": 3
}

Becomes:

Map<String, Object> settings;

Access:

String launchUrl = (String) settings.get("launchUrl");
Boolean deepLinking = (Boolean) settings.get("deepLinking");
Integer retryCount = (Integer) settings.get("retryCount");
Pros
Feels natural to Java developers.
Easy to add/remove keys.
Easy to serialize back to JSON.
Good for simple JSON structures.
Cons
Lots of casting.
Nested objects become more casting:
Map<String, Object> lti =
    (Map<String, Object>) settings.get("lti");

String version = (String) lti.get("version");
Runtime errors if the expected type is wrong.
2. JsonNode
@JdbcTypeCode(SqlTypes.JSON)
private JsonNode settings;

Access:

String launchUrl =
    settings.path("launchUrl").asText();

boolean deepLinking =
    settings.path("deepLinking").asBoolean();

Nested JSON:

{
  "lti": {
    "version": "1.3"
  }
}
String version =
    settings.path("lti")
            .path("version")
            .asText();
Pros
No casting.
Safe navigation with path().
Better for deeply nested JSON.
Rich API for traversing JSON trees.
Preserves JSON semantics exactly.
Cons
Less Java-friendly than a Map.
More verbose for simple key/value access.
