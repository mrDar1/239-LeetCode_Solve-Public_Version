import java.util.HashSet;

/*problem:
* on chessboard, size n*n, place n queens, that no queens threat each other
* return number of possible solution. */

/*solutions:
* 1st - backtrack DFS with global vars
* 2nd - same, no globals. */

/* motivation:
its easy to check if queens threat at same row or col, but how to check diagonals? -use this neat trick:
Diagonal = row - col 		(=all will be 0) --- see picture!
AntiDiagonal = row + col	(=all will be n)--- see picture!  */


/*intuition:
* recursion call each time with row+1.
* each time, place queen at all possible col of that row - if threaten - prune.
* if not threaten continue to next row.
*
* how can know if threat?
* row can never threat each other - as each recursion in different row.
* with col - use hashset to store the "taken" col number
* and use "motivation" trick to do the same with Diagonal and AntiDiagonal */

class A7_NQueens2_52_globals {
    int squareSize;
    HashSet<Integer> colHash;
    HashSet<Integer> diagonal;
    HashSet<Integer> antiDiagonal;

    public int totalNQueens(int n) { //START HERE
        this.colHash = new HashSet<>();
        this.diagonal = new HashSet<>();
        this.antiDiagonal = new HashSet<>();
        this.squareSize = n;

        return backtrack(0);
    }

    private int backtrack(int row){

//        base case- successfully reach end, no queen threaten another:
//        note: if successful at end of (row-1) can stop and return "ans+1",
//        but, to get more elegant, concise code, instead check if success at start of next iteration.
//        (when actually at that point, we are "out-of-boundary" from chessboard matrix).
        if (row == squareSize){
            return 1;
        }

        int ans = 0;

        for (int col = 0; col < squareSize ; col++) {
            int curDiagonal     = row - col;
            int curAntiDiagonal = row + col;

            //if there's another queen threaten us - prune brunch, by continue and try next coll.
            if (colHash.contains(col)||
                diagonal.contains(curDiagonal) ||
                antiDiagonal.contains(curAntiDiagonal)){

                continue;
            }

//            "add" queen to board.
            colHash.add(col);
            diagonal.add(curDiagonal);
            antiDiagonal.add(curAntiDiagonal);

//            System.out.println(colHash);
//            System.out.println(diagonal);
//            System.out.println(antiDiagonal);
//            System.out.println("*****************");

//            continue to next row, with cur board state
            ans += backtrack(row + 1);

//            "remove" queen from the board - after we seen all solution with that path.
            colHash.remove(col);
            diagonal.remove(curDiagonal);
            antiDiagonal.remove(curAntiDiagonal);
        }

//        time:  On! - real ans not known, but consider it as n!
//        space: O4n==On:   O3n-each set (got 3) can grow up to size n.
//                          O1n - recursion call stack - can grow up to n - depth of recursion (until reach row n)
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

public class A7_NQueens2_52 {
    public int totalNQueens(int n) { //START HERE
        int row = 0;
        int squareSize = n;
        HashSet<Integer> col = new HashSet<>();
        HashSet<Integer> diagonal = new HashSet<>();
        HashSet<Integer> antiDiagonal = new HashSet<>();
        return backtrack(squareSize ,row, col, diagonal, antiDiagonal);
    }
    private int backtrack(int squareSize,
                            int row,
                            HashSet<Integer> cols,
                            HashSet<Integer> diagonals,
                            HashSet<Integer> antiDiagonals) {

//        base case- successfully reach end, no queen threaten another:
//        note: if successful at end of (row-1) can stop and return "ans+1",
//        but, to get more elegant, concise code, instead check if success at start of next iteration.
//        (when actually at that point, we are "out-of-boundary" from chessboard matrix).
        if (row == squareSize){
            return 1;
        }

        int ans = 0;

        for (int col = 0; col < squareSize ; col++) {
            int curdiagonal = row - col;
            int curantiDiagonal = row + col;

            //if there's another queen threaten us - prune brunch, by continue and try next coll.
            if (cols.contains(col)||
                diagonals.contains(curdiagonal) ||
                antiDiagonals.contains(curantiDiagonal)){

                continue;
            }

//            "add" queen to board.
            cols.add(col);
            diagonals.add(curdiagonal);
            antiDiagonals.add(curantiDiagonal);

//            continue to next row, with cur board state
            ans += backtrack(squareSize, row + 1, cols, diagonals, antiDiagonals);

//            "remove" queen from the board - after we seen all solution with that path.
            cols.remove(col);
            diagonals.remove(curdiagonal);
            antiDiagonals.remove(curantiDiagonal);
        }

//        time:  On! - real ans not known, but consider it as n!
//        space: O4n==On:   O3n-each set (got 3) can grow up to size n.
//                          O1n - recursion call stack - can grow up to n - depth of recursion (until reach row n)
        return ans;
    }
}