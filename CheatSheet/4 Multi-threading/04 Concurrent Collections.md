- Most of the Java collections are not thread safe
- Ways to make collections thread safe
	- Use Collections.synchronized() method
	- Use the concurrent collections which are synchronized

## Collections.synchronized() method

```java
public class SynchronisedCollections {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Thread one = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
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

        System.out.println("Size of array : " + list.size());
    }
}
```

**Downsides of using the Collections.synchronized() approach**
- Coarse grained locking
- Limited functionality
- No Fail Fast Iterators - If the Collection is change while iterating there will be no failure, this will give inconsistent values.
- Performance Overhead


## Countdown Latch

One thread waits for _n_ threads, specified while creating the `CountDownLatch`.

Any thread, usually the main thread of the application, which calls `CountDownLatch.await()` will **wait until count reaches zero or it's interrupted by another thread**. All other threads are required to count down by calling `CountDownLatch.countDown()` once they are completed or ready.

As soon as count reaches zero, the waiting thread continues. One of the disadvantages/advantages of `CountDownLatch` is that it's not reusable: once count reaches zero you cannot use `CountDownLatch` any more.

A classical example of using `CountDownLatch` in Java is a server side core Java application which uses services architecture, where multiple services are provided by multiple threads and the application cannot start processing until all services have started successfully.

- Note: if the `.countDown()` should be called enough time to make latch count 0, or else it will not end execution
- We cannot rest the count of the latch use **Cyclic Barrier** for such use case.
 ```java
 public class Resturant {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numberOfChefs = 3;

		CountDownLatch latch = new CountDownLatch(numberOfChefs);

		new Thread(new Chef("Chef A", "Pizza", latch)).start();
		new Thread(new Chef("Chef B", "Pasta", latch)).start();
		new Thread(new Chef("Chef C", "Salad", latch)).start();

		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All the dishes are ready!");
	}

}

class Chef implements Runnable {
	private final String name;
	private final String dish;
	private final CountDownLatch latch;

	public Chef(String name, String dish, CountDownLatch latch) {
		super();
		this.name = name;
		this.dish = dish;
		this.latch = latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(name + " is preparing " + dish);
			Thread.sleep(2000);
			System.out.println(name + " has finished preparing " + dish);
			latch.countDown();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
```

## join vs countdown latch

| Countdown latch                  | join                                                           |
| -------------------------------- | -------------------------------------------------------------- |
| One thread waits for _n_ threads | Thread to complete execution before proceeding current thread  |
| used for co-ordination           | Used for thread synchronization form single thread perspective |

## Blocking Queue

 Data Structure which concurrent threads to safely put items into the queue, and take item out of the queue. Two aspect:
 - Blocking aspect - 
	 - If there is a item take out request is made to empty queue, it will be blocked until the item is available. And vice versa.
- FIFO

 BlockingQueue Interface Implementation
- BlockingDeque - double ended in nature.
- TransferQueue - provides `transfer` method which allow item to transfer from one thread to another thread without blocking, if no awaiting thread it acts as `put` block until space available for item.

 Major implementations
	=> ArrayBlockingQueue
	=> LinkedBlockingQueue
	=> PriorityBlockingQueue
	=> DelayQueue
	=> SynchronousQueue

Blocking Queue Operations
	=> put(E e)
	=> take()
	=> offer(E e)
	=> poll()
	=> peek()

```java
public class BlockingQueueDemo {
    static final int QUEUE_CAPACITY = 10;
    static BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) {
        // Producer thread
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    taskQueue.put(i);
                    System.out.println("Task produced: " + i);
                    Thread.sleep(10); // Simulate task generation time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer threads
        Thread consumerThread1 = new Thread(() -> {
            try {
                while (true) {
                    int task = taskQueue.take();
                    processTask(task, "Consumer 1");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread2 = new Thread(() -> {
            try {
                while (true) {
                    int task = taskQueue.take();
                    processTask(task, "Consumer 2");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }

    private static void processTask(int task, String consumerName) throws InterruptedException {
        // Simulate task processing
        System.out.println("Task being processed by " + consumerName + ": " + task);
        Thread.sleep(1000);
        System.out.println("Task consumed by " + consumerName + ": " + task);
    }
}
```

## Concurrent Map

_ConcurrentMap_ is an extension of the _Map_ interface. It aims to provides a structure and guidance to solving the problem of reconciling throughput with thread-safety.

By overriding several interface default methods, _ConcurrentMap_ gives guidelines for valid implementations to provide thread-safety and memory-consistent atomic operations.

Concurrent Map Implementations
	ConcurrentHashMap
	ConcurrentSkipListMap
	ConcurrentLinkedHashMap
	ConcurrentNavigableMap

```java
public class ConcurrentCache {
    private static final Map<String, String> cache = new ConcurrentHashMap<>();

    private static String compute(String key) {
        System.out.println(key + " not present in the cache, so going to compute!");
        try {
            Thread.sleep(500); // Simulating computation time
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Value for " + key;
    }

    public static String getCachedValue(String key) {
        String value = cache.get(key);

        // If not in the cache, compute and put it in the cache
        if (value == null) {
            value = compute(key);
            cache.put(key, value);
        }

        return value;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            new Thread(() -> {
                String key = "Key @ " + threadNum;
                for (int j = 0; j < 3; j++) { // Fetch the same key 3 times
                    String value = getCachedValue(key);
                    System.out.println("Thread " + Thread.currentThread().getName() + ": Key=" + key + ", Value=" + value);
                }
            }).start();
        }
    }
}
```

