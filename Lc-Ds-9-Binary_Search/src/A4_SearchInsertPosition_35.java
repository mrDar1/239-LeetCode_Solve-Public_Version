/*problem:
* given target, return its index or if missing where it should have been.
* constraint: no duplicates values! */

public class A4_SearchInsertPosition_35 {
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r){
            int mid = l + ((r - l) / 2);

            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] > target){
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}