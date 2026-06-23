`JsonNode` and `Map<String, Object>` are both good choices for JSON columns, but they serve slightly different purposes.

## 1. Map<String, Object>
```
@JdbcTypeCode(SqlTypes.JSON)
private Map<String, Object> settings;
```
JSON:
```
{
  "launchUrl": "https://example.com",
  "deepLinking": true,
  "retryCount": 3
}
```
Becomes:
```
Map<String, Object> settings;
```
Access:
```
String launchUrl = (String) settings.get("launchUrl");
Boolean deepLinking = (Boolean) settings.get("deepLinking");
Integer retryCount = (Integer) settings.get("retryCount");
```
### Pros
- Feels natural to Java developers.
- Easy to add/remove keys.
- Easy to serialize back to JSON.
- Good for simple JSON structures.
### Cons
- Lots of casting.
- Nested objects become more casting:
```
Map<String, Object> lti =
    (Map<String, Object>) settings.get("lti");

String version = (String) lti.get("version");
```
Runtime errors if the expected type is wrong.

## 2. JsonNode
```
@JdbcTypeCode(SqlTypes.JSON)
private JsonNode settings;
```
Access:
```
String launchUrl =
    settings.path("launchUrl").asText();

boolean deepLinking =
    settings.path("deepLinking").asBoolean();
```
Nested JSON:
```
{
  "lti": {
    "version": "1.3"
  }
}
```
```
String version =
    settings.path("lti")
            .path("version")
            .asText();
```
### Pros
- No casting.
- Safe navigation with path().
- Better for deeply nested JSON.
- Rich API for traversing JSON trees.
- Preserves JSON semantics exactly.
### Cons
- Less Java-friendly than a Map.
- More verbose for simple key/value access.

## Recommendation
Use a **POJO** when the schema is known
```
@JdbcTypeCode(SqlTypes.JSON)
private LtiSettings settings;
```
This is the cleanest and most type-safe.

Use **Map<String, Object>** when
- JSON is dynamic.
- Mostly flat key/value pairs.
- You want easy manipulation.

Use **JsonNode** when
- JSON can be deeply nested.
- Structure varies significantly.
- You mainly read/traverse JSON rather than modify it.

----

## Why `@Data` is Bad for Entity Classes

- Unwanted Database Queries: The auto-generated `toString()` method will traverse and print all properties, including lazily-loaded relationships. This can accidentally trigger heavy, unintended `SELECT` queries or cause.
- Infinite Recursion: If you have bidirectional relationships (e.g., `User` has `Orders`, and `Order` has a `User`), calling `toString()` on one entity will cause both to recursively print each other, resulting in a **StackOverflowError**.
- Broken Entity Identity: The default` @EqualsAndHashCode` compares every field in the class. For entities, this can cause major issues. If an entity contains uninitialized lazy-loaded fields, they may evaluate to `null`, changing the hash code of the object before and after it is fetched from the database. Furthermore, using mutable auto-incremented primary keys can break the integrity of `HashSet` and `HashMap` collections.
- Missing No-Argument Constructor: JPA entities strictly require a **public** or **protected** no-argument constructor. `@Data` only generates a constructor for `final` and `@NonNull` fields, which does not satisfy the JPA specification.


---

## is it ok for a dto to use a domain class in nested pojo?

### Why You Should Avoid It?
- Leaking Database Inners: The primary goal of a DTO is to decouple your API layer from your database layer. If your DTO contains a nested Entity, you are still exposing your database schema directly to the client.
- LazyInitializationException: If the nested Domain POJO contains lazily-loaded relationships, trying to serialize the DTO (for example, converting it to JSON to send as an API response) will trigger those fields outside the database transaction. This causes the application to crash with a LazyInitializationException.
- Serialization Loops: If your nested domain model has bidirectional relationships, the JSON serializer (like Jackson) will get stuck in an infinite loop trying to serialize them, leading to a StackOverflowError.Security & Over-posting Vulnerabilities: If a client sends a request payload mapping directly to that DTO, a malicious user can alter fields in the nested Domain POJO (like changing a user's role or an account balance) that they shouldn't have access to modify.

```
// DTO containing a raw Domain Entity
public class OrderResponseDTO {
    private Long orderId;
    private Double totalAmount;
    private Customer customerEntity; // ❌ BAD: Leaks database model and risks LazyInitException
}
```
```
// Parent DTO
public class OrderResponseDTO {
    private Long orderId;
    private Double totalAmount;
    private CustomerSummaryDTO customer; //  GOOD: A separate, flat DTO for the client
}

// Nested Child DTO
public class CustomerSummaryDTO {
    private Long id;
    private String name;
    private String email; // Only the bare minimum fields the API client actually needs
}
```
HOWEVER ENUMS are fine
```
// The shared Enum
public enum OrderStatus {
    PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

// The DTO (Perfectly safe to use the Enum here)
public class OrderResponseDTO {
    private Long orderId;
    private Double totalAmount;
    private OrderStatus status; // ✅ SAFE and clean
}
```
