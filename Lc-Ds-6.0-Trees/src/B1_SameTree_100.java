public class B1_SameTree_100 {
    //that ansewr also at "A4_Same_Tree_100" - there got better comments.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        boolean left = isSameTree(p.left,q.left);
        boolean right = isSameTree(p.right,q.right);

        return (left && right);
    }
}
