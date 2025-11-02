
Multi-Threading is the ability of CPU to perform different tasks concurrently.


## Concurrency vs Parallelism

- Concurrency - single entity (hand) switch between tasks so fast that seems like they are running at the same time. (fake parallelism)
	- No Simultaneous execution
	- Sequential execution
- Parallelism - different tasks are handled by different entity (hand).
	- Simultaneous execution
	- distributed of GPU computing


## Process vs Thread

![[Pasted image 20241023174707.png]]

| Process                                                                                                                                              | Thread                                                                                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Process is an instance of program execution. When you enter an application, it's a process. The  OS assigns **it's own Stack and Heap memory area**. | Thread is a lightweight process. It is a **unit of execution within a given program**. A single process may contain multiple threads. Each thread in the process shares the memory and resources. |

## Time Slicing Algorithm

- Switches CPU compute between the treads
![[Pasted image 20241023154613.png]]

## Thread Life-cycle

![[Pasted image 20241023154918.png]]

## Ways to run multiple thread

- Runnable Interface
- Extending Thread Class


## Runnable Interface (better approach because you can inherit multiple things)

```java
public class RunnableThreadExample {
	public static void main(String[] args) {
		Thread one = new Thread(new ThreadOne());
		Thread two = new Thread(new ThreadTwo());
		Thread three = new Thread(() -> {
			// TODO Auto-generated method stub
			for (int i = 0; i < 51; i++) {
				System.out.println("            Thread Three: " + i);
			}
		});

		one.start();
		two.start();
		three.start();
	}
}

class ThreadOne implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 51; i++) {
			System.out.println("Thread One: " + i);
		}
	}

}

class ThreadTwo implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 51; i++) {
			System.out.println("Thread Two: " + i);
		}
	}

}
```

```output
Thread Two : 0
Thread Two : 1
Thread Two : 2
Thread Two : 3
Thread Three : 0
Thread Three : 1
Thread One : 0
Thread Two : 4
```
## Extending Thread Class

```java
public class ExtendsThreadExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread one = new Thread1();
		Thread two = new Thread2();

		one.start();
		two.start();
	}

}

class Thread1 extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 51; i++) {
			System.out.println("Thread One: " + i);
		}
	}

}

class Thread2 extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 51; i++) {
			System.out.println("Thread Two: " + i);
		}
	}

}
```

```output
T2 : 0
T2 : 1
T1 : 0
T2 : 2
T1 : 1
```
## join()

- Main thread is a parent thread
- Independent execution of thread - under normal execution all thread are running independently

- `child.join()` parent thread i.e. main() is waiting for the child thread to complete, for it to continue. `join` = `waitThenContinue`
- wait current till the `join()` thread is complete.
```java
public class JoinThreadExample {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread one = new Thread(() -> {
			for (int i = 0; i < 51; i++) {
				System.out.println("Thread One: " + i);
			}
		});
		Thread two = new Thread(() -> {
			for (int i = 0; i < 51; i++) {
				System.out.println("Thread Two: " + i);
			}
		});
		
		one.start();
		two.start();
		one.join();  // main thread waits for one to complete
		System.out.println("Done executing the ThreadS");
	}
	
}
```

```output
Before invoking threads...
Thread 1 : 0
Thread 1 : 1
Thread 1 : 2
Thread 1 : 3
Thread 1 : 4
Thread 2 : 0
Thread 2 : 1
Thread 2 : 2
Done executing the threads!
Thread 2 : 3
Thread 2 : 4
Thread 2 : 5
Thread 2 : 6
```
## Daemon Thread

On basis of surface execution, two thread
- Daemon Threads
- User Threads - normal threads

- Demon threads are intended to be helper threads which can be run in background and are of low priority. Eg GC thread
- Daemon thread are terminated by the JVM when all other user threads are terminated (done with their execution)
- So, under normal circumstances, user threads are allowed to terminate once they are done with their execution, however, the daemon threads are shutdown by JVM once all the other threads are done executing.

- The *demon thread* is shutdown by JVM after execution of the *user thread*
- All thread are normal threads, unless set to daemon `thread.setDaemon(true)`
```java
public class DaemonUserThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread bgThread = new Thread(new DaemonHelper());
		Thread userThread = new Thread(new UserThreadHelper());
		bgThread.setDaemon(true);
		
		bgThread.start();
		userThread.start();
	}

}

class DaemonHelper implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		while (count < 500) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
			System.out.println("Daemon helper running ...");
		}
	}
	
}

class UserThreadHelper implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("UserHelper run");
	}
	
}
```

```output
Daemon helper running ...
Daemon helper running ...
Daemon helper running ...
Daemon helper running ...
User Thread Helper done with execution!
```

## Thread Priority

- Let's say **10 threads** in **runnable state**, only 1 CPU, to decide which thread should run first, **Thread Scheduler** sets priority for order of execution. We can set it has well.
- Under **normal circumstances** higher priority thread gets to run on the CPU.
- Default priority of a thread is **5 (NORM_PRIORITY)**. The value of **MIN_PRIORITY is 1** and the value of **MAX_PRIORITY is 10**.
- **two threads that have the same priority**, then one **can not predict** which thread will get the chance to execute first. The execution then is **dependent on the thread scheduler's algorithm** (First Come First Serve, Round-Robin, etc.)

