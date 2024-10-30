import java.util.*;
/*problem:
as "A3_FindMedianfromDataStream_295" - this time return median from sliding window.
receive given int[], and k size of the window.
//for the first k-1 elements (where the window still not complete) - do not put anything! */

/*solutions:
1st-using knowledge from A3 - work, but take too long time. On^2 time.
2nd-GPT solution, also fail for big inputs..*/

public class A4_SlidingWindowMedian_480 {
    public static void main(String[] args) {
        Solution_A4_SlidingWindowMedian_480 obj_480 = new Solution_A4_SlidingWindowMedian_480();

        System.out.print("Output for nums1: ");
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
//        Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
        int k1 = 3;
        double[] result1 = obj_480.medianSlidingWindow(nums1, k1);
        System.out.println(Arrays.toString(result1));


        System.out.print("Output for nums2: ");
        int[] nums2 = {1, 2, 3, 4, 2, 3, 1, 4, 2};
//        Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
        int k2 = 3;
        double[] result2 = obj_480.medianSlidingWindow(nums2, k2);
        System.out.println(Arrays.toString(result2));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
* use 2 heaps to find median (same techniqe as "A3_FindMedianfromDataStream_295")
* for each window - create and populate new heaps */
class Solution_A4_SlidingWindowMedian_480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] arr = new double[nums.length - k + 1];// at end - cast List to double[] as signature required.
        List<Double> ans = new ArrayList<>();//easier to work on List.

//        edge case:
        if (nums == null || nums.length < k || k <= 0) return new double[0];

//      each "slide" - calculate the window from start:
        for (int i = 0; i < nums.length - k + 1; ++i) {
            PriorityQueue<Integer> min = new PriorityQueue<>(); //how come heaps are int and signature ans is double? -since, given input as int, only when calculate avg need double.
            PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);
            int i_copy = i;

            for (int j = 0; j < k; ++j) {
                add_to_pq(max, min, nums[i_copy++]);
            }
            addmedian_toans(max, min, ans);
        }

//       at end - cast List to double[] as in signature.
        for (int i = 0; i < ans.size(); ++i) {
            arr[i] = ans.get(i);
        }
        return arr;
    }

    private void add_to_pq(PriorityQueue<Integer> max, PriorityQueue<Integer> min, int num) {
        max.add(num);
        min.add(max.remove());

        if (min.size() > max.size()) {
            max.add(min.remove());
        }
    }

    private void addmedian_toans(PriorityQueue<Integer> max, PriorityQueue<Integer> min, List<Double> ans) {
        if (max.size() > min.size()) {
            ans.add((double) max.peek());
        }
        else{
            long temp = (long)max.peek() + (long)min.peek(); //cast to long because of stupid testing of MAX_Integer
            ans.add(temp / 2.0);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A4_SlidingWindowMedian_480_gpt_also_fails {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.offer(nums[i]);
            } else {
                minHeap.offer(nums[i]);
            }

            if (i >= k) {
                if (nums[i - k] <= maxHeap.peek()) {
                    maxHeap.remove(nums[i - k]);
                } else {
                    minHeap.remove(nums[i - k]);
                }
            }

            balanceHeaps(maxHeap, minHeap);

            if (i >= k - 1) {
                result[i - k + 1] = getMedian(maxHeap, minHeap);
            }
        }

        return result;
    }
    private void balanceHeaps(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
    }
    private double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (maxHeap.size() == minHeap.size()) {
            long temp = (long)maxHeap.peek() + (long)minHeap.peek(); //cast to long because of stupid testing of MAX_Integer
            return (temp / 2.0);
        } else {
            return maxHeap.peek();
        }
    }
}