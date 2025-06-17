import java.util.*;
/*problem:
Return the minimum effort required to travel from the top-left (0,0) cell to the bottom-right cell ((rows-1, columns-1)).
effort == only the single highest difference!
effort == |<cur location> - <next location>|
can move in 4 directions only.
constraint: no negative numbers. */

/*solutions (important are 1st, 4th, and 6.):
1st - DFS - recursion - with global vars (and detailed comments)
2nd - DFS - recursion - no global vars (exactly as 1st, for practice purpose)
3rd - backtrack - recursion - (no seen[][]) - work but fail submit for TLE... do not learn from it.
4th - BFS - with class "Pair" and Q.
5th - DFS - same as 4th, iterate with Stack - which give property of DFS, its work as for this particular question no matter the order.
6th - dijixtra , similar to BFS but only traverse once and find lowest "effort way"*/

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* for convenient - copy all to global shared vars.
* find left and right boundary. to find right 2 options:
*   1st put highest possible number as in constraint. (binary search log n search so fast that still work)
*   2nd traverse all matrix until find largest value - and put it as right boundary.
*
* binary search for threshold - the min "effort" that still possible.
* each binary search explore entire matrix from top and check if can reach end with curr_effort
* - if do - try again with less effort.
* - if fail -  try again with more effort.
* for each "explore" use "seen[][]" to avoid travel twice same place.*/

//1st: DFS_recursion_with_globals
public class A7_PathWithMinimumEffort_1631 {
    int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
    int rowSize;
    int colSize;
    int[][] heights;

    public int minimumEffortPath(int[][] heights) { //Start here
        this.heights = heights;
        this.rowSize = heights.length;
        this.colSize = heights[0].length;
        int left = 0;

        //find and set right boundary - highest point at matrix.
        int right = 0;
        for (int[] row : heights){
            for (int i : row){
                right = Math.max(right, i);
            }
        }
//        /*2 ways to find "right":
//        1st - traverse until find highest element at matrix (as above)
//        2nd - just put highest boundary - as log k is very fast!! */
//        int right = 1000000;


//        binary search until find threshold:
        while (left <= right){
            int mid = left + (right - left) /2; //remember: search for lowest effort possible, mid represent cur_effort.

            if (setIsEnd(mid)){      //return true if possible to reach end with cur effort.
                right = mid - 1;  //reach end!!! - but maybe can do it with less effort.
            }else {
                left = mid + 1;   //didnt reach end!!! - try again with bigger effort.
            }
        }
//        time: O2*n*m(log k) : O1n*m-find right boundary. O log k - binary search - that done - On*m to find path.
//        space: On*m - for stack and seen.
        return left;
    }

    private boolean setIsEnd(int mid) { //try all possibilities - return true if can reach end point with cur "effort", otherwise return false.
        boolean[][] seen = new boolean[rowSize][colSize]; //avoid explore path we already been.
        return IsEnd(0,0, mid,seen);
    }

