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


## Computer Cluster Types

| **Type of Cluster**                          | **Description**                                          | **Primary Uses / Applications**                                         |
| -------------------------------------------- | -------------------------------------------------------- | ----------------------------------------------------------------------- |
| **High-Performance Computing (HPC) Cluster** | Runs tasks in parallel for maximum computation.          | Scientific simulations, weather models, physics, genomics, AI training. |
| **High-Availability (HA) Cluster**           | Continues operation even if one node fails.              | Mission-critical apps, financial systems, database failover.            |
| **Load Balancing Cluster**                   | Distributes workload across nodes.                       | Web servers, microservices, cloud hosting, high-traffic apps.           |
| **Storage Cluster**                          | Distributed scalable storage across many nodes.          | Big data storage, HDFS, Ceph, backups, file sharing.                    |
| **Grid Cluster**                             | Geographically distributed nodes working on large tasks. | Research computing, SETI@home, mathematics, distributed computing.      |
| **Kubernetes Cluster**                       | Manages containerized applications.                      | Microservices, container orchestration, CI/CD deployments.              |


## Multi-Step vs. Multi-Factor

| Feature             | **Multi-Step Authentication**       | **Multi-Factor Authentication (MFA)**         |
| ------------------- | ----------------------------------- | --------------------------------------------- |
| **Definition**      | Multiple authentication steps       | Multiple **different authentication factors** |
| **Factor Types**    | May use same factor repeatedly      | Must use **at least two different types**     |
| **Security Level**  | Medium                              | High                                          |
| **Example**         | Password → Security Question        | Password → OTP via phone                      |
| **Use Case**        | Basic added security, low-risk apps | High-security systems (banks, cloud services) |
| **Compliance**      | Often not sufficient                | Required for many security standards          |
| **User Experience** | More steps but same factor          | More secure, slight increase in complexity    |


## Cloud Computing vs Distributed Computing

| Aspect            | **Cloud Computing**                                            | **Distributed Computing**                                        |
| ----------------- | -------------------------------------------------------------- | ---------------------------------------------------------------- |
| **Definition**    | Delivery of computing resources as a service over the internet | A model where multiple computers collaborate to solve a problem  |
| **Primary Goal**  | On-demand resource access, scalability, cost efficiency        | Performance, parallelism, fault tolerance                        |
| **Ownership**     | Managed by cloud provider                                      | Managed by users/organizations                                   |
| **Resource Type** | Virtualized resources                                          | Physical (or virtual) distributed nodes                          |
| **Access**        | Internet-based, self-service                                   | Often internal networks or specialized infrastructure            |
| **Scalability**   | Automatic, elastic                                             | Requires manual configuration or custom systems                  |
| **Cost Model**    | Pay-as-you-go                                                  | Fixed cost (hardware + maintenance)                              |
| **Complexity**    | Hidden from users                                              | Exposed to users, requires specialized design                    |
| **Examples**      | AWS EC2, Google Cloud, Azure, SaaS apps                        | Hadoop cluster, Kubernetes cluster, MPI systems                  |
| **Use Cases**     | Web apps, storage, machine learning, hosting                   | Scientific computing, distributed databases, big data processing |


| Aspect                 | **Distributed Computing**                                          | **Centralized Computing**                                      |
| ---------------------- | ------------------------------------------------------------------ | -------------------------------------------------------------- |
| **Definition**         | System made of multiple connected nodes working together           | All computation handled by a central machine                   |
| **Architecture**       | Decentralized / multi-node                                         | Single-node (with thin clients)                                |
| **Scalability**        | High (horizontal scaling)                                          | Low to medium (vertical scaling)                               |
| **Fault Tolerance**    | High — failure of one node doesn't stop system                     | Low — central server failure stops operations                  |
| **Performance**        | Can be high due to parallelism                                     | Relies entirely on the central server’s capacity               |
| **Cost**               | Variable; can use commodity hardware                               | High upfront cost for powerful mainframes                      |
| **Management**         | More complex (distributed coordination)                            | Simple (everything in one place)                               |
| **Network Dependence** | Requires efficient, reliable networking                            | Requires stable connection to central machine                  |
| **Resource Sharing**   | Distributed across many nodes                                      | All resources reside in the central machine                    |
| **Examples**           | Hadoop cluster, Kubernetes cluster, microservices, distributed DBs | Mainframes, legacy enterprise systems, bank core systems       |
| **Best For**           | Big data, scientific computing, high-scale web apps                | Transaction-heavy, legacy, or strictly controlled environments |


## Opinionated vs Non-Opinionated Design

