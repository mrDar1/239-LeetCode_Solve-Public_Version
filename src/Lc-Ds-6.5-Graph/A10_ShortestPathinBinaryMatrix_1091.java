import java.util.LinkedList;
import java.util.Queue;
//only solution of LCDS
public class A10_ShortestPathinBinaryMatrix_1091 {
    int n;
    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};//8 posible directions
    public int shortestPathBinaryMatrix(int[][] grid) {//start here
        if (grid[0][0] == 1) {//edge case - cannot start
            return -1;
        }

        n = grid.length;

        // more convenient to use a 2d array instead of a set for seen
        boolean[][] seen = new boolean[n][n];
        seen[0][0] = true;
        Queue<State> queue = new LinkedList<>(); //keep track of progress
        queue.add(new State(0, 0, 1)); // row, col, steps

        while (!queue.isEmpty()) {
            State currstate = queue.poll();

            int row = currstate.row, col = currstate.col, steps = currstate.steps;
            if (row == n - 1 && col == n - 1) { //if we're at the last - return ans number of steps and finish.
                return steps;
            }

            for (int[] curdirection: directions) {
                int nextRow = row + curdirection[0];
                int nextCol = col + curdirection[1];

                if (valid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]) {
                    seen[nextRow][nextCol] = true;
                    queue.add(new State(nextRow, nextCol, steps + 1));
                }
            }
        }
//        time & space: On^2 - number of nodes.
        return -1;
    }

    public boolean valid(int row, int col, int[][] grid) {
        return 0 <= row && row < n && 0 <= col && col < n && grid[row][col] == 0;
    }
}
class State {
    int row;
    int col;
    int steps;
    State(int row, int col, int steps) {
        this.row = row;
        this.col = col;
        this.steps = steps;
    }
}