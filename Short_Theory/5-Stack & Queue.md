Stack:
an ordered collection of elements where elements are only added and removed from same end.
In the physical world, an example of a stack would be a stack of plates in a kitchen - you add plates or remove plates from the top of the pile.

In the software world, a good example of a stack is the history of your current browser's tab: let's say your on web A then B then C.
when want to go back - remove last element (C) and return to B.
and the call stack or a recursion that done using a stack.
The call at the top of the stack at any given moment is the "active" call.

another example, Function calls are pushed on a stack. The call at the top of the stack at any given moment is the "active" call.
On a return statement or the end of the function being reached, the current call is popped off the stack.

stack is LIFO, which stands for "last in, first out".

Time complexity of stack (dependent on the implementation):
	Sometimes, a stack may be implement with a linked list with a tail pointer, but here we will use the
	more common implement with DYNAMIC ARRAY:
pop and random access - O1.
search - O(1n)
push - O1 amortized-when reached the end-push will be On so its amorized, create a new bigger size and coppy all to it from the start!

uses:
For algorithm problems, stack is a good option whenever you can recognize the LIFO pattern like: "how far is the next largest element".
and Stack comes to rescue in all the pair matching type of problems.

/* *************** */
 A2 - String problems

String questions involving stacks are popular like: "20. Valid Parentheses ()[]{}"

Stacks are defined by their interface - we just need to add and remove from the same end - In Java, we can use StringBuilder as a stack as its a convenient way to get the answer in string format at the end (in c++ String are mutable so work on them).

/* *************** */
 A3 - Queues (Q)

in Queues:
Enqueue == add at tail (as "push" in regular stack).
Dequeue == delete first element. ("Dequeuing": removing first element).

While a stack followed a LIFO pattern, a queue follows FIFO (first in first out).
example of a queue in the physical world is a line at a fast food restaurant. People leave the line when they finish ordering (from the front) and people enter the line from the back (opposite ends).

example in practical software world would be any system that handles a job on a first come, first serve basis - for example, if multiple people are trying to use a printer at the same time.


One way to implement an efficient queue is by using a doubly linked list.
it can be possible to implement using dynamic array, but operations on the front of the array (adding or removal) are expensive O(n)!


uses of Q:
implement: "BFS /breadth-first search".
Outside of BFS, unlike stack, there aren't many problems whose main focus is a Q.



A7_MoovingAverageDataStream_346		-		important consept - "circular Queue" (as in use in Surveillance Camera - when reach full capacity write to oldest location).



Deque:
short for double-ended queue, and pronounced "deck".
In a deque, you can add or delete elements from both ends.
(in compare to a normal queue designates adding to one end and deleting to another end)


/******************/
short summarize:
stack  - using lifo - last in first out  (commonly implement by dynamic array, can implement with linked-list less popular).
		   commands: push & pop (both at tail)
Queues - using fifo - first in first out (commonly implement by doubly link list) commands: offer() & poll() :
offer() - add at tail
poll()  - remove from head

Deque (pronounced "deck") - double ended queues - can be add at end/start.
at java has many diffrent commands name for same functnionality!
I belive at Java best practice is to use commands:
offerFirst()- add to head.
offer()		- add to tail.
poll()		- remove head.
pollLast()  - remove tail.

/******************/

Java synonym commands for "deck" (I recommend use above commands, but Java also support these):

add elements at head:
addFirst / offerFirst / push.
				(i realy dont recommend push: in stack push is for the tail and here is head - confuse)

add elements at tail:
add / addLast / offer / offerLast.

remove elements at head:
	Poll() / removeFirst() / pollFirst() / pop()
				(i realy dont recommend pop: in stack pop is for the tail and here for head - confuse)

remove elements at tail:
	removeLast() / pollLast()

/******************/

Monotonic
stack/queue whose elements are always sorted, either ascending / descending.

maintain sorted property by removing elements that would violate the property before adding new elements. example: [1, 5, 8, 15, 23] to add 14 we must first pop 15,23 so have: [1, 5, 8, 14].

uses:
useful in problems that for each element, involves finding the "next" element based on some criteria, for example, the next greater element, how many person to the right each one can see and etc...
They're also good when you have a dynamic window of elements and you want to maintain knowledge of the maximum or minimum element as the window changes.

Monotonic stacks are a good option when a problem involves comparing the size of numeric elements, with their order being relevant.


notions:
do not say "monotonically decreasing" /"monotonically increasing" - instead say:
"monotonically non decreasing" /"monotonically non increasing" - because of duplicate value! [0,1,1,2] - is not increasing is just not decreasing!
