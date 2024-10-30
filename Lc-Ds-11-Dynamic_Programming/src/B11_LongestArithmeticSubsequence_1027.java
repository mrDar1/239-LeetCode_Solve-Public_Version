import java.util.HashMap;
import java.util.Map;
/*problem:
* return the length of the longest arithmetic subsequence (=LAS).
* arithmetic subsequence == strictly increasing/decreasing subsequence with common diff.
* subsequence == sub-array with deleted elements, without changing the order of the remaining elements.
* in other word:
* sub[i + 1] - sub[i] == x
* x==same for all i at subsequence
* note: we are not given the "difference" - must find longest one ourself. */

/*solutions:
 * 1st - brute force, iterative way
 * 2nd - bottom up */

class B11_LongestArithmeticSubsequence_1027_brute_force {
    public int longestArithSeqLength(int[] nums) {

//        edge case:
        if (nums.length <= 2) {
            return nums.length;
        }

        int maxLength = 2; // constraint: as not given "different", no matter what are those 2 elements the LAS will be 2.

        // check LAS for each pair (i, j) where i < j
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = nums[j] - nums[i];
                maxLength = Math.max(maxLength, longestArithSeqFrom(j, diff, nums));
            }
        }
//        time : On^3:
//                    N^2 pairs - inside each job of 1n.
//        space: O1 - not store vars, just "maxLength".

        return maxLength;
    }

//    check for LAS, with "diff", that start with index "j" until exhausted nums.
    private int longestArithSeqFrom( int j, int diff, int[] nums) {
        int length = 2;
        int nextNum = nums[j] + diff;

        for (int k = j + 1; k < nums.length; k++) {
            if (nums[k] == nextNum) {
                length++;
                nextNum += diff;
            }
        }

        return length;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

public class B11_LongestArithmeticSubsequence_1027 {
    public int longestArithSeqLength(int[] nums) {

//        edge cases (0,1,2 elements):
        if (nums.length <= 2) {
            return nums.length;
        }

        int maxLength = 2; // constraint: as not given "different", no matter what are those 2 elements the LAS will be 2.
        int n = nums.length;

//        dp[i].get(diff) - gives the LAS, ending at index i with common difference "diff".
        Map<Integer, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        // iterate over all pairs (i, j) when i < j.
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                // if dp[i] not contains diff - start new sequence with length 2.
                // If dp[i] already contains this difference, increment it with +1.
                dp[i].put(diff, dp[j].getOrDefault(diff, 1) + 1);
                // Update the maximum length found
                maxLength = Math.max(maxLength, dp[i].get(diff));
            }
        }
//        time : On^2: N^2 pairs.
//        space: O2 * n^2: store each pair LAS and diff.
        return maxLength;
    }
}
