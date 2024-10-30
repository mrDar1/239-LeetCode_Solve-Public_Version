import java.util.*;
/*problem:
* we need to implement auto compliance words.
* user type word, for each char he type we return a list of up to 3 values with words that are possible auto-complete.
* if got more than 3 option - return first 3 lexicographic.
*
* input:
* products[] - our dictionary words (all optional words to auto-complete)
* searchWord - what user "type". note: need to return up to 3 options for each char at "searchWord"!! */

/*note about given input "products[]":
input "products" String could have come as 1 big string: "word1 word2 word3 word4"
here problem want to easy on us so use String[]: ""word1" "word2" "word3" "word4"" - more
comfortable to reach every individual word.
"products[i]": choose the "i" word from products String (at here example if i==2, then: products[i]==word3  ) */

/*solutions:
1st - BinarySearch
2nd - BinarySearch optimized (each time the left boundary start from last answer location, small yet confusing optimize)
3rd - Trie + sort "suggestions" - concise code - like it most.
4th - Trie + preorder DFS - to receive first 3 options - long code... */


/*motivation - for binary search:
* sort "products[]", each time binary search the "searchWord",
* if have duplicates - return the left-most, since its ordered - simply return the next 3 words after it */
class A3_SearchSuggestionsSystem_1268_binarySearch_less_efficient {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        int n = products.length;
        String prefix = new String();  //constraint: for each "searchWord"-return up to 3 options. prefix represent user-new-typed-char start with 1 char,2,3,4....

        Arrays.sort(products);

