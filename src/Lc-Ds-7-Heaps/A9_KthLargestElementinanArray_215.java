import java.util.Comparator;
import java.util.PriorityQueue;
/*problem:
* given unsorted int[], and int k.
* return the k largest element.*/

/*solutions:
* 1st-max heap - readable, less good in complexity
* 2nd-same, with min-heap, better complexity.*/

/*psudo:
* create max-heap and insert all.
* remove k-1 elements from it.
* return last.*/
public class A9_KthLargestElementinanArray_215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); //for max heap.

        for (int i : nums){
            pq.add(i);
        }

        for (int i = 0; i < k - 1; i++) {
            pq.remove();
        }

//        time:On log k: O1n-build pq, k*Olog k-remove from q. O1-peek
//        space: O1n - heap.
        return pq.peek();
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int findKthLargest_2_time(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();//min heap
        for (int num: nums) {
            heap.add(num);
            if (heap.size() > k) { //pop smallest elements - as they can never be ans.
                heap.remove();
            }
        }
//      time: O n log k: n log k-populate heap + log k - each remove from heap when K-size exceeded.
//        space: Ok - heap limit so k.
        return heap.peek();
    }
}