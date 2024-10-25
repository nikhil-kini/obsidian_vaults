**These focus on how our objects interact with each other or how we interact with them.**
## Chain of Responsibility

- **This then allows us to build a chain of implementations, where each one performs some actions before or after the call to the next element in the chain**:
- Spring's filter chains, like the ones used in Spring Security, employ the Chain of Responsibility pattern
[see here for more info](https://www.baeldung.com/chain-of-responsibility-pattern)
```java
public abstract class AuthenticationProcessor {

    public AuthenticationProcessor nextProcessor;
    
    // standard constructors

    public abstract boolean isAuthorized(AuthenticationProvider authProvider);
}


public class OAuthProcessor extends AuthenticationProcessor {

    public OAuthProcessor(AuthenticationProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public boolean isAuthorized(AuthenticationProvider authProvider) {
        if (authProvider instanceof OAuthTokenProvider) {
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.isAuthorized(authProvider);
        }
        
        return false;
    }
}

public class UsernamePasswordProcessor extends AuthenticationProcessor {

    public UsernamePasswordProcessor(AuthenticationProcessor nextProcessor) {
        super(nextProcessor);
    }

    @Override
    public boolean isAuthorized(AuthenticationProvider authProvider) {
        if (authProvider instanceof UsernamePasswordProvider) {
            return true;
        } else if (nextProcessor != null) {
            return nextProcessor.isAuthorized(authProvider);
        }
    return false;
    }
}

public class ChainOfResponsibilityTest {

    private static AuthenticationProcessor getChainOfAuthProcessor() {
        AuthenticationProcessor oAuthProcessor = new OAuthProcessor(null);
        return new UsernamePasswordProcessor(oAuthProcessor);
    }

    @Test
    public void givenOAuthProvider_whenCheckingAuthorized_thenSuccess() {
        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();
        assertTrue(authProcessorChain.isAuthorized(new OAuthTokenProvider()));
    }

    @Test
    public void givenSamlProvider_whenCheckingAuthorized_thenSuccess() {
        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();
 
        assertFalse(authProcessorChain.isAuthorized(new SamlTokenProvider()));
    }
}
```

## Command Pattern

- the pattern intends to **encapsulate in an object all the data required for performing a given action (command),** including what method to call, the method’s arguments, and the object to which the method belongs.
- **implementing four components: the Command, the Receiver, the Invoker, and the Client**.
- Spring's `JdbcTemplate` uses the Command pattern, where SQL statements are encapsulated as objects, providing a common interface for executing them. 
[see here for more info](https://www.baeldung.com/java-command-pattern)


## Iterator

- The Iterator pattern allows us to work across the elements in a collection and interact with each in turn. 
- **We use this to write functions taking an arbitrary iterator over some elements without regard to where they are coming from**.
- Streams also implement the same method.
```java
void printAll<T>(Iterator<T> iter) {
    while (iter.hasNext()) {
        System.out.println(iter.next());
    }
}
```

## Memento

- **The [Memento](https://www.baeldung.com/java-memento-design-pattern) pattern allows us to write objects that are able to change state, and then revert back to their previous state.**
```java
class Undoable {
    private String value;
    private String previous;

    public void setValue(String newValue) {
        this.previous = this.value;
        this.value = newValue;
    }

    public void restoreState() {
        if (this.previous != null) {
            this.value = this.previous;
            this.previous = null;
        }
    }
}
```

