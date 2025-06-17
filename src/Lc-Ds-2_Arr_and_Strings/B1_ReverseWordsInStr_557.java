/* 3 solutions:
1st my intutive way - traverse String, each time find space - append from "spaceIndex" backward.
2nd 2 ptrs swap - using java StringBuilder,
3rd 2 ptrs swap - using like in C charAarray - most efficency  */

 /*notes for using Java (start from C, Java got it weirdness..):
 1st: in java, we have 2 ways to convert to string watch it (at last line, 2nd and 3rd ways use different casting method).
 2nd: int 3rd solution, how come the Swap of char[] work?
 in java primitive datatypes moove by val and not by ref, so sholdnt work!
 however its work because if its char[] its moove by ref! even if its primitive data type! */

import java.util.Arrays;

public class B1_ReverseWordsInStr_557 {
    public static void main(String[] args) {
        String s = new String("God Ding");
        System.out.println(reverseWords(s));
        System.out.println(reverseWords_with_Swap(s));
        System.out.println(reverseWords_using_charArray(s));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static String reverseWords(String s) {
        /*psudo:
        * traverse String s:
        * each time locate " " or find that we at end - save that index and start traverse from it backward.*/
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int lastspaceindex = -1;
        int wei = 0; //word end index

        for( ; i < s.length() ; ++i ){
            if(s.charAt(i) == ' ' || i == s.length() - 1 ){
                wei = i == s.length() - 1 ? i : i - 1; //for end of the sentence.

                for ( ; wei > lastspaceindex ; --wei){
                    sb.append(s.charAt(wei));
                }

                if(i != s.length() - 1){ //append ' ' when it's not the last word.
                    sb.append(" ");
                }

                lastspaceindex = i;
            }
        }
//        time: O2n: 1th: find end of current word. 2nd: reverse the word.
//        space: O1 - do not take in count output of n.
        return sb.toString();
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    2nd way:
    public static void Swap(StringBuilder sb, int starti, int endi) {
        char t = sb.charAt(starti);
        sb.setCharAt(starti, sb.charAt(endi));
        sb.setCharAt(endi, t);
    }
    public static String reverseWords_with_Swap(String s){
        int len = s.length();
        int starti = 0; //curr word start index
        int endi = 0; //curr word end index
        int i = 0;
        int lastspaceindex = -1;
        StringBuilder sb = new StringBuilder(s);

        for( ; i < len ; ++i){
            if(s.charAt(i) == ' ' || i == len - 1){
                starti = lastspaceindex + 1;
                endi = i == len - 1 ? i : i -1; // are we at end of String or just at space?

                for( ; starti < endi ; --endi, ++starti){
                    Swap(sb, starti, endi);
                }
                lastspaceindex = i;
            }
        }
//        time: O2.5 - 1st: StringBuilder 2nd: traverse and search spaces, 0.5-reverse each word.
//        space: O1 (not include output str)
        return sb.toString();
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    3rd way:
    public static void Swap(char[] arr, int starti, int endi) {
        char t = arr[starti];
        arr[starti] = arr[endi];
        arr[endi] = t;
    }
    public static String reverseWords_using_charArray(String s) {
        int i = 0;
        int lastspaceindex = -1;
        int starti = 0; //curr word start index
        int endi = 0; //curr word end index
        int len = s.length();
        char[] arr = s.toCharArray();

        for( ; i < len ; ++i){
            if(s.charAt(i) == ' ' || i == len - 1){
                starti = lastspaceindex + 1;
                endi = i == len - 1 ? i : i - 1;

                for( ;starti < endi ; ++starti, --endi){
                    Swap(arr, starti, endi);
                }
                lastspaceindex = i;
            }
        }
//      time: O1.5n-traverse and search spaces, 0.5-reverse each word.
//      space: O1 (not include output str)
        return new String(arr);// - is like doing "return Arrays.toString(arr);" in diffrent way.
    }
}