### Internal Implementation of Concurrent Map

 Internal implementationand working of Concurrent Map
	 Adding an element to concurrent hash map
		1. Hashing and Determining Segment
		2. Acquiring Lock
		3. Insertion in Segment
		4. Releasing Lock
	Fetching an element from concurrent hash map
		1. Hashing and Determining Segment
		2. Acquiring Lock
		3. Searching in Segment
		4. Releasing Lock


## Cyclic Barrier

- Similar to countdown latch with latch count reset
```java
public class MultiStageTour {

    private static final int NUM_TOURISTS = 5;
    private static final int NUM_STAGES = 3;

// NUM_TOURISTS = count latch count, once its 0 it will be reset automatically to original value
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_TOURISTS,() -> {
        System.out.println("Tour guide starts speaking...");
    });

    public static void main(String[] args) {
        for (int i = 0; i < NUM_TOURISTS; i++) {
            Thread touristThread = new Thread(new Tourist(i));
            touristThread.start();
        }
    }

    static class Tourist implements Runnable {
        private final int touristId;

        public Tourist(int touristId) {
            this.touristId = touristId;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUM_STAGES; i++) {
                // Perform actions at each stage
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Tourist " + touristId + " arrives at Stage " + (i + 1));

                // Wait for all tourists to arrive at the current stage
                // equivalent to Countdown method
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
```


## Exchanger

Refer to a synchronization point at which threads pair and swap element within a concurrent environment.

**The _Exchanger_ class in Java can be used to share objects between two threads of type _T_.** The class provides only a single overloaded method _exchange(T t)_.

When invoked _exchange_ waits for the other thread in the pair to call it as well. At this point, the second thread finds the first thread is waiting with its object. The thread exchanges the objects they are holding and signals the exchange, and now they can return.

Exchanger is a standalone class in java.


```java
public class ExchangerDemo {
    public static void main(String[] args) {
        // Create an Exchanger with type Integer
        Exchanger<Integer> exchanger = new Exchanger<>();

        // Create two threads
        Thread thread1 = new Thread(new FirstThread(exchanger));
        Thread thread2 = new Thread(new SecondThread(exchanger));

        // Start the threads
        thread1.start();
        thread2.start();
    }
}

class FirstThread implements Runnable {
    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            int dataToSend = 10;
            System.out.println("First thread is sending data " + dataToSend);

            // Send data to the other thread and receive data in return
            int receivedData = exchanger.exchange(dataToSend);

            System.out.println("First thread received: " + receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

// Second thread implementation
class SecondThread implements Runnable {
    private final Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            int dataToSend = 20;
            System.out.println("Second thread is sending data " + dataToSend);

            // Send data to the other thread and receive data in return
            int receivedData = exchanger.exchange(dataToSend);

            System.out.println("Second thread received: " + receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Queue vs Exchanger

| Queue                                                                                                                 | Exchanger                                                                                                     |
| --------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- |
| -> One to many communication<br>-> Asynchronous<br>-> Buffering (buffer multiple data)<br>-> Non symmetrical exchange | -> Point to Point communication<br>-> Simplicity for two threads<br>-> Synchronous<br>-> Symmetrical exchange |
| Synchronous Queue unidirectional exchange                                                                             | Bidirectional exchange                                                                                        |

## Copy On Write Array

When we’re calling the _iterator()_ method on the _CopyOnWriteArrayList,_ we get back an _Iterator_ backed up by the immutable snapshot of the content of the _CopyOnWriteArrayList_.

Its content is an exact copy of data that is inside an _ArrayList_ from the time when the _Iterator_ was created. Even if in the meantime some other thread adds or removes an element from the list, that modification is making a fresh copy of the data that will be used in any further data lookup from that list.

> Similar to the git branch working, i.e. keep a copy and make changes to new copy and it is merged back to get a single reference

![[Pasted image 20241024180318.png]]

```java
public class COWADemo {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}

class Simulation {
    private final List<Integer> list;

    public Simulation() {
        list = new CopyOnWriteArrayList<>();
        list.addAll(Arrays.asList(0,0,0,0,0,0,0,0));
    }

    public void simulate() {
        Thread one = new Thread(new WriteTask(list));
        Thread two = new Thread(new WriteTask(list));
        Thread three = new Thread(new WriteTask(list));
        Thread four = new Thread(new ReadTask(list));

        one.start();
        two.start();
        three.start();
        four.start();
    }
}

class ReadTask implements Runnable {
    private final List<Integer> list;

    public ReadTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(list);
        }
    }
}

class WriteTask implements Runnable {
    private List<Integer> list;
    private Random random;

    public WriteTask(List<Integer> list) {
        this.list = list;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.set(random.nextInt(list.size()), random.nextInt(10));
        }
    }
}
```

