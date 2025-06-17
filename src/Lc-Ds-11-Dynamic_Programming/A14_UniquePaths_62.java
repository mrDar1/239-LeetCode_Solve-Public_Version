import java.util.Arrays;

/*problem:
* given grid[m][n] size, return all possible paths from top-left to bottom-right
* constraint: can only move down/right. */

/*solutions:
 * 1st-top down
 * 2nd-bottom-up
 * 3rd-bottom-up with O1 space. */

/*intuition:
* start from end point, DFS till start point. each time, check and store sum of different sum to get that cell
*  until reach to last end point. */

public class A14_UniquePaths_62 {
    int[][] memo;
    public int uniquePaths(int m, int n) {
        this.memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
//        time:  On*m
//        space: On*m
        return dp(m-1,n-1); //important -1 without it we out of boundary!
    }

    private int dp(int row, int col) {

//        base case - traversed all grid[][].
        if (row + col == 0){
            return 1;
        }

//        already cached - use it.
        if (memo[row][col] != -1){
            return memo[row][col];
        }

        int ways = 0;
        if (row > 0){
            ways += dp(row - 1, col);
        }
        if (col > 0){
            ways += dp(row, col - 1);
        }

        memo[row][col] = ways;
        return memo[row][col];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A14_UniquePaths_62_tabulation {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];

        for (int i = 0; i < m; i++) { //at java it happens auto, good practice for c.
            Arrays.fill(memo[i], 0);
        }

        memo[0][0] = 1; //constraint! robot start at that place so its zero, but constraint is 1!
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (row > 0){
                    memo[row][col] += memo[row - 1][col];
                }
                if (col > 0){
                    memo[row][col] += memo[row][col - 1];
                }
            }
        }
//        time:  On*m
//        space: On*m
        return memo[m - 1][n - 1]; //important -1 without it we out of boundary!
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A14_UniquePaths_62_tabulation_improved_space {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        for (int row = 0; row < m; row++) {
            int[] nextRow = new int[n];

            for (int col = 0; col < n; col++) {
                nextRow[col] += dp[col];
                if (col > 0) {
                    nextRow[col] += nextRow[col - 1];
                }
            }
            dp = nextRow;
        }
//        time:  On*m
//        space: O1n
        return dp[n - 1];
    }
}