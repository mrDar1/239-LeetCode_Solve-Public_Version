import java.util.Arrays;
import java.util.PriorityQueue;

/*problem:
* return max number of units.
* units == number of boxes * number of units inside each box.
* trucksize == limit the number of boxes. but note inside each box can be change number of units. */

/*motivation: prioritize first boxes with bigger number of units.*/

/* solutions:
* 1st - my first intutive - using max-heap
* 2nd - 1st, optimize - spared inner-loop */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
* prioritize with MaxHeap the boxes with max units.
* traverse "boxTypes[][]" - each time fill truck with 1 box - prioritize max-units boxes first.
* until truck is full or no more boxes what comes first.*/
public class A7_MaximumUnitsonTruck_1710 {
    public int maximumUnits_my_first_time_solve(int[][] boxTypes, int truckSize) {
        //input boxTypes: int [][] =      <n>   [numberOfBoxesi, numberOfUnitsPerBoxi]:
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]); //max-heap - by number of units!
        int ans = 0;
        int curNumBoxes = 0;

        for (int[] arr : boxTypes){
            pq.offer(arr);
        }
//        above line could be replace with:
//        pq.addAll(Arrays.asList(boxTypes));

        while ( !pq.isEmpty() && curNumBoxes < truckSize){

            int[] node = pq.poll();

            while (node[0] > 0 && curNumBoxes < truckSize){
                ans += node[1];
                curNumBoxes++;
                node[0]--;
            }
        }
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int maximumUnits_optimized(int[][] boxTypes, int truckSize) {
        //input boxTypes: int [][] =      <n>   [numberOfBoxesi, numberOfUnitsPerBoxi]:
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]); //max-heap - by number of units!
        int ans = 0;

        for (int[] arr : boxTypes){
            pq.offer(arr);
        }
//        above line could be replace with:
//        pq.addAll(Arrays.asList(boxTypes));

        while ( !pq.isEmpty() ) {
            int[] node = pq.poll();

            int boxes_toAdd = Math.min(truckSize, node[0]);

            truckSize -= boxes_toAdd;
            ans += boxes_toAdd * node[1];

            if(truckSize == 0) //optimization
                break;
        }
//        time: O n log n - build pq and poll from it.
//        space: O1n - pq.
        return ans;
    }
}