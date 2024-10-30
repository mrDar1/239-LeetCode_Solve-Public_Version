import java.util.Arrays;

/*problem:
* given grid[][], return cheapest path from upper row to down row, can start at any position.
* each path cost the sum of all its cell val.
* constraint: matrix is always perfect square (n*n) (not n*m)
* constraint: grid[][] cell can be negative
* constraint: can only walk 3 ways: straight down, diagonal left-down or diagonal right-down*/

/*motivation: since grid[][] cell can be negative! - do not initialize memo[][] to -1 as regular,
but to NULL use Integer java wrapper */

/*solutions:
 * 1st-top down
 * 2nd-bottom-up, 2D memo (On*m space)
 * 3rd-bottom-up with 1D memo (Om space) */

// 1st-top down
public class A17_MinimumFallingPathSum_931 {
/*psudo:
* base cases - success reach end.
* prune - out of grid boundarys.
* each time explore 3 possibilities - mid(straight down), left,right
* cach the min of them and dont forget to add the curr value! */

    int n;
    int m;
    Integer[][]memo;

    public int minFallingPathSum(int[][] matrix) { //start here
        this.n = matrix.length;
        this.m = matrix[0].length;
        this.memo = new Integer[n][m];
        int minFallingSum = Integer.MAX_VALUE; //constraint: can choose any start point at first row, so try them all and compare with it.

        for (Integer[] arr : memo){ //note - it must be java wrapper Integer[][] and not int[][] to fill with null!!
            Arrays.fill(arr,null);
        }

        for (int curCol = 0; curCol < m ; curCol++) { //constraint: can choose any start point at first row, so try them all
            minFallingSum = Math.min(minFallingSum, dfs(0, curCol, matrix));
        }

//        time:  Onxm - each cell in matrix.
//        space: On+ Onxm - On-recursive call-max depth is no more than number of rows, Onxm-memo.
        return minFallingSum;
    }


    private int dfs(int row, int col, int[][] grid) {
//        base case - reach end point. later check if its cheapest path.
        if (row == n - 1){
            return grid[row][col];
        }

//      not valid - prune (case out of grid boundary)
        if (row < 0 || col < 0 || row >= n || col >= m){
            return Integer.MAX_VALUE;
        }

//        already cached - use it.
        if (memo[row][col] != null){
            return memo[row][col];
        }

        int mid = dfs(row + 1, col , grid);
        int leftdiagonal  = dfs(row + 1, col-1 , grid);
        int rightdiagonal = dfs(row + 1, col+1 , grid);

        memo[row][col] = Math.min(mid, Math.min(leftdiagonal, rightdiagonal)) + grid[row][col];
        return memo[row][col];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A17_MinimumFallingPathSum_931_tabulation {
    /*psudo:
     * start with base case if we are at last row - return the smallest of them.
     * each time send towards upper bound until reach first row.
     * when finish fill entire tabu[][] - traverse all its first row, find the min and return it. */
    public int minFallingPathSum(int[][] matrix) { //start here
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] tabu = new int[n + 1][m + 1]; // VERY IMPORTANT HERE IS +1!! for annoying edge case!

//        start iterating from last row and first col
        for (n -= 1; n >= 0; n--) {
            for (m = 0; m < matrix[0].length; m++) {
//                since here we do not have the recursion to fix outofbounds - be carfull on the edgeges!!
//                we will have special if for the corneres, than save lowest path out of 3 possible ways.
                if (m == 0) { //at left boundary - can only go straight down or diagonal right-down
                    tabu[n][m] = Math.min(tabu[n + 1][m], tabu[n + 1][m + 1]) + matrix[n][m];
                } else if (m == matrix[0].length - 1) { //at right boundary - can only go straight down or diagonal left-down
                    tabu[n][m] = Math.min(tabu[n + 1][m], tabu[n + 1][m - 1]) + matrix[n][m];
                } else { //at middle - can go 3 ways: straight down or diagonal left-down or diagonal right-down
                    tabu[n][m] = matrix[n][m] +
                                Math.min(tabu[n + 1][m],
                                Math.min(tabu[n + 1][m - 1], tabu[n + 1][m + 1]));
                }
            }
        }

//        traverse our first row of tabu[] and find the shortest path to reach bottom row - and return it.
        int minpathsum = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            minpathsum = Math.min(minpathsum, tabu[0][i]);
        }
//        time:  Onxm - each cell in matrix.
//        space: On+ Onxm - On-max depth of recursive call as row number, Onxm-memo.
        return minpathsum;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A17_MinimumFallingPathSum_931_tabulation_improvedspace {
    public int minFallingPathSum(int[][] matrix) {
        int[] dp = new int[matrix.length + 1];

        for (int row = matrix.length - 1; row >= 0; row--) {
            int[] currentRow = new int[matrix.length + 1];

            for (int col = 0; col < matrix.length; col++) {
                if (col == 0) {
                    currentRow[col] =
                            Math.min(dp[col], dp[col + 1]) + matrix[row][col];
                } else if (col == matrix.length - 1) {
                    currentRow[col] =
                            Math.min(dp[col], dp[col - 1]) + matrix[row][col];
                } else {
                    currentRow[col] = Math.min(dp[col],
                            Math.min(dp[col + 1], dp[col - 1])) + matrix[row][col];
                }
            }
            dp = currentRow;
        }

        int minFallingSum = Integer.MAX_VALUE;
        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingSum = Math.min(minFallingSum, dp[startCol]);
        }
//        time:  Onxm - each cell in matrix.
//        space: On - using two 1D arrays dp, and currentRow of size N.
        return minFallingSum;
    }
}