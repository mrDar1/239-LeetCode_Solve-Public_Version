import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
/*problem:
* again rob as much as possible and return the sum of all robbery.
* constraint: house form a tree, cannot rob 2 neighbors nodes. */

/*solutions:
 * 0-simple recursion.
 * 1st-top-down (same as before+memo)
 * 2nd-recursion - but much(!!) faster than all! due postorder the recursion "memoize" and no need for allocate "memo".
 * 3rd-bottom up */


//shared Treenode:
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
 * each time has 2 options:
 *   1 - parent rob - so can only explore children.
 *   2 - parent not rob - so can:
 *           1 - not rob cur and enjoy next time value.
 *           2 - do rob cur and explore next
 *           explore both and return max.  */
class B9_HouseRobber3_337_recursion_inefficient {
    public int rob(TreeNode root) {
//        edge case:
        if (root == null) {
            return 0;
        }
        return DFS(root, false);
    }

    private int DFS(TreeNode node, boolean parentRobbed) {
//        base case - reach null - cannot rob more than 0.
        if (node == null) {
            return 0;
        }

        //when parent robbed - cannot rob cur.
        if (parentRobbed) {
            return DFS(node.left, false) + DFS(node.right, false);
        }

        //parent not robbed - can rob cur - or may rob next:
        else {
            int robCur = node.val + DFS(node.left, true)  + DFS(node.right, true);
            int skipCur =           DFS(node.left, false) + DFS(node.right, false);
            return Math.max(robCur, skipCur);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B9_HouseRobber3_337_top_down {
    HashMap<TreeNode, Integer> robResult = new HashMap<>();
    HashMap<TreeNode, Integer> notRobResult = new HashMap<>();

    public int rob(TreeNode root) {
//        time : O1n - traverse each exactly once.
//        space: O3n : 1n- for recursion call stack. at worst case depth of recursion==n, in best case its log n.
//                     2n- 2 hashmaps of rob and not rob results.
        return DFS(root, false);
    }

    public int DFS(TreeNode curNode, boolean isParentRobbed) {
//        base case - when at null node - sum of theft == 0.
        if (curNode == null) {
            return 0;
        }

        //when parent robbed - cannot rob cur.
        if (isParentRobbed) {
//            already cached that val:
            if (robResult.containsKey(curNode)) {
                return robResult.get(curNode);
            }

            int result = DFS(curNode.left, false) + DFS(curNode.right, false);
            robResult.put(curNode, result);
            return result;
        }

        //(parent not robbed - can rob cur)
        else {
//            already cached that val:
            if (notRobResult.containsKey(curNode)) {
                return notRobResult.get(curNode);
            }

            int rob = curNode.val + DFS(curNode.left, true)  + DFS(curNode.right, true);
            int notRob =            DFS(curNode.left, false) + DFS(curNode.right, false);
            int result = Math.max(rob, notRob);

            notRobResult.put(curNode, result);
            return result;
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//optimize recursion - best!
public class B9_HouseRobber3_337 {
    public int rob(TreeNode root) {
        int[] answer = DFS(root); //sum of robbery-when start to rob from i index.     <do rob cur, do not rob cur>

//        time : O1n - traverse each exactly once.
//        space: O1n - for recursion call stack. at worst case depth of recursion==n, in best case its log n.
        return Math.max(answer[0], answer[1]); // return [rob this node, not rob this node]
    }

    public int[] DFS(TreeNode node) {

//        base case - when at null node - the sum of theft is 0,0.
        if (node == null) {
            return new int[] { 0, 0 };
        }

//        postorder DFS:
        int[] left  = DFS(node.left);
        int[] right = DFS(node.right);

        // if we rob this node, we cannot rob its children
        int robCur = node.val + left[1] + right[1];
        // else, we free to choose rob its children or not
        int notRobCur = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] { robCur, notRobCur };
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution {
    public int rob(TreeNode root) {
//        egde case:
        if (root == null) {
            return 0;
        }

        // reform tree into array-based tree
        ArrayList<Integer> tree = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        graph.put(-1, new ArrayList<>());
        int index = -1;
        // we use two Queue to store node and index
        Queue<TreeNode> q_node = new LinkedList<>();
        q_node.add(root);
        Queue<Integer> q_index = new LinkedList<>();
        q_index.add(index);

        while (q_node.size() > 0) {
            TreeNode node = q_node.poll();
            int parentIndex = q_index.poll();
            if (node != null) {
                index++;
                tree.add(node.val);
                graph.put(index, new ArrayList<>());
                graph.get(parentIndex).add(index);
                // push new node into Queue
                q_node.add(node.left);
                q_index.add(index);
                q_node.add(node.right);
                q_index.add(index);
            }
        }

        // represent the maximum start by node i with robbing i
        int[] dpRob = new int[index + 1];

        // represent the maximum start by node i without robbing i
        int[] dpNotRob = new int[index + 1];

        for (int i = index; i >= 0; i--) {
            ArrayList<Integer> children = graph.get(i);
            if (children == null || children.size() == 0) {
                // if is leaf
                dpRob[i] = tree.get(i);
                dpNotRob[i] = 0;
            } else {
                dpRob[i] = tree.get(i);
                for (int child : children) {
                    dpRob[i] += dpNotRob[child];
                    dpNotRob[i] += Math.max(dpRob[child], dpNotRob[child]);
                }
            }
        }

        return Math.max(dpRob[0], dpNotRob[0]);
    }
}