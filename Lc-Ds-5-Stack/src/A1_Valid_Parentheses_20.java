import java.util.HashMap;
import java.util.Stack;
/*problem:
* valid Parentheses
* constraint: input only parentheses '()[]{}' , no other chars!! much easier! */

/*solutions:
* 1st - using  java stack interface and hashmap.
* 2nd - C style solutions, use char[] as Stack. */

public class A1_Valid_Parentheses_20 {
    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "{([}])";
//
        SolutionOfValid_Parentheses_20 Object_20 = new SolutionOfValid_Parentheses_20();

        System.out.println(Object_20.isValid(s1));
        System.out.println(Object_20.isValid(s2));
        System.out.println(Object_20.isValid(s3));
        System.out.println(Object_20.isValid(s4));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class SolutionOfValid_Parentheses_20{
    /*psudo:
    create 1 hashmap <openbracket [({ , closebracket ])} >:

    * traverse s, each time 1 of the char are from open type [({ push to stack,
    * each time char are of closing ])] pop(). 2 cases:
        1-empty stack - can return false as we do not have stack to open the bracket just close.
        2-if closing bracket is unmatched from any bracket allready opened - return false
      at end, return true only if stack empty.*/
    public boolean isValid(String s) {
        Stack<Character> mystack = new Stack<>();

        HashMap<Character,Character> mymap = new HashMap<Character,Character>(){{ // to populate, use anonymous function - shorter, (only valid from java 8, no anonymous functions below):
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};


        for (char c : s.toCharArray()){
            if (mymap.containsKey(c)){  //if got one of opener [{( - add to stack.
                mystack.push(c);
            }else {                     // if its not in key - must be a close })].
                if (mystack.empty()){   //edge case - if empty return false.
                    return false;
                }

                char temp = mystack.pop();
                if (c != mymap.get(temp)){ //if c-that must be closing parenthesis is not matching closing parenthesis from hashmap
                    return false;
                }
            }
        }
//        time: O1n - traverse once.
//        space: O1n - at worse case can store the entire string (got only open {{{{{{ )
        return mystack.empty();
    }
}

/*      //no anonymous function:
        HashMap<Character,Character> mymap = new HashMap<>();
        mymap.put('(', ')');
        mymap.put('[', ']');
        mymap.put('{', '}');
*/

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class SolutionOfValid_Parentheses_20_using_charArr{
    public static boolean areBracketsBalanced(String s)
    {
        int i = -1;
        char[] stack = new char[s.length()];
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') { //if open bracket insert to "stack".
                stack[++i] = ch;
                continue;
            }

            if (i >= 0 && ((stack[i] == '(' && ch == ')')
                    || (stack[i] == '{' && ch == '}')
                    || (stack[i] == '[' && ch == ']'))){
                i--;
            }
            else
                return false;
        }
        return i == -1;
    }

    public static void main(String[] args)
    {
        String expr = "{()}[]";

        // Function call
        if (areBracketsBalanced(expr))
            System.out.println("Balanced");
        else
            System.out.println("Not Balanced");
    }
}
