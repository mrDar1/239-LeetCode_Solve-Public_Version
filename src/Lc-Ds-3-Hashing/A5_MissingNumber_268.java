/*problem:
* given nums[] - return missing number,
* if all are there return Maxval+1. */

/*5 solutions:
* 1st: hashset compare with max val - first messy way...
* 2nd: same as 1 - more elegant.
* 3rd: gause formula - add all numbers in full arr, then reduce all number from missing arr.
* 4th: gause formula - with BitWise
* 5th: gause formula - with BitWise, reduce to O1n */

import java.util.*;
public class A5_MissingNumber_268 {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3,5,6,7};
//        System.out.println(missingNumber1(arr));
        System.out.println(missingNumber2(arr));
        System.out.println(missingNumber3(arr));
        System.out.println(missingNumber4(arr));
        System.out.println(missingNumber5_improve(arr));
//        check bitwise:
//        int n = 2;
//        System.out.println(n^n);
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    public static int missingNumber1(int[] nums) {
        /*psudo:
        * traverse nums[] and insert all to hashmap + find max val.
        * traverse from 0-max and return !set.contain()
        * if didnt find return max+1*/
        int max = 0;
        Set<Integer> myset = new HashSet<>();

        if (nums.length == 1) return 0; //edge case

//        creating hashset and finding max val:
        for (int i : nums) {
            myset.add(i);
            max = Math.max(max, i);
        }
//      searching all the numbers from zero till max check who's missing.
        for (int i = 0; i < max; ++i) {
            if (!myset.contains(i))
                return i;
        }
        return (max + 1);
        //time: O2n - hashset and find missing.
        // space: On hashset.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int missingNumber2(int[] nums) {
        /*  2nd: hashset - same as before more elegant. */
        Set<Integer> myset = new HashSet<>();

//        creating the hashset:
        for(int i : nums){
            myset.add(i);
        }

        for(int i = 0 ; i < nums.length ; ++i){
            if(!myset.contains(i)){
                return i;
            }
        }
//        time: O2n-hashmap+searchig missing
//        space: On
        return nums.length;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int missingNumber3(int[] nums) {
/*  3rd: gause formolla (add up formula).
    psudo:
    1. add up all the number - without missing one! (run until nums.len),
    2. traverse nums[] - each time decrease from that sum cur.
    return the remain.
    note - no need to deal with edge case!! */

        int t = 0;

        for(int i = 0 ; i <= nums.length ; ++i){
            t += i;
        }

        for (int i : nums){
            t -= i;
        }
//        time: O2n - create t, substract from t and compare.
//        space: O1
        return t;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int missingNumber4(int[] nums) {
/*4th: gause formula - but insead regular operands use Bitwise!
* 1-traverse and Xor all numbers, wihtout missing one!
* 2-traverse and Xor all numbers, wiht missing one!
* 3- Xor this 2 numbers - and what we get is the missing one! */
        int actualxor = 0;
        int expectedxor = 0;

        for(int i = 0; i <= nums.length ; ++i){
            expectedxor ^= i;
        }

        for(int i = 0; i < nums.length ; ++i){
            actualxor ^= nums[i];
        }
//        time: O2n-actual Xor+ expected Xor
//        space: O1
        return actualxor ^ expectedxor;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int missingNumber5_improve(int[] nums) {
/*5th - Xor improved (1 pass):
* traverse nums[] - each time Xor curr number with number of the full arr(with no missing number).
* then Xor that number with same var - so all repeating numbers will cancel themselves.
* the only number remain is the missing one!
* that why we start with nums.maxVal - so if wont find any non-repeating number, return Max. */
        int actualxor = nums.length;//why nums.length? cause if all numbers are here then return "nums.length" - question restriction.

        for(int i = 0; i < nums.length ; ++i){
            actualxor ^= (nums[i] ^ i);
        }
//        time: O1n
//        space: O1
        return actualxor;
    }
}