/*problem:
* return pivot index (if there are several return the left-most).
* return -1 if not possible.
* pivot index == sum all numbers to the left == sum all numbers to the right.
* */
public class B10_FindPivotIndex_724 {
    /*psudo:
    * use total var that sum all input.
    *
    * traverse all input again - this time start doing prefix sum of all the left element, each time increment by 1.
    * check if pivot - return that index. */
    public static void main(String[] args){
        int[] arr = {1,7,3,6,5,6}; //ans should be 3.
        int n = pivotIndex(arr);
        System.out.println(n);
    }
    public static int pivotIndex(int[] nums){
        int leftsum = 0;
        int t = 0;

//        creating prefix total:
        for(int i : nums){
            t += i;
        }

        for(int i = 0 ; i < nums.length ; ++i){
            if (leftsum == t - leftsum - nums[i]){
                return i;
            }
            leftsum += nums[i];
        }
        return -1;
        //time: O2n - 1st for prefix total. 2nd-for find pivot.
        //space: O1 - using 1 var for the prefix sum.
    }
}
