## Exception handling
### Common exceptions

Exception | Description
--- | ---
`IOError` | Raised when a file cannot be opened
`ImportError` | Raised when python cannot find the module
`ValueError` | Raised when a built-in operation or function receives an argument that has the right type but an inappropriate value
`ZeroDivisionError` | Raised when division by zero occurs
`EOFError` | Raised when one of the built-in functions (input() or raw_input()) hits an end-of-file condition (EOF) without reading any data
`KeyboardInterrupt` | Raised when the user hits the interrupt key (normally Control-C or Delete)
`ValueError` | Raised when a built-in operation or function receives an argument that has the right type but an inappropriate value

See [Built-in Exceptions](https://docs.python.org/3/library/exceptions.html) for the full list of Python exceptions that come out of the box.

### Catching exceptions
try-except:
```py
try:
  # something dangerous

except IOError:
  # do something
  
except (RuntimeError, TypeError, NameError): # multiple exceptions
  # do something
  
# Generic exception: Catches all exceptions
except Exception as e: # access exception variable with `as e`
  # do something. e.g `print(str(e))`
```

Else:
```py
try:
  # something dangerous
  
except KeyboardInterrupt:
  # do something

# ONLY WHEN preceding except clauses did not catch anything
else:
  # do something
  # NOTE: Code in this block may raise exceptions of its own
```

Finally:
```py
try:
  # something dangerous

except KeyboardInterrupt:
  # do something

else:
  # do something

# Always executed before leaving the try statement, regardless if an exception occurred or not
finally:
  # do something
  
```

### Raising exceptions

Raising pre-defined exceptions:
```py
raise IOError
raise SyntaxError("Oops!") # Add a custom message
```

Raising custom exception:
```py
class MyException(Exception):
  pass

raise MyException("Truly exceptional!")
```