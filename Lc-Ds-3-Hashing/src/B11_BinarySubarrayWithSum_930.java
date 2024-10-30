import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*problem:
* Given binary array, return the number of contiguous subarray that sum=="goal".
* do not count empty sub array!
* do count all subaaray start with 0! */

/*solutions:
* 1st- hashmap for prefix sum
* 2nd- sliding window
* 3rd- sliding window optimized*/


public class B11_BinarySubarrayWithSum_930 {
    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 1};
        int k = 2;
        System.out.println(numSubarraysWithSum(arr, k));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int numSubarraysWithSum(int[] nums, int goal) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // <cumulative sum , freq >

        //as in all prefix sum. To account for valid sub arrays starting from index 0.
        map.put(0, 1);

        for (int num : nums) {
            sum += num;

            if (map.containsKey(sum - goal)) {
                count += map.get(sum - goal);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
//      time: O1n
//      space: O1n
        return count;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* use sliding window: however we get much more answer than we want!!
* if we want specific number lets say 2, at first we will get ans for subaaray that give us sum of 0,1,2!
* so we subtract those with same method again that remove for us 0,1 and we stay just with exactly 2!! */
class B11_BinarySubarrayWithSum_930_sliding_window {
    public int numSubarraysWithSum(int[] nums, int goal) { // start here
//        time: O2n - twice for each sliding window.
//        space: O1
        return slidingWindowAtMost(nums, goal) - slidingWindowAtMost(nums, goal - 1);
    }

    // Helper function to count the number of subarrays with sum at most the given goal
    private int slidingWindowAtMost(int[] nums, int goal) {
        int left = 0;
        int curWindowSum = 0;
        int totalSubarrayCount = 0;

        for (int right = 0; right < nums.length; right++) {
            curWindowSum += nums[right];

            while (left <= right && curWindowSum > goal) {
                curWindowSum -= nums[left++];
            }

            totalSubarrayCount += right - left + 1;
        }
        return totalSubarrayCount;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B11_BinarySubarrayWithSum_930_sliding_window_optimized {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int left = 0;
        int prefixZeros = 0;
        int curWindowSum = 0;
        int totalSubarrayCount = 0;

        for (int right = 0; right < nums.length; right++) {
            curWindowSum += nums[right];

            while (left < right && (nums[left] == 0 || curWindowSum > goal)) {
                if (nums[left] == 1) {
                    prefixZeros = 0;
                } else {
                    prefixZeros++;
                }

                curWindowSum -= nums[left];
                left++;
            }

            // Count subarrays when window sum matches the goal
            if (curWindowSum == goal) {
                totalSubarrayCount += 1 + prefixZeros;
            }
        }
//        time O1n
//        space: O1
        return totalSubarrayCount;
    }
}