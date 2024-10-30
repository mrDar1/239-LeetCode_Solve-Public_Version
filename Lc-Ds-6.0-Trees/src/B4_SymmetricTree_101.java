import java.util.LinkedList;
import java.util.Queue;

/* problem:
return true if mirror to each itself */

/*solutions:
* 1st - DFS - with globals
* 2nd - DFS - no globals
* 2nd - BFS*/

public class B4_SymmetricTree_101 {
    boolean issymetricflag = true;

    public boolean isSymmetric_with_globals(TreeNode root) {
        DFS_global(root,root);
        return (issymetricflag);
    }

    public void DFS_global(TreeNode t1 , TreeNode t2) {
//        base case:
        if (t1 == null && t2 == null){
            return;
        }

//        first logic:
//        return now - not "symmetric tree".
        if (t1 == null || t2 == null){
            this.issymetricflag = false;
            return;
        }
//        second logic (can combine both into one big "if" but like that is cool lookin):
//        return now - not "symmetric tree".
        if (t1.val != t2.val){
            this.issymetricflag = false;
            return;
        }

//        explore:
        DFS_global(t1.left, t2.right);
        DFS_global(t1.right, t2.left);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public boolean isSymmetric(TreeNode root) {
        return (DFS(root, root));
    }

    public boolean DFS(TreeNode t1 , TreeNode t2) {
        if (t1 == null && t2 == null){
            return true;
        }
        if (t1 == null || t2 == null){
            return false;
        }

//        time: O1n
//        space: Oh
        return ((t1.val == t2.val)       &&
                (DFS(t1.left, t2.right)) &&
                (DFS(t1.right, t2.left))    );
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//  3rd:
    public boolean isSymmetric_iterative_way(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            if (t1.val != t2.val) {
                return false;
            }

            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}
