https://github.com/sudheerj/angular-interview-questions?tab=readme-ov-file#what-is-angular-framework


## Reusable Angular components

- ng-template
- ng-content

|   |   |
|---|---|
|**ng-content**|**ng-template**|
|The projected content lifecycle is bound to the parent lifecycle|Passed templates lifecycle are not bound to the parent lifecycle hook|
|Use ng-content for most scenarios|Use whenever you encounter ng-content inside ngIf|

## SCSS VS CSS
| Parameters             | SCSS                                                                                                                                          | CSS                                                                                        |
| ---------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| Meaning and Definition | SCSS is a more evolved and advanced form of the CSS language. It is a preprocessor language, and we need to compile it into the CSS language. | CSS is a scripting language used for styling various web pages.                            |
| Lines of Code          | It uses fewer lines of code than CSS.                                                                                                         | CSS makes extensive use of lines of code.                                                  |
| Functions              | It consists of advanced functions.                                                                                                            | It consists of basic functions.                                                            |
| Rules for nesting      | SCSS supports the nesting rules.                                                                                                              | The standard CSS does not assign various nested rules.                                     |
| Design                 | SCSS is a special file in a SASS program that we need to write in the Ruby language.                                                          | CSS is a styling language that enables us to develop, design, and style various web pages. |
| Language               | We use the SCSS in the Ruby language.                                                                                                         | We commonly use CSS in JavaScript and HTML languages.                                      |
| Syntax                 | Follows a more structured syntax with additional features.                                                                                    | Follows a plain-text syntax.                                                               |
| Mixins                 | Allows you to create reusable code snippets.                                                                                                  | Does not provide this functionality.                                                       |
| File Extension         | Uses the .scss file extension.                                                                                                                | Uses the .css file extension.                                                              |
| Compilation            | SCSS files must be preprocessed into standard CSS files.                                                                                      | CSS files are interpreted by web browsers directly.                                        |
| Advanced Features      | SCSS contains all features of CSS and more, making it a preferable choice for developers.                                                     | Lacks many of the advanced features present in SCSS.                                       |
| Variables              | Offers the use of variables to shorten and simplify code.                                                                                     | Does not allow the use of variables.                                                       |

## authentication in app- explain login process, encryption algorithm 
 
 **Example JWT Flow Diagram**
```txt
[Angular Login Form] --> (POST /auth/login) --> [Spring Boot Auth API]      --> Verify credentials & hash match --> Generate JWT      --> Send JWT to Angular --> Store token      --> Attach token to future requests --> Backend validates
```

Authentication systems use different cryptographic approaches:

|Purpose|Algorithm|Notes|
|---|---|---|
|Password hashing|**BCrypt**, **Argon2**, **PBKDF2**|One-way hash; prevents reversing to plain text|
|Token signing|**HMAC-SHA256** (HS256), **RSA**|JWT signature to prevent tampering|
|Transport security|**TLS/HTTPS**|Encrypts entire communication between Angular & backend|

**Key security points:**

- **Passwords are never stored in plain text** — always hashed with salt (BCrypt automatically salts).
- JWTs must be signed and optionally encrypted.
- Use HTTPS to prevent Man-in-the-Middle attacks.

```ts
localStorage.setItem('token', response.token);

headers: { Authorization: `Bearer ${token}` }
```
## authorization in app- prevent user using Auth guards

SEE `02 Routes` in Angular notes

## **responsive design with Angular:**

- **use the Angular BreakpointObserver to detect the size of the current device**
- **set member variables in your component that allow you to show or hide certain elements depending on the screen size**
- **Set responsive CSS classes in your component like `is-phone-portrait` depending on the screen size**
- **Use these special classes to make the CSS of your component responsive without writing media queries**

## Forms

