[[Java Basics]]
![](collection.png)



### **Pair**
This can be implemented using generics as follows:
```java
public static class Pair<X,Y>{
  X one;
  Y two;
  public Pair(X one, Y two){
    this.one = one;
    this.two = two;
  }
}
```

### TreeMap
TreeMap can be used as a Binary Search Tree:
```java
TreeMap<K,V> BST = new TreeMap<K,V>(new Comparator);
BST.put(key, value);
BST.get(key);
BST.size();
BST.isEmpty();
```

### **QUEUE DATA TYPE**
```java
  public class Queue<Item> implements Iterable<Item>

  Queue()  //create an empty queue
  boolean isEmpty()  //return if the queue empty
  void enqueue(Item item) // insert an item onto queue
  Item dequeue()  //return and remove the item that was inserted least recently
  int size() //number of item on queue
```

### PriorityQueue
```java
PriorityQueue<Integer> PQ= new PriorityQueue<Integer>(initial_capacity, myComparator);
PQ.peek(),;
PQ.poll();
PQ.offer(item);
PQ.size();
PQ.isEmpty();
```
`PriorityQueue` can also be used to implement a max heap as follows:
```java
PriorityQueue<Long>(10, Collections.reverseOrder());
```

### LinkedList
Can be used to implement a Queue
```java
LinkedList<Object> queue = new LinkedList<Object>();
queue.peek();
queue.poll();
queue.offer(item);
queue.isEmpty();
```

### Stack
```java
Stack<Object> stack = new Stack<Object>();
stack.peek();
stack.pop();
stack.push(item);
stack.empty();  
```

### Hashmap
```java
HashMap<Key_Class, Value_Class> map;
map.put(key, value);
map.get();
map.size();
map.containsKey(key);
map.getOrDefault(key, 0); // Hashmap default values. Note: can return null if key exists and its value is null

```
Iterate a hashmap via:
```java
for (HashMap.Entry<Key_Class, Value_Class> entry: charMap.entrySet()) {
  // do something
}
```

### **SET DATA TYPE**
```java
  public class Set<Key extends Comparable<Key>> implements Iterable<Key>
  Set() //create an empty set
  boolean isEmpty()  //return if the set is empty
  void add (Key key)  //add key to the set
  void remove(Key key)  //remove key from set
  boolean contains(Key key) //return if the key is in the set
  int size() //number of elements in set
```

Set operations:
```java
boolean b = setA.containsAll(setB); // check if setB is a susetB of setA
setA.addAll(setB);                  // union - transform in-place setA into the union of setA and setB
setA.retainAll(setB);               // intersection - transforms in-place setA into the intersection of setA and setB
setA.removeAll(setB);               // difference - transforms in-place setA into the (asymmetric) set difference of setA and setB.
```
