
# What is Circuit Breaker Pattern ?

Circuit Breaker is one of the widely used best practice in the real world distributed systems

Consider a scenario where your application **A** makes synchronous calls to a remote service **R**. If service **R** becomes unavailable or responds very slowly due to performance issues, this situation will negatively impact application **A** as well.
[[12 Key Clock]]
If the application **A** receives a large number of requests, then there will be lot of threads in the waiting state, waiting for the response from R, leading to ultimately crashing the application **A**. To avoid this issue, we can make use of the Circuit Breaker Pattern, which works very similar to the Circuit Breaker used in our homes to protect the electrical devices from the power spikes. If there is a power spike, then the Circuit Breaker is tripped and will stop the flow of electricity. Similarly, when the remote service **R** in our case, if it's unavailable or responding very slowly, we can introduce a Circuit Breaker that will stop the calls to the service, for a certain amount of time. After this timeout, the Circuit Breaker will again start allowing calls to the service **R** gradually.


API Gateway is the main service that is calling 3 other services, so this will be the best place to use Circuit Breaker, similarly we can also implement this feature in the Order Service as the service is calling Inventory Service to fetch the inventory information.
**Where a synchronous service call is made for another service, we can and should implement the circuit breaker**

## Different States in the Circuit Breaker Pattern

At any given point of time, a circuit breaker will be in different states like:

- **Open**: This states indicates that the Circuit Breaker is open, and all the traffic going through the Circuit Breaker will be blocked.
    
- **Half-Open**: In this state, the Circuit Breaker will start allowing gradually the traffic to the remote service **R**
    
- **Closed**: In this state, the Circuit Breaker will allow all the requests to the service, which means that the service **R** is working well without any problems.


## Dependencies required for circuit breaker

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```
The first dependency adds the **Resilience4J** library in our project and the second dependency adds the Spring Boot Actuator that provides us with useful endpoints to get useful information about our application like **Metrics**, we can make use of these endpoints to check the state of the Resilience4J Circuit Breaker.

- modify routes to add circuit breaker
```java
	@Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
                .filter(circuitBreaker("orderServiceCircuitBreaker", // id
                URI.create("forward:/fallbackRoute"))) // forward on circuit open
                .build();
    }

// fallback response implementation
	@Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")            // id
                .GET("/fallbackRoute", request -> // path and http method
                ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable, please try again later")) // response
                .build();
    }

```
the **circuitBreaker()** method is taking an ID which is a string and then a URL parameter which points to a fallback endpoint that will be displayed when the requests are blocked when the CircuitBreaker is **OPEN**

We have the **fallbackRoute()** bean that is defined as a fallback route at the path - **/fallbackRoute** that sends a HTTP 503 Service Unavailable response back to the client.

**application.properties**
```properties
#Resilinece4j Properties
resilience4j.circuitbreaker.configs.default.registerHealthIndicator=true
resilience4j.circuitbreaker.configs.default.slidingWindowType=COUNT_BASED
resilience4j.circuitbreaker.configs.default.slidingWindowSize=10
resilience4j.circuitbreaker.configs.default.failureRateThreshold=50
resilience4j.circuitbreaker.configs.default.waitDurationInOpenState=5s
resilience4j.circuitbreaker.configs.default.permittedNumberOfCallsInHalfOpenState=3
resilience4j.circuitbreaker.configs.default.automaticTransitionFromOpenToHalfOpenEnabled=true
resilience4j.circuitbreaker.configs.default.minimum-number-of-calls=5

#Resilience4J Timeout Properties
resilience4j.timelimiter.instances.inventory.timeout-duration=3s

#Resilience4J Retry Properties
resilience4j.retry.instances.inventory.max-attempts=3
resilience4j.retry.instances.inventory.wait-duration=5s

```

## Enable Circuit Breaker for Timeouts

We can enable Circuit Breaker to implement a timeout, when the remote service is taking a very long time to respond, for that all we have to do is add the following property:

```
resilience4j.timelimiter.configs.default.timeout-duration=3s
```

With this configuration, the circuit breaker will be OPEN, when the remote service is taking more than 3 seconds to send back the response.

## Implement Retries

Sometimes, the service can be unavailable due to a small network issue (or) any other minor issue, in those cases, it's better to retry the call instead of directly activating the Circuit Breaker. For this reason, the Resilience4J library allows us to implement retries by adding the following configuration:

```
#Resilience4J Retry Properties
resilience4j.retry.configs.default.max-attempts=3
resilience4j.retry.configs.default.wait-duration=2s
```

The above configuration will retry for a maximum of 3 times, with a wait of 5 seconds in between the retries.

## For Implementing in service
- Add above dependency
- Add above application.properties

- To add to OpenFeign Client
```java
//Sl4j
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory") 
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")  // circuit breaker
    @Retry(name = "inventory")  // to retry call, to account for network inconsistency
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

// Default method implementation for the fallback in case of call failure
    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }

// Default method implementation for the fallback in case of call failure
    default boolean fallbackMethod(String code, Integer quantity, SpecificExeption specificException) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }
}
```

- To implement Timeout, we can configure the RestClient to have a connection and read time out through the requestFactory() method.

```java
@Configuration
public class RestClientConfig {

    @Value("${inventory.url}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryClient() {  // create a proxy for restclient
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .requestFactory(getClientRequestFactory())
                .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

//set timeout for the circut to trigger
    private ClientHttpRequestFactory getClientRequestFactory() {
        ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(3))
                .withReadTimeout(Duration.ofSeconds(3));
        return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
    }
}
```
