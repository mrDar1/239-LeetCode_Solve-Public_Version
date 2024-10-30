import java.util.Stack;
/*problem:
* given 2 binaryTrees - return true if they are the same */

/*solutions:
* 1st-postorder recursion, no global vars.
* 2nd-iterative with Pair class*/
public class A4_Same_Tree_100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
//        base case - if both null, its leaf return true:
        if (p == null && q == null) {
            return true;
        }

//        if only one of them null and the other does not - cannot be same tree:
        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        boolean left  = isSameTree(p.left,  q.left);
        boolean right = isSameTree(p.right, q.right);
//        time: O1n
//        space: O1n
        return left && right;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    //    iterative way:
    class Pair {
        TreeNode p;
        TreeNode q;
        Pair(TreeNode p, TreeNode q) {
            this.p = p;
            this.q = q;
        }
    }
    public boolean isSameTree_iterative_way(TreeNode p, TreeNode q) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(p, q));

        while (!stack.empty()) {
            Pair pair = stack.pop();
            TreeNode node1 = pair.p;
            TreeNode node2 = pair.q;

            if (node1 == null && node2 == null) { //leaf node
                continue;
            }

            if (node1 == null || node2 == null) {
                return false;
            }

            if (node1.val != node2.val) {
                return false;
            }

            stack.push(new Pair(node1.left, node2.left));
            stack.push(new Pair(node1.right, node2.right));
        }
//        time: O1n
//        space: O1n
        return true;
    }
}