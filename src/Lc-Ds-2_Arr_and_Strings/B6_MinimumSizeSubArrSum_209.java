/*problem:
* return the min size of sub-array, whose sum >= target.*/


public class B6_MinimumSizeSubArrSum_209 {
    public static void main(String[] args){
        int[] arr = {2,3,1,2,4,3};
        int target = 7;
//            output: 2
        int n = minSubArrayLen(target,arr);
        System.out.println(n);
    }
    public static int minSubArrayLen(int target, int[] nums){
        int r = 0;
        int l = 0;
        int cur = 0; //cur sum of the elements - we search >=target
        int ans = Integer.MAX_VALUE; // number of elements - we search Min result.

        for( ; r < nums.length ; ++r){
            cur += nums[r];

            while(cur >= target){
                ans = Math.min(ans, r - l + 1);
                cur -= nums[l++];
                 /*note: its equal to:
                 sum -= nums[l];
                 ++l; */
            }
        }
//        time: O1n space: O1
//        edge case - cant reach the sum of target - return 0 by constraint.
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
