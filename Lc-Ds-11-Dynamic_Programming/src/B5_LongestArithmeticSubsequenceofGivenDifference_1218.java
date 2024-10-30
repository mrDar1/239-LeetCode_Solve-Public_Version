/*problem:
* return length of longest subsequence with given "difference", that all elements keep: "arr[i] - arr[i+1] == difference "*/

/*solutions:
 * 1st-brute force
 * 2nd-fail top down (when start with 0, ot at arr.len index - fail at oppose testing) - fail but nice try.
 * 3rd-bottom up - On space */

import java.util.HashMap;
public class B5_LongestArithmeticSubsequenceofGivenDifference_1218 {
    public static void main(String[] args) {
        B5_LongestArithmeticSubsequenceofGivenDifference_1218_brute_force obj_1218 = new B5_LongestArithmeticSubsequenceofGivenDifference_1218_brute_force();

        System.out.println(obj_1218.longestSubsequence(new int[]{1, 2, 3, 5, 7}, 2));       // Output: 4
//        System.out.println(obj_1218.longestSubsequence(new int[]{1, 2, 3, 4}, 1));       // Output: 4
//        System.out.println(obj_1218.longestSubsequence(new int[]{1, 3, 5, 7}, 1));       // Output: 1
//        System.out.println(obj_1218.longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2)); // Output: 4
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B5_LongestArithmeticSubsequenceofGivenDifference_1218_brute_force {
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;

        for (int i = 0 ; i < arr.length; i++) {
            int sum = 1; //constraint: 1 element == 1 length valid subsequence.
            int prev = arr[i];

            for (int j = i + 1 ; j < arr.length; j++) {
                if (arr[j] - prev == difference){
                    ++sum;
//                    System.out.println(prev); //debug check.
                    prev = arr[j]; //advance the window
                }
            }
            ans = Math.max(ans, sum);
        }
//        time: On^2
//        space: O1
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B5_LongestArithmeticSubsequenceofGivenDifference_1218top_down {
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> memo = new HashMap<>(); //< element, subsequence len that end with cur element >
        int ans = 1; //constraint: 1 element == 1 length valid subsequence.

        return dp(arr.length - 1, arr, difference, memo, ans);
    }

    private int dp(int i,
                   int[] arr,
                   int difference,
                   HashMap<Integer, Integer> memo,
                   int ans) {

        if (i == 0){
            return ans;
        }

        memo.put(i, memo.getOrDefault(i - difference, 0) + 1);
        ans  = Math.max(ans, memo.get(i));

        return Math.max(ans, dp(i-1, arr, difference, memo, ans) );
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*motivation:
* traverse from left to right:
* for each element - put value of cur_subsequence_len, that end with cur element.
* each time add the sum from last element to +1 of cur. */
class B5_LongestArithmeticSubsequenceofGivenDifference_1218_bottom_up {
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer,Integer> memo = new HashMap<>(); //< element, subsequence len that end with cur element >
        int ans = 1; //constraint: 1 element == 1 length valid subsequence.

        for (int i : arr){
            memo.put(i, memo.getOrDefault(i - difference, 0) + 1);
            ans  = Math.max(ans, memo.get(i));
        }
//        time:  O1n - traverse int[] once.
//        space: O1n - memo hashmap.
        return ans;
    }
}




