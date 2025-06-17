/*problem:
* given val, insert to BST in proper way.
* all values unique.
* if several option valid - all accept */

/*solutions:
* 1st-DFS
* 2nd-iterative*/

public class A16_InsertintoBST_701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
//        base case:
        if (root == null){
            return new TreeNode(val);
        }

        if (root.val < val ){
            root.right =  insertIntoBST(root.right, val);
        }else{
            root.left  =  insertIntoBST(root.left, val);
        }
        // why do we need to do "root.right/left = insertIntoBST..."?
        //      - because we want to update the ptr of the node to the new value we will add.

//        time & space: O1h.
//        h==tree height
//        at worst case (skewed tree == O1n), at avg case - O log n.
        return root;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //iterate way:
    public TreeNode insertIntoBST_iterateway(TreeNode root, int val) {
        TreeNode node = root;

        while (node != null) {

            if (val > node.val) { // insert into right subtree
                if (node.right == null) { // insert right
                    node.right = new TreeNode(val);
                    return root;
                }else{ // explore right children
                    node = node.right;
                }

            }else {// insert into left subtree
                if (node.left == null) { // insert right
                    node.left = new TreeNode(val);
                    return root;
                }
                else{ // explore left children
                    node = node.left;
                }
            }
        }
//        time: O log n
//        space: O1.
        return new TreeNode(val);
    }
}