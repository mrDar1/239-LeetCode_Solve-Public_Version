import java.util.ArrayList;
import java.util.List;
/*problem:
* given "int[][] intervals" <start time, end time>, not overlap and sorted by the start time.
* given interval to enter "newInterval[]" <start time, end time>.
* return "intervals" after add "newInterval" in a way that properties undamaged, E.G not overlap and sorted.
* can minimize(=merge) as much overlap pairs as needed */

/*solutions:
* 1st-merge intervals
* 2nd-find location to add "newInterval" (by use binary search-min-threshold), then merge all overlap.
* 3rd-same as 2nd, faster - nice trick of dynamic sized array with "new int[0]"*/

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*pseudo:
remember constraint: "intervals" sorted and not-overlap.

1. find location 1 before "newInterval" insert place, done by:
    traverse until cur-end-time bigger than "newInterval" start time.
    some neat trick: advance index to points not 1 before, but as the over lap (will adress it as "cur"), note: cur is not added to result, just point to it.
2. if cur does overlap with "newInterval" - merge them.
    note: can be several overlap in a row so the "while" important.
    example: given: (1,2) (3,4) (5,6) , to insert (2,6) - several merging needed.
    how merge is done? edit "newInterval" min of both start-time, and max of both end-time. then add "newInterval".
3. no matter if at 2nd part was overlap (merge them to not over-lap), or if simply "newInterval" not overlap.
      do same thing: simple copy each element until end of "intervals".
4. cast to int[][] - signature constraint */
public class A12_InsertInterval_57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        int len = intervals.length;
        List<int[]> ans = new ArrayList<>();

//        until reach location to insert "newInterval", simple copy.
        while (i < len && intervals[i][1] < newInterval[0] ){
            ans.add(intervals[i]);
            ++i;
        }

//        if there is overlap fix it,
        while (i < len && (intervals[i][0] <= newInterval[1] )) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            ++i;
        }
        ans.add( newInterval );


//        at this point no more overlap, copy till end
        while (i < len ){
            ans.add(intervals[i]);
            ++i;
        }
//        time: O1n - traverse each exactly once!
//        space: O1 - no additional space use (do not count output).
        return ans.toArray(new int[ans.size()][]);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition: insert "newInterval", merge overlap interval */

/*pseudo:
* 1st - find right location to insert "newInterval":
*   that where "newInterval" start-point smaller than cur, to find it use:
*   binarySearch: "framework of min threshold point"(=formula number #5)
*
* 2nd - insert "newInterval" - probably cause overlap.
* 3rd - traverse again, after insertion, if there are overlap - merge.
* 4th - cast list to int[][] - signature constraint. */
class A12_InsertInterval_57_binary_search {
    public int[][] insert(int[][] intervals, int[] newInterval) {
//        edge case:
        if (intervals.length == 0) {
            return new int[][] { newInterval };
        }

        int n = intervals.length;
        int left = 0;
        int right = n - 1;
        int target = newInterval[0];

        // Binary search to find the position to insert newInterval
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // insert "newInterval" at right position from binarySearch.
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < left; i++) {
            ans.add(intervals[i]);
        }
        ans.add(newInterval);
        for (int i = left; i < n; i++) {
            ans.add(intervals[i]);
        }


        // merge overlapping intervals:
        for (int i = 0; i < ans.size() ; i++) {
            //cur can never overlap with last - if there is no last (never relevant for first interval)
            if (i == 0){
                continue;
            }


            // has overlap - if "curStartTime" smaller than "lastEndTime".
            if ( ans.get(i)[0] <= ans.get(i-1)[1] ){

                //initialize only here and not before, for time-complexity saving reasons.
                int curStartTime  = ans.get(i)[0];
                int curEndTime    = ans.get(i)[1];
                int lastStartTime = ans.get(i-1)[0];
                int lastEndTime   = ans.get(i-1)[1];

                int newStartTime = Math.min(curStartTime, lastStartTime);
                int newEndTime = Math.max(curEndTime, lastEndTime);

                ans.set(i - 1, new int[] { newStartTime, newEndTime });
                ans.remove(i);
                i--; // adjust index after removed element
            }
        }

        return ans.toArray(new int[ans.size()][]);

//        above line could replaced by - which make it size dynamicly adjusting,
//        according to ans.size() avoids the need to manually resize arrays.
//        return ans.toArray(new int[0][]);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
* same as 2nd, instead */
class A12_InsertInterval_57_binary_search_2 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
//        edge case:
        if (intervals.length == 0) {
            return new int[][] { newInterval };
        }

        int n = intervals.length;
        int left = 0;
        int right = n - 1;
        int target = newInterval[0];

        // Binary search to find the position to insert newInterval
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // insert "newInterval" at right position from binarySearch.
        List<int[]> intervals_with_newInterval = new ArrayList<>();
        for (int i = 0; i < left; i++) {
            intervals_with_newInterval.add(intervals[i]);
        }
        intervals_with_newInterval.add(newInterval);
        for (int i = left; i < n; i++) {
            intervals_with_newInterval.add(intervals[i]);
        }


        // merge overlapping intervals
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals_with_newInterval) {
            // If res is empty or there is no overlap, simple add.
            if ( merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0] ) {
                merged.add(interval);
            }
            // if there is an overlap,
            // merge intervals by updating the end of the last interval in res
            else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1],interval[1] );
            }
        }

        return merged.toArray(new int[0][]);

//        above line could replaced by - which make it fix-size,
//        with the need to manually resize arrays.
//        return merged.toArray(new int[merged.size()][]);
    }
}