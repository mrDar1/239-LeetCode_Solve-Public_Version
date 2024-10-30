/*problem:
Example 1: You are given a string s and an integer k. Find the length of the
longest substring that contains at most k distinct characters.
For example, given s = "eceba" and k = 2, return 3.
The longest substring with at most 2 distinct characters is "ece".*/

/*problem in my words:
* return longest substring that has at most k unique chars (do not count duplicates, the more duplicates
* bigger our answer will be) */

import java.util.*;
public class A7_Example1_Counting_from_third_article {
    public static void main(String[] args){
        String s = new String("eceba");
        int k = 2;
        int n = findLongestSubstring(s, k);
        System.out.println(n);
    }


    public static int findLongestSubstring (String s,int k){
        /*psudo: use hashmap for sliding window.*/
        int left = 0;
        int ans = 0;
        Map<Character, Integer> distinctcount = new HashMap<>();

        for(int right = 0 ; right < s.length() ; ++right){
            distinctcount.put(s.charAt(right), distinctcount.getOrDefault(s.charAt(right), 0) + 1);

            while(distinctcount.size() > k){
                distinctcount.put(s.charAt(left), distinctcount.getOrDefault(s.charAt(left), 0) - 1);
                if (distinctcount.get(s.charAt(left)) == 0){
                    distinctcount.remove(s.charAt(left));
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        //time: O1n - sliding window
        // space: On/1 - hashmap (worst case - all char are distinctive char) / O1 - 26 letters in english.
        return ans;
    }
}