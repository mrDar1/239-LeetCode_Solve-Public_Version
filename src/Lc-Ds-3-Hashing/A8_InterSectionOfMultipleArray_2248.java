/*problem:
* given int[][] - return the element that shared by all rows - sorted.
* note - constrains: in each  2nd dimesntion all values are uniqe!!
 so its much easier!!! ([][j] - in every 'j' - all elements are unique). */

import java.util.*;
public class A8_InterSectionOfMultipleArray_2248 {
    public static void main(String[] args){
        int[][] arr = {{3,1,2,4,5},{1,2,3,4},{3,4,5,6}};
        System.out.println(intersection(arr));
    }

    public static List<Integer> intersection(int[][] nums){
/*    psudo:
1. build hashmap <value, freq of each element>
2. traverse hashmap, if freq==len - add to ans.
3. use java quick sort and return ans. */
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> counts = new HashMap<>();
        int len = nums.length; // will bring only the first dimension: "int[i][j] nums" - will return i

        //counting each letter from both dimension of arr, and store it in one HashMap.
        for(int[] arr : nums){
            for(int i : arr){
                counts.put(i, counts.getOrDefault(i, 0) + 1);
            }
        }

        for(int key : counts.keySet()){
            if(counts.get(key) == len) {//constraints: each value in i is unique!! much easier!!
                ans.add(key);
            }
        }

        Collections.sort(ans);
        //time: O2n + On log n: 1st-iterate the int[][] and build hashmap. 2nd-build List. Onlogn-Java sort.
        // space: O1n
        return ans;
    }
}