//got more solutions in LC
import java.util.*;
/*3 solutins:
* 1st - HashSet - best!
* 2nd - use boolean seen[26] - check each letter
* 3rd - Bitwise same idea but with Bits-faster! */


public class A4_CheckIfSentenceIsPangram_1832 {
    public static void main(String[] args){
        String s = new String("thequickbrownfoxjumpsoverthelazydog");
        System.out.println(checkIfPangram(s));
//        System.out.println(checkIfPangram_2(s));
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static boolean checkIfPangram(String sentence){
//    psudo: create hashset to input string - at end return if size==26(number of english letters)
        Set<Character> pangram = new HashSet<>();

        for(Character c : sentence.toCharArray()){
            pangram.add(c);
        }
//        time: On
//        space: O1/n - since all english letters are only 26.
        return (pangram.size() == 26);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static boolean checkIfPangram_use_seen_boolean(String sentence) {
        boolean[] seen = new boolean[26];

        for (char c : sentence.toCharArray()){
            seen[c - 'a'] = true;
        }

        for (boolean b : seen){
            if (!b){
                return false;
            }
        }

//        time: O2n - first create seen, 2nd-check if didnt see one of the letters.
//        space: O1n - for seen[]
        return true;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static boolean checkIfPangram_BitWise(String sentence) {
        int seen = 0;

        for (char ch : sentence.toCharArray()) {
            // letterindex will be as regular indexes: a=0, b=1, c=2, d=3..... z=25
            int letterindex = ch - 'a';

            int mask = 1 << letterindex;

            // Use 'OR' operation since we only add its bit for once.
            seen |= mask;
        }

        //((1 << 26) - 1) == 111111111...... - 25 times!!
        // like what we suppose to have if seen all letters!
        return seen == (1 << 26) - 1;

//        time:  O1n - but faster than regular!
//        space: O1
    }
}


