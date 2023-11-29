
### Comment
```typescript
// this is a comment 
```

### Print
They are surrounded by the backquote \\ backtick (\`) character and embedded expressions of the form ${ expr }.
```typescript
console.log(`The String ${variable}`); // its f-string from python
```

|**Data Type**|**Explanation**|
|---|---|
|var|Variable declared with this type would have **function scope**.  <br><br>They can be **re-assigned** and **re-defined**. <br><br>When a variable **declared outside the function**, it would have **global scope** and automatically attaches itself to the window object.|
|let|Variable declared with this type would have a **block-level scope**. <br><br>They can be **re-assigned** and cannot be **redefined**.|
|const|Variable declared with this type would have a **block-level scope**. <br><br>They can be **neither re-assigned nor re-defined**.|

```Typescript
for (var count=1; count<5;count++)
{
    console.log(count);
}
console.log(count);
```

```Typescript
for (let count=1; count<5;count++)
{
    console.log(count);
}
//count is not accessible outside for loop
console.log(count);
```

```Typescript
const products:string[]=["Gadget","Furniture","Accessories"];
products[3]="Books";
//Cannot re-assign entire array. This throws compilation error.
products=["cloths","Bedsheets"];//Error
```

## Object Types
**The core primitive types in TypeScript are all lowercase!**
![](https://i.imgur.com/ctwC9yY.png)

**Bad practice** to **assign a type** to a variable is we **already initializing** it as well.
```typescript
let number1 = 5         // good practice
let number1: number = 5 // bad practice
```

However if we only declaring the variable, without giving a value to it, it's **good to write out its type**, so **later we can refer** to its correct type, or get a compiling error to warn us incorrect typing.
```typescript
let number1: number // Good Practice
let number1 = 5   // no error
let number1 = '5' // Compile error: Type of 'string' not assignable to type of 'number'
```
**Note: Variables don't default to any value, they need to be assigned**

```typescript
let number1: number   // Not Null
let number1?: number  // Optional value, no error
let number1!: number  // 
let ...number1: number  // Stream of values of type number
```


### symbol :
It is possible to create an empty symbol value using a constructor or with a string key as shown below:
```Typescript
let sym1 = Symbol(); //constructor usage 
let sym2 = Symbol(“Jack”); // optional string value 
```
**If you compare two symbols, they are always unique and immutable.**

### unique symbol:
You can use unique symbol along with read-only or const datatype to create a symbol value as primary key.
```Typescript
const sym1: unique symbol = Symbol(); 
let s1 = Symbol(); 
console.log(typeof s1); // symbol 
let s2 = Symbol(“First Symbol”); 
console.log(s2.toString()); // Symbol(First Symbol) 
let s3 = Symbol(); 
let s4 = Symbol(); 
console.log(s3===s4); //false 
```

### Objects:
```typescript
const car: { type: string, model: string, year: number } = {
  type: "Toyota",
  model: "Corolla",
  year: 2009
};
```
however TypeScript **can infer the types** of properties based on their values.
```typescript
const car = {
  type: "Toyota",
};
car.type = "Ford"; // no error
car.type = 2; // Error: Type 'number' is not assignable to type 'string'.
```

### **Array types**
```typescript
const names: string[] = ["Capitan", "Lily"];
names.push("Rocco"); // no error
names.push(3); // Error: Argument of type 'number' is not assignable to parameter of type 'string'.

//Using pop() function
let products:string[]=["Mobiles","Tablets","Television"];
products.pop();

//Using splice() function
let products:string[]=["Mobiles","Tablets","Television"];
products.splice(1,2);

//other way 
let numList: Array<number>;
numList = [1, 2, 3, 4, 5];
```

##### filter, find, reduce higher order functions
```typescript
let results = numList.filter((num) => num > 2);

let emp = empList.find((emp) => emp === "Santosh");

let sum = numList.reduce((acc, num) => acc + num);
```

If we would need a **mixed array with different types**, we could **use ```any[]```.** Then TS would allow any kind of type inside that array. We **shouldn't overuse** it, as we would loose the whole point of using TS. There are **union** types for that kind of problem.

### **Typed arrays (Tuple)**
A tuple is a typed array with a **pre-defined length and types for each index**.
Tuples are great because they allow each element in the array to be a known type of value.
```typescript
// define our tuple
let ourTuple: [number, boolean, string];

// assigning value to Tuple 
// initialize correctly
ourTuple = [5, false, 'Coding God was here'];

// Adding new value to Tuple
productAvailable.push('Samsung Galaxy J5', false);

// initialized incorrectly which throws an error
ourTuple = [false, 'Coding God was mistaken', 5];
```
**:::warning
Even though we have a *boolean*, *string*, and *number* the order matters in our tuple and will throw an error.
:::**

**You can have Labeled Tuple along with rest parameters and optional elements as below:**
```Typescript
type Demo = [one: number, two?: string, ...rest: any[]]; 
```

### **Enums**
An enum is a **special "class" that represents a group of constants** *(unchangeable variables)*.

Enums come in two flavors **string** and **numeric**. Lets start with numeric.

#### Numeric Enums - Default
By default, enums will initialize the first value to 0 and add 1 to each additional value:
```typescript
enum CardinalDirections {
  North, // 0
  East,  // 1
  South, // 2
  West   // 3
}
let currentDirection = CardinalDirections.North;
console.log(currentDirection);
// logs 0

currentDirection = 'North'; // Error: "North" is not assignable to type 'CardinalDirections'.
// throws error as 'North' is not a valid enum
```

#### Numeric Enums - Initialized
You can set the value of the first numeric enum and have it auto increment from that:
```typescript
enum CardinalDirections {
  North = 1,
  East,
  South,
  West
}
// logs 1
console.log(CardinalDirections.North);
// logs 4
console.log(CardinalDirections.West);
```

#### Numeric Enums - Fully Initialized
You can assign unique number values for each enum value. Then the values will not incremented automatically:
```typescript
enum StatusCodes {
  NotFound = 404,
  Success = 200,
  Accepted = 202,
  BadRequest = 400
}
// logs 404
console.log(StatusCodes.NotFound);
// logs 200
console.log(StatusCodes.Success);
```

**Note: You can use `const` to mask enums concise on compile.**
```typescript
const enum StatusCodes {
  NotFound = 404,
  Success = 200,
  Accepted = 202,
  BadRequest = 400
}
// logs 404
console.log(StatusCodes.NotFound);
// logs 200
console.log(StatusCodes.Success);
```

#### String Enums
Enums can also contain strings. This is more common than numeric enums, because of their readability and intent.
```typescript
enum HttpStatusCode {
  200 = 'OK',
  400 = 'Bad Request',
  401 = 'Unauthorized',
  403 = 'Forbidden',
  404 = 'Not Found',
  500 = 'Internal Server Error',
}

function handleResponse(statusCode: HttpStatusCode): void {
  const statusText = HttpStatusCode[statusCode];
  console.log(`Status: ${statusCode} ${statusText}`);
}
```

In the above example, we define a function `handleResponse` that takes a `HttpStatusCode` parameter and uses it to log the corresponding status text.

### **Any type**
`any` is a type that disables type checking and effectively allows all types to be used.

**:::danger
`any` can be a useful way to get past errors since it disables type checking, but TypeScript will not be able provide type safety, and tools which rely on type data, such as auto completion, will not work. Remember, it should be avoided at "any" cost...
:::**
```typescript
let v: any = true;
v = "string"; // no error as it can be "any" type
Math.round(v); // no error as it can be "any" type
```

### void:
void type represents undefined as the value. the undefined value represents "no value".
```Typescript
let product:void=undefined;

function displayProductDetails():void{
console.log("Product category is Gadget");
}
```

### never :
Never can be used in functions that run in infinite loop or that in which the **end of function is not reached at all.**
```Typescript
function Demo(msg: string): never {  
throw new Error(msg);  
}  

function keepProcessing(): never { 
    while (true) { 
         console.log('I always does something and never ends.')
     }
}
```
In the above example, the `throwError()` function throws an error and `keepProcessing()` function is always executing and never reaches an end point because the while loop never ends. Thus, `never` type is used to indicate the value that will never occur or return from a function.

### **The `unknown` type**
`unknown` is the **type-safe counterpart of `any`.**

Anything is assignable to "unknown", but "unknown" is not assignable to anything but itself and `any` without a type assertion or a control flow based narrowing.

We can force the compiler to trust that an unknown variable has a specific type: const bar: string = foo as string. However, typecast might backfire us if we are not mindful using it.

```typescript
let userInput: unknown;
userInput = 5;
userInput = 'Ted';
```

### **Union Types**
Union types are used when a value can be **more than a single type**.
Such as when a property would be string or number. Also you **can accept as much as different types** as you want.

##### Union ```|``` (OR)
Using the ```|``` we are saying our parameter is a string or number:
```typescript
function printStatusCode(code: string | number) {
  console.log(`My status code is ${code}.`)
}
printStatusCode(404);
printStatusCode('404');
```

## Functions

|TypeScript|JavaScript|
|---|---|---|
|Types:|Supports|Do not support|
|Required and optional parameters:|Supports|All parameters are optional|
|Function overloading:|Supports|Do not support|
|Arrow functions:|Supports|Supported with ES2015|
|Default parameters:|Supports|Supported with ES2015|
|Rest parameters:|Supports|Supported with ES2015|

### Diff ways to write functions
```typescript
//way 1
function add(num1: number, num2: number, num3?: number): number {
  return num3 ? num1 + num2 + num3 : num1 + num2;
}

console.log(add(2, 3));
console.log(add(2, 3, 5));

// Way 2 AKA Arrow Function
const sub = (num1: number, num2: number, num3 = 10): number =>
  num1 - num2 - num3;

console.log(sub(2, 3));
console.log(sub(2, 3, 5));

// Way 3
const mult = function (num1: number, num2: number): number {
  return num1 * num2;
};


function add2(num1: number, num2: number, ...num3: number[]): number {
  return num1 + num2 + num3.reduce((a, b) => a + b, 0);
}

let numbers = [1, 2, 3, 4, 5];
console.log(add2(2, 3, 5, 6, 7, 8, 9, 10));

// Generics 
function getItems<Type>(items: Type[]): Type[] {
  return new Array<Type>().concat(items);
}  


let concatResult = getItems<number>([1, 2, 3, 4, 5]);
let concatString = getItems<string>(["a", "b", "c", "d", "e"]);
```
* `num3?: number` - `?` for **optional variable**
* `num3 = 10` - to assign default values. (**Default Value Parameter**)
* `...num3: number[]` - to pass multiple value (**Rest Parameter** should be declared as an array.)

### Scope of `this.`
```Typescript
class Product{
	productName:string="Mobile";
	getProductDetails():string{
	//this has Product class scope, so we can access productName
     return "Product: "+this.productName;
     }
     testThisFunction(){
     setTimeout(function(){
//this has current function scope as it is used within callback function.Hence we cant access productName declared within Product class.
//This will log in console as undefined output.
         console.log(this.productName);
         },100);
         }
         }   
```
**Arrow function lexically captures the meaning of this keyword.**
```Typescript
class Product{
	productName:string="Mobile";
	getProductDetails():string{
	//this has Product class scope, so we can access productName
     return "Product: "+this.productName;
     }
     testThisFunction(){
     setTimeout(
//this has Product class scope as it is defined using arrow function.Hence we can access productName declared within Product class.
     ()=>{console.log(this.productName);},100);
         }
         }   
```


### **Literal types**

**Both `var` and `let` allow for changing what is held inside the variable, and `const` does not.** This is reflected in how TypeScript creates types for literals

By themselves, literal types aren’t very valuable. It’s not much use to have a variable that can only have one value!

But by **combining literals into unions, you can express a much more useful concept** - for example, functions that only accept a certain set of known values:
```typescript
function printText(s: string, alignment: "left" | "right" | "center") {
  // do something
}
printText("Hello, world", "left");
printText("G'day, mate", "centre");
// Argument of type '"centre"' is not assignable to
// parameter of type '"left" | "right" | "center"'.
```

### **Type aliases**
**:::info
TypeScript allows types to be defined separately from the variables that use them.
Aliases and Interfaces allows types to be easily shared between different variables/objects.
:::**

***Type Aliases allow defining types with a custom name (an Alias).***

Type Aliases can be used for primitives like `string` or more complex types such as `objects` and `arrays`.

We’ve been using object types and union types by writing them directly in type annotations. This is convenient, but it’s common to want to use the same type more than once and refer to it by a single name.

**A type alias is exactly that - a name for any type.** The syntax for a type alias is:
```typescript
type Point = {
  x: number;
  y: number;
};
```

You can actually use a type alias to give a name to any type at all, not just an object type. For example, a type alias can name a union type:
```typescript
type ID = number | string;
```

Helps to simplify code and reuse types without repetition.
```typescript
type User = {
    name: string;
    age: number;
}
function greetUser(user: User) {
    console.log('Hi ' + user.name);
}
```

### **Function return types and "void"**
```typescript
function add(n1:number, n2:number) {
    return n1 + n2;
}

function printResult(num: number): void {
    console.log('Result' + num);
}

printResult(add(5, 7)) // 'Result: 12'
console.log(printResult(add(5, 7)) ) // undefined
```
**void represents the return value of functions which don’t return a value**

Whenever you see a function returning void, you are explicitly told there is no return value. All functions with no return value have an inferred return type of void. This should not be confused with a function returning undefined or null .

## Class

### Creating a first class

TypeScript offers full support for the `class` keyword introduced in ES2015.
**Note: The class can have only one constructor, Overloading Not Allowed**
```typescript
// index.ts
class Department {
    name: string;
    
    constructor(n: string) {
        this.name = n;
    }
}
const accounting = New Department('Accounting');
console.log(accounting);
```

The compilation to Javascript of such a `class` depends on the `"target"` we specify. For example if we compile it to **ES5** which didn't support modern JS *(as classes only available since ES6)*, after compilation this code would be a constructor function with the Department object.
```typescript
// index.js - Compiled code
"use strict";
var Department =(function () {
    function Department(n) {
        this.name = n;
    }
    return Department;
}());
var accounting = new Department('Accounting');
console.log(accounting);
```

### `public`, `protected` and `private` modifiers

:::info
You can also use `private` for **variables** and **methods**.
:::
```typescript
class Department {
    public name: string; // visible to all
    
    private employees: string[] = [];
    #salary: number; // alternative for private syntax Prefered way
    
    protected joblevel: number; // visible for child class
    
    constructor(n: string) {
        this.name = n;
    }

    describe(this: Department) {
        console.log('Department: ' + this.name);
    }

    addEmployee(employee: string) {
        this.employees.push(employee)
    }

	getSalary(){
	console.log(`Salary - ${#salary}`);
	}
}

accounting.name = 'New name'; // won't give an error as the 'name' variable is public and reachable outside of the class declaration

accounting.employees[0] = 'Anna' // will give an error as it has a private class and cannot modify outside of the class
```
:::warning
`public` properties are accessible from outside of the class, however `public` modifier is the **default** behaviour, so we don't need to explicitly declare on every variables.
:::


### Shorthand initialization

We can refactor the constructor and variable declaration easily, so we can get rid off declaring our variables twice.

```typescript
class Department {
    name: string;
    private id: string;
    
    constructor(n: string, id: string) {
        this.name = n;
        this.id = id;
    }
}
```

*After refactor:*
```typescript
class Department {
    
    constructor(private name: string, public id: string) { 
    }
}
```
:::warning
Make sure when declaring props this way, **you must use the modifiers before the variable name.**
:::

### `readonly` properties

It's a TypeScript feature that enables us to set a variable with it to be **only read**, without any modification **inside and outside its class**.

```typescript
class Department {
    name: string;
    private readonly id: string;
    
    constructor(n: string, id: string) {
        this.name = n;
        this.id = id;
    }
}

class Student { 
	readonly Id: number; 
	Name: string; 
	constructor(code: number, name: string)     { 
		this.Id = code; 
		this.Name = name; 
	} 
} 
let student  = new Student(1, "Mike"); 
student.Id = 4; //Compiler Error 
student.Name = 'Bill';  


interface IStudent { 
	readonly Id: number; 
	Name: string; 	
} 
let studentObj:IStudent = { 
	Id:1, 
	Name:"Mark" 
}  
studentObj.Id = 9; // Compiler Error: Cannot change readonly 'Id' 

```
*Adds extra safety to certain properties only can be initialized once.*

### Statics
A static function can access only static variables and other static functions.
```Typescript
class Product{
	static productName:string="Mobile";
	
	static getProductDetails():string{
		return "Product Name is"+Product.productName;
	}
	getProduct():string{
		return "Product Name is"+Product.productName;
	}
}
Product.productName="Tablet";
console.log(Product.productName);
console.log(Product.getProductDetails());
```
:::warning
Static members can be only accessed in static methods.
:::

### Getter And Setters
```Typescript
let passcode = "secret passcode";  
class Product {
    private _productName: any;  
    get productName(): string {  
        return this._productName;  
    }  
    
    set productName(newName: boolean | string | number) {  
        if (passcode && passcode == "secret passcode") {  
            this._productName= newName;  
        }  
        else {  
            console.log("Error: Unauthorized update of employee!");  
        } 
    } 
}  

let product:Product = new Product(); 

product.productName = "Fridge";  
if (product.productName) {  
    console.log(product.productName);  
} 

product.productName = true;  
if (product.productName) {  
    console.log(product.productName);  
} 

product.productName = 123;  
if (product.productName) {  
    console.log(product.productName);  
} 

```

### Inheritance
```typescript
class ITDepartment extends Department {
    admins: string[];
    constructor(id: string) {
        super(id, admins: string[]);
        this.admins = admins;
    }
}

class Product{
     protected productId:number;
     
     constructor(productId:number){
     this.productId=productId;
     }
     
     getProduct():void{
    }
}
// Override the superclass methods inside the subclass by specifying the same function signature.
class Gadget extends Product{
    getProduct():void{
      super.getProduct();
    }
}

```

* We **cannot inherit from multiple class**. 
* Also if we **don't specify the constructor** of our sub-class, i**t will inherit from its parent** class automatically.
* After specifying a constructor, we must call the `super()` method with the properties of the base class. Only after that we can use `this`.

### Abstract classes

An abstract class is typically used to define common behaviors for derived classes to extend. Unlike a regular class, **an abstract class cannot be instantiated directly.**

To declare an abstract class, you use the `abstract` keyword.

* An **abstract method does not contain implementation**. 
* An Abstract class **has at least one abstract method.**
* It **only defines the signature of the method without including the method body**. 
* An abstract **method must be implemented in the derived class**.

The following shows the Employee abstract class that has the `getSalary()` abstract method:

```typescript
abstract class Employee {
    constructor(private firstName: string, private lastName: string) {
    }
    abstract getSalary(): number
    get fullName(): string {
        return `${this.firstName} ${this.lastName}`;
    }
    compensationStatement(): string {
        return `${this.fullName} makes ${this.getSalary()} a month.`;
    }
}
```
The `getSalary()` method is an abstract method. The derived class will implement the logic based on the type of employee.

TypeScript allows you to specify an abstract modifier on constructor signatures as shown below. 
```Typescript
interface Mobile { 
getResolution(): number; 
} 
let Ctor: abstract new () => Mobile = Gadget;
```
Thus, you can have abstract constructors. It signals that there’s no intent to run the constructor directly, so it’s safe to pass.

## Interface

We can use it to describe how an object should look like.
* An interface should have properties and method declarations.
* Properties or methods in an interface should not have any access modifiers.
* Properties cannot be initialized in a TypeScript interface.
```typescript
interface Person {
    name: string;
    age?: number; // to make it optional
    greet(phrase:string): void;
    
	//Function declared using arrow function
	displayProductName:{productId:number)=>string;
}

let user1: Person;

user1 = {
    name: 'Benji',
    age: 30,
    greet(phrase: string) {
        console.log(phrase + ' ' + this.name);
    }
}

user1.greet("Shakalaca - I'm")
```
**An `interface` cannot have an initializer, so we cannot give value to its properties.**

### Duck Typing
```Typescript
interface Product{
	productId:number;
	productName:string;
}
function getProductDetails(productobj:Product):string{
}

//Incorrect way of using the interface duck type
let prodObject={productName:'Mobile',productCategory:'Gadget'};

//Correct way of using the interface duck type by adding an additional property productCategory to demonstrate DuckTyping
let prodObject = {productId:1001,productName:'Mobile',productCategory:'Gadget'};
getProductDetails(prodObject);
```

### Extending Interfaces
An interface can be extended from an already existing one using the extends keyword.
```Typescript
interface Category{
	categoryName:string;
}
interface Product{
	productName:string;
	productId:number;
}
interface productList extends Category, Product{
	list:Array<string>;
}
let productDetails:productList={
	categoryName :'Gadget',
	productName:'Mobile',
	productId:1234,
	list:['Samsung','Motorola','LG']
}
```

### Interfaces as function types

As functions are objects in the end. We can use interface to define a function, just as types can define a function.
However **its syntax is different** from a function type.
```typescript
// type AddFn = (a: number, b: number) => number;

interface AddFn {
    (a: number, b: number): number;
}

let add: AddFn;

add = (n1: number, n2: number) => {
    return n1 + n2;
};
```

```typescript
// type AddFn = (a: number, b: number) => number;

interface Math {
    add(a: number, b: number): number;
}

class Add extends Math{
	add(a: number, b: number): number{
		//impl add
	}
}
```

### Interfaces as class types

```Typescript
interface Product{
	getProductDetails(productId:number):string;
	displayProductName:(productId:number)=>string;
}

class Gadget implements Product{
	getProductDetails():string[]{
	}
	displayProductName(productId:number):string{
	}
}

var g:Product=new Gadget();
```

### Mixins:

**Inheriting or extending from more than a class at a single time is not allowed in TypeScript.**
```Typescript
class Mobile{ 
  color(name: string) { 
    console.log(`${name}`); 
  } 
}; 
 
class TV{ 
  resolution(name: string) { 
    console.log(`${name}`); 
  } 
}; 

class Gadget extends Mobile, TV{}; // Error
```

```Typescript
interface Mobile { 
  color: string; 
  wifi: string; 
} 
 
interface TV { 
  resolution: string; 
} 

class Gadget{} 
interface Gadget extends Mobile, TV{} 

```
The Gadget class is merged with the Gadget interface, thus the Gadget class will contain the methods of both the Mobile and the TV classes.

### Declaration Merging

Two or more separate declarations with the same definition can be merged. This is called Declaration Merging.
```Typescript
interface Phone { 
  color: string; 
  vendor: string; 
} 
interface Phone { 
  price: number; 
} 
let phone: Phone = { color: “Black”, vendor: “Samsung”, price: 10000 }; 
```

Whenever you implement this concept N**on-function members** of interfaces **should be unique** or else compile-time error would be populated automatically. Whereas **Function** members are considered to be **overloads**.
```Typescript
interface Gadget { 
  clone(mobile: Mobile): Mobile;
} 
interface Gadget { 
  clone(tv: TV): TV; 
} 
interface Cloner { 
  clone(mobile: Samsung): Samsung;
  clone(mobile: Redmi): Redmi; 
} 
```
The three interfaces will merge to create a single declaration as below:
```Typescript
interface Cloner { 
  clone(mobile: Samsung): Samsung;
  clone(mobile: Redmi): Redmi; 
  clone(tv: TV): TV;
  clone(mobile: Mobile): Mobile; 
} 
```

## Import and Export between modules

```typescript
export interface User {
  name: string;
  age?: number;
  id: number;
  email: string;
}

export interface Login {
  login(): User;
}

import { Login, User } from "./interface"; // {Class, Class} from "relative_path"

// import * as UserLogin from './interface'; // Not prefered way

```

## Object Destructing

```typescript
let { name: userName, email: userLogin }: User = {
  name: "John",
  id: 1,
  email: "",
};

let a: string = userLogin; // equivalent to user.email
```

## Array Destructing

```typescript
let [user1, user2, ...restUsers]: User[] = [
  { name: "John", id: 1, email: "" },
  { name: "John1", id: 2, email: "" },
  { name: "John2", id: 3, email: "" },
  { name: "John3", id: 4, email: "" },
];


console.log(user1);
console.log(user2);
console.log(restUsers);

let result = restUsers.filter(user => user.id > 3);

```

## Namespace

* A Namespace is used to group functions, classes, or interfaces under a common name. 
* The content of namespaces is hidden by default unless they are exported.
* Use nested namespaces if required.
* The function or any construct which is not exported cannot be accessible outside the namespace.
```Typescript
namespace namespacename{
 export namespace namespacename{
    export function functionname{
    }
  }
  export function functionname{
  }
}
```

## Generics

* Generics is a concept using which you can make the same code work for multiple types.
* It accepts type parameters for each invocation of a function or a class or an interface so that the same code can be used for multiple types.
* Type Parameters are used to specify the type, a generic will work over.
* They are listed using an angle bracket<>.
```Typescript
function printData<T>(data:T):T{
	return data;
}
```

```Typescript
// Generic Array
function functionname<T> arg:Array<T>):Array<T>{}

