
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
Using the `{{…}}` syntax is called template binding.
``` html
<div class="inventory-app">
<h1>{{ product.name }}</h1>
<span>{{ product.sku }}</span>
</div>
```
the code inside the brackets is an expression. That means you can do things like this:
• `{{ count + 1 }}`
• `{{ myFunction(myArguments) }}`
In the first case, we’re using an operator to change the displayed value of `count`.
In the second case, we’re able to replace the tags with the value of the function
`myFunction(myArguments)`.

Notice that we can use template strings in attribute values, as in the href of the
a tag: `href="{{ link }}"`. In this case, the value of the `href` will be dynamically
populated with the value of link from the component class
```html
<a class="ui large header" href="{{ link }}"> {{ title }}
</a>
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
#### `$event` variable
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

### Two-way binding (not recommended, use local reference `#var` or a service)

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

ex 2:
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

ex 3: using local variable
```ts
serverName = 'Testserver';

setServer(vari: htmlElement): void{
	this.serverName = vari.value;
}
```

```html
<input
  type="text"
  class="form-control"
  #localVar>
<button (click)="serServer(localVar)">Set value</button>
<p>{{serverName}}</p>
```

