/*problem:
given BST: root, node p , node q - return the LCA.
LCA == has both p, q as descendants.
Constraints:
1 All Node.val are unique.
2 p and q will exist in the tree.
3 p != q */

/* motivation:
both recursion use properties of BST - if its not bigger and not smaller - then it got to be equal!!
remember: got constraint that val must found at tree */

/*solutions:
1st - postorder DFS with global.
2nd - postorder DFS no globals.      ---  read comment nice insight at difference 1st,2nd solutions here!
3rd - iterative same logic as in DFS.
4th - same as 3rd - but don't use "continue" use "else" - for practice.

/* **************************** */
/* **************************** */
/* **************************** */
/* **************************** */

//1st DFS with global vars.
public class B15_LowestCommonAncestorofBST_235 {
    /*psudo:
     * if both vals bigger than  curr -> go right
     * else if both vals smaller than curr -> go left.
     * else - meaning one big and one small from cur - THAT LCA!! cannot be lower than that! */

    TreeNode lca;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        DFS(root, p, q);
//    time : O1h. in average O log n - as BST, at worst case O1n
//    space: O1n - recursion calls. at worst case skewed tree == O1n.
        return this.lca;
    }

    private void DFS(TreeNode root, TreeNode p, TreeNode q) {
//        base case:
        if (root == null) {
            return;
        }
//        if both p,q  bigger than cur - traverse right.
//        if both p,q smaller than cur - traverse left.
//        else (use properties of BST) - if p,q not bigger and not smaller - then it got to be equal!! (got constraint that p,q must found at tree).
        if (p.val > root.val && q.val > root.val) {
            DFS(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            DFS(root.left, p, q);
        } else { // note: must be inside "else"!! try to remove "else" and fail! why? since at that case, when finish explore tree just update "lca" with cur val.
            this.lca = root;
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd DFS - no globals
// like 1st, but less prone to error ans slightly faster see comment!!!

/* how come at 2nd solution we can delete "else" but not at first?
- at 1st solutions - we traverse all tree. even after find LCA.
- at 2nd solutions - when find LCA - immediately return!
so, since at 1st, we keep traverse tree, lca global var - will update with unwanted values, as traversing continue*/
        class B15_LowestCommonAncestorofBST_235_no_global {
            public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        base case:
                if (root == null) {
                    return null;
                }

                if (p.val > root.val && q.val > root.val) {
                    return lowestCommonAncestor(root.right, p, q);

                } else if (p.val < root.val && q.val < root.val) {
                    return lowestCommonAncestor(root.left, p, q);

                } else { //can delete "else" and still work, how? - see comment above.
                    return root;
                }
            }
        }

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B15_LowestCommonAncestorofBST_235_ierative {
//    time : O1h. in average O log n - as BST, at worst case O1n
//    space: O1.
    public TreeNode lowestCommonAncestor_iterative_1(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
//            if cur smaller - go right
            if (p.val > root.val && q.val > root.val) {
                root = root.right;
                continue;
            }

//            if cur bigger - go left
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
                continue;
            }

            //if its not bigger and not smaller - its got to be the LCA.
            //since use "continue" - reach here only at right time, unlike 1st solution.
            return root;
        }
//        if reach here didnt find lca (constraint - must found so won't reach here)
        return null;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public TreeNode B15_LowestCommonAncestorofBST_235_ierative2(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else { //here must use that else! dont have "continue" so if dont use "else" will return not at wanted time.
                return root;
            }
        }
        return null;
    }
}
