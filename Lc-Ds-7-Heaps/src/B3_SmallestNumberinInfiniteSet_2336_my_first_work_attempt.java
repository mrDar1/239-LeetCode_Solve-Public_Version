import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
/*problem:
give set which contains all positive integers [1, 2, 3, 4, 5, ....]
constraint - pay good attention to duplicate values!

  class SmallestInfiniteSet {
    public SmallestInfiniteSet() { // Initializes the SmallestInfiniteSet object to contain all positive integers.
    }
    public int popSmallest() {     //Removes and returns the smallest integer contained in the infinite set.
    }
    public void addBack(int num) { //Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
    }}*/
/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */

/* solution (as in B2, 2nd):
 1st - use Heap+seen hashset to avoid duplicates */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* ain't gonna initialize all numbers! so its going to be like B2 - 2nd solution.
* as before - use marker from 1++,
* min-heap - contain only numbers that addback after remove.
* pop smallest - first we want to use the "addback" - as they allways smaller than "marker", if heap empty - use marker.
* how to handle of duplicates when "addBack" - use seen set.*/

public class B3_SmallestNumberinInfiniteSet_2336_my_first_work_attempt {
    int marker; // ptr to next smallest index to pop.
    PriorityQueue<Integer> heap; // contain only "addBack" elements.
    Set<Integer> set; //against duplicate - avoid "addBack" values that already did.
    public B3_SmallestNumberinInfiniteSet_2336_my_first_work_attempt() {
        this.heap = new PriorityQueue<>();
        set = new HashSet<>();
        marker = 1; //why 1 and not 0? constraint, start from 1.
    }

    public int popSmallest() {//first we want to advance from marker, but if got elements in heap-who's addBack - we take them first, as they always be smaller than "marker".
        if (this.heap.isEmpty()) {
            return marker++;
        } else {
            int ans = this.heap.poll();;
            set.remove(ans);
            return ans;
        }
    }

    public void addBack(int num) {
        if ( !this.set.contains(num) && num < marker){
            this.heap.offer(num);
            this.set.add(num);
        }
    }
}

//      k==number of "addBack".
//    time:
//    i think: k*2*(log k)  initialize: O1 , for k calls: addBack-Olog k to add heap, popSmallest-O1/log k at worst case(!heap.empty).
//    LC says: O((m+n)*log n) - popSmallest for m calles each time logn == m*log n, addBack n log n - for n calls each time log n inside.
//    space: Olog k - heap for k element that "addBack".
