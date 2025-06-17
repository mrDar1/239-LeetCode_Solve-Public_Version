import java.util.List;
import java.util.ArrayList;
/* problem:
Given a string s containing only digits, return all possible combinations of "valid IP addresses".

valid IP addresses:
we only decide where to put "."
not allowed to reorder or remove any digits in s.
use that to create exactly 4 different number, separated by "."
each number must be in range 0-255.
no leading 0
ans can be at any order.
in my words: return all valid combinations, when can only put 3 dots */

/*solutions:
* 1st-dfs
* 2nd-bfs*/

/*intuition:
 *  edge case:
 * if input stream.size() bigger than 12 - return "" - as it cannot be done! (if each number size is 3*4=12 cannot be more.)

* each DFS with 3 if's:
* 1st - prune invalid brunches (too long/short)
* 2nd - base case - when reach valid path add to ans.
* 3rd - explore in backtrack DFS matter other paths.

* each time check validation of one segment only, only when valid can try the next segment.
* segment == 1/2/3 digits. example: "x.yy.zzz.jjj"  x,yy - are 2 different segments. */

public class B6_RestoreIPAddresses_93 {
    public List<String> restoreIpAddresses(String s) {//START HERE!
        List<String> ans = new ArrayList<>();
        List<Integer> dotsLocations = new ArrayList<>(); //where chose to put dots at.
        DFS(s, 0, dotsLocations, ans);

        return ans;
    }

    private void DFS(
                        String s,
                        int startIndex,
                        List<Integer> dotsLocations,
                        List<String> ans
    ) {

//        prune invalid combinations (eliminate if remain too long or too short to create valid IP's).
        int remainingLength = s.length() - startIndex;
        int remainingNumberOfIntegers = 4 - dotsLocations.size(); //calculate how many segments left to use out of the 4 at start (constraint).
        if (
                remainingLength > remainingNumberOfIntegers * 3 || //constraint: cannot be bigger than 12 digits!
                remainingLength < remainingNumberOfIntegers        //constraint: cannot be more than 3 digits in each section of "segment"
        ) {
            return;
        }

//        base case: reach end of curPath - if valid add to ans.
        if (dotsLocations.size() == 3) {
            if (isValid(s, startIndex, remainingLength)) {
                StringBuilder sb = new StringBuilder(); //will hold curPath - full combination IP.
                //copy all segments, once by one
                int index = 0;
                for (Integer curDigit : dotsLocations) {
                    sb.append(s, index, curDigit);
                    index = curDigit;
                    sb.append('.');
                }
                //convert and add curPath to ans.
                sb.append(s.substring(startIndex));
                ans.add(sb.toString());
            }
            return;
        }

//        explore other paths/combinations (other locations to put the dots):
        for ( int curPos = 1 ; (curPos <= 3 && curPos <= remainingLength) ; ++curPos ) {
            if (isValid(s, startIndex, curPos)) { //only if curr segment valid, can move and check another.
                dotsLocations.add(startIndex + curPos);
                DFS(s, startIndex + curPos, dotsLocations, ans);
                dotsLocations.remove(dotsLocations.size() - 1);
            }
        }
    }

    private boolean isValid(String s, int start, int length) {
        if (length > 1 && s.charAt(start) == '0') { //constraint: if got only 1 digit: can be zero. if got 2/3 digits - cant start with 0.
            return false;
        }
//        nasty bug! next commented line dont work - pay attention:
//        return s.substring(start, start + length).compareTo("255") <= 0;
        int num = Integer.parseInt(s.substring(start, start + length));  //constraint: check number between 0-255. (to compare, first convert from string to int).
        return (num >= 0 && num <= 255);
    }
}

/*      complexity:
        n==numbers of different segments (constraint: 4)
        m==number of digit in each segments (constraint: 3)
        time: O(m^n * (m*n)):
                              at most m^n possibilities,
                              check 1-by1, segments are valid takes Om*n
           can say its O1 - since m==4, n==3.

        space: O(m*n)  */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B6_RestoreIPAddresses_93_secontapproach_itteration {
    private boolean isValid(String s, int start, int length) {
        return (
                length == 1 ||
                (s.charAt(start) != '0' &&
                (length < 3 ||
                s.substring(start, start + length).compareTo("255") <= 0))
        );
    }

    public List<String> restoreIpAddresses(String s) { //start here
        List<String> ans = new ArrayList<>();
        for (
                int len1 = Math.max(1, s.length() - 9);
                len1 <= 3 && len1 <= s.length() - 3;
                ++len1
        ) {
            if (!isValid(s, 0, len1)) {
                continue;
            }

            for (
                    int len2 = Math.max(1, s.length() - len1 - 6);
                    len2 <= 3 && len2 <= s.length() - len1 - 2;
                    ++len2
            ) {
                if (!isValid(s, len1, len2)) {
                    continue;
                }
                for (
                        int len3 = Math.max(1, s.length() - len1 - len2 - 3);
                        len3 <= 3 && len3 <= s.length() - len1 - len2 - 1;
                        ++len3
                ) {
                    if (
                            isValid(s, len1 + len2, len3) &&
                                    isValid(
                                            s,
                                            len1 + len2 + len3,
                                            s.length() - len1 - len2 - len3
                                    )
                    ) {
                        ans.add(
                                String.join(
                                        ".",
                                        s.substring(0, len1),
                                        s.substring(len1, len1 + len2),
                                        s.substring(len1 + len2, len1 + len2 + len3),
                                        s.substring(len1 + len2 + len3)
                                )
                        );
                    }
                }
            }
        }
        return ans;
    }
}