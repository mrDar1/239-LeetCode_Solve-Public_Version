import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*problem:
* return true if can assemble s1 from s2.
* (very similar to randsome note from magazine - but with cahnge! it must be substring of same length!
* when order doesnt matter)*/

/*solutions:
1st-my first brute force - count freq of both, then compare - fail!!! didn't get the problem right...
2nd-same method fix: use 2 freq hashmap, s1 not change, for s2-each time build the hashmap at s1 length
3rd-sort s1, and sort window of s2 each time advance the window and sort it - compare when sorted */

public class B13_PermutationInString_567 {
    public static void main(String[] args) {
        String s1 = new String("ab");
        String s2 = new String("eidbaooo");
        System.out.println(checkInclusion2(s1, s2));
        System.out.println(checkInclusion3(s1, s2));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
     * count freq for both Strings - then compare freq, if s1>s2 - return false,
     * when exhausted - true*/
    public static boolean checkInclusion_my_first_brute_force(String s1, String s2) {
        Map<Integer, Integer> maps1 = new HashMap<>();
        Map<Integer, Integer> maps2 = new HashMap<>();

        for(int i : s1.toCharArray()){
            maps1.put(i, maps1.getOrDefault(i, 0) + 1);
        }

        for(int i : s2.toCharArray()){
            maps2.put(i, maps2.getOrDefault(i, 0) + 1);
        }

        for (int i : maps1.keySet()){
            if (maps2.get(i) == null || maps2.get(i) - maps1.get(i) < 0){
                return false;
            }
        }

        return true;
//        n==s1
//        m==s2
        //time: On+m+ O1(the smaller one) -build hashmap for both then compare.
        // space: On+m - for both hashmap.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*psudo:
build hashmap for s1, and s2 - build each time new freq map of s1 length <char, freq>
compare freq of each - if s1 bigger than s2 - its false,
when exhausted - true
that way we dont need to sort!! */
    public static boolean checkInclusion3(String s1, String s2) {
        HashMap<Character, Integer> s1map = new HashMap<>(); // <char, freq>

//        edge case:
        if (s1.length() > s2.length()) {
            return false;
        }

        for (int i = 0; i < s1.length(); i++){
            s1map.put(s1.charAt(i), s1map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            HashMap<Character, Integer> s2map = new HashMap<>();
            for (int j = 0; j < s1.length(); j++) {
                s2map.put(s2.charAt(i + j), s2map.getOrDefault(s2.charAt(i + j), 0) + 1);
            }
            if (matches(s1map, s2map))
                return true;
        }
//        l1=length of s1,
//        l2=length of s2.
//        time:  O(l1+ l1*(l2-l1)) - traverse l1 once to build hashmap, then traverse part pf l2(size of l1) l2-l1 times.
//        space: O2*l1 - 2 hashmaps size of l1
        return false;
    }
    public static boolean matches(HashMap<Character, Integer> s1map, HashMap<Character, Integer> s2map) {
        for (char key : s1map.keySet()) {
            if (s1map.get(key) > s2map.getOrDefault(key, 0))
                return false;
        }
        return true;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//using sort
/*psudo:
* sort s1, and sort same length of part from s2 - if equals return true.
* if exhausted - return false. */
public static boolean checkInclusion2(String s1, String s2) {
    s1 = mysort(s1);
    for (int i = 0; i <= s2.length() - s1.length(); i++) {
        if (s1.equals(mysort(s2.substring(i, i + s1.length()))))
            return true;
    }
//    l1=length of s1,
//    l2=length of s2.
//    time: O l1(log l1) + l2(log l2) + Ol2 - traverse all s2 - each time sort other section.
//    space: Ol1
    return false;
}
    public static String mysort(String s) {
        char[] t = s.toCharArray();
        Arrays.sort(t);
        return new String(t);
    }
}
