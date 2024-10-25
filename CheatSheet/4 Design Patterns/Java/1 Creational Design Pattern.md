1. Singleton – Ensures that at most only one instance of an object exists throughout application
2. Factory Method – Creates objects of several related classes without specifying the exact object to be created
3. Abstract Factory – Creates families of related dependent objects
4. Builder **–** Constructs complex objects using step-by-step approach

## Singleton
- the Singleton is the default scope and the **IOC container**
```java
public class SingletonDP {
	public static void main(String[] args) {
		A singleton = A.getA();
		singleton.doSomething();
	}
}


class A{  
    private static A obj;  // = new A() for early initialization
    private A(){}  
      
    public static A getA(){  
      if (obj == null){  
         synchronized(A.class){  
           if (obj == null){  
               obj = new A();//instance will be created at request time  
           }  
       }              
       }  
     return obj;  
    }  
     
    public void doSomething(){  
    //write your code  
    	System.out.println("Singleton exp");
    }  
   }  
```


## Factory Method
- **defines an interface for creating an object, but let subclasses decide which class to instantiate**
- the spring framework which provides the basic support for **DI** (Dependency Injection)
```java
public class FactoryDP {
	public static void main(String args[]) throws IOException {
		GetPlanFactory planFactory = new GetPlanFactory();

		System.out.print("Enter the name of plan for which the bill will be generated: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String planName = br.readLine();
		System.out.print("Enter the number of units for bill will be calculated: ");
		int units = Integer.parseInt(br.readLine());

		Plan p = planFactory.getPlan(planName);
		// call getRate() method and calculateBill()method of DomesticPaln.

		System.out.print("Bill amount for " + planName + " of  " + units + " units is: ");
		p.getRate();
		p.calculateBill(units);
	}
}

abstract class Plan {
	protected double rate;

	abstract void getRate();

	public void calculateBill(int units) {
		System.out.println(units * rate);
	}
}

class DomesticPlan extends Plan {
	// @override
	public void getRate() {
		rate = 3.50;
	}
}// end of DomesticPlan class.

class CommercialPlan extends Plan {
	// @override
	public void getRate() {
		rate = 7.50;
	}
}

class InstitutionalPlan extends Plan {
	// @override
	public void getRate() {
		rate = 5.50;
	}
}

class GetPlanFactory {

	// use getPlan method to get object of type Plan
	public Plan getPlan(String planType) {
		if (planType == null) {
			return null;
		}
		if (planType.equalsIgnoreCase("DOMESTICPLAN")) {
			return new DomesticPlan();
		} else if (planType.equalsIgnoreCase("COMMERCIALPLAN")) {
			return new CommercialPlan();
		} else if (planType.equalsIgnoreCase("INSTITUTIONALPLAN")) {
			return new InstitutionalPlan();
		}
		return null;
	}
}
```


## Abstract Factory

