import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*problem:
* return all possibilities of increasing subsequences of at least 2 elements
* remember subsequences == contiguous section of the original arr/string that in the same order, but could be with deleted element. */

class B5_NondecreasingSubsequences_491 {
    public List<List<Integer>> findSubsequences(int[] nums) {//start here
        Set<List<Integer>> ans = new HashSet<List<Integer>>(); //why use set? - might be duplicates among the found subsequences, this way avoid them.
        List<Integer> curPath = new ArrayList<Integer>();
        int i = 0; //index to number of digits in curPath

        backtrack(i, nums, curPath, ans);

        return new ArrayList<>(ans); //convert set to List<List<Integer>> - as in problem signature.
    }

    private void backtrack(int i, //index to number of digits in curPath
                           int[] nums,
                           List<Integer> curPath,
                           Set<List<Integer>> ans) {

//        base case - valid traverse through all nums[] , add to ans.
        if (i == nums.length) {
            if (curPath.size() >= 2) { //constraint: must be longer than 2 elements.
                ans.add(new ArrayList<>(curPath));
            }
            return;
        }

//        if keep increasing order - explore even longer possible subsequences.
        if (curPath.isEmpty() || curPath.get(curPath.size() - 1) <= nums[i]) {

            curPath.add(nums[i]);
            backtrack(i + 1, nums, curPath, ans);
            curPath.remove(curPath.size() - 1);
        }

//       try to create combination of longer subsequences from next element (think about case of {0,9,1,2,3} - we wouldn't want to start from 0/9, but from 1)
        backtrack(i + 1, nums, curPath, ans);
    }
}

/*      complexity:
        time: O(2^n * n) :
            2^n - all possible paths,
            *n  - each time of the n times, add curPath to ans doing hashset so On

        space: O(2n):
            O1n-recursion call stack max depth,
            O1n-each curPath (if we count also output ans part - its O2^n) */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*how to bitmask work here:
at forst build bitmask - of huge number, that every index 0==not in ans, 1==do int ans.
 example: 1,4 would represent as(left to right): 0,1,0,0,1 - so basically instead of storing actual number,
  store represent in huge binary like-array.*/

class B5_NondecreasingSubsequences_491_BitWise {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> ans = new HashSet<List<Integer>>();

        for (int bitmask = 1; bitmask < (1 << nums.length); bitmask++) { //(1<<nums.length ==2^nums.len.
            List<Integer> curPath = new ArrayList<Integer>();

            // check the i-th bit of the bitmask
            for (int i = 0; i < nums.length; i++) {
                if (((bitmask >> i) & 1) == 1) {
                    curPath.add(nums[i]);
                }
            }
            if (curPath.size() >= 2) {
                // check whether the sequence is increasing
                boolean isIncreasing = true;
                for (int i = 0; i < curPath.size() - 1; i++) {
                    isIncreasing &= curPath.get(i) <= curPath.get(i + 1);
                }
                if (isIncreasing) {
                    ans.add(curPath);
                }
            }
        }
        return new ArrayList<>(ans);
    }
}

/*      complexity:
        time: O(2^n * n^2) :
            2^n - check 2^n bitmask,
            *n  - each time of the n times, add curPath to ans doing hashset so On

        space: O(n):
            O1n-each curPath (if we count also output ans part - its O2^n) */
