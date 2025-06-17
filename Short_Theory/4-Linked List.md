Linked lists
Advantages and disadvantages compared to arrays:

Advantages:
in linked list  - add and remove elements at any position in O(1), but must have reference to a node at the position in which you want to perform the addition/removal, otherwise the operation is O(n).
in (dynamic)arr - its O(n) from anywhere.

Linked lists having fixed sizes. While dynamic arrays can be resized, under the hood they still are allocated a fixed size - it's just that when this size is exceeded, the array is resized, which is expensive thing to do. Linked lists don't suffer from this.


disadvantages:
no random access:
If you have a large linked list and want to access the 200,000th element, then there usually isn't a better way than to start at the head and iterate 200,000th times.
In arr - O(1).

have more overhead than arrays - every element needs to have extra storage for the pointers. If you are only storing small items like booleans or characters, then you may be more than doubling the space needed.

________________________
How to work with pointers in Java?

A language like C/C++ has explicit pointers, indicated by the asterik *.
In languages without explicit pointers, like Java all non-primitive variables (like custom class objects) are treated as pointers.

"Java Wrapper": way to sign the compiler when to move by value (primitive datatypes: int, float), and when to move by reference (Wrapper: Integer, Float).
Java Wrapper, has some more properties like the ability to asign null, example: "Integer a = null;" - what not possible at regular primitive datatypes.

________________________

types of Linked lists:
1. Singly linked list
2. Doubly linked list
3. Linked lists with sentinel nodes - (for the head and tail of the list - code with sentinels is much less prone to error, there are no values in the sentinels and all the nodes with values sit between them).
4. Dummy pointers technique - when traverse the list use dummy - its copy of the ptr so we wont change the original head ptr that still points to start.

We call the start of a linked list the head and the end of a linked list the tail.
________________________

almost all link list problem can use array to solve! but that not the idea...
The point of these problems is usually to manipulate pointers in a clean way using O(1) space.
many solutions are difficult to reach because of their elegance.

note about "arr / dynamic arr":
at language like C - all arr are fix. we have workaround to make them "dynamic arr" == adjustable size.
at java / python usually they are all adjustable size.