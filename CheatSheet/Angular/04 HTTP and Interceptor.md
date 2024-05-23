![[http_1.png]]

## Anatomy

![[http_anatomy.png]]

## HTTP
- Import HttpClientModule in app.module.ts
```ts
HttpClientModule
```
- Inject the module in the component
```ts
constructor(private http: HttpClient){}
```
**Note: is a async operation, needs a subscription to initiate a call.**
### Post
```ts
this.http.post<type>('url', body,{ headers: header1, params: params1, observe: 'response', responseType: 'json'}); // need to subscribe
```
`observe: 'response' | 'body' |'event'`  to change the response type data returned to subscription. `response` gives access to headers and all info. `event` see docs
```ts
let body = {key: value};
let headers1 = new HttpHeaders({key: value});
let params1 = new HttpParams().set('key','value'); // adds query param to the url ?key=value

//Note HttpParams is immutable, addition of params need to be assigned newly
let params1 = new HttpParams();
params1 = params1.append('key1', 'value1');
params1 = params1.append('key2', 'value2');
```
### Get
```ts
this.http.get<type>('url',{ headers: header1, params: params1}); // note no body can be sent
```
### Delete
```ts
this.http.delete('url', { headers: header1, params: params1});
```

### Transform the Response with Rxjs
```ts
this.http.get<{ [key: string]: Post }>('url').pipe(map((responseData) => {
	const postsArray: Post[]= [];
	for (const key in respnseData){
	if (responseData.hasOwnProperty(key)) postsArray.push({...responseData[key], id:key});
}
}));
```
### Use service to the group all http
```ts
@Injectable({providedIn: 'root'})
export class PostsService{
	constructor(private http: HttpClient){}
	
	methods... // call and transform here, but subscribe in component
}
```
### Error handling
```ts
observable.subscribe(success, error, complete); //see Rxjs
```

```ts
error = new Subject<string>();

http.subscribe(success, error => { // to broadcast error across app
 this.error.next(error.message);
}); // unsubscribe the subscription see Rxjs 
```

```ts
error = new Subject<string>();

http.get('url').pipe(map(logic), catchError(logic)).subscribe(logic);  //generic way
```


## Interceptor

- Create interceptor service
```ts
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

export class AuthInterceptorService implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    console.log('Request is on its way');
    return next.handle(req);
  }
}
```
Note: The `req` is **immutable in nature**. Create new object and send that object.
```ts
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

export class AuthInterceptorService implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    console.log('Request is on its way');
    const modifiedRequest = req.clone({headers: req.headers.append('Auth','xyz')});
    return next.handle(modifiedRequest);
  }
}
```
- Inject in special fashion
```ts
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
```

#### Response Interceptor

```ts
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

export class AuthInterceptorService implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    console.log('Request is on its way');
    const modifiedRequest = req.clone({headers: req.headers.append('Auth','xyz')});
    return next.handle(modifiedRequest).pipe( // here change
    tap(event =>{
    console.log(event);
    if (event.type === HttpEventType.Response){
      console.log('Respnse arrived, body data: ');
      console.log(event.body);
    }}));
  }
}
```

## Multiple Interceptor
- **Order of definition matters**
```ts
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule],
  providers: [
    {  //1st
      provide: HTTP_INTERCEPTORS,
      useClass: LoggingInterceptorService,
      multi: true
    },
    {  //2nd
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
```