| Aspect                   | **Opinionated Design**                     | **Non-Opinionated Design**                      |
| ------------------------ | ------------------------------------------ | ----------------------------------------------- |
| **Philosophy**           | One “best” or recommended way to do things | Let the user decide how to structure and build  |
| **Flexibility**          | Low to medium                              | High                                            |
| **Learning Curve**       | Easier at start (clear rules)              | Harder initially (more decisions)               |
| **Configuration**        | Minimal (defaults provided)                | Extensive (user sets conventions)               |
| **Speed of Development** | Very fast for typical use cases            | Depends on user-defined setup                   |
| **Customization**        | Limited by framework choices               | Very customizable                               |
| **Risk**                 | Outgrowing framework conventions           | Architecture inconsistency or complexity        |
| **Best For**             | Rapid development, standard apps           | Complex, unique, or highly customizable systems |


## Message Brokers vs. Enterprise Service Buses

| Aspect                | **Message Broker**                             | **Enterprise Service Bus (ESB)**                             |
| --------------------- | ---------------------------------------------- | ------------------------------------------------------------ |
| **Architecture Role** | Simple message routing and queuing             | Full integration layer with orchestration                    |
| **Complexity**        | Lightweight                                    | Heavyweight, feature-rich                                    |
| **Coupling Style**    | Loose coupling between microservices           | More centralized, can become a bottleneck                    |
| **Core Functions**    | Publish/Subscribe, point-to-point, queuing     | Routing, transformation, orchestration, protocol mediation   |
| **Scalability**       | Highly scalable, distributed                   | Less scalable due to centralized nature                      |
| **Best For**          | Event-driven and microservice architectures    | Traditional enterprises with many legacy systems             |
| **Protocol Support**  | Mostly messaging protocols (AMQP, MQTT, Kafka) | Wide variety (SOAP, REST, JMS, FTP, JDBC, etc.)              |
| **Governance**        | Minimal                                        | Strong governance, policy enforcement                        |
| **Performance**       | High throughput                                | Lower due to heavy processing (transformation/orchestration) |
| **Failure Impact**    | Distributed → failures isolated                | ESB failure can impact entire enterprise                     |
| **Design Style**      | Decentralized                                  | Centralized “hub”                                            |
| **Examples**          | Kafka, RabbitMQ, SQS                           | MuleSoft, WSO2, IBM ESB                                      |


## Message Broker vs Event Bus

| Aspect                  | **Message Broker**                                    | **Event Bus**                                     |
| ----------------------- | ----------------------------------------------------- | ------------------------------------------------- |
| **Purpose**             | Reliable message delivery between distributed systems | Broadcast events to subscribers (often in-memory) |
| **Communication Model** | Queue-based (P2P) + Pub/Sub                           | Pure Pub/Sub                                      |
| **Delivery Guarantees** | High (ack, retry, persistence)                        | Low (fire-and-forget)                             |
| **Message Persistence** | Yes – stored until consumed                           | Rare – typically not stored                       |
| **Scalability**         | High; distributed cluster support                     | Medium; often local or simple networked           |
| **Use Case**            | Microservices, asynchronous workflows, job queues     | In-app communication, event-driven triggers       |
| **Ordering**            | Usually supported (queue/partition)                   | Not always guaranteed                             |
| **Latency**             | Slight overhead due to persistence                    | Very low; often in-memory                         |
| **Fault Tolerance**     | Strong (clustered, durable storage)                   | Weak (loss possible on crash)                     |
| **Complexity**          | Higher                                                | Lower                                             |
| **Examples**            | Kafka, RabbitMQ, SQS                                  | EventEmitter, Guava EventBus, NATS                |


## Transactions

A transaction is a sequence of operations that are treated as a single logical unit of work.
Either all operations in the transaction complete successfully, or none of them do.
Transactions are used to maintain data accuracy, consistency, and reliability—especially in databases, banking, and distributed systems.

| **Transaction Type**                   | **Description**                                                                                                                            | **Example Use Case**                                    |
| -------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------- |
| **1. Single (Flat) Transaction**       | A simple transaction with one logical unit of work. No nesting.                                                                            | Insert/update in a single table.                        |
| **2. Nested Transaction**              | A parent transaction contains one or more child transactions. Child can commit/rollback independently, but final result depends on parent. | Complex business workflows inside a larger transaction. |
| **3. Distributed Transaction**         | A transaction that spans multiple databases or services. Often uses **Two-Phase Commit (2PC)**.                                            | Updating DB + sending message + updating another DB.    |
| **4. Compensating (Saga) Transaction** | Used in microservices; instead of a global lock, each step has a compensating action to undo changes if something fails.                   | E-commerce order → payment → inventory → shipment.      |
| **5. Long-Running Transaction**        | Transactions that take a long time, often broken into smaller steps to avoid locking resources.                                            | Workflow approvals, business processes across systems.  |
| **6. Read-Only Transaction**           | Transaction that only reads data without modifying it.                                                                                     | Report generation, analytics queries.                   |
| **7. Savepoint-Based Transaction**     | Allows rollback to a specific **savepoint** instead of the full transaction.                                                               | Complex SQL scripts needing partial rollback.           |


