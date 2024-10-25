- **Note: Validation is done on the DTO or DAO object**
```java
public class BeerDto {

    @Null
    private UUID id;

    @NotBlank
    private String beerName;

    @NotBlank
    private String beerStyle;

    @Positive
    private Long upc;
}
```
- Add `@valid` to controller method parameter, to trigger validation
```java
 @PostMapping // POST - create new beer
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
}

 @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
```
- `@ExceptionHandler` handles error occurred by sending appropriate error details to client request


# Controller method level validations

- add `@Validated ` to controller, and `@NotNull` to method parameter (not used very often)
```java
@Validated       // here
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {


    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer( @NotNull @PathVariable("beerId") UUID beerId){
    }
```

## Controller Advice
- Top level exception handling,  Exception defined here will be applied to all the controllers.
```java
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException ex){
        return new ResponseEntity(ex.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
```