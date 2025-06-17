import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class A5_LetterCombinationsofPhoneNumber_17 {
    public static void main(String[] args) {
        Solution_A5_LetterCombinationsofPhoneNumber_17_DFS_global_vars obj_17 = new Solution_A5_LetterCombinationsofPhoneNumber_17_DFS_global_vars();
        // Test cases
        System.out.println(obj_17.letterCombinations("23")); // Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(obj_17.letterCombinations(""));  // Output: []
        System.out.println(obj_17.letterCombinations("2"));  // Output: ["a","b","c"]
    }
}
/*problem:
use "phone digits" return all possible letter combinations from that digits. */

/* solutions (note, for practice build associated "digits-chars" map differently):
 * 1-DFS with global vars - readable.
 * 2-DFS no global vars - but so elegant. */

/* complexity (for all solutions): long math! here is approximately:

time:  O(3^n * 4^m) * L.
n==all numbers with 3 options: ( 2, 3, 4, 5, 6, 8)
m==all numbers with 4 options: ( 7, 9)
L==String "digits".len()

space: O(3^n * 4^m) in the worst case. This is the space required for the output list to store all the combinations.
                 O1L-recursion will go as far as L - beyond that we backtrack.
                 O1-hashmap as is constant, so O1. */

class Solution_A5_LetterCombinationsofPhoneNumber_17_DFS_global_vars {
    private String phoneDigits; //save input "Digits" at global comfortable way
    private List<String> ans = new ArrayList<>();
    private Map<Character, String> letters; //hashmap to store number and its chars.
    public Solution_A5_LetterCombinationsofPhoneNumber_17_DFS_global_vars() { //constructor to populate map of digits to chars.
        letters = initializeMapping();
    }
    private Map<Character, String> initializeMapping() {
        Map<Character, String> mapping = new HashMap<>();
        mapping.put('2', "abc");
        mapping.put('3', "def");
        mapping.put('4', "ghi");
        mapping.put('5', "jkl");
        mapping.put('6', "mno");
        mapping.put('7', "pqrs");
        mapping.put('8', "tuv");
        mapping.put('9', "wxyz");
        return mapping;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public List<String> letterCombinations(String digits) { //START HERE
        // edge case:
        if (digits.length() == 0) {
            return ans;
        }

        this.phoneDigits = digits;
        backtrack(0, new StringBuilder()); //0-start from first location, new StringBuilder()-empty cur path.
        return ans;
    }

    private void backtrack(int index, StringBuilder path) {
//        base case - successfully build valid combination. backtrack to check if there are others.
        if (path.length() == phoneDigits.length()) {
            ans.add(path.toString()); //usually at backtrack use "new", here dont need as it global var.
            return;
        }

//        explore other combinations:
        String possibleLetters = this.letters.get(phoneDigits.charAt(index)); // for this specific curr digit, associate all its "chars"
        for (char letter : possibleLetters.toCharArray()) { //each digit associate with 3-4 different chars, so check all possibilities with it.
            path.append(letter);
            backtrack(index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A5_LetterCombinationsofPhoneNumber_17_no_global_vars {
    public List<String> letterCombinations(String digits) {//start here
        List<String> result = new ArrayList<>();
//        edge case:
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtrack(result, digits, mapping, "", 0);
        return result;
    }

    private void backtrack(List<String> result, String digits, String[] mapping, String curPath, int index) {
//        base case - successfully build valid combination. backtrack to check if there are others.
        if (index == digits.length()) {
            result.add(curPath);
            return;
        }

        String letters = mapping[digits.charAt(index) - '0']; // for this specific curr digit, associate all its "chars"
        for (int i = 0; i < letters.length(); i++) { //each digit associate with 3-4 different chars, so check all possibilities with it.
            backtrack(result, digits, mapping, curPath + letters.charAt(i), index + 1);
        }
    }
}