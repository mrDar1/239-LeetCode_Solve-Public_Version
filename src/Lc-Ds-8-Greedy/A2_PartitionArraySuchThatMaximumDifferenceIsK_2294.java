import java.util.Arrays;
/*problem:
* return number of valid subsequence.
* valid subsequence: (max_element-min_element <= k)
* constraint: no overlapping! each window with different elements from the other. */
public class A2_PartitionArraySuchThatMaximumDifferenceIsK_2294 {
    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {3, 6, 1, 2, 5};
        int k1 = 2;
        System.out.println("Test case 1: " + (partitionArray(nums1, k1) == 2 ? "Passed" : "Failed"));

        // Test case 2
        int[] nums2 = {1, 2, 3};
        int k2 = 1;
        System.out.println("Test case 2: " + (partitionArray(nums2, k2) == 2 ? "Passed" : "Failed"));

        // Test case 3
        int[] nums3 = {2, 2, 4, 5};
        int k3 = 0;
        System.out.println("Test case 3: " + (partitionArray(nums3, k3) == 3 ? "Passed" : "Failed"));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int partitionArray(int[] nums, int k) {
        int ans = 1;
        Arrays.sort(nums);
        int left = nums[0]; // start of first group:

        for (int right = 1; right < nums.length; right++) {
//            if (Math.abs( left - nums[right]) > k){ ---- at first i used that - note no need as arr is sorted!
            if (nums[right] - left > k){
                ++ans;
                left = nums[right]; //why do we jump left to right and not increasing by 1? -constraint no overlapping.
            }
        }
//        time: O 2nlogn - for sort and traverse arr.
//        space: Ologn - java use varient of quicksort. python use TimSort up to n size.
        return ans;
    }
}