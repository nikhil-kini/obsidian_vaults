
Memcached is not used widely because of lack of features, but where used the requirement is mentioned below, and is 20 to 30% faster.

Valkey === redis fork + Open Source Project (Linux Foundation)

| Feature               | Memcached                                    | Redis                                                                                |
| --------------------- | -------------------------------------------- | ------------------------------------------------------------------------------------ |
| **Data Types**        | Strings only                                 | Strings, Lists, Sets, Hashes, Sorted Sets, Bitmaps, HyperLogLogs, Geospatial indexes |
| **Architecture**      | Multi-threaded                               | Single-threaded core (with multi-threaded I/O)                                       |
| **Persistence**       | None (purely volatile)                       | RDB snapshots, AOF logging, or a hybrid approach                                     |
| **Memory Management** | Slab allocation (stops memory fragmentation) | Dynamic allocation (highly configurable eviction policies)                           |
| **Scaling**           | Horizontal (client-side sharding)            | Native cluster support (master-slave replication, auto-failover)                     |
| **Pub/Sub Messaging** | No                                           | Yes (native publish/subscribe channels and streams)                                  |
| **Execution Logic**   | No                                           | Yes (Lua scripting support)                                                          |

## 1. Introduction & Core Architecture

- **Definition**: Redis is a fast, NoSQL, in-memory data structure store used as a database, cache, and message broker ([0:00](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=0s)).
- **Two Key Processes**:
    - **Redis Client** ([0:34](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=34s))
    - **Redis Server** ([0:34](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=34s))
- **Distributed Setup**: Can be structured in a master-slave configuration across different machines ([0:34](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=34s)).
- **Performance**: Extremely fast because it holds the entire dataset in the primary memory (RAM) ([0:49](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=49s)). It can handle around 110,000 writes/sec and 81,000 reads/sec ([0:56](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=56s)).
- **Persistence**: Data is saved to the disk asynchronously based on flexible policies (elapsed time and number of updates) ([1:09](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=69s)).
- **Atomicity**: All operations in Redis are atomic, ensuring thread-safe data changes even with concurrent client access ([1:39](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=99s)).
- **Common Use Cases**: Caching, session management, webpage hit counting, and messaging queues ([1:54](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=114s)).

---

- **Global Utility Commands**:
    - `KEYS *`: Lists all existing keys in the system ([11:28](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=688s)).
    - `FLUSHALL`: Deletes every key-value pair across all databases ([11:51](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=711s)).

---

3. Data Types & Core Commands

## Strings [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

The most fundamental key-value structure ([4:52](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=292s)). Can store text, integers, and floats ([4:45](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=285s)).

- `SET key value` / `GET key`: Sets (also reassigns value to key) and retrieves a string value ([4:45](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=285s)).
- `GETRANGE key start stop`: Extracts a substring (e.g., `GETRANGE email 0 4` gets the first 5 characters) ([5:37](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=337s)).
- `MSET k1 v1 k2 v2` / `MGET k1 k2`: Sets or gets multiple key-values at once ([6:04](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=364s)).
- `STRLEN key`: Returns string length (returns 0 if key not present) ([6:43](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=403s)).
- `INCR key` / `DECR key`: Increments or decrements an integer value by 1 ([7:46](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=466s)).
- `INCRBY key amount` / `DECRBY key amount`: Modifies an integer value by a specific value ([8:08](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=488s)).
- `INCRBYFLOAT key amount`: Increments a floating-point number ([9:19](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=559s)).
- **Expiration Management**:
    - `EXPIRE key seconds`: Sets a timeout on a key after which it deletes itself ([10:02](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=602s)).
    - `TTL key`: Checks remaining life in seconds (`-2` means expired) ([10:11](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=611s)).
    - `SETEX key seconds value`: Atomically sets both a value and its expiration timer ([10:39](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=639s)).

## Lists [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

Ordered collections of string elements linked sequentially ([0:16](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=16s)).

