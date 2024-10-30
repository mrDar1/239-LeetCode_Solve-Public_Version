// Optimize from last time, using Bitwise instead of "seen" hashset.
// please do read "A7_NQueens2_52" from BackTrack chapter before.

public class A8_N_Queens2_use_Bitwise_52 {
    private int size;
    int cols;
    int diagonals;
    int antiDiagonals;

    public int totalNQueens(int n) { //START HERE
        this.size = n;
        this.cols = 0;
        this.diagonals = 0;
        this.antiDiagonals = 0;

        return backtrack(0);
    }

    private int backtrack(int row) {
//        base case - successfully reach end, no queen threaten another
        if (row == size) {
            return 1;
        }

        int ans = 0;
        for (int col = 0; col < size; col++) {
            int currDiagonal = 1 << (row - col + size); //why add "size"? - to avoid negative numbers that can cause problem in Bitwise.
            int currAntiDiagonal = 1 << (row + col);
            int currCol = 1 << col;

            // if bits are set == we got queen that threats another - prune.
            if (    (cols & currCol) != 0 ||
                    (diagonals & currDiagonal) != 0 ||
                    (antiDiagonals & currAntiDiagonal) != 0) {
                continue;
            }

            // "add" queen to the board - by set relevant bits.
            cols ^= currCol;
            diagonals ^= currDiagonal;
            antiDiagonals ^= currAntiDiagonal;

//            explore next row:
            ans += backtrack(row + 1);

            // "remove" queen from board, after explored all valid paths using above function call
            cols ^= currCol;
            diagonals ^= currDiagonal;
            antiDiagonals ^= currAntiDiagonal;
        }
//        time: the same as hash, but Bitwise operators much faster. (consider n! but unknown)
//        space: O1n: 1n - recursion call stack.
//                    O1 - last time needed O3n for hashsets of "cols" "diagonals" "antiDiagonals"
//                          with BitWise, can use 3 int for same task.

        return ans;
    }
}