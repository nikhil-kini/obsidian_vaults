
Semaphore is Synchronization mechanism used to  control access to the share resource in a multi threaded environment. uses counter to resolve.

![[Pasted image 20241025114547.png]]

- Threads with `permit` will be allowed. Permits are managed by semaphores
![[Pasted image 20241025114609.png]]

```java
public class Scraper {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 15; i++) {
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        ScrapeService.INSTANCE.scrape();
                    }
                });
            }
        }
    }
}


// singleton class
enum ScrapeService {
    INSTANCE;
    private Semaphore semaphore = new Semaphore(3);

    public void scrape() {
        try {
            semaphore.acquire();
            invokeScrapeBot();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    private void invokeScrapeBot() {
        try {
            System.out.println("Scraping data...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

```output
Scraping data...
Scraping data...
Scraping data...
Scraping data...
Scraping data...
Scraping data...
```

**Multiple Permits**
- A thread can acquire multiple permits
- Note: It should release all the acquired permits.
```java
try {
	semaphore.acquire(); // 2 times
	semaphore.acquire();
	invokeScrapeBot();
} catch (InterruptedException e) {
	throw new RuntimeException(e);
} finally {
	semaphore.release(); //2 times
	semaphore.release();
}
```

Methods of Semaphores
-> tryAcquire()
-> tryAcquire(timeout)
-> availablePermits()
-> new Semaphore(count, fairness)