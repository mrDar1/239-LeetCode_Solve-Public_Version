import java.util.Arrays;
/*problem:
* return min number of clips needed to cover whole from [0,"time"], if not possible return -1.*/

/*solutions:
*  1-i like!! by letsdoitthistime from soluton - no sorting so easier to grasp.
*  2-same GPT try to optimized (dont know if good)
*  3-math stuff didnt fully understand - may be more effince */

/*motivation: start from 0, each time we meet video that extends our end point add it to video counter.*/
public class B14_VideoStitching_1024 {
    /*psudo:
    * 1-run on all the 2d arr.
    * each time start with the last time we stop.
    * each time update max only if got continuous way from last time stop. */
    public int videoStitching(int[][] clips, int time) {
        int startPoint = 0;
        int maxPointSofar = 0;
        int video_count = 0;

        while (maxPointSofar < time){

//            update our longest start point until max point - that is in continuous way! (if got skipped - it fail)
            for (int[] cur : clips){
                int left  = cur[0];
                int right = cur[1];

                if (startPoint >= left && right > maxPointSofar){
                    maxPointSofar = right;
                }
            }

            if (startPoint == maxPointSofar){ //after we traverse all clips[][] and updated  end point - if its not continuous - we got skipping.
                return -1;
            }
            startPoint = maxPointSofar; //update for next loop.
            ++video_count;
        }

        return video_count;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int videoStitching2(int[][] clips, int time) {
        int total = 0; // Total number of clips used
        int end = 0;   // The end time of the last clip used

        Arrays.sort(clips, (a,b) -> a[0] - b[0]);
//        above line can be done with below line - but its slower.
//        Arrays.sort(clips, Comparator.comparingInt(a -> a[0]));

        for (int i = 0 ; end < time ; ) {
            int maxEnd = end;

            // Iterate through clips with start time less than or equal to 'end'
            while (i < clips.length && clips[i][0] <= end) {
                maxEnd = Math.max(maxEnd, clips[i][1]);
                i++;
            }

            // If no clip extends the coverage, return -1
            if (maxEnd == end)
                return -1;

            end = maxEnd; // Update 'end' to the maximum end time found
            total++;      // Increment the total number of clips used
        }

        return total;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int videoStitching3(int[][] clips, int time) {
        int startPoint = 0;
        int cur = 0;
        int ans = 0;

        Arrays.sort(clips, (a,b) -> a[0] - b[0]);

        while ( cur < clips.length &&
                startPoint >= clips[cur][0]  &&
                startPoint < time){
//            in order:
//            if we cant start from 0 - break. we cannot do have a full clip as missing start.
//            if we reach end of clips[][] - break. we traverse it all.
//            if low equals or bigger to 'time' - we cover the whole clip.
            int maxPointSofar = 0;

            while ( cur < clips.length && startPoint >= clips[cur][0]){
                maxPointSofar = Math.max(maxPointSofar, clips[cur][1]);
                ++cur;
            }

            startPoint = maxPointSofar;
            ++ans;
        }
        return startPoint >= time ? ans : -1;
    }
}