SEE `05 Forms in Angular notes

## Router Router Gaurds, Intreceptors

SEE `02 Routes and 04 HTTP` in Angular notes
## Component comunication

|Scenario|Method|Recommended?|
|---|---|---|
|Parent → Child|`@Input()`|✅ Yes|
|Child → Parent|`@Output()` + EventEmitter|✅ Yes|
|Sibling → Sibling|Shared Service + Subject|✅ Yes|
|Unrelated Components|Service / NgRx Store|✅ Yes|
|Direct Parent ↔ Child Access|`@ViewChild()`|⚠ Use rarely|
|Across Routes|Route Params / Query Params|✅ Yes|

## Rxjs different type of subjects, mergeMap, forkjoin, observables, 

|Operator/Concept|Key Point|
|---|---|
|**Subject**|Observable + Observer|
|**BehaviorSubject**|Has latest value for new subscribers|
|**ReplaySubject**|Replays N past values|
|**AsyncSubject**|Emits last value upon completion|
|**mergeMap**|Flattens & merges async streams in parallel|
|**forkJoin**|Waits for all async calls, emits once|
|**Observable**|Core async primitive in RxJS|

## Event Bubbling and event capturing
- event bubbling is the propogation of event from the child element to its parent element. To stop this propogation we use event.stopPropogation()

## Lazy loading

- **Lazy loading** means loading feature modules **only when they are needed** (on demand), instead of loading everything at app start.
- This reduces **initial bundle size** and improves **app startup performance**.

## ES6 Features
- Arrow Function
- unctions can now be given an indefinite number of arguments as an array, called the [rest function parameters syntax](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/rest_parameters):
		 var func = (a, b, c, ...rest) => { }
- let and const
The `let` command is a replacement for `var` that specifically grants block scope.

## Normal function vs Arrow function

| Feature                | Normal Function                | Arrow Function                           |
| ---------------------- | ------------------------------ | ---------------------------------------- |
| Syntax                 | `function name() {}`           | `() => {}`                               |
| `this`                 | Dynamic                        | Lexical (inherited)                      |
| `arguments`            | Yes                            | No                                       |
| `new` Constructor      | Yes                            | No                                       |
| Method binding needed? | Yes, often with `.bind()`      | No                                       |
| Use case               | Object methods, dynamic `this` | Callbacks, event handlers, array methods |

```ts
class Person {
  name = 'John';

  normalFunc() {
    console.log(this.name);
  }

  arrowFunc = () => {
    console.log(this.name);
  }
}

const p = new Person();
p.normalFunc(); // "John"

const normal = p.normalFunc;
normal(); // undefined (this lost)

const arrow = p.arrowFunc;
arrow(); // "John" (this is bound to Person)

----------

function normalFunc() {
  console.log(arguments); // works
}

const arrowFunc = () => {
  console.log(arguments); // ReferenceError
}

----------

function PersonFn(name) { this.name = name; }
const p = new PersonFn('John'); // works

const PersonArrow = (name) => { this.name = name; }
const p2 = new PersonArrow('John'); // TypeError
```
## Angular bootstrapping process

``` txt
index.html (contains <app-root>)
   ↓
main.ts → platformBrowserDynamic().bootstrapModule(AppModule)
   ↓
AppModule → @NgModule({ bootstrap: [AppComponent] })
   ↓
Angular Injector created
   ↓
AppComponent replaces <app-root> in DOM
   ↓
Change detection starts → App runs

```

## List of lifecycle hooks

SEE `01 Angular Lifecycle hooks` in Angular notes

## What is a strict operator and rest operator

## **1. Strict Operator**

In JavaScript/TypeScript, this often means **strict equality (`===`)** and **strict inequality (`!==`)**.

- **Strict Equality (`===`)**  
    Compares **value and type**. No type coercion happens.
    
```ts
5 === "5" // false (number vs string) 5 === 5   // true
```
- **Strict Inequality (`!==`)**  
    Checks if **value or type differ**.
    
```ts
5 !== "5" // true 5 !== 5   // false
```
  
📌 Compared to **loose equality (`==`)**, strict operators avoid unexpected conversions:

```ts
0 == false  // true   (coerces to same type) 0 === false // false  (different types)
```

---

## **2. Rest Operator (`...`)**

The **rest operator** in ES6 syntax collects multiple arguments or properties into a **single array or object**.

### **A. In Function Parameters**

```ts
function sum(...numbers: number[]) {   return numbers.reduce((total, n) => total + n, 0); } sum(1, 2, 3, 4); // 10
```

### **B. In Destructuring**

```ts
const [first, ...rest] = [10, 20, 30, 40]; console.log(first); // 10 console.log(rest);  // [20, 30, 40]
```

### **C. With Objects**
```ts
const user = { name: "Alex", age: 25, city: "London" }; const { name, ...details } = user; console.log(details); // { age: 25, city: "London" }
```

---

✅ **Quick Summary Table**

|Operator|Purpose|Example|
|---|---|---|
|`===` / `!==` (Strict)|Compare value + type|`5 === '5' // false`|
|`...` (Rest)|Gather multiple items into one|`function f(...args) {}`|