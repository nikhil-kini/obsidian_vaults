## Encrypting, encoding and hashing

**Encoding**
there's no guarantee of secrecy, even if the encoded text may at first appear undecipherable; knowing the encoding mechanism means that you will be able to decode the text.

```sh

# To encode
base64 greeting.txt
echo hello world | base64

# To Decode
echo aGVsbG8gd2hpdGUgaGF0Cg== | base64 -d
```

**Encrypting**
Encryption/decryption refers to transforming data from one form to another in order to keep the original data secret.
too used `openssl`

```sh
openssl enc -aes-256-cbc -in to_be_encryted.txt -out encrypted_file.txt
```

**Public vs private keys**

AES is an example of private key encryption, also called "symmetric" encryption; the same key is used to encrypt and to decrypt data, and the key must be kept private to ensure secrecry.

**RSA keys**
Public key encryption, or "asymmetric" encryption, uses a pair of keys; a known public key is used for encryption, while only a secret private key can be used for decryption.

**Hashing**
 is the process of transforming data into a fixed-length output using a specialized algorithm.
hashing is a "one-way" process, meaning that you can't take a resulting hash and transform it back into the original data.
```sh
echo 'hello' | md5sum
```

 
