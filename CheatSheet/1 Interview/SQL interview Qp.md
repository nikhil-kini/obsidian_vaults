
HAVING VS WHERE

WHERE - on the columns values
HAVING - on the aggregated data from GROUP BY, SUM etc

## JOIN VS SUBQUERY

| Concept      | Description                                                                                                                      |
| ------------ | -------------------------------------------------------------------------------------------------------------------------------- |
| **JOIN**     | Combines data from two or more tables **horizontally** (side by side) based on related columns.                                  |
| **Subquery** | A query **inside another query** that returns data for the outer query — can act as a filter, derived table, or computed column. |

|Feature|JOIN|Subquery|
|---|---|---|
|**Working**|Combines tables before filtering|Executes inner query first, result used in outer|
|**Performance**|Usually **faster**, optimized by DB engine|Often **slower**, especially correlated subqueries|
|**Readability**|More intuitive for combining related data|Easier when nesting logic or conditional filters|
|**Use Case**|Reporting, data merging|Filtering, aggregation, existence checks|
|**Output**|Returns combined columns|Often returns one column or aggregated value|
|**Optimization**|DB uses indexes efficiently|May create temporary result sets|
```sql
SELECT name,
       (SELECT dept_name
        FROM departments d
        WHERE d.dept_id = e.dept_id) AS dept_name
FROM employees e;
```

## What is an Index?

An **index** in a database is like an index in a book — it helps you find data **faster** without scanning the entire table.  
It is a **data structure (typically a B-Tree)** that improves the speed of data retrieval operations.

## Two Main Types of Indexes

| Type                    | Also Called     | Data Storage       | Sorting             | Physical Order                       |
| ----------------------- | --------------- | ------------------ | ------------------- | ------------------------------------ |
| **Clustered Index**     | Primary Index   | Table data itself  | Sorted by index key | Data rows **physically arranged**    |
| **Non-Clustered Index** | Secondary Index | Separate structure | Sorted by index key | **Has pointers** to actual data rows |
 
 
### TABLE with Clustered Index (on emp_id)
 ---------------------------------------

 | emp_id | name  | age |
 |--------|-------|-----|
 | 1      | John  | 25  |
 | 2      | Mary  | 30  |
 | 3      | Alex  | 28  |

### Non-Clustered Index (on name)
---------------------------------
| name | pointer (emp_id) |
|------|------------------|
| Alex | 3                |
| John | 1                |
| Mary | 2                |

```sql
CREATE TABLE Orders (
    order_id INT PRIMARY KEY,   -- Clustered index
    customer_name VARCHAR(100),
    order_date DATE
);

-- Non-clustered index
CREATE INDEX idx_order_date
ON Orders(order_date);
```

## What is a Table?

A **Table** is a **physical structure** in the database that **stores data permanently** in rows and columns.  
It’s the **core storage object** in SQL databases.

### ✅ Example

`CREATE TABLE Employees (     emp_id INT PRIMARY KEY,     name VARCHAR(50),     salary DECIMAL(10,2),     department VARCHAR(50) );`

➡️ This actually **creates a physical space** in the database where data is stored.

## What is a View?

A **View** is a **virtual table** — it **does not store data physically**, but rather **displays data** retrieved from one or more tables via a SQL query.

It’s like a **saved SQL query** that acts as a table.

### ✅ Example

`CREATE VIEW EmployeeSalaryView AS SELECT name, salary FROM Employees WHERE salary > 50000;`

➡️ This doesn’t store data itself — it just **shows results dynamically** when queried.

|Feature|**Table**|**View**|
|---|---|---|
|**Nature**|Physical object|Logical (virtual) object|
|**Storage**|Data is stored in the table|No data storage (depends on base tables)|
|**Data**|Contains actual rows|Displays data from one or more tables|
|**Definition**|Created using `CREATE TABLE`|Created using `CREATE VIEW`|
|**Modification**|You can insert/update/delete directly|Usually read-only (some updatable)|
|**Performance**|Direct data access → faster|Slightly slower (query executed every time)|
|**Dependency**|Independent|Dependent on base tables|
|**Security**|No data masking|Can restrict columns/rows for user access|
|**Usage**|Data storage|Data abstraction and simplified queries|

