import java.util.ArrayList;
import java.util.List;

/*problem:
* given number of rows - return entire pascal triangle values
* constraint: numRows >= 1*/

/* solutions:
 * 1st - top down.
 * 2nd - bottom up. */

public class B2_PascalsTriangle_118 {
//    note no need for memo[] as in PascalTriangle - the ans is in the ans output.
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        generateRows(numRows, ans);
        return ans;
    }

    private void generateRows(int remainRows, List<List<Integer>> ans) {

//        base case - reach end of tribonacii.
        if (remainRows == 1) {
            List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            ans.add(firstRow);
            return;
        }

        generateRows(remainRows - 1, ans);
        List<Integer> prevRow = ans.get(remainRows - 2); //note why -2? and not -1? ans: constraint, remainRows given as 1-index. but we use 0-index.

        List<Integer> newRow = new ArrayList<>();
        newRow.add(1); // first element of each row is always 1
        for (int i = 1; i < prevRow.size(); i++) {
            newRow.add(prevRow.get(i - 1) + prevRow.get(i));
        }
        newRow.add(1); // last element of each row is always 1

//        time:  O(n/2)^2 : on/2-average size for each row, done each time ^2.
//        space: O(n/2)^2 ???
        ans.add(newRow);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B2_PascalsTriangle_118_bottom_up {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        // initialize first row with 1.
        ans.add(new ArrayList<>());
        ans.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> curRow  = new ArrayList<>();
            List<Integer> prevRow = ans.get(rowNum - 1);

            curRow.add(1); // first element of each row is always 1
            for (int j = 1; j < rowNum; j++) {
                curRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            curRow.add(1); // last element of each row is always 1


            ans.add(curRow);
        }
//        time: O numRows^2.
//        space: O1 (do not count output)
        return ans;
    }
}