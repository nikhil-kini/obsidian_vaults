
```html
<!DOCTYPE html>
<html lang="en-US">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width" />
    <title>My test page</title>
  </head>
  <body>
    <img src="images/firefox-icon.png" alt="My test image" />
  </body>
</html>
```

- `<!DOCTYPE html>` — the doctype is the required "`<!DOCTYPE html>`".Its sole purpose is to prevent a [browser](https://developer.mozilla.org/en-US/docs/Glossary/Browser) from switching into so-called ["quirks mode"](https://developer.mozilla.org/en-US/docs/Web/HTML/Quirks_Mode_and_Standards_Mode) when rendering a document; that is, the "`<!DOCTYPE html>`" doctype ensures that the browser makes a best-effort attempt at following the relevant specifications, rather than using a different rendering mode that is incompatible with some specifications.
- `<html></html>` — the [`<html>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/html) element. This element wraps all the content on the entire page and is sometimes known as the root element. It also includes the `lang` attribute, setting the primary language of the document.
- `<head></head>` — the [`<head>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/head) element. This element acts as a container for all the stuff you want to include on the HTML page that _isn't_ the content you are showing to your page's viewers. This includes things like [keywords](https://developer.mozilla.org/en-US/docs/Glossary/Keyword) and a page description that you want to appear in search results, CSS to style our content, character set declarations, and more.
- `<meta charset="utf-8">` — This element sets the character set your document should use to UTF-8.
- `<meta name="viewport" content="width=device-width">` — This [viewport element](https://developer.mozilla.org/en-US/docs/Web/CSS/Viewport_concepts#mobile_viewports) ensures the page renders at the width of viewport, preventing mobile browsers from rendering pages wider than the viewport and then shrinking them down.
- `<title></title>` — the [`<title>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/title) element. This sets the title of your page, which is the title that appears in the browser tab the page is loaded in. It is also used to describe the page when you bookmark/favorite it.
- `<body></body>` — the [`<body>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/body) element. This contains _all_ the content that you want to show to web users when they visit your page.

## Element Tags

### Comment

Anything in HTML between `<!--` and `-->` is an **HTML comment**. The browser ignores comments as it renders the code.

```html
<!-- Something here. -->
```

### [Images](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/HTML_basics#images)

```html
<img alt="A Rockhopper Penguin standing on a beach." src="penguin.jpg" />
```

* The `src` (source) attribute, which contains the path to our image file.
* We have also included an `alt` (alternative) attribute. In the [`alt` attribute](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/img#authoring_meaningful_alternate_descriptions), you specify descriptive text for users who cannot see the image.

### [Headings](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/HTML_basics#headings)

Heading elements allow you to specify that certain parts of your content are headings — or subheadings. HTML contains [6 heading levels](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/Heading_Elements) , `<h1> - <h6>`, although you'll commonly only use 3 to 4 at most:

```html
<!-- 4 heading levels: -->
<h1>My main title</h1>
<h2>My top level heading</h2>
<h3>My subheading</h3>
<h4>My sub-subheading</h4>
```

**Note:** You'll see that your heading level 1 has an implicit style. Don't use heading elements to make text bigger or bold, because they are used for [accessibility](https://developer.mozilla.org/en-US/docs/Learn/Accessibility/HTML#text_content) and [other reasons such as SEO](https://developer.mozilla.org/en-US/docs/Learn/HTML/Introduction_to_HTML/HTML_text_fundamentals#why_do_we_need_structure). Try to create a meaningful sequence of headings on your pages, without skipping levels.

### [Paragraphs](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/HTML_basics#paragraphs)

 [`<p>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/p) elements are for containing paragraphs of text.
 
```html
<p>This is a single paragraph</p>
```

### [Lists](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/HTML_basics#lists)

The most common list types are ordered and unordered lists:
1. **Unordered lists** are for lists where the order of the items doesn't matter, such as a shopping list. These are wrapped in a [`<ul>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/ul) element.
2. **Ordered lists** are for lists where the order of the items does matter, such as a recipe. These are wrapped in an [`<ol>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/ol) element.

Each item inside the lists is put inside an [`<li>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/li) (list item) element.

```
<p>At Mozilla, we're a global community of</p>

<ul>
  <li>technologists</li>
  <li>thinkers</li>
  <li>builders</li>
</ul>

<p>working together…</p>
```

### [Links](https://developer.mozilla.org/en-US/docs/Learn/Getting_started_with_the_web/HTML_basics#links)

 [`<a>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/a) — "a" being the short form for "anchor". 

```html
<a href="https://www.mozilla.org/en-US/about/manifesto/">
  Mozilla Manifesto
</a>
```

You might get unexpected results if you omit the `https://` or `http://` part, called the _protocol_, at the beginning of the web address.

**Note:** `href` might appear like a rather obscure choice for an attribute name at first. If you are having trouble remembering it, remember that it stands for _**h**ypertext **ref**erence_.


## Block Level and Inline Elements

* **Block Level** - Takes the entire block of space when rendered in HTML. Ex: `<p>` tag.
* **Inline Elements** - Doesn't take entire block but is included in the parent Block. Ex: `<a>` tag.

### The Content Division element

**Block Level Element**.The **`<div>`** element is the generic container for flow content. It has no effect on the content or layout until styled in some way using [CSS](https://developer.mozilla.org/en-US/docs/Glossary/CSS)

```html
<div class="shadowbox">
  <p>Here's a very interesting note displayed in a lovely shadowed box.</p>
</div>
```
As a "pure" container, the `<div>` element does not inherently represent anything. Instead, it's used to group content so it can be easily styled using the [`class`](https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes#class) or [`id`](https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes#id) attributes, marking a section of a document as being written in a different language (using the [`lang`](https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes#lang) attribute), and so on.

### The Content Span element

**Inline Element**. `<span>` is very much like a [`<div>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/div) element, but [`<div>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/div) is a [block-level element](https://developer.mozilla.org/en-US/docs/Glossary/Block-level_content) whereas a `<span>` is an [inline-level element](https://developer.mozilla.org/en-US/docs/Glossary/Inline-level_content).

```html
<li>
  <span class="red">
    <a href="portfolio.html" target="_blank">See my portfolio</a>
  </span>
</li>
```

### The Table element

* The **`<table>`** element represents tabular data — that is, information presented in a two-dimensional table comprised of rows and columns of cells containing data.
* The **`<td>`** element defines a cell of a table that contains data. It participates in the _table model_.
* The **`<th>`** element defines a cell as the header of a group of table cells. The exact nature of this group is defined by the [`scope`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/th#scope) and [`headers`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/th#headers) attributes.
* The **`<tr>`** element defines a row of cells in a table. The row's cells can then be established using a mix of [`<td>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/td) (data cell) and [`<th>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/th) (header cell) elements.

```html
<table>
    <caption>Alien football stars</caption>
    <tr>
        <th scope="col">Player</th>
        <th scope="col">Gloobles</th>
        <th scope="col">Za'taak</th>
    </tr>
    <tr>
        <th scope="row">TR-7</th>
        <td>7</td>
        <td>4,569</td>
    </tr>
    <tr>
        <th scope="row">Khiresh Odo</th>
        <td>7</td>
        <td>7,223</td>
    </tr>
    <tr>
        <th scope="row">Mia Oolong</th>
        <td>9</td>
        <td>6,219</td>
    </tr>
</table>
```
Some more tags:
* [`<tbody>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/tbody)
* [`<thead>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/thead)
* [`<tfoot>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/tfoot)

*  `<tr rowspan ="2"></tr>` for 2 sized row, similarly for `<th colspan ="2"></th>`  

### [The Form element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form)

The **`<form>`** element represents a document section containing interactive controls for submitting information. **Explore more in the MDN page**

```html
<form action="" method="get" class="form-example">
  <div class="form-example">
    <label for="name">Enter your name: </label>
    <input type="text" name="name" id="name" required>
  </div>
  <div class="form-example">
    <label for="email">Enter your email: </label>
    <input type="email" name="email" id="email" required>
  </div>
  <div class="form-example">
    <input type="submit" value="Subscribe!">
  </div>
</form>
```

## [The Input (Form Input) element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input)

The **`<input>`** element is used to create interactive controls for web-based forms in order to accept data from the user; a wide variety of types of input data and control widgets are available, depending on the device and [user agent](https://developer.mozilla.org/en-US/docs/Glossary/User_agent).**Explore more in the MDN page**

```html
<label for="name">Name (4 to 8 characters):</label>

<input type="text" id="name" name="name" required
       minlength="4" maxlength="8" size="10">
```

Common Attributes:
* `placeholder`
Valid for `text`, `search`, `url`, `tel`, `email`, `password`, and `number`, the `placeholder` attribute provides a brief hint to the user as to what kind of information is expected in the field.
* `type`
A string specifying the type of control to render. For example, to create a checkbox, a value of `checkbox` is used.

### [The Label element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label)

The **`<label>`**  element represents a caption for an item in a user interface.
```html
<div class="preference">
    <label for="cheese">Do you like cheese?</label>
    <input type="checkbox" name="cheese" id="cheese">
</div>

<div class="preference">
    <label for="peas">Do you like peas?</label>
    <input type="checkbox" name="peas" id="peas">
</div>
```

###  [The Button element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/button)

The **`<button>`**  element is an interactive element activated by a user with a mouse, keyboard, finger, voice command, or other assistive technology. Once activated, it then performs an action, such as submitting a [form](https://developer.mozilla.org/en-US/docs/Learn/Forms) or opening a dialog.

```html
<button class="favorite styled"
        type="button">
    Add to favorites
</button>
```
Common Attributes:
`type`
The default behavior of the button. Possible values are:

- `submit`: The button submits the form data to the server. This is the default if the attribute is not specified for buttons associated with a `<form>`, or if the attribute is an empty or invalid value.
- `reset`: The button resets all the controls to their initial values, like [<input type="reset">](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/reset). (This behavior tends to annoy users.)
- `button`: The button has no default behavior, and does nothing when pressed by default. It can have client-side scripts listen to the element's events, which are triggered when the events occur.


## Other Helpful tags

|Element|Description|
|---|---|
|[`<hr>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/hr)|Represents a thematic break between paragraph-level elements. Ex: Header Line, Paragraph line
|[`<br>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/br)|Produces a line break in text (carriage-return) i.e to move to next line|
|[`<sub>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/sub)|Specifies inline text which should be displayed as subscript for solely typographical reasons. Subscripts are typically rendered with a lowered baseline using smaller text.|
|[`<sup>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/sup)|Specifies inline text which is to be displayed as superscript for solely typographical reasons. Superscripts are usually rendered with a raised baseline using smaller text.|

## HTML Entities

To add special characters in the rendered HTML.
```html
<p>This is Foo &#xA9; bar &#x1D306; baz &#x2603; qux </p>
```

|Character|Entity|Note|
|---|---|---|
|&|`&amp;`|Interpreted as the beginning of an entity or character reference.|
|<|`&lt;`|Interpreted as the beginning of a [tag](https://developer.mozilla.org/en-US/docs/Glossary/Tag)|
|>|`&gt;`|Interpreted as the ending of a [tag](https://developer.mozilla.org/en-US/docs/Glossary/Tag)|
|"|`&quot;`|Interpreted as the beginning and end of an [attribute](https://developer.mozilla.org/en-US/docs/Glossary/Attribute)'s value.|
||`&nbsp;`|Interpreted as the non breaking space.|
|–|`&ndash;`|Interpreted as the en dash (half the width of an em unit).|
|—|`&mdash;`|Interpreted as the em dash (equal to width of an "m" character).|
|©|`&copy;`|Interpreted as the copyright sign.|
|®|`&reg;`|Interpreted as the registered sign.|
|™|`&trade;`|Interpreted as the trademark sign.|
|≈|`&asymp;`|Interpreted as almost equal to sign.|
|≠|`&ne;`|Interpreted as not equal to sign.|
|£|`&pound;`|Interpreted as the pound symbol.|
|€|`&euro;`|Interpreted as the euro symbol.|
|°|`&deg;`|Interpreted as the degree symbol.|
[For more](https://html.spec.whatwg.org/multipage/named-characters.html#named-character-references)
[Decoder Tool](https://mothereff.in/html-entities)

## Semantic Elements

Makes your HTML element meaning full, but does not offer additional functionalities. Good for SEO, Screen-reader.
These are _some_ of the roughly 100 semantic [elements](https://developer.mozilla.org/en-US/docs/Web/HTML/Element) available:
- [`<article>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/article)
- [`<aside>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/aside)
- [`<details>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/details)
- [`<figcaption>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/figcaption)
- [`<figure>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/figure)
- [`<form>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/form)
- [`<footer>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/footer)
- [`<header>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/header)
- [`<main>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/main)
- [`<mark>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/mark)
- [`<nav>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/nav)
- [`<section>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/section)
- [`<summary>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/summary)
- [`<time>`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/time)


## References

MDN HTML Ref: [HTML Ref](https://developer.mozilla.org/en-US/docs/Web/HTML)
MDN Element List: [Element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element)
