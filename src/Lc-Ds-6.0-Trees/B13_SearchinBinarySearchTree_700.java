/*problem:
return subTree that "val" is the root
if cannot find "val" - return null. */

//solutions:
//1st - preorder-DFS with global vars.
//2nd - preorder-DFS no global vars.
//3rd - like 2nd but much elegant!!!
//4rd - like 3rd but iterative.

/*complexity for 3 first DFS solutions:
        time: O1n: DFS traverse each node once.
        space:Oh - 1h-for recursion call stack - the deepest recursion == tree lvl.
        h==height of tree:
        at worst case, degenerate tree == O1n.
        at best case, balance tree == O log n. */

public class B13_SearchinBinarySearchTree_700 {
    TreeNode ans = null;

    public TreeNode searchBST(TreeNode root, int val) {
        helper(root, val);
        return ans;
    }

    public void helper(TreeNode root, int val) {
//        base case:
        if (root == null)
            return;

//        logic, when find - can immediately stop and return:
        if (root.val == val) {
            this.ans = root;
            return;
        }

//        explore children:
        if (root.val > val) {
            helper(root.left, val);
        } else {
            helper(root.right, val);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B13_SearchinBinarySearchTree_700_DFS_no_global {
    public TreeNode searchBST(TreeNode root, int val) {
        return helper(root, val);
    }

    public TreeNode helper(TreeNode root, int val) {
//        base case:
        if (root == null)
            return null;

//        logic, when find - can immediately stop and return:
        if (root.val == val) {
            return root;
        }

//        explore children:
        if (root.val > val) {
            return helper(root.left, val);
        } else {
            return helper(root.right, val);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B13_SearchinBinarySearchTree_700_DFS_no_global_improve {
    public TreeNode searchBST(TreeNode root, int val) {
//        base case && when find result!!
        if (root == null || root.val == val) {
            return root;
        }

//        explore children:
        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B13_SearchinBinarySearchTree_700_Iterative {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && val != root.val){
            root = val < root.val ? root.left : root.right;
        }

//        time: Oh - use BST property!
//        h==height of tree:
//        at worst case, degenerate tree == O1n.
//        at best case, balance tree == O log n.
//        space: O1 - since it's a constant space solution.
        return root;
    }
}