import java.util.HashMap;
import java.util.PriorityQueue;
/*problem:
 * have int[] with a lot of duplicates
 * must shrink it by at least half!
 * each time can choose 1 int and remove all its occurrences!
 * return the min number of chosen numbers to remove - that still shrink arr by at least half.*/

/*psudo:
* create count freq hashmap.
* push freq to max-heap.
* traverse heap - each time remove from size all duplicates of that number and count number of times.*/
public class A9_ReduceArraySizetoTheHalf_1338 {
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();//count hashmap <value, number of occurrences>
        PriorityQueue <Integer> pq = new PriorityQueue<>((a,b) -> b - a); //max-heap
        int removal_count = 0; //=ans, wish to make it as small as possible.
        int size = 0;

        //populate count hashmap.
        for (int i : arr){
            map.put(i , map.getOrDefault(i, 0) + 1);
        }

        //populate max-heap only with number of occurrences.
        for (int i : map.values()){
            pq.offer(i);
            size += i;
        }

        //until reach half the size, reduce the number of occurrences and add to count.
        while ( !pq.isEmpty() && size > arr.length / 2){
            size -= pq.poll();
            removal_count++;
        }

//        time: O3n==On: O1n-freq map, O1n-build max-heap, each call takes O log n - done O1n times. O1n-removal from heap.
//        space:O2n==On: O1n-freq map, O1n-max-heap
        return removal_count;
    }
}