import java.util.List;
import java.util.ArrayList;
/*problem:
* Given a string s, partition s such that every substring of the partition is a palindrome.
* Return all possible palindrome partitioning of s. */

/*solutions:
* 1st-backtrack DFS
* 2nd-backtrack DFS with dp memoization */

public class B7_PalindromePartitioning_131 {
    public List<List<String>> partition(String s) { //Start here
        List<List<String>> ans = new ArrayList<List<String>>();
        ArrayList<String> curPath =  new ArrayList<String>();
        dfs(0, ans, curPath, s);
//        time: O(n * 2^n):
//                          2^n-all combination possibilities
//                          On-each time find valid path - copy curPath to ans.
//        space: On
        return ans;
    }
    void dfs(
            int i, //represent curr index
            List<List<String>> ans,
            List<String> curPath,
            String s
    ) {

//        base case - when successfully reach end of word.
        if (i >= s.length()) {
            ans.add(new ArrayList<String>(curPath));
        }

        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s, i, j)) {
                curPath.add(s.substring(i, j + 1));
                dfs(j + 1, ans, curPath, s);
                curPath.remove(curPath.size() - 1);
            }
        }
    }


    boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B7_PalindromePartitioning_131_with_memo {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        boolean[][] memo = new boolean[s.length()][s.length()];
        dfs(ans, s, 0, new ArrayList<>(), memo);

        return ans;
    }

    void dfs(
            List<List<String>> ans,
            String s,
            int i, //cur start index
            List<String> curPath,
            boolean[][] memo
    ) {

//        base case - when successfully reach end of word.
        if (i >= s.length()){
            ans.add(new ArrayList<>(curPath));
        }


        for (int j = i; j < s.length(); j++) {

            if (    s.charAt(i) == s.charAt(j) &&
                    ( j - i <= 2 || memo[i + 1][j - 1]) ) {

                memo[i][j] = true;
                curPath.add(s.substring(i, j + 1));
                dfs(ans, s, j + 1, curPath, memo);
                curPath.remove(curPath.size() - 1);
            }
        }
    }
}