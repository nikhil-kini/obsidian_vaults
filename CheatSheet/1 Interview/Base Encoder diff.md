| Feature             | Base16 (Hex)                     | Base32                       | Base64                                   |
| ------------------- | -------------------------------- | ---------------------------- | ---------------------------------------- |
| Character Set       | `0-9` + `A-F` (16 chars)         | `A-Z` + `2-7` (32 chars)     | `A-Z` + `a-z` + `0-9` + `+` `/`          |
| Bits per Character  | 4 bits                           | 5 bits                       | 6 bits                                   |
| Encoding Efficiency | Lowest                           | Medium                       | Highest                                  |
| Size Increase       | ~2x original size                | ~1.6x original size          | ~1.33x original size                     |
| Example Input       | `Hello`                          | `Hello`                      | `Hello`                                  |
| Encoded Output      | `48656C6C6F`                     | `JBCUYTCP`                   | `SGVsbG8=`                               |
| URL Friendly        | Yes                              | Yes                          | Not always (`+`, `/`, `=` need handling) |
| Human Readability   | Very high                        | Medium                       | Low                                      |
| Common Usage        | Hashes, debugging, MAC addresses | OTP secrets, DNS, file names, url | APIs, JWT, images, encryption payloads   |

