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

