
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
s.startswith('Str') #=> True
s.endswith('ing')   #=> True
'hello ' + 'world'  #=> 'hello world' # concatenation
','.join('abc')           #=> 'a,b,c'
','.join(['a', 'b', 'c']) #=> 'a,b,c' # For this to work, the argument has to be a list of strings
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