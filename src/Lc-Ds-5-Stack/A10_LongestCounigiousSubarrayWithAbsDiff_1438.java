import java.util.ArrayDeque;

/* problem:
 return len of longest sub-array.
 valid sub-array: |max-element - min-element| <= "limit" */

//hard!

public class A10_LongestCounigiousSubarrayWithAbsDiff_1438{
    public static void main(String[] args){
        int[] arr = {8,2,4,7};
        int limit = 4;

        int[] arr2 = {10,1,2,4,7,2};
        int limit2 = 5;

        int[] arr3 = {4,2,2,2,4,4,2,2};
        int limit3 = 0;

        Solution_LongestCounigiousSubarrayWithAbsDiff_1438 obj_1438 = new Solution_LongestCounigiousSubarrayWithAbsDiff_1438();

        System.out.println( obj_1438.longestSubarray(arr, limit) ); // desired output: 2
        System.out.println( obj_1438.longestSubarray(arr2, limit2) );// desired output: 4
        System.out.println( obj_1438.longestSubarray(arr3, limit3) );// desired output: 3
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_LongestCounigiousSubarrayWithAbsDiff_1438{
    /* psudo:
    * use 2 monotonic Dequeue, to hold max and min val of our current window.
    * slid the window and search for longest sub-array */
    public int longestSubarray(int[] nums, int limit) {
        int left = 0;
        int ans = 0;
        ArrayDeque<Integer> increasing = new ArrayDeque<>(); //hold number values! store min element in the window at the first index.
        ArrayDeque<Integer> decreasing = new ArrayDeque<>(); //hold number values! store max element in the window at the first index.

        for (int right = 0; right < nums.length; right++)
        {
            // maintain the monotonic deques
            while ( !increasing.isEmpty() && increasing.getLast() > nums[right] ) {
                increasing.removeLast();
            }
            while ( !decreasing.isEmpty() && decreasing.getLast() < nums[right] ) {
                decreasing.removeLast();
            }

            increasing.addLast(nums[right]);
            decreasing.addLast(nums[right]);

            // maintain window property
            while (decreasing.getFirst() - increasing.getFirst() > limit) { //constraint formula
                if (nums[left] == decreasing.getFirst()) {
                    decreasing.removeFirst();
                }
                if (nums[left] == increasing.getFirst()) {
                    increasing.removeFirst();
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        //time: O1n - sliding window.
        //space: On (at worst case Dequeue increase to size of n)
        return ans;
    }
}
