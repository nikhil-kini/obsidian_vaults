
`@ControllerAdvice` - is a class level annotation used to handle Exception across the project.

For Controller level Exception Handling, use `@ExceptionHandler` at individual controller level

- Controller
```java
@RequestMapping("/api/v1")
@RestController
public class ExceptionController {

	@GetMapping("/exp")
	public ResponseEntity<String> getExecption() {
		if (true)
			throw new CustomExep("Not created");
		return new ResponseEntity<String>("OK", HttpStatus.ACCEPTED);
	}
	
	@ExceptionHandler(CustomExep.class)
	public ResponseEntity<String> throwwxep(CustomExep ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
```
- Custom Exception
```java
public class CustomExep extends RuntimeException{

	public CustomExep() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomExep(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CustomExep(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomExep(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomExep(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
```
- Controller Advice -` @RestControllerAdvice` == `@ControllerAdvice` + `@ResponseBody`
```java
@RestControllerAdvice
public class ExepAdvice {
	@ExceptionHandler(CustomExep.class)
	public ResponseEntity<String> throwwxep(CustomExep ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
```