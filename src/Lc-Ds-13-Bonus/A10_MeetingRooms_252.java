import java.util.Arrays;
/*problem:
* return true if one can attend all meetings.
* int[][] intervals == <start time (include), end time (not include)> */

/*solution:
* 1st - bruteforce for practice
* 2nd - much better using intervals */


/*intuition:
 * check every pair of meeting - if overlap - return false. */
class A10_MeetingRooms_252_brute_force {
    public boolean canAttendMeetings(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if ( !valid(intervals, i , j)){
                    return false;
                }
            }
        }
//        time:  On^2
//        space: O1
        return true;
    }

    /*intuition:
     * if the Max of start is bigger than Min of end - its not valid!!!!
     * start cur meeting before finish last meeting */
    private boolean valid(int[][] intervals, int i, int j) {
        if (    Math.max(intervals[i][0], intervals[j][0]) <
                Math.min(intervals[i][1], intervals[j][1]) ){
            return false;
        }
        return true;
    }
}

/* **************************** */
/* **************************** */
/* **************************** */
/* **************************** */

/*motivation:
after sort - if there are overlap - must be of adjacent meetings. so check only them. */

public class A10_MeetingRooms_252 {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0])); //sort meeting-start-time from small to big.

        for (int i = 1; i < intervals.length ; i++) {
            if (intervals[i][0] < intervals[i-1][1]){ //if cur start time smaller than last-end-time
                return false;
            }
        }
//        time: O n log n + n: sort, n-iterate sorted intervals
//        space:
//        in python, TimSort is a hybrid of MergeSort and InsertionSort and requires On space.
//        In Java, variant of the Quick Sort algorithm. space: O logn.
        return true;
    }
}