## ACID vs CAP vs BASE

| **Aspect**                 | **ACID**                    | **CAP**                                    | **BASE**                                       |
| -------------------------- | --------------------------- | ------------------------------------------ | ---------------------------------------------- |
| **Focus**                  | Reliable transactions       | Distributed system trade-offs              | Availability + eventual consistency            |
| **Applies To**             | Databases and transactions  | Distributed systems only                   | Distributed databases                          |
| **Consistency Style**      | Strong consistency          | Strong/Available under partition           | Eventual consistency                           |
| **Availability**           | Lower under contention      | A or C must be sacrificed during partition | High availability                              |
| **Scalability**            | Harder (vertical scaling)   | Not about scaling                          | Highly scalable (horizontal)                   |
| **Tolerance to Partition** | Not required                | Mandatory factor                           | Assumed (eventual consistency)                 |
| **When Used**              | Banking, finance, inventory | Distributed architecture design            | Social networks, IoT, analytics, microservices |
| **Example Systems**        | SQL (Postgres, MySQL)       | All distributed systems                    | Cassandra, DynamoDB, CouchDB                   |


## Saga Pattern in Microservices

A Saga is a sequence of steps (local transactions) that happen across multiple microservices.
Each step either:

**Succeeds**, and the saga moves to the next step
**Fails**, and the system **triggers compensating transactions** to undo the completed steps

### Components of Saga

| Component                | Purpose                                     |
| ------------------------ | ------------------------------------------- |
| Local Transaction        | Creates/updates data in one service         |
| Compensating Transaction | Undoes local transaction on failure         |
| Events                   | Trigger next steps in choreography          |
| Orchestrator             | Controls sequence in orchestration          |
| Message Broker           | Transports events/messages between services |

### Types of saga designs

| Feature             | Choreography (Event-Driven)                   | Orchestration (Central Coordinator) |
| ------------------- | --------------------------------------------- | ----------------------------------- |
| Control             | Decentralized (events)                        | Central “orchestrator” service      |
| Coupling            | Low between services, but high event coupling | Higher coupling to orchestrator     |
| Workflow Complexity | Good for simple flows                         | Better for complex flows            |
| Monitoring          | Harder (distributed)                          | Easier (central flow)               |
| Failure Handling    | Each service listens for failure events       | Orchestrator decides compensations  |
| Scalability         | Very high                                     | Good but limited by orchestrator    |
| Risk                | Event spaghetti                               | Orchestrator becoming a bottleneck  |

### Usage

| Use Case                      | Explanation                                  |
| ----------------------------- | -------------------------------------------- |
| Multi-step workflows          | e.g., order → payment → inventory → shipping |
| Distributed databases         | Each microservice has its own DB             |
| Need for eventual consistency | Strong consistency is too expensive          |
| High availability systems     | Avoid distributed locks and 2PC              |
| Long-running processes        | Human workflows, async tasks                 |


| Category                   | Tools                                         |
| -------------------------- | --------------------------------------------- |
| Message Brokers            | Kafka, RabbitMQ, NATS, SQS                    |
| Saga Orchestration Engines | Temporal.io, Camunda/Zeebe, Netflix Conductor |
| Frameworks                 | Axon Framework, Eventuate, Spring Boot Saga   |

| Advantages                           | Disadvantages                        |
| ------------------------------------ | ------------------------------------ |
| No distributed locks                 | Harder to test end-to-end            |
| No 2-phase commit                    | Compensating actions can be complex  |
| Highly scalable                      | Eventual consistency (not immediate) |
| Fault-tolerant                       | Debugging/observability challenges   |
| Works well with event-driven systems | Requires careful design of events    |


## Serverless Architecture

Serverless Architecture is an application design pattern where developers build and deploy code without managing servers.
The infrastructure—scaling, provisioning, patching, load balancing—is handled automatically by the cloud provider.

You still run on servers, but you do not manage them.
Billing is based on actual usage (execution time, memory), not on pre-provisioned capacity.

### Core Components

**1. FaaS (Function as a Service)**

Small, stateless functions triggered by events.

Examples:

- AWS Lambda
- Google Cloud Functions
- Azure Functions
- Cloudflare Workers

**2. BaaS (Backend as a Service)**

Managed services used by your functions or frontend.

Examples:

- Firebase
- DynamoDB
- Cognito/Auth0
- S3/Blob storage