## Updatable Views

Some views **can** be updated (depending on database and conditions).

Example:

```sql
CREATE VIEW SimpleView AS SELECT emp_id, name FROM Employees;
```

You can:

```sql
UPDATE SimpleView SET name = 'Alice' WHERE emp_id = 101;
```

✅ Works — because it maps directly to a single table and column.

But if the view uses:
- `JOIN`
- `GROUP BY`
- `DISTINCT`
- `AGGREGATE functions`

then it’s **non-updatable**.

## Materialized View (Special Case)

A **Materialized View** is like a hybrid:

- It **stores data physically** (like a table)
- But it’s **based on a query** (like a view)
- You must **refresh** it manually or automatically to sync with base tables.

```sql
CREATE MATERIALIZED VIEW MonthlySales AS SELECT customer_id, SUM(amount) AS total FROM Orders GROUP BY customer_id;
```


✅ Fast to query (since data is stored)  
⚠️ Needs refresh if base data changes.

## What is a Stored Procedure?

A **Stored Procedure** is a **precompiled block of SQL code** (like a function or method) that is **stored in the database** and can be **executed repeatedly**.

It allows you to group **SQL statements (SELECT, INSERT, UPDATE, DELETE)** and **business logic** together under a single name

```sql
CREATE PROCEDURE procedure_name
    @param1 datatype,
    @param2 datatype = default_value
AS
BEGIN
    -- SQL logic here
END;
```

```sql
CREATE PROCEDURE AddEmployee
    @Name VARCHAR(50),
    @Department VARCHAR(50),
    @Salary DECIMAL(10,2)
AS
BEGIN
    INSERT INTO Employees (name, department, salary)
    VALUES (@Name, @Department, @Salary);
END;
```

```sql
EXEC AddEmployee 'John', 'IT', 65000;
```

## Input and Output Parameters

Stored Procedures can take:

- **IN parameters** – send data to procedure
- **OUT parameters** – return data from procedure
- **INOUT parameters** – both directions (some DBs)

```sql
CREATE PROCEDURE GetSalary
    @EmpId INT,
    @Salary DECIMAL(10,2) OUTPUT
AS
BEGIN
    SELECT @Salary = salary FROM Employees WHERE emp_id = @EmpId;
END;
```

```sql
DECLARE @EmpSalary DECIMAL(10,2);
EXEC GetSalary 101, @EmpSalary OUTPUT;
PRINT @EmpSalary;
```

## Conditional Logic in Stored Procedures

Stored Procedures can include **logic, loops, and conditions**, just like a programming language.
```sql
CREATE PROCEDURE UpdateSalary
    @EmpId INT,
    @Increment DECIMAL(10,2)
AS
BEGIN
    IF EXISTS (SELECT * FROM Employees WHERE emp_id = @EmpId)
        UPDATE Employees
        SET salary = salary + @Increment
        WHERE emp_id = @EmpId;
    ELSE
        PRINT 'Employee not found';
END;
```

## Stored Procedure Returning a Result Set

A stored procedure can also return **multiple rows** (just like a SELECT query).
```sql
CREATE PROCEDURE ListHighEarners
    @MinSalary DECIMAL(10,2)
AS
BEGIN
    SELECT * FROM Employees WHERE salary > @MinSalary;
END;
```

```sql
EXEC ListHighEarners 80000;
```

## Dropping or Altering Procedures
```sql
DROP PROCEDURE GetEmployeeDetails;
```

```sql
ALTER PROCEDURE GetEmployeeDetails
AS
BEGIN
    SELECT name, department FROM Employees;
END;
```

| Feature      | **Stored Procedure**                        | **Function**                     |
| ------------ | ------------------------------------------- | -------------------------------- |
| Return type  | Can return multiple values (via OUT params) | Must return one value            |
| Use in SQL   | Executed with `EXEC`                        | Can be used inside queries       |
| Side effects | Can modify data (INSERT, UPDATE)            | Cannot modify data (in most DBs) |
| Transactions | Supports                                    | Limited                          |
| Example      | `EXEC MyProc`                               | `SELECT MyFunc()`                |
