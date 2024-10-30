import java.util.*;
/*problem:
* sort string - but only the "order" char, ignore all else - can stay or move to any place be accept. */

/*solutions:
* 1st-build custom made comparator
* 2nd-use classic way of freq hashmap*/

public class B16_CustomSortString_791 {
    public static void main(String[] args){
        String order = new String("cba");
        String s = new String("abcd");
        System.out.println(customSortString1(order, s));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static String customSortString1(String order, String s) {
        // Create char array for editing
        int len = s.length();
        Character[] muatable_s = new Character[len];
        for (int i = 0; i < len; i++) {
            muatable_s[i] = s.charAt(i);
        }

        // Define the custom comparator
        Arrays.sort(muatable_s, (c1, c2) -> {
            return order.indexOf(c1) - order.indexOf(c2);
        });

        StringBuilder sb = new StringBuilder();
        for (Character c: muatable_s) {
            sb.append(c);
        }

//        n==len of s
//        k==len of order
//        time: O n log n: 1n-copy n to mutable string; Onlogn-Sort; 1n-stringbuilder ans
//        space: O1n - mutable string (do not count output string)
        return sb.toString();
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*psud:
build freq map of s<char,freq>
traverse "order" each time get from map that order and put as many time as we have it.
keep filling ans until hashmap exhausted */
    public static String customSortString2(String order, String s) {
        // Create a frequency table
        Map<Character, Integer> freq = new HashMap<>(); // <char, freq>
        StringBuilder result = new StringBuilder();

        // Initialize frequencies of letters
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            freq.put(letter, freq.getOrDefault(letter, 0) + 1);
        }

        // Iterate order string to append to result
        for (int i = 0; i < order.length(); i++) {
            char letter = order.charAt(i);
            while (freq.getOrDefault(letter, 0) > 0) {
                result.append(letter);
                freq.put(letter, freq.get(letter) - 1);
            }
        }

        // Iterate through freq and append remaining letters
        for (char letter : freq.keySet()) {
            int count = freq.get(letter);
            while (count > 0) {
                result.append(letter);
                count--;
            }
        }
//        n==len of s
//        k==len of order
//        time: On : 1n-build freq map; 1k-traverse "order"l; o(n-k)-fill what left.
//        space:On hashmap
        return result.toString();
    }
}