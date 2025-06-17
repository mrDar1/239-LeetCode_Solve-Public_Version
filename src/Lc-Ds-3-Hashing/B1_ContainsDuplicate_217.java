import java.util.HashSet;
import java.util.Set;
/*problem: if got duplicate char - return true.*/
public class B1_ContainsDuplicate_217 {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 1};
        int[] arr = {1, 2, 3, 4};
        boolean n = containsDuplicate(arr);
        System.out.println(n);
    }
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> counts = new HashSet<>();

        for (int i : nums){
            if (counts.contains(i)){
                return true;
            }
            counts.add(i);
        }
        //time: On space: On / O1 - english letter 26
        return false;
    }
}