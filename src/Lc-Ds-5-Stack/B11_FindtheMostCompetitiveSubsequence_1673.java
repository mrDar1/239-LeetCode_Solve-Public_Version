import java.util.LinkedList;
import java.util.Stack;
/*problem:
* Given an integer array nums and an integer k,
* the goal is to find the most competitive subsequence of length k.
* A subsequence is competitive if it is lexicographically smallest possible.*/

public class B11_FindtheMostCompetitiveSubsequence_1673 {
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> st = new Stack<>();
        int additionalCount = nums.length - k;

        for (int i = 0; i < nums.length; i++) {
            while (!st.isEmpty() && st.peek() > nums[i] && additionalCount > 0) {
                st.pop();
                additionalCount--;
            }
            st.push(nums[i]);
        }

        // Ensure the stack size is reduced to k
        while (st.size() > k) {
            st.pop();
        }

//        cast stack to int[] as result
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = st.pop();
        }
//        time: O2n: O1n push, O1n pop
//        space:O1n:stack (not count output)
        return result;
    }
}
