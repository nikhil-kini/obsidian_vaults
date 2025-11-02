https://www.turing.com/interview-questions/spring-boot

https://www.ambitionbox.com/interviews/accenture-interview-questions/java-developer/top-questions?campaign=topQuestionWidget

## Springboot 3.0

- **Java 17 baseline**: Spring Boot 3 requires Java 17 or higher. This means you can take advantage of the latest features and performance improvements that Java 17 offers.
- **Support for generating native images with GraalVM**: GraalVM is a high-performance virtual machine that can be used to generate native images of your Spring Boot applications. This can significantly improve the performance of your applications, especially on startup.
- **Improved observability with Micrometer and Micrometer Tracing**: Micrometer is a library that provides a unified API for collecting metrics from your Spring Boot applications. Micrometer Tracing is a library that provides tracing support for your Spring Boot applications. These libraries make it easier to monitor and troubleshoot your applications.
- **Support for Jakarta EE 10** with an EE 9 baseline: Jakarta EE 10 is the latest version of the Jakarta EE specification. Spring Boot 3 includes support for Jakarta EE 10, so you can now use Spring Boot to develop applications that are compliant with the Jakarta EE 10 specification.
- **Spring Framework 6:** Spring Boot 3 is based on Spring Framework 6, so it includes all of the new features and improvements that were introduced in Spring Framework 6.


## Java 8 and 17 features

**Java 8**
- Lambda expressions,
- Method references,
- Functional interfaces,
- Stream API,
- Default methods,
- Base64 Encode Decode,
- Static methods in interface,
- Optional class,
- Collectors class

**Java 17**
- Pattern Matching for Switch
```java
public String checkShape(Shape shape) {
    return switch (shape) {
        case Triangle t && (t.getNumberOfSides() != 3) -> "This is a weird triangle";
        case Circle c && (c.getNumberOfSides() != 0) -> "This is a weird circle";
        default -> "Just a normal shape";
    };
```
- MacOS enhancement (Aarch64), psuedo-number generator enhancement.

