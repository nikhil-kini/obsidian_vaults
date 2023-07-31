
**CSS is a style sheet language.** CSS is what you use to selectively style HTML elements.

**Note: 
* **Order of CSS matters, if 2 selector are specified the later is applied**
* **The More Specifically defined CSS selector is applied to the HTML in case of Conflict. ID > CLASS > ELEMENT. Use Dev Tool **

**Comment**
```css
/* Comment */
```

Paste the following line in the head of [[HTML]](between the [`<head>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/head) and `</head>` tags) to import CSS sheet:

```html
<link href="styles/style.css" rel="stylesheet" />
```

## Rule Set 
The whole structure is called a **ruleset**. (The term _ruleset_ is often referred to as just _rule_.) Note the names of the individual parts:

**Selector**
This is the HTML element name at the start of the ruleset. It defines the element(s) to be styled (in this example, [`<p>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/p) elements). To style a different element, change the selector.

**Declaration**
This is a single rule like `color: red;`. It specifies which of the element's **properties** you want to style.

**Properties**
These are ways in which you can style an HTML element. (In this example, `color` is a property of the [`<p>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/p) elements.) In CSS, you choose which properties you want to affect in the rule.

**Property value**
To the right of the property—after the colon—there is the **property value**. This chooses one out of many possible appearances for a given property. (For example, there are many `color` values in addition to `red`.)

Note the other important parts of the syntax:
- Apart from the selector, each ruleset must be wrapped in curly braces. (`{}`)
- Within each declaration, you must use a colon (`:`) to separate the property from its value or values.
- Within each ruleset, you must use a semicolon (`;`) to separate each declaration from the next one.

To modify multiple property values in one ruleset, write them separated by semicolons, like this:

```css
p {
  color: red;
  width: 500px;
  border: 1px solid black;
}
```

### [Selecting multiple elements](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/CSS_basics#selecting_multiple_elements)

You can also select multiple elements and apply a single ruleset to all of them. Separate multiple selectors by commas. For example:

```css
p,
li,
h1 {
  color: red;
}
```

### [Different types of selectors](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/CSS_basics#different_types_of_selectors)

There are many different types of selectors. The examples above use **element selectors**, which select all elements of a given type. But we can make more specific selections as well. Here are some of the more common types of selectors:

|Selector name|What does it select|Example|
|---|---|---|
|Element selector (sometimes called a tag or type selector)|All HTML elements of the specified type.|`p`  <br>selects `<p>`|
|ID selector|The element on the page with the specified ID. On a given HTML page, each id value should be unique.|`#my-id`  <br>selects `<p id="my-id">` or `<a id="my-id">`|
|Class selector|The element(s) on the page with the specified class. Multiple instances of the same class can appear on a page.|`.my-class`  <br>selects `<p class="my-class">` and `<a class="my-class">`|
|Descendant selector | Elements of the specified type and subtype. | `li a { color: red}` `<a>` under `<li>`'s
|Attribute selector|The element(s) on the page with the specified attribute.|`img[src]`  <br>selects `<img src="myimage.png">` but not `<img>`|
|[Pseudo-class selector](https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-classes)|The specified element(s), but only when in the specified state. (For example, when a cursor hovers over a link.)|`a:hover`  <br>selects `<a>`, but only when the mouse pointer is hovering over the link.|
|[Pseudo-elements](https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-elements)|  |[`::first-line`](https://developer.mozilla.org/en-US/docs/Web/CSS/::first-line) can be used to change the font of the first line of a paragraph.

There are many more selectors to discover. To learn more, see the MDN [Selectors guide](https://developer.mozilla.org/en-US/docs/Learn/CSS/Building_blocks/Selectors).

### Declaration Type
CSS layout is mostly based on the _box model._ Each box taking up space on your page has properties like:

- `padding`, the space around the content. In the example below, it is the space around the paragraph text.
- `border`, the solid line that is just outside the padding.
- `margin`, the space around the outside of the border.

![Three boxes sat inside one another. From outside to in they are labelled margin, border and padding](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/CSS_basics/box-model.png)

In this section we also use:

- `width` (of an element).
- `background-color`, the color behind an element's content and padding.
- `color`, the color of an element's content (usually text).
- `text-shadow` sets a drop shadow on the text inside an element.
- `display` sets the display mode of an element. (keep reading to learn more)

### [Color](https://developer.mozilla.org/en-US/docs/Web/CSS/color)

[Picker Web Page](https://htmlcolorcodes.com/color-names/)
```css
/* Keyword values */
color: currentcolor;

/* <named-color> values */
color: red;
color: orange;

/* <hex-color> values */
color: #090;
color: #009900;

/* <rgb()> values */
color: rgb(34, 12, 64, 0.6);
color: rgba(34, 12, 64, 0.6);
color: rgb(34 12 64 / 0.6);
color: rgba(34 12 64 / 0.3);

/* <hsl()> values */
color: hsl(30, 100%, 50%, 0.6);
color: hsla(30, 100%, 50%, 0.6);
color: hsl(30 100% 50% / 0.6);
color: hsla(30 100% 50% / 0.6);

/* <hwb()> values */
color: hwb(90 10% 10%);
color: hwb(90 10% 10% / 0.5);
color: hwb(90deg 10% 10%);
color: hwb(1.5708rad 60% 0%);
color: hwb(0.25turn 0% 40% / 50%);

/* Global values */
color: inherit;
color: initial;
color: revert;
color: revert-layer;
color: unset;
```

###  [Fonts and text](https://developer.mozilla.org/en-US/docs/Web/CSS/font)

1. First, find the [output from Google Fonts](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/What_will_your_website_look_like#font) that you previously saved from [What will your website look like?](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/What_will_your_website_look_like). Add the [`<link>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link) element somewhere inside your `index.html`'s head (anywhere between the [`<head>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/head) and `</head>` tags). It looks something like this:

```HTML
<link
  href="https://fonts.googleapis.com/css?family=Open+Sans"
  rel="stylesheet" />
```

- This code links your page to a style sheet that loads the Open Sans font family with your webpage.
- Add the following lines (shown below), replacing the `font-family` assignment with your `font-family` selection from [What will your website look like?](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/What_will_your_website_look_like#font). The property `font-family` refers to the font(s) you want to use for text. This rule defines a global base font and font size for the whole page. Since [`<html>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/html) is the parent element of the whole page, all elements inside it inherit the same `font-size` and `font-family`.

```css
html {
  font-size: 10px; /* px means "pixels": the base font size is now 10 pixels high */
  font-family: "Open Sans", sans-serif; /* this should be the rest of the output you got from Google Fonts */
}
```

- Now let's set font sizes for elements that will have text inside the HTML body ([h1](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements), [`<li>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/li), and [`<p>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/p)). We'll also center the heading. 

```css
h1 {
  font-size: 60px; /* check Relative sizes*/
  text-align: center;
}

p,
li {
  font-size: 16px;
  line-height: 2;
  letter-spacing: 1px;
}
```
**[Relative font size units](https://developer.mozilla.org/en-US/docs/Web/CSS/font-size)** - ex: ems, rems




## References

[MDN CSS references](https://developer.mozilla.org/en-US/docs/Web/CSS/Reference)