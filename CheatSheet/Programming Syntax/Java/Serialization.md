
Store object in the text file. Note: This is not equivalent to storing String, an object is stored in **bytecode**, or **JSON** or **YAML** format.

Uses:
- Communication (JSON Communication)
- Persistence (Store the state of particular operation )
- Deep Copy (exact replica, simply serialize to byte array and de-serialize it to another object achieves this.)
- Caching
- Cross JVM Synchronization

## Byte Code

**Classes that are eligible for serialization need to implement a special marker interface,** _Serializable._

Note that **static fields belong to a class (as opposed to an object) and are not serialized**. Also, note that we can use the keyword _transient_ to ignore class fields during serialization:
```java
public class DemoSerili {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Person person = new Person();
	    person.setAge(20);
	    person.setName("Joe");
	    person.setHeight(10);
	    
	    FileOutputStream fileOutputStream
	      = new FileOutputStream("yourfile.txt");
	    ObjectOutputStream objectOutputStream 
	      = new ObjectOutputStream(fileOutputStream);
	    objectOutputStream.writeObject(person);
	    objectOutputStream.flush();
	    objectOutputStream.close();
	    
	    FileInputStream fileInputStream
	      = new FileInputStream("yourfile.txt");
	    ObjectInputStream objectInputStream
	      = new ObjectInputStream(fileInputStream);
	    Person p2 = (Person) objectInputStream.readObject();
	    objectInputStream.close(); 
	    
	    System.out.println(p2.toString());          // transient element = 0
	    System.out.println(person.toString());      // original ele val that is set
	    System.out.println(p2 == person);           // false
	    System.out.println(p2.equals(person));      // true ( has trasient is not used in equal())
	    System.out.println(p2.getName().equals(person.getName())); // true
	}

}

class Person implements Serializable{
	private static final long serialVersionUID = 2L; // Not added to file, should not change if it is reading previously written file
	static String country = "Italy";  // also ignored
	private int age;
	private String name;
	transient int height;  // Not added to the file
	
	//getter setter, hash and equals (transient elements are not added)
}
```

> Note: **The JVM associates a version (_long_) number with each serializable class.**
> 
> If a serializable class **doesn’t declare** a **_serialVersionUID_**, the **JVM will generate** one automatically at run-time. However, it’s **highly recommended that each class declares its `serialVersionUID`, as the generated one is compiler dependent and thus may result in unexpected `InvalidClassExceptions`.** (Same issue occurs if there is a mismatch in declared `serialVersionUID` from previously written one.) 


## Inheritance and Composition

When a class implements the _java.io.Serializable_ interface, **all its sub-classes are serializable as well**. Conversely, when an object has a **reference** to another object, these objects **must implement the _Serializable_ interface separately**, or else a _NotSerializableException_ will be thrown:

```java
public class Person implements Serializable {
    private int age;
    private String name;
    private Address country; // must be serializable too
}
```

If one of the fields in a serializable object consists of an array of objects, then all of these objects must be serializable as well, or else a _NotSerializableException_ will be thrown.


## Custom Serialization

Custom serialization can be particularly useful when **trying to serialize an object that has some unserializable attributes.**
```java
private void writeObject(ObjectOutputStream out) throws IOException;
```
and
```java
private void readObject(ObjectInputStream in) 
  throws IOException, ClassNotFoundException;
```


```java
public class Employee implements Serializable{

	private static final long serialVersionUID = 12632468343877042L;
	
	private transient Address address;  // note  Transient for custom element entry
	private Personal person;
	
	// getter setter
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
		oos.writeObject(address.getHouseNumber());
	}
	
	private void readObject(ObjectInputStream ois) 
		      throws ClassNotFoundException, IOException {
		        ois.defaultReadObject();
		        Integer houseNumber = (Integer) ois.readObject();
		        Address a = new Address();
		        a.setHouseNumber(houseNumber);
		        this.setAddress(a);
		    }
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Personal p = new Personal();
	    p.setAge(20);
	    p.setName("Joe");

	    Address a = new Address();
	    a.setHouseNumber(1);

	    Employee e = new Employee();
	    e.setPerson(p);
	    e.setAddress(a);

	    FileOutputStream fileOutputStream
	      = new FileOutputStream("yourfile2.txt");
	    ObjectOutputStream objectOutputStream 
	      = new ObjectOutputStream(fileOutputStream);
	    objectOutputStream.writeObject(e);
	    objectOutputStream.flush();
	    objectOutputStream.close();

	    FileInputStream fileInputStream 
	      = new FileInputStream("yourfile2.txt");
	    ObjectInputStream objectInputStream 
	      = new ObjectInputStream(fileInputStream);
	    Employee e2 = (Employee) objectInputStream.readObject();
	    objectInputStream.close();
	    
	    System.out.println(e2.getPerson().getAge() == e.getPerson().getAge());
	    System.out.println(e2.getAddress().getHouseNumber() == e.getAddress().getHouseNumber());
	}
}

class Address{
	private int houseNumber;

	// getter setter
}

class Personal implements Serializable{
	
	private static final long serialVersionUID = 5L;
	
	private int age;
	private String name;
	transient int height;
	// getter setter
}
```


### For other types of serialization such as JSON, YML [refer this](https://www.baeldung.com/java-serialization-approaches)

