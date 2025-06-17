/*problem - return who wins the game: "alic" "bob" "tie".
* to win - one must sums highest score.
* each turn can take 1/2/3 stones - only the first!
* assume both play optimally.
* the game stop only when all stones used.
* alice always start. */

/*solutions:
* 1-top down
* 2-bottom up
* 3-bottom up optimized space */

//    top down:
public class B15_StoneGame3_1406 {
    int notComputed = 1000000000; //stone value can be negative, so initialize to very high out of bound number.
    int[] memo;

    public String stoneGameIII(int[] stoneValue) { //start here
        int n = stoneValue.length;

        memo = new int [n + 1];
        for (int i = 0; i < n; i++) {
            memo[i] = notComputed;
        }

        int dif = DFS(stoneValue, n, 0);
        if (dif > 0) {
            return "Alice";
        }
        if (dif < 0) {
            return "Bob";
        }
        return "Tie";
    }

    private int DFS(int[] stoneValue, int n, int i) {

//        base case:
        if (i == n) {
            return 0;
        }

//        already cached that val:
        if (memo[i] != notComputed) {
            return memo[i];
        }

//        explore all 3 possibilities - take 1/2/3 stones.
//        note: for case of 1 stone - no need for if as "base case" cover it.
        memo[i] = stoneValue[i] - DFS(stoneValue, n, i + 1);

        if (i + 2 <= n) {
            memo[i] = Math.max(memo[i], stoneValue[i]
                    + stoneValue[i + 1] - DFS(stoneValue, n, i + 2));
        }
        if (i + 3 <= n) {
            memo[i] = Math.max(memo[i], stoneValue[i] + stoneValue[i + 1]
                    + stoneValue[i + 2] - DFS(stoneValue, n, i + 3));
        }

        return memo[i];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B15_StoneGame3_1406_bottom_up {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int [n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = stoneValue[i] - dp[i + 1];
            if (i + 2 <= n) {
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            }
            if (i + 3 <= n) {
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            }
        }
        if (dp[0] > 0) {
            return "Alice";
        }
        if (dp[0] < 0) {
            return "Bob";
        }
//        time: On
//        space: On
        return "Tie";
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B15_StoneGame3_1406_bottom_up_improved_space {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int [4];

        for (int i = n - 1; i >= 0; i--) {
            dp[i % 4] = stoneValue[i] - dp[(i + 1) % 4];
            if (i + 2 <= n) {
                dp[i % 4] = Math.max(dp[i % 4], stoneValue[i] + stoneValue[i + 1]
                        - dp[(i + 2) % 4]);
            }
            if (i + 3 <= n) {
                dp[i % 4] = Math.max(dp[i % 4], stoneValue[i] + stoneValue[i + 1]
                        + stoneValue[i + 2] - dp[(i + 3) % 4]);
            }
        }
        if (dp[0] > 0) {
            return "Alice";
        }
        if (dp[0] < 0) {
            return "Bob";
        }
        return "Tie";
//        time: On
//        space: O1
    }
}