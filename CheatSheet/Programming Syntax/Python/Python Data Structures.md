
### List
In python, lists are variable length arrays much like `ArrayList` in C++ and `Vector` in Java. Lists offer random access and has similar time complexities (see [here](https://wiki.python.org/moin/TimeComplexity)) as variable length arrays in other languages. For the sake of consistency, we shall reserve the variable `lst` to refer to a list in the given context.

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