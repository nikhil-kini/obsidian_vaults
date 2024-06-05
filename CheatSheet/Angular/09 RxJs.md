[read this article -  My top 6 ‘Similar, but different’ RxJS operators ](https://medium.com/geekculture/my-top-6-similar-but-different-rxjs-operators-52241096ef96)
## Observables

**Observables** represent data streams that can emit values over time, and **Observers** subscribe to these Observables to receive and react to the emitted values. Subscriptions enable the communication between **Observables** and **Observers**.

**Observer** is the one which allows to emit stream of values, or throw error and also trigger when function gets completed and it is kept private. Its is defined inside observable.

**Observable** doesn't allow values to emit on its behalf we can only subscribe and get stream of values.

Note: Observable names are generally have `$` at the end has a convention ex: `numberObservable$`
```ts
// Import the necessary RxJS modules
import { Observable } from 'rxjs';

// Create an Observable that emits numbers from 1 to 5
const numberObservable = new Observable((observer) => {
  let count = 1;
  const interval = setInterval(() => {
    if (count <= 5) {
      observer.next(count);
      count++;
    } else {
      observer.complete();
      clearInterval(interval);
    }
  }, 1000);

  // Cleanup logic when the subscription is unsubscribed
  return () => {
    clearInterval(interval);
  };
});

// Subscribe to the Observable to receive values
const subscription = numberObservable.subscribe({
  next: (value) => console.log(value),
  complete: () => console.log('Observable completed.'),
});
```

## **3 Core RxJs Concepts - Error, Completion and Subscriptions**

- subscription : callback which emits the stream of values
- error : when the stream of values combined didn't work properly and throws an error than error callback is called.
- complete : completion callback is called when stream of values are completely emitted successfully.
**Note**: Both error and completion cannot happen, either one of those will be triggered based on the stream of values emitted successfully or failed.

Observer functions to emit, complete and throwing error.
next():  
```ts
observer.next() // function which emits values
```

complete() :  
```ts
observer.complete() //function which emits complete value
```

error():  
```ts
observer.error();
```

## Always Unsubscribe the create observable 
```ts
private subscription: Subscription;

this.subscription = observable.subscribe();

this.subscription.unsubscribe();  // ngOnDestroy(){}
```

## Imperative design vs Reactive design
**Imperative**
```ts
courses$.subscribe(courses => {
this.beginnerCourses = course.filter(course => course.category == 'BIGINNER');
this.beginnerCourses = course.filter(course => course.category == 'ADVANCED');
}, noop, () => console.log('completed'));
```
- Too much of logic inside the subscribe method doesn't help good in scaling complexity which leads to nested subscribe call and reach callback hell. Its not recommended to use nested subscribe also.

**Reactive**
Create 2 observables Beginner and advanced ,which is a definition and its not directly mutable and then use map operator to filter. this wont create nested subscriptions.
```ts
beginnerCourses$: Observable<Course[]>;
advancedCourses$: Observable<Course[]>;

this.beginnerCourse$ = courses$.pipe(map(courses => courses.filter(course => course.category == 'BEGINNER')));
this.beginnerCourse$ = courses$.pipe(map(courses => courses.filter(course => course.category == 'ADVANCED')));
```

## Best practices when working with RxJS:

1. **Keep Observables Short-Lived**: Avoid long-lived Observables that may lead to memory leaks. Use `takeUntil`, `takeWhile`, or other completion mechanisms to properly manage subscriptions.
2. **Use Subjects Judiciously**: Subjects are powerful but can lead to loss of data control. Prefer using dedicated Observables and operators whenever possible.
3. **Avoid Nested Subscriptions**: Nesting subscriptions can lead to confusing code and make it harder to manage resources properly. Instead, use operators like `mergeMap`, `switchMap`, `concatMap`, etc., to flatten and manage Observables effectively.
4. **Error Handling**: Always include error handling in your Observables to gracefully handle errors and prevent application crashes. Use operators like `catchError`, `retry`, etc., as appropriate.
5. **Use the Latest RxJS Version**: RxJS is actively developed, and new features and optimizations are added regularly. Ensure you are using the latest version to benefit from the latest improvements.
## Basic Operators

### from

Turn an array, promise, or iterable into an observable.
```js
import { from } from 'rxjs';

//emit array as a sequence of values
// The output will be: 1,2,3,4,5
from([1, 2, 3, 4, 5])
    .subscribe(data => ...);
```

### of

Emit variable amount of values in a sequence and then emits a complete notification.
```js
import { of } from 'rxjs';

// Emits any number of provided values in sequence
// The output will be: 1,2,3,4,5
of(1, 2, 3, 4, 5)
    .subscribe(data => ...);
```

### pipe

Allows executing operators on emitted values in the order they were defined.
```js
import { of, pipe } from 'rxjs';

of(1,2,3,4).
    pipe(
        op1(),
        op2(),
        op3()
    )
    .subscribe(data => ...)

// The emitted values will be the result of op3(op2(op1(value)))
```

### tap

Receives a value, takes an action which won't affect the value  and returns the same value.

_Useful for side effects as logging and such_.
```js
import { of, pipe } from 'rxjs';
import { tap } from 'rxjs/operators';

of(1,2,3,4).
    pipe(tap(value => console.log(`The value is ${value}`)))
    .subscribe(data => ...)

// The emitted value will be 1,2,3,4
```

## Transformation

### concatMap

Maps each value to an Observable, then flattens all of these inner Observables using concatAll.
![[concatMap.png]]

_Just like concatAll but applying a `map` function to each value (which is an Observable)._
```js
import { pipe } from 'rxjs';
import { concatMap } from 'rxjs/operators';

const ids = [1,2,3,4];
const data = [];

from(ids)
    .pipe(
        concatMap(id => this.http.get('apiurl/resource/' + id))
    )
    .subscribe(httpResponse => this.data.push(httpResponse));
```

### defaultIfEmpty

Allows setting a default value to emit if none was emitted from the source.

```js
import { of, pipe } from 'rxjs';
import { defaultIfEmpty } from 'rxjs/operators';
// We create an empty observable
of()
    .pipe(defaultIfEmpty('Empty!'))
    .subscribe(data => ...)
// This will emit 'Empty!'
```

### map

Applies a function to each emitted value.
![[Map.png]]
```js
import { of, pipe } from 'rxjs';
import { map } from 'rxjs/operators';

of(1,2,3,4)
    .pipe(map(value => value * 2))
    .subscribe(data => ...)
// This will emit 2,4,6,8
```

### reduce

Reduces the values based on a function and a seed into one reduced value.

```js
import { of, pipe } from 'rxjs';
import { reduce } from 'rxjs/operators';

of(1,2,3,4)
    .pipe(
        reduce((acc, singleValue) => acc + singleValue, 0)
    )
    .subscribe(data => ...)
// This will emit the value 10
```

### mergeMap (also called flatMap)

Maps each value to an Observable, then flattens all of these inner Observables using mergeAll.
![[mergeMap.png]]
_Just like concatMap but each subsciption is not sequential (does not wait for the previous one to complete)._

Graphical example:
```
 --(1)--------------(3)-------(5)----------------|->
   (10)--(10)--(10)-|->
 
 [mergeMap(i => 10 * i -- 10 * i -- 10 * 1 -|)]
 
 --(10)--(10)--(10)-(30)--(30)(50)(30)(50)--(50)-|->
```

### forkJoin

Given observables emits the last emitted value of each observables.
![[forkJoin.png]]
```js
import { ajax } from 'rxjs/ajax';
import { forkJoin } from 'rxjs';

const sources = {
    google: ajax.getJSON('https://api.github.com/users/google'),
    microsoft: ajax.getJSON('https://api.github.com/users/microsoft'),
    users: ajax.getJSON('https://api.github.com/users')
}

/*
  when all observables complete, provide the last
  emitted value from each as dictionary
*/
forkJoin(
  // as of RxJS 6.5+ we can use a dictionary of sources
  sources
)
    .subscribe(console.log);
    
// The value emitted will be { google: object, microsoft: object, users: array }
```

## Error Handling

### catchError

Allows to handle error in an observable sequence.
```js
import { throwError, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
//emit error
const source = throwError('This is an error!');
//gracefully handle error, returning observable with error message
const example = source.pipe(catchError(val => of(`I caught: ${val}`)));
//output: 'I caught: This is an error'
const subscribe = example.subscribe(val => console.log(val));
```

### throwIfEmpty

If the source does not emit anything at completion, this operator will force that to be considered an error.
```js
import { of, pipe } from 'rxjs';
import { throwIfEmpty } from 'rxjs/operators';
// We create an empty observable
of()
    .pipe(throwIfEmpty)
    .subscribe(
        data => ...,
        // This would print the "no value" error message
        err => console.log(err.message)
    )
```

### retry

Useful for things like HTTP requests that may fail. Allows us to re-subscribe and retry up to a number of times.
```js
import { interval, of, throwError } from 'rxjs';
import { mergeMap, retry } from 'rxjs/operators';

//emit value every 1s
const source = interval(1000);
const example = source.pipe(
  mergeMap(val => val > 5
      ? throwError('Error!')
      : of(val)
  ),
  //retry 2 times on error
  retry(2)
);
```

## Filtering to Multiple Elements

### skip

Skips a number of elements from the beginning of the source.
```js
import { of, pipe } from 'rxjs';
import { skip } from 'rxjs/operators';

of(1,2,3,4,5)
    // Skips the first 3 elements
    .pipe(skip(3))
    .subscribe(data => ...);
```

### skipWhile

Skips elements from the beginning of the source while the condition resolves to `true`. Once the condition resolves to `false`, **all the next values will be emitted.**
```js
import { of, pipe } from 'rxjs';
import { skipWhile } from 'rxjs/operators';

of(3,2,1,5,1,3)
    // Skips the first 3 elements
    .pipe(skipWhile(value => value < 4))
    .subscribe(data => ...);
```

### take

Takes a specific number of elements from the beginning of the source.
```js
import { of, pipe } from 'rxjs';
import { take } from 'rxjs/operators';

of(5,4,3,2,1)
    // Emits the first 2 elements
    .pipe(take(2))
    .subscribe(data => ...);
```

### distinct

Allows us to eliminate duplicated elements from a source. When a function is provided, that function will be used for determining the duplication.
```js
import { of, pipe } from 'rxjs';
import { distinct } from 'rxjs/operators';

of(1,1,2,2,3,3,4,4)
    .pipe(distinct())
    .subscribe(data => ...);

---

const values = [
 { id:1 , value:0 },
 { id:2 , value:1 },
 { id:1 , value:2 },
]

of(values)
    .pipe(distinct(value => value.id))
    .subscribe(data => ...);

// The emitted values will be { id:1 , value:0 } and { id:2 , value:1 }.
```

### distinctUntilChanged

Drops the value if the previous emitted value is identical to the one being evaluated.
```js
import { of, pipe } from 'rxjs';
import { distinctUntilChanged } from 'rxjs/operators';

of(1,1,2,1,3,3,2)
    // The emitted values will be 1,2,1,3,2
    .pipe(distinctUntilChanged())
    .subscribe(data => ...);
```

### filter

Filters the values from the source based on a condition applied to each value.
```js
import { of, pipe } from 'rxjs';
import { filter } from 'rxjs/operators';

of(1,2,3,4,5)
    // The emitted values will be 2, 4
    .pipe(filter(value => value % 2 == 0))
    .subscribe(data => ...);
```

## Filtering to Single Element

### first

Emits the first value of the source and unsubscribes.
```js
import { of, pipe } from 'rxjs';
import { first } from 'rxjs/operators';

of(1,2,3,4,5)
    .pipe(first()) // Only emits value 1
    .subscribe(data => ...);
```

### elementAt

Emits the element at the specified position. Throws _ArgumentOutOfRangeError_ if index < 0 or the Observable completes before reaching the index position.
```js
import { of, pipe } from 'rxjs';
import { elementAt } from 'rxjs/operators';

of(1,2,3,4,5)
    .pipe(elementAt(2)) // Only emits value 3
    .subscribe(data => ...);
```

### find

Allow to find the first element the match the condition, emit it and then unsubscribe.
```js
import { of, pipe } from 'rxjs';
import { find } from 'rxjs/operators';

of(1,2,3,4,2)
    .pipe(find(value => value == 2)) // Only emits one value 2
    .subscribe(data => ...);
```

### single

Just like `find` operator but emits an error if more than one value is found or none is emitted from the source.
```js
import { of, pipe } from 'rxjs';
import { single } from 'rxjs/operators';

 // Prints "2"
of(1,2,3,4)
    .pipe(find(value => value == 2))
    .subscribe(
        data => console.log(data),
        err => console.log('error')
    );

// Prints "undefined"
of(1,3,4)
    .pipe(find(value => value == 2))
    .subscribe(
        data => console.log(data),
        err => console.log('undefined')
    );

// Prints "error"
of(1,2,3,4,2)
    .pipe(find(value => value == 2))
    .subscribe(
        data => console.log(data),
        err => console.log('error')
    );

// Prints "error"
of()
    .pipe(find(value => value == 2))
    .subscribe(
        data => console.log(data),
        err => console.log('error')
    );
```

## Grouping Observables

### concatAll

This operator subscribes to each inner Observable only after the previous one is completed. Then, it returns the result as a single Observable.

_Useful if order matters._
```js
import { of, pipe } from 'rxjs';
import { concatAll } from 'rxjs/operators';

const abc = ['a', 'b', 'c'];
const def = ['d', 'e', 'f'];

// We build an Observable of Observables
of(of(...abc), of(...def))
    .pipe(concatAll())
    .subscribe(data => ...);
    
// This will emit the values from the first observable (abc) and, after it completes, the values from the second observable (def).
```


## Grouping Values

### toArray

Collects all the emitted values into an array which is returned only after the source completes.
```js
import { interval } from 'rxjs';
import { toArray, take } from 'rxjs/operators';

// This will emit [0,1,2]
interval(100)
    .pipe(
        take(3),
        toArray()
    )
    .subscribe(data => ...);
```

### groupBy

Groups observables based on a value into one grouped observable.
```js
import { from, pipe } from 'rxjs';
import { groupBy, mergeMap, toArray } from 'rxjs/operators';

const values = [
  { id:0, value: 0 },
  { id:1, value: 0 },
  { id:2, value: 1 },
  { id:3, value: 2 },
];

from(values)
    .pipe(
        // Group by value field
        groupBy(data => data.value),
        // Turn each group Observable into arrays
        mergeMap(group => group.pipe(toArray()))
    )
    .subscribe(data => ...);

/*
This will emit:
  [{ id:0, value: 0 }, { id:1, value: 0 }]
  [{ id:2, value: 1 }]
  [{ id:3, value: 2 }]
*/
```

## Other helpful observable

#### **merge()**

- Purpose: Merge multiple Observables into one.

Code Example:
```ts
import { of, merge } from 'rxjs';

// Create two Observables
const observable1 = of('A', 'B', 'C');
const observable2 = of('X', 'Y', 'Z');

// Merge the two Observables
const mergedObservable = merge(observable1, observable2);

// Subscribe to the merged Observable
const subscription = mergedObservable.subscribe({
  next: (value) => console.log(value),
});
// Output: 'A', 'B', 'C', 'X', 'Y', 'Z'
```

#### **switchMap()**

- Purpose: Transform each value emitted by the source Observable into a new Observable and emit its values,

Code Example:
```ts
import { of } from 'rxjs';
import { switchMap } from 'rxjs/operators';

// Create an Observable that emits 'user' objects
const userObservable = of({ id: 1, name: 'John' }, { id: 2, name: 'Jane' });

// Use the switchMap() operator to get user names
const nameObservable = userObservable.pipe(switchMap((user) => of(user.name)));

// Subscribe to the transformed Observable
const subscription = nameObservable.subscribe({
  next: (name) => console.log(name),
});
// Output: 'John', 'Jane'
```

#### **scan()**

- Purpose: Apply an accumulator function over the values emitted by the Observable and emit each intermediate result.

Code Example:
```ts
import { of } from 'rxjs';
import { scan } from 'rxjs/operators';

// Create an Observable that emits numbers from 1 to 5
const numberObservable = of(1, 2, 3, 4, 5);

// Use the scan() operator to accumulate the sum of emitted values
const sumObservable = numberObservable.pipe(scan((acc, value) => acc + value, 0));

// Subscribe to the accumulated Observable
const subscription = sumObservable.subscribe({
  next: (sum) => console.log(sum),
});
// Output: 1, 3, 6, 10, 15
```

#### **debounceTime()**

- Purpose: Delay the emission of values from the source Observable until a specified time has passed without any new values being emitted.

Code Example:
```ts
import { fromEvent } from 'rxjs';
import { debounceTime } from 'rxjs/operators';

// Create an Observable from a button click event
const button = document.getElementById('myButton');
const clickObservable = fromEvent(button, 'click');

// Use the debounceTime() operator to emit the latest click after 500ms
const debouncedObservable = clickObservable.pipe(debounceTime(500));

// Subscribe to the debounced Observable
const subscription = debouncedObservable.subscribe(() => console.log('Button clicked!'));
```

#### **takeUntil()**

- Purpose: Emit values from the source Observable until another Observable emits a value.

Code Example:
```ts
import { interval, timer } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

// Create an Observable that emits values every 1 second
const sourceObservable = interval(1000);

// Create an Observable

 that emits a value after 5 seconds
const timerObservable = timer(5000);

// Use the takeUntil() operator to stop emitting values after 5 seconds
const takeUntilObservable = sourceObservable.pipe(takeUntil(timerObservable));

// Subscribe to the takeUntil Observable
const subscription = takeUntilObservable.subscribe({
  next: (value) => console.log(value),
});
// Output: 0, 1, 2, 3, 4
```

#### **combineLatest()**

Purpose: Combine the latest values from multiple Observables and emit a new value whenever any of the combined Observables emit.

Code Example:
```ts
import { interval, combineLatest } from 'rxjs';

// Create two Observables that emit values every 1 and 2 seconds, respectively
const observable1 = interval(1000);
const observable2 = interval(2000);

// Use the combineLatest() operator to combine the latest values
const combinedObservable = combineLatest(observable1, observable2);

// Subscribe to the combined Observable
const subscription = combinedObservable.subscribe({
  next: ([value1, value2]) => console.log(`Observable 1: ${value1}, Observable 2: ${value2}`),
});
// Output: "Observable 1: 0, Observable 2: 0"
//         "Observable 1: 1, Observable 2: 0"
//         "Observable 1: 1, Observable 2: 1"
//         "Observable 1: 2, Observable 2: 1"
//         ...
```

#### **shareReplay()**

Purpose: Share the emitted values from the source Observable with multiple subscribers, replaying the last emitted value to new subscribers.

Code Example:
```ts
import { interval } from 'rxjs';
import { shareReplay } from 'rxjs/operators';

// Create an Observable that emits values every 1 second
const sourceObservable = interval(1000);

// Use the shareReplay() operator to share the emitted values
const sharedObservable = sourceObservable.pipe(shareReplay());

// Subscribe to the shared Observable
const subscription1 = sharedObservable.subscribe((value) => console.log(`Subscriber 1: ${value}`));
const subscription2 = sharedObservable.subscribe((value) => console.log(`Subscriber 2: ${value}`));
// Both subscribers will receive the same sequence of values
```

#### **takeWhile()**

Purpose: Emit values from the source Observable until a specified condition becomes false.

Code Example:
```ts
import { interval } from 'rxjs';
import { takeWhile } from 'rxjs/operators';

// Create an Observable that emits values every 1 second
const sourceObservable = interval(1000);

// Use the takeWhile() operator to stop emitting values after reaching 5
const takeWhileObservable = sourceObservable.pipe(takeWhile((value) => value <= 5));

// Subscribe to the takeWhile Observable
const subscription = takeWhileObservable.subscribe({
  next: (value) => console.log(value),
});
// Output: 0, 1, 2, 3, 4, 5
```

#### **startWith()**

Purpose: Prepend a specified value to the emissions of the source Observable.

Code Example:
```ts
import { of } from 'rxjs';
import { startWith } from 'rxjs/operators';

// Create an Observable that emits numbers from 1 to 3
const numberObservable = of(1, 2, 3);

// Use the startWith() operator to prepend 0 to the emissions
const observableWithStart = numberObservable.pipe(startWith(0));

// Subscribe to the Observable with the starting value
const subscription = observableWithStart.subscribe({
  next: (value) => console.log(value),
});
// Output: 0, 1, 2, 3
```

#### **bufferTime()**

Purpose: Periodically emit an array of collected values from the source Observable within specified time intervals.

Code Example:
```ts
import { interval } from 'rxjs';
import { bufferTime } from 'rxjs/operators';

// Create an Observable that emits values every 500ms
const sourceObservable = interval(500);

// Use the bufferTime() operator to collect values over 2 seconds
const bufferedObservable = sourceObservable.pipe(bufferTime(2000));

// Subscribe to the buffered Observable
const subscription = bufferedObservable.subscribe({
  next: (buffer) => console.log('Buffered Values:', buffer),
});
// Output: Buffer 1: [0, 1, 2]
//         Buffer 2: [3, 4, 5, 6]
//         ...
```

#### **switchMapTo()**

Purpose: Map each value emitted by the source Observable to the same inner Observable and emit its values.

Code Example:
```ts
import { of } from 'rxjs';
import { switchMapTo } from 'rxjs/operators';

// Create an Observable that emits 'user' objects
const userObservable = of({ id: 1, name: 'John' }, { id: 2, name: 'Jane' });

// Create an inner Observable that emits roles
const roleObservable = of('Admin', 'User');

// Use the switchMapTo() operator to map each user to the roles Observable
const rolesForUsers = userObservable.pipe(switchMapTo(roleObservable));

// Subscribe to the roles Observable
const subscription = rolesForUsers.subscribe({
  next: (role) => console.log(role),
});
// Output: 'Admin', 'User', 'Admin', 'User'
```

#### **exhaustMap()**

Purpose: Map each value emitted by the source Observable to an inner Observable, but ignore new inner Observables until the current one completes.

Code Example:
```ts
import { interval, fromEvent } from 'rxjs';
import { exhaustMap, take } from 'rxjs/operators';

// Create an Observable that emits values every 1 second
const sourceObservable = interval(1000);

// Create an Observable from a button click event
const button = document.getElementById('myButton');
const clickObservable = fromEvent(button, 'click');

// Use the exhaustMap() operator to ignore new clicks until the current interval completes
const clickedObservable = clickObservable.pipe(exhaustMap(() => sourceObservable.pipe(take(5))));

// Subscribe to the clicked Observable
const subscription = clickedObservable.subscribe({
  next: (value) => console.log(value),
});
// Output: 0, 1, 2, 3, 4
// (Subsequent clicks during the 5 seconds are ignored)
```

 