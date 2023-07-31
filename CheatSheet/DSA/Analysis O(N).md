
* Best case analysis - Big Omega
* Average case analysis - Big Theta
* Worst case analysis - Big **O(N)** - Max amount of time taken for the operation.

## Big O

* Calculate the step count (Ex: 5n+5)
* Ignore the lower-order terms ( 5n)
* Drop the constraints (n)
* Time complexity (O(N))

| Syntax | Time Complexity|
|-------|------|
|for-loop, **counter ++ or --** (ex:`for(i=0; i<N; i++))` | O(N)|
|for-loop, **counter \* or \/** (ex:`for(i=0; i<n; i\*2))` | O(logN), base:2 (i/2)  |
|for-loop nested loop(same constraint across loops) ` for(i; i<N; i++){for(j; j<N; j++)}}`| O(N^c), O(N^2)
|for-loop nested (diff constraint across)  ` for(i; i<N; i++){for(j; j<M; j++)}}` | O(N\*M)
|for-loop nested ` for(i; i<N; i++){for(j; j<N; j*2)}}`| O(NlogN)
|for-loop `for(i=0; i<N ; i^2)` | O(loglogN) base:2

### O(1) > O(loglogN) > O(logN) > O(N) > O(NlogN) > O(N^2) > O(2^n) > O(e^N) > O(N!)


## Recursion

**If it is possible to break the problem into smaller pieces which resemble the original problem**

* **Base Condition** - Breaks the recursive loop
* **Recursive Condition** - Call the recursive method

**Disadvantages**
* **Stack Overflow error -  if the value is large.**
* **Code will be slower compared to loops**
* **Mutual Recursion** (A calls B, and B calls A)

## Bit-wise Operator

| **Operators**| **Description**| |
|---|---|---|
|**&**|**Bitwise AND**| |
|**\|**|**Bitwise OR**| |
|**^**|**Bitwise Exclusive OR**| |
|**~**|**Bitwise Complement**| |
|**<<**|**Bitwise Shift Left**| n\*(2^i), i no. of bits shifted|
|**>>**|**Bitwise Shift Right**(Arithmetic)| floor of n\/(2^i), i no. of bits shifted |
|**>>>**|**Bitwise Shift Right zero fill** (Logical)| floor of n\/(2^i), i no. of bits shifted (for only + int)|

**To Check if the number is power of 2**
```
if (n !+=0 && (n&(n-1))==0)
	no. is power of 2
else
	not power of 2
```

**Get the i <sup>th</sup> bit of a number 'n'**
```
if ((n & (1<<i))==0)
	"ith bit is 0"
else
	"ith bit is 1"
```

**Set the i <sup>th</sup> bit of a number 'n' to 1**
```
return (n | (1<<i))
```

**Set the i <sup>th</sup> bit of a number 'n' to 0**
```
return (n & (~(1<<i)))
```

**Toggle the i <sup>th</sup> bit of a number 'n' **
```
return (n ^ (1<<i))
```
