import java.util.*;
/*Problem:
return the largest subset of element that divide with 0 leftovers:
answer[i] % answer[j] == 0, ||
answer[j] % answer[i] == 0
note: return the actual elements: List<Integer>  */

/*solutions:
 * 1st - top-down
 * 2nd - bottom up
 * 3rd - bottom up-improved space */

/*motivation:
* for efficiency - sort nums[].
* by doing that can only check one direction modulo and not both.
* modulo rules - "if dividend (left side of %) is smaller than divisor - simply return dividend."
* after sort, check "A % B" - when always A is bigger! */

class B12_LargestDivisibleSubset_368_top_down {
    HashMap<Integer, List<Integer>> memo;
    int [] nums;

    public List<Integer> largestDivisibleSubset(int[] nums) { //start here
        // edge case - empty input
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        this.memo = new HashMap<Integer, List<Integer>>(); // <index of end element, the actual subset elements >
        Arrays.sort(nums);
        this.nums = nums;

        List<Integer> maxSubset = new ArrayList<>(); //hold ans.

//        each time check length of divisible subset that end with index i,
//        update maxSubset accordingly.
        for (int i = 0; i < nums.length; ++i) {
            List<Integer> curSubset = DFS(i);
            if (maxSubset.size() < curSubset.size()){
                maxSubset = curSubset;
            }
        }
        return maxSubset;
    }


    private List<Integer> DFS(Integer i) {
//            already cached that val:
        if (this.memo.containsKey(i)){
            return this.memo.get(i);
        }

        List<Integer> maxSubset = new ArrayList<>();

        for (int k = 0; k < i; ++k) {
//            if divisible - append to subset.
//            (here the sort come to use - check only one direction, and not both. see "motivation" for more explain.
            if (this.nums[i] % this.nums[k] == 0) {
                List<Integer> curSubset = DFS(k);
                if (maxSubset.size() < curSubset.size()) {
                    maxSubset = curSubset;
                }
            }
        }

//        append new found element to divisible subset.
        List<Integer> appendCurElement = new ArrayList<>();
        appendCurElement.addAll(maxSubset);
        appendCurElement.add(this.nums[i]);

        this.memo.put(i, appendCurElement);
        return appendCurElement;
    }
}
//time:  On^2
//space: On^2

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd- bottom up
public class B12_LargestDivisibleSubset_368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {

        // edge case - empty input
        if (nums.length == 0){
            return new ArrayList<Integer>();
        }

        List<ArrayList<Integer>> DP = new ArrayList<>(); // <index of end element, the actual subset elements >
        for (int num : nums){
            DP.add(new ArrayList<Integer>());
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; ++i) {
            List<Integer> maxSubset = new ArrayList<>();

            // find largest divisible subset of previous elements.
            for (int k = 0; k < i; ++k)
                if (nums[i] % nums[k] == 0 && maxSubset.size() < DP.get(k).size()){
                    maxSubset = DP.get(k);
                }

            // append new found element to divisible subset.
            DP.get(i).addAll(maxSubset);
            DP.get(i).add(nums[i]);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i)
            if (ans.size() < DP.get(i).size()) ans = DP.get(i);
        return ans;
    }
}
//time:  On^2
//space: On

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B12_LargestDivisibleSubset_368_bottom_up_improved_space {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // edge case - empty input
        if (nums.length == 0){
            return new ArrayList<>();
        }

        Integer[] dp = new Integer[nums.length]; // <index of end element, the actual ans elements >

        Arrays.sort(nums);

        Integer maxSubsetSize  = -1;
        Integer maxSubsetIndex = -1;

        for (int i = 0; i < nums.length; ++i) {
            Integer subsetSize = 0;

            for (int k = 0; k < i; ++k)
                if (nums[i] % nums[k] == 0 && subsetSize < dp[k]){
                    subsetSize = dp[k];
                }

            // append +1 to size with the new element.
            dp[i] = subsetSize + 1;

            // We reuse this loop to obtain the largest subset size
            // in order to prepare for the reconstruction of ans.
            if (maxSubsetSize < dp[i]) {
                maxSubsetSize = dp[i];
                maxSubsetIndex = i;
            }
        }

        /* Reconstruct the largest divisible subset  */
        LinkedList<Integer> ans = new LinkedList<>();
        Integer currSize = maxSubsetSize;
        Integer currTail = nums[maxSubsetIndex];

        for (int i = maxSubsetIndex; i >= 0; --i) {

            if (currSize == 0){
                break;
            }

            if (currTail % nums[i] == 0 && currSize.equals(dp[i])) {
                ans.addFirst(nums[i]);
                currTail = nums[i];
                currSize -= 1;
            }
        }

        return ans;
    }
}
//time:  On^2
//space: On^2