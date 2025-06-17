Backtracking

"Backtrack": is the brute force "exhaustive search" (minor optimazation for it with prune).

"Exhaustive Search": most brute force way to solve a problem, Generate all possibilities and then check each of them for a solution.
In backtracking, we Abandon/Prune - paths that cannot lead to a solution, generating far fewer possibilities.


definition:
general algorithm that incrementally builds candidates to the solutions, and abandons a candidate ("backtrack"/"prune") as soon as it determines that the candidate cannot possibly lead to a valid solution.

Backtracking is almost always implemented with recursion - it really doesn't make sense to do it iteratively.


when to use:
- Backtrack is a great tool whenever a problem wants you to find all of something, or there isn't a clear way to find a solution without checking all logical possibilities.
- on Leetcode hint is (n <= ~15) - as usually have exponential time complexities.


/* *********************** */

Generation:

very common method of avoiding duplicates in backtracking problems - having an integer argument that represents a starting point for iteration at each function call "j=i".

complexity usually very complicated so give approximately.

Time and space:
	difficult!!! i most like to think about classic A3_Combinations_77 :
		//        time:  O ((n! * k) / (n-k)! * k!) ----- first allways think about upper bound!
						as n shrink every round its n!
		//        space: Ok - for recursion and "curr".


/* *********************** */

Why we need to add to ans with "new"?
ans:
curr is passed by reference, so only one version of curr exists at any given time. In this line, we are creating a copy of curr to add to ans. If we didn't create a copy and simply added curr, we wouldn't be adding the value of curr to the answer, but a reference to it.
Thus, our answer would simply consist of many references to curr, and as curr is empty at the end of the algorithm, we would be left with many empty arrays in our answer.
