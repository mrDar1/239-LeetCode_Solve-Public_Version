/*problem:
return min number of days to make m bouquets, each with k adjacent flowers. or -1 if impossible.

bloomDay[]==when flower start blooming - after bloom will bloom forever
m==how many bouquets to make
k==how many adjacent flowers in each bouquet */

/*solutions:
* 1st - binary search of min threshold.
* 2nd - same as 1st, but changed framework with +1 - really hate that method but good to know, as it common... */

public class B14_MinimumNumberofDaystoMakemBouquets_1482 {
    /*psuod:
     *   binarySearch - to find min threshold:
     *   left=day 1 constraint // flowers.min()
     *   right = as far as int go - we don't know how much time will take flowers to bloom (can be late bloomer). // flowers.max()
     *
     * pick "middle" number of days - if valid, try again with smaller number of days.
     * if fail - try again with more days. */
    public int minDays(int[] bloomDay, int m, int k) {
        //        edge case:
        if (m * k > bloomDay.length){ //number of flower to put in each bouquets bigger than given flowers[].
            return -1;
        }

        int minDay = -1; // since binary search may fail, work with "midcopy". initialize to -1 in case of fail.

//        set left & right boundary's:
        int left = Integer.MAX_VALUE;
//        int right = Integer.MAX_VALUE;
        int right = 0;
        for (int i : bloomDay){
            right = Math.max(right, i);
            left  = Math.min(left, i);
        }


        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(bloomDay, m, k, mid)){
                minDay = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return minDay;
    }

    private boolean isValid(int[] bloomDay, int m, int k, int mid) { //mid represent days.
        int numOfBouquets = 0;
        int cur = 0; //number of flowers in curr bouquet.

        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= mid) {
                cur++;

                if (cur == k) { //each time reach k flowers, start fill another bouquets.
                    numOfBouquets++;
                    cur = 0;
                }
            } else { //reset number of flowers as constraint - in each bouquet, must be adjacent!!!
                cur = 0;
            }
        }

        return numOfBouquets >= m; //if made equal/more bouquets than require isValid.
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B14_MinimumNumberofDaystoMakemBouquets_1482_less_like_binary_search {
    public int minDays(int[] bloomDay, int m, int k) {
//        edge case:
        if (m * k > bloomDay.length){ //number of flower to put in each bouquets bigger than given flowers[].
            return -1;
        }

        int left = 1;
        int right = Integer.MAX_VALUE;
        int minDay = -1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isValid(bloomDay, m, k, mid)){
                minDay = mid;
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }

        return minDay;
    }

    private boolean isValid(int[] bloomDay, int m, int k, int mid) { //mid represent days.
        int numOfBouquets = 0;
        int cur = 0; //number of flowers in curr bouquet.

        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= mid) {
                cur++;

                if (cur == k) { //each time reach k flowers, start fill another bouquets.
                    numOfBouquets++;
                    cur = 0;
                }
            } else { //reset number of flowers as constraint - in each bouquet, must be adjacent!!!
                cur = 0;
            }
        }

        return numOfBouquets >= m; //if made equal/more bouquets than require isValid.
    }
}