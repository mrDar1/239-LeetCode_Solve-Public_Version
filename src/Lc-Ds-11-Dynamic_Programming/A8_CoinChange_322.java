import java.util.Arrays;

/*problem:
return the min number of coins used - to reach exactly "amount"
if not possible - return -1.
got unlimited amount of coins, each value is as coins[] */

/*solutions:
* 1st - backtrack - Time Limit Exceeded
* 2nd - to down
* 3rd - bottom up*/

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//    BackTrack...
/*intuition:
* use DFS: whenever cur Coin freq is smaller than given "amount" check paths of:
*   1st-try to use same coin again (will happen at for loop)
*   2nd-try use next coin instead (will happen at next recursion call)
*   3rd-if, when use that coin exceeded "amount" - prune, by not doing anything.
*
*   then-save the min cost of both ways - and compare with all other paths.*/
public class A8_CoinChange_322 {
    public int coinChange(int[] coins, int amount) {
//        time: approximately: OS^n: each time reduce from amount different freq of cur coin, and path's when reduce from amount freq of all other coins.
//        S == "amount"
//        space:O1n-for recursion call stack.
        return DFS(0, coins, amount);
    }

    private int DFS(int idxCoin, int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }

        if (idxCoin < coins.length && amount > 0) {
            int maxFreq = amount/coins[idxCoin]; //max uses of same coin to reach "amount"
            int minCost = Integer.MAX_VALUE;

            for (int curFreq = 0; curFreq <= maxFreq; curFreq++) { //curFreq represent how much uses done from cur same coin.
                int curValue = curFreq * coins[idxCoin]; //curValue when use "curFreq" time of cur coin

                if (amount >= curValue) {
                    int res = DFS(idxCoin + 1, coins, amount - curValue);

                    if (res != -1) {
                        minCost = Math.min(minCost, res + curFreq);
                    }
                }
            }
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }

        return -1;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A8_CoinChange_322_top_down {
    /*intuition:
    * use DFS method - return min number of coins for each "amount".
    * start from top, each time check what is lowest number of coins to reach "curReminder".
    * sum all smaller "curReminder", cahc as tabu[0] and return it. */

    int[] coins;
    int[] memo;  //store what is the lowest coins for i "amount"
    public int coinChange(int[] coins, int amount) {
//        edge case:
        if (amount < 1){
            return 0;
        }

        this.coins = coins;
        this.memo = new int[amount]; //store what is the lowest coins for i "amount"
//        S==amount
//        time:  S*n - for each amount we try all n-coins[] (the ans are cached)
//        space: OS - to store all cached for different amount.
        return DFS(amount);
    }

    private int DFS(int rem) {
        //success!
        if (rem == 0){
            return 0;
        }

        //too big, prune brunch.
        if (rem < 0){
            return -1;
        }

        //already cached - use it.
        if (memo[rem - 1] != 0){
            return memo[rem - 1];
        }

        int minCostToCurRem = Integer.MAX_VALUE; //min cost to current reminder.
        for (int coin : coins){
            int contender = DFS(rem - coin); //contender== check cur coin, if number of coins for that "reminder", smaller than all other - update "minCostToCurRem" with it.

            if (isValid(contender, minCostToCurRem)){
                minCostToCurRem = contender + 1; //use curCoin, we have used another 1 coin.
            }
        }

        memo[rem - 1] = minCostToCurRem != Integer.MAX_VALUE ? minCostToCurRem : -1; //if didnt find any exact solution return -1.
        return memo[rem - 1];
    }

    private boolean isValid(int midres, int min) {
        if (midres < min && midres >= 0){ //we want only results that are smaller than cur min.
            return true;
        }
        return false;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A8_CoinChange_322_bottom_up {
    /*build tabulation array.
    * fill it up with amount+1.
    *
    * traverse from start of 1 until amount:
    *   traverse each coin:
    *       if coin is smaller than value - use Math.min to check it smaller than last attempt.
    *
    * return the last element in memo[] */
    public int coinChange(int[] coins, int amount) {
//        edge case: no needed coins to get to that amount.
        if (amount < 1){
            return 0;
        }

        int[] tabu = new int[amount+1]; //store what is the lowes coins for i "amount", why +1? for edge case amount==0, return 0 cost af first element.
        Arrays.fill(tabu, amount + 1);
        tabu[0] = 0; //cost non to get to val 0.

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i){
                    tabu[i] = Math.min(tabu[i]  ,  tabu[i - coins[j]] + 1 );
                }
            }
        }
//        S==amount
//        time:  S*n - for each amount we try all n-coins[]. (the ans are cached)
//        space: OS - to store all cached for different amount.
        return tabu[amount] > amount ? -1 : tabu[amount];
    }
}