import java.util.Arrays;
/*problem:
return slowest speed can get on time, if impossible -1.
must reach office in "hour" time or less.
each train only leaves at int hour, like: 1,2,3,4,5... so need to wait till int hour no matter when arrived. */

/*solutions:
* 1st - binary search
* 2nd - same, optimized right boundary, small optimized of "check".*/

/*psudo:
* first edge case: if we got dist.length > hour - return -1, since must wait to int hour (round hour up) to imagine waiting an hour.
* use binary search:
*       left  boundary == 1 (problem constraint, cannot be 0.5 - as must use int).
*       right boundary == 10^7 - problem constraint highest possible. (later optimized)
*
* each binary use check function - return true, if can reach office at "hour" time, with "mid" speed:
*       traverse dist[] and sums time (each iteration round to upper int as wait an hour)
*       but - at last train do not round - as only in last train dont wait and hour!! */
public class A8_MinimumSpeedtoArriveonTime_1870 {
    public int minSpeedOnTime(int[] dist, double hour) {
//        edge case: if got more trains than "hour", no matter how fast go, still wait to int hour for next train.
        if (dist.length > Math.ceil(hour)){
            return -1;
        }

        int left = 1;
        int right = (int)Math.pow(10,7); // highest boundary possible.

        while (left <= right){
            int mid = left + (right - left) /2;

            if (check(dist, hour, mid)){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
//        k==maximum possible answer (here is 10^7).
//        time: O n log k : O log k - binary search. check: O1n - to check each speed if possible.
//        space:O1.
        return left;
    }

    private boolean check(int[] dist, double hour, int mid_speed) {
        double sum = 0;

        for (int i = 0; i < dist.length; i++) {
            if (i == dist.length - 1){
                sum += (double) dist[i] / mid_speed;
            } else {
                sum += (double) dist[i] / mid_speed;
                sum = Math.ceil(sum);
            }
        }
        return sum <= hour;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*optimize right boundary:
got 2 options:
1st - right boundary wont be faster than fastest train speed - since no matter how fast it go,
still need to wait to round hour for the next train.
2nd - only at last train dont need to wait - only at last train may speed need to be faster.
    take only the part after the dot and check fast enough to make it on time.
    example: dist[1,2,100_000] hour==2.01 - the last train speed must be 10_000_000 to make it on 0.01 time! */
class A8_MinimumSpeedtoArriveonTime_1870_optimized_right_boundary {
    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > Math.ceil(hour)){
            return -1;
        }

        int left = 1;

        int right = Math.max(
                 Arrays.stream(dist).max().getAsInt(),
                (int)Math.ceil(dist[dist.length - 1] / (hour - dist.length + 1)));

        while (left <= right){
            int mid = left + (right - left) /2;

            if (check(dist, hour, mid)){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] dist, double hour, int mid) {
        double sum = 0;

        for (int curDist : dist){
            sum = Math.ceil(sum);         //each time we round it up - as to resemble the wait to int hour - for the train. note-do not round the last train as in it dont need to wait!
            sum += curDist / (double)mid; // distance/speed == how long it took us to cover that distance at cur speed.
        }
        return sum <= hour;
    }
}