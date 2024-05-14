## Initialization

* Required **[[00 Node Commands]] JS**
* Required **Editor**

To Install Angular globally. [Command ref.](https://angular.io/cli)
```sh
npm i @angular/cli -g
```

To check Angular version
```shell
ng version
```

To create workspace
```sh
ng new name_workspace/application_name
```
- **`--skip-tests`**: stops the generation of testing (viz. e2e, `karma.conf.js`, `protractor.conf.js`, and `*.spec.` files) including `tsconfig.spec.json`, `app.component.spec.ts`
- **`--inline-style`**: stops the generation of external css stubs instead keeping an empty string in the `.ts` file.
- **`--inline-template`**: stops the generation of external html instead keeping it in the `template` variable in the `.ts` file.
- `--minimal`: stops generation of all, above combined.


To create workspace to set prefix
```sh
ng new name_workspace/application_name --prefix='value'
```

To create workspace(for empty workspace, no default dependencies)
```sh
ng new name_workspace --createApplication=false
```

To install dependencies in `package.json`
```sh
npm i
```

To generate a application(For Empty Applications)
```sh
ng g app app_name
```

To run application Locally(runs in watch mode for `src/` )
```sh
ng serve -o
```

To add a component (in the directory)
```shell
ng g c name_of_component # = ng generate component name
```

To add a service (in the directory)
```shell
ng g s name_of_service # = ng generate service name
```

To add a environments
```shell
ng g environmets# = ng generate environments
```

To add a interface (in the directory)
```shell
ng g interface name_of_service # = ng generate interface name
```

To add a interceptor (in the directory)
```shell
ng g interceptor name_of_interceptor 
```

To add a directive (in the directory)
```shell
ng g d name_of_directive # = ng generate directive name
```

To add a module (in the directory)(with folder)
```shell
ng g m name_of_module # = ng generate module name
```

To add a module (in the directory)(without folder)
```shell
ng g m name_of_module --flat=true # = ng generate module name
```

To add a module (in the directory)(without folder and with routing )
```shell
ng g m name_of_module --routing --flat=true # = ng generate module name
```

To build the application in the production mode
```sh
ng build -c=production
```

```sh
ng serve --prod
```

To deploy the application (need to setup the deploy packages)
```
ng deploy --base-href="path to set in index.html to start"
```

To add a module (in the directory)(Lazy loaded with routing )
```shell
ng g m name_of_module --route=name_of_module --routing --module=existing_module_where_route_needs_to_be_added
```

To run Unit Test Cases
```sh
ng test
```

To run Unit Test Cases
```sh
ng test --code-coverage
```

To generate route-guard.
```sh
ng g guard name_guard
```