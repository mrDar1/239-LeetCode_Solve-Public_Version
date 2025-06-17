import java.util.Arrays;
import java.util.List;

/*problem:
* given several piles of coins: List<List<"piles">> - denoting the composition of coins from top to bottom. and k moves.
* each move can only take the top coin of every pile wanted.
* return highest value possible - to take from "piles" and add to wallet in k moves.
* note: top to bottom! so piles[0] - is the top coin at pile. */

/*solutions:
 * 1st-top down
 * 2nd-bottom-up */

/*complexity for both:
* n==size of piles, k==number of coins can be taken , x== avg coins per pile
 * time:  O n * k * x
* space: O n * k     */
public class A11_MaximumValueofKCoinsFromPiles_2218 {
    int n;
    int[][] memo;
    List<List<Integer>> piles;

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        this.piles = piles;
        n = piles.size();

        memo = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dp(0, k);
    }

    public int dp(int i, int remain) {
//        base case - reach end of "piles" or cant take no more coins.
        if (i == n || remain == 0) {
            return 0;
        }

//        if already cached - use it.
        if (memo[i][remain] != -1) {
            return memo[i][remain];
        }

        int curr = 0;
        int ans = dp(i + 1, remain); //cases choose to skip cur pile and take coin from another.

        //traverse until can take no more coins (=<k) or until cur pile is over - choose min.
        for (int j = 0; j < Math.min(remain, piles.get(i).size()); j++) {
            curr += piles.get(i).get(j); //adding top coin at cur pile. constraint: denoting the composition of coins from top to bottom
            ans = Math.max(ans, curr + dp(i + 1, remain - j - 1));// why "remain-j-1"? -constraint: given k coins. each time deduce the 1 just used (=-1) and if use more coins from same pile also them (=-j)
        }

        memo[i][remain] = ans;
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A11_MaximumValueofKCoinsFromPiles_2218_tabulation {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];

        //traverse piles, start from last
        for (int i = n - 1; i >= 0; i--) {
            //traverse as long as got enough "k" to take coins. constraint: remain >=1.
            for (int remain = 1; remain <= k; remain++) {
                dp[i][remain] = dp[i + 1][remain]; //saves other pile max. so later can choose max: from cur or from other pile.

                //traverse until can take no more coins (=<k) or until cur pile is over - choose min.
                int curr = 0;
                for (int j = 0; j < Math.min(remain, piles.get(i).size()); j++) {
                    curr += piles.get(i).get(j);
                    dp[i][remain] = Math.max(dp[i][remain], curr + dp[i + 1][remain - j - 1]);
                }
            }
        }

        return dp[0][k];
    }
}