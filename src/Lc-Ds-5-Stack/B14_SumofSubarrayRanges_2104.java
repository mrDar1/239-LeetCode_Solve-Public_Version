/*problem:
* return sum off all sub-array ranges.
* sub-array ranges == largest minus smallest elements at sub-array. */

import java.util.Stack;

public class B14_SumofSubarrayRanges_2104 {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long answer = 0;

        for (int left = 0; left < n; ++left) {
            int minVal = nums[left];
            int maxVal = nums[left];

            for (int right = left; right < n; ++right) {
                minVal = Math.min(minVal, nums[right]);
                maxVal = Math.max(maxVal, nums[right]);
                answer += maxVal - minVal;
            }
        }
//        time: On^2 - nested loops (second one is size of n-left)
//        space:O1 - 3 vars
        return answer;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class B14_SumofSubarrayRanges_2104_mono_stack {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        long answer = 0;
        Stack<Integer> stack = new Stack<>();

        // Find the sum of all the minimum.
        for (int right = 0; right <= n; ++right) {
            while (!stack.isEmpty() && (right == n || nums[stack.peek()] >= nums[right])) {
                int mid = stack.peek();
                stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                answer -= (long)nums[mid] * (right - mid) * (mid - left);
            }
            stack.add(right);
        }

        // Find the sum of all the maximum.
        stack.clear();
        for (int right = 0; right <= n; ++right) {
            while (!stack.isEmpty() && (right == n || nums[stack.peek()] <= nums[right])) {
                int mid = stack.peek();
                stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                answer += (long)nums[mid] * (right - mid) * (mid - left);
            }
            stack.add(right);
        }
//        time: On
//        space:On
        return answer;
    }
}