**Java 21**
- Record Pattern
```java
record Point(int x, int y) {}
    
public static int beforeRecordPattern(Object obj) {
    int sum = 0;
    if(obj instanceof Point p) {
        int x = p.x();
        int y = p.y();
        sum = x+y;
    }
    return sum;
}
    
public static int afterRecordPattern(Object obj) {
    if(obj instanceof Point(int x, int y)) {
        return x+y;
    }
    return 0;
}
```
- String Literal
```java
String name = "Baeldung";
String welcomeText = STR."Welcome to \{name}";
System.out.println(welcomeText);
```
- Virtual Threads - **Like platform threads, a virtual thread is also an instance of _java.lang.Thread_ class, but it isn’t tied to a specific OS thread**.
- Sequenced Collections - A [sequenced collection](https://www.baeldung.com/java-21-sequenced-collections) is a collection whose elements have a defined encounter order. It has first and last elements, and the elements between them have successors and predecessors.
![[Pasted image 20241118111945.png]]
## @Configuration vs @Component

> @Configuration Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime
> 
> @Component Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.
> 
> @Configuration is meta-annotated with @Component, therefore @Configuration classes are candidates for component scanning

## @WebMVCTest vs @SpringbootTest

>`SpringBootTest` is loading your full app (to some extend, by default it won't start the embedded container if there is one available, that's what the `webEnvironment` is there for). I wouldn't say that `@SpringBootTest` is a unit test of the controller but more an integration test, really. `WebMvcTest` is really a unit test of your controller in the sense that if it has dependency, you'll have to provide them yourself (either a config or a mock of some kind)

**Testing slices of the application** Sometimes you would like to test a simple “slice” of the application instead of auto-configuring the whole application. Spring Boot 1.4 introduces 4 new test annotations:
```
@WebMvcTest - for testing the controller layer
@JsonTest - for testing the JSON marshalling and unmarshalling
@DataJpaTest - for testing the repository layer
@RestClientTests - for testing REST clients
```
## OOPs features in details

- Object
- Class
- Inheritance
- Polymorphism
- Abstraction
- Encapsulation
- Coupling (class - strong coupling, interface - weak coupling)
- **Cohesion** refers to the level of a component which **performs a single well-defined task**. A single well-defined task is done by a highly cohesive method. The weakly cohesive method will split the task into separate parts. The **java.io package is a highly cohesive package because it has I/O related classes and interface**. However, the **java.util package is a weakly cohesive package** because it has unrelated classes and interfaces.
- Association represents the relationship between the objects.
	- One to One
	- One to Many
	- Many to One, and
	- Many to Many
>**Aggregation** is a way to achieve Association. It represents the **weak relationship** between objects. It is also termed as a ***has-a* relationship** in Java. `class A{ private B b}`
>The **composition** is also a way to achieve Association. There is a **strong relationship** between the containing object and the dependent object. the ***is-a* relationship**. `extends`, `implements`
# **@RequestParam vs @QueryParam vs @PathVariable vs @PathParam**

[Jakarta RESTful Web Services (JAX-RX)](https://en.wikipedia.org/wiki/Jakarta_RESTful_Web_Services)
Use Spring Restful

|                 |                         |                             |                                                               |
| --------------- | ----------------------- | --------------------------- | ------------------------------------------------------------- |
| Jersey (JAX-RS) | @PathParam              | @QueryParam                 | cannot pass `null`                                            |
| Spring RESTFul  | @PathVariable           | @RequestParam               | can pass `null` and also can restrict (required = true/false) |
| example         | http://xyz.ir/{segment} | http://xyz.ir/?param{param} |                                                               |

##  Spring Boot annotations and name some of them
Annotations are special **metadata tags** (starting with `@`) that tell Spring how to configure and manage components, beans, dependencies, and web endpoints.

They’re used for:

- Dependency Injection (DI)
- Configuration
- Web controllers
- Data access
- Aspect-oriented programming (AOP)
- Bootstrapping applications

#### **1️⃣ Core & Configuration Annotations**

|Annotation|Description|
|---|---|
|`@SpringBootApplication`|Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. Used to mark the main class.|
|`@Configuration`|Indicates that the class contains Spring bean definitions.|
|`@Bean`|Defines a bean to be managed by Spring’s IoC container.|
|`@ComponentScan`|Scans specified packages for Spring components.|
|`@PropertySource`|Loads properties file into Spring Environment.|

---

#### **2️⃣ Dependency Injection & Bean Annotations**

|Annotation|Description|
|---|---|
|`@Component`|Generic annotation for any Spring-managed component.|
|`@Service`|Marks a service class — a specialized `@Component`.|
|`@Repository`|Marks a DAO (Data Access Object); helps with exception translation.|
|`@Controller`|Marks a web controller (for MVC).|
|`@RestController`|Combines `@Controller` + `@ResponseBody` for REST APIs.|
|`@Autowired`|Automatically injects dependencies.|
|`@Qualifier`|Used with `@Autowired` to specify which bean to inject.|
|`@Value`|Injects property values from configuration files.|

---

#### **3️⃣ Web & REST Annotations**

|Annotation|Description|
|---|---|
|`@RequestMapping`|Maps HTTP requests to handler methods/classes.|
|`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`|Shorthand for `@RequestMapping` with specific HTTP methods.|
|`@PathVariable`|Binds a URI path variable to a method parameter.|
|`@RequestParam`|Binds a query parameter to a method parameter.|
|`@RequestBody`|Binds JSON/XML body of a request to a Java object.|
|`@ResponseBody`|Indicates that a method returns data directly, not a view.|
|`@CrossOrigin`|Enables Cross-Origin Resource Sharing (CORS).|

---

#### **4️⃣ Data Access (Spring Data JPA)**

|Annotation|Description|
|---|---|
|`@Entity`|Marks a class as a JPA entity (table).|
|`@Table`|Specifies the table name.|
|`@Id`|Marks the primary key field.|
|`@GeneratedValue`|Defines strategy for ID generation.|
|`@Column`|Customizes column mapping.|
|`@Transactional`|Declares that a method or class runs within a transaction.|

---

#### **5️⃣ Testing & Utility**

|Annotation|Description|
|---|---|
|`@SpringBootTest`|Loads the full application context for integration testing.|
|`@TestConfiguration`|Special configuration class for tests.|
|`@MockBean`|Adds a Mockito mock to the Spring context.|
## architecture of Spring Boot

Client Layer → Controller Layer → Service Layer → Repository Layer → Database Layer

## Controller and RestController.
`@Controller` is a **Spring MVC annotation** used to define a **web controller** that handles **HTTP requests** and returns **views (HTML, JSP, Thymeleaf, etc.)**.

```java
@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to Spring Boot!");
        return "home"; // returns view name "home.html" or "home.jsp"
    }

    @GetMapping("/greet")
    @ResponseBody
    public String greet() {
        return "Hello, Spring Boot!";  //returns plain text `"Hello, Spring Boot!"` because of `@ResponseBody`.
    }
}
```
## Actuators
It exposes useful **endpoints (REST APIs)** that give insight into:

- Health of the application
- Metrics (CPU, memory, HTTP requests
- Application properties
- Environment configuration
- Beans and thread dumps


## Wait, notify and notifyall

|Method|Purpose|
|---|---|
|`wait()`|Tells the current thread to **release the lock** and **wait** until another thread calls `notify()` or `notifyAll()` on the same object.|
|`notify()`|Wakes **one** thread that is waiting on the same object’s monitor.|
|`notifyAll()`|Wakes **all** threads waiting on the same object’s monitor.|

## Functional Interface lambda expression

A **Functional Interface** is an interface that has **exactly one abstract method** (SAM — _Single Abstract Method_).

A **Lambda Expression** is an **anonymous function** (no name, no class) used to implement a functional interface in a single line.

##  CRUD works in SpringBoot

|Operation|Meaning|HTTP Method|Example Endpoint|
|---|---|---|---|
|**C**|Create|`POST`|`/users`|
|**R**|Read|`GET`|`/users/{id}`|
|**U**|Update|`PUT`|`/users/{id}`|
|**D**|Delete|`DELETE`|`/users/{id}`|

## Various annotations in Spring AOP
|Term|Meaning|
|---|---|
|**Aspect**|A module that encapsulates cross-cutting logic (e.g., LoggingAspect).|
|**Join Point**|A point during program execution (like a method call) where you can apply an aspect.|
|**Advice**|The actual action taken (e.g., logging before a method runs).|
|**Pointcut**|An expression that selects _which join points_ an advice should run on.|
|**Weaving**|The process of linking aspects with target objects at runtime.|


|Annotation|Description|Example|
|---|---|---|
|**@Aspect**|Marks a class as an aspect containing advices.|`@Aspect public class LoggingAspect {}`|
|**@Before**|Runs **before** the target method executes.|`@Before("execution(* com.app.service.*.*(..))")`|
|**@After**|Runs **after** the target method (success or failure).|`@After("execution(* com.app.service.*.*(..))")`|
|**@AfterReturning**|Runs **after a successful** method execution.|`@AfterReturning(pointcut="execution(* com.app.service.*.*(..))", returning="result")`|
|**@AfterThrowing**|Runs **if method throws an exception**.|`@AfterThrowing(pointcut="execution(* com.app.service.*.*(..))", throwing="error")`|
|**@Around**|Runs **before and after** method execution (can modify arguments or results).|`@Around("execution(* com.app.service.*.*(..))")`|
|**@Pointcut**|Defines reusable expressions for join points.|`@Pointcut("execution(* com.app.service.*.*(..))")`|
## @Qualifier and @Primary
|Feature|`@Qualifier`|`@Primary`|
|---|---|---|
|**Purpose**|Select a specific bean by name|Set a default bean when multiple exist|
|**Scope**|Method parameter or field injection|Class-level annotation on a bean|
|**Priority**|Highest (overrides `@Primary`)|Lower priority than `@Qualifier`|
|**Use Case**|When you want a specific bean explicitly|When most injections should use this bean|
## - Bean Scopes in Springboot
|Scope|Description|Lifecycle|Annotation|
|---|---|---|---|
|**Singleton**|Default scope. Only **one instance** per Spring IoC container.|Created at startup, shared across the application.|`@Scope("singleton")` (optional)|
|**Prototype**|A **new instance** is created every time it is requested.|Spring doesn’t manage complete lifecycle.|`@Scope("prototype")`|
|**Request**|A **new instance per HTTP request**.|Lives for one HTTP request.|`@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)`|
|**Session**|A **new instance per HTTP session**.|Lives as long as the session is active.|`@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)`|
|**Application**|One bean instance per **ServletContext**.|Lives for the lifetime of the web application.|`@Scope(value = WebApplicationContext.SCOPE_APPLICATION)`|
|**Websocket**|One bean per **WebSocket session**.|Lives for the WebSocket session duration.|`@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)`|
## - Profiling in SpringBoot

|Feature|Description|Example|
|---|---|---|
|Profile-specific properties|`application-{profile}.properties`|`application-dev.properties`|
|Activate profile|Choose which profile to use|`spring.profiles.active=dev`|
|Profile-specific beans|`@Profile("dev")`|Only loaded when active profile matches|
|Multiple profiles|Load multiple sets of beans/config|`spring.profiles.active=dev,feature-x`|

## Linkedlist vs ArrayList

|Feature|ArrayList|LinkedList|
|---|---|---|
|**Data Structure**|Resizable **array**|**Doubly-linked list** (nodes connected by pointers)|
|**Implements**|`List`, `RandomAccess`, `Serializable`, `Cloneable`|`List`, `Deque`, `Serializable`, `Cloneable`|
|**Memory**|Stores elements in **contiguous memory**|Stores elements in **nodes**, each with data + pointers|
|**Access**|Fast **random access** using index (`O(1)`)|Slow **random access** (`O(n)`) — must traverse from head|

## Abstract class and Interface Difference

| Feature                      | Abstract Class                                                    | Interface                                                                  |
| ---------------------------- | ----------------------------------------------------------------- | -------------------------------------------------------------------------- |
| **Methods**                  | Can have **abstract + concrete** methods                          | **Abstract by default**; can have `default` and `static` methods (Java 8+) |
| **Variables**                | Can have **instance variables** (fields) with any access modifier | Fields are **public, static, final** by default                            |
| **Multiple Inheritance**     | **Not allowed** (a class can extend only one abstract class)      | **Allowed** (a class can implement multiple interfaces)                    |
| **Constructor**              | Can have constructors                                             | **Cannot have constructors**                                               |
| **Access Modifiers**         | Methods can be **private, protected, public**                     | Methods are **public** by default                                          |
| **Use Case**                 | Shared code among closely related classes                         | Define a **common contract** for unrelated classes                         |
| **Extending / Implementing** | `class A extends AbstractClass`                                   | `class B implements Interface1, Interface2`                                |
## HashSet and HashMap Implementations

- equals()
- hashCode()
- Buckets - Array of the node is called buckets. Each node has a data structure like a `LinkedList`.
- Index, n = size of array
```
Index = hashcode(Key) & (n-1)
```
- **Hash collision** - when two different **elements share same index**. It will be stored in Linked list - first 8 elements, Red Black Tree - after that
![[Pasted image 20241118123252.png]]

`HashSet` is backed by a `HashMap` internally, but the element you are adding to the `HashSet` is used as the key in the backing `HashMap`. For the **value**, a **dummy** value is used. Therefore the `HashSet`'s `contains(element)` simply calls the backing `HashMap`'s `containsKey(element)`.

### Hash Map Null
Incase of **null** key , Hashmap implementation consider it as special case and **doesnot call hashCode** method instead it stores **Entry object to 0 bucket location.**

While in **_`Hashmap`_** get method the checks if key is passed as _null_. Search Value for _null_ key in bucket _0_.
**Hence there can only be one null key in one** _`hashmap`_ **object.** And ONE value associated with it. (The last put value)


## What Is the Diamond Problem?

The **diamond problem** happens when **multiple inheritance** causes _ambiguity_ — i.e., when a class inherits the **same method** from **multiple paths**.

```java
nterface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void greet() {
        System.out.println("Hello from B");
    }
}

class C implements A, B {
    // ⚠️ Compilation error if you don't override greet()
}

// Solution
class C implements A, B {
    @Override
    public void greet() {
        // Choose one explicitly
        A.super.greet();   // or B.super.greet()
        System.out.println("Hello from C");
    }
}
```

1️⃣ **Class Wins Rule**
If a **class** provides a method implementation, it always overrides an interface default.
```java
class Base {
    void greet() {
        System.out.println("Hello from Base");
    }
}

interface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

class Derived extends Base implements A {}

public class Main {
    public static void main(String[] args) {
        new Derived().greet();  // ✅ "Hello from Base"
    }
}
```

2️⃣ **Sub-Interface Wins Rule**
If an interface **extends another interface** with the same method, the _most specific_ one wins.
```java
interface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

interface B extends A {
    default void greet() {
        System.out.println("Hello from B");
    }
}

class C implements B {}   // B overrides A

public class Main {
    public static void main(String[] args) {
        new C().greet();  // ✅ "Hello from B"
    }
}
```

|Feature|Abstract Class|Interface|
|---|---|---|
|Multiple inheritance|❌ Not allowed|✅ Allowed|
|Default methods|✅ Yes (via normal methods)|✅ Yes (Java 8+)|
|Diamond problem|❌ Avoided automatically|⚠️ Possible if defaults clash|
|Fields|Can have state|Only constants (`public static final`)|

In **Java 8**, interfaces were enhanced with:

- `default` methods → instance methods with implementation, can be overriden
- `static` methods → utility methods tied to the interface, cannot be overriden, invocation Interface.methodname()


## Why private in interface ?

You can’t make `validate()` private — because **before Java 9**, all interface methods were _implicitly public_.  
So, every implementing class could also call `validate()` — even though it’s **meant to be internal** helper logic.
Now, you can write **reusable internal helper methods** without exposing them to implementing classes.

```java
interface DataProcessor {
    default void processText(String text) {
        validate(text);
        System.out.println("Processing text: " + text);
    }

    default void processNumber(Integer number) {
        validate(number.toString());
        System.out.println("Processing number: " + number);
    }

    // ❌ Common logic duplicated in both methods
    default void validate(String data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Invalid data");
        }
    }
    
    // 🔒 Private helper (not visible to implementers)
    private void validate(String data) {
        if (data == null || data.isEmpty())
            throw new IllegalArgumentException("Invalid data");
    }

    // 🔒 Private static helper (shared by all)
    private static void logStart() {
        System.out.println("=== Starting Processing ===");
    }
}
```

## What are Variable Arguments (Varargs)?

**Variable arguments** (introduced in **Java 5**) allow a method to accept **zero or more arguments** of the same type — instead of specifying a fixed number of parameters.

You define them using an **ellipsis (`...`)**.  gives you array [] of data type.

✔ You can use `String... args` instead of `String[] args` in the `main()` method.  
✔ Both are valid and mean the same thing.


## Equal and hashCode together overriden why?

These two methods are **tightly linked** — they define what it means for two objects to be **equal** and how they behave in **hash-based collections** (like `HashMap`, `HashSet`, or `Hashtable`).

If two objects are equal according to the `equals()` method, they must have the same `hashCode()`.


```java
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return age == p.age && name.equals(p.name);
    }
    // ❌ hashCode not overridden
}

HashSet<Person> people = new HashSet<>();
people.add(new Person("Alice", 25));

// Not expected behavior
System.out.println(people.contains(new Person("Alice", 25)));  // FALSE
```

|Collection|Uses `equals()`|Uses `hashCode()`|Impact|
|---|---|---|---|
|`HashSet`|✅|✅|Determines uniqueness|
|`HashMap`|✅|✅|Determines key equality|
|`LinkedHashMap`|✅|✅|Same as HashMap but ordered|
|`TreeMap`|❌|❌|Uses `compareTo()` instead|
## What is a Marker Interface?

A **marker interface** is an **empty interface** — one that contains **no methods or fields** — used to **mark** or **tag** a class as having a special property or behavior.

`public interface Serializable {     // no methods }`

| Marker Interface         | Package     | Purpose                                                         |
| ------------------------ | ----------- | --------------------------------------------------------------- |
| `java.io.Serializable`   | `java.io`   | Marks that objects can be serialized (converted to byte stream) |
| `java.lang.Cloneable`    | `java.lang` | Marks that class supports cloning via `Object.clone()`          |
| `java.util.RandomAccess` | `java.util` | Marks that list supports fast random access (like `ArrayList`)  |

```java

public interface Auditable {
    // Marker — no methods
}
class User implements Auditable {
    String username;
}
class Product { }

void logAudit(Object obj) {
    if (obj instanceof Auditable) {  // type checking
        System.out.println("Auditing: " + obj.getClass().getSimpleName());
    } else {
        System.out.println("Not auditable.");
    }
}

```

## In Modern Java (Post–Java 5)

Marker interfaces are largely replaced by **annotations**,  
but they’re still used when you want **compile-time type safety**.

Example:  
Frameworks like **Spring**, **Hibernate**, or **JPA** sometimes use marker interfaces for configuration or scanning.

## What is the `volatile` Keyword?

`volatile` is a **modifier** you can apply to a variable (not a method, class, or object) to ensure that:

1. **Every thread reads its most up-to-date value** directly from _main memory_, not from its local CPU cache.
2. **Writes to the variable are immediately visible** to other threads.

## Why TreeSet Doesn’t Allow `null`

Because `TreeSet` needs to **compare elements** to maintain order.  
It uses either:

- **Natural ordering** (`Comparable.compareTo()`), or
- A **custom `Comparator`** (`compare()`).

When you insert `null`, it tries to compare it with other elements —  
and comparison like `null.compareTo("A")` or `comparator.compare(null, "A")`  
throws a **`NullPointerException`**. **RUNTIME EXCEPTION**

|Case|Behavior|
|---|---|
|✅ `HashSet`|Allows one `null` element|
|🚫 `TreeSet` (natural order)|Throws `NullPointerException`|
|⚠️ `TreeSet` (custom comparator handling nulls)|Allowed if comparator explicitly supports nulls|
```java
import java.util.*;

public class TreeSetAllowNull {
    public static void main(String[] args) {
        Comparator<String> nullSafeComparator =
            Comparator.nullsFirst(Comparator.naturalOrder());  // Custom comparator

        TreeSet<String> set = new TreeSet<>(nullSafeComparator);
        set.add("B");
        set.add(null);
        set.add("A");

        System.out.println(set);
    }
}

// output: [null, A, B]
```


## System.exit()

So, when `System.exit()` is called:

- The **JVM begins the shutdown sequence**.
- **No further code executes**, unless it’s in a **shutdown hook**.
- The control **never returns** to the caller.
- **Finally blocks** **may or may not run**, depending on timing.

```java
public class ExitDemo {
    public static void main(String[] args) {
        try {
            System.out.println("Inside try");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Inside catch");
        } finally {
            System.out.println("Inside finally");
        }
    }
}

//only : Inside try
```

## Microservice Components

| Component            | Purpose                             |
| -------------------- | ----------------------------------- |
| API Gateway          | Entry point, routing, security      |
| Service Registry     | Dynamic service discovery           |
| Config Server        | Centralized configuration           |
| Database per Service | Data ownership, isolation           |
| Message Broker       | Async communication                 |
| Logging & Monitoring | Observability, debugging            |
| Security             | AuthN, AuthZ, encryption            |
| CI/CD Pipeline       | Automated delivery                  |
| Containerization     | Isolation and deployment            |
| Service Mesh         | Manage network traffic and policies |
## Core Components of a Microservice Architecture

Here are the **main building blocks** that make up a microservice-based system 👇

---

### 🧱 1️⃣ **API Gateway**

- Acts as the **entry point** for all clients.    
- Routes incoming requests to the correct microservice.

- Handles:
    
    - Authentication
    - Rate limiting
    - Logging
    - Caching
    - Request aggregation        

✅ Example:  
Netflix **Zuul**, **Spring Cloud Gateway**, **Kong**, **NGINX**

---

### 🧭 2️⃣ **Service Registry & Discovery**

- Keeps track of **available microservice instances** (with their IPs and ports).
- Helps services find each other dynamically (no hardcoded URLs).    

✅ Example:  
**Eureka**, **Consul**, **Zookeeper**

---

### 💾 3️⃣ **Database per Service**

- Each microservice **owns its own database** to ensure **loose coupling**.    
- Different microservices can use different DBs (SQL, NoSQL, etc.)


✅ Example:

- ProductService → MySQL
- OrderService → PostgreSQL
- UserService → MongoDB    

---

### 🔁 4️⃣ **Inter-Service Communication**

- Services communicate via:
    
    - **Synchronous** (REST, gRPC)        
    - **Asynchronous** (Message queues, Kafka, RabbitMQ)


✅ Example:

- OrderService → PaymentService via REST    
- NotificationService ← OrderService via Kafka event


---

### 🧩 5️⃣ **Configuration Server**

- Stores externalized configurations for all microservices.
- Allows dynamic configuration updates without redeploying services.    

✅ Example:  
**Spring Cloud Config**, **HashiCorp Consul Config**, **AWS Parameter Store**

---

### 📊 6️⃣ **Centralized Logging**

- All microservices push logs to a **central log aggregator**.
- Enables monitoring, tracing, and debugging.


✅ Example Stack:  
**ELK Stack (Elasticsearch, Logstash, Kibana)**,  
or **Splunk**, **Graylog**

---

### 🧠 7️⃣ **Monitoring & Metrics**

- Tracks system health, latency, and performance.
- Helps detect failures early and enables auto-scaling.


✅ Example:  
**Prometheus + Grafana**, **New Relic**, **Datadog**

---

### 🔒 8️⃣ **Security**

- Implements authentication, authorization, and encryption.
- Usually centralized at the **API Gateway**.
- Uses standards like **OAuth2**, **JWT**, **OpenID Connect**.


✅ Example:  
**Keycloak**, **Okta**, **AWS Cognito**

---

### 📨 9️⃣ **Message Broker / Event Bus**

- Enables **asynchronous communication** and **event-driven** microservices.    
- Ensures decoupling between producers and consumers.


✅ Example:  
**Kafka**, **RabbitMQ**, **ActiveMQ**, **AWS SNS/SQS**

---

### 🧰 1️⃣0️⃣ **Service Mesh**

- Manages service-to-service communication automatically.

- Handles:
    
    - Load balancing
    - Security (mTLS)
    - Observability
    - Retry logic        

✅ Example:  
**Istio**, **Linkerd**, **Consul Connect**

---

### 🚀 1️⃣1️⃣ **Containerization & Orchestration**

- Each microservice runs in a **container**.
- Containers are managed by **orchestrators** for scaling, resilience, and deployment.


✅ Example:

- Container: **Docker**    
- Orchestration: **Kubernetes**, **ECS**, **OpenShift**

---

### 🧑‍💻 1️⃣2️⃣ **CI/CD Pipeline**

- Automates build, test, and deployment of microservices.
- Enales faster and safer releases.
✅ Example:  
**Jenkins**, **GitHub Actions**, **GitLab CI**, **ArgoCD**

---

### 🧾 1️⃣3️⃣ **Distributed Tracing**

- Tracks requests across multiple services for debugging latency issues.    

✅ Example:  
**Zipkin**, **Jaeger**, **OpenTelemetry**

---

### 🧠 1️⃣4️⃣ **Business Logic Layer**

- The core logic that performs the service’s specific function (e.g., placing an order, verifying payment).    
- Isolated and independent.

✅ Example:  
In a `PaymentService`, it might contain:

`public boolean processPayment(Order order) { ... }`

---

### 🧩 1️⃣5️⃣ **Data Access Layer**

- Responsible for data persistence and retrieval.
- Uses repositories or DAOs to interact with the database.

✅ Example:  
Spring Data JPA Repositories, MyBatis Mappers.



## What is Fault Isolation?

> **Fault Isolation** means **containing a failure** within a single component (or microservice)  
> so it **doesn’t bring down the entire system**.

| Mechanism            | Role in Fault Isolation                  |
| -------------------- | ---------------------------------------- |
| Independent services | Prevents shared failure                  |
| Circuit breaker      | Stops cascading failure                  |
| Bulkhead             | Isolates resource exhaustion             |
| Timeout/retry        | Avoids blocking                          |
| Health check         | Detects and isolates failure fast        |
| Redundancy           | Keeps service available                  |
| Monitoring           | Detects issues before system-wide impact |

| Tool                     | Use                                               |
| ------------------------ | ------------------------------------------------- |
| **Spring Boot Actuator** | Health endpoints (`/actuator/health`)             |
| **Prometheus + Grafana** | System metrics and alerts                         |
| **ELK Stack**            | Centralized logs                                  |
| **Zipkin / Jaeger**      | Distributed tracing (find failing service easily) |
Fault Isolation ensures that **one microservice’s failure doesn’t affect others**.  
It’s achieved via **independence, resilience patterns, monitoring, and redundancy**


## What is Load Balancing?

**Load Balancing** is the process of distributing network or application traffic **across multiple servers** to ensure:

- ✅ Better performance
- ✅ High availability
- ✅ Scalability
- ✅ Fault tolerance

## Two Main Types

|Type|Who decides where the request goes?|
|---|---|
|**Client-side Load Balancing**|The **client** selects the target server|
|**Server-side Load Balancing**|A **central load balancer** routes requests to servers|
## Server-Side Load Balancing

### 🏗️ How it works:

1. The **client sends a request** to a single entry point — the **load balancer**.
2. The **load balancer** decides which backend server (node) to send the request to.
3. The client does **not** know about the internal server details.


### 🔍 Example Architecture

```
Client → [Load Balancer] → Server1                       
                         ↘→ Server2                       
						  ↘→              Server3
```

### 🧠 Examples

- AWS Elastic Load Balancer (ELB)
- Nginx or HAProxy
- Google Cloud Load Balancer
- F5, Apache mod_proxy

### ⚙️ Common Algorithms

- Round Robin
- Least Connections
- IP Hash
- Weighted Round Robin

### ✅ Pros

- Easy for clients — they only call one endpoint
- Centralized management
- Easier to monitor and scale
- Can perform health checks and reroute if a node is down

### ⚠️ Cons

- Single point of failure (if LB fails — unless redundant)
- Adds an extra network hop (slight latency)

---

## 🧱 2️⃣ Client-Side Load Balancing

### 🏗️ How it works:

1. The **client knows** about all available servers (from a service registry).
2. The **client chooses** which server to send the request to (based on its algorithm).
3. No central load balancer — load is distributed by clients.

### 🔍 Example Architecture

```sql
Client → Server1 
Client → Server2 
Client → Server3
```

The **client library** performs the balancing logic.

### 🧠 Examples

- Netflix **Ribbon** (used with Spring Cloud)
- gRPC built-in client load balancing
- Consul, Eureka + RestTemplate in Spring Boot
- Kubernetes client libraries with service discovery

### ⚙️ Common Algorithms

- Round Robin
- Random
- Weighted
- Response Time–based


### ✅ Pros

- Removes central load balancer (less infrastructure)
- Better scalability for high number of clients
- Faster routing (no middle hop)


### ⚠️ Cons

- Clients must track and update server list (via Service Discovery)
- Harder to update configuration dynamically
- Less centralized visibility


## What is a Transaction?

A **transaction** is a unit of work that must be executed **completely or not at all** — this is the famous **ACID** principle:

| Property | Meaning                                       |
| -------- | --------------------------------------------- |
| **A**    | Atomicity — all operations succeed or none do |
| **C**    | Consistency — data integrity is maintained    |
| **I**    | Isolation — transactions don’t interfere      |
| **D**    | Durability — once committed, data persists    |
## The Challenge in Microservices

In **microservices**, each service has:

- Its **own database**
- Its **own transactions**
- **Runs independently**

### There are mainly **two** strategies:

|Strategy|Description|
|---|---|
|**2-Phase Commit (2PC)**|Distributed ACID-style transaction|
|**Saga Pattern**|Distributed long-running transaction using events & compensations|

Two-Phase Commit (2PC) (**rarely used** in modern cloud systems.)
- A **coordinator** starts the transaction.
- Each service (participant) performs its part and says “ready to commit”.
- If all say yes → coordinator commits.
- If any fail → coordinator sends rollback to all.

```
Coordinator
 ├──> Order Service (prepare)
 ├──> Payment Service (prepare)
 ├──> Inventory Service (prepare)
If all OK:
 ├──> Commit all
Else:
 ├──> Rollback all
```

## Saga Pattern (Preferred)

A **Saga** is a sequence of local transactions, where each local transaction updates its database and **publishes an event** to trigger the next transaction.

If one fails — previously completed steps are **compensated** with undo operations.

### 💡 Types of Sagas:

1. **Choreography-based Saga** → Event-driven
2. **Orchestration-based Saga** → Central coordinator controls flow

### Choreography-Based Saga

Each service listens to events and reacts accordingly.

#### Example: Order Processing Flow
```sql
1️⃣ Order Service → creates order → emits "OrderCreated" event  
2️⃣ Payment Service → listens, charges customer → emits "PaymentSuccess"  
3️⃣ Inventory Service → reserves items → emits "InventoryReserved"  
4️⃣ Shipping Service → ships items → emits "OrderCompleted"
```
If something fails (e.g., Payment fails):

- Payment Service emits "PaymentFailed"
- Order Service listens → cancels the order    

✅ Fully event-driven  
⚠️ Can become complex with many services (event chaos)

### Orchestration-Based Saga

A **central coordinator (Saga Orchestrator)** manages the flow.
```sql
Order Service (Orchestrator)
 ├── Calls Payment Service
 ├── Calls Inventory Service
 ├── Calls Shipping Service
```
If Inventory fails, the orchestrator tells Payment to **refund** (compensating transaction).

✅ Centralized control  
⚠️ Slight coupling with orchestrator logic

|Tool|Use|
|---|---|
|**Spring Cloud Data Flow**|Orchestration and workflow|
|**Camunda / Zeebe**|Workflow engine for Sagas|
|**Axon Framework**|Event-driven Saga orchestration|
|**Temporal.io**|Durable workflow orchestration|
|**Kafka**|Event bus for Choreography Saga|

## Disable _All_ Auto-Configuration
```java
@SpringBootApplication(exclude = AutoConfiguration.class)
```

## Disable Specific Auto-Configurations
```java
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    SecurityAutoConfiguration.class
})
```
or 
```prop
spring.autoconfigure.exclude=\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
```

## Synchronized Hashmap vs Concurrent HashMap
| Operation         | SynchronizedMap                  | ConcurrentHashMap                |
| ----------------- | -------------------------------- | -------------------------------- |
| Thread Safety     | ✅ Yes                            | ✅ Yes                            |
| Lock granularity  | One global lock                  | Fine-grained (per bucket / node) |
| Read performance  | Slow (blocks on every operation) | Fast (mostly lock-free)          |
| Write performance | Slow                             | High — allows concurrent writes  |
| Iterator          | Must be synchronized manually    | Fail-safe (non-blocking)         |
| Null keys/values  | ✅ Allowed                        | ❌ Not allowed                    |
| Use case          | Low concurrency                  | High concurrency                 |
## What is Java Flight Recorder?

**Java Flight Recorder (JFR)** is a **built-in, low-overhead performance monitoring and profiling tool** for the JVM.

It records events inside the JVM — like:

- CPU usage
- GC pauses
- Thread contention
- Method profiling
-  I/O operations
- Locks, memory allocations, exceptions, etc.

## Reflection API - Used to test private methods


## Testing

- Unit testing - method level, code lines, Code coverage  ( uses Jacoco)
- Integration testing - working between component
- end to end testing - whole MVC testing
- Performance testing

## Ensure code quality

- Code coverage
- Sonar lint for IDE
- Sonar Cube Server
- Code Review

## Java Memory Leak

A Java memory leak occurs when unused objects remain referenced and not garbage collected.  
Use **profilers**, **heap dumps**, and **structured coding practices** (close resources, weak references, bounded caches) to prevent it.

| Category                | Best Practice                             |
| ----------------------- | ----------------------------------------- |
| **Resource management** | Always close I/O and JDBC resources       |
| **Collections**         | Use bounded or weak collections           |
| **Threads**             | Use managed thread pools                  |
| **Listeners**           | Unregister listeners, use weak references |
| **Caching**             | Use eviction policies (LRU, TTL)          |
| **Profiling**           | Run memory profiler in pre-prod tests     |
| **Static data**         | Avoid static mutable objects              |
**Poorly managed Collections**
Objects stored in a `Map` or `List` and never removed.

`Map<String, User> sessionMap = new HashMap<>();`

✅ **Fix:**
- Use **WeakHashMap** for caches.
- Periodically clear old data.

**Tools**

| **JProfiler / YourKit** | Commercial profilers | Deep thread + memory analysis |
| ----------------------- | -------------------- | ----------------------------- |

| **Spring Boot Actuator + Micrometer** | Monitor heap & GC metrics | For production microservices |
| ------------------------------------- | ------------------------- | ---------------------------- |


