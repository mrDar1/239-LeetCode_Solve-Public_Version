import java.util.Stack;
// 3 solutions:
// 1st my first intuitive way with swap - not so elegant...
// 2nd 2ptrs - with stringbuilder       -- like it most!
// 3rd with stack - also very nice!

public class B2_ReverseOnlyLetters_917 {
    public static void main(String[] args){
//        String s = new String("?6C40E]");
//        String s = new String("7_28]"); // shold be "7_28]"
        String s = new String("Test1ng-Leet=code-Q");
        //    Input: s = "Test1ng-Leet=code-Q!"
        //  Output:      "Qedo1ct-eeLg=ntse-T!"
        System.out.println("the original string:");
        System.out.println(s);
//        System.out.println("solved in 3 different ways:");
        System.out.println("1st way: " + reverseOnlyLetters1(s));
        System.out.println("2nd way: " + reverseOnlyLetters2(s));
        System.out.println("3rd way: " + reverseOnlyLetters3(s));
    }

    public static void Swap(StringBuilder sb, int starti, int endi) {
        char t = sb.charAt(starti);
        sb.setCharAt(starti, sb.charAt(endi));
        sb.setCharAt(endi, t);
    }
//    my first way - swap:
    public static String reverseOnlyLetters1(String s) {
        /*psudo: create mutable string SB copy of s.
        * traverse with l and r ptrs.
        * each time both letters - swap. if not letter - skip. */
        int l = 0;
        int r = s.length() - 1 ;
        StringBuilder sb = new StringBuilder(s);

        while(l < r){
            while ( !Character.isLetter(sb.charAt(l)) &&
                    l < s.length() - 1 &&
                    l < r){
                ++l;

                if(l == s.length() - 1) {
                    return s;
                }
            }
            while ( !Character.isLetter(sb.charAt(r)) &&
                    r > 0  &&
                    l < r){
                --r;

                if(r == 0){
                    return s;
                }
            }

            Swap(sb, r, l);
            ++l;
            --r;
        }
//        time: O2n - first to traverse and then to swap-if not doing swap still move the ptr backward.
//        space: O1
        return  sb.toString();
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static String reverseOnlyLetters2(String s) {
        /*psudo:
        create empty SB.
        traverse s with left ptr,
        if the s[l] is not letter - copy it, if it does letter - then copy from s[r--]
        if at s[r] we got not-letter then skip it, we reach it with the left ptr. */

        int l = 0;
        int r = s.length() - 1 ;
        StringBuilder sb = new StringBuilder();

        for( ; l < s.length() ; ++l) {
            if(Character.isLetter(s.charAt(l)) ){
                while( ! Character.isLetter(s.charAt(r))){
                    --r;
                }
                sb.append(s.charAt(r));
                --r;
            }else {
                sb.append(s.charAt(l));
            }
        }
//        time: O1n - 1st for sliding window.
//        space: On - sb.
        return  sb.toString();
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static String reverseOnlyLetters3(String s) {
        /*psudo:
        * create stack and traverse String s, each time we got letter - push.
        * we again traverse s, if is letter-pop from stack what give reverse order.
        * if not-letter copy from original s */
        StringBuilder sb = new StringBuilder();
        Stack<Character> letters = new Stack<>();

//        building stack only for letters- poping them will reverse the stack.
        for (char c : s.toCharArray()){
            if(Character.isLetter(c)){
                letters.push(c);
            }
        }

        for(char c : s.toCharArray()){
            if(Character.isLetter(c)){
                sb.append(letters.pop());
            }else{
                sb.append(c);
            }
        }
//        time: O2n - 1st - building the stack. 2nd-reverse.
//        space: O1n - stack.
        return sb.toString();
    }
}
