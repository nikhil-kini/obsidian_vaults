[[Typescript]]
[[HTML]]
[[CSS]]

## Initialization

* Required **[[Node]] JS**
* Required **Editor**

To Install Angular globally. [Command ref.](https://angular.io/cli)
```sh
npm i @angular/cli -g
```

To check Angular version
```shell
ng version
```

To create workspace
```sh
ng new name_workspace/application_name
```
- **`--skip-tests`**: stops the generation of testing (viz. e2e, `karma.conf.js`, `protractor.conf.js`, and `*.spec.` files) including `tsconfig.spec.json`, `app.component.spec.ts`
- **`--inline-style`**: stops the generation of external css stubs instead keeping an empty string in the `.ts` file.
- **`--inline-template`**: stops the generation of external html instead keeping it in the `template` variable in the `.ts` file.
- `--minimal`: stops generation of all, above combined.


To create workspace to set prefix
```sh
ng new name_workspace/application_name --prefix='value'
```

To create workspace(for empty workspace, no default dependencies)
```sh
ng new name_workspace --createApplication=false
```

To install dependencies in `package.json`
```sh
npm i
```

To generate a application(For Empty Applications)
```sh
ng g app app_name
```

To run application Locally(runs in watch mode for `src/` )
```sh
ng serve -o
```

To add a component (in the directory)
```shell
ng g c name_of_component # = ng generate component name
```

To add a service (in the directory)
```shell
ng g s name_of_service # = ng generate service name
```

To add a environments
```shell
ng g environmets# = ng generate environments
```

To add a interface (in the directory)
```shell
ng g interface name_of_service # = ng generate interface name
```

To add a interceptor (in the directory)
```shell
ng g interceptor name_of_interceptor 
```

To add a directive (in the directory)
```shell
ng g d name_of_directive # = ng generate directive name
```

To add a module (in the directory)(with folder)
```shell
ng g m name_of_module # = ng generate module name
```

To add a module (in the directory)(without folder)
```shell
ng g m name_of_module --flat=true # = ng generate module name
```

To add a module (in the directory)(without folder and with routing )
```shell
ng g m name_of_module --routing --flat=true # = ng generate module name
```

To build the application in the production mode
```sh
ng build -c=production
```
```sh
ng serve --prod
```

To add a module (in the directory)(Lazy loaded with routing )
```shell
ng g m name_of_module --route=name_of_module --routing --module=existing_module_where_route_needs_to_be_added
```

To run Unit Test Cases
```sh
ng test
```

To run Unit Test Cases
```sh
ng test --code-coverage
```

### Files created by Angular

* **`README.md`**  - For GitHub Info

* **`karma.config.js`** - Unit-testing framework config.

* **`angular.json`** - in-case of dependent project, the info will be stored here.

* **`.gitignore`** - To ignore files, not to be committed

* **`.editorconfig`** - contains text editor related info.

* **`package.json`** - For Dependencies(*info `devDependencies:` are dependencies that are not deployed in production*), Declare `ng` commands for application.

* **`package-lock.json`** - For Dependencies(*info `devDependencies:` are dependencies that are not deployed in production*), Declare `ng` commands for application. **For team work: should be in sync with `package.json` for CLI build**.

* **`tsconfig.json`** - [[Typescript]] config file.
* **`tsconfig.app.json`** - **All the files written in `.ts` files. That will be compiled using this file.**
* **`tsconfig.spec.json`** - **All the unit testcases are written in `.spec.ts` files. That will be compiled using this file.** Extends `tsconfig.json`

* **`.browserlistrc`** - to list the browser support for the project.

* **`src`** - all program related code.
* **`main.ts` ,  `styles.scss` ,   `index.html`** - are the main files were all other files will be injected
* **`polyfill`** - make sure the code is backward compatible with **JS** versions .

### `angular.json`
```json
{

"$schema": "./node_modules/@angular/cli/lib/config/schema.json",

"version": 1,

"newProjectRoot": "projects",

"projects": {

"hotelinventoryapp": {

"projectType": "application", // defines application or Library

"schematics": {

"@schematics/angular:component": {

"style": "scss"

}

},

"root": "",

"sourceRoot": "src", // source folder for project

"prefix": "app",

"architect": {

"build": {

"builder": "@angular-devkit/build-angular:browser",

"options": {

"outputPath": "dist/hotelinventoryapp", // All the compiled files will be stored here

"index": "src/index.html", // Path for the index.html for project.

"main": "src/main.ts",

"polyfills": [

"zone.js"

],

"tsConfig": "tsconfig.app.json",

"inlineStyleLanguage": "scss",

"assets": [  // asset paths

"src/favicon.ico",

"src/assets"

],

"styles": [  // similarly CSS files path

"src/styles.scss"

],

"scripts": [] // similarly JS files path

},

"configurations": {

"production": {

"budgets": [

{

"type": "initial",

"maximumWarning": "500kb",

"maximumError": "1mb"

},

{

"type": "anyComponentStyle",

"maximumWarning": "2kb",

"maximumError": "4kb"

}

],

"outputHashing": "all"

},

"development": {

"buildOptimizer": false,

"optimization": false,

"vendorChunk": true,

"extractLicenses": false,

"sourceMap": true,

"namedChunks": true

}

},

"defaultConfiguration": "production"

},

"serve": {

"builder": "@angular-devkit/build-angular:dev-server",

"configurations": {

"production": {

"browserTarget": "hotelinventoryapp:build:production"

},

"development": {

"browserTarget": "hotelinventoryapp:build:development"

}

},

"defaultConfiguration": "development"

},

"extract-i18n": {

"builder": "@angular-devkit/build-angular:extract-i18n",

"options": {

"browserTarget": "hotelinventoryapp:build"

}

},

"test": {

"builder": "@angular-devkit/build-angular:karma",

"options": {

"polyfills": [

"zone.js",

"zone.js/testing"

],

"tsConfig": "tsconfig.spec.json",

"inlineStyleLanguage": "scss",

"assets": [

"src/favicon.ico",

"src/assets"

],

"styles": [

"src/styles.scss"

],

"scripts": []

}

}

}

}

}

}

```

### `app.module.ts`
Is called by `main.ts`. And all the module info in project are linked here.
```typescript
import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';

  

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';

  

@NgModule({ // Decorator gives meta-data to angular

declarations: [ // Component, Directive and Pipe are declared here

AppComponent

],

imports: [   // Module INFO's of all modules

BrowserModule,

AppRoutingModule

],

providers: [],

bootstrap: [AppComponent] // which component needs to be loaded first

})

export class AppModule { }
```

### Mono-repo
* For creating multiple application in same repository.
* Deploy multiple apps/libs from same repository.

### Component
Views that need to be rendered for the client.
* On creation you will get 4 files
* `.html` for html of the component
* `.css` for CSS of the component
* `ts` for ts of the component
* `spec.ts` for the unit test-cases of the ts component

**`app.component.ts`**
```Typescript
import { Component } from '@angular/core';


@Component({    // indidates component

selector: 'app-root', // HTML Tag<> liked to index.html. 'app' is a prefix from angular.json file -> prefix: value. This is for specificty, you can have custom names has well.

templateUrl: './app.component.html', // link to html

styleUrls: ['./app.component.scss'] // link to css file

})

export class AppComponent {

title = 'hotelinventoryapp';

}
```


## Template syntax

### Template Variables

- Template variables in Angular help you use data from one part of a template in another part of the template. 
- It’s a variable that is created in, and identifies a component or element within, the template itself. 
- With template variables, you can perform tasks such as respond to user input or finely tune your application's forms. In the template, you use the hash symbol, to declare a template variable.
ex:
```html
<input #phone placeholder="phone"/>
<button (click)="callPhone(phone.value)">Call</button>
```

```html
<!-- Will work -->
<input #ref1 type="text" [(ngModel)]="firstExample" />
<span [ngIf]="true">{{ref1.value}}</span>
```

```html
<!-- Will cause Error -->
<input [ngIf]="true" #ref1 type="text" [(ngModel)]="firstExample" />
<span>{{ref1.value}}</span>
```



### Pipes

*  Used for data transformation
*  Don't change actual object
* Built-in's : The following are commonly used built-in pipes for data formatting:
	- [`DatePipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/DatePipe): Formats a date value according to locale rules.
	- [`UpperCasePipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/UpperCasePipe): Transforms text to all upper case.
	- [`LowerCasePipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/LowerCasePipe): Transforms text to all lower case.
	- [`CurrencyPipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/CurrencyPipe): Transforms a number to a currency string, formatted according to locale rules.
	- [`DecimalPipe`](https://github.com/angular/angular/blob/main/api/common/DecimalPipe): Transforms a number into a string with a decimal point, formatted according to locale rules.
	- [`PercentPipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/PercentPipe): Transforms a number to a percentage string, formatted according to locale rules.
* VID time: 4:08:00  to 4:30:00
*  For a complete list of built-in pipes, see the [pipes API documentation](https://github.com/angular/angular/blob/main/api/common#pipes "Pipes API reference summary").
- To learn more about using pipes for internationalization (i18n) efforts, see [formatting data based on locale](https://github.com/angular/angular/blob/main/aio/content/guide/guide/i18n-common-format-data-locale "Format data based on locale | Angular").

Create pipes to encapsulate custom transformations and use your custom pipes in template expressions.
```sh
ng g p Pipe_name
```

#### [Pipes and precedence](https://github.com/angular/angular/blob/main/aio/content/guide/pipes-overview.md#pipes-and-precedence)
The pipe operator has a higher precedence than the ternary operator (`?:`), which means `a ? b : c | x` is parsed as `a ? b : (c | x)`. The pipe operator cannot be used without parentheses in the first and second operands of `?:`.

Due to precedence, if you want a pipe to apply to the result of a ternary, wrap the entire expression in parentheses; for example, `(a ? b : c) | x`.


### Adding external libraries

* `ng i `  is automated way to download and add Libraries
* `npm i`  is manual way, imports must be defined in either angular.json or main.class (of that dependency) after install.

### [Life-cycle hooks](https://github.com/angular/angular/blob/main/aio/content/guide/lifecycle-hooks.md)

* Component instance has life-cycle hooks which can help you to hook into different events on Components.
* Life-cycle ends when components are destroyed.
* The sequence of log messages follows the prescribed hook calling order:

Angular executes hook methods in the following sequence. Use them to perform the following kinds of operations.

|Hook method|Purpose|Timing|
|:--|:--|:--|
|`ngOnChanges()`|Respond when Angular sets or resets data-bound input properties. The method receives a `SimpleChanges` object of current and previous property values.  <br><br>**NOTE**:  <br>This happens frequently, so any operation you perform here impacts performance significantly.<br><br>See details in [Using change detection hooks](https://github.com/angular/angular/blob/main/aio/content/guide/lifecycle-hooks.md#onchanges) in this document.|Called before `ngOnInit()` (if the component has bound inputs) and whenever one or more data-bound input properties change.  <br><br>**NOTE**:  <br>If your component has no inputs or you use it without providing any inputs, the framework will not call `ngOnChanges()`.|
|`ngOnInit()`|Initialize the directive or component after Angular first displays the data-bound properties and sets the directive or component's input properties. See details in [Initializing a component or directive](https://github.com/angular/angular/blob/main/aio/content/guide/lifecycle-hooks.md#oninit) in this document.|Called once, after the first `ngOnChanges()`. `ngOnInit()` is still called even when `ngOnChanges()` is not (which is the case when there are no template-bound inputs).|
|`ngDoCheck()`|Detect and act upon changes that Angular can't or won't detect on its own. See details and example in [Defining custom change detection](https://github.com/angular/angular/blob/main/aio/content/guide/lifecycle-hooks.md#docheck) in this document.|Called immediately after `ngOnChanges()` on every change detection run, and immediately after `ngOnInit()` on the first run.|
|`ngAfterContentInit()`|Respond after Angular projects external content into the component's view, or into the view that a directive is in.  <br>See details and example in [Responding to changes in content](https://github.com/angular/angular/blob/main/aio/content/guide/lifecycle-hooks.md#aftercontent) in this document.|Called _once_ after the first `ngDoCheck()`.|
|`ngAfterContentChecked()`|Respond after Angular checks the content projected into the directive or component.  <br>See details and example in [Responding to projected content changes](https://github.com/angular/angular/blob/main/aio/content/guide/lifecycle-hooks.md#aftercontent) in this document.|Called after `ngAfterContentInit()` and every subsequent `ngDoCheck()`.|
|`ngAfterViewInit()`|Respond after Angular initializes the component's views and child views, or the view that contains the directive.  <br>See details and example in [Responding to view changes](https://github.com/angular/angular/blob/main/aio/content/guide/lifecycle-hooks.md#afterview) in this document.|Called _once_ after the first `ngAfterContentChecked()`.|
|`ngAfterViewChecked()`|Respond after Angular checks the component's views and child views, or the view that contains the directive.|Called after the `ngAfterViewInit()` and every subsequent `ngAfterContentChecked()`.|
|`ngOnDestroy()`|Cleanup just before Angular destroys the directive or component. Unsubscribe Observables and detach event handlers to avoid memory leaks. See details in [Cleaning up on instance destruction](https://github.com/angular/angular/blob/main/aio/content/guide/lifecycle-hooks.md#ondestroy) in this document.|Called immediately before Angular destroys the directive or component.|

![ng_dia](./assets/ng_dia.png)
* VID 4:46:00 to 6:50:00

### Component Communication

* where 2 or more components needs to interact with each other 
* multiple ways to achieve this: 
	* using `@Input` and `@Output`
	* using `@ViewChild` and `@ContentChild`
	* using provider Services
	* Template Outlet

#### `@Input` 

Is a door way through the data travels from Parent to Child.
**Parent to  -> Child**
```ts
import { Component, Input} from '@angular/core';

@Component({
selector: 'app-child',
templateUrl: './child.component.html',
styles: [
]
})

export class ChildComponent {
@Input() childMessage: string | undefined;
constructor(){}
}
```

```html
Say {{childMessage}}
```

```html
<!-- parent Component -->
<app-child [childMessage]="'Hello form parent'"></app-child>
```

#### `@Output`

Is a door way through the data travels from Child to Parent.
**Child to  -> Parent**
```ts
import { Component, Output, EventEmitter} from '@angular/core';

@Component({
selector: 'app-child',
templateUrl: './child.component.html',
styles: [
]
})

export class ChildComponent {
@Output() messageEvent = new EventEmitter<string>();

sendMessage(){
this.messageEvent.emit('Hello from child.');
}
}
```

```ts
import { Component } from '@angular/core';

@Component({
selector: 'app-parent',
templateUrl: './parent.component.html',
styles: [
]
})

export class ParentComponent {
receiveMessage(msg:string){
alert(msg);
}
}
```

```html
<!-- Child -->
<button (click)="sendMessage()">Send Msg</button>
```

```html
<!-- Parent -->
<app-child (messageEvent)="receiveMessage($event)"></app-child>
```

#### `@Viewchild`

- **Child to  -> Parent**
- Gives access to properties and function of the child to the parent.
- *we need to initialize **View** i.e we need to initialize **AfterViewInit***

```ts
import { Component, Output, EventEmitter} from '@angular/core';

@Component({
selector: 'app-child',
templateUrl: './child.component.html',
styles: [
]
})

export class ChildComponent {
message = 'message form child!';
}

```

```ts
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ChildComponent } from '../child/child.component';

@Component({
selector: 'app-parent',
templateUrl: './parent.component.html',
styles: [
]
})

export class ParentComponent {
@ViewChild(ChildComponent) child: any | undefined;
ngAfterViewInit(){
alert(this.child.message);
}
}
```

```html
<!-- parent -->
<app-child></app-child>
```


### ng-content

Any-time when you want to inject HTML into a component, use ng-content.
```html
<!-- Child -->
<h2>Hello from child!</h2>
<ng-content></ng-content>
```

```html
<app-child>
<p>Hello from root comp!</p>
</app-child>
```

**Multi-content Projection**
```html
<!-- Child -->
<h2>Hello from child!</h2>
<ng-content [question]></ng-content>
<ng-content [answer]></ng-content>
```

```html
<app-child>
  <h3 question>is ths ng-content cool?</h3>
  <p answer>Yes it is</p>
</app-child>
```

### Template Statement

- A template statements are something that responds to an event raised by a target like an Angular element, component, or directive.
- With template statements, your application can engage users through actions such as displaying dynamic content or submitting forms.
- It commits side effects as it changes the fields in the component.

```ts
import { Component} from '@angular/core';

@Component({
selector: 'app-root',
templateUrl: './app.component.html',
styles: []
})

export class AppComponent {
title = 'demo1';
showText = false;
  
toggleText(event: any): void{
this.showText = !this.showText;
console.log(event);
}
}
```

```html
<button (click)="toggleText($event)">Toggle me</button>
{{showText}}
```



### [Dependency Injection](https://angular.io/guide/hierarchical-dependency-injection)

* Dependencies are services or objects that a class needs to perform its function.
* Dependency injection, or DI, is a design pattern in which a class requests dependencies from external sources rather than creating them. 
* Angular's Dependency injection framework provides dependencies to a class upon instantiation. 
* You can use Angular DI to increase flexibility and modularity in your applications.
* Has built-in support in Angular
* DI providers: 
	* Class based providers
	* Value providers
	* Factory
* VID around 6:50:00 to 7:41:00

```ts
import { Injectable } from "@angular/core";

@Injectable({
providedIn: 'root'
})

export class LogService{
logMessage(msg: string): void{
console.log(msg);
}
}
```

```ts
// In App Module update providers
providers: [LogService],
```

```html
<p>Hello World</p>
```


#### Dependency Resolution
![](https://i.pinimg.com/originals/8e/82/62/8e8262dd48306f2ab4469e1e29642e4e.png)


#### Resolution Modifiers
Angular's resolution behavior can be modified with [@Optional ()](https://angular.io/api/core/Optional) , [@Self()](https://angular.io/api/core/Self) , [@SkipSelf()](https://angular.io/api/core/SkipSelf)() and [@Host()](https://angular.io/api/core/Host).
VID 7:10:00

### Angular HTTP
* [Proxying to a backend server](https://angular.io/guide/build#proxying-to-a-backend-server) for more info.
* HTTP Client
```ts
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  getData(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer auth_key'
      })
    }

    return this.http.get('https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a', httpOptions)
    .pipe(catchError((error) => {
      console.log(error);
      return throwError('Error');
    }));
  }
}
```

```ts
import { Component, ElementRef, Renderer2} from '@angular/core';
import { DataService } from './data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent {
  title = 'demo1';

  constructor(private dataservice: DataService){}

  ngOnInit(){
    this.dataservice.getData().subscribe((res) => {
      console.log(res);
    });
  }
}
```

* RxJS - Reactive Programming Library
	* **Don't use `subscribe()` it will cause memory leaks, use `pipe()` instead.**
* *Observables* are the stream of data. (Reactive programming)
* [RxJS Operators](https://rxjs.dev/guide/operators) commonly used
	* shareReplay()
	* catchError()
	* Map operators
* VID 7:41:00 to 9:36:00

#### Http Interceptors and APP_Initializer
- With interception, you declare interceptors that inspect and transform HTTP requests from your application to a server. 
- Interceptors can perform a variety of implicit tasks, from authentication to logging, for every HTTP request/response. 
- Without interception, developers would have to implement these tasks explicitly for each HttpClient method call.
* Interceptor sits between **Client** and the **Server**, headers of *tokens* can be added, so all call will have the tokens, ex: Mock-interceptor of DC project.
```ts
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()

