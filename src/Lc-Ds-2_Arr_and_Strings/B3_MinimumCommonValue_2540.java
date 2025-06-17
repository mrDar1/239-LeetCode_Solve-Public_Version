/*problem:
* got 2 sorted arr, return the lowest int, shared to both of them.
* if there is non - return -1;*/

/*3 solutions:
* 1st - my first brute force - 2 ptrs -  high time.
* 2nd - 2 ptrs optimized - like that most!
* 3rd - hashset brute force
* 4th - Binary search - over-kill but nice practice*/

import java.util.HashSet;

public class B3_MinimumCommonValue_2540 {
    /*psudo:
    * use nested loop: start from top of nums1,
    * traverse nums2 until nums2[i] >= nums[1]
    * if == return the num. if bigger - break to next number.
    * if reach at end - no shared value - so return -1 */
    public int getCommon(int[] nums1, int[] nums2) {

        for (int num_1 : nums1) {
            for (int num_2 : nums2){

                if (num_1 == num_2){
                    return num_1;
                }

                if( num_1 < num_2){
                    break;
                }
            }
        }

//        time:  On^2 - at worst case will traverse for each element in nums[1] entire nums[2]
//        space: O1
        return -1;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B35_MinimumCommonValue_2540_2ptrs {
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;

        while (i < nums1.length && j < nums2.length){
            if (nums1[i] == nums2[j]){
                return nums1[i];
            }

            if (nums1[i] < nums2[j]){
                ++i;
            }else{
                ++j;
            }

        }
        // time:  O(n + m) - at worst case will traverse both arrays once
        // space: O(1)
        return -1;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B35_MinimumCommonValue_2540_Hashshet{
/*psudo:
* insert all nums1 into hashset,
* then traverse nums2 from top - if val contains at hash - return that value.*/
    public int getCommon(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();

        for (int i : nums1){
            set.add(i);
        }

        for (int i : nums2){
            if (set.contains(i))
                return i;
        }
//        time:  On+m traverse all nums for hashmap + traverse m till find shared value /or till exhausted.
//        space: O1n - hashset
        return -1;
    }
}


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B35_MinimumCommonValue_2540_Hashshet_BinarySearch {
    /*intuition:
    * use binary search - as input sorted.
    * traverse the smaller int[] of them both,
    * each time send to BinarySearch curr element,
    * if fonud - return that value, if not continue.
    * if exahusted smaller int[] - return -1*/
    public int getCommon(int[] nums1, int[] nums2) {
//        Optimization - we want to binary search bigger int[]!!
//        here its for case nums1 bigger!
        if (nums1.length < nums2.length){
            return getCommon(nums2, nums1);
        }

        for (int i : nums2) {
            if (binarySearch(nums1, i)){
                return i;
            }
        }

        return  -1;
    }
    private boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = left + ((right - left) / 2);

            if (arr[mid] == target){
                return true;
            }

            if (arr[mid] > target){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
//        time: O 2n log n - 1n-traverse smaller int[] - Onlogn-search in bigger int[]
//        space: O1.
            return false;
    }
}