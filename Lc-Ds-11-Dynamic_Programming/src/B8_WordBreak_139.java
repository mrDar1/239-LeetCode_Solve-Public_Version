import java.util.*;
/*problem:
* return "true" if can create word "s" from wordDict - assemble in any way. can use duplicates.*/

/*solutions:
 * 0-BFS
 * 1st-top-down
 * 2nd-bottom up
 * 3rd-bottom up - other way
 * 4th-Trie  */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B8_WordBreak_139_BFS {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict); //easy search at "wordDict".
        boolean[] seen = new boolean[s.length() + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int start = queue.remove();

            if (start == s.length()) {
                return true;
            }

            for (int end = start + 1; end <= s.length(); end++) {
                if (seen[end]) {
                    continue;
                }

                if (words.contains(s.substring(start, end))) {
                    queue.add(end);
                    seen[end] = true;
                }
            }
        }

        return false;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

public class B8_WordBreak_139 {
//    top down:
    private String s;
    private List<String> wordDict;
    private int[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.s = s;
        this.wordDict = wordDict;
        this.memo = new int[s.length()];
        Arrays.fill(this.memo, -1);

//        n==s.length, m==wordDict.len, k==avg wordDict[0].len
//        time: O(n*m*k): we calculate recrusion n times.
//                        at each recursion, iterates over m words, for each word, substring costs k.

//        space: O2n - recursion, memoization.
        return dp(s.length() - 1);
    }

    private boolean dp(int i) {
//        base case - reach s len
        if (i < 0) return true;

//        already cached that val:
        if (memo[i] != -1) {
            return memo[i] == 1; //return true if its 1, false-if zero.
        }

        for (String word : wordDict) {
            // Calculate the starting index for the substring to compare with the current word
            int start = i - word.length() + 1;

            // cur word too long - prune.
            if (start < 0) {
                continue;
            }

            if( s.substring(start, i + 1).equals(word) &&
                dp(start - 1) )
            {
                memo[i] = 1;
                return true;
            }
        }

        memo[i] = 0;
        return false;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B8_WordBreak_139_bottom_up {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {

            for (String curWord : wordDict) {
                // cur word too long - prune.
                if (i < curWord.length() - 1) {
                    continue;
                }

                if (i == curWord.length() - 1 || dp[i - curWord.length()]) {
                    if (s.substring(i - curWord.length() + 1, i + 1).equals(curWord)){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B8_WordBreak_139_short_bottom_up {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len + 1]; // DP array to store whether the substring s[0..i-1] can be segmented
        dp[0] = true;  // Base case: an empty string can always be segmented

        for (int i = 1; i <= len; i++) {
            // Check all possible substrings that end at the current index i
            for (int j = 0; j < i; j++) {
                // If the substring s[0..j-1] can be segmented and s[j..i-1] is a word in the dictionary
                if (    dp[j] &&
                        words.contains(s.substring(j, i))   ) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition: first build a Trie of "wordDict", then traverse "s" and check if can assemble s from Trie. */
class TrieNode {
    boolean isWord; //flag to represent if at end of valid word.
    Map<Character, TrieNode> children; //map to children
    TrieNode() {
        this.children = new HashMap<>();
    }
}
class B8_WordBreak_139_Trie {
    public boolean wordBreak(String s, List<String> wordDict) {

//        1st part - build Trie of all "wordDict"
        TrieNode root = new TrieNode();

        for (String curWord : wordDict) {
            TrieNode cur = root;

            for (char c : curWord.toCharArray()) {
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TrieNode());
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }

//        2nd part - determine if "s" can be segmented to "wordDict"
        boolean[] dp = new boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {
//            only traverse when at start of valid words (or if at start position it must be start of valid word)
            if (i == 0 || dp[i - 1]) {
                TrieNode cur = root;
                for (int j = i ; j < s.length() ; j++) {
                    char c = s.charAt(j);

//                    cur char not at Trie - prune.
                    if (!cur.children.containsKey(c)) {
                        break;
                    }

//                    move to next char
                    cur = cur.children.get(c);

//                    at end of valid word - mark flag for next iteration.
                    if (cur.isWord) {
                        dp[j] = true;
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }
}
//        n==s.length, m==wordDict.len, k==avg wordDict[0].len
//        time: O(n^2 + m*k):
//                           m*k - build Trie.
//                           n^2 - traverse s, for each char at s search linearly at Trie.
//
//        space: On+ m*k:
//                          m*k - build Trie.
//                          n - DP.