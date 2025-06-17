/*problem:
* return true if 2 strings equal
* # == "backspace" */

/*2 solutions, both same :
1- using Stack data structure
2- using String-Builder */

import java.util.Stack;
public class A3_BackSpaceStringCompare_844 {
    public static void main(String[] args)
    {
        String s1 = "ab#c", t1 = "ad#c";
        String s2 = "ab##", t2 = "c#d#";
        String s3 = "a#c", t3 = "b";
        Solution_BackSpaceStringCompare_844 Object844 = new Solution_BackSpaceStringCompare_844();
        System.out.println(Object844.backspaceCompare(s1,t1));
        System.out.println(Object844.backspaceCompare(s2,t2));
        System.out.println(Object844.backspaceCompare(s3,t3));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//1st - use Stack
class Solution_BackSpaceStringCompare_844 {
    public boolean backspaceCompare(String s, String t) {
        Stack <Character> s_stack = new Stack<>();
        Stack <Character> t_stack = new Stack<>();

        for (Character c : s.toCharArray()){
            if (c != '#'){
                s_stack.push(c);
            }else {
                if ( !s_stack.empty()){
                    s_stack.pop();
                }
            }
        }
        for (Character c : t.toCharArray()){
            if (c != '#'){
                t_stack.push(c);
            }else {
                if ( !t_stack.empty()){
                    t_stack.pop();
                }
            }
        }
//        time: O1n - traverse once.
//        space: O1n - save to stack.
        return s_stack.equals(t_stack);
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    2nd - use StringBuilder
    public boolean backspaceCompare_use_stringbuilder(String s, String t) {
        return build(s).equals(build(t));
    }
    public String build(String s) {
        StringBuilder stack = new StringBuilder();

        for (char c: s.toCharArray()) {
            if (c != '#') {
                stack.append(c);
            } else if (stack.length() > 0) {
                stack.deleteCharAt(stack.length() - 1);
            }
        }
//        time: O1n - traverse once.
//        space: O1n - save to stack.
        return stack.toString();
    }
}


