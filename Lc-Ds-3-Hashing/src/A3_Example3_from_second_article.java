import java.util.*;

/*problem:
Example 3: Given int[] nums, find all the unique numbers that satisfy the following:
 x + 1 is not in nums, and x - 1 is not in nums. */

public class A3_Example3_from_second_article {
    public static void main(String[] args){
        int[] arr = {1,3,5,6,7,8,9,10, 15, 20, 30,31, 39,40};
        System.out.println(findNumbers(arr));
    }
    public static List<Integer> findNumbers(int[] nums) {
        Set<Integer> myset = new HashSet<>();
        List<Integer> ans = new ArrayList<>();

//        creating the set:
        for(int i : nums){
            myset.add(i);
        }

        for(int i : myset){
            if ( !myset.contains(i + 1) && !myset.contains(i - 1)){
                ans.add(i);
            }
        }
        //time: O2n - 1st: HashSet 2nd: search for relevant numbers.
        // space: On - HashSet
        return ans;
    }
}