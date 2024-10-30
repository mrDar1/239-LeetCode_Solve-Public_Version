import java.util.HashMap;
import java.util.Map;

/*problem:
* return number of continuous "nice-sub-array"
* "nice-sub-array"== exactly k odd numbers */

//again use prefix sum hashmap.
public class A11_CountNumberOfNiceSubarray_1248 {
    public static void main(String[] args){
        int[] arr = {2,2,2,1,2,2,1,2,2,2};
        int k = 2;
        System.out.println(numberOfSubarrays(arr, k));
    }


    public static int numberOfSubarrays(int[] nums, int k) {
/*psudo:
first use prefixsum subarray counts only odd numbers.
add the count of odd numbers to our map.
add to ans each time we have curr count - k.*/

        int curr = 0; // current sum of prefix hashmap - for odd element.
        int ans = 0;
        Map<Integer, Integer> counts = new HashMap<>();

        counts.put(0, 1); //for prefix sum - must have it here!

        for(int i : nums){
            curr += i % 2; //if its non-odd number will be +0
            ans += counts.getOrDefault(curr - k, 0);
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
        }
        //time and space: O1n
        return ans;
    }
}