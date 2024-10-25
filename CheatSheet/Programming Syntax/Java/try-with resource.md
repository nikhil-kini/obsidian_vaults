The resource is as an object that must be closed after finishing the program. The try-with-resources statement **ensures that each resource is closed at the end of the statement execution**.

```java
try(    // Using multiple resources  
        FileOutputStream fileOutputStream =new FileOutputStream("/java7-new-features/src/abc.txt");  
        InputStream input = new FileInputStream("/java7-new-features/src/abc.txt")){ 
        
        fileOutputStream.write(); 
        // logic
}
```

No need for `fileOutputStream.close()` logic


or
JAVA 9
```java
FileOutputStream fileStream=new FileOutputStream("javatpoint.txt");  
        try(fileStream){  
	        fileOutputStream.write(); 
	        // logic
        }
```