import java.util.Comparator;
import java.util.PriorityQueue;
/*problem:
* return the weight of last stone, if arent - return 0.
*given stones[],
* each time smash 2 heaviest stones:
* 1 x==y both destroyed.
* 2 x!=y the light one destroyed, and the heavy new weight is minus the light one */

public class A1_LastStoneWeight_1046 {
    public int lastStoneWeight(int[] stones) {
//        in java, by default all heaps are min heap,
//        convert heap into Max-heap, 2 ways:
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> b - a);
//        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder()); // slower run time...

//        build heap Data Structure:
        for (int i : stones){
            heap.add(i);
        }

        while (heap.size() > 1){
            int first  = heap.remove();
            int second = heap.remove();

            if (first != second){
                heap.add(first - second);
            }
        }
        //time: O n log n: On-to create heap (we do not sort O(nlogn), we doing N times (log n) to heapify given array stones[].
        //                 3(log n) - 2 pops and (possible) 1 insert of the smashing stones.
        // space:O1n- heapify copy of given array stones[].
        return heap.isEmpty() ? 0 : heap.remove();
    }
}
