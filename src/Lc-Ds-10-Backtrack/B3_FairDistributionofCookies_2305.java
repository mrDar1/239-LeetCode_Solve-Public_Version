//link: https://leetcode.com/problems/fair-distribution-of-cookies/description/

import java.util.Arrays;
/*problem:
given int[] "cookies" - divide all to "k" children.
Return the minimum unfairness of all distributions.
unfairness == sums the cookies of the kid with highest number of cookies.
constraint: cannot split cookie bag
constraint:  2 <= k <= cookies.length */

/* solutions:
* 1-backtrack
* 2-optimize with sort and binary search of min threshold */

/*motivation: wish to split as even possible - to lower sum of cookies for fates kid */

//1st solutions:
public class B3_FairDistributionofCookies_2305 {
    public int distributeCookies(int[] cookies, int k) {//Start here
        int[] cookPerChild = new int[k];//how many cookies each child received.
        int count_noCookieChild = k;    //number of children with no cookie - at start we didn't distribute so all children dont have cookie==k.
        int i = 0;

        return dfs( i, cookies, k, cookPerChild, count_noCookieChild );
    }
    private int dfs(int i, //index at cookies int[].
                    int[] cookies,
                    int k,
                    int[] cookPerChild,
                    int count_noCookieChild) {


//        base case - when successful finish distribute all our cookies - find fates kid:
        if (i == cookies.length){
            int max = Integer.MIN_VALUE;
            for (int j : cookPerChild){
                max = Math.max(max, j);
            }
            return max;
        }

//         constraint: cant have children end with no cookie at all - prune:
        if (count_noCookieChild > cookies.length - i){
            return Integer.MAX_VALUE; //represent failure.
        }

        int ans = Integer.MAX_VALUE;

//        DFS backtrack, explore other distribute method:
        for (int j = 0; j < k ; j++) { //why limit (j<k)? -only divide as number of the children

            count_noCookieChild -= cookPerChild[j] == 0 ? 1 : 0; //if that kid already received before then the number of kids witn no cookies not changing.
            cookPerChild[j] += cookies[i];//we want to share up to k children i cookies!!!

            ans = Math.min(ans, dfs(i+1, cookies, k, cookPerChild, count_noCookieChild));

            cookPerChild[j] -= cookies[i];
            count_noCookieChild += cookPerChild[j] == 0 ? 1 : 0;;
        }
        return ans;
    }
}

/*      complexity:
        time:   : Ok^n - try to distribute to k child, n cookies bag.
        space:  : Ok+n - Ok-for cookPerChild[], O1n-recursion max depth. */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
* use binary search of min-threshold:
* each time pick "mid" that represent sum of kid with most cookies.
* each call to isValid - sum cookies[] until reach "mid", then fill next kid bag with cookies.
*   when succeed - try again with lower number of cookies to that fat kid.
*   when fail - try again with more cookies to that fat kid.
*
* boundary's:
*  left == best edge case: number of children == number of cookie bags, will be the kid with max cookies at one bag.
*  right== worst edge case: only 1 child - will receive all cookies. (remember constraint: minimize as possible kid with most cookies.) */

//2nd solutions:
class B3_FairDistributionofCookies_2305_sort_and_binary_search {
    public int distributeCookies(int[] cookies, int k) {
        Arrays.sort(cookies);
        int left = cookies[cookies.length - 1];
        int right = Arrays.stream(cookies).sum();

        while (left <= right) {
            int[] cookPerChild = new int[k]; //how many cookies each child received, remember at start java auto initialize to 0.
            int mid = left + (right - left) / 2;

            if (isValid(cookies, cookPerChild, cookies.length -1, mid)) {
                right = mid - 1; //success! next time check if can do it with less candy to fates kid.
            } else {
                left = mid + 1;  //fail, next time check if can do it with more candy to fates kid.
            }
        }
        return left;
    }

    private boolean isValid(int[] cookies,
                            int[] cookPerChild, //how many cookies each child received.
                            int i, //index at cookies int[].
                            int mid) { //mid represent the kid with max cookies.


//        base case - success! all "k" child received at most "mid" cookies.
        if (i < 0) {
            return true;
        }

        for (int j = 0; j < cookPerChild.length; j++) {
            if (cookPerChild[j] + cookies[i] <= mid) {
                cookPerChild[j] += cookies[i];

                if (isValid(cookies, cookPerChild, i - 1, mid)) {
                    return true;
                }

                cookPerChild[j] -= cookies[i];
            }

//         constraint: cant have children end with no cookie at all - prune:
            if (cookPerChild[j] == 0) {
                break;
            }
        }
        return false;
    }
}

/*complexity:
* time: O n log n + k^n * log s
*       n log n: sort.
*       log s - binary search (s==right boundary==sum of all cookies)
*       k^n - each binary search - backtrack explore of split to k children n bags.
*
* space: On log n + k:
*       On-Ologn-java use varient of quicksort. python use TimSort up to n size.
*       k-each binary search, for cookPerChild[]. */