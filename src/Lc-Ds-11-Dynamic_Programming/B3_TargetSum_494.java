import java.util.Arrays;

/*return number of different possibilities to reach "target",
* can only use +- to each element.
* constraint: must use all of nums[]. */

/*solutions:
* 1st-top-down
* 2nd-top-down with no memo (backtrack DFS)
* 3rd-bottom up - 2D memo(size O n*m) just read didnt understand...
* 4th-bottom up - 1D memo(size O m) just read didnt understand... improved space*/

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

public class B3_TargetSum_494 {
    int totalSum;
    int[][] memo;
    int[] nums;
    int target;

    public int findTargetSumWays(int[] nums, int target) {
        this.target = target;
        this.nums = nums;
        this.totalSum = Arrays.stream(nums).sum(); //traverse entire nums[] and sums up all elements.
        this.memo = new int[nums.length][2 * totalSum + 1]; //why 2*totalsum? ans: for both positive and negative val.

        //fill memo[][] with Min value so can use cach.
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = Integer.MIN_VALUE;
            }
        }

        return dfs(0,0);
    }

    private int dfs(int i, int sum) {
//        base case - finish traverse all input: either got to target or missed it.
        if (i == nums.length ){
            if (target == sum){
                return 1;
            } else {
                return 0;
            }
        }

//        already cached that val:
        if (memo[i][sum + totalSum] != Integer.MIN_VALUE){
            return memo[i][sum + totalSum];
        }

        int plus  = dfs(i + 1, sum + nums[i] );
        int minus = dfs(i + 1, sum - nums[i] );

//        time:  On*totalSum - fill memo exactly 1.
//        space: On*2*totalSum - depth of recursion can go up to n, and memo[][] contain n*totalSum elements.
        return memo[i][sum + totalSum] = plus + minus;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B3_TargetSum_494_top_down_no_memo {
    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        calculate(nums, 0, 0, target);
        return count;
    }

    public void calculate(int[] nums, int i, int sum, int target) {
        if (i == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            calculate(nums, i + 1, sum + nums[i], target);
            calculate(nums, i + 1, sum - nums[i], target);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B3_TargetSum_494_bottom_up {
    public int findTargetSumWays(int[] nums, int target) {

        int total = Arrays.stream(nums).sum(); //total entire nums[]
        int[][] memo = new int[nums.length][2 * total + 1];
        memo[0][ nums[0] + total]  = 1;
        memo[0][-nums[0] + total] += 1;

        for (int i = 1 ; i < nums.length ; i++) {
            for (int sum = -total ; sum <= total ; sum++) {
                if (memo[i - 1][sum + total] > 0) {
                    memo[i][sum + nums[i] + total] += memo[i - 1][sum + total];
                    memo[i][sum - nums[i] + total] += memo[i - 1][sum + total];
                }
            }
        }
//        t==total sum of nums[], n==nums.len
//        time: O1(t*n). The memo array of size O(t⋅n) has been filled just once.
//        space: O(2t*n) for memo[][].
        return Math.abs(target) > total ? 0 : memo[nums.length - 1][target + total];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B3_TargetSum_494_bottom_up_improved_space {
    public int findTargetSumWays(int[] nums, int target) {
        int total = Arrays.stream(nums).sum();
        int[] memo = new int[2 * total + 1];
        memo[ nums[0] + total]  = 1;
        memo[-nums[0] + total] += 1;

        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2 * total + 1];
            for (int sum = -total ; sum <= total ; sum++) {
                if (memo[sum + total] > 0) {
                    next[sum + nums[i] + total] += memo[sum + total];
                    next[sum - nums[i] + total] += memo[sum + total];
                }
            }
            memo = next;
        }

//        t==total sum of nums[], n==nums.len
//        time: O1(t*n). The memo array of size O(t⋅n) has been filled just once.
//        space: O(2t) for memo[].
        return Math.abs(target) > total ? 0 : memo[target + total];
    }
}