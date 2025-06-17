import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
/*problem:
* for each "query" - return int[2] =
* {target, target}
* {<closest element from down> , <closest element from above>}
* if there isnt element above/under it in tree - put -1.*/

/*solutions:
3rd - work directly on tree - its fail, take too long!
1st - convert Tree into a List
2nd - same as 1st, but with TreeSet (for practice)  */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
* convert Tree into sortedList - use BST property of remain sort by inorder DFS.
* binary search sortedList for each "query" */
public class B3_ClosestNodesQueriesinBinarySearchTree_2476 {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> sortedValues = new ArrayList<>(); //list to store Tree in sorted manner.
        inOrder(root, sortedValues); //populate List in sorted manner.

        List<List<Integer>> ans = new ArrayList<>();
        for (int query : queries) {
            int[] closest = findClosest(sortedValues, query);
            ans.add(Arrays.asList(closest[0], closest[1]));
        }
//        k==number of queries.
//        time: O1n*k*log n: O1n build sortedList, Olog n - for each query * times of query.
//        space: O1n - sortedList
        return ans;
    }

    private void inOrder(TreeNode root, List<Integer> sortedValues) {
        if (root == null) {
            return;
        }
        inOrder(root.left, sortedValues);
        sortedValues.add(root.val);
        inOrder(root.right, sortedValues);
    }

    private int[] findClosest(List<Integer> sortedValues, int target) {
        int left = 0;
        int right = sortedValues.size() - 1;
        int closestSmaller = -1;
        int closestGreater = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2; //mid represent the index
            int midVal = sortedValues.get(mid);  //represent the value.


            if (midVal == target) { //found can return.
                return new int[]{target, target};

            } else if (midVal < target) {
                closestSmaller = midVal;
                left = mid + 1;
            } else {
                closestGreater = midVal;
                right = mid - 1;
            }
        }

        return new int[]{closestSmaller, closestGreater};
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B3_ClosestNodesQueriesinBinarySearchTree_2476_use_treeSet {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>(queries.size());
        TreeSet<Integer> set = new TreeSet<>();
        // populate set with inorder DFS - keep the order.
        inOrder(root,set);

        for(int i=0;i<queries.size();i++){
            ans.add(new ArrayList<>());
            int largest = -1;

            if(set.floor(queries.get(i))!=null){
                largest = set.floor(queries.get(i));
            }

            int smallest = -1;
            if(set.ceiling(queries.get(i))!=null){
                smallest = set.ceiling(queries.get(i));
            }

            ans.get(ans.size()-1).add(largest);
            ans.get(ans.size()-1).add(smallest);
        }
        return ans;

    }

    private void inOrder(TreeNode root, TreeSet set){
        if(root==null){
            return;
        }
        inOrder(root.left, set);
        set.add(root.val);
        inOrder(root.right, set);
        return;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B3_ClosestNodesQueriesinBinarySearchTree_2476_fail_TLE {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> result = new ArrayList<>();

        for (int query : queries) {
            int[] closest = findClosest(root, query);
            result.add(Arrays.asList(closest[0], closest[1]));
        }

        return result;
    }

    private int[] findClosest(TreeNode root, int target) {
        int closestSmaller = -1;
        int closestGreater = -1;

        while (root != null) {
            if (root.val == target) {
                closestSmaller = root.val;
                closestGreater = root.val;
                break;
            } else if (root.val < target) {
                closestSmaller = root.val;
                root = root.right;
            } else {
                closestGreater = root.val;
                root = root.left;
            }
        }

        return new int[]{closestSmaller, closestGreater};
    }
}
