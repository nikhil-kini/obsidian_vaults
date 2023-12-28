
### Binding syntax
```Typescript
export class RoomsComponent {
  hotelName = 'Hilton Hotel';

  numberOfRooms = 10;

  hide = false;

  toggle(){
    this.hide = !this.hide;
  }
}
```

#### String Interpolation
Any variable or method which will return string or resolve to string (number, etc implicit conversion)  can be added in between the braces.
`{{ return_type_string }}`

```html
<h1>Hotel Name {{hotelName}} </h1>
<div innerText="{{numberOfRooms}}"></div>
```

#### Property Binding
- Property binding moves a value in one direction so you can see it as a one-way street.
- You can’t go the other way. Value goes from class property to target element property.
```html
<div [innerText]="numberOfRooms"></div>
```
[innerText](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement)is a HTML Element property

#### Event Binding
- Event binding allows you to listen for and respond to user actions such as keystrokes, mouse movements, clicks, and touches. 
- To bind to an event you use the Angular event binding syntax. 
- This syntax consists of an event name within parentheses to the left of an equal sign, and a template statement to the right.
```html 
<button (click) = "toggle()">hide</button>
```

### `$event` variable
This is a special variable which contains all data on DOM element.
```html
<input
  type="text"
  class="form-control"
  (input)="onUpdateServerName($event)">
```

```ts
  onUpdateServerName(event: Event) {
    this.serverName = (<HTMLInputElement>event.target).value;
  }
```
### Attribute Binding, Class and Style Binding

- **Attribute binding** in Angular helps you set values for attributes directly. It is recommended that you set an element property with a property binding whenever possible. 
- However, sometimes you don't have an element property to bind. In those situations, you can use attribute binding. Attribute binding syntax resembles property binding, but instead of an element property between brackets, you precede the name of the attribute with the prefix attr, followed by a dot.
- Then, you set the attribute value with an expression that resolves to a string.
```html
<p [attr.attribute-you-are-targeting]="expression"></p>
```

- Similarly class binding
```html
<p [class.name-of-css-class]="expression"></p>
```

example:
```ts
import { Component} from '@angular/core';

@Component({
selector: 'app-root',
templateUrl: './app.component.html',
styles: []
})

export class AppComponent {
onSale = true;
}
```

```html
<div [class.sale]="onSale"></div> <!-- will assign the class based on the boolean onSale
```

- to set multiple class names
	- string of class names
	- object with class names
	- array of class names
```html
<div [class]="'class1 class2'"></div>
```

```html
<div [class]="{
class1: true,
class2: false
}"></div>
```

```html
<div [class]="['class1', 'class2']"></div>
```

- Similarly class binding
```html
<p [style.name-of-css-property]="expression"></p>
```
Ex:
```html
<div [style.background-color]="expression"></div>
<div [style.backgroundColor]="expression"></div> <!-- Same has above
```

```html
<div [style]="'width: 200px; height:10px;'"></div>
```

```html
<div [style]="{
width: 200px,
height:10px
}"></div>
```

### Two-way binding

- Two-way binding in Angular gives components in your application a way to share data. 
- Use two-way binding to listen for events and update values simultaneously between parent and child components. 
- Angular's two-way binding syntax is a combination of square brackets and parentheses, \[()]. This syntax combines the brackets of property binding, [], with the parentheses of event binding, ()
ex:
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
}
```

```html
<app-app-sizer [(size)]="fontSizepx"></app-app-sizer>
<div [style.font-size.px]="fontSizepx">Resize</div>
```

```ts
serverName = 'Testserver';
```

```html
<input
  type="text"
  class="form-control"
  [(ngModel)]="serverName">
<p>{{serverName}}</p>
```

```ts
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
selector: 'app-app-sizer',
templateUrl: './app-sizer.component.html',
styles: [
]
})

export class AppSizerComponent {
@Input() size: number;
@Output() sizeChange = new EventEmitter<number>();

constructor(private d: AppComponent){
this.size = d.fontSizepx;
}

dec(){
this.resize(-1);
}

inc (){
this.resize(+1);
}

resize(delta:number){
this.size = Math.min(40, Math.max(8, + this.size + delta));
this.sizeChange.emit(this.size);
}

}
```

```html
<div>
<button (click)="dec()">-</button>
<button (click)="inc()">+</button>
<label [style.font-size.px]="size">FontSize: {{size}}</label>
</div>
```

