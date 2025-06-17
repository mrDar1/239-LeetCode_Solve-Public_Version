import java.util.PriorityQueue;
/*problem:
* return largest possible sum.
* can flip each number to negative/positive - exactly "k" times*/

/*motivation: first prioritize to flip the smallest numbers. */

public class B4_MaximizeSumOfArrayAfterKNegations_1005 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue <Integer> pq = new PriorityQueue<>(); //min-heap
        int sum = 0;

//        populate min heap:
        for (int i : nums){
            pq.offer(i);
        }

        while ( !pq.isEmpty() && k > 0 ){
            int cur = pq.poll();

            cur = -cur;
            --k;
            pq.offer(cur);
        }

        while ( ! pq.isEmpty()) {
            sum += pq.poll();
        }

    return sum;
    }
}
