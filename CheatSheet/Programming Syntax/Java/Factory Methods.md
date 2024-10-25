

## List

```java
import java.util.List;  
public class FactoryMethodsExample {  
	public static void main(String[] args) {  
		List<String> list = List.of("Java","JavaFX","Spring","Hibernate","JSP");  
		for(String l:list) {  
			System.out.println(l);  
		}  
	}  
}  
```


## Set

```java
import java.util.Set;  
public class FactoryMethodsExample {  
	public static void main(String[] args) {  
		Set<String> set = Set.of("Java","JavaFX","Spring","Hibernate","JSP");  
		for(String l:set) {  
			System.out.println(l);  
		}  
	}  
}  
```


## Map

```java
import java.util.Map;  
public class FactoryMethodsExample {  
	public static void main(String[] args) {  
		Map<Integer,String> map = Map.of(101,"JavaFX",102,"Hibernate",103,"Spring MVC");  
		for(Map.Entry<Integer, String> m : map.entrySet()){    
			System.out.println(m.getKey()+" "+m.getValue());  
		}  
	}  
}  
```


## Map Entries

```java
import java.util.Map;  
public class FactoryMethodsExample {  
	public static void main(String[] args) {  
		// Creating Map Entry  
		Map.Entry<Integer, String> e1 = Map.entry(101, "Java");  
		Map.Entry<Integer, String> e2 = Map.entry(102, "Spring");  
		// Creating Map using map entries  
		Map<Integer, String> map = Map.ofEntries(e1,e2);  
		// Iterating Map   
		for(Map.Entry<Integer, String> m : map.entrySet()){    
			System.out.println(m.getKey()+" "+m.getValue());  
		}  
	}  
}  
```