/*problem:
* take absolute path and convert to canonical path - so do not save history, remove "//" to just one "/" and etc rulse:
* must start with a single slash '/'.
* must not end with a single slash '/' , unless its the root!
* must be only one '/' if got more - delete them.
* ".." means we go up
* "..." it a valid file name */


/*2 solutions:
* 1st - use a DQ - traverse stack from start-to-end. (its actually regular stack but java allow us traverse it as DQ - so I consider it as Dequeue)
* 2nd - use classic stack, first moove to different stack to reverse order.*/

import java.util.Stack;
public class A4_SimplifyPath_71 {
    public static void main(String[] args)
    {
        String s1 = "/home/";
        String s2 = "/../";
        String s3 = "/home//foo/";

        solution_SimplifyPath_71_use_1_as_deque Object_71 = new solution_SimplifyPath_71_use_1_as_deque();

        System.out.println("Lc solution: ");

        System.out.println(Object_71.simplifyPath(s1));
        System.out.println(Object_71.simplifyPath(s2));
        System.out.println(Object_71.simplifyPath(s3));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo (share for both, change only at using a dequeue or 2 stack for same idea):
split String with each '/'.
traverse the splited words and enter to stack only the valid ones,  3 conditions:
        1 if its "//" or '.' - just skip it.
        2 if its ".." then pop the last word (first check stack not empty)
        3 if got here - its valid word add to stack for next round.

connect all words together from stack - using StringBuilder.
when we split we remove '/' - so add manualy between each stack.

return sb.toString(), if empty return just '/'.
 */
class solution_SimplifyPath_71_use_1_as_deque {
    public String simplifyPath(String path) {
        Stack <String> st = new Stack<>(); // its stack but java allow to use it as Deuqueu since traverse from start to end!
        String[] component = path.split("/"); //split the path at every /

        for (String s : component){
            if (s.equals(".") || s.isEmpty() ){ //do not enter single .
                continue;
            }else if ( s.equals("..")){
                if ( !st.empty()){ //must be in different line - cause if not wont work - will put empty ".."
                    st.pop();
                }
            }else { // if we got till here - its legitimate word and add it to path.
                st.push(s);
            }
        }

        // we need to concatenate each word , now we dont have any / so now we add them:
        StringBuilder sb = new StringBuilder();
        for (String s : st){
            sb.append("/");
            sb.append(s);
        }

        return sb.length() <= 0 ? "/" : sb.toString(); //if sb is empty return empty "/".
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class solution_SimplifyPath_71_use_2stack {
    public String simplifyPath(String path) {
        Stack <String> st = new Stack<>();
        Stack <String> reverseStack = new Stack<>();
        String[] component = path.split("/"); //split the path at every /

        for (String s : component){
            if (s.equals(".") || s.isEmpty() ){ //do not enter single .
                continue;
            }else if ( s.equals("..")){
                if ( !st.empty()){ //must be in different line - cause if not wont work - will put empty ".."
                    st.pop();
                }
            }else { // if we got till here - its legitimate word and add it to path.
                st.push(s);
            }
        }

        while ( !st.empty()){
            reverseStack.add(st.pop());
        }

        // we need to concatenate each word , now we dont have any / so now we add them:
        StringBuilder sb = new StringBuilder();
        while ( !reverseStack.empty()){
            sb.append("/");
            sb.append(reverseStack.pop());
        }

        return sb.length() <= 0 ? "/" : sb.toString(); //if sb is empty return empty "/".
    }
}