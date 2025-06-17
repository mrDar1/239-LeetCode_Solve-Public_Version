/*problem:
* given arr with positive and negative elements,
* find the Min start value that when doing prefix sum to arr - will never be smaller than 1.*/

/*4 solutions:
* 1st - prefix sum with On space.
* 2nd - prefix sum with O1 space.
* 3rd - same as 2nd, more elegant, and slight faster.
* 4th - binary search */

public class A15_MinValToGetPositiveStepByStep_1413 {
    public static void main(String[] args) {
//        int[] arr = {-3,6,2,5,8,6}; //ans: 4
//      int[] arr = {1,-2,-3}; //ans: 5
//      int[] arr = {-3, 2, -3, 4, 2}; //ans 5
      int[] arr = {-5,-2,4,4,-2}; //ans 8
        int n = minStartValue(arr);
        System.out.println("minStartValue: " +n);
        System.out.println("minStartValue_prefixtotal: " + minStartValue_prefixtotal(arr));
        System.out.println("minStartValue_prefixtotal_improve: " + minStartValue_prefixtotal_improve(arr));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//   1st intuitive solution:
    public static int minStartValue(int[] arr) {
        /* intuition: build prefix sum, find the smallest number at it.
         StartValue=smallest number -himself + 1.*/

        int [] sumarr = new int[arr.length];
        int ans = 1; // minimum value, constraint: must start with 1.

        sumarr[0] = arr[0];
        ans = 0 > sumarr[0] ? (-sumarr[0] + 1) : ans;
//        above line could replace without ternary:
//        if (sumarr[0] < 1){
//            ans = Math.max(ans, -sumarr[0] + 1);
//        }


//        build prefix sum:
        for(int i = 1 ; i < arr.length ; ++i){
            sumarr[i] = sumarr[i-1] + arr[i];

            if (sumarr[i] < 1){
                ans = Math.max(ans, -sumarr[i] + 1);
            }
        }
//        time: O2n:  O1n-prefix sum; O1n-finding min.
//        space: On - prefix sum array
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    2nd - prefixt total
    public static int minStartValue_prefixtotal(int[] arr) {
        int t = arr[0];
        int ans = t < 0 ? (-t + 1) : 1; // minimum value, constraint: must start with 1.

        for(int i = 1 ; i < arr.length ; ++i){
            t += arr[i];
            if(t < 0){
                ans = Math.max(ans, -t+1);
            }
        }
//        time: O1n
//        space: O1
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //    3rd way - Lc Improvment
    public static int minStartValue_prefixtotal_improve(int[] arr) {
        int t = 0;
        int ans = 0; // minimum value

        for(int i : arr){
            t+=i;
            ans = Math.min(ans, t);
        }
//        time: O1n - but better!
//        space: O1
        return -ans + 1;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //4th - Binary Search:
    public int minStartValue_Binary_Search(int[] nums) {

        int left = 1;
        int right = 100 * nums.length + 1; //Constraint: given 100 as top boundary (think of edge case that all element are -100). and Constraint: len cannot be more than 100.

        while (left < right) {
//            int middle = right - (right - left) / 2; //fail at LC - i believe there is a bug at LC!!!
            int middle = left + (right - left) / 2;
            int total = middle;
            boolean isValid = true;

            for (int num : nums) {

                total += num;

                // if sum smaller than 1 - sign flag - to try again with larger startValue.
                if (total < 1) {
                    isValid = false;
                    break;
                }
            }

            //if not valid - try again with larger startValue.
            // if middle is valid, try again with smaller startValue.
            if (isValid) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
//        n==nums.len
//        m==100, top boundary given as constraint.
//        time: O n log mn - binary search.
//        space: O1
        return left;
    }
}