// Generic Functions
function functionname<T>(arg:t):T

// Generic Interface
interface InterfaceName<T>{
        functionname(arg T):T;
        variablename:T;
        }

// Generic Class
class className<T>{
	functionname(arg T):T;
	variablename:T;
}

```

### Generic Constraint
* Generic constraints are used to add constraints to the generic type.
* Generic constraints are added using the 'extends' keyword.

```Typescript
 class classname<T extends constraint type>{
	 functionname (arg T):T;
	 variablename:T;
 }
```

## Decorators

Decorator is a design pattern that allows you to attach additional responsibilities to an object dynamically. Decorators are a form of meta-programming, as they allow you to modify the behavior of a class or object at runtime.

The decorator is applied to the class using the `@` syntax. Decorators can be used to add functionality to classes and objects in a flexible and reusable way.
```typescript
// usually decorators start with a capital char, not obligatory, but good practice
function Logger(target: Function) {
    console.log('Logging...');
    console.log(target);
}

@Logger
class Person {
    name = 'Benji';
    
    constructor() {
        console.log('Creating person...');
    }
}

const pers = new Person()
console.log(pers);
```

A Method Decorator are declared before needed method declaration. They are used to modify, observe, or replace a method definition.

The decorator logic is applied to the Property Descriptor attribute of the respective method.
The method decorator function will be invoked at runtime with the below three arguments:

- **target** - Either the constructor function of the class for a static member or the prototype of the class for an instance member
- **Key** - the name of the decorated method
- **descriptor** - Property Descriptor value of the method.

```Typescript
function methoddecoratorname(){
	return function(target, propertyKey:string, descriptor:PropertyDescriptor){
	...
	}
}
class Product{
	@methoddecorstorname()
	functionname(arg:type){
	...
	}
}
```
## Type Gaurds

Type guards are typically used for narrowing a type and are quite similar to feature detection, allowing you to detect the correct methods, prototypes, and properties of a value. Therefore, you can easily figure out how to handle that value.

*There are five major ways to use a type guard:*

- The `instanceof` keyword
- The `keyof`
- The `typeof` keyword
- The `in` keyword
- Equality narrowing type guard
- Custom type guard with predicate

:::warning
If you want to expand more on type guards, I suggest you to have a look on [this comprehensive article](https://blog.logrocket.com/how-to-use-type-guards-typescript/) from ***Oyinkansola Awosan***!
:::

```typescript
function add(a: Combinable, b: Combinable) {
    if (typeof a === 'string' || typeof b === 'string') {
        return a.toString() + b.toString();
    }
    return a + b;
}

type UnknownEmpoloyee = Employee | Admin;

function printEmployeeInformation(emp: UnknownEmployee) {
    console.log('Name: ' + emp.name);
    if ('priviledges' in emp) {
        console.log('Priviledges: ' + emp.priviledges);
    }
    if ('startDate' in emp) {
        console.log('Start date: ' + emp.startDate);
    }
}
```

```typescript
class Car {
    drive(){
        console.log('🚗 Driving...');
    }
}

class Truck {
    drive(){
        console.log('🚛 driving...');
    }
    loadCargo(amount: number){
        console.log('Loading cargo...' + amount);
    }
}

type Vehicle = Car | Truck;

const v1 = new Car();
const v2 = new Truck();

function useVehicle(vehicle: Vehicle) {
    vehicle.drive();
    if(vehicle instanceof Truck) {
        vehicle.loadCargo(1000)
    }
}

useVehicle(v1);
```

:::danger
We cannot use `instanceof` when we are working with interfaces, as they are not compiled to javascript. So at runtime it won't be available as a constructor function.
:::