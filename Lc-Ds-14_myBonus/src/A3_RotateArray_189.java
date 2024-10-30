/*problem:
* given int[] rotate all its elements to the right K times. */

/*solutions:
* 1st - my fist brute force - rotate entire arr by 1 each round -TLE
* 2nd - */

import java.util.Arrays;

public class A3_RotateArray_189 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        rotate(arr, 1);
    }

    public static void rotate(int[] nums, int k) {
        int rotations = k % nums.length ; // number of rotations

        for (int i = 0; i < rotations; i++) {
            singleRotate(nums);
        }
        System.out.println(Arrays.toString(nums));
    }

    private static void singleRotate(int[] nums) {
        int t = nums[nums.length - 1];

        for (int i = nums.length - 1; i > 0 ; --i) {
            nums[i] = nums[i-1];
        }
        nums[0] = t;
    }
}