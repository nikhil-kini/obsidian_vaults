* **Brute Force**
* **Greedy approach**
* **Divide and Conquer**
* **Dynamic Programming**

## Greedy Approach

* Find **locally optimal solutions** at each and every step of an algorithm

Three possible outcome:
* case 1: Globally optimal solution
* case 2: Solution but not globally optimal solution
* case 3: No Solution

Where to use?
* By seeing the problem, your for-sure, that locally optimal solution will give you globally optimal solution.
* Real-life problem of large magnitude.

## Divide and Conquer

* We divide the problem into sub-problems and the sub-problems are independent of each other.
	* Divide the problem into sub-problems
	* Conquer or solve the sub-problems
	* Combine the sub-problems for the whole solution

**Advantage:**
* Supports the parallelism.

## Dynamic Programming

* We divide the problem into sub-problems and the sub-problems are dependent on each other.
* Two Optimization approach:
	* **Top-down approach or Memorization** - if not in memory, solve and store in Memory.(**using recursion**) Natural way, Easier to code. If there is small number of sub-problem we use this.
	* **Bottom-up approach or Tabulation** - if not in memory, solve and store in Memory.(**using iteration**) Not Natural way, Harder to code. If there is large number of sub-problem we use this.

