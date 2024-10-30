import java.util.PriorityQueue;
/*problem:
* return max num of apples, in single basket that can hold up to 5,000 weight */

/*motivation:
* greedily choose lowest basket first. */

/*solutions:
* 1st - max heap
* 2nd - optimized min heap */

public class A8_HowManyApplesCanYouPutintotheBasket_1196 {
    public int maxNumberOfApples(int[] weight) {
        PriorityQueue <Integer> heap = new PriorityQueue<>((a,b) -> a - b); //max-heap
        int apples = 0;
        int units = 0;

        for (int i : weight){
            heap.add(i);
        }

        while (!heap.isEmpty() && units + heap.peek() <= 5000) {
            units += heap.remove();
            apples += 1;
        }
        return apples;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*psudo:
 * "heap-sort" weight[], whenever build the heap, also count weight.
 * until stop overweight 5,000, remove "fatest" baskets */
    public int maxNumberOfApples_optimized(int[] weight) {
        PriorityQueue <Integer> pq = new PriorityQueue<>((a,b) -> b - a); //max-heap
        int sum = 0;

        for (int i : weight){
            pq.add(i);
            sum += i;
        }

        while ( !pq.isEmpty() && sum > 5000){
            sum -= pq.poll();
        }

//        time: O nlogn - O1n logn build pq. O nlogn - remoove items from pq.
//        space: O1n
        return pq.size();
    }
}
