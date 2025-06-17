/*problem:
* each flower will bloom for several time int[][] <start,end>
* people[]-is when they come to see the flowers.
* return how many flower will see each person from people. */

/* solutions:
* 1: heapify flowers bloom time. for each person return heap.size()         -- nice
* 2: Hashmap and later convert to prefix sum - represent number of blooming flowers each day, Binary search in prefix.   -- didnt like.
* 3: Simpler Binary Search - for each person return the index in sorted array of start bloom minus end bloom -- amazing!  */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.*;

public class B8_NumberofFlowersinFullBloom_2251 {
/*intuition:
sort people by arrival time.
use min heap to sort all flowers by end-of-bloom time.
traverse people arrival time, for each time:
offer and poll from heap - according to the time of start and end of bloom.
to know how much flower saw each person use heap.size() */

/*psudo: Heap:
* 1- create copy of people and sort them by who come first.
* 2- sort flowers by the flowers who bloom first.
* 3- use min-heap - to remove each time flower finish blooming
* 4- use Hashmap - to store ans <person, number of flower will see (==size of heap)>
* 5- convert hashmap to int[] - as signature require.
* algorithm:
* 6- traverse our copy of sortedPeople, each time build the heap:
*    first flower[i][0] <= person - so flower will start bloom before person come.
*    second- add flower[][1] - heap only save when flower should pop out.
*
*   when finish buildig the heap for that person - we store at HashMap - the size of that pq and countinue to next person.*/
    public int[] fullBloomFlowers_firs_approach_heap(int[][] flowers, int[] people) {
        int[] sortedPeople = Arrays.copyOf(people, people.length); //must use copy. later use original for ans there must use order of original "people[]"
        Arrays.sort(sortedPeople);

//        sorts the flowers array based on the first element of each sub-array (=flower who bloom first, appear first):
        Arrays.sort(flowers, (int[] a, int[] b) -> Integer.compare(a[0], b[0]));
//          above line could be replace with any one of 2 option below:
//        Arrays.sort(flowers, (a, b) -> Arrays.compare(a, b)); // IntelliJ not allow it, but some compilers do.
//        Arrays.sort(flowers, Comparator.comparingInt(a -> a[0]));

        Map<Integer, Integer> dic = new HashMap<>(); // < index number person from people, number of flowers he saw blooming >
        PriorityQueue<Integer> heap = new PriorityQueue<>(); //min heap <flower end of bloom day>

        int i = 0;
        for (int person : sortedPeople) {
            while (i < flowers.length && flowers[i][0] <= person) { //add to heap all flowers who start bloom.
                heap.add(flowers[i][1]);
                i++;
            }
            while (!heap.isEmpty() && heap.peek() < person) { //remove from heap all flowers who done blooming.
                heap.remove();
            }

            dic.put(person, heap.size());
        }

        int[] ans = new int[people.length]; //the dic is not sorted, now sort it accorind to order of input "people".
        for (int j = 0; j < people.length; j++) {
            ans[j] = dic.get(people[j]);
        }
//        n==flowers.len*flowers[0].len
//        m==people.len
//        time: O nlogn + m*(log n + mlog m) : O n logn - O m log m - sorting both arr ; log m - traverse m * inside each O 2(log n)-add and remove from heap  ; m-conver to ans.
//        space: Om+1==O2m+1n: copyof_sorted_peopl==m, heap==n, hashmap==m
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*intuiion:
* use hashmap to count start and end time of blooming flowers each day.
* use prefix sum to fill how many flowers bloom each day.
* binary search prefix for each people[i] day, and see how many flowers bloom that day. */
    public int[] fullBloomFlowers_second_approach_BS(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> difference = new TreeMap<>(); // < specific day , number of blooming flowers >
        difference.put(0, 0);

//        populate "difference" hashmap:
//        note: have here "flaw", hashmap only count start and end time, so the middle is not fill! later use prefix sum to calculate number of bloom flower each day.
        for (int[] flower : flowers) {
            int start = flower[0];
            int end   = flower[1] + 1; // why + 1 ? constraint as its inclusive count. (example: flower that bloom end at 3rd day, bloom also in 3rd day).

            difference.put(start, difference.getOrDefault(start, 0) + 1);
            difference.put(end,   difference.getOrDefault(end,   0) - 1);
        }

        List<Integer> positions = new ArrayList<>(); //store days which have change in number blooming flowers.
        List<Integer> prefix    = new ArrayList<>(); //hashmap only fill the "boundary's", now prefix sum to know how many flowers bloom at each day.
        int curr = 0;

        for (int key : difference.keySet()) {
            positions.add(key);
            curr += difference.get(key);
            prefix.add(curr);
        }

        int[] ans = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            ans[i] = prefix.get(binarySerch(positions, people[i]) - 1); //search in "prefix", how many flowers bloom the day people[i] came to visit. IMPORTANT: if the day not on "positions" simply use the one before it, as it hasnt change!
        }

        return ans;
    }

    public int binarySerch(List<Integer> arr, int target) { //Binary Search
        int left = 0;
        int right = arr.size() - 1;

        while (left <=  right) {
            int mid = left + ((right - left) / 2);

            if (target < arr.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*intuition:
* use 2 arrList start and end bloom time,
* sort both increasingly.
* for each person[i] binary search for start and end Lists and simply return "ans[i] = i - j".
*
* binary search - how does it work?
* lets say people[i] is another flower we want to put in start and end Lists.
* so search for the index where one would put that flower - that index minus the end index of end bloom flower - is the
* number of flowers each people[i] saw. */

    public int[] fullBloomFlowers_3_appraoch_simpler_BS(int[][] flowers, int[] people) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends   = new ArrayList<>();

        for (int[] flower: flowers) {
            starts.add(flower[0]);
            ends.add(flower[1] + 1); // why + 1 ? constraint as its inclusive count. (example: flower that bloom end at 3rd day, bloom also in 3rd day).
        }

        Collections.sort(starts);
        Collections.sort(ends);

        int[] ans = new int[people.length];

        for (int index = 0; index < people.length; index++) {
            int person = people[index];
            int i = binarySearch(starts, person);
            int j = binarySearch(ends, person);
            ans[index] = i - j;
        }
//        n==flowers.len*flowers[0].len
//        m==people.len
//        time: O(n+m)*(log n)  : On-build auxilary arr,  Onlon - sort start and finish, 1m*2 logn - find for each person.
//        space: On - build 2 arr copy of flowers[][].
        return ans;
    }
    public int binarySearch(List<Integer> arr, int target) { //note its not regular BS! its BS for when has duplicates value - find the right-most index!
        int left = 0;
        int right = arr.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (target < arr.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}