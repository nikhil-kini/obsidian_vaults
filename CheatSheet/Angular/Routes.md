### Angular Router

* In a single-page app, you change what the user sees by showing or hiding portions of the display that correspond to particular components, rather than going out to the server to get a new page.
* To handle the navigation from one view to the next, you use the Angular Router. The Router enables navigation by interpreting a browser URL as an instruction to change the view.
* Mapping the different pages in [[Additional Info]]
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

const routes: Routes = [
  {path:'first-component/:id/:name', component:FirstComponent,
  children: [
    {path: 'child-a', component: ChildAComponent},
    {path: 'child-b', component: ChildBComponent}
  ]
},{
  path:'second-component', component:SecondComponent, canActivate: [yourGuardGuard]
},{
  path:'**', component:FirstComponent
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
  // To get Parameter
    alert(this.route.snapshot.paramMap.get('id'));
    alert(this.route.snapshot.paramMap.get('name'));

  // To map the Parameter Dynamically
    this.route.params.subscribe(
    (newParam) => {
    this.user.id = newParams['id'],
    this.user.name = newParams['name']}
    );

// To get Query Parameter and fragment
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

// add query and fragments
  changeRelativeRoute(id: number, name str): void{
    this.router.navigate(['child-a', id, name], {queryParams: {allow: 1, sec='admin'}, fragment: 'loading'});
  } 
}
```
Note: `ActivatedRoute` give the current active route.

- Note: For **children router** an additional `router-outlet` needs to be added inside the Parent component
```html
<h2>First component</h2>
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
* canActivate
* canActivateChild
* canDeactivate
* canLoad
* Resolve
* VID 13:00:00

* **Router Service.**
* **Feature Module and Routing**
	* *One Component* can only be registered in one Module.
	* *Feature Routing Modules* must always be mentioned above *AppRoutingModule* in `app.module.ts`
* **Nested Routes and Child Routes**
