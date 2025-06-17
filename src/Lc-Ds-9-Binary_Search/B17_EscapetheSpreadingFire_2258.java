import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
/*problem:
return longest time can wait before start to run from fire (start from (0,0) rum to (row-1,col-1)).
can run & fire spread each minute by 1 step in 4 directions, fire spread to all directions simultaneously.

signature:
0 represents grass,
1 represents fire,
2 represents a wall that you and fire cannot pass through.*/

/*intuition:
* use binary search of max threshold, each time check with binary search if possible. */


//TODO !!! i belive i found why its not work!!!
//TODO if fire burn player - as in [0,0,0][0,1,0][0,0,0] - at next round catch player!
//TODO so just need each turn first check player move and only then fire move!


public class B17_EscapetheSpreadingFire_2258 {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] grid;
    int rowLen;
    int colLen;

    public int maximumMinutes(int[][] grid) { //start here
//        if (Arrays.deepEquals(grid, new int[][]{{0, 2, 0, 0, 1}, {0, 2, 0, 2, 2}, {0, 2, 0, 0, 0}, {0, 0, 2, 2, 0}, {0, 0, 0, 0, 0}})){
//            return 0;
//        }
        this.grid = grid;
        this.rowLen = grid.length;
        this.colLen = grid[0].length;

        // what time each cell caught on "fire" - computations.
        int[][] fireTime = new int[rowLen][colLen]; //what time each cell caught on "fire"
        Queue<int[]> fireQueue = new LinkedList<>();//bfs to calculate above.
        setFire_Time_and_Queue(fireTime,fireQueue); // populate both.


        // binary search - max threshold of delay time can be waited.
        int left = 0; //do not wait at all.
        int right = rowLen * colLen; //maybe can optimize it to rowLen or colLen but for now, go on bigger safe side.
//        later check, maybe its the max steps can make to reach end? or maybe some wall can make it longer:
//        int right = rowLen + colLen -2;
        int answer = -1; // constraint: if mission impossible: return -1.

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canEscape(mid, fireTime)) {
                answer = mid;
                left = mid + 1; //success! next time try to wait even more!
            } else {
                right = mid - 1;//fail! next try time with less wait time.
            }
        }

        return answer == (rowLen * colLen) ? (int) 1e9 : answer;
//        can also write:
//        return answer == (rowLen * colLen) ? 1_000_000_000 : answer;
    }

    private void setFire_Time_and_Queue(int[][] fireTime, Queue<int[]> fireQueue) {
//        initialize with large value - indicate its curTime not at fire.
        for (int[] row : fireTime) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // initialize locations of fire at start (minute 0)
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 1) {
                    fireQueue.offer(new int[]{i, j});
                    fireTime[i][j] = 0;
                }
            }
        }

        // BFS to mimic spread of fire to neighbors cell:
        while (!fireQueue.isEmpty()) {
            int[] cell = fireQueue.poll();
            int curRow = cell[0];
            int curCol = cell[1];
            int time = fireTime[curRow][curCol];

            for (int[] dir : directions) {
                int nextRow = curRow + dir[0];
                int nextCol = curCol + dir[1];

                if (isFireValid(nextRow, nextCol, time, fireTime)){
                    fireTime[nextRow][nextCol] = time + 1;
                    fireQueue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
    }

    private boolean canEscape(int delay, int[][] fireTime) { //mid represent number of time can wait before start escape==delay.
        int[][] playerTime = new int[rowLen][colLen];  //which time can player arrive at each cell.
        Queue<int[]> playerQueue = new LinkedList<>(); //help to build below int[][].
        for (int[] row : playerTime) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        playerQueue.offer(new int[]{0, 0});
        playerTime[0][0] = delay; //number of time at first cell - before start to run

        // BFS for player's movement
        while (!playerQueue.isEmpty()) {
            int[] cell = playerQueue.poll();

            int curRow = cell[0];
            int curCol = cell[1];
            int time = playerTime[curRow][curCol];

//            player burn on the way - prune brunch:
            if (time > fireTime[curRow][curCol]){
                return false;
            }

//            success reach end!
            if (curRow == rowLen - 1 && curCol == colLen - 1) {
                return ( time <= fireTime[curRow][curCol] ) ;  //reach end before fire.
            }

            for (int[] dir : directions) {
                int nextRow = curRow + dir[0];
                int nextCol = curCol + dir[1];

                if (isPlayerValid(nextRow , nextCol , time, playerTime, fireTime)) {
                    playerTime[nextRow][nextCol] = time + 1;
                    playerQueue.offer(new int[]{nextRow, nextCol});
                }
            }
        }

        return false;
    }

    private boolean isFireValid(int nextRow, int nextCol, int time, int[][] fireTime) {
        return( nextRow >= 0 &&
                nextCol >= 0 &&
                nextRow < rowLen &&
                nextCol < colLen &&
                grid[nextRow][nextCol] == 0 && //make sure not initialize to fire(=1) or wall(=2).
                fireTime[nextRow][nextCol] > time + 1 ); //make sure not already fired before now.
    }

    private boolean isPlayerValid(int nextRow, int nextCol, int time, int[][] playerTime, int[][] fireTime) {
        return( nextRow >= 0 &&
                nextCol >= 0 &&
                nextRow < rowLen &&
                nextCol < colLen &&
                grid[nextRow][nextCol] == 0  && //make sure initialize to gras(=0) and not fire(=1) or wall(=2).
                playerTime[nextRow][nextCol] > time + 1 && //make sure not already been there before now.
                fireTime[nextRow][nextCol] > time + 1 ); //make sure not already fired before now.
    }
}


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class B17_EscapetheSpreadingFire_2258_some_guy_who_succeed {
    int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    int[][] grid;
    int rowLen;
    int colLen;

    public int maximumMinutes(int[][] grid) {
        this.rowLen = grid.length;
        this.colLen = grid[0].length;

        List<int[]> initialFireLocation = new ArrayList<>();
        for (int i = 0; i < this.rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 1) {
                    initialFireLocation.add(new int[]{i, j});
                }
            }
        }

        int l = -1;
        int r = this.rowLen * colLen;

        while (l < r) {
            int mid = l + (r - l) / 2 + 1;
            if (reachable(grid, mid, initialFireLocation)) l = mid;
            else r = mid - 1;
        }

        return l == this.rowLen * colLen ? (int) 1e9 : l;
    }

    boolean reachable(int[][] grid, int mid, List<int[]> initialFireLocation) {
        int[][] copy = clone(grid);

        Queue<int[]> fire = new LinkedList<>();
        fire.addAll(initialFireLocation);
        while (!fire.isEmpty() && mid-- > 0) {
            if (canEscape(fire, copy)) return false;
        }

        Queue<int[]> person = new LinkedList<>();
        person.add(new int[]{0, 0});
        while (!person.isEmpty()) {
            boolean onFire = canEscape(fire, copy);
            if (canEscape(person, copy)) return true;
            if (onFire) return false;
        }
        return false;
    }

    // return true if it spreads to safehouse
    boolean canEscape(Queue<int[]> queue, int[][] grid) {
        int size = queue.size();

        while (size-- > 0) {
            int[] cell = queue.remove();

            for (int[] d : directions) {
                int nextRow = cell[0] + d[0] ;
                int nextCol = cell[1] + d[1];

                if (nextRow == rowLen - 1 && nextCol == colLen - 1) return true;

                if (nextRow >= 0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen && grid[nextRow][nextCol] == 0) {
                    grid[nextRow][nextCol] = -1;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
        return false;
    }

    int[][] clone(int[][] grid) {
        int[][] copy = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }
}