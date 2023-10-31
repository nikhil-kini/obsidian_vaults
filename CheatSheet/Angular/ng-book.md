
Using the `{{…}}` syntax is called template binding.
``` html
<div class="inventory-app">
<h1>{{ product.name }}</h1>
<span>{{ product.sku }}</span>
</div>
```
the code inside the brackets is an expression. That means you can do things like this:
• `{{ count + 1 }}`
• `{{ myFunction(myArguments) }}`
In the first case, we’re using an operator to change the displayed value of `count`.
In the second case, we’re able to replace the tags with the value of the function
`myFunction(myArguments)`.

Notice that we can use template strings in attribute values, as in the href of the
a tag: `href="{{ link }}"`. In this case, the value of the `href` will be dynamically
populated with the value of link from the component class
```html
<a class="ui large header" href="{{ link }}"> {{ title }}
</a>
```

# Inputs and Outputs

```html
<products-list
[productList]="products" <!-- input -->
(onProductSelected)="productWasSelected($event)"> <!-- output -->
</products-list>
```

The \[*squareBrackets*] pass inputs and the (*parentheses*) handle outputs.
Data flows in to your component via *input bindings* and events flow out of your
component through *output bindings*.
That is:
	• `(onProductSelected)`, the left-hand side is the name of the output we want to
	“listen” on
	• `"productWasSelected"`, the right-hand side is the function we want to call
	when something new is sent to this output
	• `$event` is a special variable here that represents the thing emitted on (i.e. sent
	to) the output.