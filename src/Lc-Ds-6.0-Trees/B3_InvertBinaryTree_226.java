/*problem: invert tree*/

/*solutions:
* 1st - preorder DFS - less room for error.
* 2nd - same, but post order DFS. since ptrs saved as "left/right" - do not need to use "temp".*/

public class B3_InvertBinaryTree_226 {
    public TreeNode invertTree(TreeNode root) {
//        base case:
        if (root == null) {
            return null;
        }

//        logic:
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

//        explore:
            invertTree(root.left);
            invertTree(root.right);

//        time: O1n
//        space:Oh - h hight of tree (O1n - if skewed tree)
        return root;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B3_InvertBinaryTree_226_postorder {
    public TreeNode invertTree(TreeNode root) {
//        base case:
        if (root == null) {
            return null;
        }

//        explore:
        TreeNode right = invertTree(root.right);
        TreeNode left  = invertTree(root.left);

//        logic:
        root.left  = right;
        root.right = left;
        return root;
    }
}