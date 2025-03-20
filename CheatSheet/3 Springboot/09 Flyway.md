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