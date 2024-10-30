import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/*problem:
got int[] - return all possible unique permutations - each digit can used once,
constraint: allow duplicates */

/*intuition:
used hashmap to count occurrences inside nums[]. then send to backtrack:
backtrack:
    base case - when reach leaf(==len of nums) - add to ans.
    traverse Hashmap:
    as in all backtrack - when traverse all possible of curPath - before send to backtrack add curValue, and after call function remove it.
    here, also update freqHashmap each time. */

public class B1_Permutations2_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> curPath = new LinkedList<>();

        HashMap<Integer, Integer> freqMap = new HashMap<>();//freq hashmap <value, occurrences>
        for (int i : nums) {
            freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
        }

        backtrack(ans, freqMap, curPath, nums.length);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans,
                           HashMap<Integer, Integer> freqMap,
                           LinkedList<Integer> curPath,
                           int len) {

//        base case - successfully valid combinations:
        if (len == curPath.size()) {
            ans.add(new ArrayList<>(curPath)); //why use new? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            Integer curValue = entry.getKey();
            Integer freq = entry.getValue(); //num of frequency

            if (freq == 0) {
                continue;
            }

            curPath.add(curValue);
            freqMap.put(curValue, freq - 1);

            backtrack(ans, freqMap, curPath, len);

//            as in all backtrack - remove last to explore other paths.
            curPath.removeLast();
            freqMap.put(curValue, freq);
        }
    }
}

/*  complexity, long math! approximately:
k==unique digits inside freqHashMap

time loose upper bound:  O(n*n!) : O1n-build hashmap, On!-traverse map key that go and shrink.
time more specific: (n + n!/(n-k)!) == (n!/(n-k)!):
                      O1n-                build hashmap.
                      numerator: n! -     each time use element cant use it again.
                      denominator: (n-k)!-bounded by number of unique elements,
                      as duplicates got way less different paths , as change in order will not produce new valid path.
                      (think of an edge case: all elements are same value - will have only 1 valid ans).
space: O3n: O1n-hashmap freq , O1n-recursion depth, O1n-build each path candidate (do not take account output). */