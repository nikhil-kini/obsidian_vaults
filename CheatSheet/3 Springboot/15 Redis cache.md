![[Pasted image 20251023115744.png]]
![[Pasted image 20251023115851.png]]
![[Pasted image 20251023115921.png]]

```yml
services:
  redis:
    image: redis:7.4.2
    ports:
      - "6379:6379"
```

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
    <version>3.1.5</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <version>3.1.5</version>
</dependency>

%% for running docker compose file %%
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-docker-compose</artifactId>
	<version>3.4.3</version>
</dependency>

```

```java
@SpringBootApplication
@EnableCaching     // Add this annotaion
public class SpringBootRedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisCacheApplication.class, args);
    }

}
```

```properties
spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.username= 
spring.data.redis.password=
spring.cache.type=redis
```

```java
@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new Jackson2JsonRedisSerializer<>(ProductDto.class)));
// for generic use "new GenericJackson2JsonRedisSerializer()"

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }
}

```
This gives us **more control over the default configuration.** For example, we can set the desired time-to-live (TTL) values and customize the default serialization strategy for in-flight cache creation.

for even more control over the cache
```java
@Bean
public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    return (builder) -> builder
      .withCacheConfiguration("itemCache",
        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
      .withCacheConfiguration("customerCache",
        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
}
```

```java
@Service
public class ProductService {

    public static final String PRODUCT_CACHE = "products";  // cache name
    private final ProductRepository productRepository;
    private final CacheManager cacheManager;     // for non annotation driven cache access and more conrtol

    public ProductService(ProductRepository productRepository, CacheManager cacheManager) {
        this.productRepository = productRepository;
        this.cacheManger = cacheManager;
    }

    @CachePut(value = PRODUCT_CACHE, key = "#result.id()") // uses spring expression language i.e reternVal.id() for key in cache sotre
    public ProductDto createProduct(ProductDto productDto) {
        var product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());
		
		
		
        Product savedProduct = productRepository.save(product);
        
        // alternative to annotation cache 
		Cache productcache = cacheManager.getCache("PRODUCT_CACHE");
		productcache.put(savedProduct.getId(), savedProduct);
		
        return new ProductDto(savedProduct.getId(), savedProduct.getName(),
                savedProduct.getPrice());
    }

    @Cacheable(value = PRODUCT_CACHE, key = "#productId") // get product from cache
    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find product with id " + productId));
        return new ProductDto(product.getId(), product.getName(),
                product.getPrice());
    }

    @CachePut(value = PRODUCT_CACHE, key = "#result.id()")
    public ProductDto updateProduct(ProductDto productDto) {
        Long productId = productDto.id();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find product with id " + productId));

        product.setName(productDto.name());
        product.setPrice(productDto.price());

        Product updatedProduct = productRepository.save(product);
        return new ProductDto(updatedProduct.getId(), updatedProduct.getName(),
                updatedProduct.getPrice());
    }

    @CacheEvict(value = PRODUCT_CACHE, key = "#productId") // delete product from cache
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
```


Testing 
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class SpringBootRedisCacheApplicationTests {

    @Container
    @ServiceConnection
    static GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:7.4.2"))
            .withExposedPorts(6379);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CacheManager cacheManager;
    @MockitoSpyBean         // to test whether the value is picked from cache or database use this conservatively in testing has it can cause issues
    private ProductRepository productRepositorySpy;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        productRepository.deleteAll(); // Ensure a clean database for each test
    }

    @Test
    void testCreateProductAndCacheIt() throws Exception {
        ProductDto productDto = new ProductDto(null, "Laptop", BigDecimal.valueOf(1200L));

        // Step 1: Create a Product
        MvcResult result = mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andReturn();

        ProductDto createdProduct = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDto.class);
        Long productId = createdProduct.id();

        // Step 2: Check Product Exists in DB
        Assertions.assertTrue(productRepository.findById(productId).isPresent());

        // Step 3: Check Cache
        Cache cache = cacheManager.getCache(ProductService.PRODUCT_CACHE);
        assertNotNull(cache);
        assertNotNull(cache.get(productId, ProductDto.class));
    }

    @Test
    void testGetProductAndVerifyCache() throws Exception {
        // Step 1: Save product in DB
        Product product = new Product();
        product.setName("Phone");
        product.setPrice(BigDecimal.valueOf(800L));
        productRepository.save(product);

        // Step 2: Fetch product
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Phone"));

        Mockito.verify(productRepositorySpy, Mockito.times(1)).findById(product.getId());

        Mockito.clearInvocations(productRepositorySpy);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Phone"));

        Mockito.verify(productRepositorySpy, Mockito.times(0)).findById(product.getId());
    }

    @Test
    void testUpdateProductAndVerifyCache() throws Exception {
        // Step 1: Create and Save Product
        Product product = new Product();
        product.setName("Tablet");
        product.setPrice(BigDecimal.valueOf(500L));
        product = productRepository.save(product);

        ProductDto updatedProductDto = new ProductDto(product.getId(), "Updated Tablet", BigDecimal.valueOf(550L));

        // Step 2: Update Product
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProductDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Tablet"))
                .andExpect(jsonPath("$.price").value(550.0));

        // Step 3: Verify Cache is Updated
        Cache cache = cacheManager.getCache(ProductService.PRODUCT_CACHE);
        assertNotNull(cache);
        ProductDto cachedProduct = cache.get(product.getId(), ProductDto.class);
        assertNotNull(cachedProduct);
        Assertions.assertEquals("Updated Tablet", cachedProduct.name());
    }

    @Test
    void testDeleteProductAndEvictCache() throws Exception {
        // Step 1: Create and Save Product
        Product product = new Product();
        product.setName("Smartwatch");
        product.setPrice(BigDecimal.valueOf(250L));
        product = productRepository.save(product);

        // Step 2: Delete Product
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/product/" + product.getId()))
                .andExpect(status().isNoContent());

        // Step 3: Check that Product is Deleted from DB
        Assertions.assertFalse(productRepository.findById(product.getId()).isPresent());

        // Step 4: Check Cache Eviction
        Cache cache = cacheManager.getCache(ProductService.PRODUCT_CACHE);
        assertNotNull(cache);
        Assertions.assertNull(cache.get(product.getId()));
    }
}
```