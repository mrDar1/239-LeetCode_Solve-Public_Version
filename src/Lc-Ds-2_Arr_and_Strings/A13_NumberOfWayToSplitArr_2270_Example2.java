/*problem:
* return number of possible ways to split the String[] in valid way.
* valid way:
* 1-right section should have at least 1 element.
* 2-left section sum bigger or equal to the right section sum. */

/*solutions:
* 1st-prefix sum
* 2-optimized same idea, save space */

public class A13_NumberOfWayToSplitArr_2270_Example2 {
    public static void main(String[] args){
//        int[] arr = {6,-1,9}; //output: 0
//        int[] arr = {10,4,-8,7}; //output: 2
        int[] arr = {2,3,1,0}; //output: 2 also

        int n = waysToSplitArray(arr);
        System.out.println(n);

        n = waysToSplitArray2(arr);
        System.out.println(n);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*intuition:
    * build prefix sum,
    * start traverse from 0 -each time add to ans if prefix[r] >= prefix[last] - prefix[r] */
    //1st:
    public static int waysToSplitArray(int[] arr){
        long[] prefix = new long[arr.length];
        int ans = 0; //count of valid subArray.
        int r = 0;
        int l = 0;

//      build the prefix arr:
        prefix[0] = arr[0];
        for(int i = 1 ; i < arr.length ; ++i){
            prefix[i] = prefix[i-1] + arr[i];
        }
//        uncomment to check the prefix sum:
//        System.out.println(Arrays.toString(prefix));

        for(; r < arr.length - 1 ; ++r){ //constraint: must be at least 1 element to the left so "len - 1"!!
            if(prefix[r] >= prefix[prefix.length - 1] - prefix[r]){
                ++ans;
            }
        }
        //time: O2n:  O1n-prefix; o1n-counting.
        // space: O1n - for prefix sum.
         return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //2nd:
    public static int waysToSplitArray2(int[] arr) {
        int  ans = 0; //count number of valid subArray.
        long t = 0; //prefix total
        long l = 0; //left section

//      build the prefix total:
        for(int i : arr){
            t += i;
        }

        for(int r = 0 ; r < arr.length - 1 ; ++r){
            l += arr[r];

            if(l >= t - l)
                ++ans;
        }

        return ans;
        //time: O2n:  O1n-prefix; o1n-counting.
        // space: O1 - no prefix sum, just integer who total sum of entire arr.
    }
}
