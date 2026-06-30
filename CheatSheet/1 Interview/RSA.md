## RSA Tutorial (Rivest–Shamir–Adleman)

RSA is an asymmetric cryptography algorithm used for:

- Encryption
- Digital signatures
- Authentication
- Secure key exchange

The key idea:

> RSA uses two different keys: one public and one private.

```
                Public Key
                  |
Sender ---------->|
                  |
               RSA
                  |
                  v
              Encrypted Data

                  |
                  |
Receiver uses

              Private Key
                  |
                  v
             Decrypted Data
```

## 2. RSA Key Pair

RSA generates:

### Public key

Example:
```
(e, n)
```
Used for:

- Encrypting
- Verifying signatures

## Private key

Example:
```
(d, n)
```
Used for:

- Decrypting
- Creating signatures

## Real-world RSA flow (HTTPS)

Example:

You open:
```
https://bank.com
```
Flow:
```
Client
  |
  |
  | Request certificate
  |
  v

Server

Contains:

Public RSA Key

  |
  |
Client encrypts secret

  |
  |
  v

Server decrypts using

Private Key
```

## RSA Digital Signature

RSA is also used backwards.

Normal:
```
Public key encrypt
Private key decrypt
```
Signature:
```
Private key sign
Public key verify
```
Flow:
```
Message

   |
   v

Hash(message)

   |
   v

Private Key

   |
   v

Signature
```
Receiver:
```
Message
   |
 Hash
   |
Compare
   |
Public key verification
```

## Java RSA Example

Generate keys:
```
import java.security.*;

public class RSAExample {

    public static void main(String[] args)
            throws Exception {


        KeyPairGenerator generator =
            KeyPairGenerator.getInstance("RSA");


        generator.initialize(2048);


        KeyPair pair =
            generator.generateKeyPair();


        PublicKey publicKey =
            pair.getPublic();


        PrivateKey privateKey =
            pair.getPrivate();


        System.out.println(publicKey);
        System.out.println(privateKey);
    }
}
```

Output:
```
Public Key:
RSA 2048

Private Key:
RSA 2048
```

## 9. RSA Encryption Java

```java
Cipher cipher =
    Cipher.getInstance("RSA");


cipher.init(
    Cipher.ENCRYPT_MODE,
    publicKey
);


byte[] encrypted =
    cipher.doFinal(
        "hello".getBytes()
    );
```

Decrypt:

```java
cipher.init(
    Cipher.DECRYPT_MODE,
    privateKey
);


byte[] decrypted =
    cipher.doFinal(encrypted);


System.out.println(
    new String(decrypted)
);
```

Output:

```
hello
```
