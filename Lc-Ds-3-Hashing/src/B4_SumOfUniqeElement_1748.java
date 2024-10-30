import java.util.HashMap;
/*problem:
* sum only the elements that appear 1 */

public class B4_SumOfUniqeElement_1748 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2};
        int n = sumOfUnique(arr);
        System.out.println(n);
    }

    /*psudo: traverse nums[] and count each char, second traverse hashmap,if value == 1 add to sum*/
    public static int sumOfUnique(int[] nums) {
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>(); // <value, freq>

        for (Integer i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Integer key : map.keySet()){
            if (map.get(key) == 1){
                sum += key;
            }
        }
//      time: O2n - first count freq, 2nd build sum of all unique elements.
//        space: O1n
        return sum;
    }
}
