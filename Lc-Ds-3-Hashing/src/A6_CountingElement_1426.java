import java.util.*;
/*problem:
* return how many numbers has again +1 */

/*solutions:
* 1st - use hashset
* 2nd - use sort and then find.*/

public class A6_CountingElement_1426 {
    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 3, 5, 5, 7, 7};
        int n = countElements(arr);
        System.out.println(n);
    }

    public static int countElements(int[] arr) {
        /*psudo:
         * store all int[] at Hashset.
         * traverse set again - each time map.contain(x+1) then count++ */
        Set<Integer> myset = new HashSet<>();
        int c = 0;

        for (int i : arr) {
            myset.add(i);
        }
        for (int i : arr) {
            if (myset.contains(i + 1)) {
                ++c;
            }
        }
//       time: O2n
//       space: O1
        return c;
    }
    public static int countElements_Sort(int[] arr) {
        /* intuition:
         * sort.
         * since arr now sorted, check if i+1==cur+1. - if do add to counter.
         * since we may have duplicates - count them. */

        int duplicate = 1; // how many instances we have of that number
        int c = 0; //count how many x+1 are there.

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i+1]){
                duplicate++;
                continue;
            }

            if (arr[i] + 1 == arr[i+1]){
                c += duplicate;
                duplicate = 1;
                continue;
            }

            duplicate = 1;
        }

//       time: O2n (logn) - sort, O1-traverse
//       space: O1
        return c;
    }
}