[[Java Basics]]
## Lambda Expression

```Java
( arguments ) -> { body }

(double num1, double num2) -> {return num1 + num2}

// can be re-written
(double x, double y) -> x + y            // Here "->" is the Lambda Operator used to define a Lambda Expression

() -> System.out.println("No Arguments passed in this Lambda Expression")

//For single parameter () is optional and declaration of type is optional
/*
 * Note:
 * For body consisting only 1 statement, {} is not necessary and
 * Return keyword must not be used for returning values!
 */
num -> num+100                                         

//If the data type of the single argument has to be declared, then the use of () is compulsory!!!!
/**
 * Note:
 * For body consisting more than 1 statements, {} is needed 
 * Return keyword is required to be used if any value is to be returned
 */
(double rate) -> { rate = rate*100;                                   
System.out.println("One argument passed: " +rate);
}

//For multiple arguments, declaration is optional only when all arguments are of same type
//Also the use of () is mandatory when declaring multiple arguments!!!
(num1, num2, num3) -> System.out.println("Multiple arguments passed: " +num1+","+num2+","+num3)
```

### Functional Interfaces can be classified into two types:

**User-Defined Functional Interface**:  These are the Functional Interfaces that are defined by the programmer.
```Java
//User Defined Lambda Expression
@FunctionalInterface
interface Operation{
	public double calculations(double num1,double num2); 
}
```

**Built-In Functional Interface**: 

**Function** – It represents a function that takes a single input parameter and returns a single value/object. For example:
```Java
Function<Long, Long> addNum = (value) -> value + 10;
```

**Predicate** – It represents a function that takes a single value/object as a parameter, and returns true or false. For example:
```Java
Predicate<Integer> checkAge = (age) -> age > 18;
```

**Supplier** – It represents a function that produces a value/an object without taking any input parameter. For example:
```Java
Supplier<Integer> generateRandom = ()-> new Integer((int) (Math.random() * 100));
```

**Consumer** – It represents a function that consumes or processes a value/an object without returning anything. For example:
```Java
Consumer<String> printValue = (name)-> System.out.println(name);
```

## Method Reference

It is effective at situations where Lambda Expression is calling an existing method.
```Java
ClassName::methodName  // The "::" operator is used for Method Referencing

List<String> strArr = List.of("Tyson", "Kai", "Max", "Ray", "Daichi");   //List.of() returns immutable list of String with mentioned values
strArr.forEach(s -> System.out.println(s));  //Printing List Using Lambda Expression

strArr.forEach(System.out::println);      //Printing List Using Method Reference 

```
Method Reference helps in reducing lines of code, where Lambda Expression is calling an existing method. But it is **not applicable in cases where we need to pass Arguments**. This is because the feature of passing arguments is not supported by Method Reference.

## Higher Order Function

Functions that can either accept other functions as parameters or return other functions as parameters are called Higher Order Functions (HOF).
```Java
stringList.sort ((str1,str2) -> str1.compareTo(str2));
```

## Streams

A Stream represents a sequence of elements from a source and supports various data processing operations. In other words, it provides an abstraction over an existing collection. Streams also provide the support to parallel threading of the code, which breaks the bigger problem into smaller chunks. These smaller problems are solved separately and their solutions are combined together to obtain the final solution. This is possible, as Streams are both serially and parallelly executable.

```Java
List<String> castList = List.of("Sam","Dean","Castiel","Crowley");
Stream<String> supernatural = castList.stream();

Integer[] array = {672, 340, 999};
Stream<Integer> stream = Arrays.stream(array);

Stream<Integer> stream = Stream.of(672, 340, 999);

//Creating a Stream for objects of Class Employee
Stream<Employee> empStream = Stream.of( new Employee("Tom",5699.5),
			new Employee("Jack",7629.2),new Employee("Jane",5289.8));


```

### The different operations that can be done on Streams are:

**forEach()** : This method is used to traverse each element of the stream. It is used mainly to display the elements in a Stream, **after the stream operation terminates**. For example:
```Java
List<String> placesToVisit= new ArrayList<String>();
placesToVisit.add("Chicago");
placesToVisit.add("Venice");
placesToVisit.add("Tokyo");
placesToVisit.add("San Francisco");
placesToVisit.add("Kyoto");
placesToVisit.add("Abu Dhabi");
placesToVisit.forEach(place -> System.out.println("Trip to " + place));

```

