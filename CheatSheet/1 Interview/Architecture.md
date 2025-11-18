## Lookup Table in Databases

A lookup table or LUT maps keys to values.
author_id 	author_name
1 	        Jack Michaelson
2 	        Sierra Fields
3 	        Nikhil Bhargav


- **The lookup table can replace complex runtime calculations with simple lookup operations.** For example, we can pre-calculate and store the output values of a complex math function in a lookup table, 
- **We use lookup tables to maintain data integrity in our application.** We can store all static key-value pairs in a single lookup table for all modules of the application.  This saves space and promotes data integrity since all the modules of our application can use the same simple key to refer to a potentially more space-consuming value.

### Lookup Table vs Hashing
- Unless indexed, the **lookup table** does a read operation by looking into the entire table to select the relevant entry (or entries). O(N).
- The efficiency of a **hash table** depends upon the strength of the hash function to map input value to a unique index as well as its processing overhead.O(1).

## Simulation vs. Emulation

Simulation: Like a movie of someone driving a car — shows behavior but not the real machine.
Emulation: Like building a replica car so that the actual car software works on it.

| Feature                     | Simulation       | Emulation           |
| --------------------------- | ---------------- | ------------------- |
| **What it models**          | Behavior/logic   | Real system exactly |
| **Runs original software?** | ❌ No             | ✔️ Yes            |
| **Complexity**              | Lower            | Higher              |
| **Speed**                   | Faster           | Slower              |
| **Accuracy**                | Behavioral       | Full-system         |
| **Example**                 | Flight simulator | SNES emulator       |

## Consensus Algorithms in Distributed Systems

Consensus algorithms are protocols that enable a group of distributed nodes (machines) to agree on a single, consistent value even in the presence of failures, unreliable communication, or malicious participants.

### Types of Failures

- Crash Fault Tolerance (CFT) - Nodes may fail by stopping (crashes), but do not lie or act maliciously.
- Byzantine Fault Tolerance (BFT) - Nodes may behave arbitrarily or maliciously.

### Key Properties of Consensus

Agreement – All correct nodes decide on the same value.
Validity – The decided value must be one proposed by a node.
Liveness – Nodes eventually reach a decision (no infinite waiting).
Fault Tolerance – Works correctly even if some nodes fail.

### Comparing Consensus Algorithms

| Algorithm    | Fault Model            | Key Features                     | Used In                  |
| ------------ | ---------------------- | -------------------------------- | ------------------------ |
| **Paxos**    | CFT                    | Proven correctness, complex      | Google, Azure            |
| **Raft**     | CFT                    | Easy to understand, leader-based | Kubernetes, etcd         |
| **VSR**      | CFT                    | Primary-backup replication       | Academic & some systems  |
| **PBFT**     | BFT                    | High message overhead            | Permissioned blockchains |
| **PoW**      | Economic/Probabilistic | Energy-intensive, decentralized  | Bitcoin                  |
| **PoS**      | Economic + partial BFT | Efficient, scalable              | Ethereum, Cardano        |
| **HotStuff** | BFT                    | Linear message complexity        | Facebook (Diem)          |

### Choosing the Right Consensus Algorithm

Pick based on:

**If nodes are trusted & failures are crashes:** Use Raft or Paxos.
**If nodes may be malicious:** Use PBFT, HotStuff, Tendermint.
**If open, permissionless environment:** Use PoW or PoS.
**If you want high throughput and finality:** Use BFT-based PoS (Tendermint, HotStuff, Casper).

## Polling vs. Webhooks

1. Polling
How it works:
Your system repeatedly asks another system: **“Any new data yet?”**
2. Webhooks
How it works:
Instead of asking repeatedly, you tell the server:
**“Send me an HTTP request when something changes.”**

