import java.util.*;
/*problem:
* return the number of good pairs.
* good pairs:
*   1. i < j
*   2. num[i] == num[j] */

/*note - if not using brute force we got some math formula needed as:
if got 1 element we got 0 good pair,
if got 2 then its 1,
but 3 is 3!!!
and 4 is 6!!!!
and 5 is 10!!!
and 6 is 15!!!
think how to do it!! */

/*solutions:
* 1st- brute force
* 2nd- hashmap - is best!
* 3rd- use combinatorial mathematics formula */

//3 solutions: 1-lc good way. 2-lc brute force and 3-my last try use math function.
public class B10_NumberofGoodPairs_1512 {
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,1};
//        int n = numIdenticalPairs_bruteforce(arr);
//        System.out.println(n);
        int n = numIdenticalPairs(arr);
        System.out.println(n);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int numIdenticalPairs_bruteforce(int[] nums) {
        int sum = 0;

        for(int i = 0 ; i < nums.length ; ++i){
            for (int j = i + 1 ; j < nums.length ; ++j){
                if (nums[i] == nums[j])
                    ++sum;
            }
        }
        return sum;
        //time: On^2 space: O1
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    traverse and each time add count to hashmap <element, freq>,
    before adding to hashmap we ans+= map.get so will give us exactly what we need */
    public static int numIdenticalPairs(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); //<element, freq>

        for (int i : nums){
            ans += map.getOrDefault(i, 0);
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
//        time: O1n
//        space: O1n
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    * build freq map <element, freq>
    * traverse map keys - only if apears more than 2 - check how much it apears with formula */
    public static int numIdenticalPairs_mylast_try_use_math_formula(int[] nums) {
        int sum = 0;
        int t = 0; //temp val, split lines to increase readability.
        Map<Integer, Integer> map = new HashMap<>(); //<element, freq>

//        build count map:
        for (int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i : map.keySet()){
            if (map.get(i) > 1) {
                t = (map.get(i) * (map.get(i) - 1) / 2);
                sum += t;
            }
        }

        return sum;
        //time: On+m-1st-hashmap, m=unique numbers in n.
        // space: On - hashmap
    }
}