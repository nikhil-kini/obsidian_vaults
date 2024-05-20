![[http_1.png]]

## Anatomy

![[http_anatomy.png]]

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