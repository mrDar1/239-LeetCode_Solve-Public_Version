/*problem:
* like 2-sum, but input is sorted:
* given sorted arr and int "target" return true, if sum of 2 elements evaluate to "target". */
public class A2_IsSumFound_Example2 {
    public static void main(String[] args) {
        Solution_A2_IsSumFound_Example2 obj_example2_IsSumFound = new Solution_A2_IsSumFound_Example2();
        int[] nums = {1, 2, 4, 6, 8, 9, 14, 15};
        int target = 13;
        System.out.println(obj_example2_IsSumFound.IsSumFound(nums, target));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A2_IsSumFound_Example2{
    public boolean IsSumFound(int []nums, int t){
        int l = 0;
        int r = nums.length - 1;

        while(l < r){
            int curr = nums[l] + nums[r];
            if (t == curr)
                return true;

            if (t > curr){
                ++l;
            }else{
                --r;
            }
        }
//        time: On
//        space:O1
        return false;
    }
}
