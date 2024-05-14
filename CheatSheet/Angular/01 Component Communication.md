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
	@Input('alias-name') childMessage2: string | undefined;
	constructor(){}
}
```

```html
Say {{childMessage}}
Say {{childMessage2}}
```

```html
<!-- parent Component -->
<app-child [childMessage]="'Hello form parent'"></app-child>
<app-child [alias-name]="'Hello form parent2'"></app-child>
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
	@Output('alias-name') messageEvent2 = new EventEmitter<string>();
	
	sendMessage(){
		this.messageEvent.emit('Hello from child.');
		
		this.messageEvent2.emit('Hello from child.');
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
<app-child (alias-name)="receiveMessage($event)"></app-child>
```


#### Note

```html
<products-list
[productList]="products" <!-- input -->
(onProductSelected)="productWasSelected($event)"> <!-- output -->
</products-list>
```
The \[*squareBrackets*] pass inputs and the (*parentheses*) handle outputs.
Data flows in to your component via *input bindings* and events flow out of your
component through *output bindings*.
That is:
	• `(onProductSelected)`, the left-hand side is the name of the output we want to
	“listen” on
	• `"productWasSelected"`, the right-hand side is the function we want to call
	when something new is sent to this output
	• `$event` is a special variable here that represents the thing emitted on (i.e. sent
	to) the output.

#### `@ViewChild` and `@ContentChild`

-  `@ViewChild` **Child to  -> Parent**
- `@ContentChild` **Parent -> Child**
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
	@ContentChild('headerInfo', {static: true}) headerInfo: ElementRef;
	// static because used in ng life cycle hook
	ngAfterViewInit(){
		alert(this.child.message);
		alert(this.childInput.nativeElement.value);
	}
}

```
*Note: ngOnInit won't work has the DOM is not rendered*
```html
<input
  type="text"
  class="form-control"
  #localVar>
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
	@ViewChild('localVar', {static: true}) childInput: ElementRef | undefined;
	ngAfterViewInit(){
		alert(this.child.message);
		alert(this.childInput.nativeElement.value);
	}
}
```

```html
<!-- parent -->
<h1 #headerInfo><h1>
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

