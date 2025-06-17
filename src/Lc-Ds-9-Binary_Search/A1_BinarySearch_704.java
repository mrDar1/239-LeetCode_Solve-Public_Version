/*problem:
* implement binary search with no duplicates elements*/
public class A1_BinarySearch_704 {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r){
            int mid = l + ((r - l) /2);

            if (nums[mid] == target){
                return mid;
            }

            if (nums[mid] > target){
                r = mid - 1;
            }

            else{ /*if (nums[mid] < target){*/
                l = mid + 1;
            }
        }
//        time: O logn , sace: O1
        return -1;
    }
}