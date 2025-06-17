/*problem:
* given string of digits, int "m"(=divisor).
* return int[] of same length, with only 1/0.
* 1==word[<sum of 0-i>] divisible with m
* 0==word[<sum of 0-i>] not divisible with m. */
public class A14_FindtheDivisibilityArrayofString_2575 {
    public int[] divisibilityArray(String word, int m) {
        long cur = 0;
        int[] ans = new int[word.length()];

        for (int i = 0 ; i < word.length() ; ++i){
            cur = (cur * 10 + (word.charAt(i) - '0')) % m;

            if (cur == 0){
                ans[i] = 1;
            }else {
                ans[i] = 0;
            }
        }

//        time: O1n.
//        space:O1 - not count the ans int[].
        return ans;
    }
}