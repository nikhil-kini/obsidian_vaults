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

- [Object](https://www.javatpoint.com/object-and-class-in-java)
- Class
- [Inheritance](https://www.javatpoint.com/inheritance-in-java)
- [Polymorphism](https://www.javatpoint.com/runtime-polymorphism-in-java)
- [Abstraction](https://www.javatpoint.com/abstract-class-in-java)
- [Encapsulation](https://www.javatpoint.com/encapsulation)
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

## architecture of Spring Boot

## Controller and RestController.

## Actuators

## Wait, notify and notifyall

## Functional Interface lambda expression
##  CRUD works in SpringBoot

## Various annotations in Spring AOP

## @Qualifier and @Primary

## - Bean Scopes in Springboot

## - Profiling in SpringBoot

## Linkedlist vs ArrayList

## Abstract class and Interface Difference

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