
`@RestController` - `@Controller` + `@ResponseBody`
`@Controller` - Specialized annotation of `@Component` for Controller layer (only this will not read the data in POST body, you need to add `@ResponseBody` in method or class to read body)
`@ResponseBody` - Serializable annotation, uses Jackson to convert JSON to POJO's

`@RequestMapping("/api/v1")` - declared at class level for initial routes

```java
@RequestMapping("/api/v1")
@RestController
public class ExController {
	
    /*
    @RequestMapping - Not Recommended for the method level for specialized annotation
    - _@GetMapping_  == @RequestMapping(method = RequestMethod.GET)
	- _@PostMapping_
	- _@PutMapping_
	- _@DeleteMapping_
	- _@PatchMapping_
    */
	@RequestMapping(value = "/exe", method = RequestMethod.GET, headers = { "key1=val1" })
	public String getFoo() {
		return "Get Foo";
	}

	@GetMapping(value = "/hi")
	public String ddo() {
		return "hi";
	}

	@GetMapping(value = "/do",      // Child route
	headers = { "key=val","key2=val2" }, // Must include header values in request
	consumes = {"application/json"},  // body type expected by the method
	produces = {"application/xml"})  // body type returned by the method
	public String hii() {
		return "do";
	}
	
// Path Variable
	@RequestMapping(value = "/ex/foos/{id}", method = RequestMethod.GET)
	public String getFoosBySimplePathWithPathVariable(
	  @PathVariable("id") long id) {
	    return "Get a specific Foo with id=" + id;
	}
	
	@GetMapping("/ex/f/{id}")
	public String getPath(@PathVariable long id) {
		return "varible " + id;
	}
	
	@GetMapping(value = "/ex/foos/{fooid}/bar/{barid}")
	public String getmultipath(@PathVariable long fooid, @PathVariable long barid) {
		return "fooid =" + fooid + " barid = " +barid;
	}
	
	@GetMapping("/ex/bars/{numericId:[\\d]+}")
	public String getregid(@PathVariable long numericId) {
		return "numeric "+numericId;
	}

// Query Param that must be included ( note: if not declared in method then it will be ignored)
	@GetMapping(value = "/ex/bars")
	public String getReqparam(@RequestParam("id") long id) {
		return "qurey param id "+ id;
	}
	
	@GetMapping(value = "/ex/bars2", params = {"id", "second"})
	public String getReqparamOptional(@RequestParam("id") long id) {
		return "qurey param id ";
	}

// Request Body	
	@PostMapping(value = "/ex/bars")
	public String getbody(@RequestParam("id") long id, @RequestBody String body) {
		return "qurey param id "+ id +" "+ body;
	}
	
	@PostMapping(value = "/ex/bars2")
	public String getbody2(@RequestParam("id") long id, @RequestBody Beer body) {
		return "qurey param id "+ id +" "+ body.toString();
	}

// To get Object has parameter
	@GetMapping("/filter")
	public Page<Book> filterBooks(@ParameterObject Pageable pageable) {
	     return repository.getBooks(pageable);
	}
}
```

## Edge Cases

- **the _value_ attribute of _@RequestMapping_ *does accept* multiple mappings**,
```java
@RequestMapping(
  value = { "/ex/advanced/bars", "/ex/advanced/foos" }, 
  method = GET)
@ResponseBody
public String getFoosOrBarsByPath() {
    return "Advanced - Get some Foos or Bars";
}
```
- **Multiple requests** using different HTTP verbs **can be mapped to the same controller** method:
```java
@RequestMapping(
  value = "/ex/foos/multiple", 
  method = { RequestMethod.PUT, RequestMethod.POST }
)
@ResponseBody
public String putAndPostFoos() {
    return "Advanced - PUT and POST within single method";
}
```
- @RequestMapping — a Fallback for All Requests
```java
@RequestMapping(
  value = "*", 
  method = { RequestMethod.GET, RequestMethod.POST ... })
@ResponseBody
public String allFallback() {
    return "Fallback for All Requests";
}
```
- Ambiguous Mapping Error
```java
// Error

@GetMapping(value = "foos/duplicate" )
public String duplicate() {
    return "Duplicate";
}

@GetMapping(value = "foos/duplicate" )
public String duplicateEx() {
    return "Duplicate";
}
```