import java.util.LinkedList;
import java.util.Queue;
/*problem:
* find min lead, when root==1*/

/*4 solutions:
 1-BFS - like it best.
 2-just like 1, check for null at other location.
 3-DFS no global vars, with helper method - elegant way. better use of recursion.
 4-just like 3, check null at other location - bad recursion practice, but good to compare and learn.

 compare 3,4 are minor differences! but 3 is better practice for later harder recursion!!
 as one need to learn practice of min work inside each function - and call to next one. */

public class A6_MinDepthOfBinaryTree_111 {
    public int minDepth_my_BFS(TreeNode root) {
//      edge case:
        if (root == null){
            return 0;
        }

        int depth = 1; //constraint: root==1
        Queue <TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            int levelsize = q.size();

            //why do we need another loop and not just pop here?
            // that a "trick" so first finish current_level and only then advance
            // levelcounter and countinue to next level.
            while (levelsize > 0){
                --levelsize;

                TreeNode cur_node = q.poll();
//               if both null == we are at leaf - return it. if several leaves on same level will stop at the leftmost (but it doesnt matter since they all same level).
                if (cur_node.left == null && cur_node.right == null){
                    return depth;
                }

                if (cur_node.left != null){
                    q.offer(cur_node.left);
                }
                if (cur_node.right != null){
                    q.offer(cur_node.right);
                }
            }
            ++depth;
        }
//        time: O1n, whenever reach ans - stop.
//        space: O0.5n - the max number of nodes at same level
//        (in the worst case of perfect tree - the leaves level will be half of the entire tree)
        return -1;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public int minDepth_BFS_same_as_1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int depth = 1;

        while ( !q.isEmpty()) {
            int qSize = q.size();

            while (qSize > 0) {
                qSize--;

                TreeNode node = q.poll();
                if (node == null) {
                    continue;
                }

                if (node.left == null && node.right == null) {
                    return depth;
                }

                q.add(node.left);
                q.add(node.right);
            }
            depth++;
        }
        return -1;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //    DFS:
    public int minDepth(TreeNode root) {
        return dfs(root);
    }
    //    auxiliary function:
    private int dfs(TreeNode root) {
//        base case:
        if (root == null){
            return 0;
        }
        // If only one child is null, then go to the other:
        if (root.left == null ){
            return 1 + dfs(root.right); //what if "root.right" is null? - will discover and stop at next recursion.
        }
        if (root.right == null){
            return 1 + dfs(root.left);
        }

        // if got here - Both children are non-null, explore both.
        return 1 + Math.min(dfs(root.left), dfs(root.right));
        // time: O1n - when find ans, will not stop continue to traverse all tree.
        // space: O1n: recursion will grow up to tree height.
        // at worst case, skewed tree (linked list), will be O1n.
        // at best case balance tree will grow to 0.5n or more formally O(nlogn)
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //    DFS:
    public int minDepth_bit_different(TreeNode root) {
        return dfs2(root);
    }
    //    auxiliary function:
    private int dfs2(TreeNode root) {
    //        base case:
        if (root == null){
            return 0;
        }

        // If only one child is null, then go to the other:
        if (root.left == null && root.right != null){
            return 1 + dfs2(root.right);
        }
        if (root.right == null && root.left != null){
            return 1 + dfs2(root.left);
        }

        // if got here - Both children are non-null, hence explore both.
        return 1 + Math.min(dfs2(root.left), dfs2(root.right));
    }
}