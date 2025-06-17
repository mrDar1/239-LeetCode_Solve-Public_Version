/*problem:
* return min time that all buses combine do "totalTrips" trips.
* time.len - number of buses, each fulfill trip at time[i] time. */

/*intuition:
* boundary:
* left  == time.min * totalTrips / number of buses (at best edge case all buses drive at fastest speed)
* right == time.max * totalTrips (at worst edge case if only the slowest bus did all trips by its oun)
* binary search of lowest threshold */

public class B9_MinimumTimetoCompleteTrips_2187 {
    public long minimumTime(int[] time, int totalTrips) {
//        set boundary's for binary search:
        long left = Integer.MAX_VALUE;
        long right = 0;
        for (int i : time){
            left  = Math.min(left, i);
            right = Math.max(right, i);
        }

        left  = left*totalTrips/time.length;
        right *= totalTrips;


        while (left <= right){
            long mid = left + (right - left)/2;

            if (isValid(time, totalTrips, mid)){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean isValid(int[] time, int totalTrips, long mid) {
        long c = 0; //count number of trips
        for (int i : time){
            c += mid / i; // (as it int division will not count half trip)
        }

        return c >= totalTrips;
    }
}

//        n==time[].lent
//        m==upper limit of BS-right boundary.
//        k==max time for 1 trip.
//        time: On log (m*k) : O1n-find left,right boundary's, O log m - BS * inside each BS traverse k times to check ifValid.
//        space:O1.