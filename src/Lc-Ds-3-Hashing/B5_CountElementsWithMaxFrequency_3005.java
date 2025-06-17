import java.util.HashMap;
/*problem:
* Return the total frequencies of elements in nums such that those elements all have the maximum frequency */


/*solutions:
1st - my first time success
2nd - Lc optimize - same idea but with 1 iteration! */
public class B5_CountElementsWithMaxFrequency_3005 {
    public static void main(String[] args) {
        int[] arr = {1,2,2,3,1,4}; //ans: 4
        System.out.println(maxFrequencyElements1(arr));
        System.out.println(maxFrequencyElements2(arr));
    }

//    my first working - work but LC did same bit change.
    /*psudo: traverse int[] and count freq.
    * second traverse hasmap and check for max val
    * 3rd traverse hashmap and each time val==max ++ans */
    public static int maxFrequencyElements1(int[] nums) {
        int ans = 0;
        int max = Integer.MIN_VALUE;
        HashMap<Integer,Integer> map = new HashMap<>(); // <element , number of occurrences>

//        count frequency
        for (int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

//        determine max frequency
        for (int i : map.keySet()){
            max = Math.max(max, map.get(i));
        }

//        each time we encounter the max frequency - add its number of occurrence.
        for (int i : map.keySet()){
            if (map.get(i) == max){
                ans += max;
            }
        }
        //time: O3n
        // space: O1n
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int maxFrequencyElements2(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int maxFrequency = 0;
        int totalFrequencies = 0;

//        count frequency, find its highest and update:
        for (int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);

            int frequency = map.get(i);

            if (frequency > maxFrequency){
                maxFrequency = frequency;
                totalFrequencies = frequency;
            }

            else if (frequency == maxFrequency){
                totalFrequencies += frequency;
            }
        }
//        time: O1n!!!
//        space: O1n
        return totalFrequencies;
    }
}
