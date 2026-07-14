## To Debug the SQL Procedure

```sql
CREATE TABLE debug_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message TEXT
);

INSERT INTO debug_log (message)
VALUES (
    CONCAT(
        'ID=', COALESCE(NEW.id, 'NULL'),
        ', OLD Status=', COALESCE(OLD.subscription_status, 'NULL'),
        ', NEW Status=', COALESCE(NEW.subscription_status, 'NULL'),
        ', product_id=', COALESCE(NEW.product_id, 'NULL'),
        ', user_id=', COALESCE(NEW.user_id, 'NULL'),
        ', isReEnroll=', COALESCE(isReEnrollProduct, 'NULL')
    )
);
```

## To List the table sizes in db

```
SELECT
    table_name,
    ROUND(data_length / 1024 / 1024, 2) AS data_mb,
    ROUND(index_length / 1024 / 1024, 2) AS index_mb,
    ROUND((data_length + index_length) / 1024 / 1024, 2) AS total_mb
FROM information_schema.TABLES
WHERE table_schema = 'your_database_name'
ORDER BY (data_length + index_length) DESC;
```
