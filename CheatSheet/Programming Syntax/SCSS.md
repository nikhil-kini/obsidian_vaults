[Reference](https://sass-lang.com/documentation/style-rules/)
# Variables
```scss
$variable-name : value; // Use $variable-name where variable is required.

body {
	background: $variable-name;
}
```

# Map
```scss
$map-name : (
	"key1": value1,
	"key2": value2
)
body {
	background: $variable-name;
	font-weight: map-get($map-name, key1); // to get the value.
}
```


# Nesting
```scss
.main { // version 1
	width : 80%;
	margin : 0 auto;

	p {
		font-weight: map-get($map-name, key1);
	}
}

.main { // version 2
	width : 80%;
	margin : 0 auto;

	.main__paragraph { // add class to the <p> tag
		font-weight: map-get($map-name, key1);
	}
}

.main { // version 3
	width : 80%;
	margin : 0 auto;

	&__paragraph { // & = to the parent method, here its .main (.main__paragraph)
		font-weight: map-get($map-name, key1);
	}
}

.main { // version 4
	width : 80%;
	margin : 0 auto;

	#{&}__paragraph { // to interpolate, i.e. to add parent to method (.main .main__paragraph)
		font-weight: map-get($map-name, key1);
	}
}
```

# Partial
* SCSS that can be reused can be stored here, the file name starts with `_fileName.scss`
```scss
@import './file-path'
```

# Function
Should be use to compute values, and return values.
```scss
@function weight($weight-name){
	@return map-get($font-weight, $weight-name);
}

.main { 
	width : 80%;
	margin : 0 auto;

	#{&}__paragraph { 
		font-weight: weight(key1);
	}
}
```

# Extend/Inheritance
```scss
// Extend/Inheritance
.infobox {
  border: 1px solid #ccc;
  padding: 10px;
  color: $color;
}

.success {
  @extend .infobox;
  border-color: green;
}

// The % prefix creates rules that never get used on their own.
// Theses classes are solely for the purpose of extending.
%info {
  position: absolute;
}

.notice {
  @extend %info;
}
```
# Mixins
Contents that are repeatedly typed can formed into mixins. Unlike function there is no return in mixins.
```scss
// Mixins
@mixin outline {
  border: 1px solid black;
}
@mixin animate( $property, $duration, $easing ) {
  transition: $property $duration $easing;
}
@mixin default_animate( $property: all, $duration: 1s, $easing: ease ) { // Mixin with defaults
  transition: $property $duration $easing;
}

aside {
  border-radius: 10px;
  @include outline;
}

a {
  @include animate( all, 1s, linear );
}
nav a {
  @include default_animate( $duration: 3s ); // Use defaults with custom $duration
}

// Useful Mixins

@mixin shadow( $x, $y, $blur, $color ) {
  -webkit-box-shadow: $x $y $blur $color;
  -moz-box-shadow: $x $y $blur $color;
  box-shadow: $x $y $blur $color;
}

@mixin animate( $property: all, $duration: 1s, $easing: ease ) {
  -webkit-transition: $property $duration $easing;
  -moz-transition: $property $duration $easing;
  -o-transition: $property $duration $easing;
  transition: $property $duration $easing;
}

@mixin links( $normal, $visited, $hover ) {
  &:visited {
    color: $visited;
  }
  &:hover, &:active, &:focus {
    color: $hover;
  }
}
```

# If Condition
If SCSS `@if` is false the properties are not added to the compiled CSS.
```scss
@if boolean_var{
	properties....
}

// If/Else
p {
  margin-left: if( $i % 2 == 0, 0px, 50px );
}
```

# Operations
The operators need to be of same type.
```scss
// Operators: +, -, *, /, and %
.container {
  width: 600px / 960px * 100%;
}
```

# Loops
```scss
// Loops
$list: (orange, purple, teal);
@each $item in $list {
  .#{$item} {
    background: $item;
  }
}

$total: 10;
$step: 360deg / $total;
@for $i from 1 through $total {
   .ray:nth-child(#{$i}){
      background: adjust-hue( blue, $i * $step );
   }
}
```