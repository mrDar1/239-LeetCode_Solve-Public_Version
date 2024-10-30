import java.util.Arrays;
/*problem:
 * Return the maximum tastiness as possible, out of "k" candy's in a basket.
 * tastiness == smallest absolute difference of the prices of any two candies in the basket.
 * constraint: cannot pick twice the same candy for 1 basket!
 *
 * in my words:
 * we want to pick from price[] different candies, with the biggest difference of prices
 * from each other, so can maximize "tastiness" */

/*solutions:
* 1st - use classic framework of max-threshold binary search
* 2nd - uas 1st, but changed framework with +1 - really hate that method but good to know, as it common...*/


/*intuition:
use max-threshold, binary search: to search for "tastiness" lvl - each time search "mid" "tastiness" lvl,
    if succeed - try again with even bigger "tastiness",
    if fail - try again with less "tastiness".

isValid - pick items only when "tastiness" above k.

right boundary - cannot be bigger than the "tastiness" of cheapest and most expensive candies. */

public class B12_MaximumTastinessofCandyBasket_2517 {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);

        int left = 0;
        int right = price[price.length - 1] - price[0];

        while (left <= right) {
            int mid = left + (right - left) / 2; // upper mid

            if (canFormBasket(price, k, mid)) {
                left = mid + 1;      // succeed - try again with even bigger "tastiness",
            } else {
                right = mid - 1; // fail - try again with less "tastiness".
            }
        }

        return right;
    }

    private boolean canFormBasket(int[] price, int k, int tastiness) {
        int count = 1;
        int lastSelected = price[0];

        for (int i = 1; i < price.length; i++) {
            if (price[i] - lastSelected >= tastiness) {
                count++;
                lastSelected = price[i]; //cannot use same candy twice, must advance.
                if (count == k) { //optimize: reach k valid items in basket, its valid can stop
                    return true;
                }
            }
        }

        return count >= k; //if cannot assemble k candies who met the requirement (above "tastiness" lvl) - return false.
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B12_MaximumTastinessofCandyBasket_2517_another_framework {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);

        int left = 0;
        int right = price[price.length - 1] - price[0];

        while (left < right) {
            int mid = left + (right - left + 1) / 2 ; // upper mid

            if (canFormBasket(price, k, mid)) {
                left = mid;      // succeed - try again with even bigger "tastiness",
            } else {
                right = mid - 1; // fail - try again with less "tastiness".
            }
        }

        return right;
    }

    private boolean canFormBasket(int[] price, int k, int tastiness) {
        int count = 1;
        int lastSelected = price[0];

        for (int i = 1; i < price.length; i++) {
            if (price[i] - lastSelected >= tastiness) {
                count++;
                lastSelected = price[i]; //cannot use same candy twice, must advance.
                if (count == k) { //optimize: reach k valid items in basket, its valid can stop
                    return true;
                }
            }
        }

        return count >= k; //if cannot assemble k candies who met the requirement (above "tastiness" lvl) - return false.
    }
}