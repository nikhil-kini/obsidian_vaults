###  Feature Module and Routing
* *One Component* can only be registered in one Module.
* *Feature Routing Modules* must always be mentioned above *AppRoutingModule* in `app.module.ts`
### Angular Router

* In a single-page app, you change what the user sees by showing or hiding portions of the display that correspond to particular components, rather than going out to the server to get a new page.
* To handle the navigation from one view to the next, you use the Angular Router. The Router enables navigation by interpreting a browser URL as an instruction to change the view.
* Mapping the different pages in 
	* Import RouterModule(default add when angular config setup)
	* `forRoot()` (should be called only once in the project) method allows us to add multiple route config
	* Default route
	* Dynamic route
	* Wild card route
* Activated Route Service
	* Allows to Read the router data.
	* Allows access to snapshot data.
	* Allows to access data from Route Config.


```ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChildAComponent } from './child-a/child-a.component';
import { ChildBComponent } from './child-b/child-b.component';
import { FirstComponent } from './first/first.component';
import { SecondComponent } from './second/second.component';
import { yourGuardGuard } from './your-guard.guard';

const routes: Routes = [{
  path:'', component:HomeComponent
},
  {path:'first-component/:id/:name', component:FirstComponent,
  canActivateChild: [yourGuardGuard] //to protect only child elements
  children: [ // need to add another router-outlet
    {path: 'child-a', component: ChildAComponent},
    {path: 'child-b', component: ChildBComponent}
  ]
},{
  path:'second-component', component:SecondComponent, canActivate: [yourGuardGuard], // to protect whole routes
  canDeactivate: [yourCanDeactivateGuard]. // to block going back
},{
  path:'404', component:404Component
},
// To pass static data from routing moudle
{ path: 'not-found', component: ErrorPageComponent, data: {message: 'Page not found!'} },
{ // Note the the order matters, it should be last in the list
  path:'**', redirectTo:"/404"
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
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styles: [
  ]
})
export class FirstComponent {
  constructor(private route: ActivatedRoute, private router:Router){}

  user: {};
  queryParams: {};

  ngOnInit (): void{
  // To get acess to Parameter from URL(static)
    alert(this.route.snapshot.paramMap.get('id'));
    alert(this.route.snapshot.paramMap.get('name'));

  // To map the Parameter Dynamically to object from URL
    this.route.params.subscribe(
    (newParam) => {
    this.user.id = newParams['id'],
    this.user.name = newParams['name']}
    );

// To get Query Parameter and fragment from URL
    console.log(this.route.snapshot.queryParams);
    console.log(this.route.snapshot.fragment);
// 
this.route.queryParams.subscribe(
    (qParam) => {
    this.queryParams = qParam}
    );
  }
  this.route.fragment.subscribe();

	// Note: by default it takes route form the root
  changeRoute(rou: string): void{
    this.router.navigate([rou]);
  }

// will give current_Url/child-a
  changeRelativeRoute(): void{
    this.router.navigate(['child-a'], {relativeTo: this.route});
  } 

// will give current_URL/id -> current_Url/child-a/id
  changeRelativeRoute(): void{
    this.router.navigate(['child-a'], {relativeTo: this.route, queryParamsHandling: 'preserve'});
  } 

// add query and fragments
  changeRelativeRoute(id: number, name str): void{
    this.router.navigate(['child-a', id, name], {queryParams: {allow: 1, sec='admin'}, fragment: 'loading'});
  } 
}
```
Note: `ActivatedRoute` give the current active route.

- Note: For **children router** an additional `router-outlet` needs to be added inside the Parent component
```html
<h2>First component</h2> // it is inside the router
<nav>
    <ul>
	    <li><a [routerLink]="['child-a', 1, 'nikhil']">Child A</a></li>
	    <li><a [routerLink]="['child-a', 1, 'nikhil']"
	    [queryParams]="{allow: 1, sec='admin'}"
	    fragment = "loading">Child A</a></li>
	    
        <li><a routerLink="child-a">Child A</a></li>
        <li><a routerLink="child-b">Child B</a></li>
        <li><a (click)="changeRoute('child-b')">Child B</a></li>
    </ul>
</nav>

<router-outlet></router-outlet>
```
Note:
- The paths are relative, use `/` for path from root
```html
<a routerLink="child-a">Child A</a>
```
is `current_Url/child-a` i.e. `domain_name/parent/child-a` where has
```html
<a routerLink="/child-a">Child A</a>
```
is `domain_name/child-a`

