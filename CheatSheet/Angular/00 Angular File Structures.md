### Files created by Angular

* **`README.md`**  - For GitHub Info

* **`karma.config.js`** - Unit-testing framework config.

* **`angular.json`** - in-case of dependent project, the info will be stored here.

* **`.gitignore`** - To ignore files, not to be committed

* **`.editorconfig`** - contains text editor related info.

* **`package.json`** - For Dependencies(*info `devDependencies:` are dependencies that are not deployed in production*), Declare `ng` commands for application.

* **`package-lock.json`** - For Dependencies(*info `devDependencies:` are dependencies that are not deployed in production*), Declare `ng` commands for application. **For team work: should be in sync with `package.json` for CLI build**.

* **`tsconfig.json`** - [[Typescript]] config file.
* **`tsconfig.app.json`** - **All the files written in `.ts` files. That will be compiled using this file.**
* **`tsconfig.spec.json`** - **All the unit testcases are written in `.spec.ts` files. That will be compiled using this file.** Extends `tsconfig.json`

* **`.browserlistrc`** - to list the browser support for the project.

* **`src`** - all program related code.
* **`main.ts` ,  `styles.scss` ,   `index.html`** - are the main files were all other files will be injected
* **`polyfill`** - make sure the code is backward compatible with **JS** versions .

### `angular.json`
```json
{

"$schema": "./node_modules/@angular/cli/lib/config/schema.json",

"version": 1,

"newProjectRoot": "projects",

"projects": {

	"hotelinventoryapp": {
	
		"projectType": "application", // defines application or Library
		
		"schematics": {
		
		"@schematics/angular:component": {
		
		"style": "scss"
		
		}
	
	},
	
	"root": "",
	
	"sourceRoot": "src", // source folder for project
	
	"prefix": "app",
	
	"architect": {
	
		"build": {
		
		"builder": "@angular-devkit/build-angular:browser",
		
		"options": {
			
			"outputPath": "dist/hotelinventoryapp", // All the compiled files will be stored here
			
			"index": "src/index.html", // Path for the index.html for project.
			
			"main": "src/main.ts",
			
			"polyfills": [
			
			"zone.js"
			
			],
			
			"tsConfig": "tsconfig.app.json",
			
			"inlineStyleLanguage": "scss",
			
			"assets": [  // asset paths
			
			"src/favicon.ico",
			
			"src/assets"
			
			],
			
			"styles": [  // similarly CSS files path
			
			"src/styles.scss"
			
			],
			
			"scripts": [] // similarly JS files path
			
		},
		
		"configurations": {
		
			"production": {
			
			"budgets": [
			
			{
				
				"type": "initial",
				
				"maximumWarning": "500kb",
				
				"maximumError": "1mb"
			
			},
			
			{
			
				"type": "anyComponentStyle",
				
				"maximumWarning": "2kb",
				
				"maximumError": "4kb"
			
			}
			
			],
			
			"outputHashing": "all"
			
			},
			
			"development": {
			
				"buildOptimizer": false,
				
				"optimization": false,
				
				"vendorChunk": true,
				
				"extractLicenses": false,
				
				"sourceMap": true,
				
				"namedChunks": true
				
			}
		
		},
		
		"defaultConfiguration": "production"
		
	},
	
	"serve": {
	
		"builder": "@angular-devkit/build-angular:dev-server",
		
		"configurations": {
		
			"production": {
			
				"browserTarget": "hotelinventoryapp:build:production"
				
			},
			
			"development": {
			
				"browserTarget": "hotelinventoryapp:build:development"
				
			}
			
			},
		
		"defaultConfiguration": "development"
		
		},
		
		"extract-i18n": {
		
			"builder": "@angular-devkit/build-angular:extract-i18n",
		
		"options": {
		
			"browserTarget": "hotelinventoryapp:build"
			
		}
	
	},
	
	"test": {
	
		"builder": "@angular-devkit/build-angular:karma",
		
		"options": {
		
			"polyfills": [
			
			"zone.js",
			
			"zone.js/testing"
			
			],
			
			"tsConfig": "tsconfig.spec.json",
			
			"inlineStyleLanguage": "scss",
			
			"assets": [
			
			"src/favicon.ico",
			
			"src/assets"
			
			],
			
			"styles": [
			
			"src/styles.scss"
			
			],
			
			"scripts": []
			
			}
			
			}
			
			}
			
		}
		
	}

}

```

### `app.module.ts`
Is called by `main.ts`. And all the module info in project are linked here.
```typescript
import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';

  

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';

  

@NgModule({ // Decorator gives meta-data to angular

declarations: [ // Custom Component, Directive and Pipe are declared here

	AppComponent

],

imports: [   // Module INFO's of all modules

	BrowserModule,

	AppRoutingModule

],

providers: [],

bootstrap: [AppComponent], // which component needs to be loaded first
entryComponents: [AlertComponent] // Dynamic component declaration
})

export class AppModule { }
```

### Mono-repo
* For creating multiple application in same repository.
* Deploy multiple apps/libs from same repository.

### Component
Views that need to be rendered for the client.
* On creation you will get 4 files
* `.html` for html of the component
* `.css` for CSS of the component
* `ts` for ts of the component
* `spec.ts` for the unit test-cases of the ts component

**`app.component.ts`**
```Typescript
import { Component } from '@angular/core';


@Component({    // indidates component

selector: 'app-root', // HTML Tag<> liked to index.html. 'app' is a prefix from angular.json file -> prefix: value. This is for specificty, you can have custom names has well.

templateUrl: './app.component.html', // link to html

styleUrls: ['./app.component.scss'] // link to css file

})

export class AppComponent {

title = 'hotelinventoryapp';

}
```

