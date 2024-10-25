Synchronization in Java is the capability to control the access of multiple threads to any shared resource.
1. To prevent thread interference.
2. To prevent consistency problem.(**Race condition**)

Types
1. Process Synchronization
2. Thread Synchronization

Thread Synchronization types
1. Mutual Exclusive
    1. Synchronized method. (not recommended)
    2. Synchronized block.
    3. Static synchronization.
2. Cooperation (Inter-thread communication in java)

```java
public class SyncronizationDemo {
	
	private static int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread one = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				increment();
			}
		});
		
		Thread two = new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				increment();
			}
		});
		
		one.start();
		two.start();
		
		one.join();
		two.join();
		
		System.out.println("conter val :" + counter);
	}
	
	
	//  >>>> CRITICAL SECTION - since its important to logic that mitigates race condition
	private synchronized static void increment() {
        counter++;
    }

}
```


## Lock  concept

Synchronization is built around an internal entity known as the lock or monitor. Every object has a lock associated with it. By convention, a thread that needs consistent access to an object's fields has to acquire the object's lock before accessing them, and then release the lock when it's done with them.

## Limitation of synchronized method

- coarse implementation, has lines of code which do not require a lock is also locked.
- reduced concurrency,  performance bottleneck.
- subclass is overriding a parent class it is required to declare the method has synchronized even if its not required.
- synchronized method - will acquire a **class** level lock, even if one method of the **class is locked** the whole class will be locked and will be in-accessible.


## Custom Lock using synchronized block


```java
public class LockWithCustomObjects {
    private static int counter1 = 0;
    private static int counter2 = 0;

// CUSOTM LOCK OBJECTS
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment1();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment2();
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

        System.out.println("Counter values : Counter 1 " + counter1 + " Counter 2 " + counter2);
    }

    private static void increment1() {
    //  >>>> CRITICAL SECTION - since its important to logic that mitigates race condition
        synchronized (lock1) {
            counter1++;
        }
    }

    private static void increment2() {
    //  >>>> CRITICAL SECTION - since its important to logic that mitigates race condition
        synchronized (lock2) {
            counter2++;
        }
    }
}
```


## Wait and notify

- Thread that is having a lock can be put to `wait`, so that it releases the lock, and other threads can continue with the execution
- `notify` is called to awake the thread that is on `wait`
- Note: 
	- `notify` notifies single thread
	- `notifyAll` notifies all the thread

If a _waiting_ `Thread` is not _notified_ by calling `notify()` or `notifyAll()` on the object the said thread is waiting on, then any one of the following may happen:

- the `Thread` keeps waiting in the object's _wait pool_
- the `Thread` becomes _runnable_ if a timeout was specified and the time elapses
- the `Thread` gets _interrupted_ and becomes _runnable_ again
- the `Thread` wakes up for **no** reason at all i.e. it was neither _notified_ nor _interrupted_

The last case is known as a _spurious wake-up_ and is one of the reasons why upon wake-up a `Thread` should always check whether the condition it was waiting for is true or not. If not, the `Thread` should call and go `wait()` again.

```java
public class WaitAndNotifyDemo {

	private static final Object LOCK = new Object();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread one = new Thread(() -> {
			try {
				one();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread two = new Thread(() -> {
			try {
				two();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		one.start();
		two.start();

	}

	private static void one() throws InterruptedException {
		synchronized (LOCK) {
			System.out.println("Hello form method one ..");
			LOCK.wait();
			System.out.println("Back again in the method one");
		}
	}

	private static void two() throws InterruptedException {
		synchronized (LOCK) {
			System.out.println("Hello form method two ..");
			LOCK.notify();
			System.out.println("Method two running even after notify ...");
		}
	}

}
```


## Producer and Consumer Problem

The producer-consumer problem is a synchronization scenario where one or more producer threads generate data and put it into a shared buffer, while one or more consumer threads retrieve and process the data from the buffer concurrently.

![[Pasted image 20241024102802.png]]

```java
public class ProducerConsumer {
	public static void main(String[] args) {
		Worker worker = new Worker(5, 0);
		Thread producer = new Thread(() -> {
			try {
				worker.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread consumer = new Thread(() -> {
			try {
				worker.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		producer.start();
		consumer.start();
	}

}

class Worker {

	private int sequence = 0;
	private final Integer top;
	private final Integer bottom;
	private final List<Integer> container;
	private final Object lock = new Object();

	public Worker(Integer top, Integer bottom) {
		super();
		this.top = top;
		this.bottom = bottom;
		this.container = new ArrayList<>();
	}

	public void produce() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (container.size() == top) {
					System.out.println("Container full, waiting for items for removal");
					lock.wait();
				} else {
					System.out.println(sequence + " Added to the container");
					container.add(sequence++);
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}

	public void consume() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (container.size() == bottom) {
					System.out.println("Container empty, waiting for items to be added ...");
					lock.wait();
				} else {
					System.out.println(container.remove(0) + " removed from the container");
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}

}
```

