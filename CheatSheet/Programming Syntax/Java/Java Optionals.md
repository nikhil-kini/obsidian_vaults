[[Java Basics]]
The object of this class may or **may not contain a non-null value**.

## Creation
**empty()**: Using this method you can create an empty optional object as follows:
```Java
Optional<Employee> employee = Optional.empty();
```

**of()**: Using this method you can create an optional object with non-null value:
```Java
Employee employee = new Employee(101, "Jack");
Optional<Employee> optional = Optional.of(employee);
```

**ofNullable()**: Using this method you can create create an optional object which contain null as well as non-null values as follows:
```Java
Employee employee = new Employee(101, "Jack");
Optional<Employee> optional = Optional.ofNullable(employee);
```

## Common Methods

**isPresent()**: This method checks whether Optional object contains non-null value or not. It returns true if non-null value is present, otherwise, it returns a false.
```Java
Employee employee = new Employee(101, "Jack");
Optional<Employee> optional = Optional.of(employee);
optional.isPresent();

```

**ifPresent(Consumer consumer)**: This method executes the consumer only if a value is present in the Optional object.
```Java
EmployeeDAO employeeDAO = new EmployeeDAO();
Optional<Employee> optional = EmployeeDAO.getEmployee(101);
optional.ifPresent(employee -> {
  System.out.println(employee.getEmployeeId());
  System.out.println(employee.getEmployeeName());
});

```

**get()**: This method retrieves the value present in Optional object.
```Java
Employee employee = new Employee(101, "Jack");
Optional<Employee> optional = Optional.of(employee);
if(optional.isPresent())
     Employee emp = optional.get();

```
If value is not present, then it throws NoSuchElementException so it is better to check the availability of value before getting the value.

**orElse(T otherValue)**: This method returns the value if present in the Optional object, else it will return otherValue.
```Java
EmployeeDAO employeeDAO = new EmployeeDAO();
Optional<Employee> optional = employeeDAO.getEmployee(103);
Employee employee = optional.orElse(new Employee(0, null));

```
If value is present in the optional corresponding to the employee id 103, it will be returned. If not present, the Employee object mentioned as the parameter will be returned.

**orElseThrow(Supplier exceptionSupplier)**: This method returns the value if present in the Optional object, else throw an exception created by the exceptionSupplier.
```Java
EmployeeDAO employeeDAO = new EmployeeDAO();
Optional<Employee> optional = employeeDAO.getEmployee(103);
try {
	employee = optional.orElseThrow(EmployeeNotFoundException::new);
	System.out.println(employee.getEmployeeId());
	System.out.println(employee.getEmployeeName());
} catch(EmployeeNotFoundException exception) {
	System.out.println("Employee is not present in the DB");
}

```
If value is present in the optional corresponding to the employee id 103, it will be returned. If not present, the exception provided by the supplier will be thrown.