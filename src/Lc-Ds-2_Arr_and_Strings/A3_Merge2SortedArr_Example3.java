import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*solutions:
* 1st-use java ArrayList.
* 2nd-same with C-style.*/

public class A3_Merge2SortedArr_Example3 {
    public static void main(String[] args) {
        int [] arr1 = {1,4,7,20,21,22};
        int [] arr2 = {3,5,6};

        A3_Solution_Merge2SortedArr obj_example3_Merge2SortedArr = new A3_Solution_Merge2SortedArr();
        System.out.println(Arrays.toString(obj_example3_Merge2SortedArr.Merge2SortedArr(arr1, arr2).toArray()));

        System.out.println("using As C array:");
        System.out.println(Arrays.toString(obj_example3_Merge2SortedArr.Merge2SortedArr_with_C_Arr(arr1, arr2)));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A3_Solution_Merge2SortedArr {
    //    use: ArrayList / dynamic arrays / Vectors / List:
    public List<Integer> Merge2SortedArr(int[] arr1, int[] arr2) {
        List<Integer> ans = new ArrayList<>();
        int i1 = 0; //index of arr 1
        int i2 = 0; //index of arr 2

        while (i1 < arr1.length && i2 < arr2.length) {
            if (arr1[i1] < arr2[i2]) {
                ans.add(arr1[i1]);
                ++i1;
            } else {
                ans.add(arr2[i2]);
                ++i2;
            }
        }

        while (i1 < arr1.length) {
            ans.add(arr1[i1]);
            ++i1;
        }

        while (i2 < arr2.length) {
            ans.add(arr2[i2]);
            ++i2;
        }

        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int[] Merge2SortedArr_with_C_Arr(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] arr3 = new int[len1 + len2];

        int i1 = 0; //index of arr 1
        int i2 = 0; //index of arr 2
        int i3 = 0; //index of arr 3

        while (i1 < len1 && i2 < len2) {
            if (arr1[i1] > arr2[i2]) {
                arr3[i3] = arr2[i2];
                ++i3;
                ++i2;
            } else {
                arr3[i3] = arr1[i1];
                ++i3;
                ++i1;
            }
        }

        while (i1 < len1) {
            arr3[i3] = arr1[i1];
            ++i3;
            ++i1;
        }
        while (i2 < len2) {
            arr3[i3] = arr2[i2];
            ++i2;
            ++i1;
        }
        return arr3;
    }
//    time: On
//    space:O1 (not counting output ans int[]).
}