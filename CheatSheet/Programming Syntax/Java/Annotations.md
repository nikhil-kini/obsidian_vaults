
## Java Custom Annotations

There are few points that should be remembered by the programmer.

1. Method should not have any throws clauses
2. Method should return one of the following: primitive data types, String, Class, enum or array of these data types.
3. Method should not have any parameter.
4. We should attach @ just before interface keyword to define annotation.
5. It may assign a default value to the method.
## Types of Annotation
1. Marker Annotation
2. Single-Value Annotation
3. Multi-Value Annotation

## 1) Marker Annotation

An annotation that has no method, is called marker annotation. For example:

 ```java
@interface MyAnnotation{}  
```
The @Override and @Deprecated are marker annotations.

---

## 2) Single-Value Annotation

An annotation that has one method, is called single-value annotation. For example:

```java
@interface MyAnnotation{  
	int value();  
}  
```

We can provide the default value also. For example:

```java
@interface MyAnnotation{  
 int value() default 0;  
}  
```

## How to apply Single-Value Annotation

Let's see the code to apply the single value annotation.

```java
@MyAnnotation(value=10)  
```

The value can be anything.

---

## 3) Multi-Value Annotation

An annotation that has more than one method, is called Multi-Value annotation. For example:

```java
@interface MyAnnotation{  
	int value1();  
	String value2();  
	String value3();  
	}  

```

We can provide the default value also. For example:

```java
@interface MyAnnotation{  
	int value1() default 1;  
	String value2() default "";  
	String value3() default "xyz";  
}  
```

## How to apply Multi-Value Annotation

Let's see the code to apply the multi-value annotation.

```java
@MyAnnotation(value1=10,value2="Arun Kumar",value3="Ghaziabad")
```

## Built-in Annotations used in custom annotations in java

- @Target
- @Retention
- @Inherited
- @Documented


**@Target** tag is used to specify at which type, the annotation is used.

|Element Types|Where the annotation can be applied|
|---|---|
|TYPE|class, interface or enumeration|
|FIELD|fields|
|METHOD|methods|
|CONSTRUCTOR|constructors|
|LOCAL_VARIABLE|local variables|
|ANNOTATION_TYPE|annotation type|
|PARAMETER|parameter|

## Example to specify annotation for a class

```java
@Target(ElementType.TYPE)  
@interface MyAnnotation{  
	int value1();  
	String value2();  
}  
```

## Example to specify annotation for a class, methods or fields

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})  
 @interface MyAnnotation{  
	 int value1();  
	 String value2();  
 }  
```
---

## @Retention

**@Retention** annotation is used to specify to what level annotation will be available.

|RetentionPolicy|Availability|
|---|---|
|RetentionPolicy.SOURCE|refers to the source code, discarded during compilation. It will not be available in the compiled class.|
|RetentionPolicy.CLASS|refers to the .class file, available to java compiler but not to JVM . It is included in the class file.|
|RetentionPolicy.RUNTIME|refers to the runtime, available to java compiler and JVM .|

## Example to specify the Retention-policy

```java
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE)  
@interface MyAnnotation{  
	int value1();  
	String value2();  
}
```


## @Inherited

By default, annotations are not inherited to subclasses. The `@Inherited` annotation marks the annotation to be inherited to subclasses.

```java
@Inherited  
@interface ForEveryone { }//Now it will be available to subclass also  
  
@interface ForEveryone { }  
class Superclass{}  
  
class Subclass extends Superclass{}  
```

## @Documented

The @Documented Marks the annotation for inclusion in the documentation.
## Declare and usage

```java
public class Test1 {
	public static void main(String args[]) throws Exception {

		Hello h = new Hello();
		Method m = h.getClass().getMethod("sayHello");

		MyAnnotation manno = m.getAnnotation(MyAnnotation.class);
		System.out.println("value is: " + manno.value());
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation {
	int value();
}

//Applying annotation  
class Hello {
	@MyAnnotation(value = 10)
	public void sayHello() {
		System.out.println("hello annotation");
	}
}
```



## Java Type Annotations

After releasing of Java SE 8 , annotations can be applied to any type use. It means that annotations can be used anywhere you use a type. For example, if you want to avoid `NullPointerException` in your code, you can declare a string variable like this:
```java
@NonNull String str;  
```

```java
@NonNull List<String>  

List<@NonNull String> str  

Arrays<@NonNegative Integer> sort  

@Encrypted File file  

@Open Connection connection  

void divideInteger(int a, int b) throws @ZeroDivisor ArithmeticException  
```


## Java Repeating Annotations

In Java 8 release, Java allows you to repeating annotations in your source code. It is helpful when you want to reuse annotation for the same class. You can repeat an annotation anywhere that you would use a standard annotation.

For compatibility reasons, repeating annotations are stored in a container annotation that is automatically generated by the Java compiler. In order for the compiler to do this, two declarations are required in your code.

- Declare a repeatable annotation type
- Declare the containing annotation type

### 1) Declare a repeatable annotation type

Declaring of repeatable annotation type must be marked with the @Repeatable meta-annotation. In the following example, we have defined a custom @Game repeatable annotation type.

```java
@Repeatable(Games.class)  
@interfaceGame{  
	String name();  
	String day();  
}  
```

The value of the @Repeatable meta-annotation, in parentheses, is the type of the container annotation that the Java compiler generates to store repeating annotations. In the following example, the containing annotation type is Games. So, repeating @Game annotations is stored in an @Games annotation.

### 2) Declare the containing annotation type

Containing annotation type must have a value element with an array type. The component type of the array type must be the repeatable annotation type. In the following example, we are declaring Games containing annotation type:

```java
@interfaceGames{  
	Game[] value();  
}  
```

Note - Compiler will throw a compile-time error, if you apply the same annotation to a declaration without first declaring it as repeatable.
Java Repeating Annotations Example

```java
// Importing required packages for repeating annotation   
import java.lang.annotation.Repeatable;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
// Declaring repeatable annotation type  
@Repeatable(Games.class)  
@interfaceGame{  
	String name();  
	String day();  
}  
// Declaring container for repeatable annotation type  
@Retention(RetentionPolicy.RUNTIME)  
@interfaceGames{  
	Game[] value();  
}  
// Repeating annotation  
@Game(name = "Cricket",  day = "Sunday")  
@Game(name = "Hockey",   day = "Friday")  
@Game(name = "Football", day = "Saturday")  
public class RepeatingAnnotationsExample {  
	public static void main(String[] args) {  
		// Getting annotation by type into an array  
		Game[] game = RepeatingAnnotationsExample.class.getAnnotationsByType(Game.class);  
		for (Gamegame2 : game) {    // Iterating values  
			System.out.println(game2.name()+" on "+game2.day());  
		}  
	}  
}  
```
OUTPUT:
	
	Cricket on Sunday
	Hockey on Friday
	Football on Saturday
