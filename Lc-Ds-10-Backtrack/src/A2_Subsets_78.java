import java.util.ArrayList;
import java.util.List;

/*problem:
* Given an integer array nums of unique elements, return all possible subsets.
* The solution set must not contain duplicate subsets. Return the solution in any order. */

public class A2_Subsets_78 {
    public List<List<Integer>> subsets(int[] nums) {//start here
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, ans, 0, new ArrayList<Integer>());
//        time:  O(n * 2^N) - DFS with 2^n nodes, O1n-at each node make copy of cur
//        space: O 2n O1n-cur O1n-recursion call stack.
        return ans;
    }

    private void backtrack(int[] nums,
                           List<List<Integer>> ans,
                           int i,
                           ArrayList<Integer> cur) {

//        base case - reach end (since next "for-loop", never reach here! its here for clarity ):
        if (i > nums.length){
            return;
        }

//        add to ans new depth: nums[i] + nums[i+1] + nums[i+2]....+ nums[len-1]
        ans.add(new ArrayList<>(cur));

        for (int j = i ; j < nums.length ; ++j){
//            no need for "if" here, unlike permutation, in subset we already skip with "j=i" so no need to check "ans.contain"
            cur.add(nums[j]);
//            System.out.println(nums[j]);
            backtrack(nums, ans, j+1, cur);
            cur.remove(cur.size() - 1);
        }
    }
}