- `LPUSH key value` / `RPUSH key value`: Pushes an item to the head (left) or tail (right) of the list ([12:07](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=727s)).
- `LPOP key` / `RPOP key`: Pops and returns an item from the left or right side ([14:41](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=881s)).
- `LRANGE key start stop`: Retrieves a subset of items (use `0 -1` for the entire list) ([12:36](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=756s)).
- `LLEN key`: Returns the overall length of the list ([14:18](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=858s)).
- `LSET key index value`: Overwrites an item at a specific position ([15:40](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=940s)).
- `LINSERT key BEFORE|AFTER pivot value`: Inserts a value around a specified element reference (`LINSERT country BEFORE India "New Zealand"` add New Zealand before India in the list, if key is not present item will not be added)([16:20](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=980s)).
- `LINDEX key index`: Fetches an element using its index number ([17:21](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1041s)).
- `LPUSHX key value` / `RPUSHX key value`: Pushes data _only_ if **the list already exists**, if list is not there it will not push the data ([18:12](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1092s)).
- `SORT key ALPHA`: Sorts string elements (ALPHA alphabetically, add desc before ALPHA to get descending order) ([19:46](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1186s)).
- `BLPOP key timeout` / `BRPOP key timeout`: Blocking pop commands that halt the execution client and wait for items to arrive until the timeout is reached ([20:25](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1225s)). If the the push command came within the timeout the item from the direction i.e. Left (BLPOP) and RIGHT (BRPOP) will be removed and respective push will be added on the side of the push.

## Sets [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

Unordered collections of completely unique string elements ([22:11](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1331s)).

- `SADD key member`: Adds a unique member; returns `0` if it is a duplicate ([22:11](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1331s)).
- `SMEMBERS key`: Lists all members inside the set ([22:45](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1365s)).
- `SCARD key`: Provides the count (cardinality) of elements ([23:33](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1413s)).
- `SISMEMBER key member`: Checks if a specific member exists (returns `1` or `0`) ([23:44](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1424s)).
- `SDIFF k1 k2`: Determines the difference between the first set and subsequent sets ([24:53](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1493s)). (only return the k1 values, not there in k2)
- `SDIFFSTORE dest k1 k2`: Computes the set difference and stores it in a new destination key ([25:39](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1539s)).
- `SINTER k1 k2`: Computes the intersection (common items) ([26:25](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1585s)).
- `SINTERSTORE dest k1 k2`: Computes the intersection and saves it to a destination key ([27:13](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1633s)).
- `SUNION k1 k2` / `SUNIONSTORE dest k1 k2`: Merges sets to return or store unique combined items ([28:09](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1689s)).

## Sorted Sets (ZSets) [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

Sets where every member is mapped to a numeric score used to sort elements from lowest to highest score ([29:39](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1779s)).

- `ZADD key score member`: Adds an item with an explicit sorting score ([30:05](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1805s)). If same score is passed, the score association will be retained but latest object will be on top.
- `ZRANGE key start stop [WITHSCORES]`: Displays values in ascending order based on index range ([31:04](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1864s)).
- `ZREVRANGE key start stop [WITHSCORES]`: Returns items sorted in descending order by rank ([33:04](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1984s)).
- `ZREVRANGEBYSCORE key max min [WITHSCORES]`: Reverses items strictly filtering by a score range ([34:08](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2048s)).
- `ZCARD key`: Counts total members ([31:32](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1892s)).
- `ZCOUNT key min max`: Counts elements with scores inside a set range (e.g., `-inf` to `+inf` to get all counts, 0 to 2 to get counts of score between and including the value) ([31:40](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1900s)).
- `ZSCORE key member`: Looks up the specific score assigned to an element ([33:28](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2008s)).
- `ZINCRBY key increment member`: Increments a member's sorting score by a given number ([34:43](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2083s)).
- `ZREM key member`: Removes a specific member from the sorted set ([32:28](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=1948s)).
- `ZREMRANGEBYSCORE key min max`: Mass-deletes members based on a score filter window ([35:29](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2129s)).
- `ZREMRANGEBYRANK key start stop`: Deletes items sequentially based on their rank index positions ([36:23](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2183s)).

