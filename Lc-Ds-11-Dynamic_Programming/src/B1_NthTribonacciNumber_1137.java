import java.util.HashMap;
/*problem:
* its like fibunacii but with 3 numbers - tribonacii */

/*3 solutions:
* 1st - recursion - after 1 bug success!!
* 2nd - bottom up - after 2 bug success! (edges boundary)
* 3rd - bottom up improve space - just copy... */

/*solutions:
 * 1st-top down
 * 2nd-bottom-up
 * 3rd-bottom-up improved space */

public class B1_NthTribonacciNumber_1137 {
    public static void main(String[] args) {
        Solution_B1_NthTribonacciNumber_1137 obj_1137 = new Solution_B1_NthTribonacciNumber_1137();
        Solution_B1_NthTribonacciNumber_1137_bottomup obj_1137_2 = new Solution_B1_NthTribonacciNumber_1137_bottomup();

        for (int i = 0; i <= 25; ++i) {
//            System.out.println(obj_1137.tribonacci(i));
            System.out.println(obj_1137_2.tribonacci(i));
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_B1_NthTribonacciNumber_1137 {
//    success after 1 debug!! top-down
    HashMap<Integer,Integer> memo = new HashMap<Integer,Integer>(){ //<number, sum>
        {   put(0,0);
            put(1,1);
            put(2,1);   }
        };

    public int tribonacci(int n) {
        if (memo.containsKey(n)){
            return memo.get(n);
        }

        memo.put(n, tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3));

//        time & space: O1n
        return memo.get(n);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_B1_NthTribonacciNumber_1137_bottomup {
    public int tribonacci(int n) {
//        edge case:
        if (n < 3){
            return n > 0 ? 1 : 0;
        }

        int[] memo = new int[n+1];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 1;

        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i-1] + memo[i-2] + memo[i-3];
        }

//        time & space: O1n
        return memo[n];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_B1_NthTribonacciNumber_1137_bottomup_improved_space {
    public int tribonacci(int n) {
//        base case:
        if (n < 3) {
            return n > 0 ? 1 : 0;
        }

        int a = 0;
        int b = 1;
        int c = 1;

        for (int i = 2; i < n ; ++i) {
            int tmp = a + b + c;
            a = b;
            b = c;
            c = tmp;
        }

        return c;
    }
}