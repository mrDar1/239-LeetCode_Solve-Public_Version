/*problem:
return the max distance of valid pair.
for pair to be valid:
   1- i<=j (i == ptr of nums1, j == ptr for nums2)
   2- nums1[i] <= nums2[j]
constraint: both arr sorted decreasingly int[].

problem in my words:
find max distance between 2 indexes that follow rules: p1<=p2 and also nums1[p1]<nums2[p2] */

/*solutions:
* 1st - 2 ptrs - fastest
* 2nd - binary search - my solution.
* 3rd - binary search from solutions - didnt like it but can learn from it (change mid with +1) */

/* **************************** */
/* **************************** */
/* **************************** */
/* **************************** */

public class B2_MaximumDistanceBetweenPairofValues_1855 {
    /*motivation:
     * in order to increase distance try to make p1 small as possible and p2 big as possible.
     * since both decreasingly sorted,  whenever nums1[p1]>nums2[p2]  - must advance p1++ and hope next element
     * would be smaller than nums2[p2].
     *
     * in all other cases always simply increase ++p2. */
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;
        int p1 = 0;
        int p2 = 0;

        while (p1 < nums1.length && p2 < nums2.length) {
//            if p1 is too high - continue to next smaller element.
            if (nums1[p1] > nums2[p2]) {
                ++p1;
            } else {
                ans = Math.max(ans, p2 - p1);
                ++p2;
            }
        }
//        n==nums1.len, m==nums2.len
//        time: O1n+m-to traverse both int[]
//        space:O1-only 3 vars
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
 * for each nums1 - binary search the threshold - that from it val smaller than nums1[p1].
 *   note: left boundary is always p1, as constraint.
 * each time compare that threshold point with the max - update if necessary. */
class B2_MaximumDistanceBetweenPairofValues_1855_binary_search {
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;

        for (int i = 0; i < nums1.length; ++i) {
            int threshold = binarySearch(nums2, nums1[i], i);
            if (threshold == -1){
                continue;
            }

            ans = Math.max(ans, threshold - i);
        }
        //        time: On log !n : O1n-traverse all of nums1, O log !n -each time binary search it inside nums2.
        //        space:O1
        return ans;
    }

    public int binarySearch(int[] nums, int nums1element, int leftBound) {
        int left = leftBound;
        int right = nums.length - 1;
        int ans = -1; //if dont find - return -1, so caller know didn't find.

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= nums1element) { //valid, try again with even smaller number at nums2.
                ans = mid;
                left = mid + 1;
            } else { //fail, try again with bigger numbers at num2.
                right = mid - 1;
            }
        }
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B2_MaximumDistanceBetweenPairofValues_1855_binary_search_lc_solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;

        // Iterate over nums1 and find insertion position to nums2.
        for (int i = 0; i < nums1.length; ++i) {
            int k = binarySearch(nums2, nums1[i]);
            if (k > i) {
                ans = Math.max(ans, k - i);
            }
        }
//        time: On*(log m) : n==nums1.lem, m==nums2.len. we traverse all n1 one by one and search for it in nums2.
//        space:O1
        return ans;
    }
    public int binarySearch(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (nums[mid] < k) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
