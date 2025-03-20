
***Soon to be deprecated.  Use RestClient  for synchronous and WebClient async. [refer this](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-restclient)***
> Note: Making the HTTP client Non-blocking doesn't make the application Reactive. It improves the HTTP call performance.

[URI builder ref](https://www.baeldung.com/spring-uricomponentsbuilder)
[Rest Template](https://www.baeldung.com/spring-5-webclient)

## Override the default HTTP client to improve performance

- Blocking implementation of client (Apache HTTP client, uses advance java non-blocking acrh). By default spring uses (SimpleRestFactory)

```xml
<dependency>
	<groupId>org.apache.httpcomponents</groupId>
	<artifactId>httpasyncclient</artifactId>
	<version>4.1.4</version>
</dependency>
```

```java
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
```

- Non-blocking implementation of the client (Apache HTTP ASYNC client), depricated in favor of **spring-reactive (uses Netty client)**
```java
//@Component
public class NIORestTemplateCustomizer implements RestTemplateCustomizer {

    public ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
        final DefaultConnectingIOReactor ioreactor = new DefaultConnectingIOReactor(IOReactorConfig.custom().
                setConnectTimeout(3000).
                setIoThreadCount(4).
                setSoTimeout(3000).
                build());

        final PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioreactor);
        connectionManager.setDefaultMaxPerRoute(100);
        connectionManager.setMaxTotal(1000);

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);

    }

    @Override
    public void customize(RestTemplate restTemplate) {
        try {
            restTemplate.setRequestFactory(clientHttpRequestFactory());
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
    }
}
```


## Rest Template usage

```java
@RequestMapping("/api/v1")
@RestController
public class RestTempController {
	
	@GetMapping("/foos")
	public String getCont() {
		// GET Simple
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/v1/ex/foos";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.toString();
	}
	
	@GetMapping("/beer")
	public String getbeer() {
		// GET Mapped to Object
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/v1/beer";
		ResponseEntity<Beer> response = restTemplate.getForEntity(url, Beer.class);
		return response.getBody().getBeerName();
	}
	
	@GetMapping("/beer2")
	public String getbeer2() throws JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/v1/beer";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		// Usage of Object Mapper
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode name = root.path("upc");

		// To access Header
		HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
		return name.asText() + httpHeaders.getContentType().toString();
	}

}
```


```java
@Test
	void test1() {
		RestTemplate restTemplate = new RestTemplate();
		
		// Refer link to build complex URI
		
		String url = "http://localhost:8080/api/v1/ex/bars3?id=1";
		String url2 = "http://localhost:8080/api/v1/ex/bars?id=1";
		
		HttpEntity<Beer> request = new HttpEntity<Beer>(new Beer(1L,"M", "e", "d"));
		
		Beer beer = restTemplate.postForObject(url, request, Beer.class);
		System.out.println(beer.toString());
		Assertions.assertNotNull(beer);
		Assertions.assertEquals(beer.getBeerName(), "M recived");
		
		URI location = restTemplate
				  .postForLocation(url, request);
		System.out.println(location);
		Assertions.assertNotNull(location);
		
		
		// Genric for all HTTP methods
		
		ResponseEntity<Beer> response = restTemplate.exchange(url, HttpMethod.POST,  request, Beer.class);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		Assertions.assertEquals(response.getBody().getBeerName(), "M recived");
		
		// OPTIONS for allow
		
		Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(url);
		optionsForAllow.forEach(System.out::println);
		
		// DELETE
		
		restTemplate.delete(url);
		
		
		// FORM encoding for submit
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("id", "1");
		
		HttpEntity<MultiValueMap<String, String>> request_build = new HttpEntity<>(map, headers);
		
		ResponseEntity<String> response_build = restTemplate.postForEntity(url2, request_build, String.class);
		Assertions.assertEquals(response_build.getStatusCode(), HttpStatus.CREATED);
		
		// PUT With exchange() and a Request Callback
		// See this in the ref url 
		
		ResponseEntity<Beer> response_3 = restTemplate
				  .exchange(url, HttpMethod.POST, request, Beer.class);
				Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		
				Beer updatedInstance =  Beer.builder().id(2L).build();
				updatedInstance.setId(response.getBody().getId());
				
				String resourceUrl = url + '/' + response.getBody().getId();
				
				restTemplate.execute(
				  resourceUrl, 
				  HttpMethod.PUT, 
				  requestCallback(updatedInstance), 
				  clientHttpResponse -> null);
		
		
		// Configure Timeout
		
		RestTemplate restTemplate_2 = new RestTemplate(getClientHttpRequestFactory());
	}
	
	
	RequestCallback requestCallback(final Beer updatedInstance) {
	    return clientHttpRequest -> {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
	        clientHttpRequest.getHeaders().add(
	          HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	        clientHttpRequest.getHeaders().add(
	          HttpHeaders.AUTHORIZATION, "Basic " + getBase64EncodedLogPass());
	    };
	}
	
	private String getBase64EncodedLogPass() {
		// TODO Auto-generated method stub
		return null;
	}


	private ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 5000;
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(timeout);
	    return clientHttpRequestFactory;
	}
```