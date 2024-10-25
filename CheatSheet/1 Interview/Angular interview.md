https://github.com/sudheerj/angular-interview-questions?tab=readme-ov-file#what-is-angular-framework


## Reusable Angular components

- ng-template
- ng-content

|   |   |
|---|---|
|**ng-content**|**ng-template**|
|The projected content lifecycle is bound to the parent lifecycle|Passed templates lifecycle are not bound to the parent lifecycle hook|
|Use ng-content for most scenarios|Use whenever you encounter ng-content inside ngIf|

## SCSS VS CSS
| Parameters             | SCSS                                                                                                                                          | CSS                                                                                        |
| ---------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| Meaning and Definition | SCSS is a more evolved and advanced form of the CSS language. It is a preprocessor language, and we need to compile it into the CSS language. | CSS is a scripting language used for styling various web pages.                            |
| Lines of Code          | It uses fewer lines of code than CSS.                                                                                                         | CSS makes extensive use of lines of code.                                                  |
| Functions              | It consists of advanced functions.                                                                                                            | It consists of basic functions.                                                            |
| Rules for nesting      | SCSS supports the nesting rules.                                                                                                              | The standard CSS does not assign various nested rules.                                     |
| Design                 | SCSS is a special file in a SASS program that we need to write in the Ruby language.                                                          | CSS is a styling language that enables us to develop, design, and style various web pages. |
| Language               | We use the SCSS in the Ruby language.                                                                                                         | We commonly use CSS in JavaScript and HTML languages.                                      |
| Syntax                 | Follows a more structured syntax with additional features.                                                                                    | Follows a plain-text syntax.                                                               |
| Mixins                 | Allows you to create reusable code snippets.                                                                                                  | Does not provide this functionality.                                                       |
| File Extension         | Uses the .scss file extension.                                                                                                                | Uses the .css file extension.                                                              |
| Compilation            | SCSS files must be preprocessed into standard CSS files.                                                                                      | CSS files are interpreted by web browsers directly.                                        |
| Advanced Features      | SCSS contains all features of CSS and more, making it a preferable choice for developers.                                                     | Lacks many of the advanced features present in SCSS.                                       |
| Variables              | Offers the use of variables to shorten and simplify code.                                                                                     | Does not allow the use of variables.                                                       |

## authentication in app- explain login process, encryption algorithm 
## authorization in app- prevent user using Auth guards


## **responsive design with Angular:**

- **use the Angular BreakpointObserver to detect the size of the current device**
- **set member variables in your component that allow you to show or hide certain elements depending on the screen size**
- **Set responsive CSS classes in your component like `is-phone-portrait` depending on the screen size**
- **Use these special classes to make the CSS of your component responsive without writing media queries**

## Forms

## Router Router Gaurds, Intreceptors

## Component comunication
## Rxjs different type of subjects, mergeMap, forkjoin, observables, 

## Event Bubbling and event capturing
- event bubbling is the propogation of event from the child element to its parent element. To stop this propogation we use event.stopPropogation()

## Lazy loading

## ES6 Features
- Arrow Function
- unctions can now be given an indefinite number of arguments as an array, called the [rest function parameters syntax](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/rest_parameters):
		 var func = (a, b, c, ...rest) => { }
- let and const
The `let` command is a replacement for `var` that specifically grants block scope.

## Normal function vs Arrow function

## Angular bootstrapping process

## List of lifecycle hooks

## What is a strict operator and rest operator