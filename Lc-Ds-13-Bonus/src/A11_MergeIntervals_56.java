import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

/*problem:
* zip all overlapping, if not overlap do not zip:
* exmaple: [1,4] [4,6] [11,12] -> [1,6] [11,12]
*
* mynote:
* similar to A10_MeetingRooms_252 - this time return arr not just bool value. */

/*motivation: since sorted, each time cur-start-time, bigger than last-end-time - merge.
* merge - done by edit last element to the bigger value: cur-end-time or last-end-time */

/*intuition:
 * sort intervals,
 * check if cur start time is bigger than last end time - if so merge couple.
 * at first round - no need to check as we do not have a before value.
 * will use LinkList DS - as we do not know the size of intervals, and easy to modify */
public class A11_MergeIntervals_56 {
    public int[][] merge(int[][] intervals) {

        LinkedList<int[]> ans = new LinkedList<>(); // <start time, end time>
        Arrays.sort(intervals, (a,b)-> (Integer.compare(a[0], b[0]))); // //sort by start-time from small to big.

        for (int[] interval : intervals){
            int curStartTime = interval[0];
            int curEndTime   = interval[1];

            //if ans empty - we are at first element and cannot zip it.
            if (!ans.isEmpty() && curStartTime <= ans.getLast()[1]){
                ans.getLast()[1] = Math.max(curEndTime, ans.getLast()[1]);
            }
            else{
                ans.add(interval);
            }
        }
//        time:  Onlogn: Onlogn-sort, O1n-traverse interval, O1n-convert LinkList to Array.
//        space:
//          in python, TimSort is a hybrid of MergeSort and InsertionSort and requires On space.
////        In Java, variant of the Quick Sort algorithm. space: O logn.
        return ans.toArray(new int[ans.size()][]); //convert from LinkList to int[] signature constraint.
    }
}