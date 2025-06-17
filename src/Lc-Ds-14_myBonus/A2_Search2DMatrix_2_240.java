/*problem:
* return true - if "target" found at given m x n int matrix.
* This matrix has the following properties:
* each row sorted in ascending way
* each column sorted in ascending way
https://leetcode.com/problems/search-a-2d-matrix-ii/  */

/*solutions:
* 1st - preorder DFS recursion - work, but too slow for big input.
* 2nd - like 1st, but optimize - explore only relevant brunches.
* 3rd - like 2nd, with iterative manner.
* 4th - like 3rd, no "continue", instead use "else" (cosmetic change)
* 5th - not effective - binary search on diagonals. */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* start from start point (0,0), 4 possibilities:
* 1st-out of bounds - return false.
* 2nd-cur == target - return true.
* 3rd-cur > target - we skip "target" return false.
* 4th-cur < target - recursive call 2 brunches simultaneously:
*       1st - ++col
*       2nd - ++row */
//1st:
class A2_Search2DMatrix_2_240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        return DFS(matrix,target,0,0);
    }

    private boolean DFS(int[][] matrix, int target, int curRow, int curCol) {
//        base case - out of boundary:
        if (curRow >= matrix.length || curCol >= matrix[0].length){
            return false;
        }

//        base case - found target:
        if (matrix[curRow][curCol] == target){
            return true;
        }

//        we're at bigger element - which mean we may skip it (not probably) or more likely it doesn't exist return false
        if (matrix[curRow][curCol] > target){
            return false;
        }

//        explore children:
        boolean increaseRow = DFS(matrix, target, curRow + 1,curCol);
        boolean increaseCol = DFS(matrix, target, curRow,curCol + 1);

        return (increaseRow || increaseCol);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*motivation:
traverse matrix from biggest element at row but smallest element at col.
each time move down-row/up-col in according manner. */

/* psudo:
* start at last row and first call - 4 possibilities:
* 1st-out of bounds - prune brunch.
* 2nd-cur == target - return true.
* 3rd-cur < target - move col up (+1).
* 4th-cur > target - move row down(-1).
* note if 3rd and 4th co-exist will discover that at next recursion-call,
* no need to run at another DFS brunch. I,E - use "else" */
//2nd:
class A2_Search2DMatrix_2_240_recursion_2nd_try {
    public boolean searchMatrix(int[][] matrix, int target) {
        return DFS(matrix,target,matrix.length - 1,0);
    }

    private boolean DFS(int[][] matrix, int target, int curRow, int curCol) {
//       1st base case - out of boundary:
        if (curRow < 0 || curCol >= matrix[0].length){
            return false;
        }

//       2nd found target:
        if (matrix[curRow][curCol] == target){
            return true;
        }

        boolean decreaseRow = false;
        boolean increaseCol = false;

//       3rd if cur < target: move col up (+1)
        if (matrix[curRow][curCol] < target){
            increaseCol = DFS(matrix, target, curRow ,curCol + 1);
        }

//       4th if cur > target: move row down(-1)
        else if (matrix[curRow][curCol] > target){ //note: if don't put "else" here will keep traverse row until reach negative boundary - but "target" never be there! waste of run-time.
            decreaseRow = DFS(matrix, target, curRow -1,curCol);
        }

        return (decreaseRow || increaseCol);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd:
/*intuition: as 2nd solution - implement with iteration */
class A2_Search2DMatrix_2_240_iterative_1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int curRow = matrix.length - 1;
        int curCol = 0;

        while (curRow >= 0 && curCol < matrix[0].length){
            if (matrix[curRow][curCol] == target) {
                return true;
            }

            if (matrix[curRow][curCol] > target){
                curRow--;
                continue;
            }

            if (matrix[curRow][curCol] < target){
                curCol++;
                continue;
            }
        }

        return false;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//4th:
class A2_Search2DMatrix_2_240_iterative_2_same_no_continue {
    public boolean searchMatrix(int[][] matrix, int target) {
        int curRow = matrix.length - 1;
        int curCol = 0;

        while (curRow >= 0 && curCol < matrix[0].length){
            if (matrix[curRow][curCol] > target){
                curRow--;
            }
            else if (matrix[curRow][curCol] < target){
                curCol++;
            }
            else { //got to be the right ans.
                return true;
            }
        }
//        assume: matrix[n][m]
//        time:  On+m
//        space: O1!
        return false;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//5th
/*notes:
 * try to optimize it and fail!
 * space: complexity: O1.
 * n==number of col.
 * time complexity: O(log n!) - we run 1 by 1 on each diagonal, as more square-shape would be faster. */
class A2_Search2DMatrix_2_240_binary_search_on_diagonals {
    public boolean searchMatrix(int[][] matrix, int target) {
//        edge case:
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        // iterate over matrix diagonals - for each, check both row and col!
        int shorterDim = Math.min(matrix.length, matrix[0].length); //to avoid overflow.
        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound   = binarySearch(matrix, target, i, true); //will scan col
            boolean horizontalFound = binarySearch(matrix, target, i, false);//will scan row
            if (verticalFound || horizontalFound) {
                return true;
            }
        }

        return false;
    }

    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix[0].length-1 : matrix.length-1;

        while (hi >= lo) {
            int mid = lo + (hi - lo)/2;

            if (vertical) { // scan col
                if (matrix[start][mid] < target) {
                    lo = mid + 1;
                } else if (matrix[start][mid] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            } else { //scan row
                if (matrix[mid][start] < target) {
                    lo = mid + 1;
                } else if (matrix[mid][start] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}

