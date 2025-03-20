HTTPS via the TLS (Transport Layer Security) protocol.

TLS can be implemented either **one-way** or **two-way**.
- In **one-way** TLS, only the client verifies the server to ensure that it receives data from the trusted server. For implementing one-way TLS, the server shares its public certificate with the clients.
- In **two-way** TLS or **Mutual TLS (mTLS)**, both the client and server authenticate each other to ensure that both parties involved in the communication are trusted. For implementing mTLS, both parties share their public certificates with each other.


##  create a public/private key pair

```sh

keytool -genkeypair -alias baeldung -keyalg RSA -keysize 4096 \
  -validity 3650 -dname "CN=localhost" -keypass changeit -keystore keystore.p12 \
  -storeType PKCS12 -storepass changeit
```

The two most popular formats are Java KeyStore (JKS) and PKCS#12. JKS is specific to Java, while PKCS#12 is an industry-standard format belonging to the family of standards defined under **Public Key Cryptography Standards** (PKCS).

### Configuring TLS in Spring
- `application.properties` , certificates are stored in `src/main/resources/keystore`
```properties
# enable/disable https
server.ssl.enabled=true
# keystore format
server.ssl.key-store-type=PKCS12
# keystore location
server.ssl.key-store=classpath:keystore/keystore.p12
# keystore password
server.ssl.key-store-password=changeit

# SSL protocol to use
server.ssl.protocol=TLS
# Enabled SSL protocols
server.ssl.enabled-protocols=TLSv1.2
```


### Configuring mTLS in Spring
- client authentication is needed and mandatory
```properties
server.ssl.client-auth=need

#trust store location
server.ssl.trust-store=classpath:keystore/truststore.p12
#trust store password
server.ssl.trust-store-password=changeit
```

The path for the location to the _truststore_ is the file that contains the list of certificate authorities that are trusted by the machine for SSL server authentication. The _truststore_ password is the password to gain access to the _truststore_ file.

