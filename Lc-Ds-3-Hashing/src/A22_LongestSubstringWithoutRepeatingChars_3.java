import java.util.HashMap;
import java.util.Map;
import java.util.*;
/*problem: find the len of the longest substring without repeating chars*/

/*4 solutions:
1st - my second solution use Hashset and sliding window - elegant solution.
2nd - my first solution - using hashmap and sliding window - less elegant solution.
another lc solution - for study:
3rd - optimized 1. (jumping left boundary at each duplicate) - less elegant see next!!
3.5 - optimized 1. (jumping left boundary at each duplicate) - second implement use it much more readable!!
4th - using arr - if we know the input is small (like 128 for ASCII) - we can use arr, that will give same O - but much faster that hashmap*/

public class A22_LongestSubstringWithoutRepeatingChars_3 {
    public static void main(String[] args){
        String s = ("pwwkew");
        System.out.println(lengthOfLongestSubstring1(s));
//        System.out.println(lengthOfLongestSubstring2(s));
//        System.out.println(lengthOfLongestSubstring3(s));
    }

    /*psudo: traverse s and store at hashset:
    * while contain - remove left ptr
    *  then, check max length with last high result*/
    public static int lengthOfLongestSubstring1(String s) {
        int max = 0;
        int l = 0; //left
        HashSet<Character> isunique = new HashSet<>();

        for (int r = 0; r < s.length(); ++r) {
            while (isunique.contains(s.charAt(r))){
                isunique.remove(s.charAt(l));
                ++l;
            }
            isunique.add(s.charAt(r));

            max = Math.max(max, r - l + 1);
        }
//        time: O1n - sliding window
//        space: O1n
        return max;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int lengthOfLongestSubstring2(String s){
        int max = 0;
        int l = 0;
        int r = 0;
        Map<Character,Integer> map = new HashMap<>(); //<char , number of occurrences>

        for( ; r < s.length() ; ++r){
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);

            while(map.get(s.charAt(r)) > 1){
                map.put(s.charAt(l), map.getOrDefault(s.charAt(l), 0) - 1);
                ++l;
            }
            max = Math.max(max, r - l + 1);
        }

        return max;
        //time: O2n - 1st for right boundary of window, 2nd - for left boundary of window.
        // space: O(min(n,k)) - will be the upper bound of the smallest n/k:
        // n-size of input string,
        // k-size of alphabet/charset - if it was english letters 26 possibilities so O1
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int lengthOfLongestSubstring3(String s){
        int ans = 0;
        int r = 0;
        int l = 0;
        Map<Character, Integer> map = new HashMap<>(); // <char, index>

        // try to extend the range of window[r, l]
        for ( ; r < s.length(); r++) {
            if (map.containsKey(s.charAt(r))) {
                l = Math.max(map.get(s.charAt(r)), l); //if we have duplicate - we jum to next
                                                        // point after first occurrence.
                                                        // must compare with l - so we wont jump backward!!

            }

            ans = Math.max(ans, r - l + 1);
            map.put(s.charAt(r), r + 1);
        }
        return ans;
//        time: O1n
//        space: O(min(n,k))
        // n-size of input string,
        // k-size of alphabet/charset - if it was english letters 26 possibilities so O1
    }

    public int lengthOfLongestSubstring3_other_way(String s) {
        int l=0;
        int len=0;
        Map<Character, Integer> map= new HashMap<>(); // <char, index>

        // abba
        for(int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);

            if (map.containsKey(c)) {
                if (map.get(c) >= l)
                    l = map.get(c) + 1;
            }
            len = Math.max(len, r - l + 1);
            map.put(c, r);
        }

        return len;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int lengthOfLongestSubstring4(String s) {
        int left = 0;
        int right = 0;
        int res = 0;
        Integer[] chars = new Integer[128];

        while (right < s.length())
        {
            char r = s.charAt(right);

            Integer index = chars[r];
            if (index != null && index >= left && index < right) {
                left = index + 1;
            }

            res = Math.max(res, right - left + 1);

            chars[r] = right;
            right++;
        }

        return res;
    }
}