|> What's read-modify-write cycle?

- Two thread incrementing same counter
![[Pasted image 20241025112905.png]]

## **Atomic Operations**

algorithms exploit low-level atomic machine instructions such as compare-and-swap (CAS), to ensure data integrity.

A typical CAS operation works on three operands:

1. The memory location on which to operate (M)
2. The existing expected value (A) of the variable
3. The new value (B) which needs to be set

**The CAS operation updates atomically the value in M to B, but only if the existing value in M matches A, otherwise no action is taken.**

In both cases, the existing value in M is returned. This combines three steps – getting the value, comparing the value, and updating the value – into a single machine level operation
## What are Atomic Variables?

Variables that allow concurrent operations in a thread safe way. Prevents race-condition. Improve performance compared to locks.


Different types of Atomic Variables
-> AtomicInteger
-> AtomicBoolean
-> AtomicLong


Basic operations
-> get()
-> set()
-> compareAndSet(expected, update)
-> getAndIncrement() / incrementAndGet()
-> getAndDecrement() / decrementAndGet()


```java
public class AtomicVariable {

//    private static int count = 0;
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                count++;
                counter.incrementAndGet();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                count++;
                counter.incrementAndGet();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Counter value is : " + counter);
//        System.out.println("Count value is : " + count);
    }
}
```

