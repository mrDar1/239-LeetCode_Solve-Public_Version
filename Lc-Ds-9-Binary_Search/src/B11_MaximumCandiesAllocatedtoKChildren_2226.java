/*problem:
* return the max candies each child can get.
* constraint:
* divide candies equally!!! into k children - each child can take only 1 bag of candies.
* each bag in different sized as given in candies[] - can split them how you want, but cannot merge!!! */

/* intuition: use max-threshold binary search.
* right boundary == candies.max - for best edge case of only 1 child. */

public class B11_MaximumCandiesAllocatedtoKChildren_2226 {
    public int maximumCandies(int[] candies, long k) {
        long left = 1;
        long right = getMaxCandies(candies);

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (isValid(candies, k, mid)) {
                left = mid + 1;  // success, not try again when give more candie to each child!
            } else {
                right = mid - 1; // fail, try again with less candies per child.
            }
        }

        return (int)right;
    }

    private long getMaxCandies(int[] candies) {
        long max = 0;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }
        return max;
    }

    private boolean isValid(int[] candies, long k, long mid) {
        long count = 0;
        for (int candy : candies) {
            count += candy / mid;
            if (count >= k) {
                return true;
            }
        }
        return count >= k;
    }
}