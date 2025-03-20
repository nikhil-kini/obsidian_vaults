
>It’s important to note that even though it is, in fact, a non-blocking client and it belongs to the _spring-webflux_ library, the solution offers **support for both synchronous and asynchronous operations**, making it suitable also for applications running on a Servlet Stack.


```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

## 3 steps

## Create an instance
```java
WebClient client = WebClient.create("http://localhost:8080");
```

```java
WebClient client = WebClient.builder()
  .baseUrl("http://localhost:8080")
  .defaultCookie("cookieKey", "cookieValue")
  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
  .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
  .build();
```

We can:
- **set the connection timeout via the _ChannelOption.CONNECT_TIMEOUT_MILLIS_ option**
- **set the read and write timeouts using a _ReadTimeoutHandler_ and a _WriteTimeoutHandler_, respectively**
- **configure a response timeout using the _responseTimeout_ directive**
```java
// Creating a WebClient Instance with Timeouts

HttpClient httpClient = HttpClient.create()
  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
  .responseTimeout(Duration.ofMillis(5000))
  .doOnConnected(conn -> 
    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
      .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

WebClient client = WebClient.builder()
  .clientConnector(new ReactorClientHttpConnector(httpClient))
  .build();
```


## Make a request

- **Method**
```java
UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
```
or
```java
UriSpec<RequestBodySpec> uriSpec = client.post();
```

- **URI path**
We can pass it to the _uri_ API as a _String:_
```java
RequestBodySpec bodySpec = uriSpec.uri("/resource");
```
OR, Using a _UriBuilder Function_:
```java
RequestBodySpec bodySpec = uriSpec.uri(
  uriBuilder -> uriBuilder.pathSegment("/resource").build());
```

- **Body**
common and straightforward option is using the _bodyValue_ method:
```java
RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue("data");

//equivalent
RequestHeadersSpec<?> headersSpec = bodySpec.body(
  BodyInserters.fromValue("data"));
```
Or by presenting a _Publisher_ (and the type of elements that will be published) to the _body_ method:
```java
RequestHeadersSpec<?> headersSpec = bodySpec.body(
  Mono.just(new Foo("name")), Foo.class);

//equivalent
RequestHeadersSpec headersSpec = bodySpec.body(
  BodyInserters.fromPublisher(Mono.just("data")),
  String.class);
```
For advance scenarios
```java
LinkedMultiValueMap map = new LinkedMultiValueMap();
map.add("key1", "value1");
map.add("key2", "value2");
RequestHeadersSpec<?> headersSpec = bodySpec.body(
  BodyInserters.fromMultipartData(map));
```

- **Headers**
To add additional header from **those that have already been set when instantiating the client**
```java
ResponseSpec responseSpec = headersSpec.header(
    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
  .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
  .acceptCharset(StandardCharsets.UTF_8)
  .ifNoneMatch("*")
  .ifModifiedSince(ZonedDateTime.now())
  .retrieve();
```



>Note:  the request spec variables (_WebClient.UriSpec_, _WebClient.RequestBodySpec_, _WebClient.RequestHeadersSpec_, _WebClient.ResponseSpec), **These directives shouldn’t be reused for different requests,** they retrieve references, and therefore the latter operations would modify the definitions we made in previous steps.

## Handle the response

We can achieve this by using either the `exchangeToMono/exchangeToFlux` or the `retrieve` method.
```java
Mono<String> response = headersSpec.exchangeToMono(response -> {
  if (response.statusCode().equals(HttpStatus.OK)) {
      return response.bodyToMono(String.class);
  } else if (response.statusCode().is4xxClientError()) {
      return Mono.just("Error response");
  } else {
      return response.createException()
        .flatMap(Mono::error);
  }
});
```
`ResponseSpec.bodyToMono` method, which will throw a `WebClientException` if the status code is **4xx** (client error) or **5xx** (server error)

```java
Mono<String> response = headersSpec.retrieve()
  .bodyToMono(String.class);
```



## WebTestClient

The _WebTestClient_ is the **main entry point for testing WebFlux server endpoints**. It has a very similar API to the _WebClient_, and it delegates most of the work to an internal _WebClient_ instance focusing mainly on providing a test context. The _DefaultWebTestClient_ class is a single interface implementation.