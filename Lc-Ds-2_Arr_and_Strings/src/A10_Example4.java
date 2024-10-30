/*problem:
*  Given an integer array nums and an integer k,
* find the sum of the subarray with the largest sum whose length is k. */

/*solution:
* fix-size sliding window (easier than regular sliding window)*/
public class A10_Example4 {
    public int findBestSubarray(int[] nums, int k) {
        int curr = 0;
        int ans = 0;

        //initialize sum for first fix-size-window
        for (int i = 0; i < k; i++) {
            curr += nums[i];
        }

        ans = curr;

        //each time advance window by 1 and update sum.
        for (int i = k; i < nums.length; i++) {
            curr += nums[i] - nums[i - k];
            ans = Math.max(ans, curr);
        }

        return ans;
    }
}
//    time:  O1n-since inner loop it amortized.
//    spcae: O1 (only 2 var use)