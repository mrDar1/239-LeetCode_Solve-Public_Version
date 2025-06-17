import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*problem:
return all valid combinations, with "k" digits, that sums to "n".
constraint: each digit can used once.
constraint: 2 <= k <= 9 */

/*solution:
* 1st - DFS backtrack
* 2nd - same as 1st, for fun, use LinkedList for path, and play with +1 at backtrack algorithm - just for fun. same. */

/*complexity:
time:  O(9! * k) / (9-k)! :
9!-each time we explore all 9 digits then 8 then 7 - as constraint only each digit once!
k- when find path, add curPath to ans take "k" time == curPath.len()
(9-k)! - denominator -  bound to it, constraint unique digit - only got 9 of those! with each k we left with 8, 7,6,.... possibilities.

space:O2k: 1k-recursions will go as deep as k. 1k-will store curPath until reach target/prune brunch.
(do not count space for result ans) */

public class A11_CombinationSum3_216 {
    /* intuition DFS BackTrack:
     * 3 edge cases:
     *      1-out of bound - prune.
     *      2-got to target! add to ans (as always with backtrack, use deep copy). after add prune.
     *      3-keep explore */
    public List<List<Integer>> combinationSum3(int k, int n) { //Star Here
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ArrayList<Integer> curPath = new ArrayList<>();

        backtrack(n, k, curPath, 0, ans);
        return ans;
    }

    protected void backtrack(int remain, //remain from n - every recursion send (n-cur)
                             int k,
                             ArrayList<Integer> curPath,
                             int i, //represent current index at path
                             List<List<Integer>> ans) {

//        base case - out of bound:
        if (remain < 0 || curPath.size() > k) {
            return;
        }

//        base case - successfully valid combinations:
        if (remain == 0 && curPath.size() == k) {
            ans.add(new ArrayList<Integer>(curPath)); //why use "new"? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

//        keep explore all possibilities, in DFS backtrack manner.
        for (int j = i + 1 ; j <= 9; ++j) { //why "j=i+1"? - advance to next index. remember constraint - must not repeat any digit! that way me make sure of it.
            curPath.add(j);
            backtrack(remain - j, k, curPath, j, ans);
            curPath.remove(curPath.size() - 1);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A11_CombinationSum3_216_2 {
    public List<List<Integer>> combinationSum3(int k, int n) { //Star Here
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        LinkedList<Integer> curPath = new LinkedList<Integer>();

        backtrack(n, k, curPath, 0, ans);
        return ans;
    }

    protected void backtrack(int remain, //remain from n - every recursion send (n-cur)
                             int k,
                             LinkedList<Integer> curPath,
                             int i, //represent current index at path
                             List<List<Integer>> ans) {

//        base case - out of bound:
        if (remain < 0 || curPath.size() > k) {
            return;
        }

//        base case - successfully valid combinations:
        if (remain == 0 && curPath.size() == k) {
            ans.add(new ArrayList<Integer>(curPath)); //why use "new"? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

//        keep explore all possibilities, in DFS backtrack manner.
        for (int j = i; j < 9; ++j) { //why "j=i+1"? - advance to next index. remember constraint - must not repeat any digit! that way me make sure of it.
            curPath.add(j + 1);
            backtrack(remain - j - 1, k, curPath, j + 1, ans);
            curPath.removeLast();
        }
    }
}