export class TestIntercoptor implements HttpInterceptor{
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authReq = req.clone({
            headers: req.headers.set('Authorization', 'Bearer auth_key')
        });

        return next.handle(authReq);
    }
}
```

```ts
// App-Module Update
providers: [{provide: HTTP_INTERCEPTORS, useClass: TestIntercoptor, multi: true}],
```

* APP_Initializer allows you to inject function as the application startup.

### Angular Router

* In a single-page app, you change what the user sees by showing or hiding portions of the display that correspond to particular components, rather than going out to the server to get a new page.
* To handle the navigation from one view to the next, you use the Angular Router. The Router enables navigation by interpreting a browser URL as an instruction to change the view.
* Mapping the different pages in [[Additional Info]]
	* Import RouterModule(default add when angular config setup)
	* `forRoot()` (should be called only once in the project) method allows us to add multiple route config
	* Default route
	* Dynamic route
	* Wild card route
* Activated Route Service
	* Allows to Read the router data.
	* Allows access to snapshot data.
	* Allows to access data from Route Config.

#### Route Guards
To generate route-guard.
```sh
ng g guard name_guard
```
* canActivate
* canActivateChild
* canDeactivate
* canLoad
* Resolve
* VID 13:00:00

* **Router Service.**
* **Feature Module and Routing**
	* *One Component* can only be registered in one Module.
	* *Feature Routing Modules* must always be mentioned above *AppRoutingModule* in `app.module.ts`
* **Nested Routes and Child Routes**

```ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChildAComponent } from './child-a/child-a.component';
import { ChildBComponent } from './child-b/child-b.component';
import { FirstComponent } from './first/first.component';
import { SecondComponent } from './second/second.component';
import { yourGuardGuard } from './your-guard.guard';

