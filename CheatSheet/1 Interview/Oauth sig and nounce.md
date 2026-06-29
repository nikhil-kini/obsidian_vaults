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

|               | OAuth 1.0 | OAuth 2.0  |
| ------------- | --------- | ---------- |
| Signature     | Yes       | Usually no |
| Uses HMAC/RSA | Yes       | No         |
| Complexity    | High      | Lower      |
| Uses HTTPS    | Optional  | Required   |
| Modern APIs   | Rare      | Common     |


| Feature | HMAC-SHA1 | HMAC-SHA256 |
|---|---|---|
| Hash Function Family | SHA-1 | SHA-2 (SHA-256) |
| Output Length | 160 bits (20 bytes) | 256 bits (32 bytes) |
| Security Level | Deprecated. Vulnerable to collision attacks. | High. No known practical collision attacks. |
| Performance | Faster, requires less processing power. | Slightly more resource-intensive. |
| Best Used For | Legacy systems. | Modern secure web apps, APIs, and blockchains. |


# What is a Nonce?

Nonce means "**Number used once**".

It is a unique, random value added to a request/message to make sure that the same request cannot be reused (replayed) by an attacker.

Main purpose:

> Prevent replay attacks.

## Problem without a nonce

Imagine an API request:
```http
POST /transfer

amount=1000
to=bankAccount123
```
An attacker captures this request:
```
User ---> Server

Transfer ₹1000
```
Later attacker sends the same request again:
```
Attacker ---> Server

Transfer ₹1000
```
Server sees a valid request and processes it again.

Result:

₹1000 transferred twice

This is a replay attack.

## With nonce

Client adds:
```http
POST /transfer

amount=1000
to=bankAccount123
nonce=839201
```
Server stores:
```
Used nonces:
839201
```
Attacker repeats:
```http
POST /transfer

amount=1000
to=bankAccount123
nonce=839201
```
Server checks:
```
Is nonce 839201 already used?

YES
|
Reject request
```

## Nonce in OAuth 1.0

OAuth signature uses nonce:

Example:
```
Authorization:

oauth_consumer_key="abc123"
oauth_nonce="784928374"
oauth_timestamp="1710000000"
oauth_signature="xyz..."
```
Flow:
```
Client
 |
 | Generates nonce
 |
 | Signs request
 |
 v
OAuth Server
 |
 | Checks:
 | 1. Signature valid?
 | 2. Timestamp valid?
 | 3. Nonce already used?
 |
 v
Accept
```

## How nonce works with signature

Request:
```
GET /users?id=10
```

Client creates:
```
nonce = 12345
timestamp = 1710000000
```

Signature input:
```
GET&
https://api.com/users&
id=10&
nonce=12345&
timestamp=1710000000
```

HMAC:
```
HMAC(secret, data)
```

Output:
```
signature=abcxyz
```
Request:
```
GET /users?id=10

nonce=12345
timestamp=1710000000
signature=abcxyz
```

## Server validation

Server receives:
```
nonce=12345
timestamp=1710000000
signature=abcxyz
```

## Checks:

### 1. Timestamp

Is request old?

Example:
```
Current time:
1710000100

Request:
1710000000

Difference:

100 seconds
```
Allowed → continue.

### 2. Nonce

Database/cache (redis cache):
```
oauth_used_nonce

12345
```
Check:
```
SELECT *
FROM oauth_nonce
WHERE nonce='12345';

redis-cli
KEY nounce:nounce_id
```
Found:
```
Reject
```
Not found:
```
Store nonce
Continue
```

## 3. Signature

Generate again:
```
HMAC(secret, request)
```
Compare:
```
Client:
abcxyz

Server:
abcxyz

MATCH
```
Accept.

## Nonce example in Java

Generate random nonce:
```
import java.util.UUID;

public class NonceGenerator {

    public static String generate() {

        return UUID.randomUUID()
                   .toString()
                   .replace("-", "");
    }

    public static void main(String[] args) {

        System.out.println(generate());

    }
}
```
Output:
```
a8f92bc7d81e4f0aa9d3
```

## Nonce vs Token vs Timestamp

| Feature  | Nonce               | Token                    | Timestamp           |
| -------- | ------------------- | ------------------------ | ------------------- |
| Purpose  | Prevent replay      | Authenticate user/client | Check request age   |
| Lifetime | Usually one request | Minutes/hours            | Few seconds/minutes |
| Stored?  | Usually yes         | Usually yes              | No                  |
| Example  | `837462`            | JWT                      | `1710000000`        |

