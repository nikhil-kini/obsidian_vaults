**It's not a server-side rendering library like Next.js it pre-renders the files in server to improve performance**

>**"Fixes SEO search-engine problems, has the crawler doesn't fully render the screen and improves page-load in slow network"**

- To auto-setup the angular universal
```sh
ng add @nguniversal/express-engine --clientProject projectName
```

- Verify `ModuleMapLoaderModule` is present in `app.server.module.ts` file, and is able to import
```sh
npm install --save @nguniversal/module-map-ngfactory-loader
```

- Fix for the browser only logic's,  i.e. `localStorage` etc
```ts
import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Store } from '@ngrx/store';

import { LoggingService } from './logging.service';
import * as fromApp from './store/app.reducer';
import * as AuthActions from './auth/store/auth.actions';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(
    private store: Store<fromApp.AppState>,
    private loggingService: LoggingService,
    @Inject(PLATFORM_ID) private platformId
  ) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      this.store.dispatch(new AuthActions.AutoLogin());
    }
    this.loggingService.printLog('Hello from AppComponent ngOnInit');
  }
}
```

-  To build
```sh
npm run build:ssr
```

- You need to have a live server to host the application, static hosting doesn't work

-  To serve
```sh
npm run serve:ssr
```
