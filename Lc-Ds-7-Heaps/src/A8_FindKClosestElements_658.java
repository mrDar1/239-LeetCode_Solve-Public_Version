import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
/*problem:
* Given a sorted integer array arr, two integers k and x, return the k closest integers to x.
* The answer should also be sorted in ascending order. If there are ties, take the smaller elements.*/

/*motivation - as input sorted can use it to binary search (would be faster than here ans).
 here practice heaps - and our answer will work also in non-sort input. */

/*solutions:
* 1st - use max-heap
* 2nd - as 1st, simpler to read but higher complexity, start here if struggling */

/*psudo:
* 1st - max-heap - "heap-sort" according to distance from x, but saves just the elements.
*       prioritize the bigger distances elements - so can easily pop them and remain with elements who close to x.
* 2nd - traverse int[] and insert to heap.
* 3rd - when size of heap > k - remove the most far elements.
* 4th - convert to ArrayList as in signature
* 5th - sort - constraint. */
public class A8_FindKClosestElements_658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {//x==target, k==number of close neighbors
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> {
            if (Math.abs(n1 - x) == Math.abs(n2 - x)) { //constraint: if both number equal distances:
                //save smaller of them (so here prioritize bigger so can easily pop it and remain with smaller).
                return n2 - n1;
            }
            return Math.abs(n2 - x) - Math.abs(n1 - x); //==in the sort, put bigger distances first.
        });

        for (int num: arr) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(heap.remove());
        }

        Collections.sort(ans);

//        time: O(n+k)*log k :  n*2*log k - heap add&remove, Ok log k - sort.
//        space: Ok - heap
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A8_FindKClosestElements_658_MinHeap {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {//x==target, k==number of close neighbors
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> {
            if (Math.abs(n1 - x) == Math.abs(n2 - x)) {
                return n1 - n2;
            }
            return Math.abs(n1 - x) - Math.abs(n2 - x);
        });

        for (int num: arr) {
            heap.add(num);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(heap.remove());
        }

        Collections.sort(ans);

//        time: Onlogn:  n*log n -populate heap, Ok-convert to List, Ok log k - sort.
//        space: On - heap
        return ans;
    }
}