/*problem:
given String, contain only 1,0. and int m, int n.
return largest subset with no more than m,n:
m-'0'
n-'1'
note: each "word" at the string is another binary String - so to create longest subset, one probably prefer the shorter first. */

/*solutions: (TLE==Time Limit Exceeded, fail for too slow...)
* 1st-brute force - TLE - nice Bitwise tricks!
* 2nd-same as 1st, small optimize - valid when traverse not at end - TLE
* 3rd-recursion - TLE
* 4th-top-down
* 5th-bottom up*/

public class B14_OnesandZeroes_474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int maxLen = 0; //represent ans.

        int totalSubsets = 1 << strs.length;
        // number of all possible subsets = (2^strs.length) , Math combination formula.
        // why we want it? in brute force, will try all possible combination.

        for (int i = 0; i < totalSubsets ; i++) {
            int curZeroes = 0;
            int curOnes = 0;
            int curLen = 0;

            // each word, check if included in the current subset
            for (int j = 0 ; j < strs.length; j++) {
                if ((i & (1 << j)) != 0) { //Bitwise math formula - determine if the j word is included in the current subset represented by i. only if it does at cur subset-check number of 1 and 0. Bitwise efficient trick.
                    int[] count = countZeroesAndOnes(strs[j]);
                    curZeroes += count[0];
                    curOnes += count[1];
                    curLen++;
                }
            }
            //if valid - choose longest to "maxLen".
            if (curZeroes <= m && curOnes <= n){
                maxLen = Math.max(maxLen, curLen);
            }
        }
//        x==avg size of each string inside strs[].
//        time:  O(2^len * x) - 2^ len.
//        space: O1
        return maxLen;

    }
    public int[] countZeroesAndOnes(String s) {
        int[] c = new int[2];// <count 0, count 1>
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B14_OnesandZeroes_474_brute_force_improved {
    public int findMaxForm(String[] strs, int m, int n) {
        int maxLen = 0;

        for (int i = 0; i < (1 << strs.length); i++) {
            int curZeroes = 0;
            int curOnes = 0;
            int curLen = 0;

            for (int j = 0; j < 32; j++) {
                if ((i & (1 << j)) != 0) {
                    int[] count = countZeroesAndOnes(strs[j]);
                    curZeroes += count[0];
                    curOnes += count[1];

                    if (curZeroes > m || curOnes > n){
                        break;
                    }
                    curLen++;
                }
            }
            if (curZeroes <= m && curOnes <= n)
                maxLen = Math.max(maxLen, curLen);
        }
//        same complexity as above, slightly faster.
        return maxLen;
    }
    public int[] countZeroesAndOnes(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B14_OnesandZeroes_474_recursion {
    public int findMaxForm(String[] strs, int m, int n) {
//        same time and space
        return calculate(strs, 0, m, n);
    }

    public int calculate(String[] strs, int i, int zeroes, int ones) {
        if (i == strs.length){
            return 0;
        }

        int[] count = countZeroesAndOnes(strs[i]);
        int taken = -1;

        if (zeroes - count[0] >= 0 && ones - count[1] >= 0){
            taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1]) + 1;
        }
        int not_taken = calculate(strs, i + 1, zeroes, ones);
        return Math.max(taken, not_taken);
    }

    public int[] countZeroesAndOnes(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B14_OnesandZeroes_474_top_down {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m + 1][n + 1];
//        time:  O(len*m*n)
//        space: O(len*m*n) - 3d array memo[][][]
        return calculate(strs, 0, m, n, memo);
    }

    public int calculate(String[] strs, int i, int zeroes, int ones, int[][][] memo) {
        if (i == strs.length) {
            return 0;
        }

        if (memo[i][zeroes][ones] != 0) {
            return memo[i][zeroes][ones];
        }

        int[] count = countZeroesAndOnes(strs[i]); //at first word.
        int taken = -1;

        if (zeroes - count[0] >= 0 && ones - count[1] >= 0){ //if valid
            taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1], memo) + 1;
        }

        int not_taken = calculate(strs, i + 1, zeroes, ones, memo); //explore with next word
        memo[i][zeroes][ones] = Math.max(taken, not_taken);
        return memo[i][zeroes][ones];
    }

    public int[] countZeroesAndOnes(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B14_OnesandZeroes_474_bottom_up {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String s : strs) {
            int[] count = countZeroesAndOnes(s);

            for (int zeroes = m; zeroes >= count[0]; zeroes--)
                for (int ones = n; ones >= count[1]; ones--)
                    dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
        }
//        time:  O(len*m*n)
//        space: O(m*n) - for dp[][]
        return dp[m][n];
    }
    public int[] countZeroesAndOnes(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
}

