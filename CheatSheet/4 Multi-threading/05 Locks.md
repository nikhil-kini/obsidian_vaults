
Locks helps to achieve synchronization.

## Locks vs Synchronized block

| Locks                                                                                                                                                                                        | Synchronized block                                                          |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| _Lock_ APIs _lock()_ and _unlock()_ operation in separate methods.                                                                                                                           | **A _synchronized__block_ is fully contained within a method**              |
| **We can achieve fairness within the Lock APIs by specifying the fairness property**. It makes sure that the longest waiting thread is given access to the lock.                             | A synchronized block doesn’t support the fairness                           |
| **The _Lock_ API provides _tryLock()_ method. The thread acquires lock only if it’s available and not held by any other thread.** This reduces blocking time of thread waiting for the lock. | A thread gets blocked if it can’t get an access to the synchronized _block_ |
| **The _Lock_ API provides a method _lockInterruptibly()_ that can be used to interrupt the thread when it’s waiting for the lock.**                                                          | _synchronized block_ can’t be interrupted                                   |

## Lock Condition

Similar to `wait` and `notify` which are for synchronized, `await` and `signal` are for locks
- FIFO in case of multiple wait
![[Pasted image 20241024181526.png]]
- All at once
![[Pasted image 20241024181651.png]]

```java
public class ConditionDemo {
    private final Integer MAX_SIZE = 5;
    private final Lock lock = new ReentrantLock();
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    private void produce(int item) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == MAX_SIZE) {
                bufferNotFull.await();
            }
            buffer.offer(item);
            System.out.println("Produced >> " + item);
            bufferNotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    private void consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                bufferNotEmpty.await();
            }
            System.out.println("Consumed << " + buffer.poll());
            bufferNotFull.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    demo.produce(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumerThread = new Thread(() -> {
           try {
               for (int i = 0; i < 10; i++) {
                   demo.consume();
                   Thread.sleep(2000);
               }
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
        });

        producerThread.start();
        consumerThread.start();
    }
}

```

```output
Produced >> 0
Consumed << 0
Produced >> 1
Produced >> 2
Consumed << 1
Produced >> 3
Produced >> 4
Consumed << 2
Produced >> 5
Produced >> 6
Consumed << 3
```
## Reentrant Lock

A reentrant lock is one where a process can claim the lock multiple times without blocking on itself. It's useful in situations where it's not easy to keep track of whether you've already grabbed a lock. If a lock is non re-entrant you could grab the lock, then block when you go to grab it again, effectively deadlocking your own process.

A re-entrant lock lets you write a method `M` that puts a lock on resource `A` and then call `M` recursively or from code that already holds a lock on `A`.

Keep the count of lock invocation to resolve the release of lock.

```java
public class ReentrantLockDemo {
    private final ReentrantLock lock = new ReentrantLock();
    private int sharedData = 0;

    public void methodA() {
        lock.lock();
        try {
            // Critical section
            sharedData++;
            System.out.println("Method A: sharedData = " + sharedData);
            // Call methodB(), which also requires the lock
            methodB();
        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        lock.lock();
        try {
            // Critical section
            sharedData--;
            System.out.println("Method B: sharedData = " + sharedData);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo example = new ReentrantLockDemo();

        // Create and start multiple threads
        for (int i = 0; i < 5; i++) {
            new Thread(example::methodA).start();
        }
    }
}
```

```output
Method A: sharedData = 1
Method B: sharedData = 0
Method A: sharedData = 1
Method B: sharedData = 0
Method A: sharedData = 1
Method B: sharedData = 0
```
**Lock Fairness** - by default the lock is unfair, but you can set a boolean to make it fair.

Important methods of Reentrant Locks
-> getHoldCount()
-> tryLock()
-> tryLock(timeout, timeUnit)
-> tryLock()                 -  has a problem, suppose other thread makes this call, the latest thread will get the lock instead of prior one, to set it to fair use time set to `0`  `tryLock(0, Time.sec)`
-> isHeldByCurrentThread()
-> getQueueLength()
-> newCondition()

## Read Write Lock

Allow to read and write resource concurrently. Read heavy task primary use case.

![[Pasted image 20241025105115.png]]

```java
public class SharedResource {
    private int counter = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void increment() {
        lock.writeLock().lock();
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " writes: " + counter);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void getValue() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reads: " + counter);
        } finally {
            lock.readLock().unlock();
        }
    }
}

class ReadWriteLockDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        // Create multiple reader threads
        for (int i = 0; i < 2; i++) {
            Thread readerThread = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    sharedResource.getValue();
                }
            });
            readerThread.setName("Reader Thread " + (i + 1));
            readerThread.start();
        }

        // Create a writer thread
        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedResource.increment();
            }
        });
        writerThread.setName("Writer Thread");
        writerThread.start();
    }
}
```

```output
Reader Thread 1 reads: 0
Reader Thread 2 reads: 0
Writer Thread writes: 1
Writer Thread writes: 2
Writer Thread writes: 3
Writer Thread writes: 4
Writer Thread writes: 5
Reader Thread 1 reads: 5
Reader Thread 2 reads: 5
Reader Thread 1 reads: 5
Reader Thread 2 reads: 5
```

## Visibility Problem

- Register, cache, ram - are storage
![[Pasted image 20241025110121.png]]
- visibility problem
![[Pasted image 20241025110410.png]]

- to fix use `volatile` keyword

## Volatile keyword

`volatile`keyword will flush the lower level cache data to the share cache. All data with `volatile` are in shared cache.
- The latency is increased in this process.

## Deadlock

Deadlock can occur in a situation when a thread is waiting for an object lock, that is acquired by another thread and second thread is waiting for an object lock that is acquired by first thread. Since, both threads are waiting for each other to release the lock, the condition is called deadlock.

![[Pasted image 20241025111052.png]]

How to Spot dead locks?

-> Manual approach
-> Programmatic approach to detect deadlock
		- taking thread dump
```sh
jsp -l

kill -3 PID_of_process_class
```

- use cyclic mx bean `ThreadMXBean`
```java
public class DeadlockDemo {
    private final Lock lockA = new ReentrantLock(true);
    private final Lock lockB = new ReentrantLock(true);

    public void workerOne() {
        lockA.lock();
        System.out.println("Worker One acquired lockA");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lockB.lock();
        System.out.println("Worker One acquired LockB");
        lockA.lock();
        lockB.unlock();
    }

    public void workerTwo() {
        lockB.lock();
        System.out.println("Worker One acquired lockB");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lockA.lock();
        System.out.println("Worker One acquired LockA");
        lockB.lock();
        lockA.unlock();
    }

    public static void main(String[] args) {
        DeadlockDemo deadlock = new DeadlockDemo();
        new Thread(deadlock::workerOne, "Worker One").start();
        new Thread(deadlock::workerTwo, "Worker Two").start();

        new Thread(() -> {
            ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
            while (true) {
                long[] threadIds = mxBean.findDeadlockedThreads();
                if (threadIds != null) {
                    ThreadInfo[] threadInfo = mxBean.getThreadInfo(threadIds);
                    System.out.println("Dead Lock detected!");
                    for (long threadId : threadIds) {
                        System.out.println("Thread with ID " + threadId + " in Dead Lock");
                    }
                    break;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
```

 How to Prevent Deadlocks?
-> Use Timeouts
-> Global ordering of locks
-> Avoid nesting of locks
-> Use Thread safe alternatives