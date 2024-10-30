import java.util.Arrays;
/*problem:
 * return the max val from trade.
 * constraints:
 * after sell stock - must wait 1 day "cooldown" before can buy again.
 * can only hold 1 stock at time.
 * must buy before sell */

/*solutions:
 * 1st-top down
 * 2nd-bottom-up
 * 3rd-bottom-up with O1 space. */

/*intuition:
* each time send to recursion 2 states: 1-index of cur day. 2-"state" of trader:
* trader can only be at 3 states:
 * 0-have no stock - can buy now/wait to buy at the future.
 * 1-do have stock - can sell now/wait to sell at the future.
 * 2-cooldown - just sold must wait cur day.
 * return max of all paths. */
class A13_BestTimetoBuyandSellStockwithCooldown_309_top_down {
    private int[][] memo;

    public int maxProfit(int[] prices) {
        this.memo = new int[prices.length][3]; // state: 0: not have stock, do not need wait. 1-have stock, no wait. 3-have no stock but must wait.
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(prices, 0, 0);
    }

    private int dp(int[] prices, int day, int state) {

//        base case - traversed all trading days.
        if (day >= prices.length) {
            return 0;
        }

//        already cached - use it.
        if (memo[day][state] != -1) {
            return memo[day][state];
        }

//        do nothing path: only when have or dont have stock, if at "cooldown" only put 0, and "doSomething" handle it.
        int doNothing = 0;
        if (state == 0 || state == 1) {
            doNothing = dp(prices, day + 1, state);
        }

        int doSomething;
        if (state == 0) {
            // If resting, may buy stock
            doSomething = -prices[day] + dp(prices, day + 1, 1);
        } else if (state == 1) {
            // If holding, may sell stock
            doSomething =  prices[day] + dp(prices, day + 1, 2);
        } else {
            // If cooling down, may only rest - update state "cooldown" finish (change from 2 to 0).
            doSomething = dp(prices, day + 1, 0);
        }

        memo[day][state] = Math.max(doNothing, doSomething);
        return memo[day][state];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A13_BestTimetoBuyandSellStockwithCooldown_309_bottom_up {
    public int maxProfit(int[] prices) {
//        edge case:
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][3];  // state: 0: not have stock, do not need wait. 1-have stock, no wait. 3-have no stock but must wait.

        // Base cases
        dp[0][0] = 0;           // Resting on day 0
        dp[0][1] = -prices[0];  // Holding on day 0 (bought the stock)
        dp[0][2] = 0;           // Cooling down on day 0 (not possible)

        for (int day = 1; day < n; day++) {
            // state 0 - not have stock, do not need wait - what higher? do nothing or buy.
            dp[day][0] = Math.max(dp[day - 1][0], dp[day - 1][2]);

            // state 1 - have stock, no wait - what higher? do nothing or sell.
            dp[day][1] = Math.max(dp[day - 1][1], dp[day - 1][0] - prices[day]);

            // state 2 - have no stock but must wait - 1 option.
            dp[day][2] = dp[day - 1][1] + prices[day];
        }

        // The maximum profit will be in either resting or cooldown state on the last day
        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd- bottom up with O1 space.
public class A13_BestTimetoBuyandSellStockwithCooldown_309 {
    public int maxProfit(int[] prices) {

        int sold = Integer.MIN_VALUE; //just sold stock, must wait.
        int reset = 0;                //finish wait, can buy again.
        int held = Integer.MIN_VALUE;

        for (int price : prices) {
            int preSold = sold;

            sold = held + price;
            held = Math.max(held, reset - price);
            reset = Math.max(reset, preSold);
        }
//        time: O1n.
//        space: O1
        return Math.max(sold, reset);
    }
}