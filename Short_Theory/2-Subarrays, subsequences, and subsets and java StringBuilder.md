Subarrays/substrings, subsequences, and subsets:

1. "Subarrays/substrings": are most strict, contiguous section of the original arr/string must be exactly the same like origin arr/string, but could be only small section of it.

2. "Subsequences": less strict than "Subarrays/substrings" but more than "subsets" - contiguous section of the original arr/string that in the same order, but could be with deleted element.
example: [1, 2, 3, 4]
includes:[1, 3], [4], [], [2, 3], [1,4], [2,4]
but not [3, 2], [5], [4, 1].

3. "Subsets": the most free of them - section of original arr - order doesnt matter, element just need to exist.
example: [1, 2, 3, 4]
includes: <all of Subsequences and:>[3, 2], [4, 1, 2], [1]
note - since the order doesnt cout, both here count the same: [1, 2, 4]==[4, 1, 2]



/**************************************************************************/

Java "StringBuilder" - what is it good for:
in most languages, strings are immutable. This means concatenating a single character to a string is an O(n) operation.
If you have a string that is 1 million characters long, and you want to add one more character, all 1 million characters need to be copied over to another string.
Many problems will ask you to return a string, and usually, this string will be built during the algorithm - so if we build our string, one char at a time - it will be O(n^2)! because each time need to copy the entire string!!
very expensive! not good!

So what we do?
1. use "StringBuilder" class.
2. When building the string, add the characters to the "StringBuilder". This is O(1) per operation. Across n operations, it will cost O(n) in total.
3. Once finished, convert the "StringBuilder" to a string using "StringBuilder.toString()" This is O(n) operation.
4. In total, it cost us :O(n+n)=O(2n)=O(n)

"
public string buildString(String s){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++)
    {
        sb.append(s.charAt(i));
    }
    return sb.toString();
}
"
In C++ and JavaScript, simply using += is fine when building strings.
