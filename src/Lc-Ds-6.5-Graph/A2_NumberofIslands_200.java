//didnt get that one..
//input - 4th matrix
public class A2_NumberofIslands_200 {
//    LC-DS solution:
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][] seen;
    public int numIslands(char[][] grid) {
        int ans = 0;
        m = grid.length;   //will give size of char[x][]
        n = grid[0].length;//will give size of char[][x]
        seen = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1' && !seen[row][col]) {
                    ans++;
                    seen[row][col] = true;
                    dfs(row, col, grid);
                }
            }
        }
//      time: O (nxm) - m=4 as it said we only got 4 ptr to each node.
        return ans;
    }
    public boolean valid(int row, int col, char[][] grid) {
        return 0 <= row && row < m && 0 <= col && col < n && grid[row][col] == '1';
    }
    public void dfs(int row, int col, char[][] grid) {
        for (int[] direction: directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (valid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]) {
                seen[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, grid);
            }
        }
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    Lc from page solution:
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;   //will give size of char[x][]
        int nc = grid[0].length;//will give size of char[][x]

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
    public int numIslands_from_page(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;   //will give size of char[x][]
        int nc = grid[0].length;//will give size of char[][x]
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }
//        time:  O(rxc) - row x column
//        space: O(rxc) - in case that the grid map
//is filled with lands where DFS goes by (r x c) deep.
        return num_islands;
    }
}

