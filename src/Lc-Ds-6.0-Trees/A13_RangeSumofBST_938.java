import java.util.Stack;
/*problem:
 in BST, return sum of values of all nodes with a value in the inclusive range [low, high] */

/*solutions:
 1st- DFS with global var
 2nd- DFS no global var
 3rd- iterative.*/

//1st - with global vars:
public class A13_RangeSumofBST_938 {
    int ans = 0;
    public int rangeSumBST_with_global_var(TreeNode root, int low, int high) {
        helper ( root,  low,  high);
        return ans;
    }
    private void helper(TreeNode root, int low, int high) {
        if (root == null){
            return;
        }

        if (root.val >= low && root.val <= high){ // why <=, >= -constraint "inclusive range"
            ans += root.val;
        }

        if (root.val >= low)
            helper(root.left, low, high);
        if (root.val <= high){
            helper(root.right, low, high);
        }
//        time: On - but good one! example: lets say 1mil values: low is 0. and high is 1mil.
//        will give O1n. however if got 0-500k will be much more effective!! will take down to half!
//        space: O1n - recursions call stack.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
//2nd - no global vars:
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null){
            return 0;
        }

        int ans = 0;
        if (root.val >= low && root.val <= high){
            ans += root.val;
        }

        if (root.val > low){
            ans += rangeSumBST(root.left, low,high);
        }

        if (root.val < high){
            ans += rangeSumBST(root.right, low,high);
        }
//        time: On - but good one! example: lets say 1mil values: low is 0. and high is 1mil.
//        will give O1n. however if got 0-500k will be much more effective!! will take down to half!
//        space: O1n - recursions call stack.
        return ans;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //    3rd solution - iterative
    public int rangeSumBST_iterative_version(TreeNode root, int low, int high) {

        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();

            if (low <= node.val && node.val <= high) {
                ans += node.val;
            }

            if (node.left != null && low < node.val) {
                stack.push(node.left);
            }

            if (node.right != null && node.val < high) {
                stack.push(node.right);
            }
        }
//        time: O1n
//        space: O1n - for the stack.
        return ans;
    }
}