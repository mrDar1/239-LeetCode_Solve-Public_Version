/*problem:
* return max of nums[i]+nums[j] - with constraint that both digits sum is the same
* note - do not return their sum of digit but return the original number values! */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Map;

public class A18_MaxSumofaPairWithEqualSumofDigits_2342 {
/*psudo:
* use hashmap to store <digit sum, List<all original number values>>, build that hashmap.
* traverse hashmap - whenever freq  bigger than 1 - choose 2 biggest number and compare to max_so_far
* how can we choose the biggest 2? we will sort the array in decrease order so first 2 element are biggest */

    public int maximumSum(int[] nums) { //start here
        int max = -1;

        Map<Integer, List<Integer>> map = new HashMap<>(); //<digitsum , List<all original numbers with that digit sum>>
        for (int i : nums){
            int curdigsum = getDigitSum(i);
            if ( !map.containsKey(curdigsum)){
                map.put(curdigsum, new ArrayList<>());
            }

            map.get(curdigsum).add(i);
        }

        for (int key : map.keySet()){
            List<Integer> arr = map.get(key);

            if (arr.size() > 1){
                Collections.sort(arr, Collections.reverseOrder());

                max = Math.max(max, arr.get(0) + arr.get(1));
            }
        }

//        time:  On logn -  ????1n-build hashmap, k-avg number of digits for each number, 1n-traverse hashmap + Onlogn - sort each arr inside hashmap.???
//        space: O1n - hashmap.
        return max;
    }
    public int getDigitSum(int n) {
        int sum = 0;

        while (n > 0){
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A18_MaxSumofaPairWithEqualSumofDigits_2342_optimized {
/*psudo:
* use hashmap to store <digit sum, highest value so far>
* traverse hashmap, whenever we saw that sum_of_digits before - check vs. ans whoes highest.
* then - only store at that key - the biggest one of them! */

    public int maximumSum(int[] nums) { //start here
        int ans = -1;
        Map<Integer, Integer> map = new HashMap<>(); // <digit sum, highest value so far>

        for (int i : nums){
            int curdigsum = getDigitSum(i);
            if ( map.containsKey(curdigsum)){
                ans = Math.max(ans, i + map.get(curdigsum));
            }
//            note - must use "getOrDefault" - since its may be the first time and we still dont have that key!!
            map.put(curdigsum, Math.max(map.getOrDefault(curdigsum,0), i) );
        }

//        time:  O1n
//        space: O1n - hashmap - but much smaller!
        return ans;
    }
    public int getDigitSum(int n) {
        int sum = 0;

        while (n > 0){
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}