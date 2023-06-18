
### List
In python, lists are variable length arrays much like `ArrayList` in C++ and `Vector` in Java. Lists offer random access and has similar time complexities (see [here](https://wiki.python.org/moin/TimeComplexity)) as variable length arrays in other languages. For the sake of consistency, we shall reserve the variable `lst` to refer to a list in the given context.
[[Slicing]] operation can be done.

#### Declaration and initialization

##### Single-dimensional list:
```python
lst = [5, 6, 7, 8]            #=> [5, 6, 7, 8] # explicit assignment
lst = [1] * 4                 #=> [1, 1, 1, 1] # generative assignment
lst = list(range(5,9))        #=> [5, 6, 7, 8] # generative assignment using range
lst = [x for x in range(5,9)] #=> [5, 6, 7, 8] # generative assignment using range and list comprehension (see list comprehension section below)
```

##### Multi-dimensional lists:
```python
# Explicit assignment
lst = [[1,2,3],[4,5,6]] #=> [[0, 0], [0, 0], [0, 0]] 

# Generative assignment
cols = 2; rows = 3
lst = [[0] * cols for i in range(rows)] #=> [[0, 0], [0, 0], [0, 0]] 
```
Caveat (see [here](https://stackoverflow.com/questions/17636567/python-initialize-multi-dimensional-list)):
```python
cols = 2; rows = 3
lst = [[0] * cols] * rows #=> [[0, 0], [0, 0], [0, 0]]
lst[0][1] = 1
print(lst)                #=> [[0, 1], [0, 1], [0, 1]]
```

#### List comprehension
List comprehension is commonly used as a shorthand for creating a new list by iterating over a given list.

It is commonly used as a filter by applying a condition over the given list:
```python
a = [1, 2, 3, 4]
odd_a = [x for x in a if i%2 == 0] #=> [2, 4]
```
It is also commonly used for modifying each item in the given list:
```python
a = [1, 2, 3, 4]
squared_a = [x**2 for x in a] #=> [1, 4, 9, 16]
squared_even_a = [x**2 if x%2 == 0 else x for x in a] #=> [1, 4, 3, 16]
```
We can also nest lists within the comprehension to iterate through multi-dimensional lists. One application for this is to flatten out a multi-dimensional list:
```python
lst_2d = [[1, 2, 3], [4, 5, 6]]
flattened_lst = [x for row in lst_2d for x in row ] #=> [1, 2, 3, 4, 5, 6] 
```

#### Iterating
To iterate over all items in the list:
```python
for item in lst:
  # do something with item
```
To iterate over all indices for items in the list:
```python
for index in range(len(lst)):
  # do something with index
```
To iterate over all indicies and their accompanying items in the list:
```python
for index, item in enumerate(lst):
  # do something with index, item
```
To iterate all items in the list in reversed order (without using slice):
```python
for item in reversed(lst):
  # do something with item
```
use `zip` to iterate over multiple lists at once (terminating on the shorter list):
```python
for item_1, item_2, item_3 in zip(lst_1, lst_2, lst_3):
  # do something with item_1, item_2, item_3
```
#### Sorting
```python
lst.sort()                          # in-place sorting
tuples = [(5,99), (4,54), (3,12), (2,44)]
sorted(tuples)                      # returns (2,44), (3,12), (4,54), (5,99)
sorted(tuples, key=lambda x: x[1])  # returns (3,12), (2,44), (4,54), (5,99)
```



### Dictionary
In Python dictionary is equivalent to hashmap.

#### Declaration and initialization
Declaring an empty dictionary:
```python
dic = {}
dic = dict()  # using contructor
```
Declaring and initializing with values:
```python
dic = {'key_1' : val_1, 'key_2' : val_2}          # via explicit assignment
dic = dict([('key_1', val_1), ('key_2', val_2)])  # via key,val pair list
dic = dict(key_1=val_1, key_2=val_2)              # via keyword arguments
dic = {x: x**2 for x in (1, 2, 3)}                # via dict comprehensions 
```

#### Iterating
```python
# Iterates each key (not guaranteed same order everytime)
for key in hashmap:
  # Do something with key
  
# Iterates each key, value pair
for key, value in hashmap.items():
  # Do something with key, value
```
#### Sorting
To get a sorted list of keys:
```python
sorted(hashmap)
```
#### Short-hands
```python
dic = {'a': 1, 'b': 2, 'c': 3}
[value for (key, value) in sorted(dic.items())] #=> [1, 2, 3]
```



### Tuples
Tuples are immutable in Python
```python
uniple = (1,) # Notice!!!
pair = (1, 2)
triplet = (1, 2) + (3,) # creates a new tuplet
```
Note: `,` is used at end to define a tuple with single value.
[[Slicing]] operation can be done.

#### Comparing tuples
The comparison operators work with tuples and other sequences. Python starts by comparing the first element from each sequence. If they are equal, it goes on to the next element, and so on, until it finds elements that differ. Subsequent elements are not considered (even if they are really big).

```python
>>> (0, 1, 2) < (0, 3, 4)
True
>>> (0, 1, 2000000) < (0, 3, 4)
True
```



### Sets
Sets are an unordered collection of *unique* elements. We can construct them by using the set() function.

```python
x = set()
```

```python
# We add to sets with the add() method
x.add(1)

# to clear all element in the set
x.clear()
```

```python
# Create a list with repeats
list1 = [1,1,2,2,3,4,5,6,1,1]
```

```python
# Cast as set to get unique values
set(list1)
```
    {1, 2, 3, 4, 5, 6}


