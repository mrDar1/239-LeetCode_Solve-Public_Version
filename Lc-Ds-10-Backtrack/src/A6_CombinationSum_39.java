import java.util.ArrayList;
import java.util.List;
/*problem:
* got candidates[] - can use each of them several time to get to sum of target.
* return all the possibilities
* constraint:
* all elements are distinct.
* target <= 40. */

/*intuition:
* brute force backtrack DFS: start by each one, if smaller than target go again, if bigger - prune */
public class A6_CombinationSum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curPath = new ArrayList<>();//cur path
        int curSum = 0;
        mybacktrack(ans, candidates,target,0, curPath, curSum);
        return ans;
    }

    private void mybacktrack(List<List<Integer>> ans,
                             int[] candidates,
                             int target,
                             int i, //represent cur deep level.
                             List<Integer> curPath,
                             int curSum) {

//        base case - successfully build valid combination. backtrack to check if there are others.
        if (curSum == target){
            ans.add(new ArrayList<>(curPath));//why use "new"? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

//        System.out.println(curPath);

        for (int j = i ; j < candidates.length ; ++j){
            int num =  candidates[j];

            if (curSum + num <= target){
                curPath.add(num);
                mybacktrack(ans, candidates, target ,j, curPath, curSum+num);
                curPath.remove(curPath.size() - 1);
            }
        }
    }
}

/*      complexity, long math! here is approximately:
        n==candidates.length
        T==target
        M==min(candidates)
        T/M==max depth of tree in this problem.
        time:  On^ (T/M) - using smaller number repeatedly until reach to target.
        space: O2*(T/M):  (T/M)-max depth of each recursion call as tree length.
                          (T/M)-each curPath max length
                          do not count output! */