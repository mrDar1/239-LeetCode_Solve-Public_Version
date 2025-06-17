/*problem:
return true, if can assemble word from given matrix.
from each cell can only move in 4 directions.
cannot go back.
can use each cell once. */

/*intuition:
* traverse iterativly until find the first char of "target word".
* when find it, start backtrack scan to all 4 directions - try and assemble the word.
* each path that its next char is not as in target - can be prune (constraint order matter) */

public class A8_WordSearch_79 {
    int n; //row length
    int m; //col length
    int[][] directions = new int[][] { {0,1}, {1,0}, {-1,0}, {0,-1} };
    boolean[][]seen;
    String target; //target word - return true if can assemble it.


    public boolean exist(char[][] board, String word) { //START HERE
        this.n = board.length;
        this.m = board[0].length;
        this.target = word;
        this.seen = new boolean[n][m];


//        iterative scan for first char at "target word" - when found start backtrack scan to all 4 directions.
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (board[row][col] == target.charAt(0)){
                    seen[row][col] = true;
                    if (backtrack(row, col, board, 1)){
                        return true;
                    }
                    seen[row][col] = false;
                }
            }
        }
        return false; //exhausted search and didnt find...
    }
    private boolean backtrack(int row, int col, char[][] board, int i) { //i==represent the path did on word.len(), advance it only when found next char.

        //base case - successfully assemble entire word:
        if (i == target.length() ){
            return true;
        }

//        DFS/backtrack scann all 4 directions:
        for (int[] dir : directions){
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (valid(nextRow,nextCol) && !seen[nextRow][nextCol] ){
                if (board[nextRow][nextCol] == target.charAt(i)){
                    seen[nextRow][nextCol] = true;
                    if(backtrack(nextRow, nextCol, board, i+1)){
                        return true;
                    }
                    seen[nextRow][nextCol] = false;
                }
            }
        }
        return false;
    }

    private boolean valid(int nextRow, int nextCol) { //return true when not out of bounds.
        return (nextRow < n &&
                nextCol < m &&
                nextCol >= 0 &&
                nextRow >= 0);
    }
}


/* complexity , long math! here is approximately:
n==board.length;
m==board[0].length;
L==word.length;

time:  O n*m*3^L:
        n*m - iterative search for first char at "target word"
        barcktrack: 3^L-each node can have 3 children not consider the one we came from, each of them at depth of L.

space: O L*n*m:
        L-recursion call stack depth,
        n*m-bolean[] seen. */
