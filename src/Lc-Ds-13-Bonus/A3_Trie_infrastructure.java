import java.util.HashMap;
import java.util.Map;

/* framework to build Tries (many other ways). */

// note: using a class is only necessary if you want to store data at each node.
// otherwise, you can implement a trie using only hash maps.
class TrieNode_1 {
    // you can store data at nodes if you wish
    int data;
    Map<Character, TrieNode_1> children;
    TrieNode_1() {
        this.children = new HashMap<>();
    }
}

public class A3_Trie_infrastructure {
    public TrieNode_1 buildTrie(String[] words) {
        TrieNode_1 root = new TrieNode_1();
        for (String word: words) {
            TrieNode_1 curr = root;
            for (char c: word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode_1());
                }

                curr = curr.children.get(c);
            }

            // at this point, you have a full word at curr
            // you can perform more logic here to give curr an attribute if you want
        }

        return root;
    }
}




