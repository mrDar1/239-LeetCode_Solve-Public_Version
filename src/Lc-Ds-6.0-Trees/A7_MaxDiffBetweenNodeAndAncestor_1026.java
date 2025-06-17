/*problem:
* find the max v.
* v == |a.val - b.val|
* a ancestor of b! (=b child of a )
* constraint: can work of diffrenet node what matter is only the value!*/

/*2 solutions:
 1-DFS , postorder, global var.
 2-DFS , postorder, no global var - more like it!*/

public class A7_MaxDiffBetweenNodeAndAncestor_1026 {
// 1-DFS , postorder, global var.
    /*psudo: use global int - store highest different.
     * use helper funcion of cur, max, min
     * update result with Math.max(result , possible result)
     * update max and min val.
     * explore recursively children nodes*/
int result = 0;
public int maxAncestorDiff(TreeNode root) {
    if (root == null){
        return 0;
    }
    helper(root, root.val, root.val);
    return this.result;
}
public void helper(TreeNode cur, int max, int min) {
//    base case:
    if (cur == null){
        return;
    }

    int possible_result = Math.max( Math.abs(max - cur.val) , Math.abs(min - cur.val) ); //constraint.
    this.result = Math.max(this.result, possible_result);

    max = Math.max(max, cur.val);
    min = Math.min(min, cur.val );

    helper(cur.left, max, min);
    helper(cur.right, max, min);
//    time: O1n
//    space: O1n: recursion will grow up to tree height.
//    at worst case, skewed tree (linked list), will be O1n.
//    at best case balance tree will grow to 0.5n or more formally O(nlogn)
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    //  2-DFS , postorder, no global var - more like it!
    public int maxAncestorDiff_optimized(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper_optimized(root, root.val, root.val);
    }
    public int helper_optimized(TreeNode node, int curMax, int curMin) {
        // if encounter leaves, return the max-min along the path
        if (node == null) {
            return curMax - curMin;
        }
        // else, update max and min
        // and return the max of left and right subtrees
        curMax = Math.max(curMax, node.val);
        curMin = Math.min(curMin, node.val);

        int left  = helper_optimized(node.left,  curMax, curMin);
        int right = helper_optimized(node.right, curMax, curMin);

        return Math.max(left, right);
    }
//    complexity - same but yet more efficient!
//    time: O1n
//    space: O1n: recursion will grow up to tree height.
//    at worst case, skewed tree (linked list), will be O1n.
//    at best case balance tree will grow to 0.5n or more formally O(nlogn)
}
