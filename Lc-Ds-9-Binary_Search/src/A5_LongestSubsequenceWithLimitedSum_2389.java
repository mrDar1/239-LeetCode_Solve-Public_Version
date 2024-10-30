import java.util.Arrays;
/*problem:
* for each "query", return what is the longest subsequence can be made from "nums[]" that sum <= query*/

public class A5_LongestSubsequenceWithLimitedSum_2389 {
    public static void main(String[] args) {
        Solution_A5_LongestSubsequenceWithLimitedSum_2389 obj_2389 = new Solution_A5_LongestSubsequenceWithLimitedSum_2389();

        int[] nums1 = {4, 5, 2, 1};
        int[] queries1 = {3, 10, 21};
        int[] result1 = obj_2389.answerQueries(nums1, queries1);
        System.out.println("the ans is: " + Arrays.toString(result1)); // Output: [2, 3, 4]

        int[] nums2 = {2, 3, 4, 5};
        int[] queries2 = {1};
        int[] result2 = obj_2389.answerQueries(nums2, queries2);
        System.out.println("the ans is: " + Arrays.toString(result2)); // Output: [0]
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A5_LongestSubsequenceWithLimitedSum_2389 {
    /*motivation:
    * first, sort "nums[]" increasingly, create prefix sum for it.
    * motivation: when traverse prefix - its lowest sum possible - since sorted that way.
    * each time search for the */

    /*psudo:
    * 1-sort nums[].
    * 2-create prefix sum.
    * each time send to function Binarysearch prefix[] and target (as in queries)
    * add to ans[] the index of the found value in prefix[] */
    public int[] answerQueries(int[] nums, int[] queries) {
        int[] ans = new int[queries.length];
        Arrays.sort(nums);

        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }
////        debug - checking prefix sum work - and it did.
//        System.out.println(Arrays.toString(nums));
//        System.out.println(Arrays.toString(prefix));

        for (int i = 0; i < queries.length; i++) {
            int target = queries[i]; //target is to find sum on longest subsequence in "nums", that <= target.
            ans[i] = myBinarySearch(target, prefix) + 1; //why +1? since prefix-sum is 0-indexed, return ans in 1-indexed.
        }
//        time: O(n+m)(log n) : On log n - sort, O1n - prefix sum, O1m-traverse inside each: O log n - binary search.
//        space: On + log n:  On since use prfix[], log n-java sort.
        return ans;
    }

    private int myBinarySearch(int target, int[] prefix) {
        int l = 0;
        int r = prefix.length - 1;

        while (l < r){
            int mid = l + (r - l)/2;

            if (prefix[mid] == target){
                return mid;
            }

            if (prefix[mid] > target){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return prefix[l] > target ? l - 1 : l;
    }
}