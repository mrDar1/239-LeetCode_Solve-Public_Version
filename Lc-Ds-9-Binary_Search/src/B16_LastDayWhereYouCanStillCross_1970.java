import java.util.LinkedList;
import java.util.Queue;

/*problem:
return the max number of days to reach bottom row.
constraint: can choose which col to start and which col to end - but must start from top row and finish at bottom row,
in longest time possible.

signature:
row==number of gird rows (1 indexed)
col==number of gird cols (1 indexed)
cells[][]== at each day - which cell will be flooded (1 indexed). */


/* Solutions:
1st - binary search of max-threshold, with preorder-DFS to explore matrix     ----      my favorite!!
2nd - binary search of max-threshold, with BFS to explore matrix.
3rd - same as 2nd, but changed BinarySearch framework with +1 - really hate that method but good to know, as it common... */

/* **************************** */
/* **************************** */
/* **************************** */
/* **************************** */

class B16_LastDayWhereYouCanStillCross_1970_DFS {
    /*intuition:
     * use binary search of max-threshold. each time check if can make the quest at "mid" time.
     * if succeed - try again with more days.
     * if fail - try again with less days.
     *
     * the "check" method:
     * usually use "seen[][]", here to save space, simply mark as -1, all seen cells.
     * at first, "fill" all cells with water up to "mid" day.
     * then try all paths until successful/fail reach bottom. */
    private final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int latestDayToCross(int row, int col, int[][] cells) { //start here
        int left = 1; //constraint - 2D array is 1-indexed!!!
        int right = row * col; //cannot be bigger that last inside matrix.

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCross(row, col, cells, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean canCross(int row, int col, int[][] cells, int curDay) {
        int[][] grid = new int[row][col]; //copy of "cells"; remember in java, initialize all to 0, represent land.

//        "fill" water at relevant cells up until curDay.
        for (int i = 0; i < curDay; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1; //why -1? -since its 1-index! not regular 0-index...
        }

//        if can finish the journey from any start point at upper row - cant stop and return true.
        for (int i = 0; i < col; ++i) {
            if (grid[0][i] == 0 && dfs(grid, 0, i, row, col)) { //why == 0? journey can only success when start from land.
                return true;
            }
        }
        return false;

    }

    private boolean dfs(int[][] grid, int curRow, int curCol, int row, int col) {
//    out of grid boundary's
        if (    curRow < 0 ||
                curCol < 0 ||
                curRow >= row ||
                curCol >= col ||
                grid[curRow][curCol] != 0) {
            return false;
        }

//        at last row - success!
        if (curRow == row - 1){
            return true;
        }

//        mark cur location as "Seen":
        grid[curRow][curCol] = -1; // mark -1: instead use seen[][] -1 to save space.

//        keep explore:
        for (int[] dir : directions){
            int nextRow = curRow + dir[0];
            int nextCol = curCol + dir[1];

            if (dfs(grid, nextRow, nextCol, row, col)){
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

public class B16_LastDayWhereYouCanStillCross_1970 {
    private final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int latestDayToCross(int row, int col, int[][] cells) { //start here
        int left = 1; //constraint - 2D array is 1-indexed!!!
        int right = row * col; //cannot be bigger that last inside matrix.

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCross(row, col, cells, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean canCross(int row, int col, int[][] cells, int curDay) {
        int[][] grid = new int[row][col]; //copy of "cells", each day "fill" relevant cell with water; remember in java, initialize all to 0, represent land.
        Queue<int[]> queue = new LinkedList<>();

//        "fill" water at relevant cells up till curDay.
        for (int i = 0; i < curDay; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1; //why -1? -since its 1-index! not regular 0...
        }

//        enqueue (=add), all cells in upper row - as possibilities to start quest to bottom row.
        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 0) {
                queue.offer(new int[]{0, i});
                grid[0][i] = -1; //after enqueue, mark as "seen". usually use seen[][] - here to save space, simply mark as -1.
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int curRow = cur[0];
            int curCol = cur[1];
            if (curRow == row - 1) { //reach bottom!
                return true;
            }

            for (int[] dir : directions) {
                int nextRow = curRow + dir[0];
                int nextCol = curCol + dir[1];

                if (    nextRow >= 0 &&
                        nextCol >= 0 &&
                        nextRow < row &&
                        nextCol < col &&
                        grid[nextRow][nextCol] == 0) {

                    queue.offer(new int[]{nextRow, nextCol});
                    grid[nextRow][nextCol] = -1; //after enqueue, mark as "seen". usually use seen[][] - here to save space, simply mark as -1.
                }
            }
        }

        return false;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B16_LastDayWhereYouCanStillCross_1970_less_liked_bs_framework {
    public int latestDayToCross(int row, int col, int[][] cells) { //start here
        int left = 1; //constraint - 2D array is 1-indexed!!!
        int right = row * col; //cannot be bigger that last inside matrix.

        while (left < right) {
            int mid = right - (right - left) / 2;

            if (canCross(row, col, cells, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean canCross(int row, int col, int[][] cells, int day) {
        int[][] grid = new int[row][col];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < day; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }

        for (int i = 0; i < col; i++) {
            if (grid[0][i] == 0) {
                queue.offer(new int[]{0, i});
                grid[0][i] = -1;
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            if (curRow == row - 1) {
                return true;
            }

            for (int[] dir : directions) {
                int newRow = curRow + dir[0];
                int newCol = curCol + dir[1];

                if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = -1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return false;
    }
}
