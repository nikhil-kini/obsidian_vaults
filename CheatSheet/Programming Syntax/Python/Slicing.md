
### Slicing
Slicing is achieved with the `[start:end:stride]` operator
* `start` describes where the slice starts (inclusive)
  * If not provided, value defaults to *0*
* `end` is where it ends (exclusive)
  * If not provided, value defaults to *length of list*. I.E. Operation defaults to `[start:length:1]`
  * If value is negative, Python interpreter will subtract that value off the length of list. For example `a[start:-x]` is translated to `lst[start:length-x]`
* `stride` indicates the interval between items in the sliced list
  * If not provided, value defaults to *1*. I.E. Operation defaults to `[start:end:1]`
  * `start` and `end` defines the sequence for stride to traverse
  * `stride` indicates the interval in sequence for which objects are picked. For example, a stride of 2 will skip 1 item in sequence at every interval.
  * a positive stride length traverses the sequence in sequential order
  * a negative stride traverses the sequence in reverse order

```python
# Assign s as a string
s = 'Hello World'

# Show first element (in this case a letter)
s[0]

# Grab everything past the first term all the way to the length of s which is len(s)
s[1:]

# Grab everything UP TO the 3rd index
s[:3]

# Last letter (one index behind 0 so it loops back around)
s[-1]

# Grab everything but the last letter
s[:-1]

# Grab everything, but go in steps size of 1
s[::1]

# We can use this to print a string backwards
s[::-1]

# We can reassign s completely though!
s = s + ' concatenate me!'
```
