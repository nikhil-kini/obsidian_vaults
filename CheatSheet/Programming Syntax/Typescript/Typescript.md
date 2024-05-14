
## Initial setup

* Install [[00 Node Commands]]
* `npm init` to create a package.json
* `npm i typescript` 
* `tsc --init` to initialize Typescript

___

### Installing and compiling TypeScript

In order for you to run TypeScript and compile it on your local machine, make sure you have Node and npm installed to be able to install it through CLI.
```typescript
// Install TypeScipt globally (use Sudo if needed)
npm install -g typescript
```

To be able to run your TS file in the browers, first you need to compile it to plain JS, which you can do with TS itself and the following command. Makes sure to navigate into the folder where you TS file is saved.
```typescript
// Compile TS file to JS
tsc [nameOfFile].ts
```

```sh
# To complie JS file via Node
node js_fileName
```
---

## The TypeScript Compiler (and its Configuration)

### Using 'Watch Mode'

It's useful so we don't have to manually recompile every-time we change something in our code. Watch mode will take care it for us, and recompile our file on the fly, so we can see the live changes in the browser.

To initiate 'watch mode', instead of `tsc app.ts` in the terminal, we can write:

```bash
tsc app.ts --watch
# OR
tsc app.ts -w
```

You can quit watch mode with `Command+C` (or `Ctrl+C` on Windows).

### Compiling the entire project/multiple files

We can tell TS to compile our whole project and recompile it every time we change something anywhere inside any files.

*You only have to make sure, you **run** this command **in the root directory** of your project, so TS will know where to look for them.*

```bash
tsc --init
```

This will create your `tsconfing.json` file, where you can specify a bunch of different things about how TS should handle your project. However, without changing anything on your `tsconfig`, you will be able to compile all your `.ts` extension files in your project with 

```bash
tsc
```

This you can use it together with the above mentioned 'watch mode', so you can see any changes in any files automatically. So you don't need to recompile every time.

```bash
tsc --watch
```

### Including and excluding files

In the `tsconfig.json` file we can specify how we would like to set up the compiler, and also can add or remove files from compilation. 

#### Let's see an example below

