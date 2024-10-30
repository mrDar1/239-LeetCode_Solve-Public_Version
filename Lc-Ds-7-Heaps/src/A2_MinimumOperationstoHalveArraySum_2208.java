import java.util.Comparator;
import java.util.PriorityQueue;
/*probelm:
* given int[], return min number of operation to reduce its sum by at least half.
* each operation we can choose any element from int[] - and reduce it by half.*/

/*motivation:
* each time reduce by half the biggest element*/

/*solutions:
* 1st-with max-heap
* 2nd-same, less elegant.*/

public class A2_MinimumOperationstoHalveArraySum_2208 {
    public int halveArray(int[] nums) {
        double target = 0; //sum of nums[] reduce by half - when reach it can stop.
        int ans = 0;

//        in java, by default all heaps are min heap. convert heap into Max-heap, 2 ways:
//        PriorityQueue<Double> heap = new PriorityQueue<>((a,b) -> b - a); // heap work with Double and lambda comparator with int.
        PriorityQueue<Double> heap = new PriorityQueue<>(Comparator.reverseOrder()); // slower run time...

        for (double num: nums) {//build max-heap:
            target += num;
            heap.add(num);
        }

        target /= 2;
        while (target > 0) {
            double biggest_element = heap.remove();
            ans++; //count another operation.
            target -= (biggest_element / 2);
            heap.add(biggest_element / 2); //we cannot edit inside heap - so 3 stages: first pop, reduce, insert back reduced element.
        }
//        time: On log n: O1n-create heap and sum(doing logn, n times).
//                        2*(log n) - take out number doing O1 on it and return in log n.
//        space: O1n - hipify copy of nums[].
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int halveArray_myfirsttime(int[] nums) {
        PriorityQueue<Double> heap = new PriorityQueue<>(Comparator.reverseOrder());
        double arrsum = 0;
        double heapsum = 0;
        int c = 0; //count how many divisions made.

        for (int i : nums){ //buid heap + calculate sum
            arrsum += i;
            heap.add((double)i);
        }
        heapsum = arrsum;

        while (heapsum > arrsum/2){
            double x = heap.remove();
            x /= 2;
            ++c;

            heapsum -= x;
            heap.add(x);
        }
//        time: On log n: O1n-create heap and sum(doing logn, n times).
//                        2*(log n) - take out number doing O1 on it and return in log n.
//        space: O1n - hipify copy of nums[].
        return c;
    }
}