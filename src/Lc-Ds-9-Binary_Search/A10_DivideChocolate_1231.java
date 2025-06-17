import java.util.Arrays;
/*problem:
create (k+1) subarr, for each subarr find total sum.
return the sweet lvl, of less sweet chunk - try to make it high as possible.
constraint: never will be too much people (always k < sweetness.length()) */

/*solutions (all same, 1st is best other 2 practice and are mess):
* 1st - use binary search for max threshold.    ----like it best!
* 2nd - same, but first time solve the binary search is whole messy */

public class A10_DivideChocolate_1231 {
    /*psudo:
     * run on arr - find left and right boundary's:
     * left ==arr.minValue
     * right==arr.sum() / (k+1) - can never give more sweet than have. (if got 1 person will get arr.sum, that highest).
     *
     * use binary search - to find threshold:
     *      if success - try again with even higher sweet lvl for each piece.
     *      if fail    - try again with less sweet lvl for each piece.
     *      note: as here looking for a max and not min return right and not the usual left. */
    public int maximizeSweetness(int[] sweetness, int k) {
        int numOfPeople = k + 1; //constraint: number of sub array's
        int tempsum = 0; //helper for calculate

//        read more details about left and right at psudo.
        int left = Integer.MAX_VALUE;
        for (int i : sweetness) {
            left = Math.min(left, i);
            tempsum += i;
        }
        int right = tempsum / numOfPeople;
        /*above line could be replaced with:
        left =  Arrays.stream(sweetness).min().getAsInt();
        right = Arrays.stream(sweetness).sum() / numOfPeople;
        */

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (Valid(sweetness, mid) >= numOfPeople) {
                left  = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private int Valid(int[] sweetness, int mid) {
        int sumOfEatenPeople = 0;
        int curSweet = 0;

        for (int i : sweetness) {
            curSweet += i;

            if (curSweet >= mid) {
                ++sumOfEatenPeople;
                curSweet = 0; // prepending for next round - next person start with 0 sweet of chocolate.
            }
        }

//        time: O2n(log n) :O1n-find left+right boundarys ; Olog n - binarysearch * inside each binarysearch: O1n to validate
//        space: O1.
        return sumOfEatenPeople;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A10_DivideChocolate_1231_old_messy_way {
    /*psudo:
     * run on arr - find left and right boundary's:
     * left ==arr.minValue
     * right==arr.sum() / (k+1) - can never give more sweet than have. (if got 1 person will get arr.sum, that highest).
     *
     * use binary search:
     * mid is bit tricker than usual do +1
     * use inner loop:
     *   each time sum the current sweetness - if >= mid
     *   number_of_people that eat +1.
     *   after we traverse all we check it work:
     *
     *   if did all number of people got chocolate:
     *   check if we can do it with more == increase left boundary
     *   if fail - check if we can do it with high == decrease right boundary. */
    public int maximizeSweetness(int[] sweetness, int k) {
        int numOfPeople = k + 1; //constraint: number of sub array's
        int tempsum = 0; //helper for calculate

//        read more details about left and right at psudo.
        int left = Integer.MAX_VALUE;
        for (int i : sweetness) {
            left = Math.min(left, i);
            tempsum += i;
        }
        int right = tempsum / numOfPeople;
        /*above line could be replaced with:
        left =  Arrays.stream(sweetness).min().getAsInt();
        right = Arrays.stream(sweetness).sum() / numOfPeople;
        */

        while (left < right) {
            int mid = (left + right + 1) / 2;
            int sumOfEatenPeople = 0;
            int curSweet = 0;

            for (int i : sweetness) {
                curSweet += i;

                if (curSweet >= mid) {
                    ++sumOfEatenPeople;
                    curSweet = 0; // preperaing for next round - next person start with 0 sweet of chocolate.
                }
            }

            if (sumOfEatenPeople >= numOfPeople) { //try bigger number for mid
                left = mid;
            } else { //try smaller number for mid.
                right = mid - 1;
            }
        }

//        time: O2n(log n) :O1n-find left+right boundarys ; Olog n - binarysearch * inside each binarysearch: O1n
//        LC did find left+right boundarys : "OS / (k + 1)" - mybe as it different method.
//        space: O1.
        return right;
    }
}


