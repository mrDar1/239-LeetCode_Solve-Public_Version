/*problem:
* at BST, delete node with given val.
* after deletion tree must remain BST.
* problem is 2 stages: first search, then delete
* constraint: each val unique.
* constraint: given key may exist at tree and may not! */

/* motivation - in BST - when at any given node, from that node if wish to find:
Predecessor==the node before==largest node before the current one:
go one time left and then only right till root.right==null

Successor==the node after==smallest node after the current one:
go one time right and then only left till root.left==null
<see photo to visualize, or draw on paper> */

/*psudo:
* 1-create 2 auxiliary functions: to find successor and predecessor of node to delete - as after delete need to maintain BST properties.
* 2-traverse the BST until find the node to delete.
* 3-once we found it got 3 possibilities:
*       1st-easiest-node is leaf- simple delete, make node null.
*
*       2nd-node got right child:
*       (if got left&right child, we wont "fix" left child as it be fine after the "swap", at cur recursion skip left child with "else"),
*       so, to delete just right child - copy key of succ to cur, then, recursive call to delete duplicate node (the original suc node that we just copied to cur).
*       that use of "swap" - is easiest to deal with ptrs in recursive matter.
*
*       at next recursion call, if leaf - do 1, if still got children do 2/3 again - so each round move all subtree 1 step up - in a "worm" movement - until reach leaf.
*
*       3rd-node got left child - exactly like 2 but with left and successor nodes. */

public class B16_DeleteNodeinBST_450 {
    //     BST property - one step right and then always left
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    //    BST property - one step left and then always right
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) { //START HERE
//        base case:
        if (root == null) return null;

        // explore relevant child nodes - if key bigger go right, if key smaller, go left:
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        // if reach here - it is the node to delete 3 possibilities:
        else {
            // 1st - the node is a leaf - easiest just delete.
            if (root.left == null && root.right == null) {
                root = null;
            }
            // 2nd - the node is not a leaf and has a right child - copy to cur the val of successor, and recursive call to right-subtree to delete the duplicate val of suc (the original node) - that way can adjust ptrs at recursive matter.
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // 3rd - the node is not a leaf, has no right child, and has a left child - same idea of 2nd.
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
//        time: O 2(log n)/2H - H=hight of tree. in avg balance tree is O log n. in skewed tree is O1n. O1H-to find node to delette. O1H-to delete it.
//        space: O1h - space for recursion calls. height of tree. O (log n) in avg balance tree. O1n - in skewed tree.
        return root;
    }
}


/*my first try - didnt work. psudo:
 * first run method to find wishnode (node with that key value)
 * then, run a loop until the next val are the wishnode.
 * create coppy of deleted node.
 * redirect next of root to those of deleted.
 * problem - what to do with children?? how handle them?*/
//    public TreeNode deleteNode(TreeNode root, int key) {
//        TreeNode root_copy = root; //ptr to start.
//        TreeNode wishnode = FindNodeatBST(root, key);
//        if (wishnode == null) return root_copy;
//
//        while (root.left != wishnode && root.right != wishnode){//root cant be here null.
//            if (key < root.val){
//                root = root.left;
//            } else if (key > root.val) {
//                root = root.right;
//            }
//        }
//
//        if (root.right == wishnode){
//            TreeNode wishleft  = wishnode.left;
//            TreeNode wishright = wishnode.right;
//
//            root.right = wishright;
//            wishright.left = wishleft;
//        } else {
//            TreeNode wishleft  = wishnode.left;
//            TreeNode wishright = wishnode.right;
//
//            root.left = wishright;
//            wishright.left = wishleft;
//        }
//
//        return root_copy;
//    }
//    private TreeNode FindNodeatBST(TreeNode root, int key) {
//        if (root == null) return null;
//
//        if (key == root.val){
//            System.out.println(root.val);
//            return root;
//        }
//        if (key < root.val){
//            FindNodeatBST(root.left,key);
//        }
//        if (key > root.val){
//            FindNodeatBST(root.right,key);
//        }
//        return null;
//    }


//
// another implemet - good quesition
//        if (root == null){ return null;}
////        traverse tree until find node.
//        if (key < root.val ){
//root.left =  deleteNode(root.left , key);
//        } else if (key > root.val) {
//root.right = deleteNode(root.right, key);
//        }
//
//                else { //if got here so we have found the node to remove! got 3 options:
////            1st-easiest got no children:
//                if (root.left == null && root.right == null) {
//root = null;
//        }
////            2nd-got right child - replace current value with next node
//        else if (root.right != null) { //if got right children
//root.val = successor(root);
//root.right = deleteNode(root.right, root.val);
//            }
////            3rd option - got left child - replace curr value with next node.
//                    else{
//root.val = predecessor(root);
//root.left = deleteNode(root.left , root.val);
//            }
//                    }
//                    return root;