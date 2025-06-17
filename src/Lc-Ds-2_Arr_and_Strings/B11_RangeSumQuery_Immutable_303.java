import java.util.*;
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */

/*problem signatures 2 methods:
 * 1st-constructor - given the input int[]
 * 2nd-return sum of input int[] - at given rang l,r.*/

/*solutions:
* 1st-brute force - just calculate specific given input.
* 2nd-use prefix sum - so each query faster
* 3rd-use Hashmap Pair - as it not necessary just for practice*/

public class B11_RangeSumQuery_Immutable_303 {
    /*psudo:
     * store input array at global int[]
     * each query - calculate specific prefix sum.*/
    int[] sum;

    public B11_RangeSumQuery_Immutable_303(int[] nums) {
        this.sum = nums;
    }

    public int sumRange(int left, int right) {
        int prefixsum = 0;

        for ( ; left < right ; left++) {
            prefixsum += sum[left];
        }

        return prefixsum;
    }

//    time: On - for each query.
//    space: O1 - as sum is reference to nums[] and not copy of it!
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B11_RangeSumQuery_Immutable_303_prefix_sum {
    /*psudo:
    * create prefix sum array.
    * each query just do prefix[r] - prefix[l] */

    int[] sum;
    public B11_RangeSumQuery_Immutable_303_prefix_sum(int[] nums) {
        this.sum = new int[nums.length + 1];

        sum[0] = 0; // note - the firt must be 0! so we can calculate prefix sum of r=1 l=0!
//        since we got at sum[0] extra element we need to run until i <= nums.len not just i < nums.len!!
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return sum[right + 1] - sum[left];
    }

//    space: On - for prefix sum.
//    time: On for prefix sum. then O1 - for each query.
}


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


/*
class B11_RangeSumQuery_Immutable_303_Hashmap {
    private Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();

    public B11_RangeSumQuery_Immutable_303_Hashmap(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i ; j < nums.length; j++) {
                sum += nums[j];
                map.put(Pair.create(i, j), sum);
            }
        }
    }

    public int sumRange(int left, int right) {
        return map.get(Pair.create(i, j));
    }
//    time & space:  On^2 - to create prefix sum for each possible combination.
}*/
