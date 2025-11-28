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



```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoClass {

// Do NOT include fields in the JSON output if their value is null.  -> when serialization
```

