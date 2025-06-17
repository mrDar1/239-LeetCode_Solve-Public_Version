Dynamic programming (DP) - add space complexity to reduce time complexity

memoization:
at tree problems, we never visited a node more than once in our DFS,
in DP - states can be repeated, usually an exponential number of times.
When we find the answer (the return value) for a given state, we cache that value - that called memoization.
(usually store values in a hash map, if the range is known - Array prefer as it faster)


2 types of DP -  Memoization(top down) & Tabulation(bottom up):
	1. top-down:  start from the top (the original problem) and move down toward the base cases - as regular recursion.
		I.E, starts with the initial problem and then recursively calls itself to solve smaller problems.
	2. bottom-up: start at the bottom (base cases) and work our way up to larger problems - done iteratively, called tabulation.
		I.E, uses a table to keep track of subproblem results, solving the smallest subproblems before the large ones.


For most people, it's easiest to start by coming up with a recursive brute-force solution and then adding memoization to it. After that, figure out how to convert it into an (often more desired) bottom-up tabulated algorithm.


Top-down vs. bottom-up look at example of fibunacci:
-Every implementation that work on "top down" will work on "bottom-up" and vice versa!!!
-Usually, top-down approach easier to write.
-top down slower to execute, as iteration has less overhead than recursion (if language support tail recursion optimization it matter less).
-bottom-up can be optimized more than top-down so sometime it prefer.

why is top-down easier to implement than bottom-up?
In top-down, the order in which you visit states doesn't matter, whereas with bottom-up, your for loop configurations must be precise.


differentiates greedy and DP:
The idea behind greedy is that local decisions do not affect other decisions.
in DP it does affect other decisions (see example at A2, A3 - Greedy close but false).



Complexity of DP (for both: Top-down & bottom-up), same as in trees and graphs:
time:  O(n*f) - O1n-each node traverse once. inside each node doing f work.
space: O(1n)  - at the end, our cach hashMap/arr will store all the states.
"State" - necessary variable to send with recursion.


when to use it:
Problems that should be solved with DP usually have two main characteristics:
1. The problem will be asking for an optimal value (max or min) of something or the number of ways to do something.
	What is the minimum cost of doing ...
	What is the maximum profit of ...
	How many ways are there to ...
	What is the longest possible ...
2. At each step, you need to make a "decision", and decisions affect future decisions.
A decision could be picking between two elements.
Decisions affecting future decisions could be something like "if you take an element x, then you can't take an element y in the future".
again, for compare, in greedy algorithm - do not take in accounct future decisions.


/********************************/

Framework for DP
3 main components:
	1-function or data structure that will compute/contain the answer to the problem for any given state
			in simple words:
			1. what the function return.
			2. what arguments the function should take (state variables).
	2-recurrence relation to transition between states
			the hardest part!!
			how should we work with the data? in fibunacii it was (n-1)+(n-2)
	3-Base cases
			when to stop.


Converting a top-down solution to a bottom-up one:
why do we want to do it?
-some benefits of using bottom-up instead of top-down. as some optimization only possible at bottom-down such as reduce space complexity.
steps:
	1-implement the top-down approach.
	2-Initialize an array dp that is sized according to the state variables.
	3-Set base cases
	4-Write for-loop that iterate over your state variables.
			important: **start iterating from the base cases and end at the answer state**
	5-logic inside loop as it were in top-down copy from there.
	6-change return from dp(...) to dp[...]

/********************************/
1D problems -  problems that can be solved with only one state variable.

reduce space complexity:
This optimization is possible whenever the recurrence relation is static.
"static" == it doesn't change between inputs and it only cares about a constant number of states.
Unfortunately, this optimization is only possible for bottom-up, and not at top down!

/********************************/

Matrix DP

A15_MinimumPathSum_64 - show how easy to flip top-down but hard to flip bottom up!

/********************************/

The three main components of the framework are:
The base cases
The recurrence relation
A data structure and a way of visiting each DP state


Without memoization, a recursive solution for Fibonacci Number would have the time complexity:
Each call to F(n) makes 2 additional calls, to F(n - 1) and F(n - 2). Those 2 calls will then generate 4 calls, which will generate 8, etc. ans: O2^n.
