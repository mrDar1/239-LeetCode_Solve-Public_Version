import java.util.Comparator;
import java.util.PriorityQueue;
/*problem:
* return min sum of piles.
* you can choose element and reduce by half - k times
* //note: try to confuse at problem description - each time round up not down. */

/*intuition:
* build max-heap
* each time take the most upper element and reduce it by half - k times.
* calculate sum with updated vals*/

/*solutions:
* both the same, 2nd got faster way to work with "Math.ceil()". */

public class A5_RemoveStonestoMinimizetheTotal_1962 {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue <Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());//for max heap.
        int sum = 0;//of stones

        for (int i : piles){ //build max-heap
            heap.add(i);
        }

        for (int i = 0; i < k; i++) {
            int cur = heap.remove();
            cur = (int)(Math.ceil((double) cur / 2 ));
            heap.add(cur);
        }

        for (int i : heap){
            sum += i;
        }
    //        time: O2n+logn: O1n- initialize heap. O1n+2*(log n) - heap remove and add. 3-sum.
    //        space: On - to store heap.
        return sum;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A5_RemoveStonestoMinimizetheTotal_1962_another_method_rounding {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());//for max heap.
        int sum = 0;//of stones

        for (int i : piles) { //build max-heap
            heap.add(i);
        }

        for (int i = 0; i < k; i++) {
            int cur = heap.remove();
            int remove = cur / 2;
            heap.add(cur - remove); //wil get "Math.ceil()" at faster way.
        }

        for (int i : heap) {
            sum += i;
        }
        //        time: O2n+logn: O1n- initialize heap. O1n+2*(log n) - heap remove and add. 3-sum.
//        space: On - to store heap.
        return sum;
    }
}