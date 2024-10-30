import java.util.Stack;
/*problem:
* finding the sum of the minimum values of every contiguous subarray of a given array arr.
* Since the sum can be very large, the result needs to be returned modulo 10^9+7. */

/*solutions:
* 1st - mono stack
* 2nd - mono stack + DP*/
public class B13_SumofSubarrayMinimums_907 {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;

        Stack<Integer> stack = new Stack<>();
        long sumOfMinimums = 0;

        // building monotonically increasing stack
        for (int i = 0; i <= arr.length; i++) {

            // when i reaches the array length, it is an indication that
            // all the elements have been processed, and the remaining
            // elements in the stack should now be popped out.

            while (!stack.empty() && (i == arr.length || arr[stack.peek()] >= arr[i])) {

                // Notice the sign ">=", This ensures that no contribution
                // is counted twice. rightBoundary takes equal or smaller
                // elements into account while leftBoundary takes only the
                // strictly smaller elements into account

                int mid = stack.pop();
                int leftBoundary = stack.empty() ? -1 : stack.peek();
                int rightBoundary = i;

                // count of subarrays where mid is the minimum element
                long count = (mid - leftBoundary) * (rightBoundary - mid) % MOD;

                sumOfMinimums += (count * arr[mid]) % MOD;
                sumOfMinimums %= MOD;
            }
            stack.push(i);
        }

        return (int) (sumOfMinimums);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B13_SumofSubarrayMinimums_907_with_DP {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;

        Stack<Integer> stack = new Stack<>();

        // make a dp array of the same size as the input array `arr`
        int[] dp = new int[arr.length];

        // making a monotonic increasing stack
        for (int i = 0; i < arr.length; i++) {
            // pop the stack until it is empty or
            // the top of the stack is greater than or equal to
            // the current element
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // either the previousSmaller element exists
            if (stack.size() > 0) {
                int previousSmaller = stack.peek();
                dp[i] = dp[previousSmaller] + (i - previousSmaller) * arr[i];
            } else {
                // or it doesn't exist, in this case the current element
                // contributes with all subarrays ending at i
                dp[i] = (i + 1) * arr[i];
            }
            // push the current index
            stack.push(i);
        }

        // Add all elements of the dp to get the answer
        long sumOfMinimums = 0;
        for (int count : dp) {
            sumOfMinimums += count;
            sumOfMinimums %= MOD;
        }

        return (int) sumOfMinimums;
    }
}
