/*problem:
given root, node p , node q - return the LCA.
LCA== has both p, q as descendants.
Constraints:
1 All Node.val are unique.
2 p and q will exist in the tree - so much easier! */

 /*classic important question!
  solved in 1973, 1984, 1988, 1993 most of the times by jews!! - search in wiki: "Lowest common ancestor"*/

 /*2 solution:
 1st-recursion-no global var - beautiful!!
 2nd-recursion-with global var - didnt like it at all but good practice!
 i dont like it since even if we find LCA at start - will keep traverse tree till end.
 at 1st when find LCA will stop. so 1st faster */

// 1st-recursion-no global var.
public class A5_LCA_LowestCommonAncestor_236 {
    /* solve strategy:
    unlike most recursion in Dynamic-programing, there order of bases does not matter,
    here order very important - mostly for when need to keep "explore" the tree.
    each recursion call return non-null only if found p or q!!!!
    when return null == didn't find p/q. */

    /*psudo:
     * traverse inorder DFS with 3 cases:
     * 1-if root==p/q - it is the LCA cannot be below of that! return root (if keep dig we loose the p/q that
     * we just found).
     *
     * recursive call to dig in for 1 next stage tree level:
     * 2-if p&&q not null == p,q both found in different nodes - then LCA is current node - we return cur.
     * 3-if reach here - it means we didn't find any of them - keep digging - LCA must be inside!
     *  first dig all left that not null, then dig all right that no null.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        edge case - empty root:
        if (root == null) {
            return null;
        }

        // 1st case - given one of them is the parent of the other - it is the LCA:
        if (root == p || root == q) {
            return root;
        }

//        traverse 1 more level depth in the tree:
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 2nd case - if both not null together, it means that both found on different subtree's and root is the LCA
        if (left != null && right != null) {
            return root;
        }

        //3rd case - if reach here - it means we didn't find any of them - keep digging - LCA must be inside!
        return (left != null) ? left : right;
//        time & space: O1n
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A5_LCA_LowestCommonAncestor_236_recursion_global_var {
    private TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.recurseTree(root, p, q);
        return this.ans;
    }
    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
        // base case: reach leaf, return false.
        if (currentNode == null) {
            return false;
        }

        //1st if cur is p/q:
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;

        // Left Recursion.
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        //2nd - if both found - update that currentNode is the LCA since can be 2 reasons:
        //1st - maybe p/q is curr and the other is at child subtree
        //2nd - maybe p and q both child subtree at different branches -the LCA cant be below here!
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        //3rd: back up and keep lookin!
        return (mid + left + right > 0);
    }
}