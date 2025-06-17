import java.util.Arrays;
import java.util.PriorityQueue;
/*problem:
* return true if can destroy all asteroids.
* start with given "mass" each asteroids in the way is either destroy you or add to given "mass". */

/*psudo:
* sort/heap asteroids from small to big, if at any given moment mass smaller than asteroid - return false.*/

/*solutions:
* 1st-with sort
* 2nd-with minheap */

public class A1_DestroyingAsteroids_2126 {
    public boolean asteroidsDestroyed_my_first_approach(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long curmass = mass; // to deal with annoying edge case.

        for (int i : asteroids){
            if (curmass < i){
                return false;
            } else {
                curmass += i;
            }
        }
//        time:  o 2n logn - O1n log n - sort. O1n-traverse list.
//        space: Ologn - java use varient of quicksort. python use TimSort up to n size.
        return true;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public boolean asteroidsDestroyed_my_second_approach(int mass, int[] asteroids) {
        PriorityQueue <Integer> minheap = new PriorityQueue<>();
        long curmass = mass; // to deal with annoying edge case.

        //populate minheap
        for (int i : asteroids){
            minheap.add(i);
        }

        while ( !minheap.isEmpty()){
            int currastroid = minheap.poll();

            if (curmass < currastroid){
                return false;
            } else {
                curmass += currastroid;
            }
        }
//      time:  O 2n log n.
//      space: O1n - minheap.
        return true;
    }
}