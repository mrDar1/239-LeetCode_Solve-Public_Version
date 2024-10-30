import java.util.*;
/*problem:
* Return the length of the longest good subarray.
* good subarray: freq of each element <= k */
public class B9_LengthofLongestSubarrayWithatMostKFrequency_2958 {
    public static void main(String[] args) {
        // Test cases
        int[] nums1 = {1, 2, 3, 1, 2, 3, 1, 2};
        int k1 = 2;
        System.out.println(maxSubarrayLength(nums1, k1)); // Output: 6

        int[] nums2 = {1, 2, 1, 2, 1, 2, 1, 2};
        int k2 = 1;
        System.out.println(maxSubarrayLength(nums2, k2)); // Output: 2

        int[] nums3 = {5, 5, 5, 5, 5, 5, 5};
        int k3 = 4;
        System.out.println(maxSubarrayLength(nums3, k3)); // Output: 4
    }

/*psudo:
use sliding window with hashmap:
each add to hashmap freq nums[r].
while freq of nums[r] > k then
take down the freq of nums[l], until smaller than k.
each iteration check max. */
    public static int maxSubarrayLength(int[] nums, int k) {
        int l = 0;
        int maxlen = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // <element, freq>

        for (int r = 0; r < nums.length; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);

            while (map.get(nums[r]) > k){
                map.put(nums[l], map.getOrDefault(nums[l], 0) - 1);
                ++l;
            }
            maxlen = Math.max(maxlen, r - l + 1);
        }
//        time:  01n - sliding window.
//        space: O1n - hash
        return maxlen;
    }
}