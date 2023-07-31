[[Python Basics]]

### File
#### Opening and closing
Python uses the following modes when invoking the `open()` function:
* `r`: read (default)
* `rb`: read bytes
* `w`: write
* `wb`: write bytes
* `r+`: read and write

File objects contain a special pair of built-in methods: `__enter__()` and `__exit__()`. When a file object's `__exit__()` method is invoked, it automatically closes the file. Files are typically opened and closed with the following commands:
```python
some_file = open(some_file_path, mode)  # opening a file
some_file.close()                       # closing a file
some_file.closed                        # check if a file is closed
```

The opening and closing of a file can be conveniently handled with a **with-as** clause:
```python
with open(some_file_path, mode) as some_file:
  # do something with some_file
```
Note: `some_file` is automatically closed when the with-as clause upon completion of the containing block. It is also closed when a `return` is made from within the block.

#### Reading
Read the entire file as a string (or otherwise)
```python
with open(some_file_path, mode) as some_file:
  file_contents = some_file.read()
```
Read the file line by line:
```python
with open(some_file_path, read_mode) as some_file:
  for line in some_file:
    # do something with line
```
#### Writing
```python
contents = 'something'
with open(some_file_path, write_mode) as some_file:
  some_file.write(contents)
```
Note: During the I/O process, data is buffered. this means that it is held in a temporary location before being written to the file. Python doesn't flush the buffer—that is, write data to the file—until it's sure you're done writing
One way to do this is to close the file. **If you write to a file without closing, the data won't make it to the target file.**
