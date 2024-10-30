import java.util.*;
/*problem: return List<List<String>>: inside each inner List - all the words that from same anagram group */
public class A16_GroupAnagram_49 {
    public static void main(String[] args){
        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println( groupAnagrams(s) );
    }
/*  intuition: hashmap: <String: sorted word, List<>: group all words of that anagram unsorted>
    traverse the sentence: each word to sort then check  if already got it:
    ig got it - append, if dont - create and add curr.
    return new: List<List<String>> values of the hashmap - which are all anagram groups.*/
    public static List<List<String>> groupAnagrams(String[] strs){
        Map<String , List<String>> groups = new HashMap<>();

        for (String s : strs){
            char[] arr = s.toCharArray(); //must convert to char[] so it be mutuable and can sort.
            Arrays.sort(arr); //sorted word will be easier to compare later.
            String key = new String(arr); //convert from char[] to String! important because hashmap keys must be immutable in java.

            if ( !groups.containsKey(key) ){
                groups.put(key, new ArrayList<String>());
            }

            groups.get(key).add(s); //inside sorted word key - add the new word (add the unsorted original).
        }
//        n=strs,
//        m=average length of each word.
//        time: O n * m *(log m) == n(m*m) since its more dominant.
//        space: On*m - each word will be placed in array within the hashmap.
        return new ArrayList<>(groups.values());
    }
}