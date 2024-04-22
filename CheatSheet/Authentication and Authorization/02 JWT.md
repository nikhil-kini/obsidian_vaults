[refer](https://auth0.com/docs/secure/tokens/json-web-tokens/json-web-token-structure)

There are three main uses for JWT:
- **Authentication**
- **Authorization**
- **Information exchange**: With the ability to be signed, JWTs are a secure way to share information between two places. Plus, the way JWTs are structured (more on that later) means it’s easy to spot if the data has been tampered with along the way.

## Structure of JWTs

A well-formed JWT consists of three concatenated `Base64url`-encoded strings, separated by dots (`.`):

- **JOSE Header**: contains metadata about the type of token and the cryptographic algorithms used to secure its contents.
	
- **JWS payload** (set of [claims](https://tools.ietf.org/html/rfc7519#section-4)): contains verifiable security statements, such as the identity of the user and the permissions they are allowed.
	
- **JWS signature**: used to validate that the token is trustworthy and has not been tampered with. When you use a JWT, you **must** [check its signature](https://auth0.com/docs/secure/tokens/json-web-tokens/validate-json-web-tokens) before storing and using it.

## Claims

There are two types of JWT claims:

- **Registered**: standard claims registered with the [Internet Assigned Numbers Authority (IANA)](https://www.iana.org/assignments/jwt/jwt.xhtml) and defined by the [JWT specification](https://tools.ietf.org/html/rfc7519) to ensure interoperability with third-party, or external, applications. OIDC standard claims are reserved claims.
    
- **Custom**:  consists of non-registered public or private claims. Public claims are collision-resistant while private claims are subject to possible collisions.

## Registered claims

The JWT specification defines seven reserved claims that are not required, but are recommended to allow interoperability with [third-party applications](https://auth0.com/docs/get-started/applications/confidential-and-public-applications/enable-third-party-applications). These are:

- `iss` (issuer): Issuer of the JWT
    
- `sub` (subject): Subject of the JWT (the user)
    
- `aud` (audience): Recipient for which the JWT is intended
    
- `exp` (expiration time): Time after which the JWT expires
    
- `nbf` (not before time): Time before which the JWT must not be accepted for processing
    
- `iat` (issued at time): Time at which the JWT was issued; can be used to determine age of the JWT
    
- `jti` (JWT ID): Unique identifier; can be used to prevent the JWT from being replayed (allows a token to be used only once)

You can see a full list of registered claims at the [IANA JSON Web Token Claims Registry](https://www.iana.org/assignments/jwt/jwt.xhtml#claims).


## Custom claims

You can define your own custom claims which you control and you can add them to a token using Actions. Here are some examples:

- Add a user's email address to an access token and use that to uniquely identify the user.
    
- Add custom information stored in an Auth0 user profile to an ID token.

As long as the Action is in place, the custom claims it adds will appear in new tokens issued when using a [refresh token](https://auth0.com/docs/secure/tokens/refresh-tokens).

For an example showing how to add custom claims to a token, see [Sample Use Cases: Scopes and Claims](https://auth0.com/docs/get-started/apis/scopes/sample-use-cases-scopes-and-claims).

### Public claims

You can create custom claims for public consumption, which might contain generic information like name and email. If you create public claims, you must either register them or use collision-resistant names through name-spacing and take reasonable precautions to make sure you are in control of the namespace you use.

In the [IANA JSON Web Token Claims Registry](https://www.iana.org/assignments/jwt/jwt.xhtml#claims), you can see some examples of public claims registered by OpenID Connect (OIDC):
- `auth_time`
- `acr`
- `nonce`

### Private claims

You can create private custom claims to share information specific to your application. For example, while a public claim might contain generic information like name and email, private claims would be more specific, such as employee ID and department name.

# JSON Web Key Sets

The JSON Web Key Set (JWKS) is a set of keys containing the public keys used to verify any JSON Web Token (JWT) issued by the Authorization Server and signed using the [RS256]() [signing algorithm](https://auth0.com/docs/get-started/applications/signing-algorithms).

When creating applications and APIs in Auth0, two algorithms are supported for signing JWTs: **RS256** and **HS256**. RS256 generates an asymmetric signature, which means a private key must be used to sign the JWT and a different public key must be used to verify the signature.

Auth0 uses the [JSON Web Key (JWK) specification](https://tools.ietf.org/html/rfc7517) to represent the cryptographic keys used for signing RS256 tokens. This specification defines two high-level data structures: **JSON Web Key (JWK)** and **JSON Web Key Set (JWKS)**. Here are the definitions from the specification:

|Item|Description|
|---|---|
|**JSON Web Key (JWK)**|A JSON object that represents a cryptographic key. The members of the object represent properties of the key, including its value.|
|**JSON Web Key Set (JWKS)**|A JSON object that represents a set of JWKs. The JSON object MUST have a `keys` member, which is an array of JWKs.|

Auth0 exposes a JWKS endpoint for each tenant, which is found at `https://{yourDomain}/.well-known/jwks.json`. This endpoint will contain the JWK used to verify all Auth0-issued JWTs for this tenant.