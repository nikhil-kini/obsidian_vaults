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
- **Hash collision** - when two different **elements share same index**. It will be stored in Linked list
![[Pasted image 20241118123252.png]]

`HashSet` is backed by a `HashMap` internally, but the element you are adding to the `HashSet` is used as the key in the backing `HashMap`. For the **value**, a **dummy** value is used. Therefore the `HashSet`'s `contains(element)` simply calls the backing `HashMap`'s `containsKey(element)`.

### Hash Map Null
Incase of **null** key , Hashmap implementation consider it as special case and **doesnot call hashCode** method instead it stores **Entry object to 0 bucket location.**

While in **_`Hashmap`_** get method the checks if key is passed as _null_. Search Value for _null_ key in bucket _0_.
**Hence there can only be one null key in one** _`hashmap`_ **object.** And ONE value associated with it. (The last put value)
## SQL Question based on Aggregate Function and Group by clause