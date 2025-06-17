Heaps
heap is one of many ways to implement a priority queue, but its maximally efficient.

max/ min heap:
If a heap is configured to find/remove the min element, it's called a min heap.
If a heap is configured to find/remove the max element, it's called a max heap.


Heaps complexity:
O(1) 	  - Find minimum element (/max element in max heap)
O (log n) - Add an element 			--- just to be clear not O(n log n)
O (log n) - Remove minimum element	--- just to be clear not O(n log n)


Heaps constraint: must be a complete tree.
rememeber: "complete tree" is "perfect binary tree", but not at last level of depth.
at last depth - can be with 0/1/2 nodes, all nodes are as left as possible.
	Intuitive when represent tree's with array - comlete trees do not have null inside array.


in many problems, using heap improve time complexity from O(n^2) to O(n log n), for n=1milion, its 50,000 times faster!

A heap is a great option whenever you need to find the maximum or minimum of something repeatedly.


Note: some languages implement a min-heap by default (java, python), while some implement a max heap by default (c++).
at c++ an easy way for opposite is to *(-1).

in java use:
		 PriorityQueue<Integer> mypq = new PriorityQueue<>(); 				//min heap - can stay empty as it java default.
		 PriorityQueue<Integer> mypq = new PriorityQueue<>((a,b) -> a - b);	//min heap. explicitly min-heap.

		 PriorityQueue<Integer> mypq = new PriorityQueue<>((a,b) -> b - a);			    //max heap.
		 PriorityQueue<Integer> mypq = new PriorityQueue<>(Comparator.reverseOrder());  //max heap.

		 (note: if measure your program speed "lambda expressions ->" are faster than "Comparator.reverseOrder" -- both way do same things!):
/* *********************** */
Heap examples
amazing tool whenever you need to repeatedly find the maximum or minimum element.


important:
if need to find a median, think about 2 Heaps, see:
A3_FindMedianfromDataStream_295 - note: Israel open university, have that question at every exam of algorithm!
/* *********************** */

Top k
"find best k element" - can easily solve with "sort"/"hashmap" - but in O (n log n).
with heap, we can solve it in O (n log k) (k derive from n and allways smaller) - it wont change so much, as log is so fast anyway. but, it is these small improvements that make your code awsome.


A7_TopKFrequentElements_347 - sort heap according to freq of elements from hashmap.
A8_FindKClosestElements_658 - sort heap according to distances from x.

/* *********************** */
Q & A:

What is the fastest an arbitrary array can be converted into a heap?
	-ans O(n).
	-The process is not trivial, but the major programming languages have built-in methods for it.



The heap property (for a min heap) is that children have values larger than their parents. This doesn't imply that the maximum element will be the final one.

/* *********************** */

heap implement (at min-heap):
"bubbling up":
When elements are added or removed, operations are done to maintain the aforementioned property of "parent <= child".
The number of operations needed scales logarithmically with the number of elements in the heap.
add:
	first add to left, only then right.
	after add, "correct invariant": swap all the way until reach a "correct relative location" (could be all the way up).
	what call: "start at bottom and move up"

remove:
	can only delete the top (at min-heap is the smallest node).
	then remove leaf-node (right first) and put it at top of tree (that way, easier with tree ptrs).
	"correct invariant" with swap - this time: "start at top and move downward": each time swap top with smallest child.


common patterns at heap:
Arr[0]			root element.
for Arr[i]:
Arr[(i-1)/2]	Returns the parent node
Arr[(2*i)+1]	Returns the left child node
Arr[(2*i)+2]	Returns the right child node
