[[Angular]]
## SPA
Stands for Single Page Application, it dose not make requests to server for every URL.
All the **compilation** is done in the **client browser**.

## Order of execution of Angular app

`main.ts` => `app.module.ts` => `index.html`

## Component selector types
### type 1 (recommended)
```ts
@Component({
  selector: 'app-server',
  templateUrl: './server.component.html'
})
```

```html
<app-server></app-server>
```
### type 2
```ts
@Component({
  selector: '[app-server]',
  templateUrl: './server.component.html'
})
```

```html
<div app-server></div>
```
## type 3
```ts
@Component({
  selector: '.app-server',
  templateUrl: './server.component.html'
})
```

```html
<div class=app-server></div>
```

## Use full Operators
| Operator | Usage                                 |
| -------- | ------------------------------------- |
| `?`      | accept variable to be undefined       |
| `!`      | accept variable to be null, undefined |
| `+`      | Typecast string to number.            |
eg: `a?: number`, `b!.value`, `+c.value`
