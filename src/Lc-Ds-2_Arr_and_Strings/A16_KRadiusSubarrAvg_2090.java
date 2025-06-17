import java.util.*;
/*problem:
given: int[] arr, int k.
return helper int[]:
for each element - find it avg of k elements after and k elements before - together.
if reach start/end and cannot compute avg - put -1. */

/*3 solutions:
* 1st - my intuitive way
* 2nd - LC1 prefix sum - nice.
* 3rd - LC2, improved space - sliding window didnt found that much elegant. */
public class A16_KRadiusSubarrAvg_2090 {
    public static void main(String[] args){
        int[] nums = {7,4,3,9,1,8,5,2,6};
        int k = 3;
//        Output: [-1,-1,-1,5,4,4,-1,-1,-1]

        int[] numsavg = new int[nums.length];
        numsavg = getAverages_firsttry(nums, k);
        System.out.println(Arrays.toString(numsavg));

//        numsavg = getAverages(nums, k);
//        System.out.println(Arrays.toString(numsavg));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*psudo: first approach:
 fill the ans arr with -1.
 use prefix sum arr.
 r = k;
 l = 0;
 middle = k/2;
 avg = r-middle / k;
 */
    public static int[] getAverages_firsttry(int[] arr, int k) {
        int len = arr.length;
        int[] ans = new int[len];
        int l = 0 ;//left of sliding window.
        int r = 0; //right boundary of sliding window
        long t = 0; //total sum of current window

//        edge case:
        if (0 == k) return arr; //constraint: in that case return original input (cannot calculate avg to 0 elements).

//        put -1 to all arr:
        Arrays.fill(ans, -1);

//        edge case:
        if(2*k+1 > len) return ans; // constraint: if k too big for input - return arr fill with -1.

//      prefix total - for first radius only:
        for( ; r < 2*k+1 ; ++r){
            t += arr[r];
//            System.out.print(t + ", ");
        }
        ans[k] = (int) (t / (2 * k + 1));

//        prefix total - for the rest of radius, advance.
        for ( ; r < len ; ++r, ++l){
            t -= arr[l];
            t += arr[r];
            ans[k+l+1] = (int) (t / (2 * k + 1));
        }
//      time: O2n - fill with -1, sliding window
//      space: O 1
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class getAverages_lc1_prefix_sum {
    public int[] getAverages(int[] nums, int k) {
        // When a single element is considered then its average will be the number itself only.
        if (k == 0) {
            return nums;
        }

        int windowSize = 2 * k + 1;
        int n = nums.length;
        int[] averages = new int[n];
        Arrays.fill(averages, -1);

        // Any index will not have 'k' elements in it's left and right.
        if (windowSize > n) {
            return averages;
        }

        // Generate 'prefix' array for 'nums'.
        // 'prefix[i + 1]' will be sum of all elements of 'nums' from index '0' to 'i'.
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // We iterate only on those indices which have atleast 'k' elements in their left and right.
        // i.e. indices from 'k' to 'n - k'
        for (int i = k; i < (n - k); ++i) {
            int leftBound = i - k;
            int rightBound = i + k;

            long subArraySum = prefix[rightBound + 1] - prefix[leftBound]; //note in prefix we choose the +1!
            int average = (int) (subArraySum / windowSize);
            averages[i] = average;
        }

//        time: O2n - n-build prefix ; n-
//        space: On - prefix sum arr.
        return averages;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class getAverages_lc2_sliding_window {
    public int[] getAverages(int[] nums, int k) {
        // When a single element is considered then its average will be the number itself only.
        if (k == 0) {
            return nums;
        }

        int n = nums.length;
        int windowSize = 2 * k + 1;
        int[] averages = new int[n];
        Arrays.fill(averages, -1);

        // Any index will not have 'k' elements in its left and right.
        if (windowSize > n) {
            return averages;
        }

        // First get the sum of first window of the 'nums' array.
        long windowSum = 0;
        for (int i = 0; i < windowSize; ++i) {
            windowSum += nums[i];
        }
        averages[k] = (int) (windowSum / windowSize);

        // Iterate on rest indices which have at least 'k' elements
        // on its left and right sides.
        for (int i = windowSize; i < n; ++i) {
            // We remove the discarded element and add the new element to get current window sum.
            // 'i' is the index of new inserted element, and
            // 'i - (window size)' is the index of the last removed element.
            windowSum = windowSum - nums[i - windowSize] + nums[i];
            averages[i - k] = (int) (windowSum / windowSize);
        }

//        time: O2n - n-fill averages[] with -1, iterate again on n and asign values.
//        space: O1
        return averages;
    }
}