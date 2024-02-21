### Service Creation

- No decorator required.
```ts
export class LoggingService {
  logStatusChange(status: string) {
    console.log('A server status changed, new status: ' + status);
  }
}
```
- The new instance of service, is handled by Angular by Dependency injection.
- Instance initiation depends on where where the `providers: []` is defined. ref below
```ts
import { Component, Input } from '@angular/core';

import { LoggingService } from '../logging.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers: [LoggingService]              // New instance for this component
})
export class AccountComponent {
  @Input() account: {name: string, status: string};
  @Input() id: number;

  constructor(private loggingService: LoggingService) {}

  onSetTo(status: string) {
    this.loggingService.logStatusChange(status);
  }
}
```

## Hierarchical Injector
- Depends on where the service  `providers: [ ServiceClass]` is placed.
- Dependencies will be share between parent and all its children and descendants.
![[Hierarchical Injector.png]]


## Maintain same service across all components and service-service injection

- same service across all components - Provide the service in `app.component.ts` or `app.module.ts`
- service-service injection - Provide the service in  `app.module.ts`

```ts
import { EventEmitter, Injectable } from '@angular/core';

import { LoggingService } from './logging.service';

@Injectable()         // Only on the Service where external-service is injected
export class AccountsService {
  accounts = [
    {
      name: 'Master Account',
      status: 'active'
    },
    {
      name: 'Testaccount',
      status: 'inactive'
    },
    {
      name: 'Hidden Account',
      status: 'unknown'
    }
  ];

/////// Event property
  statusUpdated = new EventEmitter<string>();

  constructor(private loggingService: LoggingService) {} // App Module definition

  addAccount(name: string, status: string) {
    this.accounts.push({name: name, status: status});
    this.loggingService.logStatusChange(status);
  }

  updateStatus(id: number, status: string) {
    this.accounts[id].status = status;
    this.loggingService.logStatusChange(status);
  }
}
```

```ts
import { Component, Input } from '@angular/core';

import { LoggingService } from '../logging.service';
import { AccountsService } from '../accounts.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  // providers: [LoggingService]
})
export class AccountComponent {
  @Input() account: {name: string, status: string};
  @Input() id: number;

  constructor(private loggingService: LoggingService,
              private accountsService: AccountsService) {}

  onSetTo(status: string) {
    this.accountsService.updateStatus(this.id, status);
    // this.loggingService.logStatusChange(status);

////// Emitting an event.
    this.accountsService.statusUpdated.emit(status); 
  }
}
```

```ts
import { Component } from '@angular/core';

import { LoggingService } from '../logging.service';
import { AccountsService } from '../accounts.service';

@Component({
  selector: 'app-new-account',
  templateUrl: './new-account.component.html',
  styleUrls: ['./new-account.component.css'],
  // providers: [LoggingService]
})
export class NewAccountComponent {

  constructor(private loggingService: LoggingService,
              private accountsService: AccountsService) {

//  The Event is captured in another component
    this.accountsService.statusUpdated.subscribe(
      (status: string) => alert('New Status: ' + status)
    );
  }

  onCreateAccount(accountName: string, accountStatus: string) {
    this.accountsService.addAccount(accountName, accountStatus);
    // this.loggingService.logStatusChange(accountStatus);
  }
}
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
