import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*problem:
return int[] of all possibility of number with "n" digits
constraint:  |digit[i] - digit[i+1]| == k
constraint: 0<=k<=9 */

/*solutions:
* 1st-DFS
* 2nd-BFS*/

/* shared complexity:
n==number of digits.
time:  O2^n. each time we got 2 option +/- and we do it n times.
space: O2^n:
            O(2^n-1)- each recursion max depth.
            On - size of curPath. */


public class A10_NumbersWithSameConsecutiveDifferences_967 {
    /*psudo for DFS:
     * edge case: n==1 then return all numbers 1-9.
     * start with digits: 1-9 - why all those number? -we want all start points possibilities. each send to DFS
     *
     * DFS:
     *    each time calls itself with number from last DFS and split in 2:
     *    num +-k and continue -- we check both add and substructure!
     *    each time check that we're at range of 0-9 - only the good ones send again to DFS but:
     *      1-reduce n-cause we advance 1 digit.
     *      2-with num the new number after we add/sub-struct
     *  */
    public int[] numsSameConsecDiff(int n, int k) {
//        edge case (actually got constraint that n>=2, but its a good practice):
        if (n == 1) {
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {// explore digits 1-9, at DFS way! (constraint - cannot start with 0!!!)
            DFS(n - 1, i, ans, k);
        }

//        constraint: convert List to int[], several ways:
////        1 way:
        return ans.stream().mapToInt(i -> i).toArray();
//
////        2 way:
//        int[] converted_ans = new int[ans.size()];
//        for (int i = 0; i < ans.size(); i++) {
//            converted_ans[i] = ans.get(i);
//        }
//        return converted_ans;

//        3rd way: - do not work
//        return ans.toArray();
    }

    private void DFS(int n,      //each time send to DFS with (n-1) - to represent advance of index at curPath.
                     int curPath,
                     List<Integer> ans,
                     int k) {

//        base case - successfully valid combinations:
        if (n == 0) {
            ans.add(curPath);
            return;
        }

//        creating 2 branches: 1st-curDigit+k, 2nd-curDigit-k.
        List<Integer> add_and_substract_brunch = new ArrayList<>();
        int tailDigit = curPath % 10;

        add_and_substract_brunch.add(tailDigit + k);
        if (k != 0) { //use it to avid duplicates, when k==0.
            add_and_substract_brunch.add(tailDigit - k);
        }

        for (int cur : add_and_substract_brunch) {
            if (cur >= 0 && cur <= 9) { //we only want 0-9 digit!! if its 10,11,12 or above, or smaller than 0 - discard it!!
                int updatedNum = curPath * 10 + cur;
                DFS(n - 1, updatedNum, ans, k);
            }
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A10_NumbersWithSameConsecutiveDifferences_967_BFS {
    public int[] numsSameConsecDiff(int n, int k) {

//        edge case (actually got constraint that n>=2, but its a good practice):
        if (n == 1){
            return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }

        List<Integer> queue = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);


        for(int level = 1; level < n; ++level) {
            ArrayList<Integer> nextQueue = new ArrayList<>();

            // iterate through each number within the level
            for (Integer num : queue) {
                ArrayList<Integer> add_and_substract_brunch = new ArrayList<>();
                int tailDigit = num % 10;

                add_and_substract_brunch.add(tailDigit + k);
                if (k != 0) { //use it to avid duplicates, when k==0.
                    add_and_substract_brunch.add(tailDigit - k);
                }


                for (Integer cur : add_and_substract_brunch) {
                    if (0 <= cur && cur < 10) {//we only want 0-9 digit!! if its 10,11,12 or above, or smaller than 0 - discard it!!
                        Integer newNum = num * 10 + cur;
                        nextQueue.add(newNum);
                    }
                }
            }
            // prepare for next level
            queue = nextQueue;
        }

        return queue.stream().mapToInt(i->i).toArray();
    }
}