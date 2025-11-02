
## Introduction to Fork Join Framework

Concurrency framework introduced in JAVA 7 to simply the process of parallel programming, it is designed to take advantage of multi-core systems.

Similar to Executor Service of the treads.

## Differences from Executor Framework

- Task producing Sub-tasks (divide and conquer approach )
- Per Thread Queuing and Work stealing algo (idle thread steals task from working thread)


## Use of Fork Join Framework

-> Utilization of multi core processors
-> Simplified Parallelism
-> Efficient work stealing algorithms (idle thread steals task from working thread improve efficiency)

## Key Concepts in Fork Join Framework

-> Forking
	Break large task to small task, which can be executed in parallel
-> Joining
	Waiting for the completion of the forked task and combining its results
-> RecursiveTask
	Abstract class used to **return a result after computation**, equivalent to `Callable Interface` 
-> RecursiveAction
	Used for tasks which **don't return any result**, equivalent to `Runnable Interface`

## Fork Join Pool

Implementation of Fork Join Framework. Parallelized implementation of Divide and Conquer approach

-> Work Stealing Algorithm
-> Parallelism
-> Fork & Join Operations
-> Managing Fork Join Tasks


```java
public class SearchOccurrenceTask extends RecursiveTask<Integer> {
    int[] arr;
    int start;
    int end;
    int searchElement;

    public SearchOccurrenceTask(int[] arr, int start, int end, int searchElement) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    @Override
    protected Integer compute() {
        int size = end-start+1;
        
        // Implementation of the fork join concept
        if(size > 50) {
            int mid = (start+end)/2;
            SearchOccurrenceTask task1 = new SearchOccurrenceTask(arr, start, mid, searchElement);
            SearchOccurrenceTask task2 = new SearchOccurrenceTask(arr, mid+1, end, searchElement);
            task1.fork();
            task2.fork();

            return task1.join() + task2.join();
        } 
        
        // No Implementation of concept, regular, no parallism advantage
        else {
            return search();
        }
    }

    private Integer search() {
        int count = 0;
        for (int i = start; i <= end ; i++) {
            if (arr[i] == searchElement) {
                count++;
            }
        }
        return count;
    }
}

class FJPDemo {
    public static void main(String[] args) {
        int[] arr = new int[100];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10) + 1;
        }

        int searchElement = random.nextInt(10) + 1;

        try (ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors())) {
            SearchOccurrenceTask task = new SearchOccurrenceTask(arr, 0, arr.length-1, searchElement);
            Integer occurrence = pool.invoke(task);
            System.out.println("Array is : " + Arrays.toString(arr));
            System.out.printf("%d found %d times", searchElement, occurrence);
        }
    }
}
```

```output
Array is : [4, 8, 7, 7, 6, 10, 9, 1, 4, 5, 6, 2, 9, 3, 5, 5, 2, 3, 8, 2, 4, 4, 5, 1, 10, 3, 2, 5, 10, 4, 10, 4, 9, 6, 9, 4, 3, 4, 5, 10, 3, 10, 10, 7, 6, 9, 1, 8, 9, 7, 5, 3, 5, 10, 8, 10, 2, 5, 10, 10, 2, 3, 3, 1, 4, 6, 3, 6, 1, 9, 9, 1, 5, 9, 2, 8, 2, 2, 10, 6, 4, 5, 1, 5, 8, 3, 5, 10, 4, 1, 5, 3, 3, 6, 10, 4, 4, 10, 10, 10]

2 found 9 times
```

```java
public class WorkLoadSplitter extends RecursiveAction {

    private final long workLoad;

    public WorkLoadSplitter(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        if (this.workLoad > 16) {
            System.out.println("Work Load too big, thus splitting : " + this.workLoad);
            long firstWorkLoad = this.workLoad/2;
            long secondWorkLoad = this.workLoad - firstWorkLoad;

            WorkLoadSplitter firstSplit = new WorkLoadSplitter(firstWorkLoad);
            WorkLoadSplitter secondSplit = new WorkLoadSplitter(secondWorkLoad);

            firstSplit.fork();
            secondSplit.fork();
        } else {
            System.out.println("Work Load within limits! Task being executed for workload : " + this.workLoad);
        }
    }
}

class WorkLoadSplitDemo {
    public static void main(String[] args) {
        try (ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors())) {
            WorkLoadSplitter splitter = new WorkLoadSplitter(128);
            pool.invoke(splitter);
        }
    }
}
```

```output
Work Load too big, thus splitting : 128
Work Load too big, thus splitting : 64
Work Load too big, thus splitting : 32
Work Load within limits! Task being executed for workload : 16
Work Load within limits! Task being executed for workload : 16
Work Load too big, thus splitting : 64
Work Load too big, thus splitting : 32
Work Load too big, thus splitting : 32
Work Load too big, thus splitting : 32
Work Load within limits! Task being executed for workload : 16
Work Load within limits! Task being executed for workload : 16
Work Load within limits! Task being executed for workload : 16
Work Load within limits! Task being executed for workload : 16
Work Load within limits! Task being executed for workload : 16
Work Load within limits! Task being executed for workload : 16
```