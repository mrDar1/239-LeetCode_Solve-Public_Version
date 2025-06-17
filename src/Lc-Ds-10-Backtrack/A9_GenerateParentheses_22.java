import java.util.*;

/*problem:
* Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses */

/*motivation:
* since every n - is both () we would use loop till 2*n */

/*3 solution:
* 1-brute force
* 2-BackTrack, similar to brute, optimize with pruning while traverse.
* 3-divide and conquer - nice theoretical algorithm. copied. */

//1st brute forceL
public class A9_GenerateParentheses_22 {
    /*intuition:
     * first build all possibilities, then check what valid.*/
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        Queue<String> q = new LinkedList<>(Arrays.asList(""));

        while (!q.isEmpty()) {
            String curString = q.poll();

            if (curString.length() == 2 * n) { //every n  is both ( and )
                if (valid(curString)) {
                    ans.add(curString);
                }
                continue;
            }

            q.offer(curString + '(');
            q.offer(curString + ')');
        }

        return ans;
    }

    private boolean valid(String curString) {
        int leftCount = 0;

        for (Character c : curString.toCharArray()) {
            if (c == '(') {
                leftCount++;
            } else {
                leftCount--;
            }
            if (leftCount < 0) { // constrain: at any given moment cant be ) before ( !!!!!
                return false;
            }
        }
        return leftCount == 0;
    }
//    complexity:
//        input size: 2n - since for every ( there is also ).
//        time:  O(2^2n * n):
//                           2^2n- have 2 choices '(' or ')'. with depth of 2n.
//                           O1n-validity check.
//        space: O(2^2n * n):  same as time! since first traverse and store all possibilities, then traverse for valid ans.
//        space of output (usually dont count here interesting):
//        n^i, where i==matching catalan number.
//        asymptotically bounded by, 4^n/ (n * (root n) )
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A9_GenerateParentheses_22_backtrack {
    /*intuition:
     similar to brute force - but, prune invalid brunches while traversing. */

    /* psudo:
      use a StringBuilder, each backtrack with 3 if:
      1-success base case - add to ans.
      2-invalid path - got unmatched ')' - prune. to do it simply "ignore" and would vanish.
      3-valid path, 2 options both valid (so as in backtrack explore both):
                            1-use more '('
                            2-has enough '(' so also can use ')'   */

    public List<String> generateParenthesis(int n) {
        int countLeft = 0;  // count number of '('
        int countRight = 0; // count number of ')'
        List<String> ans = new ArrayList<>();
        StringBuilder curPath = new StringBuilder();

        backtrack(n, ans, curPath, countLeft, countRight);

//        time:  O(2^n*2 / sqrt(n)) == O(4^n / sqrt(n))   (math rules of exponents):
//              O(2^n*2) - can choose one of 2 options, for 2n times.
//              sqrt(n) - pruning.
//              n-since each time change StringBuilder to String.
//        sapce: O4n==On:
//              2n-deepest recursion.
//              2n-curPath of parentheses.
        return ans;
    }

    private void backtrack(int n, List<String> ans, StringBuilder curPath, int countLeft, int countRight) {

//        base case - successfully valid combinations.
        if (curPath.length() == n * 2) {
            ans.add(curPath.toString());
            return;
        }

        if (countLeft < n) {
            curPath.append( '(' );
            backtrack(n, ans, curPath, countLeft + 1, countRight);
            curPath.deleteCharAt(curPath.length() - 1);
        }

        if (countLeft > countRight) {
            curPath.append( ')' );
            backtrack(n, ans, curPath, countLeft, countRight + 1);
            curPath.deleteCharAt(curPath.length() - 1);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A9_GenerateParentheses_22_backtrack_divide_and_conquer {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList();

        if (n == 0) {
            return new ArrayList(Arrays.asList(""));
        }

        for ( int leftCount = 0; leftCount < n; ++leftCount) {
            for (String leftString : generateParenthesis(leftCount)) {
                for (String rightString : generateParenthesis(n - 1 - leftCount)) {
                    answer.add("(" + leftString + ")" + rightString);
                }
            }
        }
        return answer;
    }
}