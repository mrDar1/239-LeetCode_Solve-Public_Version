/*problem:
* Given an integer array "nums", find the subarray with the largest sum, and return its sum. */

/*solutions
* 1st - Kadane's Algorithm
* 2nd - Divide and Conquer*/

//1st - Kadane's Algorithm:
public class A4_MaximumSubarray_53 {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int cursum = nums[0]; //cur sum

        for (int i = 1; i < nums.length; i++) {
            cursum = Math.max(cursum + nums[i] , nums[i]);
            ans = Math.max(ans , cursum);
        }

        return ans;
    }
    //time: O1n.
    //space: O1.
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd - Divide and Conquer approach (similar to merge sort):
/* intuition:
Base Case: when have only one element - return it as max element.
Divide: recursively split array to left and right halves.
Conquer: recursively find the max subarray sum for the left and right halves.
Combine: find the max subarray sum that crosses the midpoint,
    by finding the max sum subarray that starts from the middle of the array and extends to the left and right. */

class A4_MaximumSubarray_53_DaC {
    public int maxSubArray(int[] nums) {
        return maxSubArraySum(nums, 0, nums.length - 1);
    }

    private int maxSubArraySum(int[] nums, int left, int right) {
        // base case: only one element - return it.
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        int leftMax  = maxSubArraySum(nums, left, mid);
        int rightMax = maxSubArraySum(nums, mid + 1, right);

        // find the max subarray sum that crosses the midpoint
        int crossMax = maxCrossingSum(nums, left, mid, right);

        // return the maximum of 3: right, left, mid/subarray sum that crosses the midpoint
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // sum elements on the left of mid
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }

        // sum elements on the right of mid
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        return leftSum + rightSum;
    }
    //time: O n log n.
}