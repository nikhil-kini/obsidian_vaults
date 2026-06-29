## Oath Signature

OAuth signature is a way to ==prove that an API request was created by a trusted client and was not modified while travelling over the network.==

It is mainly used in OAuth 1.0a. Modern OAuth 2.0 usually does not use request signatures; it uses access **tokens + HTTPS**.

## 1. The problem OAuth signature solves

Imagine:
```
Client  ------------->  API Server
        "Give me user data"
```
**How does API know:**

Who sent this request?
Was the request changed?
Was someone replaying an old request?

A password in every request is unsafe.

OAuth signature adds a cryptographic proof.

## 2. OAuth 1.0 flow

Actors:
```
+--------+
| Client |
+--------+
    |
    |
    v
+-------------+
| OAuth Server|
+-------------+
    |
    |
    v
+------------+
| API Server |
+------------+
```
Example:

Client: Mobile app
API: Twitter API
User: Account owner

## 3. Keys involved

Client gets:

### Consumer Key

Public identifier:
```
consumer_key = abc123
```

### Consumer Secret

Private:
```
consumer_secret = xyz789
```
**Never expose secret.**

## 4. Client creates request

Example:
```
GET /users?id=10
```

Add OAuth headers:
```
Authorization:

oauth_consumer_key="abc123"
oauth_nonce="123456"
oauth_timestamp="1710000000"
oauth_signature_method="HMAC-SHA256"
oauth_signature="????"
```
The important part:
```
oauth_signature
```

## Signature creation process

### Step 1: Create parameter string

Collect:
```
consumer_key
nonce
timestamp
request params
```
Sort alphabetically.

Example:
```
id=10
oauth_consumer_key=abc123
oauth_nonce=123456
oauth_timestamp=1710000000
```
Create:
```
id%3D10%26oauth_consumer_key%3Dabc123...
```

## Step 2: Create signature base string

Format:
```
HTTP_METHOD & URL & PARAMETERS
```
Example:
```
GET&
https%3A%2F%2Fapi.com%2Fusers&
id%3D10%26oauth_consumer_key%3Dabc123
```

## Step 3: Create signing key

Using secrets:
```
consumer_secret&token_secret
```
Example:
```
xyz789&
```

## Step 4: Generate HMAC

Algorithm:
```
HMAC-SHA256
```
Input:
```
signature base string
```
Key:
```
consumer_secret
```
Output:
```
a8f7d92jd82jd92
```
That becomes:
```
oauth_signature=a8f7d92jd82jd92
```

## 5. Server verification

Server receives:
```
GET /users?id=10

oauth_signature=a8f7d92jd82jd92
```
Server:

- Gets client secret
- Recreates signature
- Compares

Example:
```
Client signature:
a8f7d92jd82jd92

Server generated:
a8f7d92jd82jd92

MATCH
```
Request accepted.

**Java example**

Generate HMAC signature:
```
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;


public class OAuthSignature {

    public static String generate(
            String data,
            String secret
    ) throws Exception {

        Mac mac =
            Mac.getInstance("HmacSHA256");

        SecretKeySpec key =
            new SecretKeySpec(
                secret.getBytes(),
                "HmacSHA256"
            );

        mac.init(key);

        byte[] result =
            mac.doFinal(
                data.getBytes()
            );

        return Base64
                .getEncoder()
                .encodeToString(result);
    }


    public static void main(String[] args)
            throws Exception {

        String signature =
            generate(
                "GET&https://api.com/users",
                "xyz789"
            );

        System.out.println(signature);
    }
}
```
**Output:**
```
p7F82kdj29dkd==
```
