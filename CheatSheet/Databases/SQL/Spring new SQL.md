## MySQL JSON_SET — Quick Definition

```sql
JSON_SET(json_doc, path, val[, path, val] ...)
```

- json_doc → the original JSON document
- path → JSON path expression ($.field, $.a.b[0], etc.)
- val → new value to set
- If the path exists, the value is replaced
- If the path does not exist, it is added

EX : Update a JSON field:

```sql
UPDATE user
SET profile = JSON_SET(profile, '$.name', 'John Doe')
WHERE id = 1;
```

```sql
from
{"name": "Old Name", "age": 30}
to
{"name": "John Doe", "age": 30}
```

EX : Add a new field

```sql
SELECT JSON_SET('{"a":1}', '$.b', 2);
```

```output
{"a":1, "b":2}
```

EX : Update nested fields

```sql
JSON_SET(profile, '$.address.city', 'New York')
```
If `address` or `city` doesn't exist, MySQL creates them.


EX : Add a new field

```sql
JSON_SET('[10, 20, 30]', '$[1]', 999);
```

```output
[10, 999, 30]
```

EX : Set multiple values at once

```sql
JSON_SET(
    info,
    '$.status', 'active',
    '$.updatedAt', NOW()
)
```

What each part means:
- `info` — the original JSON column/document
- '$.status' — JSON path of the field you want to set
- 'active' — the value to assign
- '$.updatedAt' — another JSON path
- NOW() — second value to assign


EX : `JSON_SET` Inside a SELECT Query

```sql
SELECT JSON_SET(data, '$.score', 100) AS updated_data
FROM game_state;
```

Takes the JSON stored in column data
Updates/sets the score field to 100
Returns the modified JSON as a column called updated_data
But does not write anything back to the table, because this is only a SELECT


---

## WHY COALESCE(name, '')?

**COALESCE(column, fallback)** returns the column unless it is NULL, in which case it returns the fallback value.

So:

If session.name is NOT NULL → keep it unchanged
If session.name is NULL → substitute an empty string ('') so that string concatenation does not become NULL

✔ Without COALESCE:
`CONCAT('GUIDELINE_', name)`

If name is NULL, the entire result becomes NULL, because:
`CONCAT(anything, NULL) → NULL

That would produce a NULL new session name, which is probably not desired and could violate a NOT NULL constraint.

✔ With COALESCE(name, ''):
`CONCAT('GUIDELINE_', COALESCE(name, ''))`

If name is NULL, this becomes:
CONCAT('GUIDELINE_', '')
→ 'GUIDELINE_'

This guarantees you always get a valid string and your insert won’t fail.

---
## What is a Transaction?

A transaction is a group of SQL statements that are executed as one logical unit.
It ensures that either everything succeeds OR nothing is applied.

A transaction must be:

**Atomic** — all or nothing
**Consistent** — database rules are preserved
**Isolated** — doesn't interfere with other transactions
**Durable** — once committed, it stays even after crash

These 4 are called ACID properties.

```sql
CREATE PROCEDURE transfer_funds(
    IN fromAcc INT,
    IN toAcc INT,
    IN amount DECIMAL(10,2)
)
BEGIN
    -- Statement to Rollback when there is a error
    DECLARE exit handler for SQLEXCEPTION
    BEGIN
        ROLLBACK;
    END;

    -Statement in transaction context
    START TRANSACTION;

    UPDATE accounts SET balance = balance - amount WHERE id = fromAcc;
    UPDATE accounts SET balance = balance + amount WHERE id = toAcc;

    COMMIT;  -- finalize the changes, if there is no error.
END;
```
