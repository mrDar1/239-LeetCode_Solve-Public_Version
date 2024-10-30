import java.util.Arrays;
/*problem: find "LCS=longest Common Subsequence" of 2 strings.
Subsequence==contiguous section of the original arr/string that in the same order, but could be with deleted element.*/

/* nice problem, used in a real-world problem:
check diff between two files (diffing).
Git needs to do this when merging branches. It's also used in
genetic analysis (combined with other algorithms) as a measure of similarity between two genetic codes.

6 solutions
TODO my favorites are 2 and 4!!!!
* 1st top-down recursive memo - high complexity dumb way - not to use it!
* 2nd top-down optimized - readable.
* 3rd same as 2, shorted code by combine 2 edge case (out of bounds + cach - do that by initialize 1+bounds with -1)
* 4th bottom-up iterative - clean and short
* 5th bottom-up iterative - optimize space to minO(n,m) - hard! */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//1st approach - recrusion:
public class A9_LongestCommonSubsequence_1143 {
    private int[][] memo;
    private String text1;
    private String text2;

    public int longestCommonSubsequence(String text1, String text2) {
        // Make the memo big enough to hold the cases where the pointers
        // go over the edges of the strings.
        this.memo = new int[text1.length() + 1][text2.length() + 1];
        // We need to initialise the memo array to -1's so that we know
        // whether or not a value has been filled in. Keep the base cases
        // as 0's to simplify the later code a bit.
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                this.memo[i][j] = -1;
            }
        }
        this.text1 = text1;
        this.text2 = text2;

//        m==len of string1,
//        n==len of string2.
//        time:  O m*n^2 : m*n-nummber of subproblem to be solved, inside each iteration we scan in m(=string1) for that char what cost up to O1n.
//        space: O m*n - upper bound. in practice, many of the subproblems are unreachable. as char in str1 that not found at str2 - we do not solve it.
        return DFS(0, 0);
    }

    private int DFS(int p1, int p2) {
        // Check whether or not we've already solved this subproblem.
        // This also covers the base cases where p1 == text1.length
        // or p2 == text2.length.
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }

        // Option 1: we don't include text1[p1] in the solution.
        int option1 = DFS(p1 + 1, p2);

        // Option 2: We include text1[p1] in the solution, as long as
        // a match for it in text2 at or after p2 exists.
        int firstOccurence = text2.indexOf(text1.charAt(p1), p2);
        int option2 = 0;
        if (firstOccurence != -1) {
            option2 = 1 + DFS(p1 + 1, firstOccurence + 1);
        }

        // Add the best answer to the memo before returning it.
        memo[p1][p2] = Math.max(option1, option2);
        return memo[p1][p2];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
 * fill memo with -1 to mark didnt check it before.
 * call to dp with start pointer of 0,0
 * whenever both char equal - continue search in both.
 * whenever both char not same, check 2 paths:
 *   1.keep p1 and advance p2 ptr - as to check if next char at text 2 part of LCS
 *   2.advance p1 ptr - to check if change start point in text1 - can create longer Subsequence */

//2nd - top-down optimized, readable
class A9_LongestCommonSubsequence_1143_Optimized_recursion_lcds {
    String text1;
    String text2;
    int m;
    int n;
    int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.m = text1.length();
        this.n = text2.length();
        this.memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dp(0, 0);
    }

    public int dp(int p1, int p2) {

//        edge case - out of boundary:
        if (p1 == m || p2 == n) {
            return 0;
        }

//        already cached - use it
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }

//        recurrence relations:
//        1. if both equally - continue both to check LCS
//        2. if both not equally: check Math.max of 2 paths:
//                  with next char as start point,
//                  and path with same start pont with other char at "text2".
        int ans = 0;
        if (text1.charAt(p1) == text2.charAt(p2)) {
            ans = 1 + dp(p1 + 1, p2 + 1);
        } else {
            ans = Math.max(dp(p1 + 1, p2), dp(p1, p2 + 1));
        }

        memo[p1][p2] = ans;
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd: top down optimized (as 2, shorter writing - combine 2 edge case (out of bounds + cach - do that by initialize 1+bounds with -1)
class A9_LongestCommonSubsequence_1143_Optimized_recursion {
    private int[][] memo;
    private String text1;
    private String text2;
    public int longestCommonSubsequence(String text1, String text2) {
        this.memo = new int[text1.length() + 1][text2.length() + 1]; //use +1 - for also include base case of out of boundary.

//        fill memo with -1 to mark didnt check it before.
//        as 0's to simplify the later code a bit.
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                this.memo[i][j] = -1;
            }
        }
        this.text1 = text1;
        this.text2 = text2;
//        m==len of string1,
//        n==len of string2.
//        time:  O1m*n
//        space: O1m*n
        return memoSolve(0, 0);
    }

    private int memoSolve(int p1, int p2) {
//        check 2 edge cases::
//        1-if already cached - return it.
//        2-if reach boundary - return.
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }

        int answer = 0;
        if (text1.charAt(p1) == text2.charAt(p2)) {
            answer = 1 + memoSolve(p1 + 1, p2 + 1); // if both char are equals - ans + 1 and keep iterating.
        } else {
            answer = Math.max(memoSolve(p1, p2 + 1), memoSolve(p1 + 1, p2)); //if both are not eqaul - then advance only one of them to the next round.
        }

        memo[p1][p2] = answer;
        return memo[p1][p2];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//4th bottom-up:
class A9_LongestCommonSubsequence_1143_Iterative_bottom_up_4th {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1]; //Java auto fill all with 0.

        for (int row = n - 1; row >= 0; row--) {
            for (int col = m - 1; col >= 0; col--) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    dp[row][col] = 1 + dp[row + 1][col + 1];
                } else {
                    dp[row][col] = Math.max(dp[row + 1][col], dp[row][col + 1]);
                }
            }
        }
//        time and space: O1n*m
        return dp[0][0];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//5th:
class A9_LongestCommonSubsequence_1143_Iterative_bottom_up_5th_optimized_space {
    public int longestCommonSubsequence(String text1, String text2) {
//        make sure text1 - always shorter (help with reducing space).
        if (text2.length() < text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        int[] previous = new int[text1.length() + 1];
        int[] current  = new int[text1.length() + 1];

        for (int col = text2.length() - 1; col >= 0; col--) {
            for (int row = text1.length() - 1; row >= 0; row--) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    current[row] = 1 + previous[row + 1];
                } else {
                    current[row] = Math.max(previous[row], current[row + 1]);
                }
            }
            // The current column becomes the previous one, and vice versa.
            int[] temp = previous;
            previous = current;
            current = temp;
        }

//        time:  On*m
//        space: O Min(n,m).
        return previous[0];
    }
}