## HyperLogLog [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

A memory-efficient, probabilistic data structure used to estimate the unique count (cardinality) of massive items (like user IPs or emails) with minimal memory footprint ([36:54](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2214s)).

- `PFADD key element`: Adds unique elements into the HyperLogLog structure ([37:15](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2235s)).
- `PFCOUNT key`: Returns the approximated unique count of the elements ([37:49](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2269s)). (`PFCOUNT key1 key2` gives the added count of 2 keys )
- `PFMERGE dest source1 source2`: Combines multiple HyperLogLogs into a single destination key ([38:46](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2326s)).

# Hashes [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

Maps of string fields and string values that act perfectly for storing complex structural objects ([39:33](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2373s)).

- `HSET key field value` / `HGET key field`: Sets or retrieves a singular field within an object ([39:39](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2379s)).
- `HMSET key f1 v1 f2 v2` / `HMGET key f1 f2`: Handles multiple fields simultaneously ([41:51](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2511s)).
- `HGETALL key`: Pulls all fields and their matching values out of the hash ([40:43](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2443s)).
- `HKEYS key` / `HVALS key`: Isolates and extracts only the field names or only the values ([40:12](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2412s)).
- `HEXISTS key field`: Verifies whether a target field exists in the hash structure ([40:55](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2455s)).
- `HLEN key`: Evaluates the total field count inside the hash ([41:22](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2482s)).
- `HINCRBY key field amount` / `HINCRBYFLOAT key field amount`: Increments numbers inside hash fields ([42:45](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2565s)).
- `HDEL key field`: Deletes a field from the hash structure ([43:36](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2616s)).
- `HSTRLEN key field` : To get the string length of the value.
- `HSETNX key field value`: Adds a field _only_ if it does not yet exist ([44:21](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2661s)).

---

# 4. Advanced Features

