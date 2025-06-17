/*problem:
* Return fastest time until reach the bottom right square (n - 1, n - 1) if start at the top left square (0, 0).
 * constraint: can move only at 4 directions.
 * constraint: can move infinite distance in 0 time.
 * constraint: grid is square (size n * n).
 * each grid[i][j] represent its height, can only reach higher height when wait grid[i][j] time - so as long
 * as time pass can move to more elements but target is to wait min time!! */

/*solutions:
 * 1st - binary search of min threshold.
 * 2nd - same as 1st, but changed framework with +1 - really hate that method but good to know, as it common...
 *      also use here different way of move 4 directions DFS. */

public class B15_SwiminRisingWater_778 {
/*intuition:
* use binary search of min-threshold: each time check if curr time is possible to traverse to last point of
* grid, use DFS each time. */

/*complexity:
* grid size: n^2.
* time: On^2 * log n : o log n - binary search each "mid", inside each BS doing On^2-DFS, at worst case traverse all exactly once to find path.
* space: O2* n^2 - 1-n^2-recursion DFS call, 1n^2-visited[][]. */

    private static final int[][] direcrions = {{1,0}, {-1, 0}, {0,1} , {0,-1}};

    public int swimInWater(int[][] grid) { //START HERE
        int left = 0;
        int right = (grid.length * grid.length) - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(grid, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean isValid(int[][] grid, int t) {
        boolean[][] visited = new boolean[grid.length][grid.length];
        return dfs(grid, 0, 0, t, visited);
    }

    private boolean dfs(int[][] grid, int curRow, int curCol, int t, boolean[][] visited) {
//        out of grid boundary's, or already visited there, or mid is too small prune brunch:
        if (    curRow < 0 ||
                curCol < 0 ||
                curRow >= grid.length ||
                curCol >= grid.length ||
                visited[curRow][curCol] ||
                grid[curRow][curCol] > t) { //t==mid, check if can do it in mid/t time.
            return false;
        }

        // reach end is Possible!
        if (curRow == grid.length - 1 && curCol == grid.length - 1) {
            return true;
        }

        visited[curRow][curCol] = true;
        for (int[] dir : direcrions) {
            int nextRow = curRow + dir[0];
            int nextCol = curCol + dir[1];

            if (dfs(grid, nextRow, nextCol, t, visited)) {
                return true;
            }
        }
        return false;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B15_SwiminRisingWater_778_less_like_framework {
    private static final int[] DIRS = {0, 1, 0, -1, 0};

    public int swimInWater(int[][] grid) { //START HERE
        int left = 0;
        int right = (grid.length * grid.length) - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (canSwim(grid, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canSwim(int[][] grid, int t) {
        boolean[][] visited = new boolean[grid.length][grid.length];
        return dfs(grid, 0, 0, t, visited);
    }

    private boolean dfs(int[][] grid, int curRow, int curCol, int t, boolean[][] visited) {
//        out of grid boundary's, prune brunch:
        if (curRow < 0 || curRow >= grid.length || curCol < 0 || curCol >= grid.length || visited[curRow][curCol] || grid[curRow][curCol] > t) {
            return false;
        }

        // reach end isPossible!
        if (curRow == grid.length - 1 && curCol == grid.length - 1) {
            return true;
        }

        visited[curRow][curCol] = true;
        for (int i = 0; i < 4; i++) {
            int nextRow = curRow + DIRS[i];
            int nextCol = curCol + DIRS[i + 1];

            if (dfs(grid, nextRow, nextCol, t, visited)) {
                return true;
            }
        }
        return false;
    }
}