| Feature         | Polling                            | Webhooks                     |
| --------------- | ---------------------------------- | ---------------------------- |
| Communication   | Client repeatedly asks             | Server pushes updates        |
| Latency         | Higher (unless frequent polling)   | Very low                     |
| Efficiency      | Low (many wasted requests)         | High                         |
| Scalability     | Worse                              | Better                       |
| Implementation  | Simple                             | Requires endpoint + security |
| Ideal use cases | Simple clients, no public endpoint | Fast, real-time updates      |

### Use Polling when:

- You cannot expose a public webhook endpoint
- Updates are infrequent and real-time is not needed
- Working in a restricted network (e.g., IoT, on-prem systems)

### Use Webhooks when:

- You need real-time updates
- You want to minimize server/client load
- You can expose and secure an HTTP endpoint

## Big Endian vs. Little Endian

Endianness refers to the byte order used to store multi-byte data (e.g., 16-bit, 32-bit, 64-bit numbers) in memory.

<img width="853" height="555" alt="image" src="https://github.com/user-attachments/assets/7106928d-37ec-4c38-b427-6586a63e447d" />


| Feature                      | Big Endian            | Little Endian          |
| ---------------------------- | --------------------- | ---------------------- |
| Lowest memory address stores | Most significant byte | Least significant byte |
| Human-readability            | Higher                | Lower                  |
| CPU efficiency               | Depends, often slower | Often faster           |
| Network protocols            | Standard              | Requires conversion    |
| Dominant today?              | No                    | Yes                    |

- Big Endian: MSB first → intuitive, used by networks.
- Little Endian: LSB first → efficient on modern CPUs, widely used today.

## Horizontal vs Vertical scaling

| Feature        | Vertical Scaling                 | Horizontal Scaling                           |
| -------------- | -------------------------------- | -------------------------------------------- |
| Method         | Add resources to one machine     | Add more machines                            |
| Cost           | Expensive at high end            | Scales cost-effectively                      |
| Limit          | Hardware limits                  | Nearly unlimited                             |
| Failure Impact | Single point of failure          | Fault-tolerant                               |
| Complexity     | Low                              | High                                         |
| Best For       | Monoliths, small apps, databases | Microservices, distributed systems, web apps |
| Downtime       | Often required                   | Usually none                                 |

## GUID

A **GUID** (Globally Unique Identifier) is a 128-bit value used to uniquely identify objects, resources, or entities across systems, networks, and time—with an extremely low chance of duplication.

- GUID = 128-bit globally unique identifier
- UUID = standardized version; GUID = Microsoft term
- Used for uniquely identifying anything in distributed systems
- Many versions exist; v4 (random) and v7 (time-ordered) are most common

GUIDs avoid collisions by using:
- Massive 128-bit space
- Cryptographically secure randomness (v4, v7)
- Unique hardware identifiers (v1)
- Timestamp + sequence counters
- Hash-based deterministic generation (v3, v5)

## Threads vs Process

| Concept           | Process                                       | Thread                                           |
| ----------------- | --------------------------------------------- | ------------------------------------------------ |
| Definition        | Independent program with its own memory space | A lightweight unit of execution within a process |
| Memory            | Separate memory                               | Shared memory with same process                  |
| Isolation         | Strong                                        | Weak                                             |
| Context Switching | Expensive                                     | Cheaper                                          |
| Failure Impact    | Low (isolated)                                | High (shared memory)                             |

| Aspect        | Threads              | Processes                |
| ------------- | -------------------- | ------------------------ |
| Memory        | Shared               | Separate                 |
| Performance   | Faster switching     | Slower switching         |
| Isolation     | Weak                 | Strong                   |
| Communication | Easy (shared memory) | Harder (IPC needed)      |
| Failure Scope | Whole process crash  | Contained to one process |
| Debugging     | Harder               | Easier                   |
| Security      | Lower                | Higher                   |
| Resource Use  | Low                  | High                     |

### When to Use Threads

Need fast, lightweight concurrency
Tasks share common data
Building servers (thread pools)
High-performance, real-time apps