-  **Factory to select different types of sub-Factory**
- Factory -> sub-factories -> concrete class impl selections
```java
public class AbstractFactoryDP {
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Enter the name of Bank from where you want to take loan amount: ");
		String bankName = br.readLine();

		System.out.print("\n");
		System.out.print("Enter the type of loan e.g. home loan or business loan or education loan : ");

		String loanName = br.readLine();
		AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
		Bank b = bankFactory.getBank(bankName);

		System.out.print("\n");
		System.out.print("Enter the interest rate for " + b.getBankName() + ": ");

		double rate = Double.parseDouble(br.readLine());
		System.out.print("\n");
		System.out.print("Enter the loan amount you want to take: ");

		double loanAmount = Double.parseDouble(br.readLine());
		System.out.print("\n");
		System.out.print("Enter the number of years to pay your entire loan amount: ");
		int years = Integer.parseInt(br.readLine());

		System.out.print("\n");
		System.out.println("you are taking the loan from " + b.getBankName());

		AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");
		Loan l = loanFactory.getLoan(loanName);
		l.getInterestRate(rate);
		l.calculateLoanPayment(loanAmount, years);
	}
}

interface Bank {
	String getBankName();
}

class HDFC implements Bank {
	private final String BNAME;

	public HDFC() {
		BNAME = "HDFC BANK";
	}

	public String getBankName() {
		return BNAME;
	}
}

class ICICI implements Bank {
	private final String BNAME;

	ICICI() {
		BNAME = "ICICI BANK";
	}

	public String getBankName() {
		return BNAME;
	}
}

class SBI implements Bank {
	private final String BNAME;

	public SBI() {
		BNAME = "SBI BANK";
	}

	public String getBankName() {
		return BNAME;
	}
}

abstract class Loan {
	protected double rate;

	abstract void getInterestRate(double rate);

	public void calculateLoanPayment(double loanamount, int years) {
		/*
		 * to calculate the monthly loan payment i.e. EMI
		 * 
		 * rate=annual interest rate/12*100; n=number of monthly installments; 1year=12
		 * months. so, n=years*12;
		 * 
		 */

		double EMI;
		int n;

		n = years * 12;
		rate = rate / 1200;
		EMI = ((rate * Math.pow((1 + rate), n)) / ((Math.pow((1 + rate), n)) - 1)) * loanamount;

		System.out.println("your monthly EMI is " + EMI + " for the amount" + loanamount + " you have borrowed");
	}
}// end of the Loan abstract class.

class HomeLoan extends Loan {
	public void getInterestRate(double r) {
		rate = r;
	}
}// End of the HomeLoan class.

class BussinessLoan extends Loan {
	public void getInterestRate(double r) {
		rate = r;
	}

}// End of the BusssinessLoan class.

class EducationLoan extends Loan {
	public void getInterestRate(double r) {
		rate = r;
	}
}// End of the EducationLoan class.

abstract class AbstractFactory {
	public abstract Bank getBank(String bank);

	public abstract Loan getLoan(String loan);
}

class BankFactory extends AbstractFactory {
	public Bank getBank(String bank) {
		if (bank == null) {
			return null;
		}
		if (bank.equalsIgnoreCase("HDFC")) {
			return new HDFC();
		} else if (bank.equalsIgnoreCase("ICICI")) {
			return new ICICI();
		} else if (bank.equalsIgnoreCase("SBI")) {
			return new SBI();
		}
		return null;
	}

	public Loan getLoan(String loan) {
		return null;
	}
}// End of the BankFactory class.

class LoanFactory extends AbstractFactory {
	public Bank getBank(String bank) {
		return null;
	}

	public Loan getLoan(String loan) {
		if (loan == null) {
			return null;
		}
		if (loan.equalsIgnoreCase("Home")) {
			return new HomeLoan();
		} else if (loan.equalsIgnoreCase("Business")) {
			return new BussinessLoan();
		} else if (loan.equalsIgnoreCase("Education")) {
			return new EducationLoan();
		}
		return null;
	}

}

class FactoryCreator {
	public static AbstractFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("Bank")) {
			return new BankFactory();
		} else if (choice.equalsIgnoreCase("Loan")) {
			return new LoanFactory();
		}
		return null;
	}
}

```


## Builder Pattern

- **ObjectMapper** uses case
```java
public class BuilderDP {
	private final String title;

	private final String text;

	private final String category;

	BuilderDP(Builder builder) {
		this.title = builder.title;
		this.text = builder.text;
		this.category = builder.category;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public String getCategory() {
		return category;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String title;
		private String text;
		private String category;

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public Builder category(String category) {
			this.category = category;
			return this;
		}

		public BuilderDP build() {
			return new BuilderDP(this);
		}
	}

	public static void main(String[] args) {
		BuilderDP bdp = BuilderDP.builder().text("hi").title("ji").category("n").build();
		System.out.println(bdp.title);
	}
}
```