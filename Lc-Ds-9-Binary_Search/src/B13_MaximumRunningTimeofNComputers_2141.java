import java.util.Arrays;

/* problem:
return the max minutes all "n" computers can work together simultaneously.
1 computer == 1 battery no more!
batteries[] - how many minutes can hold each battery.
constraint: as many switch we want in 0 time!
constraint: 1 <= n <= batteries.length  */

/* solution from page:
 * 1st-Sorting and Prefix Sum
 * 2nd-BS of max threshold
 * 3rd-same as 2 - but changed framework with +1 - really hate that method but good to know, as it common... */

/*motivation:
since can switch in 0 time, first n batteries - are the strongest.
all others no matter their number, can be thought as "extra" since can switch in 0 time! */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//1st: sorting and prefix sum
public class B13_MaximumRunningTimeofNComputers_2141 {
/*general intuition:
 * use n biggest battery's to n computers.
 * if got more batteries than computers:
 * all remaining batteries can be thought as 1 big battery - as can swap in 0 time */

    public long maxRunTime_1ST_approach(int n, int[] batteries) {
        Arrays.sort(batteries);
        long extra = 0;
        for (int i = 0; i < batteries.length - n; i++) {
            extra += batteries[i];
        }

        // live stands for the n largest batteries we chose for n computers.
        // here will save all the in-use batteries - the largest that after sort are last.
        int[] live = Arrays.copyOfRange(batteries, batteries.length - n, batteries.length);

        // We increase the total running time using 'extra' by increasing
        // the running time of the computer with the smallest battery.
        for (int i = 0; i < n - 1; i++) {
            // If the target running time is between live[i] and live[i + 1].
            if (extra < (long)(i + 1) * (live[i + 1] - live[i])) {
                return live[i] + extra / (long)(i + 1);
            }

            // Reduce 'extra' by the total power used.
            extra -= (long)(i + 1) * (live[i + 1] - live[i]);
        }

        // If there is power left, we can increase the running time
        // of all computers.
        return live[n - 1] + extra / n;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*intuition:
* use binary search to find max threshold.
* each time search if "mid" running time can work. adjust search accordingly.
*
* right boundary - at optimal edge case: batteries.sum()/n */
    public long maxRunTime_2nd_approach_BS_regular_framework(int n, int[] batteries) {
        long left = 1; //constraint - battery always bigger than 1.

        //find right boundary.
        long right = 0;
        for (int power : batteries){
            right += power;
        }
        right /= n;


        while (left <= right){
            long mid = left + (right - left) / 2; //remember: mid represent minutes all n computer works.

            if (isValid(batteries, n, mid)){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return right;
    }

    private boolean isValid(int[] batteries, int n, long mid) {
        long extra = 0;

        for (int curBattery : batteries){
            extra += Math.min(curBattery, mid); //check "mid" time, so if battery stronger than mid - dont use it! as may use it for another battery, or dont use it at all - know only when continue BinarySearch
        }
            return (extra >= (long)(n * mid)); //isValid: if extra power enough for n computers.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public long maxRunTime_2nd_approach_BS_hate_that_framework(int n, int[] batteries) {
        long left = 1; //constraint - battery always bigger than 1.

        //find right boundary.
        long right = 0;
        for (int power : batteries){
            right += power;
        }
        right /= n;


        while (left < right){
            long mid = right - (right - left) / 2;

            if (isValid2(batteries, n, mid))
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }
    private boolean isValid2(int[] batteries, int n, long mid) {
        long extra = 0;

        for (int power : batteries){
            extra += Math.min(power, mid);
        }
        return (extra >= (long)(n * mid));
    }
}
