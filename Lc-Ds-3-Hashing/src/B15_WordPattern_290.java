import java.util.HashMap;
import java.util.Map;
/*problem: map char keys to words and check all correct, can only be 1 map!
* very similar to B14*/

/*solutions:
* 1st-using 2 hashmap to map CtoS and StoC - then compare, see next!!
* 2nd-did it again like this one better!!
* 3rd-from lc - didnt get that...
*   hashmap<> - not specify time and doing raw use of it.*/

public class B15_WordPattern_290 {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
        System.out.println(wordPattern2("abba", "dog cat cat dog"));
    }

    /*intuition: use 2 hashmap and map each word to char and other way around,
    * traverse one of them and whenever not match - return false, when exhasted return true */
    public static boolean wordPattern(String pattern, String s) {
        HashMap <Character, String> map_char = new HashMap<>();
        HashMap <String, Character> map_word = new HashMap<>();
        String[] words = s.split(" ");

//        edge case:
        if (words.length != pattern.length())
            return false;

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if ( !map_char.containsKey(c)) {
                if (map_word.containsKey(w)) {
                    return false;
                } else {
                    map_char.put(c, w);
                    map_word.put(w, c);
                }

            } else {
                String mapped_word = map_char.get(c);
                if (!mapped_word.equals(w))
                    return false;
            }
        }
//        time: O1n+m -n-length of pattern,m-length of s
//        space: Ow- w=unique words in s, only 26 keys in english.
        return true;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static boolean wordPattern1_another_version(String pattern, String s) {
        HashMap <Character, String> map_char = new HashMap<>();
        HashMap <String, Character> map_word = new HashMap<>();
        String[] words = s.split(" ");

//        edge case:
        if (words.length != pattern.length()){
            return false;
        }

        for (int i = 0 ; i < pattern.length() ; ++i){
            char   c = pattern.charAt(i);
            String w = words[i];

            if ( !(map_char.containsKey(c))){
                if (!map_word.containsKey(w)){
                    map_char.put(c,w);
                    map_word.put(w,c);
                } else {
                    return false;
                }
            }else {
                if (!map_char.get(c).equals(w) || !map_word.get(w).equals(c) ){
                    return false;
                }
            }
        }

//        time: O1n+m -n-length of pattern,m-length of s
//        space: Ow- w=unique words in s, only 26 keys in english.
        return true;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static boolean wordPattern2(String pattern, String s) {
        HashMap map_index = new HashMap<>(); // <word from pattern or char, index>
        String[] words = s.split(" ");

        if (words.length != pattern.length())
            return false;

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if (!map_index.containsKey(c))
                map_index.put(c, i);

            if (!map_index.containsKey(w))
                map_index.put(w, i);

            if (map_index.get(c) != map_index.get(w))
                return false;
        }
//        time: O1n+m -n-length of pattern,m-length of s
//        space: Ow- w=unique words in s, only 26 keys in english.
        return true;
    }
}
