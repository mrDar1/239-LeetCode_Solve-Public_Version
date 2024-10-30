import java.util.ArrayList;
import java.util.Arrays;

/*problem:
 * given prices[] of stocks - return max profit can be made, with "k" transactions or less.
 * constraint-can only hold 1 stock at a time.
 * note: 1k==include buy and sell together */

/*solutions:
* 1st-top down
* 2nd-bottom-up */

/* shared complexity:
* time & space: O n*k.
* n==prices.len
* k==remain */

public class A10_BestTimetoBuyandSellStock4_188 {
    int n;
    int[] prices;
    int[][][] memo;  //<index at prices[] , bool: 0==not have and 1==do have stock , how many transaction left >

    public int maxProfit(int k, int[] prices) {
        this.prices = prices;
        this.n = prices.length;


        memo = new int[n][2][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dp(0, 0, k);
    }

    public int dp(int i, int holding, int remain) {

//        base case - reach end of stocks[] or cant do no more transactions.
        if (i == n || remain == 0) {
            return 0;
        }

//        if already cached - use it.
        if (memo[i][holding][remain] != -1) {
            return memo[i][holding][remain];
        }

        int ans = dp(i + 1, holding, remain);

        if (holding == 1) {
            ans = Math.max(ans,  prices[i] + dp(i + 1, 0, remain - 1)); //cur prophet after sell
        } else {
            ans = Math.max(ans, -prices[i] + dp(i + 1, 1, remain)); //curr prophet after buy.
        }

        memo[i][holding][remain] = ans;
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A10_BestTimetoBuyandSellStock4_188_bottom_up {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1]; //<index at prices[] , bool: 0==not have and 1==do have stock , how many transaction left >

        for (int i = n - 1; i >= 0; i--) {
            for (int remain = 1; remain <= k; remain++) {
                for (int holding = 0; holding < 2; holding++) {
                    int ans = dp[i + 1][holding][remain];

                    if (holding == 1) {
                        ans = Math.max(ans, prices[i] + dp[i + 1][0][remain - 1]);
                    }
                    else {
                        ans = Math.max(ans, -prices[i] + dp[i + 1][1][remain]);
                    }
                    dp[i][holding][remain] = ans;
                }
            }
        }

        return dp[0][0][k];
    }
}