Transactions [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

Allows a client to stage and run a block of commands sequentially and atomically ([45:21](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2721s)).

- `MULTI`: Initiates transaction block; subsequent commands return `QUEUED` ([45:38](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2738s)).
- `EXEC`: Fires and runs all queued actions sequentially ([46:23](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2783s)).
- `DISCARD`: Cleans out the queue and aborts the current transaction block ([46:50](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2810s)).
- `WATCH key`: Monitors a key for external modifications ([47:07](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2827s)). If another client alters this key before `EXEC` runs, the transaction fails entirely ([47:24](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2844s)).

# Pub/Sub (Publisher/Subscriber Model) [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

Decoupled messaging architecture where clients listen to channels or broadcast messages to them ([48:32](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2912s)).

- `SUBSCRIBE channel`: Subscribes a client to listen for real-time messages on a channel ([49:05](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2945s)). `SUBSCRIBE channel1 channel2` for having multi-channel subs.
- `PUBLISH channel message`: Dispatches a message to all active subscribers on a channel ([49:51](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=2991s)).
- `PSUBSCRIBE pattern`: Subscribes via wildcards (e.g., `PSUBSCRIBE news*` listens to `news1`, `news2`) ([51:27](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3087s)). `n?ws` naws, nbws  etc will be subs. 
- `PUBSUB CHANNELS`: Lists all actively subscribed non-pattern channels ([54:28](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3268s)).
- `PUBSUB NUMSUB channel`: Checks the active subscriber count for specific channels ([55:03](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3303s)).
- `PUBSUB NUMPAT`: Counts total pattern-based channel subscriptions across the server ([55:32](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3332s)).

# Lua Scripting (hands on skipped due to error and out of scope)

Enables executing multiple actions on the server with complete atomicity using Lua scripts ([56:22](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3382s)). 

- `EVAL "script" numkeys keys args`: Runs inline Lua code using `redis.call()` internally ([56:34](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3394s)).
- `SCRIPT LOAD "script"`: Compiles and saves a script on the server, returning a unique SHA-1 hash identifier ([1:02:58](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3778s)).
- `EVALSHA sha1_hash numkeys keys args`: Runs a pre-loaded script using its SHA-1 hash to reduce network overhead ([1:03:20](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3800s)).
- `SCRIPT EXISTS hash`: Verifies whether a script hash is cached on the server ([1:03:57](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3837s)).
- `SCRIPT FLUSH`: Wipes out all stored scripts from the server cache ([1:04:07](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3847s)).
- _Note_: Scripts should not be too long; running scripts lock the server and cause a bottleneck until they hit their default 5-second execution timeout ([1:04:41](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3881s)).

---

# 5. Connection, Security, & Architecture

Database Selection [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

- Redis includes distinct, indexed isolated storage databases ([1:05:33](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3933s)).
- The default database index is `0` ([1:05:39](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3939s)).
- `SELECT index`: Switches to another data index environment (e.g., `SELECT 1`) ([1:05:45](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3945s)).
- Keys are strictly unique to their respective database index number ([1:06:43](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4003s)).

## Client Infrastructure

- `PING`: Connectivity check; returns `PONG` when active ([1:05:15](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3915s)).
- `ECHO message`: Echoes back text on the terminal ([1:05:26](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=3926s)).
- `CLIENT LIST`: Displays system details for all active client connections ([1:07:53](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4073s)).
- `CLIENT SETNAME name`: Assigns a descriptive identity string to the current connection ([1:08:23](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4103s)).
- `CLIENT KILL ID client_id`: Terminates an active connection forcefully using its ID ([1:09:07](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4147s)).

## Password Configuration

- `CONFIG SET requirepass "password"`: Instantly sets a plain text authentication lock ([1:09:52](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4192s)).
- `AUTH password`: Authenticates a client connection to lift restriction locks ([1:10:29](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4229s)).

---

# Geospatial Indexes

Redis uses a **spherical earth model** to compute distances ([1:11:57](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4317s)). It converts coordinates into a 52-bit integer **GeoHash** and manages them internally using **Sorted Sets** ([1:11:26](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4286s)). It is subject to a small error rate of around 0.5% ([1:12:03](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4323s)).

- `GEOADD key longitude latitude member`: Populates coordinates indexed to a specific name ([1:12:16](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4336s)).
- `GEOPOS key member`: Extracts coordinates for specified items ([1:14:32](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4472s)).
- `GEOHASH key member`: Generates a standard URL-mappable geographic hash text value ([1:13:27](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4407s)).
- `GEODIST key m1 m2 [unit]`: Computes a straight line distance between two members ([1:15:22](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4522s)). Supported units include meters (`m`), kilometers (`km`), miles (`mi`), and feet (`ft`) ([1:15:45](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4545s)).
- `GEORADIUS key long lat radius unit [WITHCOORD] [WITHDIST] [ASC|DESC]`: Searches and displays nearby location records around explicit coordinate points ([1:17:20](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4640s)).
- `GEORADIUSBYMEMBER key member radius unit`: Runs a circular radius lookup utilizing an existing stored item position as its center reference point ([1:20:19](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4819s)).

---

## Performance Benchmarking [[1](https://www.youtube.com/watch?v=XCsS_NVAa1g#:~:text=Redis%20is%20an%20in%2Dmemory%20data%20structure%20store,Redis%20in%20this%20crash%20course%20for%20beginners.)]

Redis includes an integrated performance utility tool named `redis-benchmark` to test server performance limits ([1:22:24](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=4944s)).

## Remote Testing Syntax

bash

```
redis-benchmark -h <host_ip> -p <port_number>
```

Use code with caution.

_(By default, it tests local configurations across all commands using a 3-byte data payload size via 50 parallel clients) ([1:23:47](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=5027s))._

Diagnostic Modifiers

- `-n <count>`: Changes total test command calls used during the evaluation sequence ([1:24:27](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=5067s)).
- `-d <size>`: Adjusts testing raw payload data packet size (e.g., `-d 100kb`) ([1:25:04](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=5104s)).
- `-c <clients>`: Sets the total volume of simultaneous parallel client load simulators ([1:26:30](https://www.youtube.com/watch?v=XCsS_NVAa1g&t=5190s)).

---