What I really like about this approach is that it includes all of the possible values and their reasoning. I know that there is the [docs](https://www.typescriptlang.org/docs/) with all of this information, but how convinient is just read it in the codebase without jumping through apps. 

```typescript
{
  "compilerOptions": {
    /* Basic Options */
      
    // "incremental": true,                   /* Enable incremental compilation */
    "target": "es5" /* Specify ECMAScript target version: 'ES3' (default), 'ES5', 'ES2015', 'ES2016', 'ES2017', 'ES2018', 'ES2019', 'ES2020', or 'ESNEXT'. */,
    "module": "commonjs" /* Specify module code generation: 'none', 'commonjs', 'amd', 'system', 'umd', 'es2015', 'es2020', or 'ESNext'. */,
    // "lib": [],                             /* Specify library files to be included in the compilation. */
    // "allowJs": true,                       /* Allow javascript files to be compiled. */
    // "checkJs": true,                       /* Report errors in .js files. */
    // "jsx": "preserve",                     /* Specify JSX code generation: 'preserve', 'react-native', or 'react'. */
    // "declaration": true,                   /* Generates corresponding '.d.ts' file. */
    // "declarationMap": true,                /* Generates a sourcemap for each corresponding '.d.ts' file. */
    // "sourceMap": true,                     /* Generates corresponding '.map' file. */
    // "outFile": "./",                       /* Concatenate and emit output to single file. */
    "outDir": "./build" /* Redirect output structure to the directory. */,
    // "rootDir": "./",                       /* Specify the root directory of input files. Use to control the output directory structure with --outDir. */
    // "composite": true,                     /* Enable project compilation */
    // "tsBuildInfoFile": "./",               /* Specify file to store incremental compilation information */
    // "removeComments": true,                /* Do not emit comments to output. */
    // "noEmit": true,                        /* Do not emit outputs. */
    // "importHelpers": true,                 /* Import emit helpers from 'tslib'. */
    // "downlevelIteration": true,            /* Provide full support for iterables in 'for-of', spread, and destructuring when targeting 'ES5' or 'ES3'. */
    // "isolatedModules": true,               /* Transpile each file as a separate module (similar to 'ts.transpileModule'). */
        
    /* Strict Type-Checking Options */
        
    "strict": true /* Enable all strict type-checking options. */,
    // "noImplicitAny": true,                 /* Raise error on expressions and declarations with an implied 'any' type. */
    // "strictNullChecks": true,              /* Enable strict null checks. */
    // "strictFunctionTypes": true,           /* Enable strict checking of function types. */
    // "strictBindCallApply": true,           /* Enable strict 'bind', 'call', and 'apply' methods on functions. */
    // "strictPropertyInitialization": true,  /* Enable strict checking of property initialization in classes. */
    // "noImplicitThis": true,                /* Raise error on 'this' expressions with an implied 'any' type. */
    // "alwaysStrict": true,                  /* Parse in strict mode and emit "use strict" for each source file. */
    
    /* Additional Checks */
        
    // "noUnusedLocals": true,                /* Report errors on unused locals. */
    // "noUnusedParameters": true,            /* Report errors on unused parameters. */
    // "noImplicitReturns": true,             /* Report error when not all code paths in function return a value. */
    // "noFallthroughCasesInSwitch": true,    /* Report errors for fallthrough cases in switch statement. */
    
    /* Module Resolution Options */
        
    // "moduleResolution": "node",            /* Specify module resolution strategy: 'node' (Node.js) or 'classic' (TypeScript pre-1.6). */
    // "baseUrl": "./",                       /* Base directory to resolve non-absolute module names. */
    // "paths": {},                           /* A series of entries which re-map imports to lookup locations relative to the 'baseUrl'. */
    // "rootDirs": [],                        /* List of root folders whose combined content represents the structure of the project at runtime. */
    // "typeRoots": [],                       /* List of folders to include type definitions from. */
    // "types": [],                           /* Type declaration files to be included in compilation. */
    // "allowSyntheticDefaultImports": true,  /* Allow default imports from modules with no default export. This does not affect code emit, just typechecking. */
    "esModuleInterop": true /* Enables emit interoperability between CommonJS and ES Modules via creation of namespace objects for all imports. Implies 'allowSyntheticDefaultImports'. */,
    // "preserveSymlinks": true,              /* Do not resolve the real path of symlinks. */
    // "allowUmdGlobalAccess": true,          /* Allow accessing UMD globals from modules. */
        
    /* Source Map Options */
        
    // "sourceRoot": "",                      /* Specify the location where debugger should locate TypeScript files instead of source locations. */
    // "mapRoot": "",                         /* Specify the location where debugger should locate map files instead of generated locations. */
    // "inlineSourceMap": true,               /* Emit a single file with source maps instead of having a separate file. */
    // "inlineSources": true,                 /* Emit the source alongside the sourcemaps within a single file; requires '--inlineSourceMap' or '--sourceMap' to be set. */
    
    /* Experimental Options */
        
    // "experimentalDecorators": true,        /* Enables experimental support for ES7 decorators. */
    // "emitDecoratorMetadata": true,         /* Enables experimental support for emitting type metadata for decorators. */
        
    /* Advanced Options */
        
    "skipLibCheck": true /* Skip type checking of declaration files. */,
    "forceConsistentCasingInFileNames": true /* Disallow inconsistently-cased references to the same file. */,
    "resolveJsonModule": true
  },
      
  /*  <<< Here we can either include, or exclude files as an array >>>*/
      
    "include": ["./src/index.ts"],
    "exclude": [
        "node_modules" // node_modules is ignored by default
    ],
}
```

We can also use these paths as a wildcard with the asterisks (\*)

```typescript
"exclude": [
    "*.test.ts" // all files with '.test' pattern will be ignored
    "**/*.dev.ts" // any files with '.dev' pattern in any folder will be ignored
],
```

### Setting a compilation target

Modern browsers support all JS ES6 features, so ES6 is a good choice. You might choose to set a lower target if your code is deployed to older environments, or a higher target if your code is guaranteed to run in newer environments.

```typescript
{
    "target" : "es6",
}
```

The target setting changes which JS features are down leveled and which are left intact. For example, an arrow function () => this will be turned into an equivalent function expression if target is ES5 or lower.


### Understanding TypeScript Core Libs

```typescript
{
    // "lib": [], /* Specify library files to be included in the compilation. */
}
```

TypeScript includes a default set of type definitions for built-in JS APIs (like `Math`), as well as type definitions for things found in browser environments (like `document`). TypeScript also includes APIs for newer JS features matching the target you specify; for example the definition for `Map` is available if target is ES6 or newer.

:::info
**Default** settings available when it's commented out. Once we specify the empty array of `"lib": []`, we tell TS to only include whatever we specify.
```typescript
{
    // Exact default settings while commented out, all core JavaScript feature
    "lib": [
        "dom",
        "es6",
        "dom.iterable",
        "scripthost"
    ],
}
```
:::

*You may want to change these for a few reasons:*

* Your program doesn’t run in a browser, so you don’t want the DOM type definitions for example a NodeJS app
* Your runtime platform provides certain JavaScript API objects (maybe through polyfills), but doesn’t yet support the full syntax of a given ECMAScript version
* You have polyfills or native implementations for some, but not all, of a higher level ECMAScript version

### Working with Source Maps

Source maps help us debugging and development. When we enable it, it will generate a `.js.map` file which then act as a bridge between our JS compiled file and the TS file, which then the browser will understand.
Then in the Source Tab on our DevTools, we have both `.js`, `.ts` file and we can debug straight our `.ts` file, which is super convenient.

```typescript
{
    "sourceMap" : true,  /* Generates corresponding '.map' file. */
}
```

### `rootDir` and `outDir`

`outDir` handles where the compiled `.js` files will be placed. In default, will create them next to their `.ts` file, however if we would like a better folder structure, we can specify a folder, and when we compile our TS files, the folder will be created with all the compiled JS files.
If you keep your TS files in separate folders, the created compiled folder will follow the same structure.

```typescript
{
    "outDir": "./dist" /* Redirect output structure to the directory. */,
}
```

`rootDir` behaves similar to `include`, however if we specify the folder we want the compiler to run. It will not look for other `.ts` files outside of that folder.


```typescript
{
    "rootDir": "./src", /* Specify the root directory of input files. Use to control the output directory structure with --outDir. */
}
```

### Stop emitting files on compilation errors

```typescript
{
    "noEmitOnError": false, /* Default: false */
}
```

When it's `false`, the `.js` files will be generated even when an error occur. However in a project we would like to make sure there is no error in our application by setting it to `true`. 
Then when there is an error, won't compile any files to javascript.

### Code quality options

```typescript
{
    /* Additional Checks */
        
    // "noUnusedLocals": true,                /* Report errors on unused locals. */
    // "noUnusedParameters": true,            /* Report errors on unused parameters. */
    // "noImplicitReturns": true,             /* Report error when not all code paths in function return a value. */
    // "noFallthroughCasesInSwitch": true,    /* Report errors for fallthrough cases in switch statement. */
}
```

`"noUnusedLocals": true` will give us a warning/error whenever we declare a variable and not using it.

` "noImplicitReturns": true` will give a warning when a function has a code path where not all return a value. So we implicitly has to return nothing, or fix our code if that wasn't our planned outcome.


## Advanced Types

### Intersection types

With intersection types we can combine multiple and any kind of types, similar to the `interface ... extends ...`. 

An intersection type creates a new type by combining multiple existing types. The new type has all features of the existing types. The type order is not important when you combine types.

You can use it with object types as the example below, or with union types as well.

```typescript
type Admin = {
    name: string;
    priviledges: string[];
};

type Employee = {
    name: string;
    startDate: Date;
};

type ElevatedEmployee = Admin & Employee;

const emp1: ElevatedEmployee = {
    name: 'Benji',
    priviledges: ['create-server'],
    startDate: new Date(),
}

type Combinable = string | number;
type Numeric = number | boolean;

type Universal = Combinable & Numeric;
```

### More on Type Guards

TypeScript uses some built-in JavaScript operators like `typeof`, `instanceof`, and the `in` operator, which is used to determine if an object contains a property. Type guards enable you to instruct the TypeScript compiler to infer a specific type for a variable in a particular context, ensuring that the type of an argument is what you say it is.

Type guards are typically used for narrowing a type and are quite similar to feature detection, allowing you to detect the correct methods, prototypes, and properties of a value. Therefore, you can easily figure out how to handle that value.

*There are five major ways to use a type guard:*

- The `instanceof` keyword
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

### Discriminated Unions

It's a pattern that makes implementing type guards easier. If you have a class with a literal member then you can use that property to discriminate between union members.

To convert a union type into a discriminated union type, we use a common property across our types *(in our case I gave a prop name: `kind`)*. This property can be any name and will serve as an ID for the different types. Every type will have a different literal type for that property.

```typescript
interface Bird {
    kind: 'bird';         // literal type
    flyingSpeed: number;
}

interface Horse {
    kind: 'horse';
    runningSpeed: number;
}

type Animal = Bird | Horse;

function moveAnimal(animal: Animal) {
    let speed;
    switch (animal.kind) {
        case 'bird':
            speed = animal.flyingSpeed;
            break;
        case 'horse':
            speed = animal.runningSpeed;
    }
    console.log('Moving at speed: ' + speed);
}

moveAnimal({kind: 'bird', flyingSpeed: 67});
```

### Type casting

Helps you tell Typescript that some value is of a specific type where TypeScript is not able to detect it on it's own, but you as a developer know that it will be the case.

There is 2 ways to use type casting, and both are the same with different syntax.

```typescript
// wrong typing, not specific enough to get the value from the input
const userInputElement = document.getElementById('user-input'); // TS only know that this is an HTMLElement, doesn't recognise what type of element exacly

// one way to get this to work
const userInputElement = <HTMLInputElement>document.getElementById('user-input')!; // here TS will know the exact type, therefore we can get the value

// another way
const userInputElement = document.getElementById('user-input')! as HTMLInputElement;

userInputElement.value = 'Hi there!';
```

The exclamation mark allows us to tell TypeScript that the expression in front of it will never yield null.

### Index properties

It is a feature that allows us to create objects which are more flexible regarding the properties they might hold.

So long story short, I need an object where I'm pretty clear regarding the value type. It should be a `string`, but where I don't advance how many properties I'll have and which name the properties will have. For such a scenario we can use **index types**.


```typescript
interface ErrorContainer {
    // id: string;   VALID
    [prop: string]: string;
}

const errorMsg: ErrorContainer = {
    email: 'Not valid email!';
    // 1: 'Not valid email!'; although the prop is a type of number, it's still valid, as number can be converted to a string, however it wouldn't work the other
    name: 'Must start with a capital characther!';
}
```

We can also add predefined types to the same interface, however they must be the same type. So for example `id` can be a `string`, as the index type also a `string`, but **cannot** be a `number`. This is all the restriction we have.

This feature gives us this extra flexibility that we don't need to know in advance which property names we want to use and how many properties we need.

***Another example***

```typescript
interface Dictionary {
  [key: string]: string | number | boolean;
}

const myDict: Dictionary = {
  name: 'Alice',
  age: 30,
  isAdmin: true,
};

console.log(myDict['name']);     // Output: 'Alice'
console.log(myDict['age']);      // Output: 30
console.log(myDict['isAdmin']);  // Output: true
```

In this example, we define an interface called `Dictionary` that has an index signature with a key of type `string` and a value that can be a `string`, a `number`, or a `boolean`. This allows us to create an object that can store key-value pairs of different types.

We then define an object called `myDict` that conforms to the `Dictionary` interface, and assign it a few key-value pairs of different types. We can access the values of the object using bracket notation and passing in the key as a `string`.

This kind of dictionary-like object can be useful in many scenarios where you need to store and access data in a flexible and dynamic way.

### Function overloads

A feature that allows us to define multiple function signatures, so to say, for one and the same function. Which simply means we can have multiple possible ways of calling a function with different parameters, to then do something inside of that function.

Function overloading in TypeScript lets you define functions that can be called in multiple ways.

Using function overloading requires defining the overload signatures: a set of functions with parameter and return types, but without a body. These signatures indicate how the function should be invoked.

Additionally, you have to write the proper implementation of the function (implementation signature): the parameter and return types, as well the function body. Note that the implementation signature is not callable.

Aside from regular functions, methods in classes can be overload too.

```typescript
type Combinable = string | number;

function add(a: Combinable, b: Combinable) {
    if (typeof a === 'string' || typeof b === 'string') {
        return a.toString() + b.toString();
    }
    return a + b;
}

const result = add('Benji' + ' Peto');
result.split(' '); // would throw an error, as TypeScript doesn't know which will be the return type exactly, and we cannot call .split() method on a number
```

We can add function overload signatures before the function, to tell TS what cases could handle to avoid this error.

```typescript
type Combinable = string | number;

function add(a: string, b: string): string; // implementation signature
function add(a: Combinable, b: Combinable) {
    if (typeof a === 'string' || typeof b === 'string') {
        return a.toString() + b.toString();
    }
    return a + b;
}

const result = add('Benji' + ' Peto');
result.split(' '); // would run properly
```

We could add as many of cases as we want.

### Optional chaining

In bigger more complex applications you certainly work with structured nested data where you don't know with certainty if some property on an object is set or if it's maybe undefined. For this reason we can use optional chaining (**?**) with the question mark character. So if the property doesn't exist, it won't throw a runtime error, and our app keeps running.

```typescript
const fetchedUserData = {
    id: 'u432',
    name: 'Capitan',
    /* job: {
        title: 'CEO',
        description: 'Dog runs this company',
    } */
}

console.log(fetchedUserData?.job?.title);
```

### Nullish coalescing

The nullish coalescing (**??**) operator is a logical operator that returns its right-hand side operand when its left-hand side operand is null or undefined, and otherwise returns its left-hand side operand.

```typescript
const userInput = undefined;

const storedData = userInput ?? 'DEFAULT';

console.log(storedData); // output: 'DEFAULT'
```

## Generics

### Built-in generics & what are generics?

TypeScript includes a number of built-in generic types that you can use in your code. These types are part of the standard library, and they are designed to make it easier to work with common data structures and patterns.

Here are a few examples of built-in generic types in TypeScript:

- `Array<T>`: Represents an array of values of a specific type. For example, `Array<string>` represents an array of strings.

- `Promise<T>`: Represents a value that will be available asynchronously in the future. For example, `Promise<string>` represents a promise that will resolve to a string at some point in the future.

- `IterableIterator<T>`: Represents an iterator that produces values of a specific type. For example, `IterableIterator<number>` represents an iterator that produces numbers.

- `ReadonlyArray<T>`: Represents an array that is read-only, meaning that its values cannot be modified. For example, `ReadonlyArray<string>` represents an array of strings that cannot be modified.

These are just a few examples of the built-in generic types that are available in TypeScript. You can find a complete list of built-in types in the TypeScript documentation.

```typescript
const names: Array<string> = []; // same as: string[]

const promise: Promise<string> = new Promise((resolve, reject) => {
   setTimeout(() => {
       resolve('This is done!');
   }, 2000); 
});
```

#### What are generics?

In TypeScript, generics are a way to create reusable components that can work with a variety of types. They allow you to write code that can be used with multiple different types while still providing type-safety.

Here's a simple example of how generics can be used in TypeScript:

```typescript
// This is a generic function that takes a single argument of type T
function identity<T>(arg: T): T {
  return arg;
}

// We can call the function with different types
let output = identity<string>("Hello, world!");  // Output: "Hello, world!"
let output = identity<number>(42);  // Output: 42
```

In this example, the `identity` function is a generic function that takes a single argument of type `T` and returns a value of the same type. When we call the function, we specify the type of `T` that we want to use. In the first call, we use `string`, and in the second call, we use `number`. The function will then return the value that was passed in, ensuring that the correct type is used.

Generics can also be used with classes, interfaces, and other types in TypeScript. They are a powerful tool for creating flexible and reusable code that can be used with a variety of types.

### Creating a generic function

Here is a generic function in TypeScript that takes in two arguments, both of which can be of any type:

```typescript
function genericFunction<T, U>(arg1: T, arg2: U): void {
  console.log(arg1, arg2);
}
```

Here's how you can use this function:

```typescript
genericFunction<string, number>('hello', 42); // outputs 'hello' 42
genericFunction<boolean, string>(true, 'world'); // outputs true 'world'
```

In the function definition, `T` and `U` are type variables. They are placeholders for the actual types that will be passed to the function when it is called. In this example, the first time the function is called, `T` is inferred to be of type `string` and `U` is inferred to be of type `number`. The second time the function is called, `T` is inferred to be of type `boolean` and `U` is inferred to be of type `string`.

The function simply logs the values of the arguments to the console. Since the function does not return anything, its return type is `void`.

### Working with Constraints

Sometimes you may want to restrict the types of `T`and `U` here, so of your Generic Types.
You can do that with **type constraints**. For generic types, you can set certain constraints regarding the types your generic types can be based on. You do this with the `extends` keyword in the angled brackets after the type which you wanna constrain.

```typescript
function merge<T extends object, U extends object>(objA: T, objB: U) {
    return Object.assign(objA, objB);
}

const mergeObj = merge({name: 'Benji'}, age: {30}));
```

### The `keyof` constraint

The keyof constraint in TypeScript is a way to specify that the type of a variable is the key of a specific type. This is often used in conjunction with a type that is an index type or a mapped type.

For example, consider the following type:

```typescript
type ColorMap = {
  red: '#ff0000',
  green: '#00ff00',
  blue: '#0000ff'
}
```

Here, `ColorMap` is a type that maps string keys to string values. If we wanted to create a variable that could be assigned any of the keys in this type, we could use the `keyof` constraint as follows:

```typescript
let colorKey: keyof ColorMap;
```

Now, `colorKey` is a variable that can be assigned any of the keys in the `ColorMap` type (i.e., 'red', 'green', or 'blue').

The `keyof` constraint can also be used in combination with other types.

```typescript
type ColorMap = {
  red: '#ff0000',
  green: '#00ff00',
  blue: '#0000ff'
}

type ColorName = 'red' | 'green' | 'blue';

let colorKey: keyof ColorMap & ColorName;
```

Here, `colorKey` is a variable that can be assigned any of the keys in the `ColorMap` type, and it must also be a value of the `ColorName` type.

### Generic Classes

In TypeScript, a generic class is a class that is parameterized over types. This means that the class defines one or more type variables, which can be used to specify the types of fields, method parameters, and return values.

To create a generic class, you use the `<T>` syntax to specify the type variables.

```typescript
class Pair<T, U> {
  constructor(public first: T, public second: U) {}
}
```

Here, `Pair` is a generic class that has two type variables: `T` and `U`. These type variables are used to specify the types of the first and second fields, respectively.

To create an instance of a generic class, you need to specify the types to use for the type variables.

```typescript
let pair = new Pair<string, number>('hello', 42);
```

Here, we are creating an instance of the `Pair` class, with the type `string` for the `T` type variable and the type `number` for the `U` type variable.

You can also use type inference to let TypeScript infer the types for the type variables.

```typescript
let pair = new Pair('hello', 42);
```

In this case, TypeScript will infer that the type of the first field is `string` and the type of the second field is `number`.


## Decorators

### A first class decorator

Decorator is a design pattern that allows you to attach additional responsibilities to an object dynamically. Decorators are a form of metaprogramming, as they allow you to modify the behavior of a class or object at runtime.

To create a decorator in TypeScript, you need to define a function that takes the target object as an argument, and returns the modified object.

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

### Working with decorator factories

A decorator factory is a function that returns a decorator. This can be useful when you want to customize the behavior of a decorator based on arguments passed to the factory.

To create a decorator factory, you simply define a function that returns a decorator function. 

```typescript
function Logger(logString: string) {
    return function(target: Function) {
        console.log(logString);
        console.log(target); 
    }
}

@Logger('LOGGING - PERSON')
class Person {
    name = 'Benji';
    
    constructor() {
        console.log('Creating person...');
    }
}

const pers = new Person()

console.log(pers);
```

#### Building more useful decorators

```typescript
function Logger(logString: string) {
    return function(target: Function) {
        console.log(logString);
        console.log(target); 
    }
}

function WithTemplate(template: string, hookId: string) {
    return function(_: Function) {    // with the '_' we can tell TS that we are aware of this argument (constructor), but we won't use it
        const hookElement = document.getElementById('hookId');
        if (hookElement) {
            hookElement.innerHTML = template;
        }
    }
}

@WithTemplate('<h1>My person object</h1>', 'app')    // this will render the <h1> tag in the DOM
@Logger('Logging')
class Person {
    name = 'Benji';
    
    constructor() {
        console.log('Creating person...');
    }
}

const pers = new Person()

console.log(pers);
```

:::info
You can always add more decorator to a class or anywhere you may need.
The execution of multiple decorators will start from bottom to top. Which means first the `@Logger` then the `@WithTemplate` decorator will run, however before all of that the decorator factories will run of each decorator from top to bottom, as in normal JavaScript. 
:::

### Diving into property decorators

```typescript
function Log(target: any, propertyName: string | Symbol) {
    console.log('Property decorator!');
    console.log(target, propertyName);
}

class Product {
    @Log
    title: string;
    private _price: number;
    
    set price(val: number) {
        if (val > 0) {
            this._price = val
        } else {
            throw new Error('Invalid price - should be positive')
        }
    }

    constructor(t: string, p: number) {
        this.title = t;
        this._price = p;
    }

    getPriceWithTax(tax: number) {
        return this._pricce * (1 + tax);
    }
}
```

It executes basically when your class definition is registered by JavaScript. So it executes when you define this property basically to JavaScript as part of your class.

### Accessor & Parameter decorators

In TypeScript, accessors are class members that provide a getter and/or setter for accessing a class property. They can be used to execute logic when a property is accessed or modified, or to create a computed property.

```typescript
function Log(target: any, propertyName: string | Symbol) {
    console.log('Property decorator!');
    console.log(target, propertyName);
}

function Log2(target: any, name: string, descriptor: PRopertyDescriptor) {
    console.log('Accessor decorator!');
    console.log(target);
    console.log(name);
    console.log(descriptor);
}

class Product {
    @Log
    title: string;
    private _price: number;
    
    @Log2
    set price(val: number) {
        if (val > 0) {
            this._price = val
        } else {
            throw new Error('Invalid price - should be positive')
        }
    }

    constructor(t: string, p: number) {
        this.title = t;
        this._price = p;
    }

    getPriceWithTax(tax: number) {
        return this._pricce * (1 + tax);
    }
}
```

Parameter decorators are a way to attach metadata to a function's parameters. They are not widely used, and are typically used in conjunction with a decorator factory, which is a function that returns a decorator.

:::warning
**Link**

[typestack/class-validator](https://github.com/typestack/class-validator) is a ready made package which can help you use validation on class based decorators.
:::

## Type Alias

Type aliases help us to have a meaningful name for existing datatypes aligned with the purpose of the variable. It confirms the new name interprets the same declaration.
```Typescript
type MeaningfulName = < an existing Typescript type>
```

**Simple Types :**
```Typescript
type ProductIdNum = number;
function getProduct(id: ProductIdNum) {
    console.log(`Product found for id ${id}`)
}
getProduct(1002);
getProduct(null); // Error: argument type is not a number
```

**Union Types :**
```Typescript
type ProdCountType = number | string | undefined;
function getProductDetail(pName: string, pCount: ProdCountType){
    console.log(`Name: ${pName} ; Count: ${pCount}`)
}
getProductDetail("SamsungGalaxy10", 2);
getProductDetail("LenovoNote3", "No stock!");
getProductDetail("Redmi5", undefined);
```

**Function Types :**
```Typescript
type PromoCodeGenerator = (pName: string, pId: number) => string;
let generator: PromoCodeGenerator = function (pName: string, pId: number): string {
    return pName.substr(0, 4).toLocaleUpperCase() + pId;
}
let offer = generator("Lenovo 3", 1002);
console.log(`Please use the Promocode: ${offer}`)
```

**Object Types :**
```Typescript
type ProductStatusObject = { pId: string, availablity: boolean }
function checkProductAvailablity(product: ProductStatusObject) {
    if (product.availablity == true) {
        console.log(`${product.pId} is available`);
    }
    else {
          console.log(`${product.pId} is not available`);
    }
}
let product1 : ProductStatusObject = {pId: "LENOVO9089", availablity: true}
checkProductAvailablity(product1);
let product2 : ProductStatusObject = { availablity: false, pId: "SAMSUN8811"}
checkProductAvailablity(product2);
```

**Array Types :**
```Typescript
type ProductStatusObject = { pId: string, availablity: boolean }

type AvailabiltyArray = [ ProductStatusObject ]

let product1: ProductStatusObject = { pId: "LENOV9912", availablity: true  };
let product2: ProductStatusObject = { pId: "REDMI8878", availablity: false };
let product3: ProductStatusObject = { pId: "SAMSU5633", availablity: false }
let product4: ProductStatusObject = { pId: "OPPO1128",availablity: true}

let products: AvailabiltyArray = [product1];
products.push(product2,product3, product4);

let availableProducts = products.filter( (product) => product.availablity)
console.log(availableProducts)
```

**Generic Types :**
```Typescript
type ProductCatelog<T> = { pCategoryCode: T, availableProducts: Array<T> }

function availableProducts<T>(pCatelog : ProductCatelog<T>) {
console.log(`Products on category code ${pCatelog.pCategoryCode} :
            ${(pCatelog.availableProducts)}`)
}

//ProductCatelog by code number
let productByCodeNumber: ProductCatelog<number>;
productByCodeNumber = {pCategoryCode : 12321, availableProducts :[4566,7788,9090]}
availableProducts(productByCodeNumber)

//ProductCatelog by code string
let productByCodeString: ProductCatelog<string>;
productByCodeString = {pCategoryCode : "PCC2020",
 availableProducts :["Pr4566","Pr7788","Pr9090"]}
availableProducts(productByCodeString)
```

**Type Literals :**
```Typescript
type ratings = "Good" | "Average" | "Excellent" | 0;
let customerFeedback1: ratings = "Good";
let customerFeedback2: ratings = 0;
let customerFeedback3: ratings = "Not bad"; 
// Error: Could not find literal value match.
```

### Generic utility types

Generic utility types are types that are parameterized over other types, and are used to represent common type transformations. Some examples of generic utility types include:

* `Partial<T>`: Makes all properties in `T` optional
* `Readonly<T>`: Makes all properties in `T` read-only
* `Record<K, T>`: Creates a type with a set of properties of type `T` whose names are of type `K`
* `Pick<T, K>`: Creates a type by picking the set of properties `K` from `T`
* `Omit<T, K>`: Creates a type by removing the set of properties `K` from `T`

Example how to use these:

```typescript
interface Todo {
  title: string;
  description: string;
  completed: boolean;
}

type PartialTodo = Partial<Todo>;
// PartialTodo is equivalent to:
// {
//   title?: string;
//   description?: string;
//   completed?: boolean;
// }

type ReadonlyTodo = Readonly<Todo>;
// ReadonlyTodo is equivalent to:
// {
//   readonly title: string;
//   readonly description: string;
//   readonly completed: boolean;
// }

type TodoById = Record<string, Todo>;
// TodoById is equivalent to:
// {
//   [id: string]: Todo;
// }

type TodoTitle = Pick<Todo, 'title'>;
// TodoTitle is equivalent to:
// {
//   title: string;
// }

type OmitCompleted = Omit<Todo, 'completed'>;
// OmitCompleted is equivalent to:
// {
//   title: string;
//   description: string;
// }
```


## Interface vs Type

| **Interfaces**                                                                                                             | **Type Aliases**                                                                                                                                                            |
| -------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| In Typescript, interfaces focus on the Type Checking feature. It defines and shapes a construct                            | Type Aliases is a custom or hand-written name provided to an existing data type of Typescript                                                                               |
| It allows optional, readOnly properties                                                                                    | It allows optional, readOnly properties                                                                                                                                     |
| It allows callable and static properties                                                                                   | It allows callable and static properties                                                                                                                                    |
| Interfaces can be extended by other Interfaces and Type Aliases<br><br>Ex: interface Result extends score, Grade{<br><br>} | Type Aliases cannot be extended by other Interfaces and Type Aliases.<br><br>Intersections will help in Type Aliases to do extending<br><br>Ex: type Result = Score & Grade |
| It can be implemented by a class                                                                                           | It can be implemented by a class and extended by an Interface.                                                                                                              |
| Interface addresses declaration merging                                                                                    | Type Aliases do not address declaration merging                                                                                                                             |


## Resources

[GIt MD Ref.](https://github.com/benjaminpeto/notes_understanding-typescript/blob/main/README.md)