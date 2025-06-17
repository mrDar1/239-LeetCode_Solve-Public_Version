import java.util.Stack;
/*problem:
* return number of valid subarray.
* valid: the leftmost smaller than all other elements. */
public class B10_Number_of_Valid_Subarrays_1063 {
    public static void main(String[] args) {
        Solution_B10_Number_of_Valid_Subarrays_1063 obj_1063 = new Solution_B10_Number_of_Valid_Subarrays_1063();
        // Test cases
        int[] nums1 = {1, 4, 2, 5, 3};
        int[] nums2 = {3, 2, 1};
        int[] nums3 = {2, 2, 2};

        // Expected output
        int expected1 = 11;
        int expected2 = 3;
        int expected3 = 6;

        // Test cases
        int result1 = obj_1063.validSubarrays(nums1);
        int result2 = obj_1063.validSubarrays(nums2);
        int result3 = obj_1063.validSubarrays(nums3);

        // Check if the results match the expected output
        System.out.println("Test Case 1: " + (result1 == expected1));
        System.out.println("Test Case 2: " + (result2 == expected2));
        System.out.println("Test Case 3: " + (result3 == expected3));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_B10_Number_of_Valid_Subarrays_1063{
    public int validSubarrays(int[] nums) {
        int ans = 0;

        Stack<Integer> st = new Stack<>(); //index of elements.
        for (int i = 0; i < nums.length; i++) {
            // Keep popping elements from the stack
            // until the current element becomes greater than the top element.
            while(!st.isEmpty() && nums[i] < nums[st.peek()]) {
                // The diff between the current index and the stack top would be the subarray size.
                // Which is equal to the number of subarrays.
                ans += (i - st.peek());
                st.pop();
            }
            st.push(i);
        }

        // For all remaining elements, the last element will be considered as the right endpoint.
        while (!st.isEmpty()) {
            ans += (nums.length - st.peek());
            st.pop();
        }

//        time: O2n : first push to stack than pop.
//        space: O1n - stack.
        return ans;
    }
}