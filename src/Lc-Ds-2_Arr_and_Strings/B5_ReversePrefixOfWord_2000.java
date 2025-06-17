import java.util.*;
/*problem:
* reverse only part of the string - from start until "ch"*/

/*solutions:
* 1st - 2 ptrs - best!
* 2nd - Stack - not so elegant.. my first success
* 3rd - Stack - optimized!! solving again */
public class B5_ReversePrefixOfWord_2000 {
    public static void main(String[] args){
        String s = new String("abcdefd");
        char ch = 'd';
        System.out.println(reversePrefix(s,ch));
    }

    public static String reversePrefix(String word, char ch) {
        /*psudo:
        * copy input string to mutual string (StringBuilder).
        * traverse "word" until find "ch"
        * use a swap method from l to r ptr (l==0)*/
        StringBuilder sb = new StringBuilder(word);
        int r = 0;
        int l = 0;

        for( ; r < word.length() ; ++r ){
            if(word.charAt(r) == ch){
                break;
            }
        }

//        edge case: (if the 'ch' isn't at word).
        if(r == word.length()) return word;

        while( l < r) {
            Swap(sb, l++ , r--);
        }
        return sb.toString();
    }
    public static void Swap(StringBuilder str, int l, int r){
        char t = str.charAt(l);
        str.setCharAt(l, str.charAt(r));
        str.setCharAt(r, t);
    }
    //time: O2.5 - 1st stringbuilder, 2nd-find 'ch'. 3rd-swap.
    // space: O1 not count return n.



    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    public static String reversePrefix_stack(String word, char ch) {
        /*psudo:
        * until find "ch" put everything to stack
        * if exahusted - return original "word".
        * when found ch - put to new string until stack empty, the rest from original */
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();
        boolean isChFound = false;

        if (word.length() <= 1){
            return word;
        }

        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == ch && !isChFound){
                isChFound = true;
                st.push(word.charAt(i));
                if (i == word.length() - 1){
                    while (!st.empty()){
                        ans.append(st.pop());
                    }
                }
                continue;
            }

            if ( !isChFound){
                st.push(word.charAt(i));
                continue;
            } else {
                while (!st.empty()) {
                    ans.append(st.pop());
                }
            }

            ans.append(word.charAt(i));
        }
//        time:  O2n - first until find ch push to stack, seond-from stack to ans.
//        space: O1n - for the stack (do not count sb as it output)
        return isChFound ? ans.toString() : word;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    public String reversePrefix_opimized_stack(String word, char ch) {
        /*psudo:
        * traverse word: each time push to stack - until exahuted then return original string.
        * if find ch - fill StringBuilder with stack,pop() until its empty.
        * from that position, push chars to sb until finish and return sb.*/
        int i = 0;
        Stack<Character> st = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for ( ; i < word.length() ; ++i){
            st.push(word.charAt(i));

            if (word.charAt(i) == ch){

                while (!st.empty()){
                    ans.append(st.pop());
                }
                ++i;
                while (i < word.length()){
                    ans.append(word.charAt(i++));
                }

                return ans.toString();
            }

        }

//        time:  O2n - first until find ch push to stack, seond-from stack to ans.
//        space: O1n - for the stack (do not count sb as it output)

        //if got here - didnt find ch return original.
        return word;
    }
}