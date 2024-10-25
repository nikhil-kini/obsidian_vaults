An Interface that contains **exactly one abstract method** is known as functional interface. It can have any number of default, static methods but can contain only one abstract method. It can also declare methods of object class.

```java
@FunctionalInterface  
interface sayable{  
	void say(String msg);

 // It can contain any number of Object class methods.  
    int hashCode();  
    String toString();  
    boolean equals(Object obj);  
}  
public class FunctionalInterfaceExample implements sayable{  
	public void say(String msg){  
		System.out.println(msg);  
	}  
	public static void main(String[] args) {  
		FunctionalInterfaceExample fie = new FunctionalInterfaceExample();  
		fie.say("Hello there");  

	}  
}  
```



## Java Lambda Expression Syntax

```
(argument-list) -> {body}  
```

Java lambda expression is consisted of three components.

1) **Argument-list**: It can be empty or non-empty as well.
2) **Arrow-token**: It is used to link arguments-list and body of expression.
3) **Body**: It contains expressions and statements for lambda expression.

No Parameter Syntax

```java
() -> {  
//Body of no parameter lambda  
}  
```

One Parameter Syntax

```java
(p1) -> {  
//Body of single parameter lambda  
}  
```

Two Parameter Syntax

```java
(p1,p2) -> {  
//Body of multiple parameter lambda  
}  
```

**if there is only one statement, you may or may not use return keyword.**
Note: Implemented Lambda method signature must be same as the Functional interface method.
```java
interface Addable{  
	int add(int a,int b);  
}  
  
public class LambdaExpressionExample6 {  
	public static void main(String[] args) {  
		  
		// Lambda expression without return keyword.  
		Addable ad1=(a,b)->(a+b);  
		System.out.println(ad1.add(10,20));  
		  
		// Lambda expression with return keyword.    
		Addable ad2=(int a,int b)->{  
							return (a+b);   
							};  
		System.out.println(ad2.add(100,200));  
	}  
}  
```

## Java Method References

It is compact and easy form of lambda expression.
Three types of method references in java:
1. Reference to a static method.
2. Reference to an instance method.
3. Reference to a constructor.

## Reference to a Static Method

**refer to static method defined in the class.**
Syntax

```java
ContainingClass::staticMethodName  
```

```java
interface Sayable{  
	void say();  
}  
public class MethodReference {  
	public static void saySomething(){  
		System.out.println("Hello, this is static method.");  
	}  
	public static void main(String[] args) {  
		// Referring static method  
		Sayable sayable = MethodReference::saySomething;  
		// Calling interface method  
		sayable.say();  
	}  
}  
```


## Reference to an Instance Method

**refer instance methods**

Syntax

```java
containingObject::instanceMethodName  
```

```java
interface Sayable{  
	void say();  
}  
public class InstanceMethodReference {  
	public void saySomething(){  
		System.out.println("Hello, this is non-static method.");  
	}  
	public static void main(String[] args) {  
		InstanceMethodReference methodReference = new InstanceMethodReference(); // Creating object  
		// Referring non-static method using reference  
			Sayable sayable = methodReference::saySomething;  
		// Calling interface method  
			sayable.say();  


			// Referring non-static method using anonymous object  
			Sayable sayable2 = new InstanceMethodReference()::saySomething; // You can use anonymous object also  
			// Calling interface method  
			sayable2.say();  
	}  
}  
```


## Reference to a Constructor

**refer a constructor by using the new keyword** Here, we are referring constructor with the help of functional interface.

Syntax

```java
ClassName::new  
```

```java
interface Messageable{  
	Message getMessage(String msg);  // Message Obj signature
}  
class Message{  
	Message(String msg){  
		System.out.print(msg);  
	}  
}  
public class ConstructorReference {  
	public static void main(String[] args) {  
		Messageable hello = Message::new;  
		hello.getMessage("Hello");  
	}  
}  
```