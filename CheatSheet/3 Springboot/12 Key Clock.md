
## What is Keycloak?

Keycloak is an open-source Authorization Server that can be used to outsource the authentication and authorization from our application. Keycloak supports various authentication and authorization protocols like OAuth2, OpenID Connect, SAML, etc. It also offers features like Single Sign On (SSO), and Multi-Factor Authentication (MFA) out of the box.

## Download Keycloak

- `compose.yml`
```yml
services:
  keycloak-mysql:
    container_name: keycloak-mysql
    image: mysql:8.3.0
    volumes:
      - ./volume-data/mysql_keycloak_data:/var/lib/mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: keycloak
      MYSQL_USER: keycloak
      MYSQL_PASSWORD: password
  keycloak:
    container_name: keycloak
    image: quay.io/keycloak/keycloak:24.0.1
    command: [ "start-dev", "--import-realm" ]
    environment:
      DB_VENDOR: MYSQL
      DB_ADDR: mysql
      DB_DATABASE: keycloak
      DB_USER: keycloak
      DB_PASSWORD: password
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin
    ports:
      - "8181:8080"
    volumes:
      - ./docker/keycloak/realms/:/opt/keycloak/data/import/
    depends_on:
      - keycloak-mysql
```

```sh
docker compose up -d
```

## Keycloak Configuration

open the URL [http://localhost:8181](http://localhost:8181) this should open the home page of keycloak, provide admin/admin as the credentials as we have configured it in the docker-compose file.

In Keycloak, all the clients, users, and roles related to a particular application (or) a group of applications reside inside something known as a **realm**. **Realms are independent of each other**, so if you create one client/user in one realm, you cannot use it from another realm.

In our project, we will be using the Client Credentials grant, to communicate with the API Gateway and fetch an Access Token, and this access token will be verified by the API Gateway against Keycloak.

To get started, log in to the Keycloak Admin page using the above-mentioned credentials and the first thing we are going to do is to create the realm.

- After logging in, click on the dropdown with the text "Keycloak" in the top left-side corner and click on the Create Realm button

- Provide the name of the realm(**eg**: spring-microservices-realm) and click on the **Create** button
    
- Now the realm should be created successfully
    
- Next, click on the **Clients** link to the left sidebar, and click on **Create Client**
    
- Provide any Client ID you like eg: test-client-id and click on **Next**
    
- Check Client Authentication as ON
    
- In the Authentication Flow, select **Service accounts roles** and unselect all other options, this makes sure that our client supports Client Credentials grant
    
- Click on **Next** and then **Save**
    
- Finally, click on the Credentials tab, here you can view the client secret that is generated automatically and you can also regenerate a new client secret. Make sure to copy the client secret, we will use this in the later parts to request a token to access the API Gateway


## Configure Keycloak in API Gateway

Now let's configure keycloak in our api-gateway application, for that we need to add the **spring-security-oauth2-resource-server** dependency to our pom.xml file
```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

Now let's configure the application.properties with the Authorization Server details. For that first, we need to retrieve the Issuer URI of our authorization server. For keycloak, it's usually in the format:  
`http://<key-cloak-url>/realm/<realm-name>`

For the realm we created in the previous step, the issuer uri will be: [http://localhost:8181/realms/spring-microservices-realm](http://localhost:8181/realms/spring-microservices-realm)

Now let's go ahead and configure this inside our Spring Boot API Gateway application.

```
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8181/realms/spring-microservices-realm
```

The next step is to create the Security Configuration class, let's create a package called config and create a class inside the package - SecurityConfig.class

```java
package com.programming.techie.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
}

```

This is the basic Security Configuration, Spring Security already creates for us out of the box, you can also choose to ignore adding this file if you don't need to add any additional configuration.

Now let's run the ApiGatewayApplication.java class and test out our endpoints using postman.
- Go to Realm Settings -> OpenID Endpoint Configuration -> Copy Token Endpoint URL

- Open the Postman client and inside the request window, click on the Authorization tab and then select OAuth2.
    
- Select the Grant Type as Client Credentials
    
- Enter the Client ID and Client Secret of the client we created in the previous steps
- Token Endpoint URL from before

    
- Leave the rest of the fields as it is, and click on the Get New Access Token button.
    
- This will make a call to the Token Endpoint and fetches us a new Access Token.
    
- Click on the Use Token method, to add the token to our Request Context Window.
    
- Select any request you want to make for example: call the Product Service Endpoint - GET [HTTP://localhost:9000/api/product](HTTP://localhost:9000/api/product) and click on Send