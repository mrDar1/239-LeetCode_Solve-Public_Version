import java.util.HashMap;
/*problem:
* return lowest cost to climb outside of array.
* each time pay, can step 1 or 2 stairs. */

/* solve in 2 ways:
* 1st-top-down recursion memoization, with global vars.
* 2nd-bottom up iterative tabulating
* 3rd-bottom up, optimize space complexity. */

public class A2_MinCostClimbingStairs_746 {
    HashMap<Integer,Integer> memo = new HashMap<>();
    int[] cost;

    public int minCostClimbingStairs(int[] cost) {
        this.cost = cost;
        int i = cost.length; //as in top-down: start from top, and go to base cases.
        return dp(i);
    }

    private int dp(int i) {
        //base case:
        if (i <= 1){ //y <=1? -constraint: can choose to skip the 1 step!!
            return 0;
        }

        if (memo.containsKey(i)){
            return memo.get(i);
        }

        memo.put(i, Math.min( dp(i - 1) + cost[i-1] , dp(i - 2) + cost[i-2]));
        /*why do we need to do dp()+cost[] ?
         * ans:   dp()-will give cost so far,
         *        cost[] will give cur cost.
         * when have these 2 compare what cheaper: to skip last step, or do step in it */

        return memo.get(i);
    }
}
//        time:  O1n-each traverse once.
//        space: O2n-first for memo, second recursion.

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A2_MinCostClimbingStairs_746_tabulating {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];//must use +1! why? constraint: reach outside of array! (not simply reach to last elements)

//        edge/base case - didnt realy use it...
        if (n == 0) return Integer.MAX_VALUE;

//        in java no need to initialize to zero just good practice to whom work on c.
//        constraint: can reach first or second step with zero cost as can skip it.
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n ; i++) { //why 2? constraint, as can skip the first 2 steps.
            //remember: dp[]-retrive sum so far,
            //          cost[]-retrive curr cost.
            dp[i] = Math.min( dp[i - 1] + cost[i-1] , dp[i - 2] + cost[i-2] ) ;
        }

//        time and space: O1n.
        return dp[n];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A2_MinCostClimbingStairs_746_tabulating_optimizeed {
    public int minCostClimbingStairs(int[] cost) {
        int downOne = 0;
        int downTwo = 0;

        for (int i = 2; i < cost.length + 1; i++) {
            int temp = downOne;

            downOne = Math.min(downOne + cost[i - 1], downTwo + cost[i - 2]);
            downTwo = temp;
        }

        return downOne;
    }
}
//time: O1n
//space: O1 - only use 2 vars of last 2 options.