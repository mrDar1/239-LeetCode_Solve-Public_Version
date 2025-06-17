import java.util.Arrays;
/* problem:
given infinite coins, each one worth "coins[]".
find all different combinations that sums to "amount".
return 0 if not possible.
constraint - ans fit in int. */

/*solutions:
 * 1st-top-down
 * 2nd-bottom up -
 * 3rd-bottom up - improved space */

//top down:
public class B7_CoinChange2_518 {
    int[][] memo;
    int[] coins;

    public int change(int amount, int[] coins) {
        this.coins = coins;
        this.memo = new int[coins.length][amount + 1]; // hold: <how many different paths to get to "remainAmount" start from i index, remainAmount>

        for (int[] arr : memo){
            Arrays.fill(arr, -1);
        }

//        time:  O2(n*amount) - 1st-initialize memo[][], 2nd-recursions computes states.
//        space: O(n*amount) - 1st-initialize memo[][],2nd-recursions can grow to  O(n+amount) as we are reducing either the number of coins or the required amount while going from one recursive call to another.
        return dfs(0, amount);
    }

    private int dfs(int i, int remainAmount) {
//        base case - another successful path +1.
        if (remainAmount == 0){
            return 1;
        }

//        invalid - prune brunch.
        if (i >= coins.length){
            return 0;
        }

//        already cached that val:
        if (memo[i][remainAmount] != -1){
            return memo[i][remainAmount];
        }

//        too small - prune brunch (constraint: no negative coins)
//        at first use that "optimization" but no need, as next "if" will do it - will never allow "remainAmount" to be negative
//        if (remainAmount < 0){
//            return 0;
//        }

//        cannot use cur coin at all - check next coin.
        if (coins[i] > remainAmount){
            return memo[i][remainAmount] = dfs(i+1, remainAmount);
        }

//        2 possibilities:
//        1st - use that coin,
//        2nd - skip that coin and use the next.
        memo[i][remainAmount] = dfs(i, remainAmount - coins[i]) +
                                dfs(i+1, remainAmount);

        return memo[i][remainAmount];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B7_CoinChange2_518_bottom_up {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];  // hold: <how many different paths to get to "remainAmount" start from i index, remainAmount>

// Initialize the edge-base case: There is exactly one way to make the amount 0, by choosing no coins.
//        good if amount == 0
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

// Initialize the case where no coins are available: There are zero ways to make a positive amount.
//        good if we got empty int[] coins.
        for (int i = 1; i <= amount; i++) {
            dp[0][i] = 0;
        }

        for (int i = n - 1 ; i >= 0 ; i--) {
            for (int j = 1; j <= amount ; j++) {
                // If the coin value is greater than the current amount, we can't use this coin.
                if (coins[i] > j) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                // Otherwise, the number of ways to make amount j includes the number of ways to make amount j
                // without using this coin (dp[i + 1][j]) plus the number of ways to make amount j using this coin
                // (dp[i][j - coins[i]]).
                    dp[i][j] = dp[i + 1][j] + dp[i][j - coins[i]];
                }
            }
        }
        // The answer to the problem is in dp[0][amount], which gives the number of ways to make the amount using all coins.
        return dp[0][amount];
//        same complexity as above.
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B7_CoinChange2_518_bottom_up_improved_space {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

//        time: On*amount - first to initizlize dp[], nested loop that take On*amount
//        space: Oamount to do dp[].
        return dp[amount];
    }
}