- Pipes can only be used in the template and inside string interpolation.
```html
<div> {{ var | pipe}}</div>
```

```html
<div> {{ name | uppercase}}</div>
```

- Parameterize pipe
```html
<div> {{ today | date:'fullDate' }}</div>
```
[refer](https://angular.dev/api) search pipe for more info

- Combining Pipes, **Order Matters, Left -> Right execution**
```html
<div> {{ today | date:'fullDate' | uppercase }}</div>
```

- Async Pipe will subscribe to the observable and changes accordingly, regular pipe treats variable has string.
```html
<div> {{ observable | async}}</div>
```

*  Used for data transformation
*  Don't change actual object
* Built-in's : The following are commonly used built-in pipes for data formatting:
	- [`DatePipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/DatePipe): Formats a date value according to locale rules.
	- [`UpperCasePipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/UpperCasePipe): Transforms text to all upper case.
	- [`LowerCasePipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/LowerCasePipe): Transforms text to all lower case.
	- [`CurrencyPipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/CurrencyPipe): Transforms a number to a currency string, formatted according to locale rules.
	- [`DecimalPipe`](https://github.com/angular/angular/blob/main/api/common/DecimalPipe): Transforms a number into a string with a decimal point, formatted according to locale rules.
	- [`PercentPipe`](https://github.com/angular/angular/blob/main/aio/content/guide/api/common/PercentPipe): Transforms a number to a percentage string, formatted according to locale rules.
	
*  For a complete list of built-in pipes, see the [pipes API documentation](https://github.com/angular/angular/blob/main/api/common#pipes "Pipes API reference summary").
- To learn more about using pipes for internationalization (i18n) efforts, see [formatting data based on locale](https://github.com/angular/angular/blob/main/aio/content/guide/guide/i18n-common-format-data-locale "Format data based on locale | Angular").

Create pipes to encapsulate custom transformations and use your custom pipes in template expressions.
```sh
ng g p Pipe_name
```

#### [Pipes and precedence](https://github.com/angular/angular/blob/main/aio/content/guide/pipes-overview.md#pipes-and-precedence)
The pipe operator has a higher precedence than the ternary operator (`?:`), which means `a ? b : c | x` is parsed as `a ? b : (c | x)`. The pipe operator cannot be used without parentheses in the first and second operands of `?:`.

Due to precedence, if you want a pipe to apply to the result of a ternary, wrap the entire expression in parentheses; for example, `(a ? b : c) | x`.


## Custom Pipe

- Implement Pipe
```ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'shorten'
})
export class ShortenPipe implements PipeTransform {
  transform(value: any, limit: number = 10, another: string) {
    if (value.length > limit) {
      return value.substr(0, limit) + ' ...';
    }
    return value;
  }
}
```
- Import in app.module.ts
```ts
ShortenPipe
```
- use
```ts
<div> {{ name | shorten:3:'hi' }}</div>
```

- Custom pipe used has a result filter
```ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter',
  pure: false // to run the pipe without change to the input parameter, by default pipe will not run un-less the input changes to save performance hits
})
export class FilterPipe implements PipeTransform {

  transform(value: any, filterString: string, propName: string): any {
    if (value.length === 0 || filterString === '') {
      return value;
    }
    const resultArray = [];
    for (const item of value) {
      if (item[propName] === filterString) {
        resultArray.push(item);
      }
    }
    return resultArray;
  }

}
```

```ts
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  appStatus = new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve('stable');
    }, 2000);
  });
  servers = [
    {
      instanceType: 'medium',
      name: 'Production',
      status: 'stable',
      started: new Date(15, 1, 2017)
    },
    {
      instanceType: 'large',
      name: 'User Database',
      status: 'stable',
      started: new Date(15, 1, 2017)
    },
    {
      instanceType: 'small',
      name: 'Development Server',
      status: 'offline',
      started: new Date(15, 1, 2017)
    },
    {
      instanceType: 'small',
      name: 'Testing Environment Server',
      status: 'stable',
      started: new Date(15, 1, 2017)
    }
  ];
  
  filteredStatus = ''; // two-way binding for filter string
  
  getStatusClasses(server: {instanceType: string, name: string, status: string, started: Date}) {
    return {
      'list-group-item-success': server.status === 'stable',
      'list-group-item-warning': server.status === 'offline',
      'list-group-item-danger': server.status === 'critical'
    };
  }
  onAddServer() {
    this.servers.push({
      instanceType: 'small',
      name: 'New Server',
      status: 'stable',
      started: new Date(15, 1, 2017)
    });
  }
}
```

```html
<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-10 col-md-8 col-sm-offset-1 col-md-offset-2">
      <input type="text" [(ngModel)]="filteredStatus">
      <br>
      <button class="btn btn-primary" (click)="onAddServer()">Add Server</button>
      <br><br>
      <h2>App Status: {{ appStatus | async}}</h2>
      <hr>
      <ul class="list-group">
        <li
          class="list-group-item"
          *ngFor="let server of servers | filter:filteredStatus:'status'"
          [ngClass]="getStatusClasses(server)">
          <span
            class="badge">
            {{ server.status }}
          </span>
          <strong>{{ server.name | shorten:15 }}</strong> |
          {{ server.instanceType | uppercase }} |
          {{ server.started | date:'fullDate' | uppercase }}
        </li>
      </ul>
    </div>
  </div>
</div>
```