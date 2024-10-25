
## Creating a generic class:
```java
class MyGen<T>{  
T obj;  
void add(T obj){this.obj=obj;}  
T get(){return obj;}  
}  
```
The `T` type indicates that it can refer to any type (like String, Integer, and Employee). The type you specify for the class will be used to store and retrieve the data.


## Type Parameters

The common type parameters (**std convention only, no strict rule**) are as follows:

1. T - Type
2. E - Element
3. K - Key
4. N - Number
5. V - Value


## Generic Method
- the scope of arguments is limited to the method where it is declared. 
- It allows **static as well as non-static methods**.
```java
public static < E > void methodName(E[] parameter) {  
        //logic
    }  
```

```java
public < E > void methodName(E[] parameter) {  
        //logic
    }  
```
static ex
```java
public class Test1 {
	public static < E > void printArray(E[] elements) {  
        for ( E element : elements){          
            System.out.println(element );  
         }  
         System.out.println();  
    }  
    public static void main( String args[] ) {  
        Integer[] intArray = { 10, 20, 30, 40, 50 };  
        Character[] charArray = { 'J', 'A', 'V', 'A', 'T','P','O','I','N','T' };  
  
        System.out.println( "Printing Integer Array" );  
        printArray( intArray  );   
  
       System.out.println( "Printing Character Array" );  
        printArray( charArray );   
    }   
}
```

non-static
```java
public class Test1 {
	public  < E > void printArray(E[] elements) {  
        for ( E element : elements){          
            System.out.println(element );  
         }  
         System.out.println();  
    }  
    public static void main( String args[] ) {  
        Integer[] intArray = { 10, 20, 30, 40, 50 };  
        Character[] charArray = { 'J', 'A', 'V', 'A', 'T','P','O','I','N','T' };  
  
        System.out.println( "Printing Integer Array" );  
        new Test1().printArray( intArray  );   
  
       System.out.println( "Printing Character Array" );  
       new Test1().printArray( charArray );   
    }   
}
```


## Wildcard in Java Generics

- use a wildcard as a **type of a parameter, field, return type, or local variable**
- cannot use for invocation of generic methods
- ONLY USED FOR TYPE SPECIFICATION
### Upper Bounded Wildcards
- It is used by declaring wildcard character ("?") followed by the **extends** (in case of, class) or **implements** (in case of, interface) keyword, followed by its upper bound.
```java
List<? extends Number>  

List<Integer> //valid
List<Number> //valid
List<Object> // Invalid
```
it means any **child class of Number, are allowed** e.g., **Integer, Float, and double**
Here,
**?** is a wildcard character.
**extends**, is a keyword.
**Number**, is a class present in java.lang package


### Unbounded Wildcards

useful in the following scenarios: -
- When the given method is implemented by using the functionality provided in the **Object class**.
- When the generic class contains the methods that don't depend on the type parameter.

```java
List<?>  

List< any type is valid >
```


## Lower Bounded Wildcards
- lower bounded wildcards is to restrict the unknown type to be a specific type or a supertype of that type.
```java
List<? super Integer>  

List<Integer> //valid
List<Number> //valid
List<Object> //valid
```
it means any **child class of Number, are allowed** e.g., **Integer, Float, and double**
Here,
**?** is a wildcard character.
**super**, is a keyword.
**Number**, is a class present in java.lang package