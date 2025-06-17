import java.util.*;
/*problem:
* we got sliding window of size k.
* return the max number of vowels in a single window */

/*2 solutions:
* 1st - my first success - not so elegant..
* 2nd - elegant. */

public class B7_MaxNumberOfVowelsInSubstringOfGivenLength_1456 {
    public static void main(String[] args)
    {
//        String s = new String("tnfazcwrryitgacaabwm");
        String s = new String("leetcode");
        int k = 3;

        int n = maxVowels(s, k);
        System.out.println(n);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    first try - work but bit messy
    public static int maxVowels_firsttry(String s, int k) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int cur = 0; //cur number of vowels in our window.
        int max = 0;
        int r = 0;
        int l = 0;

        if(k == 0) return 0;//edge case

//        creating first window:
        for ( ; r < k ; ++r){
            cur = vowels.contains(s.charAt(r)) ? ++cur : cur;
        }
        max = cur;

//        sliding the window:
        for ( ; r < s.length() ; ++r){
            cur = vowels.contains(s.charAt(r)) ? ++cur : cur;
            cur = vowels.contains(s.charAt(l++)) ? --cur : cur;

            if (vowels.contains(s.charAt(r))){
                max = Math.max(cur, max);
            }
        }

        //time: On-sliding window.
        // space: O1
        return max;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//  exactly the same - but cleaner.
    public static int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int cur = 0; //cur number of vowels in our window.
        int max = 0;
        int r = 0;
        int l = 0;

        if(k == 0) return 0;//edge case

//        creating first window:
        for ( ; r < k ; ++r){
            if(vowels.contains(s.charAt(r))){
                ++cur;
            }
        }
        max = cur;

//        sliding the window:
        for ( ; r < s.length() ; ++r, ++l){
            if (vowels.contains(s.charAt(r))){
                ++cur;
            }
            if (vowels.contains(s.charAt(l))){
                --cur;
            }
            max = Math.max(cur, max);
        }
        //time: On-sliding window.
        // space: O1
        return max;
    }
}