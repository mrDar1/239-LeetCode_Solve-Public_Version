/*problem:
given an integer array nums consisting of n elements, and an integer k.
Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
(Any answer with a calculation error less than 10-5 will be accepted).
in simple words: find max avg for window size k. */

/*solutions (both same, second optimized):
1st - division at each round.
2nd - only division when return - faster, but could cause overflow!!

shared complexity:
time: O1n
space: O1 */

public class A11_MaximumAverageSubarray1_643 {
    public static void main(String[] args) {
//        int[] arr = {1, 12, -5, -6, 50, 3};
//        int k = 4;
        int[] arr = {0,1,1,3,3};
        int k = 4;

        double n = findMaxAverage(arr, k);
        System.out.println("max average is: " + n);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //1st:
    public static double findMaxAverage(int[] nums, int k) {
        int l = 0;
        int r = 0;
        double sum = 0;
        double avg = 0;

//        initialize first k elements:
        for(; r < k ; ++r){
            sum += nums[r];
        }
        avg = sum / k;

        for(; r < nums.length ; ++r, ++l){
            sum += nums[r];
            sum -= nums[l];
            avg = Math.max(avg, sum / k);
        }
        return avg;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //2nd:
    public static double findMaxAverage_Faster(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int max = 0;
        int cur = 0;

//        initialize first k elements:
        for(; r < k ; ++r){
            cur += nums[r];
        }
        max = cur;

        for(; r < nums.length ; ++r, ++l){
            cur += nums[r];
            cur -= nums[l];
            max = Math.max(cur, max);
        }
        return ((double)max/k);
    }
}