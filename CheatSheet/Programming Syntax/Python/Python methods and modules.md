```python
# To find length
len('Hello')

# To define Infinity
float("inf")

```


### complex()
complex() returns a complex number with the value real + imag*1j or converts a string or number to a complex number.
```python
# Create 2+3j
complex(2,3)
```


### Range
`range(start, stop, step)` provides a convenient construct for generating an ordered list of numbers
```python
range(6)            #=> [0,1,2,3,4,5]
range(1,6)          #=> [1,2,3,4,5]
range(1,6,3)        #=> [1,4]
range(10,-1,-1)     #=> [10,9,8,7,6,5,4,3,2,1,0]

3 in range(1,4)     #=> True
3 not in range(1,4) #=> False
```


#### Math
Python contains useful inbuilt math functions
```python
a = [2,3,4,5]
max(a)        #=> 5
min(a)        #=> 2
sum(a)        #=> 14
abs(-1)       #=> 1
pow(x, y)     #=> x ** y
divmod(x, y)  #=> (x // y, x % y)
complex(re, im)   # a complex number with real part re, imaginary part im. im defaults to zero.
c.conjugate()     # conjugate of the complex number c
```


#### Strings
```python
s = "String"              # single-line string
s = '''This is a
  mutli-line string'''
s[0]        #=> 'S'       # retrieve character
s[1:4]      #=> 'tri'     # slice
len(s)      #=> 6         # string length
s.lower()   #=> "string"  # downcase
s.upper()   #=> "STRING"  # upcase
s.isalpha() #=> True      # Alpha numeric check 
s.find('Str') #=> Int index of Str in s
s.startswith('Str') #=> True
s.endswith('ing')   #=> True
'hello ' + 'world'  #=> 'hello world' # concatenation
','.join('abc')           #=> 'a,b,c'
','.join(['a', 'b', 'c']) #=> 'a,b,c' # For this to work, the argument has to be a list of strings

# to get list of string elements
x = [int(cha) for cha in str(num)]
```


### List
```python
lst.count(item)         # counts the number of times item appears in list 
lst.index(item)         # returns index of first occurence of the item
lst.insert(index, item) # inserts item at position index, suceeding elements are pushed down | in-place | does not return
lst.append(item)        # append item to end of list | in-place | does not return
lst.remove(item)        # remove item | in-place | does not return
lst.pop(index)          # removes object at index and returns it (if not index provided, will pop last) | in-place | returns popped item
del(lst[index])         # same as pop exept it doesn't return the object
del lst[index]          # same as above
```

### Dictionaries
Common methods to check and update members
```python
key in dic        # check if key is in dic
dic[key] = value  # adding/updating value. Note key can be a number
del dic[key]      # delete (key, val) from dic
```
Common methods to extract members:
```python
dic.items()   # returns an list of (key, value) tuples, not in any order
dic.keys()    # returns an list of keys, not in any order
dic.values()  # return an list of values, not in any order
```

#### re
```python
import re
re.search(r'\d+\.\d*N','1.312740N, 103.779490E').group(0) #=> '1.312740N'
```

#### datetime
```python
import datetime
date = datetime.now()   # current date and time
date.year
date.month
date.day
date.hour
date.minute
date.second
```

#### random
```python
from random import randint
randint(low, high)  # generates random interger between low to high inclusive
```

#### enum (only available on Python 3.4 onwards)
```python
from enum import Enum
class EdgeDetector(Enum):
  prewit = 0
  sobel = 1
```

#### Zip()

fill in

### Collections
#### Counter()

fill in

#### dequeue(LIFO)



### Math Module

#### Ceil

#### Floor


