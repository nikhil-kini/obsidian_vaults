**Structural [design patterns](https://www.baeldung.com/design-patterns-series) are those that simplify the design of large object structures** by identifying relationships between them.

## Adapter

- **acts as an intermediary to convert an otherwise incompatible interface to one that a client expects**.
```java
List<String> musketeers = Arrays.asList("Athos", "Aramis", "Porthos");
```


```java
//This is the client class.  
public class AdapterDP {
	public static void main(String args[]) {
		CreditCard targetInterface = new BankCustomer();
		targetInterface.giveBankDetails();
		System.out.print(targetInterface.getCreditCard());
	}
}

interface CreditCard {
	public void giveBankDetails();

	public String getCreditCard();
}// End of the CreditCard interface.

// This is the adapter class.  
class BankDetails {
	private String bankName;
	private String accHolderName;
	private long accNumber;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public long getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(long accNumber) {
		this.accNumber = accNumber;
	}
}// End of the BankDetails class.

//This is the adapter class. 
class BankCustomer extends BankDetails implements CreditCard {
	public void giveBankDetails() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Enter the account holder name :");
			String customername = br.readLine();
			System.out.print("\n");

			System.out.print("Enter the account number:");
			long accno = Long.parseLong(br.readLine());
			System.out.print("\n");

			System.out.print("Enter the bank name :");
			String bankname = br.readLine();

			setAccHolderName(customername);
			setAccNumber(accno);
			setBankName(bankname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getCreditCard() {
		long accno = getAccNumber();
		String accholdername = getAccHolderName();
		String bname = getBankName();

		return ("The Account number " + accno + " of " + accholdername + " in " + bname
				+ "  bank is valid and authenticated for issuing the credit card. ");
	}
}// End of the BankCustomer class.
```

## Bridge

-  **allows separation between abstractions and implementations so that they can be developed independently from each other but still have a way, or bridge, to coexist and interact**.
PostgreSQL, we might have:
```java
Connection connection = DriverManager.getConnection(url);
```
`postgress` is the bridge that gives meta data
```java
String url = "jdbc:postgresql://localhost/demo";
```

![[Pasted image 20241018143055.png]]
```java
public class BridgeDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Bridge
		Shape square = new Square(new Blue());
		
		System.out.println(square.draw());
	}

}

interface Color {
	String fill();
}

class Blue implements Color {
	@Override
	public String fill() {
		return "Color is Blue";
	}
}

abstract class Shape {
	public Shape(Color color) {
		super();
		this.color = color;
	}

	protected Color color;

	// standard constructors

	abstract public String draw();
}

class Square extends Shape {

	public Square(Color color) {
		super(color);
	}

	@Override
	public String draw() {
		return "Square drawn. " + color.fill();
	}
}
```

## Decorator

- **when we want to enhance the behavior of an object without modifying the original object itself**
```java
public class DecoratorDP {
	private static int choice;

	public static void main(String args[]) throws NumberFormatException, IOException {
		do {
			System.out.print("========= Food Menu ============ \n");
			System.out.print("            1. Vegetarian Food.   \n");
			System.out.print("            2. Non-Vegetarian Food.\n");
			System.out.print("            4. Exit                        \n");
			System.out.print("Enter your choice: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			choice = Integer.parseInt(br.readLine());
			switch (choice) {
			case 1: {
				VegFood vf = new VegFood();
				System.out.println(vf.prepareFood());
				System.out.println(vf.foodPrice());
			}
				break;

			case 2: {  // Decorator declaration
				Food f1 = new NonVegFood((Food) new VegFood());
				System.out.println(f1.prepareFood());
				System.out.println(f1.foodPrice());
			}
				break;

			default: {
				System.out.println("Other than these no food available");
			}
				return;
			}// end of switch

		} while (choice != 4);
	}
}

interface Food {
	public String prepareFood();

	public double foodPrice();
}// End of the Food interface.

class VegFood implements Food {
	public String prepareFood() {
		return "Veg Food";
	}

	public double foodPrice() {
		return 50.0;
	}
}

// Decorator
abstract class FoodDecorator implements Food {
	private Food newFood;

	public FoodDecorator(Food newFood) {
		this.newFood = newFood;
	}

	@Override
	public String prepareFood() {
		return newFood.prepareFood();
	}

	public double foodPrice() {
		return newFood.foodPrice();
	}
}

class NonVegFood extends FoodDecorator {
	public NonVegFood(Food newFood) {
		super(newFood);
	}

	public String prepareFood() {
		return super.prepareFood() + " With Roasted Chiken and Chiken Curry  ";
	}

	public double foodPrice() {
		return super.foodPrice() + 150.0;
	}
}
```


## Facade Pattern

- **just provide a unified and simplified interface to a set of interfaces in a subsystem, therefore it hides the complexities of the subsystem from the client**
- **every Abstract Factory** is a type of **Facade.**

## Flyweight
- **The flyweight pattern takes the weight, or memory footprint, off of our objects by recycling them**
Flyweight can be spotted all over the [_Number_](https://www.baeldung.com/java-number-class) classes in Java.

The `valueOf` methods used to create an object of any data type’s wrapper class are designed to cache values and return them when required.

For example, [_Integer_](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Integer.html) has a static class, _IntegerCache,_ which helps its [_valueOf_](https://www.baeldung.com/java-convert-string-to-int-or-integer#integervalueof) method to always cache values in the range -128 to 127:

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high) {
        return IntegerCache.cache[i + (-IntegerCache.low)];
    }
    return new Integer(i);
}
```

## Proxy

- **This pattern offers a proxy, or a substitute, to another complex object**.
- proxy pattern is used heavily in [AOP](https://docs.spring.io/spring/docs/2.5.x/reference/aop.html#aop-understanding-aop-proxies) and [remoting](https://docs.spring.io/spring/docs/2.5.x/reference/remoting.html).
```java
public class ProxyDp {
	public static void main(String[] args) {
		OfficeInternetAccess access = new ProxyInternetAccess("Ashwani Rajput");
		access.grantInternetAccess();
	}
}

interface OfficeInternetAccess {
	public void grantInternetAccess();
}

class RealInternetAccess implements OfficeInternetAccess {
	private String employeeName;

	public RealInternetAccess(String empName) {
		this.employeeName = empName;
	}

	@Override
	public void grantInternetAccess() {
		System.out.println("Internet Access granted for employee: " + employeeName);
	}
}

// proxies other interface implementation
class ProxyInternetAccess implements OfficeInternetAccess {
	private String employeeName;
	private RealInternetAccess realaccess;

	public ProxyInternetAccess(String employeeName) {
		this.employeeName = employeeName;
	}

	@Override
	public void grantInternetAccess() {
		if (getRole(employeeName) > 4) {
			realaccess = new RealInternetAccess(employeeName);
			realaccess.grantInternetAccess();
		} else {
			System.out.println("No Internet access granted. Your job level is below 5");
		}
	}

	public int getRole(String emplName) {
		// Check role from the database based on Name and designation
		// return job level or job designation.
		return 9;
	}
}
```


## Observer

- An Observer Pattern says that **"just define a one-to-one dependency so that when one object changes state, all its dependents are notified and updated automatically".**
```java

// Observers = subscribers
 class ResponseHandler1 implements Observer {  
    private String resp;  
    public void update(Observable obj, Object arg) {  
        if (arg instanceof String) {  
            resp = (String) arg;  
            System.out.println("\nReceived Response: " + resp );  
        }  
    }  
}// End of the ResponseHandler1 interface.  


 class ResponseHandler2 implements Observer {  
    private String resp;  
    public void update(Observable obj, Object arg) {  
        if (arg instanceof String) {  
            resp = (String) arg;  
            System.out.println("\nReceived Response: " + resp );  
        }  
    }  
}// End of the ResponseHandler2 interface.  


// Observable = publisher
public class EventSource extends Observable implements Runnable {  
    @Override  
    public void run() {  
        try {  
            final InputStreamReader isr = new InputStreamReader(System.in);  
            final BufferedReader br = new BufferedReader(isr);  
            while (true) {  
                String response = br.readLine();  
                setChanged();  
                notifyObservers(response);  
            }  
        }  
        catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}// End of the Eventsource class.  
```

## Strategy Pattern

A Strategy Pattern says that **"defines a family of functionality, encapsulate each one, and make them interchangeable".**
- Regular interface implementation
```java
interface NotificationStrategy {
    void notify(User user, Message message);
}

class EmailNotificationStrategy implements NotificationStrategy {
    ....
}

class SMSNotificationStrategy implements NotificationStrategy {
    ....
}
```


## Template Method

- **We’ll define a base class with the template method and a set of one or more abstract methods** – either unimplemented or else implemented with some default behavior. **The template method then calls these abstract methods in a fixed pattern.**
```java
//This is an abstract class.  
public abstract class Game {  
      
       abstract void initialize();  
       abstract void start();  
       abstract void end();  
      
       public final void play(){  
  
          //initialize the game  
          initialize();  
  
          //start game  
          start();  
            
          //end game  
          end();  
       }  
}// End of the Game abstract class.  


//This is a class.  
  
public class Chess extends Game {  
	 @Override  
	   void initialize() {  
		  System.out.println("Chess Game Initialized! Start playing.");  
	   }  
	 @Override  
	   void start() {  
		  System.out.println("Game Started. Welcome to in the chess game!");  
	   }  
	@Override  
	   void end() {  
		  System.out.println("Game Finished!");  
	   }  
}// End of the Chess class.  

//This is a class.  
public class TemplatePatternDemo {  
  
public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {  
		  
		 Class c=Class.forName(args[0]);  
		 Game game=(Game) c.newInstance();  
		 game.play();     
	   }  
}// End of the Soccer class.  
```

## Visitor[](https://www.baeldung.com/java-behavioral-patterns-jdk#visitor)

**The [Visitor](https://www.baeldung.com/java-visitor-pattern) pattern allows our code to handle various subclasses in a typesafe way, without needing to resort to _instanceof_ checks.**