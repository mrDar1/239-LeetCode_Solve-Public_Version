Greedy algorithms:

any algorithm that follows 2 rules: choose locally optimal decision at every step.
1. what makes a decision "optimal"?
	-	example: find the max sum: given a choice between two numbers, it is optimal to take the larger one.

2. what makes a decision "local"?
	-	consider only the available options at the current step. doesn't consider any future consequences.

In many problems, a greedy approach may lead to an answer that is very close to the correct answer, but still wrong.
in real life, greedy algorithms can give good approximations with significantly less computation.

in many greedy problems, first, sorting the input at the start. this is because we want to greedily choose the max/min elements in many problems, and sorting the input makes this convenient.

clasic problem:
"TSP / travelling salesman problem":
use greedy and time complexity is O(n^2), greedy solution can only wrong at about 25% at most.
for an exact solution, time complexity is O(2^n) (which way slower), (and many people doubt such an algorithm exists.)



great example:
Imagine that you were delivering pizzas, and you had to visit 5 different houses. You're terrible at planning, so you decide to just start with the nearest house. After completing that delivery, you again choose to go to the nearest of the 4 remaining houses, and so on until you finish the deliveries. It turns out, that if you went in a different order, there was a bridge between two of the houses that could have reduced your total travel time. At each step, you were greedy and only cared about the immediate optimal choice. The choices were local because you considered only the next house and ignored all future decisions at the time of each decision.





__________
Usually, if a problem can be solved greedily, then it is the fastest way to solve it. The hard part is figuring out if you can use greedy.

The most common thing associated with greedy algorithms is: Sorting.

The most common characteristic greedy problems have is:
-Problem is asking for the max/min of something (is shared with dynamic programming)

from prev chapter - heaps, most problem could classified as greedy.
