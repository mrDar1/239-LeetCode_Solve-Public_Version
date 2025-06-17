import java.util.HashSet;
import java.util.HashMap;

/*problem:
* return the max sum of unique elements in a row (subarray)
* in other word: find freq of all elements.
* erase the biggest sum of subarray, with only unique elements */

/*solutions:
* 1st - using sliding window + set - my intuitive way
* 2nd - using hashmap of <element, index> and jump it to right position - nice way! of LC learn the cool jump. */
public class B12_MaxErasureValue_1695 {
    public static void main(String[] args) {
        int[] arr = {4, 2, 4, 5, 6};
        System.out.println(maximumUniqueSubarray2(arr));
        System.out.println(maximumUniqueSubarray5(arr));

//        System.out.println(maximumUniqueSubarray2(arr));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*psudo:
* using sliding window + set
* each time element inside window seen - shrink window.
* each time compare elements. */
//Approach 2: Two Pointer Approach Using Set
    public static int maximumUniqueSubarray2(int[] nums) {
        int left = 0;
        int currentSum = 0;
        int result = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int right = 0; right < nums.length; right++) {
            while (set.contains(nums[right])) {
                set.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }
            currentSum += nums[right];
            set.add(nums[right]);
            result = Math.max(result, currentSum);
        }
//        time: O2n
//        space: Om (M=number of unique elements - size of the sliding window)
        return result;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    Approach 5: Using Prefix Sum with HashMap
    /*intuition:
    build hashmap of <element, index>
    build prefix sum arr.

    traverse nums[] - each time find duplicate - jump to the next place we seen that element.
    compute sum using the prefix sum arr.
    */
    public static int maximumUniqueSubarray5(int[] nums) {
        int left = 0;
        int result = 0;
        HashMap<Integer, Integer> lastIndexMap = new HashMap<>(); // <element, index>
        int[] prefixSum = new int[nums.length + 1];

        for (int right = 0; right < nums.length; right++) {
            int currentElement = nums[right];

            prefixSum[right + 1] = prefixSum[right] + currentElement;

            if (lastIndexMap.containsKey(currentElement)) {
                left = Math.max(left, lastIndexMap.get(currentElement) + 1); // must compare with left so we wont jump backward!!!
            }
            // update result with maximum sum found so far
            result = Math.max(result, prefixSum[right + 1] - prefixSum[left]);
            lastIndexMap.put(currentElement, right);
        }
//        time: O1n
//        space: O2n-hashmap+index arr
        return result;
    }
}
