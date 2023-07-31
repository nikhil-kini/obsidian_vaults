[[Java Basics]]
## MATH LIBRARY
```java
  Public Class Math{
    double abs(double a)    // absolute value of a
    double max(double a, double b)  //maximum of a and b
    double min(double a, dobule a)  //minimum of a and b
    double sin(double theta) //sine of theta
    double cos(double theta) //cosine of theta
    double tan(double theta) //tangent of theta
    double toRadians(double degrees) // convert angle from degrees to radians
    double toDegrees(double radians)  // convert angle from radians to degrees
    double exp(double a)  // exponential (e^a)
    double pow(double a, double p) //raise a to the bth power (a^b)
    double random() //random in [0,1)
    double sqrt(double a)  //square root of a
    }
```

## Integer
```java
Integer.parseInt("123")
Integer.toBinaryString(number)
Integer.MAX_VALUE
Integer.MIN_VALUE
```

## Strings
```java
int length = String.length();
.trim()                             // remove leading and trailing whitespaces
.replaceAll("\\s+", " ");           // remove multiple sequential whitespaces
Integer.toString(10, 2);            // parse int to bin string: convert 10 to "1010"
StringBuffer                        // append(), reverse(), length(), substring(), charAt(), deleteCharAt(), delete(start, end_exclusive)
char[] charArr = str.toCharArray(); // convert string to char array
String str = new String(charArr);   // convert char array to string
toLowerCase();
toUpperCase();
```

## Random
```java
Random gen = new Random();
gen.nextInt(x); // returns random number between 0 and x exclusive
```

## Arrays
```java
int length = array.length
subarray = Arrays.copyOfRange(arr, start, end_exclusive); // only after 1.6
System.arraycopy(src_arr, src_start, dest_arr, dest_start, copy_length);  // makes copy of array
Arrays.sort(arr);     // sorts array in-place
Arrays.toString(arr); // e.g. [1,2,3] => "1, 2, 3"
Arrays.fill(arr, deafault_val); // initializes all cells with the default value given
```

## Iterators
```java
ArrayList<Integer> al = ...
Iterator<Integer> itr = al.iterator();
while(itr.hasNext()) {
  Integer curr = itr.next();
  // do something with curr
}
```

## Comparators
Comparators are classes for comparison between objects. see http://www.mkyong.com/java/java-object-sorting-example-comparable-and-comparator/
### Using anonymous classes
```java
Comparator<Integer> myComparator = new Comparator<Integer>() {
  public int compare(Integer o1, Integer o2){
    return o2 - o1; // biggest to smallest (smallest to biggest is o1 - o2)
    // return o1.compareTo(o2); // using the class' natural comparator and in ascending order
  }
};
```
### Using actual classes
```java
class ColorComparator implements Comparator<CarSort> {
  public int compare(CarSort c1, CarSort c2) {
  return c1.getColor().compareTo(c2.getColor());
  }
}
```

## Comparable
```java
Class MyClass implements Comparable<MyClass> {
  int value;
  // ...
  public int compareTo(MyClass something) {
    return this.value - something.value;  // ascending order
  }
}
```