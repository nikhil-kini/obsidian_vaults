
[Initial PostgreSQL setup on Installation](https://stackoverflow.com/questions/1471571/how-to-configure-postgresql-for-the-first-time)

**Connection options:**
`-h, --host=HOSTNAME`          database server host or socket directory (default:"/var/run/postgresql")
`-p, --port=PORT `                database server port (default: "5432")
`-U, --username=USERNAME`   database user name (default: "wings_of_liberty")
`-w, --no-password`              never prompt for password
`-W, --password`                    force password prompt (should happen automatically)

```sh
psql -h localhost -p 5432 -U <role_name> <database_Name>
```

### Commands in PSQL session

| Commands | Usages |
|------|------|
|`\?` | to get info on more session commands|
|`\c <database_Name>`| to change the database.|
|`\c <database_Name> <user>`| to change the database and user.|
|`\i` | to run a SQL script
|`\l`| to list the databases|
|`\q` | to quit the connections|
|`\d <none/table_name>`| to describe the database, table etc.|


#### To Create a database:
```sql
CREATE DATABASE nameOfDataBase;
```


#### To Delete a database (Permanently!!!!):
```sql
DROP DATABASE nameOfDataBase;
```


#### Create Table:
[DATA_TYPES REF FOR INIT](https://www.postgresql.org/docs/current/datatype.html)
```sql
CREATE TABLE tableName(
colName colType,
..
);
```

Ex:
```sql
CREATE TABLE weather (
city varchar(80),
temp_lo int,
temp_hi int,
prcp real,
date date
);`
```


#### Delete Table:
```sql
DROP TABLE tableName;
```


#### Insert into Tables
```sql
INSERT INTO tableName (colName, ..) VALUES (colValues, ..);
```
Ex:
```sql
INSERT INTO weather VALUES ('San Francisco', 46, 50, 0.25,
'1994-11-27');
```
Note: If the Column and Values to be inserted are same in number and are correspond to respective position in Table Definition, column name specification can be skipped.

Generate Mock-data SQL file from [Mockaroo](https://mockaroo.com/) 
use it to run SQL file from p-shell : `\i fileName_withpath.sql`


#### Display

**For selecting all columns**
```sql 
SELECT * FROM table_name;
```

**For selecting 2 columns**
```sql
SELECT first_col, second_col FROM table_name;
``` 


#### OrderBy

**To sort column (default: ASC)**
```sql 
SELECT * FROM table_name ORDER BY col_name;
``` 

**To sort column DESC**
```sql
SELECT * FROM table_name ORDER BY col_name, col2_name DESC;
```


#### Distinct

**To get distinct values in the column**
```sql
SELECT DISTINCT col_name FROM table_name ORDER BY col_name;
``` 

#### Where And Clause

**To filter rows with value**
```sql
SELECT * FROM table_name WHERE col_name = value;
``` 

**To filter rows with both values**
```sql
SELECT * FROM table_name WHERE col_name = value AND col2_name = value;
``` 

**To filter rows with either values**
```sql
SELECT * FROM table_name WHERE col_name = value OR col2_name = value;` 
```

**To filter rows with combo**
```sql
SELECT * FROM table_name WHERE col_name = value AND (col2_name = value OR col3_name = value);` 
```


#### Distinct

**To get distinct values in the column**
```SQL
SELECT DISTINCT col_name FROM table_name ORDER BY col_name;
```


#### Where And Clause

**To filter rows with value**
```SQL
SELECT * FROM table_name WHERE col_name = value;
```

**To filter rows with both values**
```SQL
SELECT * FROM table_name WHERE col_name = value AND col2_name = value;
```

**To filter rows with either values**
```SQL
SELECT * FROM table_name WHERE col_name = value OR col2_name = value;
```

**To filter rows with combo**
```SQL
SELECT * FROM table_name WHERE col_name = value AND (col2_name = value OR col3_name = value);
```


#### Operators

#### Comparison 

|Operator | Description |
|----|---|
|=   | equals|
|<   | less than
| \>   | greater than
|<=  | less than or equals
|\>=  |  greater than or equals
|\<>  | not equals

#### Limit and Offset

**limit the output to 4 values.**
```SQL
SELECT * FROM table LIMIT 4;
```

**limit the output to 4 values (official SQL way).**
```SQL
SELECT * FROM table FETCH FIRST 4 ROWS ONLY;
```

**leave 5 rows print next 4 rows**
```SQL
SELECT * FROM table OFFSET 5 LIMIT 4;
```


#### IN

**to filter rows with either values**
```SQL
SELECT * FROM table_name WHERE col1_name = value1 OR col1_name = value2 col1_name = value3;
```
similarly equals
```SQL
`SELECT * FROM table_name WHERE col1_name IN (value1, value2, value3);`
```



#### Between
**get rows between those two values.**
```SQL
SELECT * FROM table_name WHERE col1_name BETWEEN value1 AND value2;
```


#### Like

**for Pattern search**
```SQL
SELECT * FROM table WHERE col1 LIKE 'pattern';
```
for searching gmail.com email ids
```SQL
SELECT * FROM person WHERE email LIKE '%@gmail.com
```
for searching gmail any email ids
```SQL
SELECT * FROM person WHERE email LIKE '%@gmail.%'
```

`%` - any, **many** random characters
`_` - any, **single** random characters

#### ILike
```SQL
SELECT * FROM table WHERE col1 ILIKE 'pattern';
```
for Pattern search ignore-case


#### Group by
```SQL
SELECT col, COUNT(*) FROM table GROUP BY col;
```
for grouping by and giving the count of distinct values

```SQL
SELECT col, COUNT(*) FROM table GROUP BY col ORDER BY col;
```
 for grouping by and giving the count of distinct values and sorting by col

#### Group By Having

**`HAVING` used only with `GROUP BY`,** it should be used right after `GROUP BY` usage.
```SQL
SELECT col, COUNT(*) FROM table GROUP BY col HAVING COUNT(*) > 5;
```
 - for grouping by and giving the count of distinct values having more than 5 count

##### `COUNT(*)` is a [aggregate](https://www.postgresql.org/docs/current/functions-aggregate.html) function that count grouped by objects

##### `MAX(col)` gives the max value in the column
```SQL
SELECT MAX(col) FROM table;
```


##### `MIN(col)` gives the min value in the column
```SQL
SELECT MIN(col) FROM table;
```

```SQL
SELECT col, col2, MIN(col) FROM table GROUP BY col, col2;
```


##### `AVG(col)` gives the AVG value of the col
```SQL
SELECT AVG(col) FROM table;
```

```SQL
SELECT ROUND(AVG(col)) FROM table;
```
 to round the value

##### `SUM(col)` gives the SUM value of the col
```SQL
SELECT SUM(col) FROM table;
```

```SQL
SELECT col, col2, SUM(col) FROM table GROUP BY col, col2;
```


### Arithmetic Operator

    +  sum
    -  subtract
    *  multiply
    /  divide
    ^  power
    ! factorial
    % modulo

```sql
SELECT col1, col2, ROUND((col1 * 0.1), 2) FROM table;
```
- get 10% value of col1 and round it to 2 decimal place.


### Alias

`AS`
```SQL
SELECT col1, col2, ROUND((col1 * 0.1), 2) AS col_name FROM table;
```
- get 10% value col is named as col_name. Overrides the col to new name.



#### COALESCE
```SQL

```
`COALESCE(col, default_value_when_empty)` to fill empty cell in coulmn with default.
```SQL

```
`SELECT COALESCE(email, 'Email not provided') FROM person;`


#### NULLIF
```SQL
NULLIF(val1, val2)
```
returns val1 if val1 != val2
```SQL
SELECT COALESCE( (val / NULLIF(0, 0)), 0);
```
will not throw 0/0 error

#### Time stamps and Dates

[REF for DATE and TIME](https://www.postgresql.org/docs/current/datatype-datetime.html)

`NOW()`
```SQL
SELECT NOW();
```
- gives the timestamp and date of now.

`NOW()::DATE`
```SQL
SELECT NOW()::DATE;
```
- gives the date of now.

`NOW()::TIME`
```SQL
SELECT NOW()::TIME;
```
- gives the timestamp of now.


`INTERVAL 'value'`
```SQL
SELECT NOW() - INTERVAL '1 YEAR';
```
- gives the timestamp and date of now minus 1 year.

```SQL
SELECT NOW() - INTERVAL '1 MONTH';
```
 - gives the timestamp and date of now minus 1 Month.
 
```SQL
SELECT NOW() - INTERVAL '1 DAY';
```
- gives the timestamp and date of now minus 1 Day.
   
 
`EXTRACT('value')`
```SQL
SELECT EXTRACT(YEAR FROM NOW());
```
- extracts year form now
   
```SQL
SELECT EXTRACT(MONTH FROM NOW());
``` 
- extracts month form now

```SQL
SELECT EXTRACT(DAY FROM NOW());
```
- extracts day form now

```SQL
SELECT EXTRACT(DOW FROM NOW());
```
- extracts day of week form now\
   
```SQL
SELECT EXTRACT(CENTURY FROM NOW());
```
 - extracts century form now

`AGE(val1,val2)`
```SQL
SELECT AGE(NOW(), dob_col) AS age;
```



### Constraints

#### Primary Key

Unique fields for identifying the row.

`PRIMARY KEY` - when creation of table, use inbuilt func to manage it.
```SQL
ALTER TABLE table_name DROP CONSTRAINT indexes_name;
```
- to drop Primary key

```SQL
ALTER TABLE table_name ADD PRIMARY KEY (col_name);
```
- to add Primary key

Delete or fix non-unique values in the col name before setting it has a primary key.

#### Unique
```SQL
ALTER TABLE table_name ADD CONSTRAINT new_constraint_name UNIQUE (col_name);
```
- to make columns contain unique values

also be done by
```SQL
ALTER TABLE table_name ADD UNIQUE (col_name);
```
 - naming of constraint index is done by SQL

```SQL
ALTER TABLE table_name DROP CONSTRAINT indexes_name;
```
- to drop Unique

#### Check
```SQL
ALTER TABLE table_name ADD CONSTRAINT new_constraint_name CHECK (col1_name = 'val1' OR col1_name = 'val2');
```
 - to make columns contain only val1 and val

```SQL
ALTER TABLE person ADD CONSTRAINT gender_enum CHECK (gender IN ('Genderqueer',
 'Bigender',
 'Genderfluid',
 'Male',
 'Non-binary',
 'Polygender',
 'Female',
 'Agender'));
```
 

#### Delete
```SQL
DELETE FROM table_name;
```
 - to delete everyone!!!!
```SQL
DELETE FROM table_name WHERE col1_name = val;
```
 - to delete one row, best practice to use Primary Key for WHERE constraint.

#### Update
```SQL
UPDATE table_name SET col_name = val
```
- to update every rows in col
```SQL
UPDATE table_name SET col1_name = val, col2_name = val2 WHERE col_name = val
```
- to update a column, where row with value

#### Handling constraint error - do nothing

```sql
<command_conflict> ON CONFLICT (col_with_conflict) DO NOTHING;
```
- col must be unique

```SQL
insert into person (id, first_name, last_name, email, gender, date_of_birth, country_of_birth) values (1 ,'Mollie', 'Daville', 'mdaville2@chronoengine.com', 'Female', '11/17/2022', 'China') ON CONFLICT (id) DO NOTHING;
```
 - id is primary key, and we are trying to add row with id that already exist.
   
#### Handling constraint error - do update with the latest 
```SQL
<command_conflict> ON CONFLICT (col_with_conflict) DO UPDATE SET col_name = EXCLUDED.col_name, col2_name = EXCLUDED.col2_name;
```

```SQL
insert into person (id, first_name, last_name, email, gender, date_of_birth, country_of_birth) values (1 ,'Mollie', 'Daville', 'mdaville2@chronoengine.com', 'Female', '11/17/2022', 'China') ON CONFLICT (id) DO UPDATE SET email = EXCLUDED.email;
```
 - updates only email column with the new value in insert, rest will not.

#### Foreign Key and Joins

![image.png](attachment:image.png)

Here `col_table1_name REFERNCES table2_name (col_table2_name);` is used to set Foreign Key.

 ```sql
create table person (
    id BIGSERIAL NOT NULL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	gender VARCHAR(7) NOT NULL,
	email VARCHAR(100),
	date_of_birth DATE NOT NULL,
	country_of_birth VARCHAR(50) NOT NULL,
	car_id BIGINT REFERENCES car (id),
	UNIQUE(car_id)
);
```
#### Inner Joins - displays common value found in both table

![image-2.png](attachment:image-2.png)
```SQL
table1_name JOIN table2_name ON table1_name.clo = table2_name.col;
```

```SQL
SELECT * FROM person JOIN car ON person.car_id = car.id;
```

```SQL
SELECT person.first_name, car.make, car.model, car.price FROM person JOIN car ON person.car_id = car.id;
```


#### Left Joins - displays common value found in both table + left table values

![image-3.png](attachment:image-3.png)
```SQL
table1_name LEFT JOIN table2_name ON table1_name.clo = table2_name.col;
```
Use `
```SQL
SELECT * FROM person LEFT JOIN car ON person.car_id = car.id;
```

```SQL
SELECT person.first_name, car.make, car.model, car.price FROM person LEFT JOIN car ON person.car_id = car.id;
```

```SQL
SELECT person.first_name, car.make, car.model, car.price FROM person LEFT JOIN car USING (car_id);
```
- if both table have car_id col and join is done using that col.

#### Delete Dependency Row

You can either delete the Dependent row or Update Dependent col to null and perform, delete in Dependency row.


### Export to CSV

```SQL
\copy (SQL_Query) TO 'path' DELIMITER ',' CSV HEADER;
```

```SQL
\copy (SELECT * FROM person) TO '~/Desktop/result.csv' DELIMITER ',' CSV HEADER;
```



### Serial and Serialization

`BIGSERIAL`

![image.png](attachment:image.png)

if you invoke the function, you will update increment last value by one.
to reset - 
```sql
ALTER SEQUENCE seq_name RESTART WITH num;
```

![image-2.png](attachment:image-2.png)


### Extensions
```SQL
SELECT * FROM pg_available_extensions;
```
- to view all extensions
```SQL
CREATE EXTENSION IF NOT EXISTS "extension_name";
```
- to install extension

`\df` - to find the functions available to us for use
`SELECT func_name();` - to invoke function

EX: for using Extension.

![image-3.png](attachment:image-3.png)

