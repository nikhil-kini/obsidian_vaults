## Template Driven VS Reactive Forms

| Template Driven                     | Reactive Forms                                          |
| ----------------------------------- | ------------------------------------------------------- |
| Infers the Form Object from the DOM | Created pro-grammatically and synchronized with the DOM |
| less control                        | Finer control                                           |
| import `FormsModule`                | import `ReactiveFormsModule`                            |
## Template Driven
- To get JS representation of the form in Angular add `ngModel` directive with `name`.
```html
<forms>
	<input ngModel name="field-name"></input>
</forms>
```
-  To call submit action use `ngSubmit`, don't call `(click)` has the default forms tag calls submit (built into HTML), use template variable `#name="ngForms"` to pass the JS object to method.(`"ngForm"` converts html element_ref to JS object)
```html
<forms (ngSubmit)="submit_method_ts(form_access)" #form_access = "ngForm">
	<input ngModel name="field-name"></input>
	<button type = "submit">sub</button>
</forms>
```

```html
<forms (ngSubmit)="submit_method_ts(form_access)" #form_access = "ngForm">
	<input [ngModel]="default_value" name="field-name"></input>
	<select [ngModel]="value1">
		<option value="value1">value_name</option>
		<option value="value2">value_name</option>
	</select>
	<text-area [(ngModel)] = "property_in_ts_file"></text-area>
	<div>{{ property_in_ts_file }}</div>
	<button type = "submit">sub</button>
</forms>
```
`[ngModel]="default_value"` to pre-populate the value in form.
`[(ngModel)] = "property_in_ts_file"` to two way binding communication.

```ts
submit_method_ts(form_access: NgForm){
  console.log(form_access);
}

//alternative
@ViewChild('form_access') signUpForm: NgForm;
onSubmimt(){
  console.log(this.signUpForm);
}
```
- Forms Property `dirty`, `touched`, `invalid` helps us in validating forms.

- Radio button
```ts
gender = ['mail', 'female'];

<input type="radio" name="gender" ngModel [value]="gender" required> {{gender}} </input>
```

### Validation

- Built-in directives for validation `required`, `email`, 
```html
<forms (ngSubmit)="submit_method_ts(form_access)" #form_access = "ngForm">
	<input ngModel name="field-name" required>
	<button type = "submit" [disabled]="!form_access.valid">sub</button>
</forms>
```
`[disabled]="!form_access.valid"` disables button, if the forms property `valid` is true.

```css
input.ng-invalid.ng-touched{
	border: 1px solid red; /* to add error styles */
}
```
`.ng-invalid.ng-touched` classes are added by angular to the form elements based on their state.

```html
<input ngModel name="field-name" required #name="ngModel">
<span *ngIf="!name.valid && name.touched"> enter correct details</span>
```
`#name="ngModel"` for local error display operation.

### Group

```html
<forms (ngSubmit)="submit_method_ts(form_access)" #form_access = "ngForm">
	<div ngModelGroup="group1" #groupData="ngModelGroup">
		<input [ngModel]="default_value" name="field-name">
		<select [ngModel]="value1">
			<option value="value1">value_name</option>
			<option value="value2">value_name</option>
		</select>
	</div>
	<span *ngIf="!groupData.valid && groupData.touched"> enter correct details</span>
	<text-area [(ngModel)] = "property_in_ts_file"></text-area>
	<div>{{ property_in_ts_file }}</div>
	<button type = "submit">sub</button>
</forms>
```
`ngModelGroup="group1" #groupData="ngModelGroup"` - to form a group, and to get the group-data for validation.

### Reset, Set and Patch values of form

- **Note: `setValue(obj)` reset all the form  object (needs to be all form data fields).**
```ts
@ViewChild('form_access') signUpForm: NgForm;
onSubmimt(){
  console.log(this.signUpForm);
}

this.signUpForm.setValue({
	formGroupName: {  // if Group is present in form
		formFieldName: value;
	}
})
```

- **Note: `form.patchValue(obj)` only resets the data present in the object*
```ts
@ViewChild('form_access') signUpForm: NgForm;
onSubmimt(){
  console.log(this.signUpForm);
}

this.signUpForm.form.patchValue({
	formGroupName: {  // if Group is present in form
		formFieldName: value;
	}
})
```

- Reset form - form resets like the page was loaded again
```ts
@ViewChild('form_access') signUpForm: NgForm;
onSubmimt(){
  console.log(this.signUpForm);
}

this.signUpForm.reset(); // to all values

this.signUpForm.reset({ // for specific values
	formGroupName: {  
		formFieldName: value;
	}
});
```


