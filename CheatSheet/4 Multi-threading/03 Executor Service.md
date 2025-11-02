	Note: DEAD LOCK
	- All Exeptions must be handeled with try catch to `executor.shutdown()` the executor, the thrown exception will not shutdown the executor.
	- `try(executor_init){}` above Java V21 handles execption shutdown

The Java ExecutorService is the interface that allows us to execute tasks on threads **asynchronously**. It is present in the java.util.concurrent package. The ExecutorService helps **maintain a pool of threads and assigns them tasks**. It also provides the facility to **queue up tasks until there is a free thread available** if the number of tasks is more than the threads available.

Additionally, the ExecutorService interface provides ways to control how tasks are carried out.

Types:
- SingleThreadExecutor
- FixedThreadPoolExecutor
- CachedThreadPool
- ScheduledExecutor

![[Pasted image 20241024110239.png]]
## Thread Pool
**Java Thread pool** represents a group of worker threads that are waiting for the job and reused many times.


## SingleThreadExecutor

- Uses single thread to execute the task
![[Pasted image 20241024111743.png]]

```java
public class SingleThreadExecutorDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 5 ; i++) {
			executorService.execute(new Task(i));
		}
		
		executorService.shutdown();
		
		/*
		 * for JAVA version 21 and above to auto shutodown
		 * try (ExecutorService executorService = Executors.newSingleThreadExecutor()){
		 * for (int i = 0; i < 5 ; i++) { executorService.execute(new Task(i)); } }
		 */
	}
}

class Task implements Runnable{
	private final int taskId;

	public Task(int taskId) {
		super();
		this.taskId = taskId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Task with ID " + taskId +  " being executed by Thread " + Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
```

```output
Task with ID : 0 being executed by thread : pool-1-thread-1
Task with ID : 1 being executed by thread : pool-1-thread-1
Task with ID : 2 being executed by thread : pool-1-thread-1
Task with ID : 3 being executed by thread : pool-1-thread-1
Task with ID : 4 being executed by thread : pool-1-thread-1
```

## FixedThreadPoolExecutor

- A pool of threads execute tasks in the queue.
- Execution sequence cannot be determined.
![[Pasted image 20241024115008.png]]

```java
public class FixedThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < 5 ; i++) {
			executorService.execute(new Work(i));
		}
		
		executorService.shutdown();
		
		/*
		 * for JAVA version 21 and above to auto shutodown
		 * try (ExecutorService executorService = Executors.newFixedThreadPool(2)){
		 * for (int i = 0; i < 5 ; i++) { executorService.execute(new Task(i)); } }
		 */
	}
}

class Work implements Runnable{
	private final int workId;
	
	public Work(int workId) {
		super();
		this.workId = workId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Task with ID " + workId +  " being executed by Thread " + Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
```

```output
Task with ID : 2 being executed by thread : pool-1-thread-2
Task with ID : 1 being executed by thread : pool-1-thread-1
Task with ID : 3 being executed by thread : pool-1-thread-1
Task with ID : 4 being executed by thread : pool-1-thread-2
Task with ID : 5 being executed by thread : pool-1-thread-1
Task with ID : 6 being executed by thread : pool-1-thread-2
Task with ID : 7 being executed by thread : pool-1-thread-1
```

## CachedThreadPool

- No need to give the thread pool count
- Synchronous queue can have only one task, if there is no free thread available it will create a new thread and execute.
- free thread can idle for 60 sec after that they are killed.
![[Pasted image 20241024120005.png]]
```java
public class CachedThreadPoolDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 1000; i++) {
			executorService.execute(new TaskOne(i));
		}

		executorService.shutdown();

		/*
		 * for JAVA version 21 and above to auto shutodown try (ExecutorService
		 * executorService = Executors.newCachedThreadPool()){ for (int i = 0; i < 5 ;
		 * i++) { executorService.execute(new Task(i)); } }
		 */
	}

}

class TaskOne implements Runnable {

	private final int taskId;

	public TaskOne(int taskId) {
		super();
		this.taskId = taskId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Task with ID " + taskId + " being executed by Thread " + Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
```

```output
Task : 9 being executed by pool-1-thread-10
Task : 1 being executed by pool-1-thread-2
Task : 5 being executed by pool-1-thread-6
Task : 8 being executed by pool-1-thread-9
Task : 6 being executed by pool-1-thread-7
Task : 4 being executed by pool-1-thread-5
Task : 7 being executed by pool-1-thread-8
Task : 3 being executed by pool-1-thread-4
Task : 2 being executed by pool-1-thread-3
Task : 0 being executed by pool-1-thread-1
```

## ScheduledExecutor

- All task are in Delay Queue, asynchronous in nature.
- Use `shutdownNow()` to immediate shutdown after condition, `shutdown()` is wasteful in nature, waits for the queue to be empty before shutdown.
![[Pasted image 20241024121414.png]]
```java
public class ScheduledExecutorDemo {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);  // thread pool size
		scheduledExecutorService.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, TimeUnit.MILLISECONDS); // run probe task, start after 1 sec, sleep 2 sec before next execution
		
		try {
			if(!scheduledExecutorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) { // run executor for 5 sec.
				scheduledExecutorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			scheduledExecutorService.shutdownNow();
		}
		
	}

}

class ProbeTask implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Probing end point for updates ....");
	}
	
}
```

```output
Probing end point for updates...
Probing end point for updates...
Probing end point for updates...
```

## Ideal thread pool size

- It Depends,
	- CPU - 4 core = 4 thread
		 - more threads will reduce performance, due to switching
	- IO intensive tasks
		- Experiment performance

```java
public class CpuIntensiveTask {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        System.out.println("Created thread pool with " + cores + " cores");
        for (int i = 0; i < 20; i++) {
            service.execute(new CpuTask());
        }
    }
}

class CpuTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Some CPU intensive task being done by :" + Thread.currentThread().getName());
    }
}
```

## Callable Interface

- If you want to return a value from multi-threaded method use `Callable` instead of `Runnable` interface

- `future.get() `is blocking in nature
![[Pasted image 20241024123230.png]]

```java
public class CallableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Future<Integer> result = executorService.submit(new ReturnValueTask());

//		System.out.println(result.get());
//		System.out.println("Main thread execution complete!");

		/*
		 * result.cancel(true); to cancel the future boolean
		cancelled = result.isCancelled(); 
		boolean done = result.isDone(); to check if future is complete
		 */

		// >> set timeout for ending .get within the secified limit
		try {
			System.out.println(result.get(1000, TimeUnit.MILLISECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			executorService.shutdownNow();
		}

		System.out.println("Main thread execution complete!");

		executorService.shutdownNow();

		/*>>> try(init){} Exception auto closes the executorService
		 * try(ExecutorService executorService = Executors.newFixedThreadPool(2)) {
		 * Future<Integer> result = executorService.submit(new ReturnValueTask());
		 * 
		 * System.out.println(result.get(1, TimeUnit.SECONDS));
		 * System.out.println("Main thread execution completed!"); }
		 */
	}

}

class ReturnValueTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(5000);
		return 12;
	}

}
```

```output
12
Main thread execution completed!
```

error due to timeout
```output
Exception in thread "main" java.util.concurrent.TimeoutException
at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:204)
at executorService.CallableDemo.main(CallableDemo.java:16)
```