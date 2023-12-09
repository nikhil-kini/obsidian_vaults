YAML Ain't Markup Language.
Example:
```yml
---
 doe: "a deer, a female deer"
 ray: "a drop of golden sun"
 pi: 3.14159
 xmas: true
 french-hens: 3
 calling-birds:
   - huey
   - dewey
   - louie
   - fred
 xmas-fifth-day:
   calling-birds: four
   french-hens: 3
   golden-rings: 5
   partridges:
     count: 1
     location: "a pear tree"
   turtle-doves: two
```
vs JSON
```json
{
  "doe": "a deer, a female deer",
  "ray": "a drop of golden sun",
  "pi": 3.14159,
  "xmas": true,
  "french-hens": 3,
  "calling-birds": [
     "huey",
     "dewey",
     "louie",
     "fred"
  ],
  "xmas-fifth-day": {
  "calling-birds": "four",
  "french-hens": 3,
  "golden-rings": 5,
  "partridges": {
    "count": 1,
    "location": "a pear tree"
  },
  "turtle-doves": "two"
  }
}
```

## Outline Indentation and Whitespace

* Whitespace is part of YAML's formatting. Unless otherwise indicated, **newlines** indicate the **end of a field**. 
* You structure a YAML document with indentation. The indentation level can be one or more spaces. The **specification forbids tabs because tools treat them differently**. Consider this document. The items inside are indented with two spaces.

## Comments

```yml
___
# This is a full line comment
foo: bar # this is a comment, too
```

## YAML Data Types

Values in YAML's key-value pairs are scalar.
It's usually good enough to enclose strings in quotes, leave numbers unquoted, and let the parser figure it out.

### Key-Value Pairs and Dictionaries
* The key-value is YAML's basic building block. Every item in a YAML document is a member of at least one dictionary. 
* The key is always a string. 
* The value is a scalar so that it can be any datatype.

### Numeric types
* An integer can be decimal, hexadecimal, or octal.
* As you expect, **Ox** indicates a value is **hex**, and a **leading zero** denotes an **octal** value.
```yml
---
 foo: 12345
 bar: 0x12d4
 plop: 023332

 foo: 1230.15
 bar:  12.3015e+05
```
we can represent not-a-number (NAN) or infinity.
```yml
---
foo: .inf
bar: -.Inf
plop: .NAN
```

### Strings
YAML strings are Unicode.

```yml
---
foo: this is a normal string
```
But if we want escape sequences handled
```yml
---
foo: "this is not a normal string\n"
bar: this is not a normal string\n
```
* YAML will not escape strings with single quotes, but the single quotes do avoid having string contents interpreted as document formatting.
* String values can span more than one line. With the fold (greater than) character, you can specify a string in a block.
```yml
# it's interpreted without the newlines.
bar: >
  this is not a normal string it
  spans more than
  one line
  see?
```
The block (pipe) character has a similar function, but YAML interprets the field **exactly as is.**
```yml
# it's interpreted with the newlines.
bar: |
  this is not a normal string it
  spans more than
  one line
  see?
```

### Nulls
enter nulls with a tilde(~) or the unquoted null string literal.
```yml
---
foo: ~
bar: null
```

### Booleans
```yml
---
foo: True
bar: False
light: On
TV: Off
```

### Arrays
specify arrays or lists on a single line.
```yml
---
items: [ 1, 2, 3, 4, 5 ]
names: [ "one", "two", "three", "four" ]
```

can put them on multiple lines.
```yml
---
items:
  - 1
  - 2
  - 3
  - 4
  - 5
names:
  - "one"
  - "two"
  - "three"
  - "four"
```

The multiple line format is useful for lists that contain complex objects instead of scalars.
```yml
___ # [things, other things]
items:
  - things:
      thing1: huey
      things2: dewey
      thing3: louie
  - other things:
      key: value
```
An array can contain any valid YAML value. The values in a list do not have to be the same type.

### Dictionaries
you can put dictionaries inline.
```yml
---
foo: { thing1: huey, thing2: louie, thing3: dewey }
```
span lines
```yml
---
foo: bar
bar: foo
```
can be nested
```yml
---
foo:
  bar:
    - bar
    - rab
    - plop
```

## Advanced

### Chomp Modifiers

Multiline values may end with whitespace, and depending on how you want the document to be processed you might not want to preserve it. YAML has the **strip** chomp and **preserve** chomp operators. To save the last character, add a plus to the fold or block operators.

```yml
bar: >+
  this is not a normal string it
  spans more than
  one line
  see?
```
So, if the value ends with whitespace, like a newline, YAML will preserve it. To strip the character, use the strip operator.
```yml

bar: |-
  this is not a normal string it
  spans more than
  one line
  see?
```


### Multiple documents
* A document **starts with three dashes and ends with three periods**. 
* Some YAML processors require the document start operator. 
* The end operator is usually optional. 
* For example, Java's Jackson will not process a YAML document without the start, but Python's PyYAML will. You'll usually use the end document operator when a file contains multiple documents.

Doc 1
```yml
---
bar: foo
foo: bar
...
```
Doc 2
```yml
---
one: two
three: four
```

On loading both files combined
```
New document:
bar : foo
foo : bar
New document:
one : two
three : four
```