### When to Use Processes

Need strong isolation ( For security purpose )
Running separate applications
Avoiding crashes leaking across tasks
Multi-process architectures (e.g., Chrome browser, databases)

## CDN vs. Caching — Key Differences

| Feature      | CDN                                   | Caching                                 |
| ------------ | ------------------------------------- | --------------------------------------- |
| Purpose      | Deliver content from nearest location | Speed up access by reusing stored data  |
| Location     | Distributed edge servers worldwide    | Anywhere (browser, server, DB, app)     |
| Focus        | Reduce **network latency**            | Reduce **processing or retrieval time** |
| Data Type    | Mostly static assets                  | Any type (static, dynamic, DB, API)     |
| Scope        | Global                                | Local or system-specific                |
| Who uses it? | End-users across the internet         | Applications, servers, browsers         |

## Cold vs Warm Cache

| Feature      | Cold Cache                        | Warm Cache                 |
| ------------ | --------------------------------- | -------------------------- |
| Cache State  | Empty or uninitialized            | Populated with useful data |
| Performance  | Slow                              | Fast                       |
| Cache Hits   | Rare / none                       | Frequent                   |
| System Load  | High (fallback to DB or upstream) | Low                        |
| Typical When | Startup, deployment, cache clear  | Steady state after traffic |

A system might look slow right after:
Restart
Deployment
TTL expiration
Cache flush
Scaling event (new nodes with empty caches)

This is because caches start **cold** and need traffic to become **warm**. Because the data needs to be populated in cache after the processing for first couple of time, after data build up the frequently used data can be used.

## Data Warehouse

A data warehouse is mainly defined as a centralized organizational archive of data collections that contributes to decision-making tasks.

<img width="916" height="533" alt="image" src="https://github.com/user-attachments/assets/da381ae4-84f5-48e9-bf6f-f96456afe274" />


| Feature     | Database            | Data Warehouse               | Data Lake          |
| ----------- | ------------------- | ---------------------------- | ------------------ |
| Purpose     | Run apps            | Analytics                    | Store everything   |
| Data Type   | Structured          | Structured & semi-structured | Any                |
| Processing  | OLTP                | OLAP                         | ELT/ETL (Flexible) |
| Schema      | Schema-on-write     | Schema-on-write              | Schema-on-read     |
| Cost        | Moderate            | High                         | Low                |
| Performance | Fast for small data | Fast for queries             | Depends (raw data) |
| Users       | Developers          | Analysts                     | Data scientists    |
| Data State  | Current, real-time  | Cleaned, historical          | Raw, unprocessed   |

Database → stores operational data
Data Lake → stores raw data from DBs, logs, APIs
Data Warehouse → stores cleaned, modeled data for BI

### Data Warehouse Characteristics

| **Characteristic**                      | **Description**                                                                                        |
| --------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| **Subject-Oriented**                    | Organized around key business subjects (sales, customers, finance) rather than applications.           |
| **Integrated**                          | Data from multiple sources is cleaned, standardized, and unified (consistent formats, naming, coding). |
| **Non-Volatile**                        | Data is stable; once loaded, it is not frequently updated or deleted. Mainly read-only.                |
| **Time-Variant**                        | Stores historical data with timestamps for trends and long-term analysis (weeks, months, years).       |
| **Large Volume**                        | Designed to store massive datasets (TBs–PBs).                                                          |
| **Supports OLAP**                       | Optimized for analytical queries, aggregations, and multi-dimensional analysis.                        |
| **Structured / Semi-Structured**        | Accepts well-defined schemas but can also handle JSON, XML, Parquet, etc.                              |
| **Separates Analytics from Operations** | Keeps analytical workloads separate from transactional (OLTP) systems to avoid performance impact.     |

### Types