## Reactive Forms

```ts
signUpForm: FormGroup;  // form name

ngOnInit(){ // needs to be initialized before form is rendered
	this.signUpForm = new FormGroup({
		'fieldName': new FormControl(null), //intial value needs to be set 
		'fieldName2': new FormControl('value')
	})
}
```

```html
<forms [formGroup]="signUpForm" (ngSubmit)="submit_method_ts(form_access)">
	<input  formControlName="fieldName">
</forms>
```

### Validation

```ts
signUpForm: FormGroup;  

ngOnInit(){ 
	this.signUpForm = new FormGroup({
		'fieldName': new FormControl(null, Validators.required), // singel validator
		'fieldName2': new FormControl('value',[ 
		Validators.required,
		Validators.email
		]) // add array of validators
	})
}
```

```html
<input  formControlName="fieldName">
<span *ngIf="!signUpForm.get('fieldName').valid && signUpForm.get('fieldName').touched')"> error Mssage </span>

// to validate whole form
<span *ngIf="!signUpForm.valid && signUpForm.touched')"> error Mssage </span>
```
Same CSS has Template Drive will be added to fields

### Grouping
```ts
signUpForm: FormGroup;  

ngOnInit(){ 
	this.signUpForm = new FormGroup({
		'formGroupName1': new FormGroup({
			'fieldName1': new FormControl(null, Validators.required),
			'fieldName2': new FormControl(null, Validators.required)
		})
		'fieldName': new FormControl(null, Validators.required), 
		'fieldName3': new FormControl('value',[ 
		Validators.required,
		Validators.email
		]) 
	})
}
```

```html
<forms [formGroup]="signUpForm" (ngSubmit)="submit_method_ts(form_access)">
	<div formGroupName='formGroupName1'>
		<input  formControlName="fieldName1">
		<input  formControlName="fieldName2">
		<span *ngIf="!signUpForm.get('formGroupName1.fieldName2').valid && signUpForm.get('fieldName').touched')"> error Mssage </span>
	</div>
</forms>
```

### Form Control Array

```ts
signUpForm: FormGroup; 

ngOnInit(){ 
	this.signUpForm = new FormGroup({
		'fieldName': new FormControl(null), 
		'fieldName2': new FormControl('value'),
		'fieldArrayName': new FormArray([])
	});
}

addToFormArray(){
	const control = new FormControl(null, Validator.required);
	(<FormArray> this.signupForm.get('fildName3')).push(control);
}
```

```html
<div formArrayName="fieldArrayName">
  <button type="button"
	(click)="onAddHobby()">Add </button>
  <div
	*ngFor="let fieldControl of signupForm.get('fieldArrayName').controls; let i = index">
		<input type="text" [formControlName]="i">
  </div>
</div>
```

## Custom Validators
- Is a function which return object `{[s: string]: boolean}` `boolean` is always `true` for failure or `null` for valid condition
```ts
'fieldName3': new FormControl('value',[ 
		Validators.required,
		Validators.email,
		this.functionName.bind(this) // has the form control is access the fuction it will not have refrence to the current class elements, use bind() to pass current class if you want to use this. in the validator
		])  

functionName(control: FormControl): {[s: string]: boolean}{
	if (this.condition_for_validator){
		return {'error msg': true}; // for validation fail
	}
	return null; // validation pass
}
```

- custom error messages, see the **forms obj** to get the **specific error** code to give **custom msg**.
```html
<span *ngIf="signupForm.get('groupData.fieldName').errors['error msg']"> custom message for the error</span>
```

- Async Validators
```ts
'fieldName3': new FormControl('value',[ 
		Validators.required,
		Validators.email], this.functionAsyncName)  // Asyc valicator are the third argument in FormControl

functionAsyncName(control: FormControl): Promise<any> | Observabel<any>{
	const promise = new Promise<any>((resolve, reject) => {
		setTimeout(()=>{
			if (this.condition_for_validator){
				resolve({'error msg': true}); // for validation fail
			}else resolve(null); // validation pass
		}, 1500)
	});
	return promise;
}
```

### Status Change and Value Changes

- Value refers to the field changes in the form 
```ts
this.signupForm.valueChanges.subscribe(
	val => // logic to run on every time the value change in form
)
```

- Status refers to the `Invaild`, `Valid` etc. states of form
```ts
this.signupForm.statusChanges.subscribe(
	val => // logic to run on every time the status change in form
)
```


### Reset, Set and Patch values of form

Same has the Template driven approach