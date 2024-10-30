import java.util.*;
/*problem:
* remove "k" elements.
* return the min number of elements left (duplicates counts as 1) */

/*motivation:
try to "vanish" elements, do that by first prioritize to delete
unique numbers as possible, then the less freq element.
since "vanish" number with many duplicate is expensive.*/

/* solutions:
* 1st - first try - work slow, only beats 5% in LC
* 2nd - same as 1, but use reverse sort, so much faster - beats 55% in LC - only since reverseSort!!
* 3rd - same as 1, with min-heap, increase speed to beats in LC 70% !!! */

public class A4_LeastNumberofUniqueIntegersafterKRemovals_1481 {
    public int findLeastNumOfUniqueInts_my_first_try(int[] arr, int k) {
        HashMap <Integer, Integer> map = new HashMap<>(); //<num , occurrences >
        List<Integer> freqList = new ArrayList<>(); //hold only number of frequency.

//        populate freq map:
        for (int i : arr){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
//        populate List from map.
        for (int i : map.values()){
            freqList.add(i);
        }

        Collections.sort(freqList, (a,b) -> a - b); //ascending order.
//        above line can be replace with:
//        Collections.sort(freqList);

        while ( k > 0){
            int freq = freqList.get(0);

            if (k >= freq){
                k -= freq;
                freqList.remove(0);
            }else { //we do not have enough k to remove anything
                break;
            }
        }

//        time:O n logn == 1n-hashmap, 1n-copy to List, On log n - sort, k - remove elements from list.
//        space: O2n - freq hashmap and freqList.
        return freqList.size();
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int findLeastNumOfUniqueInts_same_with_reverse_order(int[] arr, int k) {
//        build freq hashmap:
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num: arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

//        List of freq:
        List<Integer> ordered = new ArrayList<>();
        for (int val: counts.values()) {
            ordered.add(val);
        }

        Collections.sort(ordered, Comparator.reverseOrder());

        while (k > 0) {
            int freq = ordered.get(ordered.size() - 1);
            if (k >= freq) {
                k -= freq;
                ordered.remove(ordered.size() - 1);
            } else {
                break;
            }
        }

        return ordered.size();
    }


    public int findLeastNumOfUniqueInts_heap_approach(int[] arr, int k) {
        HashMap <Integer, Integer> map = new HashMap<>(); //<num , occurrences >
        PriorityQueue<Integer> pqmin = new PriorityQueue<>(); //hold only number of frequency.

//        freq hashmap:
        for (int i : arr){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

//        min-heap of freq:
        for (int i : map.values()){
            pqmin.add(i);
        }

        while ( k > 0 && !pqmin.isEmpty()){
            int freq = pqmin.peek();

            if (k >= freq){
                k -= freq;
                pqmin.poll();

            }else { //we do not have enough k to remove anything
                break;
            }
        }
//        time:O n logn == 1n-hashmap, 1n-List On log n - sort, k - add and remove.
//        space: O2n - hashmap and list.
        return pqmin.size();
    }
}