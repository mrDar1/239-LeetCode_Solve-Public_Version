A hash function is a function that takes an input and deterministically converts it to an integer that is less than a fixed size set by the programmer (use modulu %). same input will always be converted to the same integer.
The main constraint with arrays is that they are a fixed size, and the indices have to be integers.

Because hash functions can convert any input into an integer, we can effectively remove the constraint of indices needing to be integers. When a hash function is combined with an array, it creates a hash map, also known as a hash table or dictionary.

With arrays, we map indices to values. With hash maps, we map keys to values, and a key can be almost anything!
hash map's keys are immutable! (this is language dependent but generally a good rule of thumb)
hash map is an unordered data structure that stores key-value pairs.


An ordered data structure is one where the insertion order is "remembered" (like link-list). An unordered data structure is one where the insertion order is not relevant - like hashmap.

To summarize, a hash map is an unordered data structure that stores key-value pairs. You can iterate over both the keys and values of a hash map, but the iteration won't necessarily follow any order (there are many implementations and this is language dependent for the built-in types).


O(1) complexity in hashmap & arr:
	Find length/number of elements
	Updating values
	Iterate over elements

O(1) complexity in hashmap (not O(1) in arr):
	Add an element and associate it with a value
	Check if an element exists
	Delete an element if it exists

disadvantage:
1. The biggest disadvantage of hash maps is that for smaller input sizes - big O ignore constans, hashing O1 is more like O(10).
when the input is small - hashmap will be slower than arr, even thouge its O1 complexity.
2. Hash tables can also take up more space - implemented using a fixed size array - that takes more time to increase than array.
3. collisions.


Collisions - When different keys convert to the same integer.
multiple ways handle them, here we talk about chaining - linkedList that connect the coollisions.
to avoid Collisions - use prime numbers for the modulu, mostly:
10,007
1,000,003
1,000,000,007


HashSets:
difference - sets do not map their keys to anything.
Sets are more convenient to use when you only care about checking if elements exist. You can add, remove, and check if an element exists in a set all in O1.

An important thing to note about sets is that they don't track frequency. If you have a set and add the same element 100 times, the first operation adds it and the next 99 do nothing.

________
Map - has 3 most common types:
1. HashMap/HashSets - regular, fastest, unorder.
2. TreeSet - sorted, like alphabetically. slow speed.
3. LinkedHashset - saves order of insertion - faster than TreeSet, slower than HashMap.
________


Arrays as keys?
being immutable is usually a requirement for being a hash map key.
in Java - Arrays are mutable, so how do we store an ordered collection of elements as a key?
- Answer: convert the array into a string.
(C++ support mutable keys in their built-in implementations.)
(Python use tuple: an ordered collection of elements, similar to a list. However, unlike lists, tuples are immutable.




note:
at java, can traverse entire keys of hashtable, one by one as in for loop (and with key reach key and/or value). but cannot traverse values directly!

at java,
"getOrDefault": if try to "get" key that is not at hash, will get error, to work around use "getOrDefault" - so if its not exist - create it now.
