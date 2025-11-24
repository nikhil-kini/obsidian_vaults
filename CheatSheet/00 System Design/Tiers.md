These three form the classic 3-tier architecture.

```
+------------------------+
|   Presentation Tier    |
|  (UI - Web/Mobile)     |
+-----------+------------+
            |
            v
+------------------------+
| Application / Logic    |
| (API, Services, BLL)   |
+-----------+------------+
            |
            v
+------------------------+
|      Data Tier         |
| (DB, Storage, Cache)   |
+------------------------+
```

4-Tier Architecture Example

- Client (UI)
- API Gateway
- Business Services
- Database

5-Tier Architecture Example

- Client
- Web Server
- Application Server
- Integration Layer (Messaging/Queue)
- Database


## N-Tier

**N-Tier architecture** is a software architecture pattern where an application is separated into multiple logical layers (tiers), each with a specific responsibility.

“N” means any number of tiers (3-tier, 4-tier, etc.).


## Key Principles

| Principle                  | Description                                        |
| -------------------------- | -------------------------------------------------- |
| **Separation of concerns** | Each layer has a dedicated responsibility          |
| **High cohesion**          | A layer groups related functionality               |
| **Low coupling**           | Layers interact through explicit boundaries        |
| **Testability**            | Business logic can be tested without UI or DB      |
| **Replaceability**         | Layers can be swapped (e.g., change DB technology) |

| Advantages                           | Disadvantages                                 |
| ------------------------------------ | --------------------------------------------- |
| Clear separation of responsibilities | Layers can become rigid & slow                |
| Easy maintainability                 | Changes may cascade through many layers       |
| Supports testing & modularity        | Extra boilerplate for simple apps             |
| Good for traditional monoliths       | Hard to use for highly scalable microservices |
| Supports team specialization         | Deep call chain → performance overhead        |

| Use Case                                   | Suitability                          |
| ------------------------------------------ | ------------------------------------ |
| Monolithic applications                    | ⭐ Excellent                          |
| Enterprise systems (banks, insurance, ERP) | ⭐ Excellent                          |
| CRUD-based business apps                   | ⭐ Excellent                          |
| Microservices                              | ⚠️ Works, but can become heavyweight |
| Real-time high-performance systems         | ❌ Not ideal                          |

| Feature              | Layered              | Hexagonal (Ports & Adapters)        | Clean Architecture        |
| -------------------- | -------------------- | ----------------------------------- | ------------------------- |
| Dependency Direction | Downward             | Toward domain core                  | Toward domain core        |
| Testability          | Medium               | High                                | Very high                 |
| Coupling             | Higher               | Low                                 | Very low                  |
| Best For             | Enterprise monoliths | Microservices, maintainable systems | Complex, scalable systems |

