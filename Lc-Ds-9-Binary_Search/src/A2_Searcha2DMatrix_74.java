/*problem:
* search in increasing binary matrix
* with duplicates!
* return true if in matrix */

/*2 solutions:
* both same, second more elegant.*/

public class A2_Searcha2DMatrix_74 {
    public static void main(String[] args) {
        Solution_A2_Searcha2DMatrix_74 obj_74 = new Solution_A2_Searcha2DMatrix_74();

        int target1 = 3;
        int[][] matrix1 = { { 1,  3,  5,  7},
                            {10, 11, 16, 20},
                            {23, 30, 34, 60}};
        System.out.println(obj_74.searchMatrix_1st(matrix1, target1)); // Output: true

        int target2 = 13;
        int[][] matrix2 = { { 1,  3,  5,  7},
                            {10, 11, 16, 20},
                            {23, 30, 34, 60}};
//        System.out.println(obj_74.searchMatrix_1st(matrix2, target2)); // Output: false
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A2_Searcha2DMatrix_74 {
    public boolean searchMatrix_1st(int[][] matrix, int target) {
        int lenOfCol = matrix.length;
        int lenOfRow = matrix[0].length;
        int left = 0;
        int right = lenOfCol * lenOfRow - 1;

//        System.out.println("len of row: " + lenOfRow + " len of column:" + lenOfCol);

        while (left <= right){
            int mid = left + ((right - left) / 2);
            int row = mid / lenOfRow;
            int col = mid % lenOfRow;
//            how come "col = mid % lenOfRow"  work?
//            uncomment and see:
//            System.out.println("mid : " + mid + ". row : " + row + ". col : " + col + ". value: " + matrix[row][col]);
//            dont forget, len is 11, so half of it, in floor-division is 5!

            if (matrix[row][col] == target){
                return true;
            }

            if (matrix[row][col] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }

        }
//        time: O (log (n*m)), n==lenOfCol, m==lenOfRow.
//        space:O1
        return false; //if reach here - value not found...
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public boolean searchMatrix_2nd(int[][] matrix, int target) {
        int lenOfCol = matrix.length;
        int lenOfRow = matrix[0].length;

//        edge case:
        if (lenOfCol == 0) {
            return false;
        }

        // binary search
        int left = 0;
        int right = lenOfCol * lenOfRow - 1;
        int midIdx;
        int midValue;

        while (left <= right) {
            midIdx = (left + right) / 2;
            midValue = matrix[midIdx / lenOfRow][midIdx % lenOfRow];

            if (target == midValue)
                return true;
            else {
                if (target < midValue)
                    right = midIdx - 1;
                else
                    left = midIdx + 1;
            }
        }
        return false;
    }
}