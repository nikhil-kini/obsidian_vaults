
# `@ConfigurationProperties` for non final 
```java
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BreweryClient {
	private String apihost;

	public setapi(){} //setter is a must for binding
}
```

- `application.properties`
```properties
sfg.brewery.apihost= value
```

# For final

```java
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotalConnections;
    private final Integer defaultMaxTotalConnetions;
    private final Integer connectionRequestTimeout;
    private final Integer socketTimeout;

    public BlockingRestTemplateCustomizer(@Value("${sfg.maxtotalconnections}") Integer maxTotalConnections,
                                          @Value("${sfg.defaultmaxtotalconnections}") Integer defaultMaxTotalConnetions,
                                          @Value("${sfg.connectionrequesttimeout}")Integer connectionRequestTimeout,
                                          @Value("${sfg.sockettimeout}")Integer socketTimeout) {
        this.maxTotalConnections = maxTotalConnections;
        this.defaultMaxTotalConnetions = defaultMaxTotalConnetions;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.socketTimeout = socketTimeout;
    }
}
```


```properties
sfg.maxtotalconnections=100
sfg.defaultmaxtotalconnections=20
sfg.connectionrequesttimeout=3000
sfg.sockettimeout=3000
```