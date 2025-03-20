
A [**microservice**](https://en.wikipedia.org/wiki/Microservices) architecture is a variant of the [service-oriented architecture](https://en.wikipedia.org/wiki/Service-oriented_architecture "Service-oriented architecture") structural style. It is an [architectural pattern](https://en.wikipedia.org/wiki/Architectural_pattern "Architectural pattern") that arranges an application as a collection of [loosely coupled](https://en.wikipedia.org/wiki/Loose_coupling "Loose coupling"), [fine-grained](https://en.wikipedia.org/wiki/Granularity "Granularity") services, communicating through [lightweight protocols](https://en.wikipedia.org/wiki/Lightweight_protocol "Lightweight protocol"). One of its goals is that teams can develop and [deploy](https://en.wikipedia.org/wiki/Software_deployment "Software deployment") their services independently of others.

**Note** : Manage Transient Errors, Secure talking between the apps.


## Microservices Principles

### Single Responsibility Principle
The single responsibility principle states that a class or a module in a program should have only one responsibility. Any microservice cannot serve more than one responsibility, at a time.

### Modeled around business domain
Microservice never restrict itself from accepting appropriate technology stack or database. The stack or database is most suitable for solving the business purpose.

### Isolated Failure
The large application can remain mostly unaffected by the failure of a single module. It is possible that a service can fail at any time. So, it is important to detect failure quickly, if possible, automatically restore failure.

### Infrastructure Automation
The infrastructure automation is the process of scripting environments. With the help of scripting environment, we can apply the same configuration to a single node or thousands of nodes. It is also known as configuration management, scripted infrastructures, and system configuration management.

### Deploy independently
Microservices are platform agnostic. It means we can design and deploy them independently without affecting the other services.


## MSA VS SOA

| Microservice Based Architecture (MSA)                                                                                                           | Service-Oriented Architecture (SOA)                                            |
| ----------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------ |
| Microservices uses **lightweight protocols** such as **REST**, and **HTTP**, etc.                                                               | SOA supports **multi-message protocols**.                                      |
| It focuses on **decoupling**.                                                                                                                   | It focuses on application service **reusability**.                             |
| It uses a **simple messaging system** for communication.                                                                                        | It uses **Enterprise Service Bus** (ESB) for communication.                    |
| Microservices follows "**share as little as possible**" architecture approach.                                                                  | SOA follows "**share as much as possible architecture**" approach.             |
| Microservices are much better in **fault tolerance** in comparison to SOA.                                                                      | SOA is not better in fault tolerance in comparison to MSA.                     |
| Each microservice have an **independent** database.                                                                                             | SOA services share the **whole** data storage.                                 |
| MSA used **modern** relational databases.                                                                                                       | SOA used **traditional** relational databases.                                 |
| MSA tries to **minimize** sharing through bounded context (the coupling of components and its data as a single unit with minimal dependencies). | SOA **enhances** component sharing.                                            |
| It is better suited for the **smaller** and **well portioned**, web-based system.                                                               | It is better for a **large** and **complex** business application environment. |