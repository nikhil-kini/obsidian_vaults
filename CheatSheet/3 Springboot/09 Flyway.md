Flyway = Manage SQL structure as a code.

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/order_service
spring.datasource.username=root
spring.datasource.password=mysql
spring.jpa.hibernate.ddl-auto=none
server.port=8081
```
We are using the **spring.jpa.hibernate.ddl-auto** property as **none** because we don't want Hibernate to create the database tables and manage migrations, we will be handling that using the [Flyway library.](https://flywaydb.org/)

By using Flyway, we can provide the necessary SQL scripts that will be executed whenever we need to change our database schema. We need to provide these scripts under the **src/main/resources/db/migration** folder.



Flyway will also follow a particular naming convention to identify the SQL scripts, we need to name the files like below: 

`V<Number>__file-name.sql`
Example: V1__init.sql, V2__add_products.sql, etc.

Note that the number, inside the name of the SQL file, needs to be incremented for each database migration you want to run.

Let's create the below file to create the Order table

**V1__init.sql**

```sql
CREATE TABLE `t_orders`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `order_number` varchar(255) DEFAULT NULL,
    `sku_code`  varchar(255),
    `price`    decimal(19, 2),
    `quantity` int(11),
    PRIMARY KEY (`id`)
);
```

> File needs to placed in this location `src/main/resources/db/migration` for it to be detected.
> Similarly for `test-container` testing library to pick-up, it should be placed in `src/test/resources/db/migration`

To run flyway with maven
```sh
mvn flyway:clean    # will delete all tables and data
mvn flyway:info     # status of script in application
mvn flyway:repair   # will repair failed migration
mvn flyway:migrate  # will migrate to newest version present

# To set the flyway cred manually (try -D flyway.url, -D flyway.user .. if below is giving error)
mvn flyway:migrate \
  -Dflyway.url=jdbc:mysql://localhost:3306/namedb \
  -Dflyway.user=dbuser \
  -Dflyway.password=dbpass

# To get from application.yml
mvn flyway:repair -D flyway.configFiles=config/application-local.yml
or
mvn flyway:repair -D flyway.configFiles=config/application-local.yml

mvn flyway:migrate -D flyway.outOfOrder=true   -- for allowing execution of lower series execution
mvn flyway:migrate -D flyway.ignoreMigrationPatterns='*:ignored'   -- for ignoring the above

# application.yml must contain following fields
flyway.url: jdbc:mysql://localhost:3306/dbname?autoReconnect=true&useSSL=false
flyway.user: root
flyway.password: root
flyway.locations: filesystem:migrations/sql
flyway.table: flyway_migration_schema_version
```

| Type           | Example            | Purpose             |
| -------------- | ------------------ | ------------------- |
| Versioned SQL  | `V1__init.sql`     | Schema/data changes |
| Repeatable SQL | `R__views.sql`     | Re-run when changed |
| Java migration | `V2__DataFix.java` | Complex logic       |
| No extension   | `V1__init`         | Ignored/not valid   |

## JAVA
```
src/main/java/db/migration/
    V3__InsertUsers.java
```

```java
package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;

public class V3__InsertUsers 
        extends BaseJavaMigration {

    public void migrate(Context context)
            throws Exception {

        context.getConnection()
               .createStatement()
               .execute(
                 "INSERT INTO users VALUES(1,'John')"
               );
    }
}
```
Useful when SQL is not enough. And complex logic is about to execute.

## Repeatable migrations

Starts with R

Example:
```
R__create_views.sql
R__refresh_procedures.sql
```
Runs when checksum changes.

Common for:

Views
Stored procedures
Functions

```
CREATE OR REPLACE VIEW active_users AS
SELECT *
FROM users
WHERE active=true;
```

## What is a checksum?

A checksum is a value Flyway calculates from the content of a migration file.

Example file:
```
R__create_views.sql
```

Content:
```
CREATE VIEW active_users AS
SELECT *
FROM users
WHERE active = true;
```

Flyway calculates something like:
```
checksum = 123456789
```
and stores it in the Flyway history table:
flyway_schema_history

```
+--------------------------+
| script                   |
| checksum                 |
+--------------------------+
| R__create_views.sql      |
| 123456789                |
+--------------------------+
```

Before:
```
CREATE VIEW active_users AS
SELECT *
FROM users;
```

After:
```
CREATE VIEW active_users AS
SELECT *
FROM users
WHERE active = true;
```

Now Flyway calculates:
```
new checksum = 987654321
```

Compare:
```
Database:
123456789

File:
987654321
```
Mismatch.

Flyway says:
```
File changed
Run repeatable migration again
```

## Why repeatable migrations exist

Some database objects are not usually versioned:

Views
Stored procedures
Functions
Triggers

## Difference with V migrations

Versioned migration:
```
V1__create_table.sql
V2__add_column.sql
V3__insert_data.sql
```

Runs once:
````
V1  ---> done
V2  ---> done
V3  ---> done
```
If you edit V1 later:
```
checksum changed
```

Flyway throws:
```
Validate failed:
Migration checksum mismatch
```
because old migrations should never change.

```
V = Versioned = execute once, never edit
R = Repeatable = execute again when content changes
```
