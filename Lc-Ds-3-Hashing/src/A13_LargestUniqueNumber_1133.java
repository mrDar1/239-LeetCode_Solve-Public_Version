import java.util.HashMap;
import java.util.Map;
/*problem:
* return the largest integer that only occurs once.
* If no integer occurs once return -1.*/

public class A13_LargestUniqueNumber_1133 {
    public static void main(String[] args){
//        int[] arr = {9,9,8,8}; //ans -1.
        int[] arr = {5,7,3,9,4,9,8,3,1}; //ans 8
        int n = largestUniqueNumber(arr);
        System.out.println(n);
    }
//    psudo: traverse int[] and count occurences.
//    traverse hashmap if value==1 check compare with max.
    public static int largestUniqueNumber(int[] nums){
        HashMap<Integer,Integer> count = new HashMap<>(); // <number, count freq>
        int max = -1;

//        build hashmap:
        for (int i : nums){
            count.put(i, count.getOrDefault(i, 0) + 1);
        }

        for (int i : count.keySet()){
            if(count.get(i) == 1){
                max = Math.max(max, i);
            }
        }

//        time: O2n - build hashmap + find max
//        space: O1n
        return max;
    }
}