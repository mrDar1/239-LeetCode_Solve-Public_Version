import java.util.*;
/*problem: given binary int[] nums,
return max len of contiguous sub-array with equal number of 1,0 */

/*psudo:
* use "isbalance" var:
* 0==balance,
* positive number==got x 1 more than 0,
* negative number==got x 0 more than 1. example: isbalance=3 - then got 3 more 1 than 0.
*
* traverse nums[] - each time update isbalance, and if 0 - update our longest so far sub-array.
*
* */



//didnt get this one...

public class A15_CountigousArra_525 {
    public static void main(String[] args) {
        Solution_A15_CountigousArra_525_hashmap_with_if obj_525 = new Solution_A15_CountigousArra_525_hashmap_with_if();
        int[] arr = {0, 1, 0}; // ans 2
        System.out.println(obj_525.findMaxLength(arr));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class Solution_A15_CountigousArra_525_brute_force{
    public int findMaxLength(int[] nums){
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            int isbalance = nums[i] == 0 ? -1 : 1 ;
            for (int j = i + 1; j < nums.length; j++) {
                isbalance += nums[j] == 0 ? -1 : +1;

                if (isbalance == 0){
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
//        time: On^2
//        space:O1 - only 2 vars required.
        return maxLen;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A15_CountigousArra_525_hashmap_with_if {
    public int findMaxLength(int[] nums) {
        int isbalance = 0; //if the number positive=have more 1, negative=have more 0, 0=equals.
        int maxlen = 0; // length of "balance" sub array.
        Map<Integer, Integer> map = new HashMap<>(); // <isbalance, index>

        for(int i = 0;i < nums.length;i++) {
            isbalance += (nums[i] == 1) ? 1 : -1;
            // Array from index 0 to i contains equal number of 0's and 1's
            if(isbalance == 0){
                maxlen = Math.max(maxlen, i+1);
            }

            if(map.containsKey(isbalance)) {
                maxlen = Math.max(maxlen, i - map.get(isbalance));
            } else {
                map.put(isbalance, i);
            }
        }
//        time O1n - entire array traverse once.
//        space: O1n - max size of hashmap will be n - if all elements are 1,0.
        return maxlen;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A15_CountigousArra_525_hashmap_with_put {
    public static int findMaxLength(int[] nums) {
        int isbalance = 0; //if the number positive=have more 1, negative=have more 0, 0=equals.
        int maxlen = 0; // length of "balance" sub array.
        Map<Integer, Integer> map = new HashMap<>(); // <isbalance, index>
        map.put(0, -1); // ??? Initial condition: balance 0 at index -1. for cases where the balance start from 0. ???

        for(int i = 0;i < nums.length;i++) {
            isbalance += (nums[i] == 1) ? 1 : -1;

            if(map.containsKey(isbalance)) {
                maxlen = Math.max(maxlen, i - map.get(isbalance));
            } else {
                map.put(isbalance, i);
            }
        }
//        time O1n - entire array traverse once.
//        space: O1n - max size of hashmap will be n - if all elements are 1,0.
        return maxlen;
    }
}

//next time - implement brute force and recurion of brute force!! then try to understand that shit again...