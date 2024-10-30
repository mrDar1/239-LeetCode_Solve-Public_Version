/*problem:
return smallest x possible.
given nums[] , int threshold
for each number at nums[] divide it by x.
then sums all nums[], sum must be <= threshold */

/*3 solutions:
* 1st - brute force, start with 1 and increase - got neat trick to avoid java ceil method.
* 2nd - same as 1, more elegant.
* 3rd - optimized with binary search to find min x */

/* **************************** */
/* **************************** */
/* **************************** */
/* **************************** */

//1st brute force:
/* motivation: start with smallest divisor 1, check if work, if fail try again with ++1 until success */
public class A9_FindtheSmallestDivisorGivenThreshold_1283 {
    public int smallestDivisor_first_brute_success(int[] nums, int threshold) {
        int left = 1; //smallest divisor

        int right = 0; //find max val inside nums[] - no point in use divisor bigger than it.
        for (int i : nums){
            right = Math.max(right,i);
        }

        for ( ; left <= right ; ++left ){
            int sum = 0;

            for (int i : nums){
                sum += (i + left - 1) / left;
//                sum += Math.ceil((double)i / left);
//                both do same thing!!! it neat trick to avoid java Math.ceil

//                prune - if already bigger than sum, stop, try again with ++left.
                if (sum > threshold){
                    break;
                }
            }

            if (sum <= threshold){
                return left;
            }
        }
        return -1;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int smallestDivisor_second_brute_(int[] nums, int threshold) {
        int left = 1;

        int right = 0;
        for (int i : nums){
            right = Math.max(right,i);
        }

        for ( ; left <= right ; ++left){
            int sum = 0;
            boolean thresholdExceeded = true;

            for (int i : nums){
                sum += Math.ceil((double)i / left);

                if (sum > threshold){
                    thresholdExceeded = false;
                    break;
                }
            }

            if (thresholdExceeded){
                return left;
            }
        }
        return -1;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int smallestDivisor_Binarysearch_binarySearch(int[] nums, int threshold) {
        int left = 1;

        int right = 0; //find max val inside nums[] - no point in use divisor bigger than it.
        for (int i : nums) {
            right = Math.max(right, i);
        }

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (valid(mid, nums, threshold)) {
                right = mid - 1; // work - next check if can decrease divisor
            } else
                left = mid + 1;  //fail - next timr increase divisor

        }
//        k==higest val inside nums[]
//        time: O2n log k : O1n - find right top boundary;  O log k - binary search + inside each binary O1n to check valid
//        space: O1
        return left;
    }

    private boolean valid(int mid, int[] nums, int threshold) {
        int sum = 0;

        for (int i : nums) {
            sum += (i + mid -1) /mid;
//            neat way to do java Math.ceil((double)i / mid);

        }
        return sum <= threshold;
    }
}