    private boolean IsEnd(int curRow, int curCol, int mid, boolean[][] seen) {
//        base case - if reach end with cur "effort" return true:
        if (curRow == rowSize - 1 && curCol == colSize - 1){
            return true;
        }

        seen[curRow][curCol] = true;
        for (int[] direction : directions) { //constraint: can walk only 4 possibilities.
            int nextRow = curRow + direction[0];
            int nextCol = curCol + direction[1];

            if (isValid(nextRow, nextCol) && !seen[nextRow][nextCol]){
                int currentDifference = Math.abs(heights[nextRow][nextCol] - heights[curRow][curCol]);
                if (currentDifference <= mid) {
                    if (IsEnd(nextRow, nextCol, mid, seen)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isValid(int nextRow, int nextCol) { // return true if cur_location at valid location inside matrix.
        return (0 <= nextRow &&
                0 <= nextCol &&
                this.rowSize > nextRow &&
                this.colSize > nextCol);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd:
class A7_PathWithMinimumEffort_1631_DFS_recursion_no_globals { //beats 99% !!!!
    int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public int minimumEffortPath(int[][] heights) { //Start here
        int left = 0;
        int right = 0;
        for (int[] row : heights){
            for (int i : row){
                right = Math.max(right, i);
            }
        }

//        binary search until find threshold:
        while (left <= right){
            int mid = left + (right - left) /2;

            if (setIsEnd(mid, heights)){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
//        time: O2*n*m(log k) : O1n*m-find right boundary. O log k - binary search - that done - On*m to find path.
//        space: On*m - for stack and seen.
        return left;
    }

    private boolean setIsEnd(int mid, int[][] heights) {  //try all possibilities - return true if can reach end point with cur "effort", otherwise return false.
        int rowSize = heights.length;
        int colSize = heights[0].length;

        boolean[][] seen = new boolean[rowSize][colSize]; //avoid explore path we already been.
        return IsEnd(0,0, mid, rowSize, colSize, seen, heights);
    }

    private boolean IsEnd(int curRow, int curCol, int mid, int rowSize, int colSize, boolean[][] seen, int[][] heights) {
//        base case - if reach end with cur "effort" return true:
        if (curRow == rowSize - 1 && curCol == colSize - 1){
            return true;
        }

        seen[curRow][curCol] = true;
        for (int[] direction : directions) {
            int nextRow = curRow + direction[0];
            int nextCol = curCol + direction[1];

            if (valid(nextRow, nextCol, rowSize, colSize) && !seen[nextRow][nextCol]){
                int currentDifference = Math.abs(heights[nextRow][nextCol] - heights[curRow][curCol]);
                if (currentDifference <= mid) {
                    if (IsEnd(nextRow, nextCol, mid, rowSize, colSize, seen, heights)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean valid(int nextRow, int nextCol, int rowSize, int colSize) { // return true if cur_location at valid location at matrix.
        return ( 0 <= nextRow &&
                0 <= nextCol &&
                rowSize > nextRow &&
                colSize > nextCol);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/* why do brute force fail for TLE and DFS work?
backtrack do not use "seen[][]" to avoid travel places that has already explored. */
//3rd:
class A7_PathWithMinimumEffort_1631_brute_force_backtrack {
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[][] heights;
    int rowSize;
    int colSize;
    int maxSoFar = Integer.MAX_VALUE; //help pruning - each time pass it - skip to next round.


    public int minimumEffortPath(int[][] heights) {
        this.heights = heights;
        this.rowSize = heights.length;
        this.colSize = heights[0].length;
        return backtrack(0, 0, 0);
    }


    int backtrack(int curRow, int curCol, int maxDifference) {
//        base case - when reach end - return.
        if (curRow == this.rowSize - 1 && curCol == this.colSize - 1 ) {
            this.maxSoFar = Math.min(this.maxSoFar, maxDifference);
            return maxDifference;
        }

        int currentHeight = heights[curRow][curCol];
        this.heights[curRow][curCol] = 0;
        int minEffort = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int nextRow = curRow + this.directions[i][0];
            int nextCol = curCol + this.directions[i][1];

            if (isValid(nextRow, nextCol) && this.heights[nextRow][nextCol] != 0) {
                int currentDifference = Math.abs(this.heights[nextRow][nextCol] - currentHeight);
                int maxCurrentDifference = Math.max(maxDifference, currentDifference);

                if (maxCurrentDifference < this.maxSoFar) {
                    int result = backtrack(nextRow, nextCol, maxCurrentDifference);
                    minEffort = Math.min(minEffort, result);
                }
            }
        }
        heights[curRow][curCol] = currentHeight;
        return minEffort;
    }

    private boolean isValid(int nextRow, int nextCol) { // return true if cur_location at valid location inside matrix.
        return (0 <= nextRow &&
                0 <= nextCol &&
                this.rowSize > nextRow &&
                this.colSize > nextCol);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//4th
class A7_myimplement_PathWithMinimumEffort_1631_BFS {
    int rowSize;//rows len
    int colSize;//column len
    int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public int minimumEffortPath(int[][] heights) { //Start here
        this.rowSize = heights.length;
        this.colSize = heights[0].length;
        int left = 0;

        //find and set right boundary - highest point at matrix.
        int right = 0;
        for (int[] row : heights){
            for (int i : row){
                right = Math.max(right, i);
            }
        }
//        /*2 ways to find "right":
//        1st-traverse until find highest element at matrix (as above)
//        or just put highest boundary - as log k is very fast!! */
//        int right = 1000000;

//        binary search until find threshold:
        while (left <= right){
            int mid = left + (right - left) /2; //remember: search for lowest effort possible, mid represent cur_effort.

            if (IsEnd(mid, heights)){
                right = mid - 1;  //reach end!!! - but maybe can do it with less effort.
            }else {
                left = mid + 1;   //didnt reach end!!! - try again with bigger effort.
            }
        }
//        time: O2*n*m(log k) : O1n*m-find right boundary. O log k - binary search - that done - On*m to find path.
//        space: On*m - for stack and seen.
        return left;
    }
    private boolean IsEnd(int mid, int[][] heights) { //try all possibilities - return true if at end point, otherwise return false.
        Queue<MyPair> queue = new LinkedList<>();

        boolean[][] seen = new boolean[this.rowSize][this.colSize]; //avoid explore path we already been to.
        seen[0][0] = true;
        queue.offer(new MyPair(0,0));

        while (!queue.isEmpty()){
            MyPair cur = queue.poll();

            if (cur.row == rowSize - 1 && cur.col == colSize -1){
                return true;
            }

            for (int[] direction : directions){
                int nextrow = cur.row + direction[0];
                int nextcol = cur.col + direction[1];

                if (valid(nextrow, nextcol) && !seen[nextrow][nextcol]){
                    if (Math.abs(heights[nextrow][nextcol] - heights[cur.row][cur.col]) <= mid) {
                        seen[nextrow][nextcol] = true;
                        queue.offer(new MyPair(nextrow,nextcol));
                    }
                }
            }
        }
        return false; //if got here - didnt reach end so its false.. try again next round with bigger effort.
    }

    private boolean valid(int nextrow, int nextcol) { // return true if cur_location at valid location at matrix.
        return ( 0 <= nextrow &&
                0 <= nextcol &&
                rowSize > nextrow &&
                colSize > nextcol);
    }

    class MyPair{
        int row;
        int col;
        MyPair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//5th:
//DFS - not with recursion, but iterate with Stack.
class A7_PathWithMinimumEffort_1631_DFS_with_Stack_iterative {
    int rowSize;//rows len
    int colSize;//column len
    int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public int minimumEffortPath(int[][] heights) { //Start here
        this.rowSize = heights.length;
        this.colSize = heights[0].length;
        int left = 0;

        //find and set right boundary - highest point at matrix.
        int right = 0;
        for (int[] row : heights){
            for (int i : row){
                right = Math.max(right, i);
            }
        }
//        /*2 ways to find "right":
//        1st-traverse until find highest element at matrix (as above)
//        or just put highest boundary - as log k is very fast!! */
//        int right = 1000000;

//        binary search until find threshold:
        while (left <= right){
            int mid = left + (right - left) /2; //remember: search for lowest effort possible, mid represent cur_effort.

            if (IsEnd(mid, heights)){
                right = mid - 1;  //reach end!!! - but maybe can do it with less effort.
            }else {
                left = mid + 1;   //didnt reach end!!! - try again with bigger effort.
            }
        }
//        time: O2*n*m(log k) : O1n*m-find right boundary. O log k - binary search - that done - On*m to find path.
//        space: On*m - for stack and seen.
        return left;
    }
    private boolean IsEnd(int mid, int[][] heights) { //try all possibilities - return true if at end point, otherwise return false.
        Stack<MyPair> st = new Stack<>();

        boolean[][] seen = new boolean[this.rowSize][this.colSize]; //avoid explore path we already been to.
        seen[0][0] = true;
        st.push(new MyPair(0,0));

        while (!st.empty()){
            MyPair cur = st.pop();

            if (cur.row == rowSize - 1 && cur.col == colSize -1){
                return true;
            }

            for (int[] direction : directions){
                int nextrow = cur.row + direction[0];
                int nextcol = cur.col + direction[1];

                if (valid(nextrow, nextcol) && !seen[nextrow][nextcol]){
                    if (Math.abs(heights[nextrow][nextcol] - heights[cur.row][cur.col]) <= mid) {
                        seen[nextrow][nextcol] = true;
                        st.push(new MyPair(nextrow,nextcol));
                    }
                }
            }
        }
        return false; //if got here - didnt reach end so its false.. try again next round with bigger effort.
    }

    private boolean valid(int nextrow, int nextcol) { // return true if cur_location at valid location at matrix.
        return ( 0 <= nextrow &&
                 0 <= nextcol &&
                 rowSize > nextrow &&
                 colSize > nextcol);
    }

    class MyPair{
        int row;
        int col;
        MyPair(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//6th:
class A7_myimplement_PathWithMinimumEffort_1631_dijixtra {
    int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    class Pair {
        int row;
        int col;
        Integer difference;

        Pair(int row, int col, Integer difference) {
            this.row = row;
            this.col = col;
            this.difference = difference;
        }
    }

    public int minimumEffortPath(int[][] heights) { //START HERE
        int rowSize = heights.length;
        int colSize = heights[0].length;

        int[][] differenceMatrix = new int[rowSize][colSize];
        for (int[] eachRow : differenceMatrix) {
            Arrays.fill(eachRow, Integer.MAX_VALUE);
        }

        differenceMatrix[0][0] = 0;
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>((a, b) -> (a.difference.compareTo(b.difference)));
        boolean[][] seen = new boolean[rowSize][colSize];
        queue.add(new Pair(0, 0, differenceMatrix[0][0]));

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            seen[curr.row][curr.col] = true;

            if (curr.row == rowSize - 1 && curr.col == colSize - 1){
                return curr.difference;
            }

            for (int[] direction : directions) {
                int nextRow = curr.row + direction[0];
                int nextCol = curr.col + direction[1];

                if (isValid(nextRow, nextCol, rowSize, colSize) && !seen[nextRow][nextCol]) {
                    int currentDifference = Math.abs(heights[nextRow][nextCol] - heights[curr.row][curr.col]);
                    int maxDifference = Math.max(currentDifference, differenceMatrix[curr.row][curr.col]);

                    if (differenceMatrix[nextRow][nextCol] > maxDifference) {
                        differenceMatrix[nextRow][nextCol] = maxDifference;
                        queue.add(new Pair(nextRow, nextCol, maxDifference));
                    }
                }
            }
        }
        return differenceMatrix[rowSize - 1][colSize - 1];
    }

    private boolean isValid(int nextRow, int nextCol, int rowSize, int colSize) { // return true if cur_location at valid location at matrix.
        return (0 <= nextRow &&
                0 <= nextCol &&
                rowSize > nextRow &&
                colSize > nextCol);
    }
}