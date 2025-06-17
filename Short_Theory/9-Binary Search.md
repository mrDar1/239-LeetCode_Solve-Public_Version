Binary Search

search in: O(log n) find index of value x in arr, or find index to add value x, so remain in sorted manner.

Normally, binary search is done on an array of sorted elements, but can use binary search in more creative ways, as we'll see.

real-lif-example: if you have ever looked up a word in a dictionary, then you probably flipped to about the middle, looked at the first letter of the words on the page you flipped to, and then either checked the left or right half depending on the first letter of the word you were looking for.

Formula to avoid Overflow: (in python / javascript no overflow, but relavnt for languge with overflow as c/Java):
left  + ((right - left) / 2) ---- used to avoid overflow.
right - ((right - left) / 2) ---- used to avoid overflow.


if the element you are searching for does not exist, then to maintain sorted order (just like in a normal binary search)
 - insert left!


anytime the problem provides anything sorted, think about binary search - usually it's a huge optioptimization.

/* *********************** */
"O(n) solution spaces" / "Binary search - for greedy problems" / "find threshold" :

common at: "what is the max/min that something can be done".

When a problem wants you to find the min/max, it wants you to find the threshold where the task transitions from impossible to possible.
	(threshold dictionary: When the threshold is reached, something else happens or changes.)

framework steps:
1st - establish the possible solution space by identifying the minimum and maximum possible answer. (lets call it k.) (I like to call it the upper boundary and lower boundary).
2nd - binary search on this solution space, until find the threshold.
		so complexity is O(n log k).

return left  == ask for minimum
return right == ask for maximum

/* *********************** */
Q & A:

The max iterations a binary search does is logarithmic relative to the input size. What is the base of this logarithm?
  	ans: 2 - each time split by half.

The worst case time complexity of binary search is O(log n). What is the best case?
	ans: O(1) - when ans is "middle".

What is the worst case space complexity of binary search?
	ans: O(1) - only 3 vars.
