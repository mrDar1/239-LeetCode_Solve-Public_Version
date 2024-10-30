import java.util.Arrays;

/*problem:
* get from start (top-left) to end (bottom-right).
* return all different roads can be taken.
* can move only down/right.
* 1-represent obstacle.
* 0-represent clear path. */

/*solutions:
 * 1st-top down
 * 2nd-bottom-up with 2D (space O n*m)
 * 3rd-bottom-up with 1D (space O m)
 * 3rd-bottom-up with O1 space (edit original grid[][] so its like 2nd 2D) */

//1st-recursion classic way.
public class A16_UniquePaths2_63 {
    int n;
    int m;
    int[][] memo;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.n = obstacleGrid.length;
        this.m = obstacleGrid[0].length;
        this.memo = new int[n][m];

        for (int[] row: memo){
            Arrays.fill(row,-1);
        }

        return dp(0,0, obstacleGrid);
    }
    private int dp(int row, int col, int[][] obstacleGrid) {

//        base case - reach end point (need "ternary if" for stupid edge case - that last cell blocked...)
        if (row==n-1 && col==m-1){
            return obstacleGrid[row][col] == 0 ? 1 : 0;
        }

//      not valid - prune (case out of grid or obstacle)
        if (row >= n || col >= m || obstacleGrid[row][col]==1){
            return 0;
        }

//        already cached - use it.
        if (memo[row][col] != -1){
            return memo[row][col];
        }

//        keep explore right and down paths.
        int pathDown  = dp(row + 1,col, obstacleGrid);
        int pathRight = dp(row,col + 1, obstacleGrid);

        return memo[row][col] = pathDown + pathRight;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*motivation: use memo dp[][] to cach how many paths have to get to that cell.
 each time add them to the way from left/up */

class A16_UniquePaths2_63_iterative_with_Onm_space_2D{
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];

        //constraint: only 1 way to reach start point.
        if (obstacleGrid[0][0] == 0) {
            dp[0][0] = 1;
        }

        // build prefix sum for row (number of paths when reach cell from left)
        for (int row = 1; row < n; row++) {
            if (obstacleGrid[row][0] == 0) { //if ==1 - its obstacle do not count.
                dp[row][0] = dp[row - 1][0];
            }
        }

        // build prefix sum for col (number of paths when reach cell from above)
        for (int col = 1; col < m; col++) {
            if (obstacleGrid[0][col] == 0) { //if ==1 - its obstacle do not count.
                dp[0][col] = dp[0][col - 1];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 0) {  //if ==1 - its obstacle do not count.
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; //add both ways to reach cell from left/up.
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*motivation: each time save only number of paths to reach cell - of 1 row.
* build the prefix sum of the next using the last prefix sum. */
class A16_UniquePaths2_63_iterative_with_Om_1D {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;

        int[] dp = new int[m];
        dp[0] = 1;        //constraint: only 1 way to reach start point.

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (obstacleGrid[row][col] == 1) { //obstacle - so cant be reach.
                    dp[col] = 0;
                } else if (col > 0) {
                    dp[col] += dp[col - 1];
                }
            }
        }
//        time: On*m
//        space: On
        return dp[m - 1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*motivation - edd the original grid[][] to like 2D sum of paths array */
class A16_UniquePaths2_63_iterative_with_O1_space {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        edge case - start with obstacle.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        obstacleGrid[0][0] = 1; //constraint: only 1 way to reach start point.

        // build prefix sum for row (number of paths when reach cell from left)
        for (int row = 1; row < n; row++) {
            obstacleGrid[row][0] =
                    (obstacleGrid[row][0] == 0 &&
                    obstacleGrid[row - 1][0] == 1)
                    ? 1
                    : 0;
        }

        // build prefix sum for col (number of paths when reach cell from above)
        for (int col = 1; col < m; col++) {
            obstacleGrid[0][col] =
                    (obstacleGrid[0][col] == 0 &&
                    obstacleGrid[0][col - 1] == 1)
                    ? 1
                    : 0;
        }

        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                if (obstacleGrid[row][col] == 0) { //sum ways from left and above.
                    obstacleGrid[row][col] = obstacleGrid[row - 1][col] + obstacleGrid[row][col - 1];
                } else { //obstacle so unreachable.
                    obstacleGrid[row][col] = 0;
                }
            }
        }

//        time: On*m
//        space: O1
        return obstacleGrid[n - 1][m - 1];
    }
}
