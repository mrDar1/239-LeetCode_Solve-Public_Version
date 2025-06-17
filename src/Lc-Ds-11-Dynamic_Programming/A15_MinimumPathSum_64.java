import java.util.Arrays;

/*problem:
* given grid[][], return min cost sum to reach from top-left to bottom right.
* each step cost cell val.
* can only move down and right.
* grid[][] consists only positive numbers */

/*solutions: //TODO conclusion: note how easy it is to flip top-down, but bottom-up much trickier!
 * 1st-top down (send to recursion finish point of grid)
 * 1.5-top down - inverted for practice (send to recursion 0,0)
 * 2nd-bottom-up
 * 3rd-bottom-up with O1m space.
 * 3.5-bottom-up with O1m space - inverted for practice (instead of return len-1, return 0)*/

/*intuition:
 * start from end point, DFS till start point. each time, check and store at memo sum of path
 * until reach that cell, choose the min cost to reach cur cell. each time calculate curr cost from costs of cell before */

public class A15_MinimumPathSum_64 {
    int[][] memo;
    int[][] grid;
    int n;
    int m;
    public int minPathSum(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        this.memo = new int[n][m]; //store the min cost to reach each cell in 2D array.

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }
//        time: O2n*m - initialized memo to -1, traverse min path sum.
//        space:On*m - memoization.
        return dp(n-1,m-1);
    }

    private int dp(int row, int col) {
//        base case - reach start point.
        if (row + col == 0){
            return grid[0][0];
        }

//        already cached - use it.
        if (memo[row][col] != -1){
          return memo[row][col];
        }

        int ans = Integer.MAX_VALUE;
        if (row > 0){
            ans = Math.min(dp(row - 1, col), ans);
        }
        if (col > 0){
            ans = Math.min(dp(row, col - 1), ans);
        }

        memo[row][col] = grid[row][col] + ans;
        return memo[row][col];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A15_MinimumPathSum_64_flip_instead_0_to_end {
    int[][] memo;
    int[][] grid;
    int n;
    int m;
    public int minPathSum(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        this.memo = new int[n][m]; //store the min cost to reach each cell in 2D array.

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }
//        time: O2n*m - initialized memo to -1, traverse min path sum.
//        space:On*m - memoization.
        return dp(0,0);
    }

    private int dp(int row, int col) {
//        base case - reach start point.
        if (row == n -1 && col == m-1){
            return grid[n -1][m-1];
        }

//        already cached - use it.
        if (memo[row][col] != -1){
          return memo[row][col];
        }

        int ans = Integer.MAX_VALUE;
        if (row < n -1){
            ans = Math.min(dp(row + 1, col), ans);
        }
        if (col < m - 1){
            ans = Math.min(dp(row, col + 1), ans);
        }

        memo[row][col] = grid[row][col] + ans;
        return memo[row][col];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A15_MinimumPathSum_64_bottom_up {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] tabu = new int[n][m]; //store the min cost to reach each cell in 2D array.
        tabu[0][0] = grid[0][0];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (row + col == 0){ //skip start point, as cost to get there is 0.
                    continue;
                }

                int ans = Integer.MAX_VALUE;

                //when not at first row - check if can reach cur cell, at cheaper cost from the cell above.
                if (row > 0) {
                    ans = Math.min(ans, tabu[row - 1][col]);
                }
                //when not at first col - check if can reach cur cell, at cheaper cost from the cell to the left.
                if (col > 0) {
                    ans = Math.min(ans, tabu[row][col - 1]);
                }

//                update cur cell with the min cost to reach it plus its own cost.
                tabu[row][col] = ans + grid[row][col];
            }
        }

//        time: O2n*m - initialized memo to -1, traverse min path sum.
//        space:On*m - memoization.
        return tabu[n - 1][m - 1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition - no need to memo entire grid, enoghe to store min way of each row above - and compare what cheapest - to get from row
* above or from cell to the left. */
class A15_MinimumPathSum_64_bottom_up_Om_space {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] tabu = new int[m]; //store the min cost to reach each cell at row in 1D array - prefix sum array of rows.

        // initialize the first row.
        tabu[0] = grid[0][0];
        for (int col = 1; col < m; col++) {
            tabu[col] = grid[0][col] + tabu[col - 1];
        }

        for (int row = 1; row < n; row++) {
            // update first cell in cur row
            tabu[0] += grid[row][0];

            for (int col = 1; col < m; col++) {
                tabu[col] = grid[row][col] + Math.min(tabu[col], tabu[col - 1]); //each time store lowest path to cell - from above or from the left.
            }
        }

        return tabu[m - 1];
    }
}
//        time: O1n*m - traverse grid[][] exactly once.
//        space:Om - memoization of only col.

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A15_MinimumPathSum_64_bottom_up_Om_space_inverted_for_practice {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] tabu = new int[m];

        for (int row = n - 1; row >= 0; row--) {
            for (int col = m - 1; col >= 0; col--) {

                if (row == n - 1 && col != m - 1) {
                    tabu[col] = grid[row][col] + tabu[col + 1];
                }
                else if (col == m - 1 && row != n - 1){
                    tabu[col] = grid[row][col] + tabu[col];
                }
                else if (col != m - 1 && row != n - 1){
                    tabu[col] = grid[row][col] + Math.min(tabu[col], tabu[col + 1]);
                }

                else tabu[col] = grid[row][col];
            }
        }
        return tabu[0];
    }
}