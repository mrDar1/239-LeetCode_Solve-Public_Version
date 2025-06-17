/*problem:
* koko can eat up to k banana's in an hour.
* koko can eat only from 1 pile each hour no matter how fast she eat.
* return min k speed - that koko eats all banana's.
* constraint: piles.length <= h (=so always can found an answer! no fail)
* constraint: k is integer! */

/* motivation:
* low bound  == 1 (cannot eat less than 1 banana per hour, constraint: k==int).
* high bound == biggest piles[] - if eat faster than bigger pile, still wait until end of hour before can start next pile.
* to find the biggest pile - one will traverse all piles[] */

/*solutions:
* 1st - brute force. each round +1 till first success.
* 2nd - same idea with binary search */


public class A6_KokoEatingBananas_875 {
    /* psudo:
     * the brute force way: start with lower boundary and +1 each iteration till first success! */
    public int minEatingSpeed_brute_force(int[] piles, int h) {
        int l = 1; // low boundary. constraint: cannot eat less than 1 banana per hour.

        while (!mycheck(piles, h, l)) {//each time we fail==koko do not eat fast enough - increase speed for next round.
            ++l;
        }
        return l;
    }
    private boolean mycheck(int[] piles, int h, int l) {
        int sumHours = 0;

        for (double i : piles) {
            sumHours += Math.ceil(i / (double) l);  //Math.ceil==rounds a number UP to the nearest integer.
        }
        return sumHours <= h;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/* psudo:
 * initialize both boundary's and binary search to find threshold.
 * before each binary search we'll do check.
 * how to do the check function? - do bananas/k. */
    public int minEatingSpeed_my_binarysearch(int[] piles, int h) {

        int l = 1; // low boundary. constraint: cannot eat less than 1 banana per hour.
        int r = 0; //highest boundary - traverse all piles to find biggest one.
        for (int i : piles) {
            r = Math.max(r, i);
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (!check(piles, h, mid)) { //answer is not valid - increment k (=banana's per hour speed).
                l = mid + 1;
            } else {                     //answer is valid - check lower k maybe can decrease speed.
                r = mid - 1;
            }
        }
//        time: O1n + n*(log k) : O1n-find max boundary, o n log K-binary search + insiside each binary search we traverse all.
//        space: O1.
        return l; //when reach here is only when threshold found!
    }

    private boolean check(int[] piles, int h, int l) {
        int sumHours = 0;

        for (double i : piles) {
            sumHours += Math.ceil(i / (double) l);  //Math.ceil==rounds a number UP to the nearest integer.
        }
        return sumHours <= h;
    }
}