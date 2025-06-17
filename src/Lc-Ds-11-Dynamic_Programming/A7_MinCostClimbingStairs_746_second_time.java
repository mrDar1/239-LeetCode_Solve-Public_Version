import java.util.HashMap;
//did in A2 - good problem do it again...

/*problem:
 * return lowest cost to climb outside of array.
 * each time pay, can step 1 or 2 stairs. */

/* solve in 2 ways:
 * 1st-top-down recursion memoization, with global vars.
 * 2nd-bottom up iterative tabulating
 * 3rd-bottom up, optimize space complexity. */

public class A7_MinCostClimbingStairs_746_second_time {
    HashMap<Integer,Integer> memo = new HashMap<>();
    public int minCostClimbingStairs(int[] cost) {
//        time: O1n traverse each element once
//        space:O2n-hashmap memo, and recursion max depth.
        return dp(cost.length,cost);
    }

    private int dp(int i, int[] cost) {
        if (i <= 1){
            return 0; //y 0? since we can skip the first step!
        }

        if(memo.containsKey(i)){
            return memo.get(i);
        }

        // dp() + cost[]: dp()==sum cost so far + cost[]==current step cost
        memo.put(i, Math.min( dp(i-1, cost) + cost[i-1] , dp(i-2, cost) + cost[i-2] ));
        return memo.get(i);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A7_MinCostClimbingStairs_746_second_time_tabulating {
    public int minCostClimbingStairs(int[] cost) {
        int[] memo = new int[cost.length + 1]; //must use +1! why? constraint: reach outside of array! (not simply reach to last elements)

        if (cost.length <= 1){//can skip the first step.
            return 0;
        }

//        in java no need to initialize to zero just good practice to me to work on c later!
        memo[0] = 0;
        memo[1] = 0;

        for (int i = 2; i <= cost.length; i++) { //why 2? as we can skip the first.
            //remember: memo[]-retrive sum so far cost[]-retrive curr cost
            memo[i] = Math.min( cost[i-2] + memo[i-2] ,cost[i-1] +  memo[i-1]);
        }

//        time: O1n traverse each element once
//        space:O2n-hashmap memo, and recursion
        return memo[cost.length];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A7_MinCostClimbingStairs_746_second_time_tabulating_optimized {
    public int minCostClimbingStairs(int[] cost) {
        int downOne = 0;
        int downTwo = 0;

        if (cost.length <= 1){//can skip the first step.
            return 0;
        }

        for (int i = 2; i <= cost.length; i++) { //why 2? as we can skip the first 2.
            int temp = downOne;
            downOne = Math.min(downOne + cost[i - 1], downTwo + cost[i - 2]);
            downTwo = temp;
        }
//        time: O1n traverse each element once
//        space:O1 - 3 vars.
        return downOne;
    }
}