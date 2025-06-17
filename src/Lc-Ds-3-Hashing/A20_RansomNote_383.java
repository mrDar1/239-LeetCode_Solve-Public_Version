import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/*problem:
* we want to crete the "ransomNote" from "magazine", each letter can use once.
* if poossible return true. */

//4 solution:
// 1. 1 count hashmap - my first successful try!
// another solutions from LC for study:
// 2. java - find index for each letter and remove it from "magazine" (no good for interview)
// 3. 2 count freq hashmap.
// 4. 1 count freq hashmap - same idea but bit different implement
// 5. sorting and stack - nice way! store both at sorted stack then compare letter by letter.

public class A20_RansomNote_383 {
    public static void main(String[] args) {
        String ransomNote = new String("aa");
        String magazine = new String("aab");

        boolean n = canConstruct1(ransomNote, magazine);
        System.out.println(n);

        n = canConstruct2(ransomNote, magazine);
        System.out.println(n);

        n = canConstruct3(ransomNote, magazine);
        System.out.println(n);

        n = canConstruct4(ransomNote, magazine);
        System.out.println(n);

        n = canConstruct5(ransomNote, magazine);
        System.out.println(n);
    }
/*  psudo:
* traverse magazine and count chars.
* traverse ransomenote and each time compare - if bigger than magazine return false
* at end return true*/
    public static boolean canConstruct1(String ransomNote, String magazine) {
        Map<Character, Integer> magmap = new HashMap<>(); //magazine map <letter, number of occurrences>

//        edge case:
        if (ransomNote.length() > magazine.length()) return false;

        for (Character c : magazine.toCharArray()){
            magmap.put(c, magmap.getOrDefault(c, 0) + 1);
        }

        for(Character c : ransomNote.toCharArray()){
            magmap.put(c, magmap.getOrDefault(c, 0) - 1);
            if (magmap.get(c) < 0){
                return false;
            }
        }
//      time: O1n+1m - traverse once on magazine (n) and ransomNote(m)
//      space: O1n - size of magazine
        return true;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


//2nd solution: each time - remove from the "magazine" - the current char at "ransomNote".
    public static boolean canConstruct2(String ransomNote, String magazine) {
        // For each character, c,  in the ransom note.
        for (char c : ransomNote.toCharArray()) {
            // Find the index of the first occurrence of c in the magazine.
            int index = magazine.indexOf(c);
            // If there are none of c left in the String, return False.
            if (index == -1) {
                return false;
            }
            // Use substring to make a new string with the characters
            // before "index" (but not including), and the characters
            // after "index".
            magazine = magazine.substring(0, index) + magazine.substring(index + 1);
        }
        // If we got this far, we can successfully build the note.
        return true;
    //        time: O n*2m == On*m- m=find the letter we need at magazine.
    //        space: Om - magazine
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    //    3rd solution:
//    psudo: hash both string and count each letter, check that magazine is bigger.
    public static boolean canConstruct3(String ransomNote, String magazine) {
        Map<Character, Integer> magmap = new HashMap<>(); //magazine hashmap <char, occurrences>
        Map<Character, Integer> ranmap = new HashMap<>(); //ransomNote hashmap <char, occurrences>

//        edge case:
        if (ransomNote.length() > magazine.length()) return false;

//        count each letter in magzine
        for (char c : magazine.toCharArray()) {
            magmap.put(c, magmap.getOrDefault(c, 0) + 1);
        }

        //        count each letter in randsome
        for (char c : ransomNote.toCharArray()) {
            ranmap.put(c, ranmap.getOrDefault(c, 0) + 1);
        }

        for (char c : ranmap.keySet()) {
            if (magmap.get(c) == null){
                return false;
            }
            if (magmap.get(c) - ranmap.get(c) < 0) {
                return false;
            }
        }
        return true;
        //time: On+Om+On. n==ransome hashmap, m=magazine hashmap, n-ransom hash values "-" magazine hashvalues. ->Om
        // space: Ok/O1 - k==what input we can have at ransome/magazine, usually 26 english letters O1
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //    auxiliry method:
    // Takes a String, and returns a HashMap with counts of each character.
    private static Map<Character, Integer> makeCountsMap(String s) {
        Map<Character, Integer> counts = new HashMap<>();

        for (char c : s.toCharArray()) {
            int currentCount = counts.getOrDefault(c, 0);
            counts.put(c, currentCount + 1);
        }
        return counts;
    }
    public static boolean canConstruct4(String ransomNote, String magazine) {

        // Check for obvious fail case.
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // create hashmap with method: < 1 letter at a time, count how many times its appear >
        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);

//        traverse ransomNote, each letter we see - remove from magazine hashmap, if we cant-return false.
        for (char c : ransomNote.toCharArray()) {
            int countInMagazine = magazineCounts.getOrDefault(c, 0);
            if (countInMagazine == 0) {
                return false;
            }
            magazineCounts.put(c, countInMagazine - 1);
        }
        return true;
        //time: On+m. m=magazine hashmap, n=ransome, then traverse ransome and each time remove letter.
        // space: Ok/O1 - more info above.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    // Please, if there's a nicer way of doing this, without getting tangled in
    // primitives vs Java's generics let me know in the article comments :-)
    private static Stack<Character> sortedCharacterStack(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        Stack<Character> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            stack.push(charArray[i]);
        }
        return stack;
    }

    public static boolean canConstruct5(String ransomNote, String magazine) {
        // Check for obvious fail case.
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Reverse sort the characters of the note and magazine, and then
        // put them into stacks.
        Stack<Character> magazineStack = sortedCharacterStack(magazine);
        Stack<Character> ransomNoteStack = sortedCharacterStack(ransomNote);

        // And now process the stacks, while both have letters remaining.
        while (!magazineStack.isEmpty() && !ransomNoteStack.isEmpty()) {
            // If the tops are the same, pop both because we have found a match.
            if (magazineStack.peek().equals(ransomNoteStack.peek())) {
                ransomNoteStack.pop();
                magazineStack.pop();
            }
            // If magazine's top is earlier in the alphabet, we should remove that
            // character of magazine as we definitely won't need that letter.
            else if (magazineStack.peek() < ransomNoteStack.peek()) {
                magazineStack.pop();
            }
            // Otherwise, it's impossible for top of ransomNote to be in magazine.
            else {
                return false;
            }
        }

        // Return true if the entire ransomNote was built.
        return ransomNoteStack.isEmpty();

//        m==magazine.len
//        n==ransomNote.len
//        time:  Om log m - sort, another m to push to stack, also n log n for magazine, and n to push to stack. then another n+m to comare them both. dominant is just Om log m
//        space: Om+n - for both stack.
    }
}
