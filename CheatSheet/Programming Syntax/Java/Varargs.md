
## Syntax of varargs:

The varargs uses ellipsis i.e. three dots after the data type. Syntax is as follows:

```java
return_type method_name(data_type... variableName){}
```

## Rules for varargs:
- There can be only one variable argument in the method.
- Variable argument (varargs) must be the last argument.

```java
void method(String... a, int... b){}//Compile time error  

void method(int... a, String b){}//Compile time error  
  
```


```java
class VarargsExample3{  
   
 static void display(int num, String... values){  
  System.out.println("number is "+num);  
  for(String s:values){  
   System.out.println(s);  
  }  
 }  
  
 public static void main(String args[]){  
  
 display(500,"hello");//one argument   
 display(1000,"my","name","is","varargs");//four arguments  
 }   
}  
```


##  Java 9 @SafeVarargs Annotation
It was included in Java7 and can only be applied on
- Final methods
- Static methods
- Constructors

**To avoid compile time warning**

**From Java 9**, it can also be used with **private instance methods**.

**Note: The @SafeVarargs annotation can be applied only to methods that cannot be overridden. Applying to the other methods will throw a compile time error.**
```java
public class SafeVar{  
    // Applying @SaveVarargs annotation  
    @SafeVarargs  
    private void display(List<String>... products) { // Not using @SaveVarargs  
        for (List<String> product : products) {  
            System.out.println(product);  
        }  
    }  
    public static void main(String[] args) {  
        SafeVar p = new SafeVar();  
        List<String> list = new ArrayList<String>();  
        list.add("Laptop");  
        list.add("Tablet");  
        p.display(list);  
    }     
}  
```

