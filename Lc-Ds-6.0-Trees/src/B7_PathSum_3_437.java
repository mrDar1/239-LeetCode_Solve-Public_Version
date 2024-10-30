import java.util.HashMap;
/*problem:
* return how many different paths are there that node sum along the path == "target".
* this time, path does not have to end with leaf!!
* but, must always direct downward, no side paths!*/


/*psudo: using 3 global vars: target, count/ans, hashmap for frequncy count.
* update global target.
* send to helper function the root, and 0 value.
* inside helper:
* 1-edge case - empty root.
* 2-update currsum
* 3-check it target==cursum - if so count.
* 4-check number of occurrences for "cursum-target".
* 5-update hashmap
* 6-traverse left and right subtrees.
* 7-remove occurence of current val - so wont count parallel. */

public class B7_PathSum_3_437 {
    int count = 0; //count paths.
    HashMap<Long, Integer> h = new HashMap<>(); //<value, number of occurrences >

    public int pathSum(TreeNode root, int targetSum) {
        preorder_DFS(root,0L, targetSum);
//        time: O1n - each node traverse once.
//        space:O1n - for hashmap, keep the number of nodes.
        return count;
    }
    private void preorder_DFS(TreeNode root, long currsum, int targetSum) {
//        edge case:
        if (root == null){
            return;
        }

        //update currsum & check if equal to target:
        currsum += root.val;
        if (currsum == targetSum){
            ++this.count;
        }

        this.count += h.getOrDefault(currsum - targetSum , 0);//count all pathes so far

        h.put(currsum, h.getOrDefault(currsum, 0) + 1);//update hashmap, with "currsum" value.

//        process children:
        preorder_DFS(root.left,  currsum, targetSum);
        preorder_DFS(root.right, currsum, targetSum);

        h.put(currsum, h.get(currsum) - 1) ; //remove current sum from hashmap- so wont count parallel only children.
    }
}