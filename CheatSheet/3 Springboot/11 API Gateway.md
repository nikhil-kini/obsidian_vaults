## What is an API Gateway?

An API Gateway also called an Edge Server, acts as an entry point for our microservices, so that external clients can access the services easily. It also helps us to handle cross-cutting concerns like Monitoring, Security, etc. In some instances, API Gateway also acts as Load Balancers.

## Why to use API Gateway?

In our microservice project landscape, we have 3 services accessible to the user:
- Product Service
- Order Service
- Inventory service

For example, imagine that external clients like Web and Mobile applications consume these three independent services through the exposed endpoints. If the internal implementation of these services changes, then also the clients need to update the Endpoints on their side.

To workaround this issue, we use an API Gateway as the facade that provides an abstraction over the internal microservices.

## Spring Cloud Gateway MVC

**Spring Cloud Gateway MVC** is a library under the Spring Cloud project, that provides the API Gateway functionality. 

To implement this feature, Spring Cloud Gateway uses the below building blocks:

- Routes
- Predicates
- Filters

### Routes

A Route is the basic building block of the gateway, it can be defined using a unique-Id, a destination URI, and a collection of predicates and filters

### Predicates

A Predicate is nothing but a criteria or a condition that you define to match against the incoming HTTP Request, for example, you can create a routing rule where you want to route the requests that have a specific Header and Request Parameter to Service A, then you can consider the headers and request parameters you want to match against the request as predicates.

### Filters

Filters are components that allow you to modify the requests and responses before they are sent to the destination.

Let's see how we can implement the API Gateway in our project using Spring Cloud Gateway MVC.


To create the routing rules defined above, for that we can follow 2 approaches

- Using Java API
- Using Property files


## Using JAVA
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration(proxyBeanMethods = false)
public class Routes {
@Bean
public RouterFunction<ServerResponse> productServiceRoute() {
return route("product_service")
.route(RequestPredicates.path("/api/product"), http("http://localhost:8080"))
.build();
}

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return route("order_service")  // Route ID to identify not with mapping
                .route(RequestPredicates.path("/api/order"), http("http://localhost:8081"))// mapping ..:9000/api/.. to ..:8081/api/..
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return route("inventory_service")
                .route(RequestPredicates.path("/api/inventory"), http("http://localhost:8082"))
                .build();
    }


@Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("inventory_service_swagger")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(setPath("/api-docs")) // Map ..:9000/aggregate/.. to ..:8082/api-docs
                .build();
    }

}
```

The **route()** method takes in two arguments one for the path which is the predicate we want to match in this case (/api/product), and the second argument is `http("<target-destination-url>")` which points to the target destination ie. product service that is running at [http://localhost:8080](http://localhost:8080).  -- **Mapping**

the only differences are obvious, with the path being` /api/order`, routed to the **Order Service**, and the path `/api/inventory` to the **Inventory Service**.

`applicaiton.properties`
```
server.port=9000
```