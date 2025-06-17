/*problem:
* Given an array of positive integers nums and an integer k,
* find the length of the longest subarray whose sum is less than or equal to k */

/*solution - classic sliding window FrameWork. (just like Example1 - but here multiply not sum) */
public class A9_SubarrayProductLessThanK_713_Example3 {
    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 6};
        int k = 100;
        int n = numSubarrayProductLessThanK(arr, k);
        System.out.println("the answer is: " + n);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; //edge case

        int l = 0;
        int r = 0;
        int cursum = 1; //must be 1 for multiply: "cursum *= nums[r];"
        int ans = 0;

        for (; r < nums.length; ++r) {
            cursum *= nums[r];
            while (cursum >= k) {
                cursum /= nums[l];
                ++l;
            }
            ans += r - l + 1;
        }
        return ans;
        //time: On
        // space: O1
    }
}