| Type           | Purpose                    | Characteristics                      |
| -------------- | -------------------------- | ------------------------------------ |
| **EDW** Enterprise Data Warehouse        | Enterprise-wide analytics  | Centralized, integrated, large-scale |
| **ODS** Operational Data Store       | Operational reporting      | Near real-time, frequently updated   |
| **Data Mart**  | Department-level analytics | Subset of DW, faster, focused        |
| **Virtual DW** | Logical analytics layer    | No physical data, federated          |


### OLAP
**Online Analytical Processing (OLAP) is a method for loading and transferring data from outside origins to data warehouses, generating valuable information from it, and supporting queries on it.** Most OLAP programs rely heavily on queries and frequently use data from DWs.

### OLTP
**Online Transactional Processing (OLTP) allows several users to make numerous database transactions and arrangements in real time, usually over the Internet. The key difference between OLAP and OLTP is that each system has been designed to handle different kinds of operations.

Extensive analysis of data for better decision-making is achieved and designed for OLAP. On the contrary, OLTP is designed to process a significant amount of transactions for applications that use client self-service.

## Application Server vs. Web Server

| Feature              | **Web Server**                 | **Application Server**                               |
| -------------------- | ------------------------------ | ---------------------------------------------------- |
| **Primary Function** | Serve static content           | Execute business logic & serve dynamic content       |
| **Handles**          | HTTP requests only             | HTTP + other protocols (RMI, RPC, JMS, etc.)         |
| **Content Type**     | Static: HTML, CSS, JS, images  | Dynamic: API responses, data processing, logic       |
| **Execution**        | No backend logic execution     | Runs server-side programs (Java, Python, .NET, etc.) |
| **Environment**      | Lightweight                    | Heavyweight                                          |
| **Scalability**      | High for static content        | Must scale application logic                         |
| **Example Workload** | Deliver a website page         | Process login, payments, business rules              |
| **Caching**          | Strong for static files        | Moderate; usually relies on DB/cache systems         |
| **Examples**         | Nginx, Apache HTTP Server, IIS | Node.js, Spring Boot, Tomcat, WildFly, Django        |

## Should We Deploy Database and Web Server Apart?

| Question                                       | Answer                                          |
| ---------------------------------------------- | ----------------------------------------------- |
| Should DB and Web Server be deployed together? | ❌ No (except small apps)                        |
| Should they be separate in production?         | ✅ Yes                                           |
| Benefit?                                       | Security, scalability, performance, reliability, differnt hardware requirement |

## Differences Between Cloud, Grid and Cluster

Cloud computing provides on-demand computing resources (compute, storage, networking) over the internet, usually paid per use.

Grid computing uses a distributed network of loosely coupled computers to work on a single large task by splitting it into smaller subtasks.

Cluster computing uses multiple tightly coupled computers (often in the same data center) that work together as a single system.

| Feature                | **Cloud Computing**     | **Grid Computing**               | **Cluster Computing**                |
| ---------------------- | ----------------------- | -------------------------------- | ------------------------------------ |
| **Coupling**           | Loosely coupled         | Very loosely coupled             | Tightly coupled                      |
| **Location of Nodes**  | Global (vendor-owned)   | Distributed across organizations | Same data center / LAN               |
| **Purpose**            | On-demand services      | Large distributed computations   | High performance & availability      |
| **Scalability**        | Very high (elastic)     | High (many nodes)                | Medium                               |
| **Resource Ownership** | Cloud provider          | Multiple independent owners      | Single organization                  |
| **Cost Model**         | Pay-as-you-go           | Typically free/shared            | Org-owned hardware                   |
| **Main Use Case**      | General computing, apps | Scientific workloads             | HPC & reliable services              |
| **Management**         | Managed by provider     | Complex, decentralized           | Centrally managed                    |
| **Fault Tolerance**    | High                    | Moderate                         | High                                 |
| **Examples**           | AWS, Azure, GCP         | BOINC, Globus Toolkit            | Kubernetes clusters, Hadoop clusters |



