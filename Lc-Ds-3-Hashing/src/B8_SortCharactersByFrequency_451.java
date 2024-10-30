import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
/*problem:
sort the String in new order: the more freq element has - the more prioritize it will be.
if 2 elements has same freq - can be either way */
public class B8_SortCharactersByFrequency_451 {
    public static void main(String[] args) {
        Solution_SortCharactersByFrequency_451 sorter = new Solution_SortCharactersByFrequency_451();
        System.out.println(sorter.frequencySort("tree"));   // Output: "eetr" or "eert"
        System.out.println(sorter.frequencySort("cccaaa")); // Output: "aaaccc" or "cccaaa"
        System.out.println(sorter.frequencySort("Aabb"));   // Output: "bbAa" or "bbaA"
    }
}
class Solution_SortCharactersByFrequency_451 {
/*psudo: traverse s and count freq.
store all keys into a sorted decreased-by-freq list.
convert the list to a String using a StringBuilder. */

    public String frequencySort(String s) {
        Map<Character, Integer> counts = new HashMap<>(); // <element, freq>
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        List<Character> characters = new ArrayList<>(counts.keySet());
        Collections.sort(characters, (a, b) -> counts.get(b) - counts.get(a)); //sorts the list "characters" based on the frequencies of characters stored in the "counts" map, with characters having higher frequencies appearing earlier in the sorted list.

        StringBuilder sb = new StringBuilder();
        for (char c : characters) {
            int occurrences = counts.get(c);
            for (int i = 0; i < occurrences; i++) {
                sb.append(c);
            }
        }
//        time: O 2n+ n-logn: 1st: n-build map, 2nd: k-log k - sort, k==size of uniqe elements in map. 3rd: n-build sb - take every k and mulitply with occurence what gives n.
//        space: Ok+n: k-hashmap, n-sb.
        return sb.toString();
    }
}
