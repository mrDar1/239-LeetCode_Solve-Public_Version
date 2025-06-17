import java.util.*;
/*problem: given positive and negative arr, return its arr after square each element - sorted. */
/*solution:
* 1st-square each then sort with built-in method (2 traversal).
* 2nd-reduced to 1 traversal - use 2 ptrs - first check max vale after absolute value only then square. */
public class A6_SquareOfSortedArr_977 {
    public static void main(String[] args) {
        int[] arr1 = {-4, -1, 0, 3, 10};
        int[] temparr1 = new int[4];

        int[] arr2 = {-4, -1, 0, 3, 10};
        int[] temparr2 = new int[4];

        temparr1 = sortedSquares(arr1);
        System.out.println("intuitive:" + Arrays.toString(temparr1));

        temparr2 = sortedSquares_efficiency(arr2);
        System.out.println("efficiency:" + Arrays.toString(temparr2));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int[] sortedSquares(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = arr[i] * arr[i];
        }
        Arrays.sort(arr);
//        time: On + n log n == O n log n: n for square, n log n: for sort.
//        space: On / O log n - in java, implement as variant of QuickSort, Ologn. but if the array is highly structured (has few sorted subarrays) its On.
        return arr;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*motivation:
    * the largest numbers are the one on the right (always positive)
    * but also the one on the left (always negative!)
    * so each round we check whos bigger and put it at end.*/
    public static int[] sortedSquares_efficiency(int[] arr) {
        int l = 0;
        int len = arr.length;
        int r = len - 1;
        int[]ans = new int[len];

        for(int i = len - 1 ; i >= 0 ; --i){
            if(Math.abs(arr[l]) < Math.abs(arr[r])){
                ans[i] = arr[r] * arr[r];
                --r;
            }else {
                ans[i] = arr[l] * arr[l];
                ++l;
            }
        }
//        time: O1n
//        space: O1 (if take output as count On)
        return ans;
    }
}