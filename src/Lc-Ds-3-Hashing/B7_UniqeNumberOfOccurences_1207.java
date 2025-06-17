import java.util.HashMap;
import java.util.HashSet;
/*return true if the freq of all element is unique */

public class B7_UniqeNumberOfOccurences_1207 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 1, 1, 3};
        System.out.println(uniqueOccurrences(arr));
    }
    /*psudo: use hashmap and count chars.
    * use hashet and enter each occurence - if contain - return false.
    * at the end - return true */
    public static boolean uniqueOccurrences(int[] arr) {
    HashMap<Integer, Integer> map = new HashMap<>(); // <value, freq>
    HashSet<Integer> set = new HashSet<>(); //seen

    for (int i : arr){
        map.put(i, map.getOrDefault(i, 0) + 1);
    }

    for (int i : map.values()){
        if (set.contains(i)){
            return false;
        }
        set.add(i);
    }
//        time: O2n - count occurrence, check uniqe.
//        space: O1n
    return true;
    }
}