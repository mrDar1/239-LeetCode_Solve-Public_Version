import java.util.HashMap;
/*problem:
* return max prophet, but cannot rob 2 adjacent houses. */

/* solutions:
* top-down, with global vars.
* bottom up,
* bottom up optimized O1-space*/

public class A3_HouseRobber_198 {
    HashMap<Integer,Integer> memo = new HashMap<>(); //<index, sum of rob so far>
    int nums[];

    public int rob(int[] nums) {
        this.nums = nums;

//        edge case:
        if(nums.length == 1){
            return nums[0];
        }

//        time&space: O1n - each node visited exactly once.
        return dp(nums.length -1);
    }

    private int dp(int i) {
        if (i==0){
            return this.nums[0];
        }

        if (i==1){
            return Math.max(this.nums[0], this.nums[1] );
        }

        if (memo.containsKey(i)){
            return memo.get(i);
        }

        memo.put(i, Math.max(dp(i - 2) + this.nums[i] , dp(i - 1)));
        return memo.get(i);
    }
}
//        time&space: O1n - each node visited exactly once.

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A3_HouseRobber_198_bottom_up {
    public int rob(int[] nums) {
//        edge case - to prevent out of bounds error
        if(nums.length == 1) {
            return nums[0];
        }

        int[] tabulate = new int[nums.length];

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        tabulate[0] = nums[0];
        tabulate[1] = Math.max(nums[0], nums[1] );

        for (int i = 2; i < nums.length; i++) {
            tabulate[i] = Math.max(tabulate[i - 2] + nums[i], tabulate[i - 1]);
        }
        return tabulate[nums.length - 1];
    }
}
//        time&space: O1n - each node visited exactly once.

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A3_HouseRobber_198_bottom_up_optimized {
    public int rob(int[] nums) {
//        edge case - to prevent out of bounds error
        if(nums.length == 1) {
            return nums[0];
        }

        int back2step = nums[0];
        int back1step = Math.max( nums[0], nums[1]) ;

        for (int i = 2; i < nums.length; i++) {
            int temp = back1step;
            back1step = Math.max(back1step, back2step + nums[i]);
            back2step = temp;

        }
        return back1step;
    }
}
//        time:  O1n.
//        space: O1!!
//This optimization is possible whenever the recurrence relation is
// static == it doesn't change between inputs and it only cares about a static number of states.