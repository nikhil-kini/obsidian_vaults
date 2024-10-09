[Design Patterns](https://www.sourcecodeexamples.net/search/label/Design%20Pattern) denote the best computer programming practices in object-oriented software development. Spring framework has been built by using the following design pattern or standard practices.   
  
  Here is the list of well-known design patterns used in the [Spring Framework](https://www.javaguides.net/p/spring-tutorial-beginners-to-expert.html).

# Proxy Design Pattern

The proxy pattern is used heavily in [AOP](https://docs.spring.io/spring/docs/2.5.x/reference/aop.html#aop-understanding-aop-proxies) and [remoting](https://docs.spring.io/spring/docs/2.5.x/reference/remoting.html).

A good example of a proxy design pattern is [org.springframework.aop.framework.ProxyFactoryBean](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/aop/framework/ProxyFactoryBean.html). This factory constructs an AOP proxy based on Spring beans. The proxy provides a surrogate or placeholder for another object to control access to it.  

> Read more details about Proxy Design Pattern here at [Proxy Design Pattern](https://ramesh-java-design-patterns.blogspot.in/2017/12/proxy-design-pattern.html).

# Singleton Design Pattern

The Singleton design pattern ensures that there will exist only a single instance of the object in the memory that could provide services.

  

In the spring framework, the Singleton is the default scope and the IOC container creates exactly one instance of the object per spring IOC container.  
  
To define a bean as a singleton in XML, we would write, for example:

```xml
<bean id="accountService" class="com.foo.DefaultAccountService"/>

<!-- the following is equivalent, though redundant (singleton scope is the default) -->
<bean id="accountService" class="com.foo.DefaultAccountService" scope="singleton"/>
```

To define a bean as a singleton in java based bean configuration, we would write, for example:

```java
@Configuration
public class AppConfiguration {

    @Bean
    @Scope("singleton") // default scope 
    public UserService userService(){
        return new UserService();
    }
}
```

# Factory design pattern

This pattern allows the initialization of an object through a public static method, called the factory method. The Spring container acts as a factory, producing and managing beans based on configuration and request. There are two types of Spring containers:

  
**Spring BeanFactory Container:** It is the simplest container present in the spring framework which provides the basic support for DI (Dependency Injection). We use the following interface to work with this container.  
**Spring ApplicationContext Container:** It is another container present in the spring container that adds extra enterprise-specific functionality. These functionalities include the capability to resolve textual messages from a properties file and publish application events to attentive event listeners. We use the following interface to work with this container.  
  
ApplicationContext Example:

```java
package net.javaguides.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.close();
    }
}
```

> Read more details about the Factory design pattern here at a [Factory design pattern](https://ramesh-java-design-patterns.blogspot.in/2017/12/factory-design-pattern.html).

# Template Design Pattern 

Spring provides various template classes, like JdbcTemplate for database operations, where common steps of an algorithm are abstracted in a base class and specific steps are implemented in derived classes.

  

This pattern is used extensively to deal with boilerplate repeated code (such as closing connections cleanly, etc..). For example [JdbcTemplate](https://docs.spring.io/spring/docs/2.5.x/reference/jdbc.html#jdbc-JdbcTemplate), [JmsTemplate](https://docs.spring.io/spring/docs/2.5.x/reference/jms.html#jms-jmstemplate), and [JpaTemplate](https://docs.spring.io/spring/docs/2.5.x/reference/orm.html#orm-jpa-template).  

> Read more details about Template Design Pattern here at [Template Design Pattern](https://ramesh-java-design-patterns.blogspot.in/2017/12/template-method-design-pattern.html).

# Command Pattern

Spring's JdbcTemplate uses the Command pattern, where SQL statements are encapsulated as objects, providing a common interface for executing them. 

# Chain of Responsibility Pattern

Spring's filter chains, like the ones used in Spring Security, employ the Chain of Responsibility pattern, where a request is passed along a chain of handlers. 

# Builder Pattern

Spring's BeanFactory and ApplicationContext use the builder pattern to create complex objects with different configurations.

# Model View Controller Pattern

Spring leverage the MVC pattern to structure web applications. In Spring MVC uses the Model-View-Controller (MVC) architecture, where controllers handle incoming requests, models manage data and business logic, and views display information to users. This separation enhances code organization, maintainability, and teamwork in software development projects.

> Read more details about Model View Controller here at [Model View Controller Pattern](https://www.javaguides.net/2019/08/model-view-controller-mvc-design-in-java.html).

# Front Controller Pattern

Spring provides [DispatcherServlet](http://static.springsource.org/spring/docs/2.5.x/api/org/springframework/web/servlet/DispatcherServlet.html) to ensure an incoming request gets dispatched to your controllers.  
  
The front controller design pattern is used to provide a centralized request-handling mechanism so that all requests will be handled by a single handler. This handler can do the authentication/ authorization/ logging or tracking of requests and then pass the requests to corresponding handlers. 

> Read more details about Front Controller Pattern here at [Front Controller Pattern](http://ramesh-java-design-patterns.blogspot.in/2018/01/front-controller.html).

# View Helper Pattern

Spring has a number of custom JSP tags, and velocity macros, to assist in separating code from presentation in views.  
  
**View Helper Pattern** separates the static view such as JSPs from the processing of the business model data.  
  
Frameworks like Spring and Struts provide their own tag libraries to encapsulate processing logic in a helper instead of a view such as JSP files.

> Read more details about View Helper Pattern here at [View Helper Pattern](http://ramesh-java-design-patterns.blogspot.in/2018/02/view-helper-pattern.html).

  

# Dependency Injection (DI) Pattern

Spring is renowned for its extensive use of Dependency Injection, where objects are passed their dependencies rather than creating them internally. This promotes loose coupling, easier testing, and modular development.  

> Read more details about the Dependency injection Pattern here at [Dependency](https://en.wikipedia.org/wiki/Dependency_injection) [injection Pattern](https://en.wikipedia.org/wiki/Dependency_injection).

# Service Locator Pattern 

[ServiceLocatorFactoryBean](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/beans/factory/config/ServiceLocatorFactoryBean.html) stores the information of all the beans in the context. When the client code asks for a service (bean) using a name, it simply locates that bean in the context and returns it. Client code does not need to write spring-related code to locate a bean.

  
The service locator design pattern is used when we want to locate various services using JNDI lookup. Considering the high cost of looking up JNDI for a service, the Service Locator pattern makes use of the caching technique. For the first time, a service is required, Service Locator looks up in JNDI and caches the service object. Further lookup or the same service via Service Locator is done in its cache which improves the performance of the application to a great extent.  

> Read more details about Service Locator Pattern here at [Service Locator Pattern](http://ramesh-java-design-patterns.blogspot.in/2018/02/service-locator-pattern.html).

# Observer Pattern

Spring's event-handling mechanism uses the Observer pattern. Application events are published to listeners, enabling communication between loosely coupled components.  

> Read more details about Observer Design Pattern here at [Observer Design Pattern](https://ramesh-java-design-patterns.blogspot.in/2017/12/observer-design-pattern.html).

# Context Object Pattern 

Context object pattern encapsulating system data in a Context Object allows it to be shared with other parts of the application without coupling the application to a specific protocol.

  
The [ApplicationContext](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/ApplicationContext.html) is the central interface within a Spring application for providing configuration information to the application.  

> Read more details about the Context Object Pattern here at [Context Object Pattern](https://ramesh-java-design-patterns.blogspot.in/2018/04/context-object-pattern.html).