/*problem:
* given grid[][], fill only with 1,0.
* return the area of largest square fill only with 1.
* square == perfect square! n*n size only not n*m.
* area == n*n == row*col == number of cell in the square. */

/* solutions:
* 1st-top-down
* 2nd-bottom up
* 3rd-bottom up - improve space.  */

//1st - top_down
public class B13_MaximalSquare_221 {
/*intuition: traverse grid[][] - start from top left and travel to right and down at this order.
* when find 1 - traverse its surrounding (=right,down, and diagonal) and save that size.
* repeat process and each time compare the maxSize */
    public int maximalSquare(char[][] matrix) { //START HERE
//        edge case:
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        Integer[][] memo = new Integer[rows][cols];
        int maxSquareSide = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    maxSquareSide = Math.max(maxSquareSide, maximalSquareFrom(matrix, memo, row, col));
                }
            }
        }
//        n,m==row,col
//        time:  O1n*m - traverse each exactly once and cach it!
//        space: On*m:  On*m-cached at memo,
//                      On+m-recursion(each time find 1 - got till end of row and end of col, and end of diagonal - cannot go more than that.)
        return maxSquareSide * maxSquareSide;
    }

    private int maximalSquareFrom(char[][] matrix, Integer[][] memo, int row, int col ) {
//        out of boundary
        if (row >= matrix.length || col >= matrix[0].length) {
            return 0;
        }

//        not 1 - prune brunch (=return 0)
        if (matrix[row][col] == '0') {
            return 0;
        }

//        already cached that val:
        if (memo[row][col] != null) {
            return memo[row][col];
        }

        int right    = maximalSquareFrom(matrix, memo, row, col + 1);
        int down     = maximalSquareFrom(matrix, memo, row + 1, col);
        int diagonal = maximalSquareFrom(matrix, memo, row + 1, col + 1);

        //why Math.min? since if 1 of them is 0 - its not perfect square. and cannot be count.
        //since use that "min" - will not get the area of square only the squareSide.
        memo[row][col] = 1 + Math.min(right, Math.min(down, diagonal));
        return memo[row][col];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd:
class B13_MaximalSquare_221_bottom_up {
//    same idea and complexity of top-down.
    public int maximalSquare(char[][] matrix) {
        //        edge case:
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxSquareSide = 0;

        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                if (matrix[row-1][col-1] == '1'){
                    dp[row][col] = Math.min(Math.min(dp[row][col - 1], dp[row - 1][col]), dp[row - 1][col - 1]) + 1;
                    maxSquareSide = Math.max(maxSquareSide, dp[row][col]);
                }
            }
        }
        return maxSquareSide * maxSquareSide;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd:
class B13_MaximalSquare_221_bottom_up_improved_space {
    public int maximalSquare(char[][] matrix) {
        //        edge case:
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] dp = new int[cols + 1];

        int maxSquareSide = 0;
        int prev = 0;

        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                int temp = dp[col];

                if (matrix[row - 1][col - 1] == '1') {
                    dp[col] = Math.min(Math.min(dp[col - 1], prev ), dp[col]) + 1;
                    maxSquareSide = Math.max(maxSquareSide, dp[col]);
                } else {
                    dp[col] = 0;
                }
                prev = temp;
            }
        }
        return maxSquareSide * maxSquareSide;
    }
}
//        n,m==row,col
//        time:  O1n*m - traverse each exactly once and cach it!
//        space: Om - cached at memo