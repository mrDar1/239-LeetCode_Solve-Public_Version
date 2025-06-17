import java.util.LinkedList;
import java.util.Queue;

//input 4th: matrix.
public class A12_01Matrix_542 {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] updateMatrix(int[][] mat) {//start here. // Method to update the matrix with minimum distance to nearest zero
        m = mat.length;   //size of int[x][]
        n = mat[0].length;//size of int[][x]

        Queue<State> queue = new LinkedList<>();
        boolean[][] seen = new boolean[m][n];

        // Adding all zeros to the queue. marking them as seen and initial step to 1. why 1? because its only the Q not ans, and at next step val==1 we copy number of step from last. and then incremenet it for the next node.
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] == 0) {
                    queue.add(new State(row, col, 1));
                    seen[row][col] = true;
                }
            }
        }

        // Breadth-first search to update the distances
        while (!queue.isEmpty()) {
            State currstate = queue.remove();
            int row = currstate.row, col = currstate.col, steps = currstate.steps;

            // Check all four directions
            for (int[] direction: directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];

                // Check if the next position is valid and not visited
                if (valid(nextRow, nextCol, mat) && !seen[nextRow][nextCol]) {
                    seen[nextRow][nextCol] = true;
                    queue.add(new State(nextRow, nextCol, steps + 1));
                    mat[nextRow][nextCol] = steps;
                }
            }
        }

//        time and space: O(1n*1m) - only visited each square once. and doest constant work on it.
        return mat;
    }

    public boolean valid(int row, int col, int[][] mat) {
        return 0 <= row && row < m && 0 <= col && col < n && mat[row][col] == 1;
    }
}
//class State { //us at A10_ShortestPathinBinaryMatrix_1091
//    int row;
//    int col;
//    int steps;
//    State(int row, int col, int steps) {
//        this.row = row;
//        this.col = col;
//        this.steps = steps;
//    }
//}
