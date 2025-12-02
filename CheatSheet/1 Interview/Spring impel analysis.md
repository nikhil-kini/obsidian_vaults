⚠️ Why interface constants are discouraged (best practice)

Using interfaces only to hold constants is considered a bad practice (“Constant Interface Anti-pattern”) because:

It pollutes the namespace of implementing classes.

**Interfaces should define behavior, not constants.**

| Feature                            | Interface Variable                      | Class `public static final` Variable    |
| ---------------------------------- | --------------------------------------- | --------------------------------------- |
| Modifiers                          | Always `public static final` (implicit) | Explicitly declared                     |
| Initialization                     | Required                                | Required for `final`                    |
| Place                              | Inside interface only                   | Inside class only                       |
| Purpose                            | Constants shared by contracts / APIs    | Class-level constants or configurations |
| Can be overridden?                 | No                                      | No, but can be shadowed                 |
| Can interface have mutable fields? | **No**                                  | Class can have other mutable fields     |


Better alternatives:

A final class Constants { ... }
Enum for grouped constants

### Use interface variables when:

You want constants associated with an API contract

Example: HTTP status codes, error codes, message types

### Use class static final variables when:

The constant belongs to a specific class behavior

Example: configuration values, utility constants

---

```java
.map(dto -> {
    dto.setTransactionId("123");   -- do not use peek for modification of data
    return dto;
}).toList();
```

```java
.peek(dto -> System.out.println(dto))
```

| Feature                       | `peek`                   | `map`                               |
| ----------------------------- | ------------------------ | ----------------------------------- |
| **Intended for**              | Debugging / side effects | Transformation                      |
| **Returns modified element?** | No (same element)        | Yes                                 |
| **Should mutate objects?**    | ❌ Avoid                  | ✔ Acceptable                        |
| **Safe in production code?**  | Usually no               | Yes                                 |
| **Common use cases**          | Logging                  | Updating elements, converting types |

| Situation                                                                                    | Safe? | Explanation                                                   |
| -------------------------------------------------------------------------------------------- | ----- | ------------------------------------------------------------- |
| Objects are designed to be mutable (DTOs, POJOs)                                             | ✔ Yes | Mutation is part of their purpose.                            |
| Stream is **sequential**, not parallel                                                       | ✔ Yes | No race conditions or shared-state hazards.                   |
| Mutation is part of the **logical transformation** (normalizing, cleaning, enriching fields) | ✔ Yes | `map` is semantically a transformation step.                  |
| Mutation is **local** to the pipeline and not used elsewhere                                 | ✔ Yes | No external code depends on pre-mutation state.               |
| Mutation is **deterministic** and idempotent                                                 | ✔ Yes | Running it again produces same result; predictable behavior.  |
| Objects are shared across threads or reused elsewhere                                        | ❌ No  | Other code may observe inconsistent state.                    |
| Using **parallelStream()**                                                                   | ❌ No  | Objects can be mutated by multiple threads → race conditions. |
| Mutation involves **external side effects** (DB update, HTTP call, logging)                  | ❌ No  | `map` should not trigger significant side effects.            |
| Mutation depends on external mutable state                                                   | ❌ No  | Introduces nondeterministic behavior; order matters.          |
| Expected behavior is to generate **new immutable objects**                                   | ❌ No  | Violates immutability and functional design.                  |

---

```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoClass {

// Do NOT include fields in the JSON output if their value is null.  -> when serialization
```

