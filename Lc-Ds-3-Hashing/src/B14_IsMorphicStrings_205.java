import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
/*problem:
Two strings s and t are isomorphic if the characters in s can be replaced to get t,
and characters in t can be replaced to get s */

/*solutions:
* 1st-use arrays to map both StoT and TtoS then compare.
* 2nd-use hashmap to index - didnt get that...*/

public class B14_IsMorphicStrings_205 {
    public static void main(String[] args) {
        B14_IsMorphicStrings_205 checker = new B14_IsMorphicStrings_205();       // creating the Object
        System.out.println("1st way with arr: ");
//        System.out.println(checker.isIsomorphic1("egg", "add"));     // Output: true
//        System.out.println(checker.isIsomorphic1("foo", "bar"));     // Output: false
//        System.out.println(checker.isIsomorphic1("paper", "title")); // Output: true
        System.out.println(checker.isIsomorphic1("abcdefghijklmnopqrstuvwxyzva", "abcdefghijklmnopqrstuvwxyzck")); // Output: false
        System.out.println("second way with hashmap - my favorite: ");
//        System.out.println(checker.isIsomorphic2("egg", "add"));     // Output: true
//        System.out.println(checker.isIsomorphic2("foo", "bar"));     // Output: false
//        System.out.println(checker.isIsomorphic2("paper", "title")); // Output: true
        System.out.println(checker.isIsomorphic2("abcdefghijklmnopqrstuvwxyzva", "abcdefghijklmnopqrstuvwxyzck")); // Output: false
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    * use 2 int[], and fill them with -1,
    * each time map both StoT and Ttos - if have change - its false!
    * when exhasted - true.*/
    public boolean isIsomorphic1(String s, String t) {
        int[] map_S_to_T = new int[256];
        int[] map_T_to_S = new int[256];

        Arrays.fill(map_S_to_T, -1);
        Arrays.fill(map_T_to_S, -1);

        //        edge case:
        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Case 1: No mapping exists in either map:
            if (map_S_to_T[c1] == -1 && map_T_to_S[c2] == -1) {
                map_S_to_T[c1] = c2;
                map_T_to_S[c2] = c1;
            }

            // Case 2: the map doesnt match at both!
            else if (!(map_S_to_T[c1] == c2 && map_T_to_S[c2] == c1)) {
                return false;
            }
        }
        return true;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public boolean isIsomorphic2(String s, String t) {
//        edge case:
        if (s.length() != t.length()) {
            return false;
        }
        return transformString(s).equals(transformString(t));
    }

    private String transformString(String s) {
        Map<Character, Integer> indexMapping = new HashMap<>(); //<char, its index>
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);

            if (!indexMapping.containsKey(c1)) {
                indexMapping.put(c1, i);
            }

            sb.append(indexMapping.get(c1));
            sb.append(",");//got to have it here! why? i dont know..
        }
        debugging:
        System.out.println(sb.toString());
//        n== size of s
//        m== size of t
//        time: O1(n+m)
//        space:O1(n+m)
        return sb.toString();
    }
}