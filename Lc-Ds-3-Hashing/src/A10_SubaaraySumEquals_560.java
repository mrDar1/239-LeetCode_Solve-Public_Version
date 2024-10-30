import java.util.HashMap;
import java.util.Map;
/*problem:
* return how many sub-array that sum exactly k.*/

/*intuition:
* use neat-trick math formula:
* use prefix-sum with hashmap - if prefix_sum[i]-k == 0 - then its valid! the number of times we got that freq
* note-won't work at 2 passes! since order matter! */

public class A10_SubaaraySumEquals_560 {
    public static void main(String[] args){
        int[] arr = {1,2,3};
        int k = 3;
        int n = subarraySum(arr, k);
        System.out.println(n);
    }

    public static int subarraySum(int[] nums, int k) {
/*psudo:
1-build prefix-sum-hashmap <curr_prefix_sum, freq>
2-traverse nums[] - if hashmap.contains(k-curr_prefix_sum) - then add number of its freq to ans! */
        int ans = 0;
        int curr = 0; // curr sum
        Map<Integer, Integer> counts = new HashMap<>(); // <curr_prefix_sum, freq>

        counts.put(0, 1);
//        must put 1 in the 0 key! why? for prefix sum to work!
//        becaues if we have exactly k we need to substract
//        it from our hashmap - so must have 0 in it.

//        building the prefix sum hashmap and search for solutions.
        for (int num : nums) {
            curr += num;
            ans += counts.getOrDefault(curr - k, 0); // how many different array we got so far that accumulate them lead to sum.
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
        }

        //time: O1n traverse only once.
        // space: On - hashing
        return ans;
    }
}