/*problem:
 * return the longest path, between any 2 node
 * not has to be use root!
 * longest path==represented by the number of edges between nodes*/

/*strategy:
 * 1-longest path must be between 2 leaves! prove by contradiction:
 * if not at leaf, can extend length by at least 1!!
 * 2-longest path must be on different brunches! */

/*solutions:
 postorder recursion, use global var.*/

public class A8_DiamatarOfBinaryTree_543 {
    private int diameter; //longest path so far

    public int diameterOfBinaryTree(TreeNode root) {
        this.diameter = 0;
        longestPath(root);
        return diameter;
    }

    private int longestPath(TreeNode node){
        if(node == null) {
            return 0;
        }

        int leftPath  = longestPath(node.left);
        int rightPath = longestPath(node.right);

        diameter = Math.max(diameter, leftPath + rightPath);

//        why here its "," when before was "+" - since we want different branches! if use "+" here, in skewed tree will get twice the height!!
        return Math.max(leftPath, rightPath) + 1; // +1 for cur edge.
//    time: O1n
//    space: O1n: recursion will grow up to tree height.
//    at worst case, skewed tree (linked list), will be O1n.
//    at best case balance tree will grow to 0.5n or more formally O(log n)
    }
}