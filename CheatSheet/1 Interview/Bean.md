## Bean

In Spring, a **bean** is an object that is created, managed, and controlled by the **Spring IoC (Inversion of Control)** container.

Spring Bean Lifecycle

The lifecycle is the sequence Spring follows from creating a bean until destroying it.
```
        Application starts
              |
              v
      Bean Definition Loaded
              |
              v
      Bean Instantiation
              |
              v
      Dependency Injection
              |
              v
      BeanNameAware
              |
              v
      BeanFactoryAware
              |
              v
      BeanPostProcessor (before)
              |
              v
      @PostConstruct
              |
              v
      InitializingBean.afterPropertiesSet()
              |
              v
      Custom init-method
              |
              v
      Bean Ready to Use
              |
              v
      @PreDestroy
              |
              v
      DisposableBean.destroy()
              |
              v
      Bean Destroyed
```

## 1. Bean Instantiation

Spring creates the object.

Example:
```
@Component
public class UserService {

}
```
Spring internally does:
```
new UserService();
```

## 2. Dependency Injection

Spring injects dependencies.

Example:
```
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

}
```
Spring does:
```
UserService
      |
      |
UserRepository
```

## 3. Bean Name Awareness

If your bean implements:
```
BeanNameAware
```
Spring gives the bean its name.

Example:
```
public class MyBean implements BeanNameAware {

    public void setBeanName(String name){
        System.out.println(name);
    }
}
```
Output:
```
myBean
```

## 4. BeanPostProcessor

Runs custom logic before/after initialization.

Example:
```
@Component
public class MyProcessor 
implements BeanPostProcessor {

}
```
Methods:
```
postProcessBeforeInitialization()

postProcessAfterInitialization()
```
Spring uses this internally for:
AOP
proxies
annotations

## 5. @PostConstruct

Runs after dependencies are injected.

Example:
```
@Component
public class UserService {


    @PostConstruct
    public void init(){
        System.out.println("Bean initialized");
    }

}
```
Output:
```
Bean initialized
```
Common usage:
Load cache
Initialize variables
Validate configuration

## 6. Bean Ready

Now the bean can be used:
```
userService.createUser();
```

## 7. Bean Destruction

When application shuts down:
```
@PreDestroy
public void cleanup(){
    System.out.println("Cleaning resources");
}
```
Used for:

Closing connections
Releasing resources
Example Full Lifecycle
```
@Component
public class DatabaseService {

    @PostConstruct
    public void start(){
        System.out.println("Opening connection");
    }


    @PreDestroy
    public void stop(){
        System.out.println("Closing connection");
    }
}
```
Flow:
```
Spring starts
     |
Creates DatabaseService
     |
Injects dependencies
     |
@PostConstruct
     |
Application runs
     |
Application stops
     |
@PreDestroy
Bean Scopes affect lifecycle
```
Default:
```
@Scope("singleton")
```
One object per Spring container.
```
Controller
Service
Repository

      |
      v

One shared bean
```
Other scopes:

| Scope     | Lifecycle                       |
| --------- | ------------------------------- |
| singleton | One bean for entire application |
| prototype | New bean every request          |
| request   | One bean per HTTP request       |
| session   | One bean per HTTP session       |
