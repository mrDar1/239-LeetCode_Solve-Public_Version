/*problem:
* delete only leaf nodes with value target.
* after delete - there are new leaf - also need to be checked!*/

/*intuition:
* pre-ordered DFS - since assign root.left/right - when return null that == delete!!
* as ptr re-directed to null, there is no way to access deleted node. */

public class B6_DeleteLeavesWithGivenValue_1325 {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return DFS(root,target);
    }

    private TreeNode DFS(TreeNode root, int target) {
//        base case:
        if (root == null){
            return null;
        }

//        explore children:
        root.left  =  DFS(root.left , target);
        root.right =  DFS(root.right , target);

//        logic - re-direct ptr to not get to cur node.
        if (root.left  == null &&
            root.right == null &&
            root.val == target){//if its leaf && cur.val==target - return null - which delete
            return null;
        }

        return root;
//    time: O1n
//    space: Oh: recursion will grow up to tree height.
//    at worst case, skewed tree (linked list), will be O1n.
//    at best case balance tree will grow to 0.5n or more formally O(nlogn)
    }
}