import java.util.Arrays;
import java.util.HashSet;

/*problem:
 * given sorted int[] - each element appears exactly twice.
 * only one element appears once - return its value. */

/*solutions:
 * 1st - brute force use hashset.
 * 2nd - brute force use compare.
 * 3rd - same as 2nd but more elegant.
 * 4th - binarySearch
 * 5th - binarySearch improved - use property that unique must be at even index with no pair. */

public class B6_SingleElementinSortedArray_540 {
    public static void main(String[] args) {
        Solution_B6_SingleElementinSortedArray_540 obj_540 = new Solution_B6_SingleElementinSortedArray_540();
        int[] nums1 = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        int[] nums2 = {3, 3, 7, 7, 10, 11, 11};

        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + obj_540.singleNonDuplicate_first_brute(nums1)); //desire: 2

        System.out.println("\nTest Case 2:");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + obj_540.singleNonDuplicate_first_brute(nums2)); //desire: 10
    }
}

class Solution_B6_SingleElementinSortedArray_540 {
    /*psudo: each time add and remove from set - return the only remain element. */
    public int singleNonDuplicate_first_brute(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int i : nums){
            if (!set.contains(i)){
                set.add(i);
            }else {
                set.remove(i);
            }
        }
        for (int i : set){
            return i;
        }
//        time: O1n
//        space: O1 (hold only 1)
        return -1;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
     * each time do nums[i] - nums[i+1]
     * if change from 0 - return the first. */
    public int singleNonDuplicate_2nd_brute_force(int[] nums) {
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            int x = nums[i] - nums[i + 1];

            if (x != 0)
                return nums[i];
        }
        return nums[nums.length - 1];
    }
    public int singleNonDuplicate_2nd_brute_force_more_elegant(int[] nums) {

        for (int i = 0; i < nums.length - 1 ; i = i + 2) {
            if (nums[i] != nums[i + 1])
                return nums[i];
        }
        return nums[nums.length - 1];
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    /* intuition:
     * need to find the single element that has no duplicates.
     * using regular BS and add boolean "halvesAreEven" - if true - unique element not here. go to other side.
     * jump in 2 - as its moving in pairs. */

    /*psudo:
     * left==0, right==nums[].len-1;
     * find regular mid
     * add boolean "halvesAreEven" - if true - unique element not here. go to other side.
     * if nums[mid] == nums[mid+1]
     *      if bool==true
     *          l = mid + 2
     *          else: r = mid - 1
     * else if nums[mid] == nums[mid-1]
     *      if bool==true
     *          r = mid - 2;
     *          else: l = mid + 1;
     * else -we're at midd return it.
     *
     * return left.*/
    public int singleNonDuplicate_binary_search(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right){
            int mid = left + (right - left) / 2;
            boolean halvesAreEven = (right - mid) % 2 == 0 ; //if true: right half has even number.
//            so it wont be in it, must be in other side.

            if (nums[mid] == nums[mid + 1]){
                if (halvesAreEven){
                    left = mid + 2;
                }else {
                    right = mid - 1;
                }
            }

            else if (nums[mid] == nums[mid - 1]) {
                if (halvesAreEven) {
                    right = mid - 2;
                }else {
                    left = mid + 1;
                }
            }

            else {      //middle is the unique element.
                return nums[mid];
            }
        }
//        time: O log n - BS
//        spcae: O1
        return nums[left];
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*intuition:
    * The single element is at the first even index not followed by its pair!!!
    * until the single element, all elements start on even index!! */
    public int singleNonDuplicate_BS_only_on_duplicates(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
//        time: Log n/2 - BS on half the element!!
//        space: O1
        return nums[left];
    }
}
