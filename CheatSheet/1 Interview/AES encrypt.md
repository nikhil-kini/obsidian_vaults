## 1. What is AES?

AES (Advanced Encryption Standard) is a symmetric encryption algorithm.

**Symmetric means:**

>The same secret key is used to encrypt and decrypt data.

Example:
```
Plain Text
    |
    |  AES Encrypt + Secret Key
    |
    v
Encrypted Data (Cipher Text)
    |
    |  AES Decrypt + Same Secret Key
    |
    v
Plain Text
```

## 2. What is a Key?

The key is the secret value that controls encryption.

| AES Type | Key Size            |
| -------- | ------------------- |
| AES-128  | 128 bits (16 bytes) |
| AES-192  | 192 bits (24 bytes) |
| AES-256  | 256 bits (32 bytes) |

AES-256 key:
```
32 bytes:

7f8a91bc32d0....
```
The longer the key, the larger the search space.

## 3. What is an IV?

IV = **Initialization Vector**

An IV is a **random value used to make the same plaintext produce different ciphertext every time.**

Example without IV:
```
Encrypt:

"password123"

Result:

ABCDEF123
```
Every time:
```
"password123"
        |
        |
        v
ABCDEF123
```
Problem:
Attackers can identify repeated values.


With IV:
```
Message:
password123


Encryption #1

Key:
MY_KEY

IV:
11111111

Output:
8F91ABCD


Encryption #2

Key:
MY_KEY

IV:
99999999

Output:
72FF1234
```
Same message, different encrypted output.

## 4. Key vs IV Difference

|                         | Key                       | IV                    |
| ----------------------- | ------------------------- | --------------------- |
| Purpose                 | Secret encryption control | Randomizes encryption |
| Secret?                 | YES                       | Usually NO            |
| Same for every message? | Usually yes               | NO                    |
| Stored with ciphertext? | Never                     | Yes                   |
| Required for decrypt?   | Yes                       | Yes                   |

A common pattern:

Encrypted Data:
```
+------------+----------------+
| IV         | Cipher Text    |
+------------+----------------+
```

Example:
```
Base64(
    IV + encrypted_data
)
```

## 5. AES Modes

AES itself only encrypts blocks. A mode defines how.

Common modes:

### AES-ECB (avoid)
AES/ECB/PKCS5Padding

Problem:
```
Same input → same output.
```
Example:
```
User1 password:
ABC

Encrypted:
XYZ


User2 password:
ABC

Encrypted:
XYZ
```
Leaks patterns.

### AES-CBC
`AES/CBC/PKCS5Padding`

Uses IV.

Flow:
```
Plain Text
     |
     |
    IV
     |
     v
 AES-CBC
     |
     v
Cipher Text
```
Better, but needs authentication separately.

## AES-GCM (Recommended)
`AES/GCM/NoPadding`

Modern standard.

Provides:

- Encryption
- Integrity check

Meaning:

If someone modifies ciphertext:
```
ABC123
```
to:
```
ABC999
```
decryption fails.

## 6. Java AES Example (AES-GCM)

Encrypt:
```
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;


public class AESExample {


    private static final byte[] KEY =
            "12345678901234567890123456789012"
            .getBytes();


    public static String encrypt(String text) throws Exception {


        byte[] iv = new byte[12];

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);


        Cipher cipher =
            Cipher.getInstance("AES/GCM/NoPadding");


        SecretKeySpec key =
            new SecretKeySpec(KEY, "AES");


        GCMParameterSpec spec =
            new GCMParameterSpec(128, iv);


        cipher.init(
            Cipher.ENCRYPT_MODE,
            key,
            spec
        );


        byte[] encrypted =
            cipher.doFinal(
                text.getBytes()
            );


        byte[] result =
            new byte[iv.length + encrypted.length];


        System.arraycopy(
            iv,0,result,0,iv.length
        );


        System.arraycopy(
            encrypted,
            0,
            result,
            iv.length,
            encrypted.length
        );


        return Base64.getUrlEncoder()
                .encodeToString(result);
    }


public static String decrypt(String encrypted) throws Exception {

        byte[] encryptedBytes = Base64.getUrlDecoder().decode(encrypted);


        // first 12 bytes = IV
        byte[] iv = new byte[12];
        System.arraycopy(
                encryptedBytes,
                0,
                iv,
                0,
                12
        );


        // remaining bytes = encrypted data
        byte[] cipherText = new byte[
                encryptedBytes.length - 12
                ];

        System.arraycopy(
                encryptedBytes,
                12,
                cipherText,
                0,
                cipherText.length
        );


        SecretKeySpec key =
                new SecretKeySpec(
                        KEY,
                        "AES"
                );


        Cipher cipher =
                Cipher.getInstance(
                        "AES/GCM/NoPadding"
                );


        GCMParameterSpec spec =
                new GCMParameterSpec(
                        128,
                        iv
                );


        cipher.init(
                Cipher.DECRYPT_MODE,
                key,
                spec
        );


        byte[] decrypted =
                cipher.doFinal(cipherText);


        return new String(
                decrypted,
                StandardCharsets.UTF_8
        );
    }
}
```
Output:
```
gH23jd83jdks92jdks....
```

## 7. Decryption Flow

You receive:
```
Base64 String
        |
        |
decode
        |
        v

IV + Ciphertext

        |
        |
AES-GCM + Key

        |
        v

Original text
```

## 9. Important Security Rules

Do:

✅ Use AES-GCM
✅ Generate random IV every encryption
✅ Store IV with ciphertext
✅ Store key in secrets manager/environment variable

Don't:

❌ Hardcode keys in source code
```
String key="password123";
```
❌ Reuse IV

❌ Use AES-ECB