- `domain_name/child-a/1/nikhil`
```html
<li><a [routerLink]="['child-a', 1, 'nikhil']">Child A</a></li>
```
- `domain_name/child-a/1/nikhil?allow=1&sec=admin#loading`
```html
<li><a [routerLink]="['child-a', 1, 'nikhil']"
	    [queryParams]="{allow: 1, sec='admin'}"
	    fragment = "loading">Child A</a></li>
```
### RouterLinkActive
- To add CSS Class to the a object based on the selected Router Link
```html
<ul class="nav nav-tabs">
        <li role="presentation"
            routerLinkActive="active_CSS_CLASS"
            [routerLinkActiveOptions]="{exact: true}">
          <a routerLink="/">Home</a>
        </li>
        <li role="presentation"
            routerLinkActive="active_CSS_CLASS">
          <a routerLink="servers">Servers</a>
        </li>
        <li role="presentation"
            routerLinkActive="active_CSS_CLASS">
          <a [routerLink]="['users']">Users</a>
        </li>
      </ul>
```
Note: `[routerLinkActiveOptions]="{exact: true}"` is  to say apply class on the exact link and not on partial

#### Route Guards
To generate route-guard.
```sh
ng g guard name_guard
```
Implementation 
* canActivate
* canActivateChild
* canDeactivate
* canLoad
* Resolve
* VID 13:00:00

**canActivate,  canActivateChild**
- mention in router Module
- implementation on activated route service
```ts
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  CanActivateChild
} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';

import { AuthService } from './auth.service';

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.authService.isAuthenticated()
      .then(
        (authenticated: boolean) => {
          if (authenticated) {
            return true;
          } else {
            this.router.navigate(['/']);
          }
        }
      );
  }

  canActivateChild(route: ActivatedRouteSnapshot,
                   state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.canActivate(route, state);
  }
}
```

```ts
export class AuthService {
  loggedIn = false;

  isAuthenticated() {
    const promise = new Promise(
      (resolve, reject) => {
        setTimeout(() => {
          resolve(this.loggedIn);
        }, 800);
      }
    );
    return promise;
  }

  login() {
    this.loggedIn = true;
  }

  logout() {
    this.loggedIn = false;
  }
}
```

**canDeactivateGuard**
- mention in router module
- Implementation of the canDeactivateService and interface
- Component must implement the canDeactivate
```ts
import { Observable } from 'rxjs/Observable';
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

export interface CanComponentDeactivate {
  canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean;
}

export class CanDeactivateGuard implements CanDeactivate<CanComponentDeactivate> {

  canDeactivate(component: CanComponentDeactivate,
                currentRoute: ActivatedRouteSnapshot,
                currentState: RouterStateSnapshot,
                nextState?: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return component.canDeactivate();
  }
}
```
implementation in component
```ts
 canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.allowEdit) {
      return true;
    }
    if ((this.serverName !== this.server.name || this.serverStatus !== this.server.status) && !this.changesSaved) {
      return confirm('Do you want to discard the changes?');
    } else {
      return true;
    }
  }
```

## Static data from routing module

```ts
{ path: 'not-found', component: ErrorPageComponent, data: {message: 'Page not found!'} },
```

```ts
export class ErrorPageComponent implements OnInit {
  errorMessage: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
	// for static data
    this.errorMessage = this.route.snapshot.data['message'];
  }

}
```

## Dynamic Data (Resolve)
- need to implement the resolver
```ts
{ path: ':id', component: ServerComponent, resolve: {server: ServerResolver} },
```

```ts
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';

import { ServersService } from '../servers.service';

interface Server {
  id: number;
  name: string;
  status: string;
}

@Injectable()
export class ServerResolver implements Resolve<Server> {
  constructor(private serversService: ServersService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Server> | Promise<Server> | Server {
    return this.serversService.getServer(+route.params['id']);
  }
}
```

```ts
export class ErrorPageComponent implements OnInit {
  errorMessage: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
	// for dynamic data
    this.route.data.subscribe(
      (data: Data) => {
        this.errorMessage = data['message'];
      }
    );
  }

}
```


