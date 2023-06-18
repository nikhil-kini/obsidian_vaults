
## Iterators

### Iterator basics
```python
lst = [3,1,4,1,5,9,2,6,5]
lst_itr = iter(lst) # creates a fresh iterator over lst

# Simple function that advances the iterator until exhaustion, priting each value along the way
def print_iter(itr):
  while True:
    value = next(itr, None) # this will advance the iterator and return the item itr is currently pointing to. Here we explicitly stated that when itr reaches past the last item, we want it to return None. If second argument is not provided, Python will throw the StopIteration error.
    if value: # if not None
      print(value)
    else:
      break
```


### Casting iterators
```python
print_iter(lst_itr)
print('Empty List:', list(lst_itr)) # At this point in time the iterator has exhausted and therefore this will return an empty list
lst_itr = iter(lst) # re-assign fresh iterator
print('Casting fresh iterator as list:',list(lst_itr)) # You can cast a fresh iterator as a list. Doing so will exhaust the iterator!
print('Exhausted iterable casted as list will be empty:',list(lst_itr)) # This will print [] because the iterator has exhausted again at this point
```

### Map object is an iterator
```python
doubled = map(lambda x: 2*x, lst)
print_iter(doubled) # proves that map object is just an iterator
print('Exhausted map iterable casted as list will be empty:', list(doubled))
```
