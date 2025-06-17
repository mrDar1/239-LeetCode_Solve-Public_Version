/*problem:
* given 2 Strings, consist only lowercase english.
* return how many chars to add to s, until t can be subsequence of s.*/

/*solutions:
both same, 2nd more elegant*/

/*psudo:
* whenever both chars equal - advance both ptrs.
* when not equal - advance only s ptr.
* when s exhausted, count number of char from t ptr till end (= number of char to add to s) */
public class B7_AppendCharacterstoStringtoMakeSubsequence_2486 {
    public int appendCharacters(String s, String t) {
        int s_ptr = 0;
        int t_ptr = 0;
        int ans = 0;

        while (s_ptr < s.length() && t_ptr < t.length()){

            if (s.charAt(s_ptr) == t.charAt(t_ptr)){
                ++s_ptr;
                ++t_ptr;
            }

            else /*if (s.charAt(s_ptr) != t.charAt(t_ptr))*/{
                ++s_ptr;
            }
        }

        while (t_ptr < t.length()){
            ++ans;
            ++t_ptr;
        }

        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B7_AppendCharacterstoStringtoMakeSubsequence_2486_same_more_elegant {
    public int appendCharacters(String s, String t) {
        int s_ptr = 0;
        int t_ptr = 0;

        while (s_ptr < s.length() && t_ptr < t.length()) {
            if (s.charAt(s_ptr) == t.charAt(t_ptr)) {
                t_ptr++;
            }
            s_ptr++;
        }

        return t.length() - t_ptr;
    }
}