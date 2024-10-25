
- **Stream does not store elements. It simply conveys elements** from a source such as a data structure, an array, or an I/O channel, through a pipeline of computational operations.
- Stream is functional in nature. Operations performed on a stream **does not modify it's source**. For example, filtering a Stream obtained from a collection produces a new Stream without the filtered elements, rather than removing elements from the source collection.
- **Stream is lazy and evaluates code only when required**.
- The elements of a stream are only visited once during the life of a stream. Like an Iterator, a **new stream must be generated to revisit the same elements of the source.**

Example:

```java
List<Float> productPriceList2 =productsList.stream()  
                                .filter(p -> p.price > 30000)// filtering data  
                                .map(p->p.price)        // fetching price  
                                .collect(Collectors.toList()); // collecting as list  
```

```java
Stream.iterate(1, element->element+1)  
        .filter(element->element%5==0)  
        .limit(5)  
        .forEach(System.out::println);  // output: 5 10 15 20 25
```

```java
Float totalPrice = productsList.stream()  
                    .map(product->product.price)  
                    .reduce(0.0f,(sum, price)->sum+price);   // accumulating price  

// More precise code   
        float totalPrice2 = productsList.stream()  
                .map(product->product.price)  
                .reduce(0.0f,Float::sum);   // accumulating price, by referring method of Float class  
```


```java
// max() method to get max Product price     
        Product productA = productsList.stream().max((product1, product2) -> product1.price > product2.price ? 1: -1).get();    
        System.out.println(productA.price);


        // min() method to get min Product price    
        Product productB = productsList.stream().min((product1, product2)-> product1.price > product2.price ? 1: -1).get();    
        System.out.println(productB.price);    
```

```java
 productsList.stream()  
                    .filter(product->product.price<30000)  
                    .count();  
```

```java
// Converting product List into Set  
Set<Float> productPriceList =   
productsList.stream()  
            .filter(product->product.price < 30000)   // filter product on the base of price  
            .map(product->product.price)  // .map(Product::getPrice)
            .collect(Collectors.toSet());   // collect it as Set(remove duplicate elements)  
```

**Note: Method reference to map is always static**
```java
 List<Float> productPriceList =   
                productsList.stream()  
                            .filter(p -> p.price > 30000) // filtering data  
                            .map(Product::getPrice)         // fetching price by referring getPrice method  
                            .collect(Collectors.toList());  // collecting as list  
```

```java
 // Converting Product List into a Map  
        Map<Integer,String> productPriceMap =   
            productsList.stream()  
                        .collect(Collectors.toMap(p->p.id, p->p.name));  
```

JAVA 9

```java
List<Integer> list   
        = Stream.of(1,2,3,4,5,6,7,8,9,10)  
                .takeWhile(i -> (i % 2 == 0)).collect(Collectors.toList());     
    System.out.println(list);  // output: []
```
**This example returns an empty list because it fails at first list element, and takewhile stops here.**

```java
List<Integer> list   
        = Stream.of(2,2,3,4,5,6,7,8,9,10)  
                .takeWhile(i -> (i % 2 == 0)).collect(Collectors.toList());     
    System.out.println(list);  //[2,2]
```

**Java Stream dropWhile() Method**

Stream dropWhile method returns result on the basis of order of stream elements.

**Ordered stream**: It returns a stream that contains elements after dropping the elements that match the given predicate.

**Unordered stream**: It returns a stream that contains remaining elements of this stream after dropping a subset of elements that match the given predicate.
Java Stream dropWhile() Method Example

```java
import java.util.List;  
import java.util.stream.Collectors;  
import java.util.stream.Stream;  
public class StreamExample {  
	public static void main(String[] args) {  
		List<Integer> list   
		= Stream.of(2,2,3,4,5,6,7,8,9,10)  
				.dropWhile(i -> (i % 2 == 0)).collect(Collectors.toList());     
	System.out.println(list);  
	}  
}  
```

Output:

	[3, 4, 5, 6, 7, 8, 9, 10]


**Java 9 Stream ofNullable() Method**

Stream ofNullable() method returns a sequential stream that contains a single element, if non-null. Otherwise, it returns an empty stream.

It helps to handle null stream and NullPointerException.
Java 9 Stream ofNullable() Method Example 1

```java
import java.util.List;  
import java.util.stream.Collectors;  
import java.util.stream.Stream;  
public class StreamExample {  
	public static void main(String[] args) {  
		List<Integer> list   
		= Stream.of(2,2,3,4,5,6,7,8,9,10)  
				.dropWhile(i -> (i % 2 == 0)).collect(Collectors.toList());     
	System.out.println(list);  
	}  
}  
```

Output:

	[3, 4, 5, 6, 7, 8, 9, 10]

Java 9 Stream ofNullable Method Example 2

```java
import java.util.stream.Stream;  
  
public class StreamExample {  
	public static void main(String[] args) {  
		Stream<Integer> val   
		= Stream.ofNullable(null);     
	val.forEach(System.out::println);  
	}  
}  
```

**This program will not produce any output.**



**Java Stream Iterate Method**

It takes **three arguments, seed, hasNext and next.**
Java Stream Iterate Method Example

```java
import java.util.stream.Stream;  
  
public class StreamExample {  
	public static void main(String[] args) {  
		Stream.iterate(1, i -> i <= 10, i -> i*3)  
		.forEach(System.out::println);  
	}  
}  
```
Output:

	1
	3
	9