const routes: Routes = [
  {path:'first-component/:id', component:FirstComponent,
  children: [
    {path: 'child-a', component: ChildAComponent},
    {path: 'child-b', component: ChildBComponent}
  ]
},{
  path:'second-component', component:SecondComponent, canActivate: [yourGuardGuard]
},{
  path:'**', component:FirstComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

```

```html
<h1>Angular router app</h1>
<nav>
	<ul>
		<li><a routerLink="/first-component/1">First Component</a></li>
		<li><a routerLink="/second-component">Second Component</a></li>
	</ul>
</nav>

<router-outlet></router-outlet>
```

```ts
import { Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styles: [
  ]
})
export class FirstComponent {
  constructor(private route: ActivatedRoute){}

  ngOnInit (): void{
    alert(this.route.snapshot.paramMap.get('id'));
  }
 
}
```

```html
<h2>First component</h2>
<nav>
    <ul>
        <li><a routerLink="child-a">Child A</a></li>
        <li><a routerLink="child-b">Child B</a></li>
    </ul>
</nav>

<router-outlet></router-outlet>
```


### Template Driven Forms

- Angular supports two design approaches for interactive forms.
- Template-driven forms are suitable for small or simple forms, while reactive forms are more scale-able and suitable for complex forms.
```ts
export class Pet{
    constructor(
        public id: number,
        public name: string,
        public specie: string
    ){}
}
```

```ts
import { Component, ElementRef, Renderer2} from '@angular/core';
import { Pet } from './pet';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent {
  title = 'demo1';

  species = ['fish', 'cat', 'dog'];
  model = new Pet(1, 'Goldie', this.species[0]);
  submitted = false;

  onSubmit(){
    this.submitted = true;
  }
}
```

```html
<div [hidden]="submitted">
<p>{{model | json}}</p>
<form #petForm="ngForm" (ngSubmit)="onSubmit()">
    <div>
        <label for="name">Name</label>
        <input type="text"
        id="name"
        required
        [(ngModel)]="model.name"
        name="name">
    </div>
    <div>
        <label for="species">Species</label>
        <select name="specie" id="species" [(ngModel)]="model.specie">
            <option *ngFor="let specie of species" [value]="specie">{{specie}}</option>
        </select>
    </div>
    <button [disabled]="!petForm.form.valid" type="submit">Submit</button>
</form>
</div>
<div [hidden]="!submitted">
    <h2>You submitted:</h2>
    <p>Name: {{model.name}}</p>
    <p>Specie: {{model.specie}}</p>
    <button (click)="submitted = false">Edit</button>
</div>
```

### Reactive Forms

* Reactive forms provide a model-driven approach to handling form inputs whose values change over time. 
* Each change to the form state returns a new state, which maintains the integrity of the model between changes.
* Import ReactiveFormsModule
* `formControl` to create Forms
* `formGroup` to group Multiple Controls
* `formBuilder` to build complex Forms.
*  VID 13:32:00

#### Form Control

```ts
import { Component, ElementRef, Renderer2} from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent {
  title = 'demo1';
  
  name = new FormControl('');

  updateName(){
    this.name.setValue('Namcy');
    }
}
```

```html
<label for="name">Name: </label>
<input id="name" type="text" [formControl]="name">

<p>value: {{name.value}}</p>

<button (click)="updateName()">Update name</button>
```

#### Form Group

```ts
import { Component, ElementRef, Renderer2} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent {
  title = 'demo1';
  
  profileForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
  })

  onSubmit(){
    console.log(this.profileForm.value);
  }
}

