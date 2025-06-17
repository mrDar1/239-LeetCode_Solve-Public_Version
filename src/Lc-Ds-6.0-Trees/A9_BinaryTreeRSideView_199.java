import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*problem: return only the right-most nodes at each level*/

/*solutions:
* 1st - BFS
* 2nd - PostOrder DFS with global var (more elegant than 4th).
* 3rd - PostOrder DFS no global var         ------ my favorite!
* 4th - exactly like 2nd, changed location of "if". i believe its less elegant so don't use it. */

/*DFS complexity (all 3):
    time: O1n
    space: O1n: recursion will grow up to tree height.
    at worst case, skewed tree (linked list), will be O1n.
    at best case balance tree will grow to 0.5n or more formally O(log n) */

public class A9_BinaryTreeRSideView_199 {
    public List<Integer> rightSideView(TreeNode root) {
//        edge case - empty list
        if (root == null) {
            return new ArrayList<>();
        }

        List <Integer> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            //each time qsize will give the number of nodes in current level - as in BFS - breadth-first search
            //must use copy of qsize - as original will update each iteration.
            int qsize = q.size();

//            at each iteration - we go on each node at current level, then we pop them one by one,
//            and add their children for inspection at the next round.
            for (int i = 0; i < qsize; i++) {
                TreeNode node = q.poll();

                if (i == qsize -1 ){
                    ans.add(node.val);
                }

                if (node.left != null){
                    q.offer(node.left);
                }
                if (node.right != null){
                    q.offer(node.right);
                }
            }
        }
//        time: O1n - each node traverse once.
//        space: On - max size of Q is the size of the last number of nodes. in the worst case - perfect tree, O(n/2)==On.
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A9_BinaryTreeRSideView_199_postorder_DFS_global_better_practice {
    /*intuition:
    * each time the "ans/rightside" size will be equal to the level-number.
    * so the last node at level==rightside.size() */
    List<Integer> rightside = new ArrayList();

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return this.rightside;
        }

        helper(root, 0);
//        time: O1n
//        space:OH - height of tree. (at skewed tree is O1n)
        return this.rightside;
    }

    public void helper(TreeNode node, int level) {
//        base case:
        if (node == null){
            return;
        }

//        if at end of row:
        if (level == this.rightside.size()) {
            this.rightside.add(node.val);
        }

//        explore children. if null - discover and exit at next call.
        helper(node.right, level + 1);
        helper(node.left,  level + 1);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A9_BinaryTreeRSideView_199_postorder_DFS_no_global_my_favorite {
    /* intuition (same as 2nd way):
     * each time the "ans/rightside" size will be equal to the level-number.
     * so the last node at level==rightside.size() */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightside = new ArrayList<>();
        helper(root, 0, rightside);
//        time: O1n
//        space:OH - height of tree. (at skewed tree is O1n)
        return rightside;
    }

    private void helper(TreeNode node, int level, List<Integer> rightside) {
        if (node == null) {
            return;
        }

        if (level == rightside.size()) {
            rightside.add(node.val);
        }

        helper(node.right, level + 1, rightside);
        helper(node.left,  level + 1, rightside);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A9_BinaryTreeRSideView_199_postorder_DFS_global_less_elegant {
    /*intuition:
     * each time the "ans/rightside" size will be equal to the level-number.
     * so the last node at level==rightside.size() */
    List<Integer> rightside = new ArrayList();

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return this.rightside;
        }

        helper(root, 0);
//        time: O1n
//        space:OH - height of tree. (at skewed tree is O1n)
        return this.rightside;
    }

    public void helper(TreeNode node, int level) {
        if (level == this.rightside.size()) {
            this.rightside.add(node.val);
        }

        if (node.right != null) {
            helper(node.right, level + 1);
        }
        if (node.left != null) {
            helper(node.left, level + 1);
        }
    }
}
