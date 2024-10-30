/*problem: start from 'n', add only positive number - until digit sum <= "target"
* return the min number that make it possible.
* constraint: always possible.*/

/*solutions:
* note-both shared same helper method "CalculateDigitsSum".
* 1st-my naive way (work but to slow...)
* 2nd-same with math formula. */

public class B13_MinimumAdditiontoMakeIntegerBeautiful_2457 {
    public static void main(String[] args){
        Solution_B13_MinimumAdditiontoMakeIntegerBeautiful_2457 obj_2457 = new Solution_B13_MinimumAdditiontoMakeIntegerBeautiful_2457();
        long n = 467;
        int target = 6;
        System.out.println(obj_2457.makeIntegerBeautiful_myfirst_approach_work_but_slow_TLE(n, target));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_B13_MinimumAdditiontoMakeIntegerBeautiful_2457 {
    /*psudo:
     * 1- use method count digits.
     * 2- if its not equal to target add 1 until it does. */
    public long makeIntegerBeautiful_myfirst_approach_work_but_slow_TLE(long n, int target) {
        int ans = 0;
        long curDigitSum = CalculateDigitsSum(n);

        while (curDigitSum > target){
            ++n;
            curDigitSum = CalculateDigitsSum(n);
            ans++;
        }

        return ans;
    }

    private long CalculateDigitsSum(long n) { //shared for bothat ways.
        long ans = 0;

        while (n > 0){
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public long makeIntegerBeautiful_math_formula_optimized(long n, int target) {
        long n_copy = n;
        long base = 1;

        while (CalculateDigitsSum(n) > target){
            n = n / 10 + 1;
            base *= 10;
        }

        return n * base - n_copy;
    }
}
/*
explanation: While the current sum of digits is bigger than target,
we do n = n // 10 + 1
Take example of n = 123456, the process is
123456 -> 123460 -> 123500 -> 124000 -> 130000 -> 2000000

We find the valid n with digits sum no bigger than target,
then we return the final value of n minus its original value n0.
*/