| Characteristic           | Description                               |
| ------------------------ | ----------------------------------------- |
| **No server management** | Cloud handles provisioning and scaling    |
| **Event-driven**         | Functions triggered by events/requests    |
| **Autoscaling**          | Scales automatically based on load        |
| **Pay-per-use**          | Pay only for execution time               |
| **Stateless**            | Functions should not rely on local memory |
| **Ephemeral**            | Functions run quickly and terminate       |

| Drawback                   | Explanation                              |
| -------------------------- | ---------------------------------------- |
| **Cold starts**            | First request after idle time is slower  |
| **Vendor lock-in**         | Relies heavily on cloud provider’s tools |
| **Debugging complexity**   | Harder to debug distributed functions    |
| **Limited execution time** | Many FaaS platforms limit run duration   |
| **Stateless constraints**  | Requires external storage for state      |

| Feature            | Serverless  | Microservices         | Monolith           |
| ------------------ | ----------- | --------------------- | ------------------ |
| Scaling            | Automatic   | Manual/auto           | Manual             |
| Deployment unit    | Function    | Service               | Whole system       |
| Cost               | Pay-per-use | Pay for services 24/7 | Large upfront cost |
| State              | Stateless   | Can be stateful       | Usually stateful   |
| Operational burden | Very low    | Medium                | High               |
| Vendor lock-in     | High        | Medium                | Low                |


## Distributed System vs. Distributed Computing

| Term                      | What It Emphasizes                                                                                 |
| ------------------------- | -------------------------------------------------------------------------------------------------- |
| **Distributed System**    | The *system itself*: many machines working together and appearing as one.                          |
| **Distributed Computing** | The *computations or tasks*: splitting a problem across multiple machines for parallel processing. |

In simple terms:

Distributed System → Architecture / Design of the system
Distributed Computing → Computation done across distributed nodes

| Category              | Distributed System                                | Distributed Computing                                |
| --------------------- | ------------------------------------------------- | ---------------------------------------------------- |
| **Primary Goal**      | Build a cohesive system across many machines      | Speed up or scale computation                        |
| **Main Concern**      | Coordination, consistency, reliability            | Task decomposition, parallel execution               |
| **User View**         | Appears as a single system                        | Appears as multiple workers solving pieces of a task |
| **Fault Tolerance**   | Essential (system must keep running)              | Important but depends on workload                    |
| **Example Use Cases** | Databases, messaging queues, microservices        | Analytics jobs, ML training, simulations             |
| **Examples**          | Kafka, MongoDB cluster, Kubernetes, Redis cluster | Spark, Hadoop MapReduce, MPI, Dask                   |
| **Focus Area**        | System design & architecture                      | Algorithms & computation                             |
| **Communication**     | Often long-running communication                  | Often batch-style or task-based communication        |

## Microservices and Cross-Cutting Concerns

Cross-cutting concerns are functionalities that are needed across multiple microservices but **do not belong to any single business domain.**

| Concern                            | Description                                          |
| ---------------------------------- | ---------------------------------------------------- |
| **Authentication & Authorization** | JWT, OAuth2, API keys, identity provider integration |
| **Observability**                  | Logging, metrics, tracing                            |
| **Security**                       | TLS, secrets, certificates, policies                 |
| **Configuration management**       | External config, feature flags                       |
| **Resilience**                     | Retries, timeouts, circuit breakers                  |
| **Rate limiting & throttling**     | API gateway or service mesh                          |
| **Caching**                        | Distributed caches (Redis), local caches             |
| **Routing**                        | Service registry, load balancing                     |
| **Data validation**                | Input validation across edges                        |

### Why Cross-Cutting Concerns Are Challenging in Microservices

In a monolith, cross-cutting concerns are often implemented via:
- middleware
- shared libraries
- aspects (AOP)
- interceptors

In microservices, each service is:
- independently deployable
- independently scaled
- autonomous with its own DB

This creates challenges:
- Duplicated logic across services
- Hard to enforce consistency
- Harder to update shared code
- Risk of tight coupling

### Approaches to Cross-Cutting Concerns (Solutions)

| Approach                    | Strength                        | Weakness                 | Typical Use                        |
| --------------------------- | ------------------------------- | ------------------------ | ---------------------------------- |
| **API Gateway**             | Offloads external edge concerns | Not for internal traffic | Auth, rate limiting, client-facing |
| **Service Mesh**            | Uniform internal policies       | Operational overhead     | mTLS, retries, circuit breakers    |
| **Shared Libraries**        | Simple, local                   | Coupling, version issues | Logging, serialization             |
| **Platform Services**       | Centralized visibility          | Needs DevOps skills      | Logging, metrics, config           |
| **Middleware/Interceptors** | Easy to use                     | Duplicated logic         | Validation, per-app filters        |





