import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;

/*problem:
return true if its "close".
"close":
    1. if got same chars at least once (example: abbbb, aabaa - is good ).
    2. if got same freq for all chars(can swap) */

/* 3 solutions:
1st - using hashmap.
2nd - lc same idea with array
3rd - like 2 with auxiliary method for practice */

public class B17_DetermineIf2StringsAreClose_1657 {
    public static void main(String[] args){
        System.out.println(closeStrings1("cabbba", "abbccc"));
        System.out.println(closeStrings2("cabbba", "abbccc"));
        System.out.println(closeStrings3("cabbba", "abbccc"));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    count how many occurrences each char has in both Strings.
    then compare all chars and check that the other string has them.
    lastly, we save in increasing List both occurrences of each word and equals==true; */
    public static boolean closeStrings1(String word1, String word2) {
        Map<Character, Integer> word1Map = new HashMap<>();
        Map<Character, Integer> word2Map = new HashMap<>();

//        edge case:
        if (word1.length() != word2.length()) {
            return false;
        }

        for (char c : word1.toCharArray()) {
            word1Map.put(c, word1Map.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            word2Map.put(c, word2Map.getOrDefault(c, 0) + 1);
        }

        if ( !word1Map.keySet().equals(word2Map.keySet()) ) {
            return false;
        }

        List<Integer> word1FrequencyList = new ArrayList<>(word1Map.values());
        List<Integer> word2FrequencyList = new ArrayList<>(word2Map.values());

        Collections.sort(word1FrequencyList);
        Collections.sort(word2FrequencyList);

//        debugging:
//        System.out.println(word1FrequencyList);
//        System.out.println(word2FrequencyList);

//        n==word1.len
//        m==word2.len
//        time: Onlogn : On+m build hshmap; On+m copy to List; On logn sort
//        sace: On+m
        return word1FrequencyList.equals(word2FrequencyList);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //    solution 2 -  LC Using Frequency Array Map
    public static boolean closeStrings2(String word1, String word2) {
        int word1Map[] = new int[26];
        int word2Map[] = new int[26];

//        edge case:
        if (word1.length() != word2.length()) {
            return false;
        }

        for (char c : word1.toCharArray()) {
            word1Map[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            word2Map[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if ((word1Map[i] == 0 && word2Map[i] > 0) ||
                (word2Map[i] == 0 && word1Map[i] > 0)) {
                    return false;
            }
        }
        Arrays.sort(word1Map);
        Arrays.sort(word2Map);
        return Arrays.equals(word1Map, word2Map);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //    solution 3 - sam idea with auxilary method
    public static boolean closeStrings3(String word1, String word2) {
        int[] freq1 = getFrequency(word1);
        int[] freq2 = getFrequency(word2);

        return compareFrequency(freq1, freq2);
    }
    public static int[] getFrequency(String str) {
        int[] freq = new int[26];
        for(int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        return freq;
    }
    public static boolean compareFrequency(int[] freq1, int[] freq2) {
        for(int i = 0; i < 26; i++)
            if((freq1[i] != freq2[i]) && (freq1[i] * freq2[i] == 0))
                return false;

        Arrays.sort(freq1);
        Arrays.sort(freq2);
        for(int i = 0; i < 26; i++)
            if(freq1[i] != freq2[i]) return false;

        return true;
    }
}