
Is a paradigm of solving all requirement of the program using functions alone. The function do not mutate data outside their function.

## Higher Order Function (HOF)

**HOF** - A function which accepts another function as a parameter.
**Callback functions** - The parameter function.

## Lamda Expressions

```python
lambda arguments:body

lambda (x,y): x*y
```

## In-built HOF

* **Foreach** (not in python)
* **Map**
```python
collection_variable = []
map(lambda expression, collection_variable)
list(map(lambda x:x*2, collection_variable))
```
* **Filter**
```python
collection_variable = []
filter(lambda expression, collection_variable)
list(filter(lambda x:x>2, collection_variable))
```
* **Reduce**
```python
collection_variable = []
filter(lambda expression, collection_variable)
reduce(lambda x,y:x+y, collection_variable) # to get sum all elements
```

