import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/* problem:
given subset(nums1) and original arr(nums2), foreach number at subset search in original arr,
the next bigger number to its right.

return: int[] that fill with the value of greater element to the right - if non found put -1
constraint: no duplicates!
constraint: All integers of nums1 also appear in nums2. */

/*3 solution:
1st: my brute force - found here nice bug!
2nd: brute force using hash.
3rd: optimized using stack.*/

public class A11_NextGreatElement_496 {
    public static void main(String[] args)
    {
        int[] nums1_1 = {4,1,2};
        int[] nums2_1 = {1,3,4,2};
        //Output: [-1,3,-1]

        int[] nums1_2 = {2,4};
        int[] nums2_2 = {1,2,3,4};
        //Output: [3,-1]

        Solution_NextGreatElement_496 obj_496 = new Solution_NextGreatElement_496();

//        System.out.println("my first testing brute force:");
//        System.out.println(Arrays.toString(obj_496.nextGreaterElement_mybruteforce(nums1_1,nums2_1)));
//        System.out.println("my second testing brute force:");
//        System.out.println(Arrays.toString(obj_496.nextGreaterElement_mybruteforce(nums1_2,nums2_2)));
//        System.out.println(" ");
////
////
        System.out.println("brute force with hash:");
        System.out.println("first testing brute force:");
        System.out.println(Arrays.toString(obj_496.nextGreaterElement_brute_with_hash(nums1_1,nums2_1)));
        System.out.println("second testing brute force:");
        System.out.println(Arrays.toString(obj_496.nextGreaterElement_brute_with_hash(nums1_2,nums2_2)));
        System.out.println(" ");
//
//        nextGreaterElement_optimized_stack_with_hash obj_496_stack = new nextGreaterElement_optimized_stack_with_hash();
//        System.out.println("optimized with Stack:");
//        System.out.println("first testing Stack:");
//        System.out.println(Arrays.toString(obj_496_stack.nextGreaterElement(nums1_1,nums2_1)));
//        System.out.println("second testing Stack:");
//        System.out.println(Arrays.toString(obj_496_stack.nextGreaterElement(nums1_2,nums2_2)));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_NextGreatElement_496 {
    //my first naive approach - TLE submission.
    /*psudo:
    * for each char in nums1 first initialize to -1.
    * then, if found that in nums 2 - find greater element.*/
    public int[] nextGreaterElement_mybruteforce(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            boolean isfound = false;
            ans[i] = -1;

            for (int j = 0 ; j < nums2.length; ++j) {
                if (nums1[i] == nums2[j]){
                    isfound = true;
                    continue; //same number cant consider the greater element.
                }

//                isfound = nums1[i] == nums2[j];
//                note: above line is not right!! very long time bug!!!
//                both are the same, but, above line wil change "isfound" each iteration.
//                but that not what we want!! we want if "isfound" 1 time keep iterate until find bigger than it.

                if (isfound && nums1[i] < nums2[j]) {
                    ans[i] = nums2[j];
                    break;
                }
            }
        }
//        time: On^2.
//        space O1
        return ans;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    * first build hashmap of indexes.
    * traverse nums1 foreach element in it, find index from hashmap at nums2 - if not there asign -1 */
    public int[] nextGreaterElement_brute_with_hash(int[] nums1, int[] nums2) {
        int j;
        int[] res = new int[nums1.length]; //result[]
        HashMap<Integer, Integer> map = new HashMap<>(); //<value , index of val at nums2 (original arr)>

//        building hashmap:
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }

//        debugging
//        for (int key : map.keySet() ){
//            System.out.println("the key is:" + key +" val is:" + map.get(key));
//        }
//        System.out.println(" ");

        for (int i = 0; i < nums1.length; i++) {
            res[i] = -1;
            for (j = map.get(nums1[i]) + 1 ; j < nums2.length ; j++) { //why do we need "j=...+1" - since we dont want the element itself just bigger from it to the right!
                debugging:
                System.out.println("j is: " + j);
                if (nums1[i] < nums2[j]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
//        m==nums1
//        n==nums2
//          time: Om*n
//          space: O1n
        return res;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* use monotonic decreasing stack.
* build hashmap for nums2 for each element mapped it next greater element.
* convert hashmap to res int[] */
class nextGreaterElement_optimized_stack_with_hash {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();           // store values from nums2 ; push every element on the stack if it is less than the previous element on the top of the stack
        HashMap<Integer, Integer> map = new HashMap<>();// <element, next great element to the right>

//        creating hashmap & stack :
        for (int i = 0 ; i < nums2.length ; ++i) {
            while ( !stack.empty() && stack.peek() < nums2[i] ){
                map.put(stack.pop(), nums2[i]);//debugging-map:<1,3> <3,4>
            }

            stack.push(nums2[i]); //debugging-stack:4,2
        }

        // for remaining elements in the stack, there is no next greater element
        while (!stack.empty()){
            map.put(stack.pop(), -1); //debugging-map:<1,3> <3,4> + <2,-1> <4,-1>
        }

//        convert hashmap into res[]
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
//        m==nums1
//        n==nums2
//        time: O2n+m: 1n-for hashmap; 1n for put in res.
//        space: O2n - hashmap is 1n, stack-will store at most 1n values.
        return res;
    }
}