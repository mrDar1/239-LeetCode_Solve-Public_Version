/*problem:
at sorted nums[] - find target with BS - if not found return -1.
nums[] sorted but rotated!! so its not regular increase, its increase until certain point, then increase from it again. */

/*intuition:
* first find pivot with binary search.
* then use 2 binary searches  - left and right side of pivot - in search for "target"
*
* note: why do must use 2 different BinarySearch function:
* 1st-to find pivot - we do not expect to find "specific target" just the minimum value.
* 2nd-regular binarySearch after "specific target"
*
* so, at the first don't use "if mid==target" !!! only at second as regular binarySearch!! */


public class B7_SearchinRotatedSortedArray_33 {
    public int search(int[] nums, int target) {
        // find index of pivot element (=smallest element)
        int pivotIndex = binarySearchPivot(nums, nums[nums.length - 1] );

        // binary search over left flank of pivot.
        int ans = binarySearch(nums, 0, pivotIndex - 1, target);

        if (ans != -1) { // optimization - if fond at left plank dont need to search right also, stop here!
            return ans;
        }

        // binary search over right flank of pivot.
        return binarySearch(nums, pivotIndex, nums.length - 1, target);
    }


    private int binarySearchPivot(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r){
            int mid = l + (r-l) / 2;

            if (nums[mid] > target){
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return l;
    }

    // Binary search over an inclusive range [left_boundary ~ right_boundary]
    private int binarySearch(int[] nums, int leftBoundary, int rightBoundary, int target) {
        int left  = leftBoundary;
        int right = rightBoundary;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;

            } else if (nums[mid] > target) {
                right = mid - 1;

            } else {
                left = mid + 1;
            }
        }
        return -1; //element is not at arr curr bounds.
    }
}
/*  complexity:
    time: O 2 * log n : log n find pivot with BS, log n - find target (got 2 binary search but each for n/2 size)
    space: O1*/