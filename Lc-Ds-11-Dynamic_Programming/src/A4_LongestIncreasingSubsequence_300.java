import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*problem:
* return the length of the longest strictly increasing subsequence (=LIS)
* subsequence == sub-array with deleted elements, without changing the order of the remaining elements. */

/*solutions:
1st - top down
2nd - bottom up
3rd - greedy binary search
4th - same as 3rd, simplify. read it if struggle to understand 3rd (instead binary search, use regular traversal) */

/*psudo:
 * use hashmap <end point index , LIS>
 * on first method:
 *   traverse all possible end point send to dp method to compare.
 * dp: from all possibilities that ends with i inedx, return biggest LIS.
 * base case - if got 1 element at this subsequence - its length == 1. */
public class A4_LongestIncreasingSubsequence_300 {
    HashMap<Integer, Integer> memo = new HashMap<>();// <end point index , LIS>
    int[] nums;

    public int lengthOfLIS(int[] nums) {
//        edge case:
        if (nums.length == 1) return 1; //1 element is as 1.

        int ans = Integer.MIN_VALUE;
        this.nums = nums;

        for (int i = 1; i < nums.length; i++) {
            ans = Math.max(ans, dp(i));  // Update ans with the maximum LIS ending at index i
        }

//        time: On^2: nested loop,  O1n-work on each state and there n states.
//        space: O1n - and cannot improved as recurrence relation is not static.
        return ans;
    }


    private int dp(int i) {

        if (memo.containsKey(i)){
            return memo.get(i);
        }

        int pathLen = 1; //cur path length, base case - if got only 1 element can think of it as increasing 1 element.

        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]){ // if expression valid-means does increase
                pathLen = Math.max(pathLen, dp(j) + 1);
            }
        }

        memo.put(i, pathLen);
        return pathLen;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A4_LongestIncreasingSubsequence_300_tabulating {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); //if got only 1 element can think of it as increasing sub-array with 1 element.
        int ans = 1;

//        i represent the last index, for each i check all optional start point (j) and save the highest.
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i ; j++) {
                if (nums[j] < nums[i]){// if the expression valid-means before element must be smaller.
                    dp[i] = Math.max(dp[i] , dp[j] + 1 );
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
//        time: On^2: nested loop,  O1n-work on each state and there n states.
//        space: O1n - and cannot improved as recurrence relation is not static.
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
* unlike last 2 methods, here i represent start index.
* try to build LIS that start at i, whenever next element bigger than cur - simply add it.
* but, whenever it small - greedily replace it.
* motivation: we wish to minimize the distance between 2 elements as possible, what probably produce bigger LIS.
*
* note: the binary search is the regular formula for non-duplicates elements.
* we know there are no duplicates since we build ourself the "curPath" and put only increasing elements with no duplicate allowed,
* thus, we can also binary search in it.*/
class A4_LongestIncreasingSubsequence_300_binary_Search {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> curPath = new ArrayList<>();
        curPath.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];

            if (cur > curPath.get(curPath.size() - 1)) { //if keep increasing - simply add to curPath.
                curPath.add(cur);
            } else {                                     //whenever cur element is smaller than last - greedily replace and put cur at right positions at curPath.
                int j = binarySearch(curPath, cur);
                curPath.set(j, cur);
            }
        }

        return curPath.size();
    }

    private int binarySearch(ArrayList<Integer> curPath, int num) {
        int left = 0;
        int right = curPath.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (curPath.get(mid) == num) {
                return mid;
            }

            if (curPath.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
//time:   O n log n : for each element at n, doing binary search (log n)
// space: O1n - at worst edge case, when each element does increase from last - will grow to size of nums[].

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A4_LongestIncreasingSubsequence_300_binary_Search_idea_but_easier_to_understand {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> curPath = new ArrayList<>();
        curPath.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];

            if (cur > curPath.get(curPath.size() - 1)) { //if keep increasing - simply add to curPath.
                curPath.add(cur);
            }

            else {
                int j = 0;
                while (cur > curPath.get(j)) { //whenever cur element is smaller than last - greedily replace and put cur at right positions at curPath.
                    j += 1;
                }

                curPath.set(j, cur);
            }
        }

        return curPath.size();
    }
}
//time:  On^2 - even its same as 1st and 2nd approach - for the average case and best case is faster!
//       O1n - traverse each nums[] - each time with different start point.
//          O1n - inside each loop, whenever encounter smaller element than cur - search right "sorted" place to put inside curPath
//          at worst case - first half of input increasing and second half are all decreasing will be On^2.
//          but for the average case much faster!
// space: O1n - at worst edge case, when each element does increase from last - will grow to size of nums[].