


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












