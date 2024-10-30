import java.util.HashMap;
/*problem:
* return max val of robbed house.
* cannot rob neighbors,
* constraint: circular hood - first and last are neighbors. */

/*solutions:
 * 1st-top-down
 * 2nd-bottom up - On space - not intuitive but good practice for harder problems
 * 3rd-bottom up - O1 space - readable */

public class B4_HouseRobber2_213 {
    public static void main(String[] args) {
        B4_HouseRobber2_213_recursion_my_first_success robber = new B4_HouseRobber2_213_recursion_my_first_success();
        int[] nums = {1,2,3,4};
        System.out.println(robber.rob(nums)); // Output should be 4
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B4_HouseRobber2_213_recursion_my_first_success {
    int[] nums;

    public int rob(int[] nums) {
        this.nums = nums;

//        edge case:
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }


        int start_at_first_house  =  dfs(0, nums.length-2, new HashMap<>()); //endHouse-one before last house as it circular hood.
        int start_at_second_house =  dfs(1, nums.length-1, new HashMap<>()); //endHouse-the last house

//        time complexity  : On
//        space complexity : On
        return Math.max(start_at_first_house, start_at_second_house);
    }


    private int dfs(int startHouse, int endHouse, HashMap<Integer,Integer> memo) {

//        base case - finish traverse all input
        if (endHouse == startHouse){
            return nums[startHouse];
        }

//      out of boundary - prune
        if (endHouse < startHouse){
            return 0;
        }

//        already cached that val:
        if (memo.containsKey(endHouse)){
            return memo.get(endHouse);
        }

        int max = Math.max(dfs(startHouse,endHouse-1, memo) ,
                           dfs(startHouse,endHouse-2, memo) + nums[endHouse]);

        memo.put(endHouse, max);
        return memo.get(endHouse);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B6_HouseRobber2_213_bottom_up_with_DP_table {
    public int rob(int[] nums) {
//        edge cases:
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int max1 = robCircularHood(nums, 0, nums.length - 2);
        int max2 = robCircularHood(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    private int robCircularHood(int[] nums, int start, int end) {
//        initialize tabulation:
        int[] dp = new int[end - start + 1];
        dp[0] = nums[start];
        if (end > start) {
            dp[1] = Math.max(nums[start], nums[start + 1]);
        }

        for (int i = start + 2; i <= end; i++) {
            dp[i - start] = Math.max(dp[i - start - 1], dp[i - start - 2] + nums[i]);
        }

        return dp[end - start];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B4_HouseRobber2_213_bottom_up {
    public int rob(int[] nums) {
//        edge cases:
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]) ;


        int max1 = robCircularHood(nums, 0, nums.length - 2);
        int max2 = robCircularHood(nums, 1, nums.length - 1);

//        time complexity  : on
//        space complexity : o1
        return Math.max(max1, max2);
    }

    public int robCircularHood(int[] nums, int startHouse, int endHouse) {
        int prev1 = 0; //represent max money till 1 house before
        int prev2 = 0; //represent max money till 2 house before

        for (int i = startHouse; i <= endHouse; i++) {
            int cur = Math.max(nums[i] + prev2 , prev1);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}