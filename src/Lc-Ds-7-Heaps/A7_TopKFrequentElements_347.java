import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*problem:
* Given an integer array nums and an integer k,
* return the k most frequent elements. It is guaranteed that the answer is unique.*/

/*motivation:
* could easily solve with sort! we want to decrease time complexity even more so use heap.
use: sort according to freq, but save just actual element. */

/*solutions:
1st - use min-heap, so time complexity is O (n log k).
2nd - as 1st, use max-heap, increase space complexity, but more readable - if struggling start here.
2nd - my first fail attempt - use heap to saves occurrences and not actual elements. good to learn from. */

public class A7_TopKFrequentElements_347 {
    public static void main(String[] args) {
        Solution_A7_TopKFrequentElements_347 obj_347 = new Solution_A7_TopKFrequentElements_347();
        // Example 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] result1 = obj_347.topKFrequent(nums1, k1);
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + Arrays.toString(result1));

        // Example 2
        int[] nums2 = {1};
        int k2 = 1;
        int[] result2 = obj_347.topKFrequent(nums2, k2);
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + Arrays.toString(result2));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* 1st - hashmap count freq of each element.
* 2nd - build min heap, "heap-sort" prioritize according to freq, but saves only actual element values.
* 3rd - traverse min-heap - and until it size==k - remove all elements. (so stay only with high freq elements)
* 4th - convert heap to int[] as signature. */
class Solution_A7_TopKFrequentElements_347 {
    public int[] topKFrequent(int[] nums, int k) {
//        initialize & populate count hashmap <num, count freq>
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

//        min heap, "heap-sort" prioritize according to freq, but saves only actual element values. (more detail below):
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
//       both same - could write above code (with lambda), with comparator:
//       PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (int num: map.keySet()) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }

//        convert from heap to int[] formant - as signature.
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.remove();
        }
//        time: O n log k: O1n-hashmap. 2*log k - add and remove from pq. Ok-traverse to int[]
//        space: On+k - n-size of hashmap + k-heap.
        return ans;
    }

/*     detail explain - about "heapify" input - according to freq:
       min-heap "prioritized less frequent element first" (so they'll pop first and we stay with
       big number of occurrence - which good for this specific problem):
       If the result of "map.get(n1) - map.get(n2)" is negative, it means that n1 has a lower frequency than n2.
       If the result is positive, it means that n1 has a higher frequency than n2.
       in my words: if we got positive number - prioritize the second - as it got less number of
       occurrences - more natural in min-heap.
       if we got negative number - prioritize the first - as it got less occurrences.
*/

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*psudo:
* same as 1st, here use max-heap so increase space complexity.
* but easier to read for start. */
    public int[] topKFrequent_max_heap(int[] nums, int k) {
//        initialize & populate count hashmap <num, count freq>
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n2) - map.get(n1));//max-heap
        for (int num: map.keySet()) {
            heap.add(num);
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.remove();
        }
//        time: O n log k: O1n-hashmap. log k - add to heap. Ok-traverse to int[]
//        space: O2n - size of hashmap + heap.
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    my first fail attempt - is nice try but bring back the number of occurrences and not the number itself.
    public int[] topKFrequent_my_first_fail_attempt(int[] nums, int k) {
        HashMap<Integer , Integer> map = new HashMap<>(); //<num, frequency>
        PriorityQueue<Integer>  pq = new PriorityQueue<>();
        int[] ans = new int[k];

//        initialize hashmap
        for (int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i : map.keySet()){
            pq.add(map.get(i));
            if (pq.size() > k){
                pq.remove();
            }
        }

        for (int i = 0; i < k ; i++) {
            ans[i] = pq.remove();
        }

        return ans;
    }
}