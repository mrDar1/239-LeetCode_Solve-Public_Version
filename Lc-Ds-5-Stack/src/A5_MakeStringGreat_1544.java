import java.util.Stack;
/*problem:
* return the string after remove "bad pairs"
* "bad pairs": aAb , Aab - both return just 'b'. */

/* solutions:
* 1st - stack - used as DQ
* 2nd - 2 stack
* 3rd - recursion + iteration (no good - On^2, just for fun)
* 4th - 2 recursion (same as 3, practice recursion)
* 5th - iterative way - same idea - note how important to be careful at edges!! */

public class A5_MakeStringGreat_1544 {
    public static void main(String[] args)
    {
        String s1 = "leEeetcode";
        String s2 = "abBAcC";
        String s3 = "s";

        Solution_MakeStringGreat_1544_stack_as_dequeue object_1544 = new Solution_MakeStringGreat_1544_stack_as_dequeue();

        System.out.println(object_1544.makeGood(s1));
        System.out.println(object_1544.makeGood(s2));
        System.out.println(object_1544.makeGood(s3));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//1st
class Solution_MakeStringGreat_1544_stack_as_dequeue {
    /*psudo:
    * create stack<Char>, push each letter.
    * before push, peek and if equal 'A'-'a' pop
    * convert to string using SB - traverse stack from top-to-end, as in DQ, its allow from java 8 but no classic way of work with stack*/
    public String makeGood(String s) {
        Stack <Character> stack = new Stack<>();

        for(Character c : s.toCharArray()){
            if ( !stack.empty() && Math.abs((int)(stack.peek() - c)) == ((int)('a'-'A'))) {
//          above line can replace with:
//          if (!stack.isEmpty() && Math.abs(stack.lastElement() - currChar) == 32)
                stack.pop();
            }else {
                stack.push(c);
            }
        }

//        use SB to change to string - or else will print with ',' [h,e,l,l,o]
        StringBuilder sb = new StringBuilder();
        for (Character c : stack){
            sb.append(c);
        }

//        time: O2n - 1n-build stack, 1n-traverse to sb.
//        space: O1n
        return sb.toString();
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd
class Solution_MakeStringGreat_1544_2_stacks {
    /*psudo:
     * create stack<Char>, push each letter.
     * before push, peek and if equal 'A'-'a' pop */
    public String makeGood(String s) {
        Stack <Character> stack = new Stack<>();

        for(Character c : s.toCharArray()){
            if ( !stack.empty() && Math.abs((int)(stack.peek() - c)) == ((int)('a'-'A'))) {
//          above line can replace with:
//          if (!stack.isEmpty() && Math.abs(stack.lastElement() - currChar) == 32)
                stack.pop();
            }else {
                stack.push(c);
            }
        }

//        use SB to change to string - or else will print with ',' [h,e,l,l,o]
        StringBuilder sb = new StringBuilder();
        Stack <Character> reverse_stack = new Stack<>();

        while ( !stack.empty()){
            reverse_stack.push(stack.pop());
        }

        while ( !reverse_stack.empty()){
            sb.append(reverse_stack.pop());
        }

//        time: O3n: n-build stack,n-reverse stack , n-traverse to sb
//        space: O1n
        return sb.toString();
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd
class Solution_MakeStringGreat_1544_recursion_and_iteration{
    /*psudo:
     traverse s,
     each time adjacent char are "bad pair", use substring to remove them both */
    public String makeGood(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (Math.abs((int)(s.charAt(i) - s.charAt(i+1))) == ((int)('a'-'A'))){
                return makeGood(s.substring(0,i) + s.substring(i+2));
            }
        }

//      time:  On^2: O1n-traverse letters in s, each substring takes another n-2 time, so at worst case for each n, we need another n==On^2
//      space: On^2: by recursion depth and substring connect operations:
//              each recursion reduce max of 2 char so, in worse case when all are Pairs that On/2
//              O1n-each substring operation takes O1n
        return s;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//4th
class Solution_MakeStringGreat_1544_recursion_with_recursion{
    public String makeGood(String s) {
        return makeGoodHelper(s, 0);
    }

    private String makeGoodHelper(String s, int index) {
        if (index >= s.length() - 1) {
            return s;
        }

        if (Math.abs(s.charAt(index) - s.charAt(index + 1)) == ('a' - 'A')) {
            return makeGoodHelper(s.substring(0, index) + s.substring(index + 2), Math.max(0, index - 1));
        }

        return makeGoodHelper(s, index + 1);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//5th
class Solution_MakeStringGreat_1544_iterative {
    public String makeGood(String s) {
        int i = 0;

        while (i < s.length() - 1) {
            if (Math.abs(s.charAt(i) - s.charAt(i + 1)) == ('a' - 'A')) {
                s = s.substring(0, i) + s.substring(i + 2);
                i = Math.max(i - 1, 0); // edge case: when have "bad pair" at right on start - do not decrement i below 0.
            } else {
                i++;
            }
        }
//        same time and space as other recurions
        return s;
    }
}