import java.util.Map;
import java.util.TreeMap;
/*problem:
* trips[][] == <numPassengers, from, to>
* capacity == empty seats
* return true/false if possible to take all passengers when car do not turning back! */

/* solutions:
* 1st-sort input (with TreeMap) and traverse number of passenger according to timestamp (=location on road).
* 2nd-bucket sort - bucket represent the timestamp(=location on road) after sort, traverse buckets, search for over-people.
* 3rd-Difference array (improved space) */

// trips[][] == <numPassengers, from, to>
class A1_CarPooling_1094_sort_TreeMap {
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> timestamp = new TreeMap<>(); //tree map to sort the input. <what km ar we ,num of people>

        for (int[] curTrip : trips) {
            int people  = curTrip[0];
            int from    = curTrip[1];
            int to      = curTrip[2];

            timestamp.put(from, timestamp.getOrDefault(from, 0) + people);
            timestamp.put(to,   timestamp.getOrDefault(to,   0) - people);
        }

        int curNumOfPeople = 0;
        for (int peoplePerPart : timestamp.values()) { //note: TreeMap sort keys. so here the "timestamp.values()" that represented "curNumOfPeople" - will show in the order of the road from start to end at sorted manner.
            curNumOfPeople += peoplePerPart;
            // why its "+="? -since need to count the people who were at the car before and the new people.
            // so where do we remove the people who left? every "to" hold negative number of people.
            if (curNumOfPeople > capacity) {
                return false;
            }
        }

//        time: O(N log N) + O(2N) = O(N log N):
//                On - traverse trip[][] to build TreeMap.
//                O(n log n) - sorting costs (done by TreeMap)
//                On - traverse TreeMap all points of collect/remove passenger.
//
//        space: O(n) TreeMap. (at worst edge case - each single passenger will have his unique "from" and "to" points which will be N)
        return true;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A1_CarPooling_1094_bucket_sort {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] timestamp = new int[1001]; //why 1001? -constraint "to" <= 1000

        for (int[] curTrip : trips) {
            int people  = curTrip[0];
            int from    = curTrip[1];
            int to      = curTrip[2];

            timestamp[from] += people;
            timestamp[to]   -= people;
        }

        int curNumOfPeople = 0;
        for (int peoplePerPart : timestamp) {
            curNumOfPeople += peoplePerPart;
            // why its "+="? -since need to count the people who were at the car before and the new people.
            // so where do we remove the people who left? every "to" hold negative number of people.

            if (curNumOfPeople > capacity) {
                return false;
            }
        }
//        n==1001, can consider O1 but will address as O1n.

//        time: O2n == On:
//        1n-initialize 1001 buckets to zero (do not count)
//        1n-update 1001 buckets with number of people.
//        1n-traverse 1001 buckets - check for over-people.
//        space: O1n - 1001 buckets.
        return true;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

public class A1_CarPooling_1094 {
/*intuition:
traverse "trips" and find the farthest "timeStamp" - here its the farest place car will reach.
traverse "trips" again, if number of people increase capacity - return false. */
    public boolean carPooling(int[][] trips, int capacity) {

        int farthestPoint = 0;
        for (int[] curTrip : trips){
            farthestPoint = Math.max(farthestPoint, curTrip[2]);
        }

        int[] peoplePerPart = new int[farthestPoint + 1];

        for (int[] curTrip : trips){
            int people  = curTrip[0];
            int from    = curTrip[1];
            int to      = curTrip[2];

            peoplePerPart[from] += people;
            peoplePerPart[to]   -= people;
        }

        int curNumOfPeople = 0;
        for (int timeStamp : peoplePerPart){
            curNumOfPeople += timeStamp;
            // why its "+="? -since need to count the people who were at the car before and the new people.
            // so where do we remove the people who left? every "to" hold negative number of people.

            if (curNumOfPeople > capacity) {
                return false;
            }
        }
//        time: O2n: 1n-find farthest part, 1n-traverse and update prefix[] of "peoplePerPart".
//        space: Ok - prefix. k==furthest point we get.
        return true;
    }
}