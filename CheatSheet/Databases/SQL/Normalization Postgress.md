
*If you’ve chosen the right data structure and organized things well, the algorithms will almost always be self-evident. Data structures, not algorithms, are central to programming.*

Database normalization the process of organizing the columns (attributes) and tables (relations) of a relational database to reduce data redundancy and improve data integrity. Normalization is also the process of simplifying the design of a database so that it  achieves the optimal structure.

**Partial Unique Index**

```sql

```

**Constraints are Guarantees**

```sql

```
* Avoiding Database Anomalies

**Normal Form**

• 1st Normal Form (1NF )  
	A table (relation) is in 1NF if:  
		1. There are no duplicated rows in the table.  
		2. Each cell is single-valued (no repeating groups or arrays).  
		3. Entries in a column (field) are of the same kind.
		4. Using row order to convey information is not permitted.
• 2nd Normal Form (2NF )  
	* A table is in 2NF if it is in 1NF and if all non-key attributes are dependent on all of the key. Since a partial dependency occurs when a non-key attribute is dependent on only a part of the composite key, the definition of 2NF is sometimes phrased as: “A table is in 2NF if it is in 1NF and if it has no partial dependencies.” 
	* Each non-key attribute in the table must be dependent on the entire primary key.
	* It solves **Deletion, Update, Insertion Anomalies.**
• 3rd Normal Form (3NF )  
	Every **non-key attribute** in a table should depend on the key, the whole key, and nothing but the key
	A table is in 3NF if it is in 2NF and if it has no transitive dependencies
	• Boyce-Codd Normal Form (BCNF )  - (stronger than 3NF)
		Every **attribute** in a table should depend on the key, the whole key, and nothing but the key  
• 4th Normal Form (4NF )  
	* A table is in 4NF if it is in BCNF and if it has no multi-valued dependencies.
	* Multi-valued dependencies in a table must be multi-valued dependencies on the key. 
• 5th Normal Form (5NF )  
	* A table is in 5NF, also called “Projection-join Normal Form” (PJNF ), if it is in 4NF and if every join dependency in the table is a consequence of the candidate keys of the table.  
	* The table (which must be in 4NF) cannot be describable as the logical result of joining some other tables together.
• Domain-Key Normal Form (DKNF )  
	A table is in DKNF if every constraint on the table is a logical consequence of the definition of keys and domains.