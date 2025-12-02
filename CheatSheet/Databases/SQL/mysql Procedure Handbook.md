## SQL Stored Procedure

A stored procedure is a block of SQL code stored in the database that you can run whenever you need it — like a reusable function.

## Basic Structure of a Stored Procedure (MySQL)

```sql
DELIMITER $$

CREATE PROCEDURE hello_world()
BEGIN
    SELECT 'Hello from a stored procedure!' AS message;
END $$

DELIMITER ;
```

Run it:

```sql
CALL hello_world();
```

`DELIMITER $$`
MySQL needs a different ending symbol because procedures contain many semicolons.

`CREATE PROCEDURE name()`
Defines the procedure.

`BEGIN … END`
The block of code that runs.

`DELIMITER ;`
Reset normal semicolon after the procedure is created.

## Procedure with Input Parameters

```sql
DELIMITER $$

CREATE PROCEDURE greet_user(IN userName VARCHAR(50))
BEGIN
    SELECT CONCAT('Hello, ', userName, '!') AS greeting;
END $$

DELIMITER ;
```

```sql
CALL greet_user('Alice');
```

```output
Hello, Alice!
```

## Procedure with Output Parameters

```sql
DELIMITER $$

CREATE PROCEDURE add_numbers(
    IN a INT,
    IN b INT,
    OUT result INT
)
BEGIN
    SET result = a + b;
END $$

DELIMITER ;
```

```sql
SET @outValue = 0;
CALL add_numbers(5, 7, @outValue);
SELECT @outValue;
```

## Using Variables Inside a Procedure

```sql
DELIMITER $$

CREATE PROCEDURE show_time()
BEGIN
    DECLARE currentTime VARCHAR(30);

    SET currentTime = NOW();

    SELECT currentTime AS 'Server Time';
END $$

DELIMITER ;
```

## Conditionals (IF / ELSE)

```sql
DELIMITER $$

CREATE PROCEDURE check_age(IN age INT)
BEGIN
    IF age >= 18 THEN
        SELECT 'Adult' AS status;
    ELSE
        SELECT 'Minor' AS status;
    END IF;
END $$

DELIMITER ;
```

## Loops (WHILE Example)

```sql
DELIMITER $$

CREATE PROCEDURE count_to_five()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 5 DO
        SELECT i AS number;
        SET i = i + 1;
    END WHILE;
END $$

DELIMITER ;
```

## Using Cursors (for multi-row processing)

Cursors let you iterate through rows like a loop.
```sql
DELIMITER $$

CREATE PROCEDURE list_users()
BEGIN
    DECLARE finished INT DEFAULT 0;
    DECLARE userName VARCHAR(100);

    DECLARE user_cursor CURSOR FOR
        SELECT name FROM users;      -- Declare a array of rows

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;  -- Declare a Handler, to handle case: when the loop has no row left to loop, i.e set finished to 1.

    OPEN user_cursor;     -- Start operation of cursor

    read_loop: LOOP
        FETCH user_cursor INTO userName;

        IF finished = 1 THEN
            LEAVE read_loop;
        END IF;

        SELECT userName AS user;
    END LOOP;

    CLOSE user_cursor;
END $$

DELIMITER ;
```

```sql
DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;
```

When using a cursor, you fetch rows one by one:

```sql
FETCH cursor_name INTO someVariable;  -- Get the next row from the cursor and put the values into variables
```

- When there are no more rows, MySQL triggers a condition called NOT FOUND.
- Without a handler, the procedure would stop with an error.
- With this handler, instead of stopping, MySQL will:

✔ Continue the procedure
✔ Set finished = 1
✔ Allow your loop to exit gracefully

Cursor fetches a row → `finished = 0`
Cursor fetches next → `finished = 0`
Cursor runs out of rows → NOT FOUND triggers → handler sets `finished = 1`
Loop checks `finished = 1` → exits the loop safely

```sql
OPEN user_cursor; -- Start using this cursor and prepare the rows so I can fetch them.
```
**What it does:**
Executes the SELECT query attached to the cursor
Prepares a result set
Positions the cursor before the first row

```sql
CLOSE user_cursor; -- I’m done. Clean up the cursor and release memory
```

```sql
read_loop: LOOP
```
`read_loop` → this is the name of the loop
`LOOP` → this starts the loop block

end it with:
``sql
END LOOP;
```
or you can specifically reference the name:
```sql
END LOOP read_loop;
```

```sql
read_loop: LOOP
    FETCH user_cursor INTO userName;

    IF finished = 1 THEN
        LEAVE read_loop;  -- exit the loop using its name
    END IF;

    SELECT userName;
END LOOP read_loop;
```

`LEAVE read_loop`
→ This breaks out of the loop (like `break` in other languages)

`ITERATE read_loop`
→ This jumps back to the beginning of the loop (like `continue`)

### `FOR` 
```sql
DECLARE user_cursor CURSOR FOR SELECT name FROM users; -- this cursor will read rows from the following SELECT query.
```
DECLARE user_cursor CURSOR → create a cursor named user_cursor
FOR → “attach the cursor to…”
SELECT name FROM users → “this query’s rows”

## Dropping a Procedure

```sql
DROP PROCEDURE IF EXISTS procedure_name;
```


