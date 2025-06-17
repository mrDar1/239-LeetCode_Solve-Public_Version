import java.util.*;
/*problem:
given list of unsorted int[], with duplicate elements, find all combinations/subsets that subset.sum()=="target"
each element can only use once.
cannot use same elements with different order! */

/*solutions:
 * 1st - use freq hashmap and convert hashmap to ArrayList! since hashmap is not sort, and must move by index.
 *       for practice, 2 ways of:
 *          -"getOrDefault"
 *          -convert hashmap to List with lambda that i like!!
 * 2nd - using Sort to avoid duplicates - best solution!
 * 3rd - my first fail attempt - work directly on freq hashmap - work but only when has no duplicates - believe got what to learn here! */

class B2_CombinationSum2_40_with_hashmap_no_sort {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> curPath = new LinkedList<>();

//        freq hashmap:
        HashMap<Integer, Integer> freqMap = new HashMap<>(); //<value, number of occurrences>
        for (int cur : candidates) {
            if (freqMap.containsKey(cur)){
                freqMap.put(cur,freqMap.get(cur) + 1 );
            }
            else freqMap.put(cur, 1);
        }
////        note for-loop above could replace with (mainly for practice & fun):
//        for (int cur : candidates) {
//            freqMap.put(cur, freqMap.getOrDefault(cur, 0) + 1);
//        }


//         Lambda   -  convert freqMap table to a list of <num, count>
        List<int[]> freqList = new ArrayList<>();
        freqMap.forEach((key, value) -> {
            freqList.add(new int[] { key, value });
        });
//         Map.Entry - another way to convert hashMap to ArrayList:
//        List<int[]> freqList = new ArrayList<>();
//        for (Map.Entry<Integer,Integer> entry : freqMap.entrySet() ){
//            freqList.add(new int[] {entry.getKey(), entry.getValue()});
//        }

        backtrack(curPath, target, 0, freqList, ans);

//        time:  O2^n: 2^n- worst edge case - when all numbers in candidates[] unique, O2n-build freqMap and convert to List,
//        space: O4n : O2n-hashmap and listofHashmap, O1n-each curPath tried, O1n-recursive call depth,
//        Note: we did not take into account the space needed to hold the final results of combination in the above analysis.
        return ans;
    }

    private void backtrack( LinkedList<Integer> curPath,
                            int remain,
                            int i, //represent curr index
                            List<int[]> freqList,
                            List<List<Integer>> ans) {

//        by combine these 2 enjoy slightly optimize speed.
//        base case - successfully valid combinations &
//        base case - curPath sum too big - prune.
        if (remain <= 0) {
            if (remain == 0) {
                ans.add(new ArrayList<Integer>(curPath)); //why use new? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            }
            return;
        }


        for (int j = i; j < freqList.size() ; ++j) { //j represent next index.
            int[] entry = freqList.get(j);
            int candidate = entry[0];
            int freq      = entry[1];

            if (freq <= 0) continue;

            // add a new element to the current combination
            curPath.addLast(candidate);
            freqList.set(j, new int[] { candidate, freq - 1 });

            // continue the exploration with the updated combination
            backtrack(curPath, remain - candidate, j, freqList, ans);

            // backtrack the changes, so that we can try another candidate
            freqList.set(j, new int[] { candidate, freq });
            curPath.removeLast();
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B2_CombinationSum2_40_with_sort {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> curPath = new LinkedList<>();

        Arrays.sort(candidates);

        backtrack(candidates, curPath, target, 0, ans);
//        time:  O2^n:
//              O2^n - backtrack, at worst exhaustive search will take 2^n.
//              sort will take O n log n.

//        space: O2n: O1n-each curPath tried, O1n-for each recursion in worst case.
//Note: we did not take into account the space needed to hold the final ans of combination in the above analysis.
        return ans;
    }

    private void backtrack(
            int[] candidates,
            LinkedList<Integer> curPath,
            Integer remain,
            Integer i, //represent cur index
            List<List<Integer>> ans
    ) {

//        base case - successfully valid combinations:
//        (note-unlike before, here when (remain-candidate < 0) - prune done at other place):
        if (remain == 0) {
            ans.add(new ArrayList<Integer>(curPath)); //why use new? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

        for (int j = i; j < candidates.length; ++j) { //j represent next index
            Integer pick = candidates[j];

            //skip duplicate
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }

            // cannot be ans - prune. "candidates" sorted, so from here on only bigger element - can stop.
            if (remain - pick < 0) {
                break;
            }

            curPath.addLast(pick);
            backtrack(candidates, curPath, remain - pick, j + 1, ans);
            curPath.removeLast();
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//first fail attempt.
public class B2_CombinationSum2_40 {
    /* psudo:
     * use linkList curPath.
     * build hashmap to count freq. and send to backtrack:
     * backtrack:
     *   if sum==target - add to ans.
     *   traverse hashmap:
     *       if freq==0 || sum > target - skip,
     *       each time send to backtrack new path with new last digit.
     *       as all backtrack - remove last element from path and updated freq value. */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> curPath = new LinkedList<>();
        HashMap<Integer,Integer> freqMap = new HashMap<>(); //<num, freq>

//        build hashmap freq:
        for (int i : candidates){
            freqMap.put(i, freqMap.getOrDefault(i,0) + 1);
        }

        backtrack(ans, curPath, freqMap, target, 0);

        return ans;
    }

    private void backtrack(List<List<Integer>> ans,
                           LinkedList<Integer> curPath,
                           HashMap<Integer, Integer> freqMap,
                           int target,
                           int i) {

//        by combine these 2 enjoy slightly optimize speed.
//        base case - successfully valid combinations &
//        base case - curPath sum too big - prune.
        if (sumOfPath(curPath) >= target){
            if (sumOfPath(curPath) == target){
                ans.add(new ArrayList<>(curPath));
            }
            return;
        }

        for (int num : freqMap.keySet()){
            int freq = freqMap.get(num);

            if (freq <= 0 || sumOfPath(curPath) > target){
                continue;
            }

            curPath.addLast(num);
            freqMap.put(num, freq - 1);

            backtrack(ans, curPath, freqMap, target, i + 1);

            curPath.removeLast();
            freqMap.put(num, freq);
        }
    }

    private int sumOfPath(LinkedList<Integer> path) {
        int sum = 0;
        for (int i : path){
            sum += i;
        }
        return sum;
    }
}