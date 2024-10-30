/*problem:
Given an array of positive integers nums and an integer k,
find the length of the longest subarray whose sum is less than or equal to k. */

/*solution: classic Sliding Window FrameWork */
public class A7_Example1 {
    public int findLength(int[] nums, int k) {
        int left = 0;
        int curr = 0; // curr is the current sum of the window
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            curr += nums[right];

            while (curr > k) {
                curr -= nums[left];
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
//    time:  O1n-since inner loop it amortized.
//    spcae: O1 (only 3 var use)
}
