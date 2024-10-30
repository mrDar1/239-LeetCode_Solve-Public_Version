import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/*problem:
* return min subsequence array until reach duplicate value.
* can start whenever choose.
* if not found return -1*/

/*solutions:
* 1st - sliding window brute force - pass testing, but fail for TLE
* 2nd - hashmap <value, indexes>
* 3rd - hashmap - improved space!
* 4th - same as 3rd - solve with recursion to practice. */

//brute force - sliding window:
public class A17_MinimumConsecutiveCardstoPickUp_2260 {
    public static int minimumCardPickup(int[] cards) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < cards.length; i++) {
            for (int j = i+1; j < cards.length; j++) {
                if (cards[i] == cards[j]){
                    min = Math.min(min, j - i + 1);
                }
            }
        }
//        time: On^2
//        space: O1
        return min==Integer.MAX_VALUE ? -1 : min;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A17_real_MinimumConsecutiveCardstoPickUp_2260_hashmap {
    public static int minimumCardPickup(int[] cards){
/*intuion:
* build hashmap that sotre <number, all indexes it appears>
* traverse all keys and find the min value - if arent then return -1 as constraint.*/
        int ans = Integer.MAX_VALUE;
        Map<Integer, List<Integer>> dic = new HashMap<>(); //<number, all indexes it appears>

        for (int i = 0; i < cards.length; i++) {
            int num = cards[i];

            if ( !dic.containsKey(num) ) {
                dic.put(num, new ArrayList<>());
            }

            dic.get(num).add(i);
        }

//      traverse all values inside hashmap - find min window size.
        for (int key: dic.keySet())
        {
            List<Integer> arr = dic.get(key);
            for (int i = 0 ; i < arr.size() - 1 ; ++i) {
                ans = Math.min(ans, arr.get(i + 1) - arr.get(i) + 1);
            }
        }

//        edge case - didn't find any pair:
        ans = (ans == Integer.MAX_VALUE) ? -1 : ans;

        return ans;
        //time: O2n -  1st-build hashmap, 2nd: n-traverse keyset and find min value.
        // space: On - store input at hashmap
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A17_real_MinimumConsecutiveCardstoPickUp_2260_hashmap_improved_space {
    public static int minimumCardPickup(int[] cards) {
        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> dic = new HashMap<>(); //<value, last index it appears>

        for (int i = 0; i < cards.length; i++) {
            int num = cards[i];

            if ( dic.containsKey(num) ) {
                ans = Math.min(ans, i - dic.get(num) + 1);
            }

            dic.put(num, i);
        }

//        edge case - didn't find any pair:
        ans = (ans == Integer.MAX_VALUE) ? -1 : ans;

//        time: O1n - traverse just once!
//        space: O1n - but probably be less than before!! its O1n only at worst case that has no duplicates!
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A17_real_MinimumConsecutiveCardstoPickUp_2260_same_as_3rd_with_recursion {
    public int minimumCardPickup(int[] cards) { // star here
        int cur = 0;
        int minSizeOfWindow = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>(); // <value, its last seen index>
//        time: O1n - traverse all cards once
//        spoace: O2n-recursion, hashmap
        return helper(cur, minSizeOfWindow, map , cards);
    }

    private int helper(int cur, int minSizeOfWindow, Map<Integer, Integer> map, int[] cards) {
        if (cur == cards.length){
            return minSizeOfWindow == Integer.MAX_VALUE ? -1 : minSizeOfWindow;
        }

        if (map.containsKey(cards[cur])){
            minSizeOfWindow = Math.min(minSizeOfWindow, cur - map.get(cards[cur]) + 1);
        }

        map.put(cards[cur], cur);

        return helper(cur + 1, minSizeOfWindow, map , cards);
    }
}