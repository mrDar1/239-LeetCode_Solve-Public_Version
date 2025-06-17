import java.util.Arrays;
/*problem:
* return the max val from trade.
* fee-is how much it cost to buy and sell stock (charge once for both).
* constraints:
* can only hold 1 stock at time.
* must buy before sell */

//todo - understand 4,5 and compare to 2,3.
/*solutions:
 * 1st-top down
 * 2nd-bottom-up
 * 3rd-bottom-up improved space
 * 4th-another bottom up
 * 5th-another bottom-up improved space */

/*intuition:
 * got 3 decisions:
     * 1 do nothing
     * 2 buy stock
     * 3 sell stock
 * each time choose the profitable way - it is 3 explores but can reduce explore to only 2, since may only do nothing and buy/sell - cant do both!
 * on last day - must sell the stock. */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A12_BestTimetoBuyandSellStockwithTransactionFee_714_top_down {
    private int[] prices;
    private int fee;
    private int[][] memo;

    public int maxProfit(int[] prices, int fee) {
        this.prices = prices;
        this.fee = fee;

        this.memo = new int[prices.length][2]; // 0: no stock, 1: has stock
        for (int i = 0; i < prices.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dp(0, 0);
    }

    private int dp(int day, int hasStock) {

//        base case - traversed all trading days.
        if (day == prices.length) {
            return 0;
        }

//        already cached - use it.
        if (memo[day][hasStock] != -1) {
            return memo[day][hasStock];
        }

        int doNothing = dp(day + 1, hasStock);

        int doSomething;
        if (hasStock == 1) {
            // sell Stock
            doSomething =  prices[day] - fee + dp(day + 1, 0);
        }
        else { // has no stock, may buy stock
            doSomething = -prices[day]       + dp(day + 1, 1);
        }

        memo[day][hasStock] = Math.max(doNothing, doSomething);
        return memo[day][hasStock];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

public class A12_BestTimetoBuyandSellStockwithTransactionFee_714 {
    public int maxProfit(int[] prices, int fee) {
        int[] sellStock     = new int[prices.length];//max profit if sell stock.
        int[] purchaseStock = new int[prices.length];//max profit if buy stock.

        purchaseStock[0] = -prices[0]; //the price to buy stock on day 0.

        for (int i = 1; i < prices.length; i++) {
            purchaseStock[i] = Math.max(purchaseStock[i-1], sellStock[i-1] - prices[i]);
//            purchaseStock[i-1] == do nothing.
//            sellStock[i-1] - prices[i] == if we sell the stock yesterday(1) minus the price its cost to buy it now.
//            (1)yesterday - not really sell stock yesterday, just see the max profit yesterday if today we will buy the stock.

            sellStock[i]     = Math.max(sellStock[i -1] , (purchaseStock[i-1] + prices[i] - fee));
//            sellStock[i -1] == do nothing.
//            purchaseStock[i-1] + prices[i] - fee == the price we get from selling stock minus the fee.
        }

//        time: O1n - traverse each exactly once.
//        space:O2n - 2 int[] size of n.
        return sellStock[prices.length - 1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A12_BestTimetoBuyandSellStockwithTransactionFee_714_improved_space{
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int sellStock = 0;
        int purchaseStock = -prices[0];

        for (int i = 1; i < n; i++) {
            int tmp = purchaseStock;
            purchaseStock = Math.max(purchaseStock, sellStock - prices[i]);
            sellStock = Math.max(sellStock, tmp + prices[i] - fee);
        }

//        time:  O1n
//        space: O1
        return sellStock;
    }
}

class Solution1 {
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2]; // 0: no stock, 1: hold stock

        // Base cases
        dp[0][0] = 0; // No stock on day 0
        dp[0][1] = -prices[0]; // Hold stock on day 0

        for (int i = 1; i < prices.length; i++) {
            // Max profit not holding stock on day i
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            // Max profit holding stock on day i
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        // Maximum profit on the last day with no stock in hand
        return dp[prices.length - 1][0];
    }
}

class Solution2 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // Variables to store the max profit when not holding a stock and when holding a stock
        int cash = 0; // Max profit when not holding a stock
        int hold = -prices[0]; // Max profit when holding a stock (initially bought on day 0)

        for (int i = 1; i < n; i++) {
            // Update the cash and hold values for day i
            cash = Math.max(cash, hold + prices[i] - fee); // Max profit if we sell the stock today
            hold = Math.max(hold, cash - prices[i]); // Max profit if we buy the stock today
        }

        // The max profit when not holding any stock on the last day
        return cash;
    }
}