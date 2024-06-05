In general:
> Browsers are able to optimize rendering flows. In summary, we should always try to create our animations using CSS transitions/animations where possible. If your animations are really complex, you can may have to rely on JavaScript-based animations instead.

> CSS transitions is hard to apply while DOM is manipulated by the angular.

```ts
import:[
BrowserAnimationModule  // app.moudle.ts
]
```
Animate is the transition between one state to another state, so define states.
1 state =>transition=> 2 state
## Trigger

set-up
- Identify the block to animate
```html
<div [@divState]="state"> </div>
```
- Set it in the .ts file
```ts
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  animations: [
    trigger('divState', [
      state('normal', style({
        'background-color': 'red',
        transform: 'translateX(0)'
      })),
      state('highlighted', style({
        'background-color': 'blue',
        transform: 'translateX(100px)'
      })),
      transition('normal <=> highlighted', animate(300)), // transition applied to both states
      // transition('highlighted => normal', animate(800)) // transition applied only to transition from `highlighted` to `normal`
    ])]
    })
```
-  Switch between states using a instance variable,
```ts
export class AppComponent {
  state = 'normal';


  onAnimate() {
    this.state == 'normal' ? this.state = 'highlighted' : this.state = 'normal';
  }

```
 - To execute the code at start and done
 ```ts
 <div
        style="width: 100px; height: 100px"
        [@divState]="state"
        (@divState.start)="animationStarted($event)"
        (@divState.done)="animationEnded($event)">
</div>
```


```ts
import { Component } from '@angular/core';
import {
  trigger,
  state,
  style,
  transition,
  animate,
  keyframes,
  group
} from '@angular/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  animations: [
    trigger('divState', [
      state('normal', style({
        'background-color': 'red', // camelCase backgroundColor, keep it uniform across for animation to work properly
        transform: 'translateX(0)'
      })),
      state('highlighted', style({
        'background-color': 'blue',
        transform: 'translateX(100px)'
      })),
      transition('normal <=> highlighted', animate(300)),
      // transition('highlighted => normal', animate(800))
    ]),
    trigger('wildState', [
      state('normal', style({
        'background-color': 'red',
        transform: 'translateX(0) scale(1)'
      })),
      state('highlighted', style({
        'background-color': 'blue',
        transform: 'translateX(100px) scale(1)'
      })),
      state('shrunken', style({
        'background-color': 'green',
        transform: 'translateX(0) scale(0.5)'
      })),
      transition('normal => highlighted', animate(300)),
      transition('highlighted => normal', animate(800)),
      transition('shrunken <=> *', [ // to pass the in between transition  at begining
        style({
          'background-color': 'orange'
        }),
        animate(1000, style({ // trasition stlye leaving. note inside animate
          borderRadius: '50px'
        })),
        animate(500)
      ])
    ]),
    trigger('list1', [
      state('in', style({
        opacity: 1,
        transform: 'translateX(0)'
      })),
      transition('void => *', [ // void is special case for no-existing
        style({
          opacity: 0,
          transform: 'translateX(-100px)'
        }),
        animate(300)
      ]),
      transition('* => void', [
        animate(300, style({
          transform: 'translateX(100px)',
          opacity: 0
        }))
      ])
    ]),
    trigger('list2', [
      state('in', style({
        opacity: 1,
        transform: 'translateX(0)'
      })),
      transition('void => *', [
        animate(1000, keyframes([ // in 1sec how many transition should occur
          style({
            transform: 'translateX(-100px)',
            opacity: 0,
            offset: 0
          }),
          style({
            transform: 'translateX(-50px)',
            opacity: 0.5,
            offset: 0.3
          }),
          style({
            transform: 'translateX(-20px)',
            opacity: 1,
            offset: 0.8
          }),
          style({
            transform: 'translateX(0px)',
            opacity: 1,
            offset: 1
          })
        ]))
      ]),
      transition('* => void', [
        group([  // animation that happen asycronaslly. both animation happen at the same time
          animate(300, style({
            color: 'red'
          })),
          animate(800, style({
            transform: 'translateX(100px)',
            opacity: 0
          }))
        ])
      ])
    ]),
  ]
})
export class AppComponent {
  state = 'normal';
  wildState = 'normal';
  list = ['Milk', 'Sugar', 'Bread'];

  onAnimate() {
    this.state == 'normal' ? this.state = 'highlighted' : this.state = 'normal';
    this.wildState == 'normal' ? this.wildState = 'highlighted' : this.wildState = 'normal';
  }

  onShrink() {
    this.wildState = 'shrunken';
  }

  onAdd(item) {
    this.list.push(item);
  }

  onDelete(item) {
    this.list.splice(this.list.indexOf(item), 1);
  }

  animationStarted(event) {
    console.log(event);
  }

  animationEnded(event) {
    console.log(event);
  }
}
```
```html
<div class="container">
  <div class="row">
    <div class="col-xs-12">
      <h1>Animations</h1>
      <button class="btn btn-primary" (click)="onAnimate()">Animate!</button>
      <button class="btn btn-primary" (click)="onShrink()">Shrink!</button>
      <hr>
      <div
        style="width: 100px; height: 100px"
        [@divState]="state"
        (@divState.start)="animationStarted($event)"
        (@divState.done)="animationEnded($event)">
      </div>
      <br>
      <div
        style="width: 100px; height: 100px"
        [@wildState]="wildState">
      </div>
    </div>
  </div>
  <hr>
  <div class="row">
    <div class="col-xs-12">
      <input type="text" #input>
      <button class="btn btn-primary" (click)="onAdd(input.value)">Add Item!</button>
      <hr>
      <ul class="list-group">
        <li
          class="list-group-item"
          (click)="onDelete(item)"
          [@list1]
          *ngFor="let item of list">
          {{ item }}
        </li>
      </ul>
    </div>
  </div>
  <hr>
  <div class="row">
    <div class="col-xs-12">
      <ul class="list-group">
        <li
          class="list-group-item"
          (click)="onDelete(item)"
          [@list2]
          *ngFor="let item of list">
          {{ item }}
        </li>
      </ul>
    </div>
  </div>

</div>
```