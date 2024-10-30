/* problem:
 receive n - high bounds of search.
 build function that search in log n, after random number in sorted arr until find it.

 each time return number automated receive info:
-1 - num < pick
+1 - num > pick
0  - num == pick */

public class B1_GuessNumberHigherorLower_374 {
    public int guessNumber(int n) {
        int l = 0;
        int r = n;

        while (l <= r){
            int mid = l + (r - l) /2;

            if ((mid) == 0){
//                comment to avoid error in Intelij - to use in LC uncomment and remove above line
//            if (guess(mid) == 0){
                return mid;
            }

            if ((mid) > 0){
//                comment to avoid error in Intelij - to use in LC uncomment and remove above line
//            if (guess(mid) > 0){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
//        time: O log n - binary search.
//        space: O1.
        return l;
    }
}
