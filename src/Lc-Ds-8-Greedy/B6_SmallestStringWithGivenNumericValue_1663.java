import java.util.Arrays;
import java.util.Map;

/*problem:
Return the lexicographically smallest string with:
1. sum == k
2. length == n
lexicographically: each char==number: a=1, b=2... z=26 */

/*motivation: first use biggest char possible, when too big - start decreasing size.*/

/*solutions:
1st - fill all with 'a' then swap with z as necessary.
2nd - 1st, optimize, not fill all with, instead leave room for it if necessary. */
public class B6_SmallestStringWithGivenNumericValue_1663 {
    /*psudo:
    * fill all String with 'a' as it smallest possible.
    * then try to change all to z(=25) if too big, use whatever number left in k. */
    public String getSmallestString(int n, int k) {
        char[] ans = new char[n];
        Arrays.fill(ans, 'a');
        k -= n; //since all fill with 1's - the min possible value.

        for (int i = n - 1 ; (i >= 0 && k > 0) ; i--){
            int add = Math.min(25, k); //why 25 and not 26? since already fill with 1(=a), if not put z is 26-1=25.

            ans[i] = (char)(ans[i] + add);
            k -= add;
        }

//        time: O2n:  O1n - fill with a. O1n-iterate and adjust the chars.
//        space: O1 do not count output.
        return new String(ans);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public String getSmallestString_optimize(int n, int k) {
        char[] ans = new char[n];

        for (int i = n - 1 ; i >= 0 ; i--){
            int add = Math.min(26, k - i);

            ans[i] = ((char) ('a' + add - 1));
            k -= add;
        }

//        time: O1n
//        space: O1
        return new String(ans);
    }
}