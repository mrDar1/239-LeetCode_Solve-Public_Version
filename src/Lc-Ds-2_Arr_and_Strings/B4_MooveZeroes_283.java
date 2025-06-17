import java.util.*;
/*problem:
* given arr with mix elements - return same arr, with all 0 as last elements */

/*3 solutions:
1st - first pass:  count number of zeros.
      second pass: each char that not 0 put instead nums[l++]. from there till end put 0.
2nd - 2 ptrs: if nums[r] not 0, nums[l++] = nums[r] - so skip all zero's.
        from l till end - fill with 0.
3rd - Swap - each time "push" the 0 forward.

first 2 O2n more intuitive, second On - better! with swap.*/

//problem constraint: working on original array so need to change the call-method name each time.
public class B4_MooveZeroes_283 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};
//        [0,1,0,3,12] - Output: [1,3,12,0,0]
//        [0,0,1] - Output: [1,0,0]
        moveZeroes3(arr);
        System.out.println(Arrays.toString(arr));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static void moveZeroes1(int[] nums) {
        int c = 0; // count number of 0.
        int l = 0;

//        counting 0:
        for (int i : nums) {
            if (i == 0) {
                ++c;
            }
        }

//        put numbers at front:
        for (int i : nums) {
            if (i != 0) {
                nums[l++] = i;
            }
        }
//      add 0 to end.
        for (; c > 0; --c) {
            nums[l] = 0;
            ++l;
        }
        //time: O2n - first counting 0, second building the new arr.
        // space: O1.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static void moveZeroes2(int[] nums) {
        int lastnonzero = 0; //index to nonzero char location
        int i = 0;

        for (; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[lastnonzero] = nums[i];
                ++lastnonzero;
                /* can write it in single line: nums[lastnonzero++] */
            }
        }

        for (i = lastnonzero; i < nums.length; ++i) {
            nums[i] = 0;
        }
        //time: O1n - first time to copy the numbers, continue from that place and put zeros.
        // space: O1
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    third way solution:
    public static void moveZeroes3(int[] nums) {
        int lastnonzero = 0; //index to nonzero char location
        int cur = 0;

        for ( ; cur < nums.length; ++cur) {
            if (nums[cur] != 0) {
                Swap(nums, lastnonzero++, cur);
            }
        }
        //time: O1n
        // space: O1
    }
    public static void Swap( int[] arr, int l, int r){
        int t = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
    }
}

