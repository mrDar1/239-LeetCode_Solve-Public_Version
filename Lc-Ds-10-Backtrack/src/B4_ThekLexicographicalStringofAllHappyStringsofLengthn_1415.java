import java.util.ArrayList;
import java.util.List;

/*problem:
generate all "happy strings" of a given length n, sort them lexicographically.
return the k-th string from the list, if there arent or k too big - return empty string.
"happy strings":
1. only "a,b,c" letter.
2. s[i] != s[i + 1] */

public class B4_ThekLexicographicalStringofAllHappyStringsofLengthn_1415 {
    public String getHappyString(int n, int k) {
        List<String> ans = new ArrayList<>();
        generateHappyStrings(n, "", ans);

        return ans.size() >= k ? ans.get(k - 1) : "";
    }

    private void generateHappyStrings(int n,
                                      String curPath,  //cur build combination.
                                      List<String> ans) {

//        base case - reach end:
        if (curPath.length() == n) {
            ans.add(curPath);
            return;
        }

        for (char ch : new char[]{'a', 'b', 'c'}) {
            if (curPath.length() == 0 || curPath.charAt(curPath.length() - 1) != ch) {
                generateHappyStrings(n, curPath + ch, ans); //concatenate String + cur char - very convenient at java.
            }
        }
    }
}