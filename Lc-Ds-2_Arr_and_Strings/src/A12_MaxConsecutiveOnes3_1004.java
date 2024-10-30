/*problem:
Given binary array nums and an integer k,
return the maximum number of consecutive 1's in the array if you can flip at most k 0's. */

/*solutions: (same as Example2 - but here given k flips))*/
public class A12_MaxConsecutiveOnes3_1004 {
    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 3;
        System.out.println(longestOnes(arr, k));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int longestOnes(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int cur = 0; //current number of 0.
        int max = 0;

        for( ; r < nums.length ; ++r){
            if(nums[r] == 0) {
                ++cur;
            }

            while(cur > k){
                if(nums[l] == 0){
                    --cur;
                }
                ++l;
            }

            max = Math.max(max, r - l + 1 );
        }
//      time: On space: O1
        return max;
    }
}