**map()** :  This method is used to return a new stream based on operations done on an existing stream. For example:
```Java
List<String> placesToVisit= new ArrayList<String>();
placesToVisit.add("Chicago");
placesToVisit.add("Venice");
placesToVisit.add("Tokyo");
placesToVisit.add("San Francisco");
placesToVisit.add("Kyoto");
placesToVisit.add("Abu Dhabi");
placesToVisit.stream().map(place -> place.toUpperCase()).forEach(place -> System.out.println(place));

```

**filter()** : This method is used to return a new filtered stream based on conditions given for filtering. For example:
```Java
List<String> placesToVisit= new ArrayList<String>();
placesToVisit.add("Chicago");
placesToVisit.add("Venice");
placesToVisit.add("Tokyo");
placesToVisit.add("San Francisco");
placesToVisit.add("Kyoto");
placesToVisit.add("Abu Dhabi");
placesToVisit.stream().filter(place -> place.length() == 5).forEach(x -> System.out.println(x));

```

**sorted()** : This method is used to sort the elements in a Stream. Argument for this method is optional. For Example:
```Java
//No Argument passed in sorted()
List<String> placesToVisit= new ArrayList<String>();
placesToVisit.add("Chicago");
placesToVisit.add("Venice");
placesToVisit.add("Tokyo");
placesToVisit.add("San Francisco");
placesToVisit.add("Kyoto");
placesToVisit.add("Abu Dhabi");
placesToVisit.stream().sorted().forEach(x -> System.out.println(x));

//Lambda Expression passed as Argument in sorted()
List<String> placesToVisit= new ArrayList<String>();
placesToVisit.add("Chicago");
placesToVisit.add("Venice");
placesToVisit.add("Tokyo");
placesToVisit.add("San Francisco");
placesToVisit.add("Kyoto");
placesToVisit.add("Abu Dhabi");
placesToVisit.stream().sorted((str1,str2) -> str1.compareTo(str2)).forEach(x -> System.out.println(x));

```

**collect()** : This method stores the modified stream as a new collection type (it can be list, map etc.), **after the stream operation terminates**, under a new identifier.
```Java
List<Integer> number = new ArrayList();
number.add(2);
number.add(3);
number.add(4);
number.add(5);
//The Modified Stream is stored in "doubled" using collect()
List<Integer> doubled = number.stream().map(x >2*x).collect(Collectors.toList());
		
System.out.println(doubled);
```

### Pipelining
Streams follow a pipeline format of execution. All the operations that are chained together form the intermediate operations. The last operation, after which the final result is obtained, forms the terminal operation. All the intermediate operations are executed simultaneously based on the stream and the required conditions.

Operations such as **map(), filter(), sorted()** comprise the intermediate operations, as these operations look after the filtering, modifying, and ordering aspects of the streams. Also, i**ntermediate operations return a stream after its execution**. Whereas, operations such as **forEach() and/or collect() comprise the terminal operations**, as these operations do not return any stream. Instead, they deliver the final stream by either by displaying them (forEach()) or by storing them in a collection (collect()).

**Streams are said to be lazy because until the terminal operation is invoked, none of the intermediate operations is executed.**
```Java
Stream<Integer> intStream = Stream.of(10,2,7,5,6,5,8,11);

intStream.filter(n -> { System.out.println("Filtering Current Element: "+n); return n % 2 == 0; })
		 .map(n -> { System.out.println("Mapping Current Element: "+n); return n * n * n; })
		 .sorted()
		 .forEach(n -> System.out.println(n));

```

**!!! IMP: Steams are  not Reuse-able, i.e once used Stream cannot be reused.**
```Java
Stream<String> streamNames = Stream.of("Deadpool", "Iron Man", "Spiderman", "Captain America", "Punisher", "Black Panther");
streamNames.filter(name -> name.length()<=10)                         //Line1
           .sorted()
           .forEach(name -> System.out.println(name));               


streamNames.map(name -> name.toUpperCase())         // Error 
           .forEach(name -> System.out.println(name));

```