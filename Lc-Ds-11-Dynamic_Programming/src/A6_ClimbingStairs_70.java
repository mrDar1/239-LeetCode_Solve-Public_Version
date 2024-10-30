import java.util.Arrays;
import java.util.HashMap;

/*problem:
return number of different ways to climb stairs (sound like backtrack, but no),
each time can make 1/2steps. */

/*solutions:
* 1-brute force, DFS
* 2-DP-top down (with hashmap)
* 3-DP-top down (with Array)
* 4-bottom up tabulating
* 5-fibunacci, using properties from fibunacci. */

public class A6_ClimbingStairs_70 {
    /*intuition: brute force explore all paths. */
    public int climbStairs_brute_force(int n) {
//        time: On^2 - check number of paths from each choice.
//        space: On-tree height can be as deep as n.
        return dfs(0,n);
    }
    private int dfs(int i, int n) {
//        base case - reach end.
        if (i > n){
            return 0;
        }

//        valid combination! add +1.
        if (i==n){
            return 1;
        }

//        recurrence relations:
        return (dfs(i+1,n) + dfs(i+2,n)); //explore 1 step , 2 steps.
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A6_ClimbingStairs_70_secondway_top_down_memo_hashmap {
    HashMap<Integer,Integer> memo = new HashMap<>();
    public int climbStairs(int n) {
//        time:  O1n-size of recursion.
//        space: O1n-size of Hashmap
        return dfs(0,n);
    }
    private int dfs(int i, int n) {
        if (i > n){
            return 0;
        }
        if (i==n){
            return 1;
        }

        if (memo.containsKey(i)){
            return memo.get(i);
        }

        memo.put (i, dfs(i+1,n) + dfs(i+2,n)); //<index, number of possibilities>
        return memo.get(i);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A6_ClimbingStairs_70_secondway_top_down_arr_memo {
    int[] memo;
    public int climbStairs(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo,0); //no need for that in java - good practice for C!
//        time:  O1n-size of recursion.
//        space: O1n-size of Hashmap
        return dfs(0,n);
    }
    private int dfs(int i, int n) {
        if (i > n){
            return 0;
        }
        if (i==n){
            return 1;
        }

        if (memo[i] != 0 ){
            return memo[i];
        }

        memo[i] =  (dfs(i+1,n) + dfs(i+2,n)); //<number of possibilities for that index>
        return memo[i];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A6_ClimbingStairs_70_bottom_up_tabulating {
    public int climbStairs(int n) {
        int[] tabu = new int[n + 1];
        Arrays.fill(tabu,0); //no need for that in java - good practice for C!

        if (n == 1){
            return 1;
        }

        tabu[1] = 1;
        tabu[2] = 2;
        for ( int i = 3 ; i <= n ; i++) {
            tabu[i] = tabu[i-1] + tabu[i-2];
        }

//        time:  O1n-traverse n.
//        space: O1n-size of tabulation arr
        return tabu[n];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A6_ClimbingStairs_70_fibunacci{
    public int climbStairs(int n) {
        if (n==1){
            return 1;
        }

        int first = 1;
        int second = 2;

        for (int i = 3; i <= n; i++) {
            int cur = second + first;
            first = second;
            second = cur;
        }
//        time:  O1n
//        space: O1
        return second;
    }
}