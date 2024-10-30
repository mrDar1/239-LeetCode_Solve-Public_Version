import java.util.*;

/*problem:
* return the index of 2 elements that sum to "target"
* if there aren't - return false */

/*solutions:
 * 1st: with hashmap - 2 passes - O2n.
 * 2nd: with hashmap - 1 passes - O1n.
 * 3rd: as 2nd, in C-style (no hashmap) - fail LC test, important way for embedded programmer..
 * 4th: brute force
 * 5th: brute force with recursion - just for fun */

public class A1_TwoSum1 {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 4, 6, 8, 9, 14, 15};
//        int target = 13;
//        System.out.println(TwoSum(arr, target));

        int[] arr2 = {2,7,11,15};
        int target2 = 9;
        System.out.println(Arrays.toString(twoSum_two_Pass(arr2, target2)));
        System.out.println(Arrays.toString(twoSum_One_Pass(arr2, target2)));
    }

    public static int[] twoSum_two_Pass(int[] nums, int target){
        /*solution:
        * creating hash map of all element <value, index>.
        * traverse second time - if map.contain(compliment) - return indexes,
        * otherwise return false */
        int compliment = 0;
        Map<Integer, Integer> dic = new HashMap<>();

//        creating the hashmap:
        for(int i = 0 ; i < nums.length ; ++i){
            dic.put(nums[i], i);
        }
//        check it work:
//        for(int i : dic.keySet()){
//            System.out.println("key: " + i + " value: " + dic.get(i));
//        }

        for(int i = 0 ; i < nums.length ; ++i) {
            compliment = target - nums[i];

//            if we have compliment but its not the same number we working on!
            if(dic.containsKey(compliment) && dic.get(compliment) != i){
                return new int[] {i, dic.get(compliment)};
            }
        }
        // time: O2n - first for hashmap, 2nd for search compliment
        // space: On - hashmap.
//        if not found:
        return new int[]{-1,-1};
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    public static int[] twoSum_One_Pass(int[] nums, int target){
        /*solution:
        * create hashmap on the go - each time put at map<value, index>
        * but before that check if already contain compliment - if so can stop right away!*/
        int compliment = 0;
        Map<Integer, Integer> dic = new HashMap<>();

        for(int i = 0 ; i < nums.length ; ++i) {
            compliment = target - nums[i];

            if(dic.containsKey(compliment)) {
                return new int[]{i, dic.get(compliment)};
            }
            dic.put(nums[i], i);
        }
        // time: O1n space: On-hashmap.
//        if not found:
        return new int[]{-1,-1};
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    public static int[] twoSum_C_style(int[] nums, int target){
        int[] temp = new int[nums.length];


        for(int i = 0 ; i < nums.length ; ++i) {
            int cur = nums[i];

            temp[cur] += 1;

            if (target - cur < 0){
                continue;
            }

            if(temp[target-cur] != 0){
                return new int[]{i, target-cur};
            }
        }
        // time: O1n space: On-hashmap-like arr.

//        if not found:
        return new int[]{-1,-1};
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    public static int[] twoSum_brute_force(int[] nums, int target) {
        /*psudo:
         * just check all option.*/
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    return new int[] {i, j};
                }
            }
        }
//        time:  On^2
//        space: O1
        return null;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    public static int[] twoSum_brute_force_recursion(int[] nums, int target) {
//        time: On^2
//        space:O2-recursion - n-for each "helper", then we need another n to check possibilities - but its complete before next round.
        return helper(nums, target, 0);
    }

    private static int[] helper(int[] nums, int target, int start) {
        if (start > nums.length) {
            return new int[] {-1,-1};
        }

        int[] result = inner_helper(nums, target, start, start + 1);

        if (result[0] != -1 && result[1] != -1){
            return result;
        }

        return helper(nums, target, start + 1);
    }

    private static int[] inner_helper(int[] nums, int target, int start, int curr) {
        if (curr >= nums.length){
            return new int[] {-1,-1};
        }

        if (nums[start] + nums[curr] == target){
            return new int[] {start, curr};
        }

        return inner_helper(nums, target, start, curr + 1);
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


////solve again after few weeks:
//    public static int[] twoSum_hashmap_practice(int[] nums, int target) {
//        Map<Integer, Integer> map = new HashMap<>(); // <value, index>
//
//        for (int i = 0; i < nums.length ; i++) {
//            int compliment = target - nums[i];
//
//            if (map.containsKey(compliment)){
//                return new int[]{i, map.get(compliment)};
//            }
//
//            map.put(nums[i], i );
//        }
//
//        return new int[] {-1,-1};
//    }
}
