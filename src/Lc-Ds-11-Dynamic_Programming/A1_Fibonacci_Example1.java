import java.util.HashMap;
/*4 solutions:
* 1st - brute force recursion
* 2nd - brute force iterative
* 3rd - top down (recursion with memoization - store at hashmap, if can - use arr as it faster)
* 4th - bottom up (iterative with tabulation (store at arr))*/

//each time uncomment different solution see the change at speed!!!

public class A1_Fibonacci_Example1 {
    public static void main(String[] args){
        A1_Fibonacci_Example1_solved_withDp_Top_Down obj_e1 = new A1_Fibonacci_Example1_solved_withDp_Top_Down();
        A1_Fibonacci_Example1_solved_withDp_BottomUp obj_e2 = new A1_Fibonacci_Example1_solved_withDp_BottomUp();

        for (int i = 0 ; i < 40 ; ++i){
//            System.out.println(fib(i)); //non DP - recursion
//            System.out.println(fib_iterative_way(i)); //non DP - iterative
//            System.out.println(obj_e1.fib(i)); //with DP - top down-memoization
            System.out.println(obj_e2.fib(i)); //with DP - bottom up-tabulation
        }
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int fib(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
//        time: O2^n - as each function calls to 2 more function n times.
//        space: O1
        return (fib(n-1) + fib(n-2));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int fib_iterative_way(int n) {
        int last = 0;
        int cur = 1;

        for (int i = 0; i <= n; i++) {
            int next = last + cur;
            last = cur;
            cur = next;
        }
//        time: O2^n - as each function calls to 2 more function n times.
//        space: O1
        return cur;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A1_Fibonacci_Example1_solved_withDp_Top_Down {
    HashMap<Integer,Integer> map = new HashMap<>(); //<index element, fib value>
    public int fib(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }

        if (map.containsKey(n)){
            return map.get(n);
        }


        map.put (n, fib(n-1) + fib(n-2));
//        time and space: O1n - traverse exactly once.
        return map.get(n);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A1_Fibonacci_Example1_solved_withDp_BottomUp {
    public int fib(int n) {
//      edge case:
        if (n==0){
            return 0;
        }

        int[] dp = new int[ n + 1]; // store fib value
        dp[0] = 0; //in java no need to initialize to 0, just for sake of clarity.
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

//        time and space: O1n - traverse exactly once.
        return dp[n];
    }
}
