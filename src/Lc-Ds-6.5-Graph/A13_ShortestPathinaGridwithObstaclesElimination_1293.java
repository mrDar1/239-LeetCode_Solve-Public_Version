import java.util.LinkedList;
import java.util.Queue;
//didnt get that...
public class A13_ShortestPathinaGridwithObstaclesElimination_1293 {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestPath(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        Queue<State_r> queue = new LinkedList<>();
        queue.add(new State_r(0, 0, k, 0));
        boolean[][][] seen = new boolean[m][n][k + 1];
        seen[0][0][k] = true;

        while (!queue.isEmpty()) {
            State_r curstate = queue.remove();
            int row = curstate.row, col = curstate.col, remain = curstate.remain, steps = curstate.steps;

            if (row == m - 1 && col == n - 1) {// if got so far were at the end and return its value.
                return steps;
            }

            for (int[] direction: directions) {
                int nextRow = row + direction[0], nextCol = col + direction[1];

                if (valid(nextRow, nextCol)) {
                    if (grid[nextRow][nextCol] == 0) {
                        if ( !seen[nextRow][nextCol][remain]) {
                            seen[nextRow][nextCol][remain] = true;
                            queue.add(new State_r(nextRow, nextCol, remain, steps + 1));
                        }
                        // otherwise, it is an obstacle and we can only pass if we have remaining removals
                    } else if (remain > 0 && !seen[nextRow][nextCol][remain - 1]) {
                        seen[nextRow][nextCol][remain - 1] = true;
                        queue.add(new State_r(nextRow, nextCol, remain - 1, steps + 1));
                    }
                }
            }
        }
//        time and space: O(1n * 1m * 1k)
        return -1;
    }
    public boolean valid(int row, int col) {
        return 0 <= row && row < m && 0 <= col && col < n;
    }
}

class State_r {
    int row;
    int col;
    int remain; //how many obstacle remain that we can remove at cur stage.
    int steps; //steps we did so far.
    State_r(int row, int col, int remain, int steps) {
        this.row = row;
        this.col = col;
        this.remain = remain;
        this.steps = steps;
    }
}