        for (char c : searchWord.toCharArray()) {
            prefix += c;

            int start = myBinarySearch(products, 0, prefix);

            ans.add(new ArrayList<>());
            //why need to allocate space here and not right before add to ans?
            //-constraint: each time all 3 ans options would be at 1 shared string.


            for (int i = start; i < Math.min(start + 3, n); i++) {
                if (     products[i].length() < prefix.length() ||
                        !products[i].substring(0, prefix.length()).equals(prefix)) {
                    //check if user-new-typed-char(=prefix), matches optional auto-complete word (=products[i]) - if not - break. as have nothing to suggest user.
                    break;
                }

                ans.get(ans.size() - 1).add(products[i]);
            }
        }
        return ans;
    }

    //    framework of BS to search in duplicate elements input - the leftmost.
    int myBinarySearch(String[] products, int start, String word) {
        int left = start;
        int right = products.length; //== length of cur word. not entire sentence.

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (products[mid].compareTo(word) >= 0) { //when would "compareTo" be smaller than 0? - if "products[mid]" is lexicographically less than "word".
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
/* complexity of binary search:
n==product[].len
m==searchWord.len
time: On log n + m log n + m^2 :
                          n log n - sort products[].
                          log n * m   - binary search each time at sorted products[], done for each char at searchWord (=m).
                          m^2 - in java string immutable - each time doing "prefix += c" need to copy all from start.
space:
       O log n - for java variant of the Quick Sort algorithm.
       On - for python, TimSort is a hybrid of MergeSort and InsertionSort */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A3_SearchSuggestionsSystem_1268_binarySearch_more_efficient {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        int n = products.length;
        int start = 0;
        int bsStart = 0; //optimization -  each time start search from last time location
        String prefix = new String();

        Arrays.sort(products);

        for (char c : searchWord.toCharArray()) {
            prefix += c;

            start = myBinarySearch(products, bsStart, prefix);

            ans.add(new ArrayList<>());

            for (int i = start; i < Math.min(start + 3, n); i++) {

                if (    products[i].length() < prefix.length() ||
                        !products[i].substring(0, prefix.length()).equals(prefix)) {
                    break;
                }
                ans.get(ans.size() - 1).add(products[i]);
            }

            bsStart = Math.abs(start);
        }
        return ans;
    }

    //    framework of BS to search in duplicate elements input - the leftmost.
    int myBinarySearch(String[] products, int start, String word) {
        int left = start;
        int right = products.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (products[mid].compareTo(word) >= 0)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
/* complexity of binary search:
n==product[].len
m==searchWord.len
time: On log n + m! log n + m^2 :
                          n log n - sort products[].
                          log n * m!   - binary search each time at sorted products[], done for each char at searchWord (=m), but each time start search from last location so m keep reducing = m!.
                          m^2 - in java string immutable - each time doing "prefix += c" need to copy all from start.
space:
       O log n - for java variant of the Quick Sort algorithm.
       On - for python, TimSort is a hybrid of MergeSort and InsertionSort */

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd Trie - first build try, only then start answer problem.
class TrieNode {
    // you can store data at nodes if you wish
    Map<Character, TrieNode> children;
    List<String> suggestions;

    TrieNode() {
        this.children = new HashMap<>();
        this.suggestions = new ArrayList<>();
    }
}

public class A4_SearchSuggestionsSystem_1268 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
//        build Trie:
        TrieNode root = new TrieNode(); //Trie start with empty String: "".

        for (String product : products) {//traverse single word in String
            TrieNode dummy = root; //for each word, start with new empty root.

            for (char c : product.toCharArray()) { //traverse each char in word. if we have char sign to it - create another value to that key.
                if ( !dummy.children.containsKey(c)) {
                    dummy.children.put(c, new TrieNode());
                }

                dummy = dummy.children.get(c); //advance dummy to next "child" node.
                dummy.suggestions.add(product);//add curr word as option.

                //keep valid window size (here is size of 3 of the lexicographic smallest)
                Collections.sort(dummy.suggestions);
                if (dummy.suggestions.size() > 3) {
                    dummy.suggestions.remove(dummy.suggestions.size() - 1);
                }
            }
        }

//        start to answer question:
        List<List<String>> ans = new ArrayList<>();
        TrieNode dummy = root; //start from the Trie root.

        for (char c : searchWord.toCharArray()) {
            if (dummy.children.containsKey(c)) {
                dummy = dummy.children.get(c);
                ans.add(dummy.suggestions);
            } else {
                // deadend reached - return empty "ArrayList", as no valid suggestions.
                dummy.children = new HashMap<>();
                ans.add(new ArrayList<String>());
            }
        }
//        n==product.len
//        k==product[0].len - avg.
//        m==searchWord.len
//        time: O(nk + m): nk-build trie, m-iterate trie and return ans. (+ O1==4 log 4 - to sort up to 4 "suggestions")
//        space: ??? Onk ???
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//4th - Trie + preorder DFS - to receive first 3 options.
class A3_SearchSuggestionsSystem_1268_2_Trie {
    List<List<String>> suggestedProducts(String[] products, String searchWord) {

        List<List<String>> ans = new ArrayList<>();
        String prefix = new String(); //constraint: for each "searchWord"-return up to 3 suggestions. prefix represent user-new-typed-char start with 1 char,2,3,4....
        Trie trie = new Trie();

        // build Trie - add all words.
        for (String w : products) {
            trie.insert(w);
        }

        for (char c : searchWord.toCharArray()) {
            prefix += c;
            ans.add(trie.getWordsStartingWith(prefix));
        }
        return ans;
    }
}

// Trie with tailor made function to get 3 suggestions (=3 words starting with given prefix)
class Trie {

    class Node {
        boolean isWord = false; //flag each time at end of word.
        List<Node> children = Arrays.asList(new Node[26]); // can only be english char.
    }
    Node Root;
    Node cur;
    List<String> suggestions;
    Trie() {
        Root = new Node();
    }

    List<String> getWordsStartingWith(String prefix) {
        cur = Root;
        suggestions = new ArrayList<String>();

        // Move curr to the end of prefix in its trie representation.
        for (char c : prefix.toCharArray()) {
            if (cur.children.get(c - 'a') == null) { //if char is not at children node - ignore cur by return last suggestions.    why need c-'a'? -since children are at arr this way convert them to kind of bucket sort 0-25 equal to english letters.
                return suggestions;
            }
            cur = cur.children.get(c - 'a');
        }
        dfsWithPrefix(cur, prefix);
        return suggestions;
    }

    // DFS on trie with given prefix adds suggestions, limited to 3
    void dfsWithPrefix(Node cur, String word) {
        if (suggestions.size() == 3) {
            return;
        }

        if (cur.isWord) {
            suggestions.add(word);
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (cur.children.get(c - 'a') != null) {
                dfsWithPrefix(cur.children.get(c - 'a'), word + c);
            }
        }
    }

    // Inserts the string in trie.
    void insert(String s) {
        cur = Root;
        for (char c : s.toCharArray()) {
            if (cur.children.get(c - 'a') == null) {
                cur.children.set(c - 'a', new Node());
            }
            cur = cur.children.get(c - 'a');
        }
        cur.isWord = true;
    }
}