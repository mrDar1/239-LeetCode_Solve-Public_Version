import java.util.Comparator;
import java.util.PriorityQueue;
/*problem:
return the K largest elements in a stream that keep adding numbers to.
each time added new number - return the K largest number. */

/*signature:
class KthLargest {
    public KthLargest(int k, int[] nums) {  //constructor - receive given input.
    }
    public int add(int val) {               //each time to add new element - return K largest number.
    }
}*/
/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

/*solutions (both same idea):
* 1st - easier to read.
* 2nd - as 1st, optimize.*/


public class A11_KthLargestElementinaStream_703 {
    private int k;
    private PriorityQueue<Integer> heap; // min heap - size of k, each time the last value is Kth largest.

    public A11_KthLargestElementinaStream_703(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();

//        build heap
        for (int i: nums) {
            heap.offer(i);
        }
//        removing all smaller elements - what stays in heap are Kth biggest numbers.
        while (heap.size() > k) {
            heap.poll();
        }
    }

//    enter to the heap new val, then remove smallest - so what stays in heap are Kth biggest numbers.
    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
//              n== nums.size
//              m== number of time called to add.
//        time: O(N log N + M log k) - first N log N - for initialize constructor - log n -each add, log k - each remove.
//              each call to add is 2log k - offer and poll..
//        space: On - since first ionization (only after its limited to size of k)
        return heap.peek();
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A11_KthLargestElementinaStream_703_optimize {
    private int k;
    private PriorityQueue<Integer> heap; // min heap - size of k, each time the last value is Kth largest.

    public A11_KthLargestElementinaStream_703_optimize(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();

//        build heap
        for (int i : nums) {
            heap.offer(i);
            while (heap.size() > k) {
                heap.poll();
            }
        }
    }

    //    enter to the heap new val, then remove smallest - so what stays in heap are Kth biggest numbers.
    public int add ( int val){
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
//              n== nums.size
//              m== number of time called to add.
//        time: O(N log N + M log k) - first N log N - for initialize constructor - log n -each add, log k - each remove.
//              each call to add is 2log k - offer and poll..
//        space: Ok - size of heap limited to k.
        return heap.peek();
    }
}
