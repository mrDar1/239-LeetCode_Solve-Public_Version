import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*problem: find large val at each depth. */

/*solution:
* 1st - BFS
* 2nd - DFS, postorder no global vars. (similar to "A9_BinaryTreeRSideView_199" there use more techniques but
* both similar so here use my most-favorite way from there. */

public class A10_FindLargestNodeEachRow_515 {
    public List<Integer> largestValues(TreeNode root) {
//        edge case:
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while ( !q.isEmpty()){
            int levelsize = q.size(); //q.size() will update, so has to create copy.

            //each time we get here - we start new lvl of depth,
            // so initilize max to minimum int.
            int max = Integer.MIN_VALUE;

            while ( levelsize > 0){
                --levelsize;

                TreeNode node = q.poll();
                max = Math.max(max, node.val);

                if (node.left != null){
                    q.offer(node.left);
                }
                if (node.right!= null){
                    q.offer(node.right);
                }
            }
//            each time we get here - is only after we iterate entire row - and get the max!
            ans.add(max);
        }
//        time: O1n
//        space: O0.5n - size of Q, at worst case perfect tree the last level can only have n/2 nodes at max.
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A10_FindLargestNodeEachRow_515_DFS {
    /*intuition:
     * as in A9_BinaryTreeRSideView_199: each time the "ans" size will be equal to the level-number.
     *
     * here, we traverse DFS so each time we reach new depth - we simply add that element as highest.
     * each time when reach that level again - edit element to be max - according to cur depth */

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        DFS(root, 0 , ans);
        return ans;
    }

    private void DFS(TreeNode root, int leveldepth, List<Integer> ans) {
//        base case:
        if (root == null){
            return;
        }

//        when reach new depth - simply add.
//        when reach that depth again - update ans with Max element.
        if (leveldepth == ans.size()){
            ans.add(root.val);
        } else {
            ans.set(leveldepth, Math.max(root.val, ans.get(leveldepth)));
        }

        DFS(root.left,  leveldepth + 1, ans);
        DFS(root.right, leveldepth + 1, ans);
    }
}
//DFS complexity
//time: O1n
//space: O1n: recursion will grow up to tree height.
//at worst case, skewed tree (linked list), will be O1n.
//at best case balance tree will grow to 0.5n or more formally O(log n)