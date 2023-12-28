### Directives

* Used to change the behavior and appearance of DOM element.
* Can implement all life-cycle hooks
* Can not have template i.e, html and css file.
* 2 Types - 
	* Components — directives with a template. This type of directive is the most common directive type.
	* Attribute directives — directives that change the appearance or behavior of an element, component, or another directive.  ( can only modify DOM)
	* Structural directives — directives that change the DOM layout by adding and removing DOM elements. `*` (can add or remove DOM) 
* Built-in's : `*ngIf`, `*ngFor`, `*ngSwitch`, `ngClass`, `ngStyle`
* VID time: 3:28:00 to 4:08:00

#### Component Directives


#### Attribute Directives

Most common Attribute Directives are: `ngClass`, `ngStyle`, `ngModel` .
```html
<div [ngClass]="isSpecial ? 'special' : ''">Example</div>
<div [ngClass]="currentClasses">Example</div>
<div [ngStyle]="currentStyle">Example</div>
<label for="example">{{name}}</label>=
<input [(ngModel)]="name" id="example" /> <!-- Need add formsModule dependency-->
```

```ts
isSpecial = false;
currentClasses = {};
currentStyle = {};
name = 'bob';

ngOnInit(){
this.setCurrentClasses();
this.setCurrentStyle();
}

setCurrentClasses(){
this.currentClasses = {
saveable: true,
modified: false,
special: true
}
}

setCurrentStyle(){
this.currentStyle = {
'font-style': 'italic',
'font-weight': 'bold'
}
}
```

Make your custom directives
```sh
ng g d name-of-directive
```

**ElementRef**:
An `ElementRef` is backed by a render-specific element. In the browser, this is usually a DOM element. i.e. `ElementRef` grants direct access to its hosts DOM elements through its *nativeElement property*.

**HostListener**
- Decorator that declares a DOM event to listen for, and provides a handler method to run when that event occurs.
- Angular invokes the supplied handler method when the host element emits the specified event, and updates the bound element with the result.
- If the handler method returns false, applies `preventDefault` on the bound element.

```ts
import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
selector: '[appHighilght]'
})

export class HighilghtDirective {
el: ElementRef;

@Input() appHighilght = '';

@HostListener('mouseenter') onMouseEnter(){
this.highlight(this.appHighilght);
} 

@HostListener('mouseleave') onMouseleave(){
this.highlight('');
}

constructor(el: ElementRef) {
this.el = el;
el.nativeElement.style.backgroundColor = this.appHighilght;
}

private highlight(color: string){
this.el.nativeElement.style.backgroundColor = color;
}
}
```

```html
<p [appHighilght]="'red'">Text</p>
```

#### Structural Directives

Structural directives are responsible for HTML layout. They shape or reshape the DOM's structure, typically by adding, removing, and manipulating the host elements to which they are attached.
- NgIf—conditionally creates or disposes of subviews from the template.
- NgFor—repeat a node for each item in a list.
- NgSwitch—a set of directives that switch among alternative views. 

You can add or remove an element by applying an NgIf directive to a host element.

*When NgIf is false, Angular removes an element and its descendants from the DOM. Angular then disposes of their components, which frees up memory and resources.*
```ts
import { Component} from '@angular/core';

@Component({
selector: 'app-root',
templateUrl: './app.component.html',
styles: []
})

export class AppComponent {
title = 'demo1';
fontSizepx = 16;
firstExample :any |undefined;
isActive = true;
  
items = [
{name: 'Bob'},
{name: 'Roy'},
{name: 'Monika'}
]

sItem = {name: 'Monika'};
}
```

```html
<div *ngIf="isActive">Active</div> 

<div *ngIf="isActive">Active</div> 

<!--> index is a special variable that give iteration count<-->
<div *ngFor="let item of items; let i = index">{{i}} - {{item.name}}</div> 


<div [ngSwitch]="sItem.name">
	<div *ngSwitchCase="'Bob'">Hi dad</div>
	<div *ngSwitchCase="'Monika'">Hi Mom</div>
	<div *ngSwitchDefault>Hi Friend</div>
</div>
```

#### if and else
```html
<div *ngIf="isActive; else notActive">Active</div> 

<ng-template #notActive>
<div> Not Active </div>
</ng-template>

```
