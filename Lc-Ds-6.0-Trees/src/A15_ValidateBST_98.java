import java.util.Stack;
/*problem: return true if its valid BST.
note - got to be long or it wont pass testing!! */

/*solutions:
* 1st-postorder DFS, no global vars
* 2nd-iterative way
* note: there are many different other ways i most-like those. */

public class A15_ValidateBST_98 {
    /*psudo postorder DFS:
    * 1st - base case - null.
    * 2nd - base case - do not require BST properties return false.
    * 3rd - given BST required confirmed - to next recursion call the boundary of Max/Low - will be curnode.val */

    public boolean isValidBST(TreeNode root) {
        return dfs(root,Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long minValue, long maxValue) {
//       base case:
        if (root==null){
            return true;
        }

        if (minValue >= root.val || maxValue <= root.val){
            return false;
        }

        boolean left  = dfs(root.left,  minValue , root.val);
        boolean right = dfs(root.right, root.val , maxValue);

//        time:  O1n
//        space: O1n
        return (left && right);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    iterative way got same complexity - but much harder to write.
    public boolean isValidBST_iterativeway(TreeNode root) {
//    Create a stack to store the state of nodes
        Stack<State> stack = new Stack<>();
        stack.push(new State(root, Long.MIN_VALUE, Long.MAX_VALUE));

        while (!stack.empty()) {
            State state = stack.pop();
            TreeNode node = state.node;
            long small = state.small;
            long large = state.large;

//            Check if the current node violates the BST property
            if (small >= node.val || node.val >= large) {
                return false;
            }

//        Push the left child with updated constraints
            if (node.left != null) {
                stack.push(new State(node.left, small, node.val));
            }

//            Push the right child with updated constraints
            if (node.right != null) {
                stack.push(new State(node.right, node.val, large));
            }
        }
//      If the entire tree is traversed without violation, return true
        return true;
    }
    class State {
        TreeNode node;
        long small;
        long large;
        State(TreeNode node, long small, long large) {
            this.node = node;
            this.small = small;
            this.large = large;
        }
    }
}