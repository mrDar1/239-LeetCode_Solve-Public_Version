/*problem:
* return Max number of edges - that in zig-zag manner (=child left, then right, then left so on...).
* can start from any node,
* must only move down no parallel! */

/*intuition:
* each recursion call - if at "zig-zag manner" increase step+1. else-reset steps counter */

/*solutions:
* 1st - DFS whith global vars.
* 2nd - DFS no globals*/

public class B8_LongestZigZagPathBinaryTree_1372 {
    /*preorder DFS - each time before keep explore compare to global var.*/
    int longestSoFar = 0;
    public int longestZigZag(TreeNode root) {
        maxZigZag(root.left , false, 1); //just go left,  next round right.
        maxZigZag(root.right, true,  1); //just go right, next round left.
//        time: O1n
//        space:O1n
        return this.longestSoFar;
    }
    private void maxZigZag(TreeNode node,boolean isCamefromRight, int steps){
//        edge case:
        if (node == null){
            return;
        }

        this.longestSoFar = Math.max(this.longestSoFar, steps);

        if (isCamefromRight){ //if true - means we came from "right" so now left (we still explore other subtree, but reset counter).
            maxZigZag(node.left , false, steps + 1 ); //valid zig-zag - increase count.
            maxZigZag(node.right, true, 1 );          //  not zig-zag - reset count.

        }else {
            maxZigZag(node.left , false,1 );
            maxZigZag(node.right, true, steps + 1 );
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B8_LongestZigZagPathBinaryTree_1372_no_global {
    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxZigZag(root, true , 0),
                        maxZigZag(root, false, 0) );
    }

    private int maxZigZag(TreeNode node, boolean isCamefromRight, int length) {
        if (node == null){
            return length - 1;
        }

        int left;
        int right;

        if (isCamefromRight){
            left  = maxZigZag(node.left , false , length + 1);
            right = maxZigZag(node.right, true  , 1);
        }else {
            left  = maxZigZag(node.left , false  , 1);
            right = maxZigZag(node.right, true , length + 1);
        }

        return Math.max(length, Math.max(left, right));
    }
}