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

#### Component Directives (custom)

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

#### Custom Creation
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
import { Directive, ElementRef, HostListener, Input, Renderer2 } from '@angular/core';

@Directive({
	selector: '[appHighilght]'
})

export class HighilghtDirective {
	@Input() highLightColor = '';
	@Input('appHighilght') defaultColor = 'transparent';
	
	@HostListener('mouseenter') onMouseEnter(){
		this.highlight(this.highLightColor);
	} 
	
	@HostListener('mouseleave') onMouseleave(){
		this.highlight('');
	}
	
	constructor(private el: ElementRef, private renderer: Renderer2) {
		//el.nativeElement.style.backgroundColor = this.appHighilght; Wrong Way
		this.renderer.setStyle(el.nativeElement, 'background', this.highLightColor)
	}
	
	private highlight(color: string){
		//this.el.nativeElement.style.backgroundColor = color; Wrong way
		this.renderer.setStyle(el.nativeElement, 'background', this.highLightColor)
	}
}
```

```ts
// Better approach

import { Directive, ElementRef, HostListener, Input, Renderer2 } from '@angular/core';

@Directive({
	selector: '[appHighilght]'
})

export class HighilghtDirective {
	
	constructor(private el: ElementRef, private renderer: Renderer2){}
	
	@Input() highLightColor = 'green';
	@Input('appHighilght') defaultColor = 'transparent';
	// or Input() appHighilght = 'transparent';  replace defaultColor
	@HostBinding('style.background') background = 'transparent';
	
	@HostListener('mouseenter') onMouseEnter(){
		this.background = this.highLightColor;
	} 
	
	@HostListener('mouseleave') onMouseleave(){
		this.background = 'transparent';
	}
	
}
```

```html
<p appHighilght [highLightColor]="'red'" [defaultColor]="green">Text</p>

<p  [highLightColor]="'red'" [appHighilght]="green">Text</p> <!-- SAME
```
Note: you can use **Renderer2** for styling the DOM from directive.


#### Structural Directives

Structural directives are responsible for HTML layout. They **shape or reshape the DOM's structure, typically by adding, removing, and manipulating the host elements** to which they are attached.
- NgIf—conditionally creates or disposes of subviews from the template.
- NgFor—repeat a node for each item in a list.
- NgSwitch—a set of directives that switch among alternative views. 

**Note: Only one Structural Directive can be added to one HTML tag.**
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

#### Custom Creation
Make your custom directives
```sh
ng g d name-of-directive
```

```ts
import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appUnless]'
})
export class UnlessDirective {
  @Input() set appUnless(condition: boolean) {
    if (!condition) {
      this.vcRef.createEmbeddedView(this.templateRef);
    } else {
      this.vcRef.clear();
    }
  }

  constructor(private templateRef: TemplateRef<any>, private vcRef: ViewContainerRef) { }

}
```

```html
<div *appUnless="isActive">Active</div> 
```
#### if and else
```html
<div *ngIf="isActive; else notActive">Active</div> 

<ng-template #notActive>
<div> Not Active </div>
</ng-template>

```

### Note
```html
<div *ngIf="isActive">Active</div> 
```

same has
```html
<ng-template [ngIf]="isActive">
	<div>Active</div>
</ng-template>

```