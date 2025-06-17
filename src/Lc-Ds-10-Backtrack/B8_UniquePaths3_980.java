/*problem:
return number of steps from start to end cell, after walk on all cell that are non-obstacle paths.
* -1==obstacle cannot walk
* 0 ==empty square
* 1 ==start point
* 2 ==end point
constraint: move only at 4 directions */

public class B8_UniquePaths3_980 {
    int rowSize;
    int colSize;
    int[][] grid;
    int steps_count;

    public int uniquePathsIII(int[][] grid) { //START here

//        update global vars:
        this.grid = grid;
        this.rowSize = grid.length;
        this.colSize = grid[0].length;
        this.steps_count = 0; // ans - how many steps.

        int start_row = 0;
        int start_col = 0;
        int non_obstacles = 0; //represent the number of cells must step on before reach end line.

//        find start point and count number of clear cells - that must walk on.
        for (int row = 0; row < rowSize; ++row)
            for (int col = 0; col < colSize; ++col) {
                int cell = grid[row][col];

                if (cell >= 0){
                    non_obstacles += 1;
                }
                if (cell == 1) {
                    start_row = row;
                    start_col = col;
                }
            }

        backtrack(start_row, start_col, non_obstacles);

//        n==rowSize, m==colSize
//        time:  O(3^(m*n))-Although technically we have 4 directions to explore at each step, we came from 1 so remain 3.
//        space: O(m*n) - Thanks to the in-place technique, we did not use any additional memory to keep track of the state. but recursion can grow to On*m - entire grid size.
        return this.steps_count;
    }


    protected void backtrack(int row, int col, int remain) {
        // base case - successful reach end, return.
        if (this.grid[row][col] == 2 && remain == 1) { //y remain==1 and not 0? because still need to do that step.
            this.steps_count += 1;
            return;
        }

        // mark cur cell as visited.
        int temp = grid[row][col]; //save that value - so can return it later when backtrack.
        grid[row][col] = -10; //-10 represent visited cell.
        remain -= 1; // we now have one less square to visit

        // explore the 4 potential directions around
        int[] row_offsets = {0, 0, 1, -1};
        int[] col_offsets = {1, -1, 0, 0};
        for (int i = 0; i < 4; ++i) {
            int nextRow = row + row_offsets[i];
            int nextCol = col + col_offsets[i];

//            out of grid boundary - prune.
            if (    0 > nextRow || nextRow >= this.rowSize ||
                    0 > nextCol || nextCol >= this.colSize){

                continue;
            }

//            obstacle or seen - prune.
            if (grid[nextRow][nextCol] < 0)
                continue;

            backtrack(nextRow, nextCol, remain);
        }

        // like every backtrack - unmark the square after the visit (=cancel the "visited" sign), so can explore next time different path
        grid[row][col] = temp;
    }
}