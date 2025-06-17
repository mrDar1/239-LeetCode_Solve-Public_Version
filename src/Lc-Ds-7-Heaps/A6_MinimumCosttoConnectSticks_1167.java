import java.util.PriorityQueue;
/*problem:
* given sticks[] - return the min cost to connect all sticks until remain only one */

/*motivation:
* there are stick that would used multiple times - so best if these was the smallest*/

/*psudo:
* create min-heap. and populate with sticks[].
* until heap.size==1, each time remove 2 smallest elements, combine and return to heap. */

public class A6_MinimumCosttoConnectSticks_1167 {
    public int connectSticks(int[] sticks) {
        int cost = 0; //constraint - return min cost.
        PriorityQueue<Integer> minheap = new PriorityQueue<>();

        for (int i:sticks){//populate heap.
            minheap.add(i);
        }

        while (minheap.size() > 1){
            int first  = minheap.remove();
            int second = minheap.remove();

            minheap.add(first + second);
            cost += first + second;
        }

//        time: O1n + nlog n== On*(logn):  O1n-build head. (O1n)* 3*(log n) remove, remove and add * done for each element in heap.
//        space: O1n - heap
        return cost;
    }
}