```

```html
<form [formGroup]="profileForm" (ngSubmit)="onSubmit()">
    <label for="first-name">First Name: </label>
    <input type="text" name="first-name" id="first-name" formControlName="firstName"/>
    <label for="last-name">Last Name: </label>
    <input type="text" name="last-name" id="last-name" formControlName="lastName">
    <button type="submit" [disabled]="!profileForm.valid">Submit</button>
</form>
```


### Forms validation
- To add validation to a template-driven form, you add the same validation attributes as you would with native HTML form validation. Angular uses directives to match these attributes with validator functions in the framework. 
- Every time the value of a form control changes, Angular runs validation and generates either a list of validation errors that results in an INVALID status or null, which results in a VALID status.
* **Form validators**
	* `required` validator
	* *Form instance -* `#roomsForm="ngForm"` (anyAttributeName = ngClassName)
		* `roomsForm.pristine`
		* `roomsForm.valid`
		* `roomsForm.dirty`
		* `roomsForm.invalid`
		* `roomsForm.value`
	 * `#roomamanites = "ngModel"` 
* **Covers Custom Directive with Forms**
	* Are similar to components, but they will not have templates.
	* Custom validation using directives.
* VID 9:36:00 to 12:20:40

#### Validation for Template Driven Forms

```ts
import { Component, ElementRef, Renderer2} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [`
  .special{
    font-size: 40px;
  }`]
})
export class AppComponent {
  title = 'demo1';
  nameInput = '';

}
```

