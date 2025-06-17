import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class A1_Permutations_46 {
    public static void main(String[] args) {
        Solution_A1_Permutations_46 obj_46 = new Solution_A1_Permutations_46();

        int[] nums1 = {1, 2, 3};
        System.out.println("Input: " + java.util.Arrays.toString(nums1));
        System.out.println("Output: " + obj_46.permute(nums1));
        System.out.println(" ");

//        int[] nums2 = {0, 1};
//        System.out.println("Input: " + java.util.Arrays.toString(nums2));
//        System.out.println("Output: " + obj_46.permute(nums2));
//        System.out.println(" ");
//
//        int[] nums3 = {1};
//        System.out.println("Input: " + java.util.Arrays.toString(nums3));
//        System.out.println("Output: " + obj_46.permute(nums3));
//        System.out.println(" ");
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/* problem:
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
permutation == contains all the elements of nums with no duplicates. \
note: no cant use same element twice! */

/*solutions:
* 1st - classic way.
* 2nd - slightly optimize with seenSet*/

/*intuition:
use preorder DFS:
add nums[i], from it, first try nums[i+1], nums[i+2], nums[i+3]..... nums[len-1]
when reach deepest leaf - backtrack to nums[len-2], nums[len-3],nums[len-4].... nums[0] then repeat. */
class Solution_A1_Permutations_46{
    public List<List<Integer>> permute(int[] nums) {// start here.
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums,ans, new ArrayList<Integer>());
//        time: long math! approximately:  (n! * n^2):
//              n! each brunch start with n possibilities, then n-1, n-2....
//              1n - traverse nums[]
//              1n - check cur.contains(i) - note its not a set! that a list so traverse all element 1 by 1.
//        space: O2n: O1n-cur
//                    O1n-recursion call stack, depth of call stack==len of cur (limited to n).
//                    (do not count ans output)
        return ans;
    }

    private void backtrack(int[] nums, List<List<Integer>> ans, ArrayList<Integer> cur) {

//        base case: reach leaf node (=end), finish that "brunch".
        if (cur.size() == nums.length){
            ans.add(new ArrayList<>(cur)); //why use new? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

        for (int i : nums){
            if (!cur.contains(i)){
                cur.add(i);
//                System.out.println(cur);
                backtrack(nums, ans, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A1_Permutations_46_optimize_with_set{
    public List<List<Integer>> permute(int[] nums) {// start here.
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        backtrack(nums,ans, new ArrayList<Integer>(), seen);
//        time: long math! approximately:  O(n! * o1n):
//              n! each brunch start with n possibilities, then n-1, n-2....
//              1n - traverse nums[]
//        space:
        return ans;
    }

    private void backtrack(int[] nums, List<List<Integer>> ans, ArrayList<Integer> cur, Set<Integer> seen) {

//        base case: reach leaf node (=end), finish that "brunch".
        if (cur.size() == nums.length){
            ans.add(new ArrayList<>(cur)); //why use new? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

        for (int i : nums){
            if (!seen.contains(i)){
                seen.add(i);
                cur.add(i);
//                System.out.println(cur);
                backtrack(nums, ans, cur, seen);
                cur.remove(cur.size() - 1);
                seen.remove(i);
            }
        }
    }
}