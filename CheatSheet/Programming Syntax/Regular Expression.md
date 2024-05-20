[Reference](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions/Cheatsheet)
[Test Environment](https://regex101.com/)`
_everything is essentially a character_, and we are writing patterns to match a specific sequence of characters.

**_best practice to write as specific regular expressions as possible_** to ensure that we don't get false positives when matching against real world text.

- _it may be **as simple as the common letters on each line_**.
```
abcdef
ab
```

```regex
ab              ///is a vaild regex
```

- **Character Classes**
```
abc123xyz
define "123"
```
`
```regex
\w\d
```
Note: Uppercase letter '\W' is contains no word where as '\w' is contains word

- **Wild card character**
```
cat.
896
?=+.
abc1
```

```regex
\.
```
Note: you need to escape the dot by using a slash `\.` to override all pattern matching of `.` i.e `.` - all character, `\.` - contains dot

- **Matching specific characters (single)**
```
can
man
fan
dan -- skip 
ran -- skip
pan -- skip
```

```regex
[c|m|f]an
```
Matches any one of the enclosed characters.

- **Excluding specific characters**
```
hog
dog
bog --skip
```

```regex
[^b]og
```
Anything but `b`, **use only inside brackets**

- **Character ranges**
```
Ana
Bob
Cpc
aax --skip
bby --skip
ccz --skip
```

```regex
[A-C]\w\w
```

- **Matching specific characters (multiple number char)**
```
wazzzzzup
wazzzup
wazup --skip
```

```regex
waz{2,5}up
```
Note: `z{3}` - exactly 3 times, `z{2,5}` - at-least  2 at-most 5 

- **To match an arbitrary number of characters**
```
aaaabcc
aabbbbc
aacc
a --skip
```

```regex
a+b*c+
```
the **_Kleene Star_ \*** and the **_Kleene Plus_ +**, which essentially represents **either _0 or more_** or **_1 or more_** of the character that it follows

- **Characters optional**
```
1 file found?
2 files found?
24 files found?
No files found. --skip
```

```regex
\?
```
the **_?_** (question mark) metacharacter which denotes **_optionality_**. `?` - optionality, `\?` - char

- **All White Spaces**
```
1. abc
2.    abc
3.      abc
4. abc
```

```regex
\s+abc
```
The most common forms of whitespace you will use with regular expressions are the **_space_** (_**␣**_), the **_tab_** (_**\t**_), the **_new line_** (_**\n**_) and the **carriage return** (_**\r**_) (useful in Windows environments), and these special characters match each of their respective whitespaces. In addition, a _whitespace_ special character **_\s_** will match **_any_** of the specific whitespaces above.

- **Starting and ending**
```
Mission: successful
Last Mission: unsuccessful
Next Mission: successful upon capture of target
```

```regex
^Mission.+successful$
```
_start and the end **of the line_** using the special **_^_ (_hat_)** and **_$_ (_dollar sign_)** metacharacters

- **Match groups (Capture data from file)**
```
|capture|file_record_transcript.pdf|       file_record_transcript
|capture|file_07241999.pdf|                file_07241999
|skip|testfile_fake.pdf.tmp|
```

```regex
^(file.+)\.pdf$  -- filename without extension

^(file.+\.pdf)$ -- filename with extension
```
Regular expressions allow us to not just match text but also to **_extract information for further processing_**. This is done by defining **_groups of characters_** and capturing them using the special parentheses **_(_** and **_)_** metacharacters. Any sub-pattern inside a pair of parentheses will be **_captured_** as a group.

- **Nested Groups (capture sub-group)**
```
Jan 1987     | Jan 1987 | 1987
May 1969     | May 1969 | 1969
Aug 2011     | Aug 2011 | 2011
```

```regex
(\w+\s(\d+))
```

- **Combine with other expression**
```
1280x720     | 1280 | 720
1920x1600    | 1920 | 1600
1024x768     | 1024 | 768
```

```regex
(\d{4})*x(\d{3,4})
```

- **Logical OR**

```
I love cats
I love dogs
I love logs --skip
I love cogs --skip
```

```regex
I love ([cb]ats*|[dh]ogs?)
```