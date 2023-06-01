## Exceptions
The exception hierarchy in Java is as follows:
```
Exception heirarchy:
       [Throwable]
       /         \
[Error]          [Exception]
                 /         \
    [IOException]          [RuntimeException]
```

### Useful throw-able methods
```java
getMessage();                 // Returns a detailed message about the exception that has occurred. This message is initialized in the Throwable constructor.
Throwable cause = getCause(); // Returns the cause of the exception as represented by a Throwable object.
toString();                   // Returns the name of the class concatenated with the result of getMessage()
printStackTrace();            // Prints the result of toString() along with the stack trace to System.err, the error output stream
```

### Try/Catch block
```java
try {
  // Protected code
}
catch(ExceptionType1 e1) {
  // Catch block
}
catch(ExceptionType2 e2) {
  // Catch block
}
catch(ExceptionType3 e3) {
  // Catch block
}
finally {
  // (optional)The finally block always executes, whether or not an exception has occurred
}
```

### Throws/throw
```java
public class className {
  public void deposit(double amount) throws RemoteException {
    // Method implementation
    if (condition) {
      throw new RemoteException();
    }
  }
   // Remainder of class definition
}
```

### Custom exception classes
```java
public class InsufficientFundsException extends Exception {
  private double amount;
  public InsufficientFundsException(double amount) {
    this.amount = amount;
  } 
  public double getAmount() {
    return amount;
  }
}
```
