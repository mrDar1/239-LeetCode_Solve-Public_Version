/*problem:
split arr to k sub-array.
sum each sub-array.
minimize ths sum of each sub-array as possible.
return sum of the biggest sub array, that is part of the minimized sub-array. */

/* motivation:
 * each time use binary-search after "maxSum",
 * "maxSum" == the threshold, the number that when split nums[] to k sub-array,
 * it is the biggest sum in all sub-arrays. */

/*solutions:
* 1st - binary search - my favorite!
* 2nd - binary search - first time solve, messy */

/* **************************** */
/* **************************** */
/* **************************** */
/* **************************** */

import java.util.Arrays;

class A11_SplitArrayLargestSum_410_2nd_time_solve {
/*psudo:
* left & right boundarys:
*       left ==nums.maxVal - at edge case nums.len == k - size of the biggest sum from all sub-array is nums.maxVal, cannot be smaller than it.
*       right==nums.sum() - at edge case k==1, size of the once sub-array is the sum of all elements. cannot be bigger than it.
*
* binary-search - create k sub-array with "mid" as the maxSum sub-array:
*   if success - try again with smaller number (constraint: minimize as possible).
*   if fail - try again with bigger number.
*       keep binary search until find the threshold */
    public int splitArray(int[] nums, int k) {
//      update left & right values:
//      left==nums.maxVal, right==nums.sum()
        int left = Integer.MIN_VALUE;
        int right = 0;
        for (int i : nums) {
            right += i;
            left = Math.max(left, i);
//            above line could be replaced with:
//            left = left > i ? left : i;
        }

//        above lines could be replaced with (takes much longer to compute, at LC decrease from 100% to 38% at speed!):
//        int left =  Arrays.stream(nums).max().getAsInt();
//        int right = Arrays.stream(nums).sum();


        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(nums, mid, k)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
//        k==sum of nums[].
//        time: O2n (log k) : O1n-find left & right boundary's; O log  k - binary search + inside each: O1n-number of splits.
//        space: O1.
        return left;
    }

    private boolean isValid(int[] nums, int maxSum, int k) { //maxSum=="mid", as mid represent the biggest sum in each sub-arr
        int curSubSum = 0; //sum of curr sub-arr
        int c = 1; //count number of splits, always start from 1 - for the first sub-array.

        for (int i : nums) {
            if (curSubSum + i <= maxSum) { //when smaller than maxSum - can add more elements.
                curSubSum += i;
            }
            else {  //whenever reach maxSum - remove last item from last sub-arr, and add it to cur-sub-arr. to represent it, dont actually change anything in sub-array(they dont exist just count them)- so just count last element as if it part of cur and not from last.
                ++c;
                curSubSum = i;
                if (c > k){ //constraint: exactly k sub-array! if reach it means split to too many sub-arr, next binary-search, try again with bigger sum for each sub-arr.
                    return false;
                }
            }
        }
        return true;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd:
public class A11_SplitArrayLargestSum_410 {
    /*psudo:
     * create boundarys:
     * left ==nums[].maxVal - constraint: cannot be smaller than 1 element val
     * right==nums[].sum()  - cannot be bigger, at edge case - of k==1 sub-array - will be sum of k.
     *
     * binary search:
     *  create regular mid.
     *  each time send to method - that check number of splits to this mid value.
     *  if good - try smaller number: right = mid - 1; and save to outer var "midcopy" - to return ans at end.
     *  if fail - try bigger number:  left  = mid + 1;
     *
     * auxilary method psudo:
     * run on nums[] and count, whenever > mid - ++numOfSplit.
     * return numOfSplit + 1 (+1 for the last one)
     * */
    public int splitArray_myfirsttimesolve(int[] nums, int k) {
//      update left & right values. left==nums.maxVal, right==nums.sum()
        int left = Integer.MIN_VALUE;
        int right = 0;
        for (int i : nums){
            right += i;
            left = Math.max(left, i);
//            above line could be replace with:
//            left = left > i ? left : i;
        }

//        above lines could be replaced with (takes much longer to compute):
//        int left =  Arrays.stream(nums).max().getAsInt();
//        int right = Arrays.stream(nums).sum();

        int midcopy = 0;
        while (left <= right){
            int mid = left + (right - left) / 2;

            if ( findNumOfSplitSubarr(nums, mid) <= k){
                right = mid - 1;
                midcopy = mid;
            }else {
                left = mid + 1;
            }
        }
//        k==sum of nums[].
//        time: O2n (log k) : O1n-find left & right boundarys; O log  k - binary search + inside each: O1n-number of splits.
//        spcae: O1.
        return midcopy;
    }

    private int findNumOfSplitSubarr(int[] nums, int mid) {
        int cursum = 0;
        int c = 1; //count number of splits, start from 1 constraint.

        for (int i : nums) {
            if (cursum + i <= mid){ //when smaller than mid - can add more elements.
                cursum += i;
            } else { //whenever reach mid - remove last item from last sub-arr, and add it to cur-sub-arr. to represent it, dont actually change anything in sub-array(they dont exist just count them)- so just count last element as if it part of cur and not from last.
                cursum = i;
                ++c;
            }
        }
        return c;
    }
}