/*problem:
given "n" dices, each with "k" faces (=k-1).
return how many different possibilities to reach exactly "target"
constraint: ans must be modulu of 1000000007 */

/*solutions:
 * 1st-top-down
 * 2nd-bottom up */

public class B10_NumberofDiceRollsWithTargetSum_1155 {
    final int MOD = 1000000007; //constraint.

    public int numRollsToTarget(int n, int k, int target) { //start here
        Integer[][] memo = new Integer[n + 1][target + 1];
        return DFS(0, n, 0, target, k, memo);
    }

    int DFS(int diceIndex, int n, int currSum, int target, int k, Integer[][] memo) {
//        base case - traverse all dices, if its exactly "target" +1 to ans. else prune(=0).
        if (diceIndex == n) {
            return currSum == target ? 1 : 0;
        }

//            already cached that val:
        if (memo[diceIndex][currSum] != null) {
            return memo[diceIndex][currSum];
        }

        int ways = 0;
        //traverse dice-options can be 1 to given k (constraint)
        //optimize: no point in traverse numbers who are bigger than (target - curSum)-never produce valid ans.
        for (int i = 1; i <= Math.min(k, target - currSum); i++) {
            ways = (ways + DFS(diceIndex + 1, n, currSum + i, target, k, memo)) % MOD;
        }
        return memo[diceIndex][currSum] = ways;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B10_NumberofDiceRollsWithTargetSum_1155_bottom_up {
    final int MOD = 1000000007;

    public int numRollsToTarget(int n, int k, int target) {
        int[][] memo = new int[n + 1][target + 1];
        // initialize base case
        memo[n][target] = 1; //constraint: dice cannot be 0.

        for (int diceIndex = n - 1; diceIndex >= 0; diceIndex--) {
            for (int currSum = 0; currSum <= target; currSum++) {
                int ways = 0;

                // Iterate over the possible face value for current dice
                for (int i = 1; i <= Math.min(k, target - currSum); i++) {
                    ways = (ways + memo[diceIndex + 1][currSum + i]) % MOD;
                }

                memo[diceIndex][currSum] = ways;
            }
        }

        return memo[0][0];
    }
}