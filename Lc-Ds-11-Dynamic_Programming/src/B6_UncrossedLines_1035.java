import java.util.Arrays;

/*problem - classic important one:
* return how many lines can be connected between 2 arr, 2 conditions:
* 1 - num1[i]==num2[j]
* 2 - the lines must not cross each other! */

/*solutions:
 * 1st-top-down
 * 1.5-top-down - same as 1st, small changes at boundary's (1st is better practice, i like that one more).
 * 2nd-bottom up - Olen1*len2 space
 * 3rd-bottom up - improved space - O2(Math.min(len1,len2)) space */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

public class B6_UncrossedLines_1035 {
/*intuition:
* start from last: if both elements equal sol+1, and reduce both ptrs.
*               if do not equal: explore 2 options, reduce first ptr or the second,  return highest:
*                    1- check ans when reduce ptr from the first
*                    2- when reduce ptr from the second. */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int[][] memo = new int[len1 + 1][len2 + 1]; //why +1? to avoid edge cases.

        for (int[] arr : memo){
            Arrays.fill(arr, -1);
        }

//        Time complexity: O 2(n1⋅n2):
//                          O(n1⋅n2) initialize memo to -1.
//                          O(n1⋅n2) traverse and fill memo again.
//        Space complexity: O(n1⋅n2) - for memo[][]
        return dfs(len1, len2, nums1, nums2, memo);
    }

    private int dfs(int len1, int len2, int[] nums1, int[] nums2, int[][] memo) {
//        base case - finish traverse entire arr:
        if (len1 <= 0 || len2 <= 0 ){
            return 0;
        }

//        already cached that val:
        if (memo[len1][len2] != -1){
            return memo[len1][len2];
        }

        if (nums1[len1 - 1] == nums2[len2 - 1]){
            return memo[len1][len2] =
                    ( 1 + dfs(len1 - 1, len2 - 1, nums1, nums2, memo) );
        }else {
            return memo[len1][len2] = Math.max(
                                dfs(len1, len2 - 1, nums1, nums2, memo),
                                dfs(len1 - 1, len2, nums1, nums2, memo)     );
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B6_UncrossedLines_1035_top_down {
    /*intuition:
     * start from last: if both elements equal sol+1, and reduce both ptrs.
     *               if do not equal: explore 2 options, reduce first ptr or the second,  return highest:
     *                    1- check ans when reduce ptr from the first
     *                    2- when reduce ptr from the second. */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int[][] memo = new int[len1][len2];

//        fill memo with -1 - technique for C.
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
//        java style:
//        for (int[] arr : memo){
//            Arrays.fill(arr, -1);
//        }

//        Time complexity: O 2(n1⋅n2):
//                          O(n1⋅n2) initialize memo to -1.
//                          O(n1⋅n2) traverse and fill memo again.
//        Space complexity: O(n1⋅n2) - for memo[][]
        return dfs(len1 - 1, len2 - 1, nums1, nums2, memo);
    }

    private int dfs(int len1, int len2, int[] nums1, int[] nums2, int[][] memo) {
//        base case - finish traverse entire arr:
        if (len1 < 0 || len2 < 0 ){
            return 0;
        }

//        already cached that val:
        if (memo[len1][len2] != -1){
            return memo[len1][len2];
        }

        if (nums1[len1] == nums2[len2]){
            return memo[len1][len2] =
                    ( 1 + dfs(len1 - 1, len2 - 1, nums1, nums2, memo) );
        }else {
            return memo[len1][len2] = Math.max(
                    dfs(len1, len2 - 1, nums1, nums2, memo),
                    dfs(len1 - 1, len2, nums1, nums2, memo)     );
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B6_UncrossedLines_1035_bottom_up {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int[][] memo = new int[len1 + 1][len2 + 1]; //why +1? to avoid edge cases.

//        in java no need to initialize to 0, good practice for c.
//        why initialize to 0? -the memo[][] represent how many lines can be drawn, start with 0 lines.
        for (int[] arr : memo){
            Arrays.fill(arr, 0);
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]){
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                }else {
                    memo[i][j] = Math.max( memo[i - 1][j] ,memo[i][j - 1] );
                }
            }
        }
//        Time complexity: O 2(n1⋅n2):
//                          O(n1⋅n2) initialize memo to -1.
//                          O(n1⋅n2) traverse and fill memo again.
//        Space complexity: O(n1⋅n2) - for memo[][]
        return memo[len1][len2];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B6_UncrossedLines_1035_bottom_up_improved_space {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
//        optimize - choose shorter of them:
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        int[] memo = new int[len2 + 1];
        int[] prevMemo = new int[len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    memo[j] = 1 + prevMemo[j - 1];
                } else {
                    memo[j] = Math.max(memo[j - 1], prevMemo[j]);
                }
            }
            prevMemo = memo.clone();
        }

//        Time complexity: O 2(n1⋅n2):
//                          O(n1⋅n2) initialize memo to -1.
//                          O(n1⋅n2) traverse and fill memo again.
//        Space complexity: O2(len2) - for memo[] and prevMemo[]
        return memo[len2];
    }
}