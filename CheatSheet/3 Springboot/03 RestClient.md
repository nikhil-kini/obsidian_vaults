
## 1. Introduction[](https://www.baeldung.com/spring-boot-restclient#introduction)

_RestClient_ is a synchronous HTTP client introduced in Spring Framework 6.1 M2 that supersedes [_RestTemplate_](https://www.baeldung.com/rest-template). A synchronous HTTP client sends and receives HTTP requests and responses in a blocking manner, meaning it waits for each request to complete before proceeding to the next one.

## 2. _RestClient_ and _RestTemplate_[](https://www.baeldung.com/spring-boot-restclient#resttemplate)

_RestTemplate_, as the name suggests, is built on a template design pattern. It’s a behavioral design pattern that defines the skeleton of an algorithm in a method, allowing subclasses to provide specific implementations for certain steps. While it’s a powerful pattern, it creates a need for overloading, which can be inconvenient.

**To improve on this, _RestClient_ features a fluent API.** A fluent API is a design pattern that allows method chaining in a way that makes the code more readable and expressive by sequentially calling methods on an object, often without the need for intermediate variables.

Let’s start with creating a basic _RestClient_:

```java
RestClient restClient = RestClient.create();
```

## 3. Simple Fetching With HTTP Request Methods[](https://www.baeldung.com/spring-boot-restclient#methods)

Similar to _RestTemplate,_ or any other rest client, **_RestClient_ allows us to make HTTP calls with request methods**. Let’s walk through different HTTP methods to create, retrieve, modify, and delete resources.

We’ll operate on an elementary _Article_ class:

```java
public class Article {
    Integer id;
    String title;
    // constructor and getters
}
```

### 3.1. Use GET to Retrieve Resources[](https://www.baeldung.com/spring-boot-restclient#1-use-get-to-retrieve-resources)

**We’ll use the GET HTTP method to request and retrieve data from a specified resource on a web server without modifying it.** It’s primarily employed for read-only operations in web applications.

To start, let’s get a simple _String_ as the response without any serialization to our custom class:

```java
String result = restClient.get()
  .uri(uriBase + "/articles")
  .retrieve()
  .body(String.class);
```

### 3.2. Use POST to Create a Resource[](https://www.baeldung.com/spring-boot-restclient#2-use-post-to-create-a-resource)

**We’ll use the POST HTTP method to submit data to a resource on a web server, often to create new records or resources in web applications.** Unlike the GET method, which retrieves data, POST is designed for sending data to be processed by the server, such as when submitting a web form.

The URI should define what resource we want to process.

Let’s send a simple Article with an ID equal to 1 to our server:

```java
Article article = new Article(1, "How to use RestClient");
ResponseEntity<Void> response = restClient.post()
  .uri(uriBase + "/articles")
  .contentType(APPLICATION_JSON)
  .body(article)
  .retrieve()
  .toBodilessEntity();
```

Because we specified the “_APPLICATION_JSON”_ content type, the instance of the _Article_ class will be automatically serialized to JSON by the Jackson library under the hood. In this example, we ignore the response body using the _toBodilessEntity()_ method. A POST endpoint doesn’t need to, and often doesn’t, return any payload.

### 3.3. Use PUT to Update a Resource[](https://www.baeldung.com/spring-boot-restclient#3-use-put-to-update-a-resource)

Next, **we’ll look at the PUT HTTP method employed to update or replace an existing resource with the data provided**. It’s commonly used for modifying existing entities, or other resources in web applications. Typically, we need to specify the updated resource, ensuring a complete replacement.

Let’s modify the article we created in the previous paragraph. The URI we provide should identify the resource we want to change:

```java
Article article = new Article(1, "How to use RestClient even better");
ResponseEntity<Void> response = restClient.put()
  .uri(uriBase + "/articles/1")
  .contentType(APPLICATION_JSON)
  .body(article)
  .retrieve()
  .toBodilessEntity();
```

Similar to the previous paragraph, we’ll rely on _RestClient_ to serialize our payload and ignore the response.

### 3.4. Use DELETE to Remove a Resource[](https://www.baeldung.com/spring-boot-restclient#4-use-delete-to-remove-a-resource)

**We’ll use the DELETE HTTP method to request the removal of a specified resource from a web server.** Similar to GET endpoints, we usually don’t provide any payload for the request, and rely on parameters encoded in the URI:

```java
ResponseEntity<Void> response = restClient.delete()
  .uri(uriBase + "/articles/1")
  .retrieve()
  .toBodilessEntity();
```

## 4. Support for Request Attributes[](https://www.baeldung.com/spring-boot-restclient#support-for-request-attributes)

Regarding support for request attributes of the type supported in _WebClient_, it’s important to understand that **_WebClient_ introduced attributes to address a limitation, specifically, of reactive environments**. This limitation is the lack of dependable thread-locals. Since _RestTemplate_ and _RestClient_ can use thread locals, we rarely need request attributes in this sense.

A few use cases for attributes with a _RestClient_, such as passing attributes to interceptors, do exist. To facilitate that, Spring Framework 6.2 adds _getAttributes_ to the _org.springframework.http.HttpRequest_ interface. It returns a mutable map of request attributes for an _HttpRequest_ request. We can create an interceptor to update request attributes:

```java
@Test
void updateRequestAttribute() throws Exception {
    String attrName = "attr1";
    String attrValue = "value1";

    assertDoesNotThrow(() -> { 
      ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
        request.getAttributes().put(attrName, attrValue);
	return execution.execute(request, body);
      };
    });
}
```

Under most other circumstances, though, **we can build a request URI with query parameters based on a map, without using the request attributes API**, for example:

```java
RestClient restClient = RestClient.builder()
    .baseUrl("https://example.com/api")
    .build();

String pathVariable = "pathVariable";
ResponseEntity response = restClient.get()
    .uri(uriBuilder -> uriBuilder
      .path("/" + pathVariable)
      .queryParam("param1", "value1")
      .queryParam("param2", "value2")
      .build())
    .header("Content-Type", "application/json")
    .retrieve()
    .toEntity(String.class)
    .block();
```

## 5. Deserializing Response[](https://www.baeldung.com/spring-boot-restclient#deserializing-response)

We often want to serialize the request and deserialize the response to some class we can efficiently operate on. **The _RestClient_ is equipped with the ability to perform JSON-to-object conversions**, a functionality powered by the Jackson library.

Moreover, we can use all data types supported by _RestTemplate_ because of the shared utilization of message converters.

Let’s retrieve an article by its ID, and serialize it to the instance of the _Article_ class:

```java
Article article = restClient.get()
  .uri(uriBase + "/articles/1")
  .retrieve()
  .body(Article.class);
```

Specifying the class of the body is a bit more complicated when we want to get an instance of some generic class, like _List_. For example, if we want to get all the articles, we’ll get the `_List<Article>_` object. In this case, we can use the _ParameterizedTypeReference_ abstract class to tell _RestClient_ what object we’ll get.

We don’t even need to specify the generic type, Java will infer the type for us:

```java
List<Article> articles = restClient.get()
  .uri(uriBase + "/articles")
  .retrieve()
  .body(new ParameterizedTypeReference<>() {});
```

## 6. Parsing Response With Exchange[](https://www.baeldung.com/spring-boot-restclient#parsing-response-with-exchange)

**The _RestClient_ includes the _exchange()_ method for handling more advanced situations by granting access to the underlying HTTP request and response.** As such, the library won’t apply default handlers, and we must process the status ourselves.

Let’s say the service we’re communicating with returns a 204 status code when no articles are in the database. Because of that slightly nonstandard behavior, we want to handle it in a special way. We’ll throw an _ArticleNotFoundException_ exception when the status code is equal to 204, and also a more generic exception when the status code isn’t equal to 200:

```java
List<Article> article = restClient.get()
  .uri(uriBase + "/articles")
  .exchange((request, response) -> {
      if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(204))) {
          throw new ArticleNotFoundException();
      } else if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))) {
          return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
      } else {
          throw new InvalidArticleResponseException();
      }
});
```

Because we’re working with a raw response here, we also need to deserialize the body of the response ourselves using [_ObjectMapper_](https://www.baeldung.com/jackson-object-mapper-tutorial).

## 7. Error Handling[](https://www.baeldung.com/spring-boot-restclient#error)

By default, when _RestClient_ encounters a 4xx or 5xx status code in the HTTP response, it raises an exception that’s a subclass of _RestClientException_. **We can override this behavior by implementing our status handler.**

Let’s write one that will throw a custom exception when we can’t find the article:

```java
Article article = restClient.get()
  .uri(uriBase + "/articles/1234")
  .retrieve()
  .onStatus(status -> status.value() == 404, (request, response) -> {
      throw new ArticleNotFoundException(response);
  })
  .body(Article.class);
```

## 8. Building _RestClient_ From _RestTemplate_[](https://www.baeldung.com/spring-boot-restclient#legacy)

_RestClient_ is the successor of _RestTemplate,_ and in older codebases, we’re very likely to encounter implementation using _RestTemplate_.

Fortunately, it’s straightforward to create a _RestClient_ instance with a configuration of the old _RestTemplate_:

```java
RestTemplate oldRestTemplate;
RestClient restClient = RestClient.create(oldRestTemplate);
```