```html
<form action="">
    <input type="text" name="name" id="" required minlength="4" [(ngModel)]="nameInput" #name="ngModel">
    <div *ngIf="name.invalid && (name.dirty || name.touched)">
        <div *ngIf="name.errors?.['required']">
            Name is required
        </div>
        <div *ngIf="name.errors?.['minlength']">
            Name must be atleast 4 characters
        </div>
    </div>
</form>
```

#### Validation for reactive forms

```ts
import { Component, ElementRef, Renderer2} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [`
  .special{
    font-size: 40px;
  }`]
})
export class AppComponent {
  title = 'demo1';

  nameInput = '';
  
  validationForm = new FormGroup({
    name: new FormControl(this.nameInput, [
      Validators.required,
      Validators.minLength(4)
    ])
  });

  get name(){
    return this.validationForm.get('name');
  }
}
```

```html
<form [formGroup]="validationForm">
    <input type="text" name="name" id="" required minlength="4" formControlName="name">
        <div *ngIf="validationForm.get('name')?.hasError('required') && validationForm.get('name')?.touched">
            Name is required
        </div>
        <div *ngIf="validationForm.get('name')?.hasError('minlength')">
            Name must be atleast 4 characters
        </div>
</form>
```



### Lazy Loading

To download small part of the application, on-demand, lazy loading is used.

### Provider Types

* root
* any












