
**Open API** - Tool that documents the API endpoints in JSON or YAML.
**Swagger** - UI Tool that takes Open API response and creates UI for it. Since we are in browser we can hit those endpoint.

## Add Dependency

Spring Boot 3.x requires to use version 2 of springdoc-openapi: (For MVC, diff for Webflux)
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```
- for Webflux
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
    <version>2.1.0</version>
</dependency>
```



## Configure and access

### Open API
- `application.properties`
```plaintext
springdoc.api-docs.path=/api-docs
```

Then, we’ll be able to access the docs at:
```plaintext
http://localhost:8080/api-docs
```

The OpenAPI definitions are in JSON format by default. For _yaml_ format, we can obtain the definitions at:
```plaintext
http://localhost:8080/api-docs.yaml
```

### Swagger

for swagger UI
```xml
<dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.5.0</version>
</dependency>
```

`application.properties' file:
```plaintext
springdoc.swagger-ui.path=/swagger-ui-custom.html
```

As another example, we can **sort the API** paths according to their **HTTP methods** 
```plaintext
springdoc.swagger-ui.operationsSorter=method
```

So now our API documentation will be available at [_http://localhost:8080/swagger-ui-custom.html_](http://localhost:8080/swagger-ui-custom.html).


- Automatic Document Generation Using JSR-303 Bean Validation
```java
public class Book {
    private long id;
    
    @NotBlank
    @Size(min = 0, max = 20)
    private String title;
    
    @NotBlank
    @Size(min = 0, max = 30)
    private String author;
}
```

- Generate Documentation Using `@ControllerAdvice` and `@ResponseStatus`
```java
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)            // needs to be added for documentation
    
    public ResponseEntity<String> handleConversion(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleBookNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
```

## Config Document name and more

``` java
package com.techie.microservices.product.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Product Service API")
                        .description("This is the REST API for Product Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Product Service Wiki Documentation")
                        .url("https://product-service-dummy-url.com/docs"));
    }
}
```
## Add Description to Response part of doc (_@Operation_ and _@ApiResponses_)

```java
@Operation(summary = "Get a book by its id")  // title of end point
@ApiResponses(value = { // main response list

  @ApiResponse(responseCode = "200", description = "Found the book", // success 
    content = { @Content(mediaType = "application/json",  // content type
      schema = @Schema(implementation = Book.class)) }),  // schema of the response
      
  @ApiResponse(responseCode = "400", description = "Invalid id supplied",// error
    content = @Content), 
    
  @ApiResponse(responseCode = "404", description = "Book not found", 
    content = @Content) })
@GetMapping("/{id}")
public Book findById(@Parameter(description = "id of book to be searched") 
  @PathVariable long id) {
    return repository.findById(id).orElseThrow(() -> new BookNotFoundException());
}
```




## Add Description to Request body part of doc (@io....RequestBody)

```java

// Response section
@Operation(summary = "Create a new book")
@ApiResponses(value = { 
   @ApiResponse(responseCode = "201", description = "Book created successfully",
    content = { @Content(mediaType = "application/json",
      schema = @Schema(implementation = Book.class)) }),
  @ApiResponse(responseCode = "400", description = "Invalid input provided") })

@PostMapping("/")

// Request section annotation
public Book createBook(@io.swagger.v3.oas.annotations.parameters.RequestBody(
    description = "Book to create", required = true,   // desc
    content = @Content(mediaType = "application/json",   // content type
      schema = @Schema(implementation = Book.class),    // request schema
      examples = @ExampleObject(value = "{ \"title\": \"New Book\", \"author\": \"Author Name\" }")))                      // example with populated schema
    @RequestBody Book book) {
    return repository.save(book);
}
```


## Aggregating the documentation in API Gateway

We can aggregate all the documentation and expose it in a single place in the API Gateway.

To do that add the below dependencies to the pom.xml of the API Gateway service.

```xml
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.5.0</version>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-api</artifactId>
			<version>2.5.0</version>
		</dependency>
```

Next, let's add the below properties to aggregate the URLs of all the 3 services.

```properties
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.api-docs.enabled=true
# List of urls and name, that maps PATH to Swagger UI from the routed new aggregate open-api (JSON/YML data below)
springdoc.swagger-ui.urls[0].name=Product Service
springdoc.swagger-ui.urls[0].url=/aggregate/product-service/v3/api-docs
springdoc.swagger-ui.urls[1].name=Order Service
springdoc.swagger-ui.urls[1].url=/aggregate/order-service/v3/api-docs
springdoc.swagger-ui.urls[2].name=Inventory Service
springdoc.swagger-ui.urls[2].url=/aggregate/inventory-service/v3/api-docs
```

We defined each service with a separate URL, whenever the user visits this URL, we have to route this request to the appropriate service, and for that, we need to add the corresponding routes in the **Routes.java** class.

```java
// Config Routes of the gateway, Maps Open API (JSON OR YMAL not UI)
    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoutes(){
    	return GatewayRouterFunctions.route("product_service_swagger")
    			.route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
    			.filter(FilterFunctions.setPath("/api-docs"))
    			.build();
    }
    
    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(setPath("/api-docs"))
                .build();
    }

@Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("inventory_service_swagger")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(setPath("/api-docs"))
                .build();
    }
```



Next, we have to add the security configuration to **make sure that API Gateway allows the requests without authentication.**


```java
@Configuration
public class SecurityConfig {

    private final String[] freeResourceUrls = {"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/aggregate/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(freeResourceUrls).permitAll()
                        .anyRequest().authenticated())
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }



// CORS configuration to the API gateway
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
```


In the above configuration, we have defined a variable called **freeResourceUrls**, where we should permit all the requests to these paths. To allow the calls to the downstream microservices, we added the path **/aggregate/** that covers the path for all the 3 services:

/aggregate/product-service/v3/api-docs

/aggregate/inventory-service/v3/api-docs

/aggregate/order-service/v3/api-docs

Lastly, we have also defined CORS configuration, as we will be accessing different services through the browser from API Gateway.


## CORS (for regular micro-service not gateway)

We also need to update the microservices to define CORS, or else we will get a CORS ERROR while accessing the API Documentation. So, let's add the below CORS Configuration in all the services by creating a class **CorsConfig.java**

## Spring Boot CORS – Method level with @CrossOrigin
```java
 @CrossOrigin(origins = {
            "http://domain1.com",
            "http://domain2.com"
        },
        allowedHeaders = "X-AUTH-TOKEN",
        allowCredentials = "false",
        maxAge = 15 * 60,
        methods = {
            RequestMethod.GET,
            RequestMethod.POST
        })

    @GetMapping("/users/{username}/todos")
    public List < Todo > getAllTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }
```
## Spring Boot CORS – Class level with @CrossOrigin

```java
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class TodoController {

	@GetMapping("/users/{username}/todos")
    public List < Todo > getAllTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }
}
```

## Global CORS Configuration
```java
package com.techie.microservices.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowCredentials(false);
    }
}
```