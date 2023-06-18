[[Python Data Structures]]
[[Python methods and modules]]
[[Python Iterators]]
[[PIP]]

* Python is an interpreted, high-level and general-purpose, dynamically typed programming language
* It is also Object oriented, modular oriented and a scripting language.
* In Python, everything is considered as an Object.
* A python file has an extension of .py
* Python follows Indentation to separate code blocks instead of flower brackets({}).
* Note: `Tab` is not the same as 4 `space`, need to adjust in the text editor config, if not done already.


### Comments
```python
# Single line comment
"""
Multiple
Line
Comment
"""
```


#### Shebang
[See here](https://en.wikipedia.org/wiki/Shebang_(Unix)) for the purpose of the shebang (`#!`) in Unix systems. You may include the shebang at the top of a Python script to execute it without having to type `python` before the code name when running the code from the terminal. After proper configuration of the file manager, shebang also allows for running a python script via double-clicking its file within the file-manager interface.

For latest version of Python 3 scripts:
```py
#!/usr/bin/env python3
```
For latest version of Python 2 scripts:
```py
#!/usr/bin/env python2
```
For both Python 2 and 3 scripts (NOT RECOMMENDED):
```py
#!/usr/bin/env python
```


### Continuation character
`\` is the continuation character. The line following the continuation character is considered a continuation of the current line:
```python
print("hello" + \
  "word")

if x not in range(8) or \
  y not in range(3):
```


### Basic Datatypes

| Data Type | Description |
| --------- | ----------- |
| int | Integer values [0, 1, -2, 3] |
| float | Floating point values [0.1, 4.532, -5.092] |
| char | Characters [a, b, @, !, \`] |
| str | Strings [abc, AbC, A@B, sd!, \`asa] |
| bool | Boolean Values [True, False] |
| complex | Complex numbers [2+3j, 4-1j] |

### Check types
```python
type('spam')    #=> <type 'str'>
type(1) is int  #=> True
```


### Typecasting

**Type conversion functions**

* The `int` function takes any value and converts it to an integer.`int` can convert floating-point values to integers, but it doesn’t round off; it chops off the fraction part.
* `float` converts integers and strings to floating-point numbers
* `str` converts its argument to a string
```python
int('1010', 2)  #=> 2     # cast from binary to decimal int
str(2)          #=> "2"   # cast to string
float(2)        #=> 2.0   # cast int 2 into float
```


### Binary
```python
bin(10)       #=> 0b1010
10 == 0b1010  #=> True
```


### Reserved words in Python

| Keyword | Description  | Category |
|---------- | ---------- | --------- | 
| True      | Boolean value for not False or 1 | Value Keyword|
| False     | Boolean Value for not True or 0 | Value Keyword |
| None      | No Value | Value keyword |
| and       | returns true if both (oprand) are true (other language && ) | Operator keyword |
| or        | returns true of either operands is true (other language || ) | Operator keyword |
| in        | returns true if word is in iterator | Operator keyword |
| is        | returns true if id of variables are same | Operator keyword |
| not       | returns opposite Boolean value | Operator Keyword |
| if | get into block if expression is true | conditional |
| elif | for more than 1 if checks | conditional |
| else | this block will be executed if condition is false | conditional |
| for | used for looping | iteration |
| while | used for looping | iteration |
| break | get out of loop | iteration | 
| continue | skip for specific condition | iteration |
| def | make user defined function | structure |
| class | make user defined classes | structure |
| lambda | make anonymous function | structure |
| with | execute code within context manager's scope | structure |
| as | alias for something | structure |
| pass | used for making empty structures(declaration) | structure |
| return | get value(s) from function, get out of function | returning keyword |
| yield | yields values instead of returning (are called generators) | returning keyword |
| import | import libraries/modules/packages | import |
| from | import specific function/classes from modules/packages | import |
| try | this block will be tried to get executed | exception handling |
| except | is any exception/error has occured it'll be executed | exception handling |
| finally | It'll be executed no matter exception occurs or not | exception handling | 
| raise | throws any specific error/exception | exception handling |
| assert | throws an AssertionError if condition is false | exception handling |
| async | used to define asynchronous functions/co-routines | asynchronous programming |
| await | used to specify a point when control is taken back | asynchronous programming |
| del | deletes/unsets any user defined data |  variable handling |
| global | used to access variables defined outside of function | variable handling |
| nonlocal | modify variables from different scopes | variable handling |


### Operators

| Operator | Description |
|-|-|
|  ( )	|  grouping parenthesis, function call, tuple declaration |
|  [ ]	|  array indexing, also declaring lists etc.|
|  !	|    relational not, complement, ! a  yields true or false |
|  ~   | 	bitwise not, ones complement, ~a |
| \-   |	unary minus, - a |
|  \+   | 	unary plus,  + a |
|  \*   |	multiply, a * b |
| \** |  power of, a \** b|
|  /   	| divide, a / b |
|  %    |	modulo, a % b |
|  \+   | 	add, a + b |
| \-   | 	subtract, a - b |
| <<   | shift left,  left operand is shifted left by right operand bits (multiply by 2) |
| \>>   |	shift right, left operand is shifted right by right operand bits (divide by 2) |
 | <    |	less than, result is true or false,  a %lt; b
| <=   |	less than or equal, result is true or false,  a <= b
| \>    |	greater than, result is true or false,  a > b
| \>=   |	greater than or equal, result is true or false, a >= b
|  ==   |	equal, result is true or false,  a == b
| !=  | 	not equal, result is true or false,  a != b
|  & | bitwise and,  a & b
| ^ | bitwise exclusive or XOR,  a ^ b
| \| | bitwise or,  a | b
|  &&, and | relational and, result is true or false,  a < b && c >= d
| \|\|, or | relational or, result is true or false,  a < b \|\| c >= d |
| =  | store or assignment |
|  += | add and store |
|  -=  | subtract and store |
|  \*= | multiply and store |
|  /= | divide and store|
|  %= | modulo and store|
| <<= | shift left and store|
|  \>>= | shift right and store|
|  &= | bitwise and and store|
|  ^= | bitwise exclusive or and store|
|  \|= | bitwise or and store|
|  , | separator as in   ( y=x,z=++x )|


### Logical Operator

```python
# Logical AND
1<2 and 2<3
```

```python
# Logical OR
1==2 or 2<3
```

```python
# Logical Not
not(1<2 and 2<3)
```

```python
# IS and IS NOT operators

x is y               # x is the same as y, is not same as ==,
                     # because it compares both value and type()
x is not y           # x is not the same as y
```

```python
# The `in` operator
>>> 'a' in 'banana'
True
>>> 'seed' in 'banana'
False
```


### Naming the variable in Python
The names you use when creating these labels need to follow a few rules:
1. Names can not start with a number.
2. There can be no spaces in the name, use _ instead.
3. Can't use any of these symbols :'",<>/?|\()!@#$%^&*~-+
4. It's considered best practice (PEP8) that names are lowercase.
5. Avoid using the characters 'l' (lowercase letter el), 'O' (uppercase letter oh), 
   or 'I' (uppercase letter eye) as single character variable names.
6. Avoid using words that have special meaning in Python like "list" and "str"


### String 
* All Strings are Unicode from Python3. `u'abc' = 'abc'`
* The expression in brackets is called an ***index***. The index indicates which character in the sequence you want. And it must be a integer.
* Strings are immutable, i.e, `s[0] = 's'` is not possible.
* [[Slicing]] operation can be performed.

#### String comparison
Python does not handle uppercase and lowercase letters the same way that people do. All the uppercase letters come before all the lowercase letters, so:

`Your word, Pineapple, comes before banana.`

Ex:
```python
if word == 'banana':
    print('All right, bananas.')
    
if word < 'banana':
    print('Your word,' + word + ', comes before banana.')
elif word > 'banana':
    print('Your word,' + word + ', comes after banana.')
else:
    print('All right, bananas.')
```
A common way to address this problem is to convert strings to a standard format, such as all lowercase, before performing the comparison.


### Print
#### Formatted String Literals (f-strings)

```python
name = 'Fred'
print(f"He said his name is {name}.")
```

	 He said his name is Fred.
 
Pass `!r` to get the string representation:
```python
print(f"He said his name is {name!r}")
```

	He said his name is 'Fred'.

**Float formatting follows `"result: {value:{width}.{precision}}"`**

```python
num = 23.45678
print("My 10 character, four decimal number is:{0:10.4f}".format(num))
print(f"My 10 character, four decimal number is:{num:{10}.{6}}")
```

    My 10 character, four decimal number is:   23.4568
    My 10 character, four decimal number is:   23.4568

Note that with f-strings, precision refers to the total number of digits, not just those following the decimal. This fits more closely with scientific notation and statistical analysis. Unfortunately, f-strings do not pad to the right of the decimal, even if precision allows it:

```python
num = 23.45
print("My 10 character, four decimal number is:{0:10.4f}".format(num))
print(f"My 10 character, four decimal number is:{num:{10}.{6}}")
```

    My 10 character, four decimal number is:   23.4500
    My 10 character, four decimal number is:     23.45

```python
num = 23.45
print("My 10 character, four decimal number is:{0:10.4f}".format(num))
print(f"My 10 character, four decimal number is:{num:10.4f}")
```

    My 10 character, four decimal number is:   23.4500
    My 10 character, four decimal number is:   23.4500

#### Other Common Print types
* refer this for [.format()]([https://docs.python.org/3/library/string.html#formatstrings](https://docs.python.org/3/library/string.html#formatstrings))
* other regular usages
```python
print("Welcome to Python!")         # ends with newline
print("Welcome to Python!", end='') # ends without the default ending newline
print(str1, str2)                   # prints the 2 strings separated by a space
print(lst)                          # prints an list
```


### Input
```python
name = input("What is your name?")  # read in string
num = float(input("give a number")) # read in number
```


### Selection statement
```py
if condition_1:
  # do something 1
elif condition_2:
  # do something 2
else:
  # do something else
```
Note: There are no switch statements in Python.
The compiler follows [[Short Circuit Logic]]


### Conditional assignment
```py
x = 10 if a > b else 11
```

This is analogous to the more common conditional assignment syntax in other languages:
```java
int x = a > b ? 10 : 11 ;
```


### For loop
```python
for i in some_list:
  # do something
```


### For-else loop
The else block will execute only when the loop condition is evaluated to `False`. i.e. it will only run once at the end
```python
for number in numbers: 
  # do something
else:
  # do something else
```


### While loop
```python
while condition:
  # do something
```


### While-else loop
Just like for-else loops, the else block is executed only when the while-loops terminates formally
```python
while condition: 
  # do something
else:
  # do something else
```


### Control flow
```python
break     # breaks out of current loop
continue  # continues to next iteration in loop
pass      # dose nothing
```


### Functions
Be careful with names, you wouldn't want to call a function the same name as a [built-in function in Python](https://docs.python.org/3/library/functions.html) (such as len).

The first line of the function definition is called the header; the rest is called the body. The header has to end with a colon and the body has to be indented. By convention, the indentation is always four spaces. The body can contain any number of statements.
```python
def name_of_function(arg1,arg2):
    '''
    This is where the function's Document String (docstring) goes.
    When you call help() on your function it will be printed out.
    '''
    # Do stuff here
    # Return desired result
```


### Return
use a `return` statement. `return` allows a function to *return* a result that can then be stored as a variable, or used in whatever manner a user wants.

*Very Common Question: "What is the difference between *return* and *print*?"
**The return keyword allows you to actually save the result of the output of a function as a variable. The print() function simply displays the output to you, but doesn't save it for future use. Let's explore this in more detail**

### Lambda: anonymous functions
```python
lst = [0, 1, 2, 3, 4, 5, 6]
filter(lambda x: x % 3 == 0, lst) #=> [0, 3, 6]
```

