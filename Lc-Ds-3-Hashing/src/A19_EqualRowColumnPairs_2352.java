import java.util.*;
/*problem:
* given grid, return how many equal row and column we got
* note: we need to sum all rows, sum all column - then multiply row_sum * col_sum !!!
* note: perfect square!! so grid.len==grid[0].len!!! */

/*solutions:
* 1-hashmap
* 2-Trie*/

public class A19_EqualRowColumnPairs_2352 {
    public static void main(String[] args) {
        A19_EqualRowColumnPairs_2352_hashmap obj_2352 = new A19_EqualRowColumnPairs_2352_hashmap();
        // Test case 1
        int[][] grid1 = {
                {3, 2, 1},
                {1, 7, 6},
                {2, 7, 7}
        };
        int result1 = obj_2352.equalPairs(grid1);
        System.out.println("Test case 1: " + result1); // Expected output: 1

        // Test case 2
        int[][] grid2 = {
                {3, 1, 2, 2},
                {1, 4, 4, 5},
                {2, 4, 2, 2},
                {2, 4, 2, 2}
        };
        int result2 = obj_2352.equalPairs(grid2);
        System.out.println("Test case 2: " + result2); // Expected output: 3
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
 * use 2 hashmap for rows and column: <row/colu , freq>
 * problem: keys must be imutable! so we convert them to string so can be keys!!
 * then we multiply them and return ans.*/
class A19_EqualRowColumnPairs_2352_hashmap {
    public int equalPairs(int[][] grid) {
        Map<String, Integer> dic = new HashMap<>();  //<copy row    in string format, number of occurrences>
        Map<String, Integer> dic2 = new HashMap<>(); //<copy column in string format, number of occurrences>

//        traverse rows and save them in hashmap.
        for (int[] row: grid) {
            String key = convertToKey(row); //take mutuable [] and change it to imutable String - so can be key.
            dic.put(key, dic.getOrDefault(key, 0) + 1);
        }

//        traverse column and save them in hashmap.
        for (int col = 0; col < grid[0].length; col++) {
            int[] currentCol = new int[grid.length];

            for (int row = 0; row < grid.length; row++) {
                currentCol[row] = grid[row][col];
            }

            String key = convertToKey(currentCol);
            dic2.put(key, dic2.getOrDefault(key, 0) + 1);
        }

        int ans = 0;
        for (String key : dic.keySet()) {
            ans += dic.get(key) * dic2.getOrDefault(key, 0);
        }

//        size of grid: n * n (constraint: grid.len==grid[0].len)
//        time:  O2 n^2 -  1st-On^2 - build both hashmap. 2nd-On^2-multiplying the freq
//        space: O2 n^2 - if all rows and colums unique - will grow to size of grid.
        return ans;
    }

    //take mutuable int[] and change it to imutable String - so can be key.
    public static String convertToKey(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(",");
        }

        return sb.toString();
    }
}


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class A19_EqualRowColumnPairs_2352_trie {
    public int equalPairs(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        Trie myTrie = new Trie();

        for (int[] row : grid) {
            myTrie.insert(row);
        }

        for (int c = 0; c < n; ++c) {
            int[] colArray = new int[n];
            for (int r = 0; r < n; ++r) {
                colArray[r] = grid[r][c];
            }
            ans += myTrie.search(colArray);
        }
//        time & space: O2 n^2 - first n^2-for create trie, second On^2 - search in trie
        return ans;
    }
}

class TrieNode {
    int count;     // count of how many times a specific path ends at this node
    Map<Integer, TrieNode> children;  //store child nodes, where key is an integer and value is the TrieNode corresponding to that integer

    public TrieNode() {
        this.count = 0;
        this.children = new HashMap<>();
    }
}

class Trie {
    TrieNode trie;

    public Trie() {
        this.trie = new TrieNode();
    }

    public void insert(int[] array) {
        TrieNode myTrie = this.trie; // start from root node

        for (int a : array) {
//            if curr node do not have child for int 'a', create a new TrieNode for 'a'
            if (!myTrie.children.containsKey(a)) {
                myTrie.children.put(a, new TrieNode());
            }
//            move to the child node corresponding to 'a'
            myTrie = myTrie.children.get(a);
        }
        myTrie.count += 1;
    }

// returns the count of how many times this path (array of int in the trie) has been inserted
    public int search(int[] array) {
        TrieNode myTrie = this.trie;
        for (int a : array) {
            if (myTrie.children.containsKey(a)) {
                myTrie = myTrie.children.get(a);
            } else {
                return 0;
            }
        }
        return myTrie.count;
    }
}

