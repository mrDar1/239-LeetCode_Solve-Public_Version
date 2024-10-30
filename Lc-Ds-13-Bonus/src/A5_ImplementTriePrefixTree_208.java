//
///*problem signature:
//class Trie {
//    public Trie() {    }                            //initialize obj
//    public void insert(String word) {    }          //insert single word into Trie.
//    public boolean search(String word) {    }       //return true if found "word" at Trie (=inserted before)
//    public boolean startsWith(String prefix) {    } //return true - if there is in Trie word that start with cur prefix.
//                                                    //what is "prefix" stand-for? -we may search not for a single word but a several sequence of chars together! it can later assemble one longer word, or several words together.
//} */
//
///*problem clarify:
// * both "search" and "startsWith" similar:
// * "startsWith" - less strict - as it only need to check the start and not where prefix end.
// * "search" - is stricter - as it search for specific word, that end. must check not part from other word.
// * example:
// * if trie hold the word "apple", and searched for "app":
// * "startsWith" - return true
// * "search" - return false. */
//
///** problem additional data:
// * Your Trie object will be instantiated and called as such:
// * Trie obj = new Trie();
// * obj.insert(word);
// * boolean param_2 = obj.search(word);
// * boolean param_3 = obj.startsWith(prefix);
// */
//
///* solutions:
// * 1st - classic way, long.
// * 2nd - same, much shorter, for practice do the longer version. */
//
//
///*motivation:
//* since "search" and "startsWith" - so similar, use one function to check them both.
//* "search": if the last node - is the end of word char - return true.
//* "startsWith": do not need to check last char... its less strict */
//class Trie {
//    private TrieNode root;
//    public Trie() { //constructor
//        root = new TrieNode();
//    }
//
//    //insert single word into trie.
//    public void insert(String word) {
//        TrieNode dummy = root;
//
//        for (int i = 0; i < word.length(); i++) {
//            char curChar = word.charAt(i);
//
//            if (!dummy.containsKey(curChar)) { //create new node only if curChar not exist - that way our Trie has less nodes and so more efficient.
//                dummy.put(curChar, new TrieNode());
//            }
//            dummy = dummy.get(curChar);
//        }
//        dummy.setEnd();
//    }
//
//    //return true if found "word" at Trie (=inserted before)
//    //constraint: its stricter than "startsWith" - as word must end and not be part of longer word.
//    public boolean search(String word) {
//        TrieNode dummy = searchPrefix(word);
//
//        return (dummy != null && dummy.isEnd());
//    }
//
//    //return true if found this "prefix" at Trie.
//    public boolean startsWith(String prefix) {
//        TrieNode dummy = searchPrefix(prefix);
//        return (dummy != null);
//    }
//
//    //    auxiliary function - search "word"/"prefix"(=several chars together) - if success - return its last node, if fail - return null.
//    private TrieNode searchPrefix(String word) {
//        TrieNode dummy = root;
//
//        for (int i = 0; i < word.length(); i++) {
//            char curChar = word.charAt(i);
//
//            if (dummy.containsKey(curChar)) {
//                dummy = dummy.get(curChar);
//            } else {
//                return null;
//            }
//        }
//        return dummy;
//    }
//}
//
//class TrieNode {
//    private TrieNode[] links; // Array to store child nodes
//    private final int R = 26; // Number of possible characters (assuming only lowercase a-z)
//    private boolean isEnd; // Flag to indicate if the node represents the end of a word
//
//    public TrieNode() { //constructor
//        links = new TrieNode[R];
//    }
//
//    // check if there is a child node "ch"
//    public boolean containsKey(char ch) {
//        return links[ch - 'a'] != null;
//    }
//
//    // get relevant child node.
//    public TrieNode get(char ch) {
//        return links[ch - 'a'];
//    }
//
//    // put a child for cur char (allocate space not here, at caller function)
//    public void put(char ch, TrieNode node) {
//        links[ch - 'a'] = node;
//    }
//
//    public void setEnd() {
//        isEnd = true;
//    }
//
//    public boolean isEnd() {
//        return isEnd;
//    }
//}
//
///* **************************** */
///* **************************** */
///* **************************** */
///* **************************** */
//
///* **************************** */
///* **************************** */
///* **************************** */
///* **************************** */
//
//
//class Trie_short {
//    public class Node {
//        private boolean isWord;
//        private Node[] child;
//
//        public Node() {
//            isWord = false;
//            child = new Node[26];
//        }
//    }
//
//    Node start;
//    public Trie_short() {
//        start = new Node();
//    }
//
//    public void insert(String word) {
//        int n = word.length();
//
//        Node dummy = start;
//
//        for (int i = 0 ; i < n ; i++) {
//
//            int index = word.charAt(i) - 'a';
//
//            if (dummy.child[index] == null) {
//                dummy.child[index] = new Node();
//            }
//
//            dummy = dummy.child[index];
//        }
//
//        dummy.isWord = true;
//    }
//
//    public boolean search(String word) {
//
//        int n = word.length();
//
//        Node dummy = start;
//        for (int i = 0 ; i < n ; i++) {
//            int index = word.charAt(i) - 'a';
//
//            if (dummy.child[index] == null) return false;
//            dummy = dummy.child[index];
//        }
//
//        return dummy.isWord;
//    }
//
//    public boolean startsWith(String prefix) {
//        int m = prefix.length();
//        Node dummy = start;
//
//        for (int i = 0 ; i < m ; i++) {
//            int index = prefix.charAt(i) - 'a';
//
//            if (dummy.child[index] == null) return false;
//            dummy = dummy.child[index];
//        }
//
//        return true;
//    }
//}
