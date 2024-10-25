
TO-DO




Java 9 introduced a new feature that allows us to use diamond operator with anonymous classes. Using the diamond with anonymous classes was not allowed in Java 7.

In Java 9, as long as the inferred type is denotable, we can use the diamond operator when we create an anonymous inner class.

```java
abstract class ABCD<T>{  
	abstract T show(T a, T b);  
}  
public class TypeInferExample {  
	public static void main(String[] args) {  
		ABCD<String> a = new ABCD<>() { // diamond operator is empty, compiler infer type  
			String show(String a, String b) {  
				return a+b;   
			}  
		};    
		String result = a.show("Java","9");  
		System.out.println(result);  
	}  
}  
```

```java
abstract class ABCD<T>{  
	abstract T show(T a, T b);  
}  
public class TypeInferExample {  
	public static void main(String[] args) {  
		ABCD<String> a = new ABCD<String>() { // diamond operator is not empty  
			String show(String a, String b) {  
				return a+b;   
			}  
		};    
		String result = a.show("Java","9");  
		System.out.println(result);  
	}  
}  
```
