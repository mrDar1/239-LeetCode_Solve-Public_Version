import java.util.ArrayList;
import java.util.List;
/*problem:
return all possible subsets with length k, in range [1,n]
 n>=1. */

public class A3_Combinations_77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(1,n, k,ans, new ArrayList<Integer>() );
//        time:  long math! approximately:  O ((n! * k) / (n-k)! * k!)
//        space: O2k - for recursion's call stack and "curr".
        return ans;
    }

    private void backtrack(int i, int n, int k, List<List<Integer>> ans, ArrayList<Integer> cur) {
//        base case - reach k len.
        if (cur.size() == k){
            ans.add(new ArrayList<>(cur));
            return;
        }

        for (int j = i; j <= n; j++) {
            cur.add(j);
            backtrack(j+1, n, k, ans, cur);
            cur.remove(cur.size() - 1);
        }
    }
}