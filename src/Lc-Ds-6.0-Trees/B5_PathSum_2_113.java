import java.util.ArrayList;
import java.util.List;
/*problem:
return all path's that sum-up to "target".
all path's should be as List<List>>.
constraint: path must reach leaf! cannot stop at middle of tree!*/

/*solutions:
* 1st - DFS - no global vars */

public class B5_PathSum_2_113 {
    public static void main(String[] args) {
        // Example usage:
        Solution_B5_PathSum_2_113 obj_113 = new Solution_B5_PathSum_2_113();

        // Example 1:
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.left = new TreeNode(5);
        root1.right.right.right = new TreeNode(1);
        int targetSum1 = 22;
        System.out.println(obj_113.pathSum(root1, targetSum1)); // Output: [[5, 4, 11, 2], [5, 8, 4, 5]]

        // Example 2:
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        int targetSum2 = 5;
        System.out.println(obj_113.pathSum(root2, targetSum2)); // Output: []

        // Example 3:
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        int targetSum3 = 0;
        System.out.println(obj_113.pathSum(root3, targetSum3)); // Output: []
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*psudo:
* 1 create ans list.
* 2 DFS till leaf, and as in it - save path of nodes.
* if got to leaf && target sum - add path to ans.
*
* on any case - backtrack each time 1 node and check if there are other paths. */
class Solution_B5_PathSum_2_113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) { //START HERE
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> temp_path = new ArrayList<Integer>();//inner list to store curr path of all nodes. if get to targetsum add to ans. else discard.

        recurseTree(root, sum, temp_path, ans);

//        time: On^2 - traverse each node only once - but if not get to target then backtrak to prev node and go again.
//        in worst case - perfect tree - we have n/2 leafs - for each - pefrorm potential O1n operations of copy to temp_path
//        space: same.
        return ans;
    }

    private void recurseTree(TreeNode root,
                             int remain_sum,
                             List<Integer> temp_path,
                             List<List<Integer>> ans) {

//        base case:
        if (root == null) {
            return;
        }

        temp_path.add(root.val);

        if (root.left  == null &&
            root.right == null &&
            remain_sum == root.val ) {//if we're leaf && target - add innerList temp_path, to ans List<List>>.

            ans.add(new ArrayList<>(temp_path) );
        }else {
//            if not at leaf, keep explore (also keep explore if not at valid sum-but if it is leaf - prune branch next recursion call):
            recurseTree(root.left,  remain_sum - root.val, temp_path, ans);
            recurseTree(root.right, remain_sum - root.val, temp_path, ans);
        }

        // on any case - backtrack: remove last node from current path and try again with other children nodes. check if are other paths.
        temp_path.remove(temp_path.size() - 1);
    }
}