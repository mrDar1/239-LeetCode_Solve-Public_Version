import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*problem return true if tree is "even-odd tree".
* "even-odd tree": binary tree,
* for every odd  lvl: all node.val decreasing && all values are Odd.
* for every even lvl: all node.val increasing && all values are Even. */

/*solutions:
1st - BFS.
2nd - preorder DFS, with global vars */

public class B12_EvenOddTree_1609 {
    public boolean isEvenOddTree(TreeNode root) {
        boolean isEvenlvl = true;//flag. when true-we want increasing values. start lvl is 0 so true.
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelsize = q.size();

//            each start of new level - create relevant prev.
            int prev = Integer.MIN_VALUE;
            if (!isEvenlvl) {
                prev = Integer.MAX_VALUE;
            }

            for (int i = 0; i < levelsize ; i++) {
                TreeNode cur = q.poll();

//                check valid: for even we want increasing && Odd values, for odd decreasing && Even values:
                if (( isEvenlvl && (cur.val <= prev || cur.val % 2 != 1)) ||
                   ( !isEvenlvl && (cur.val >= prev || cur.val % 2 != 0))) {

                    return false;
                }

                prev = cur.val; //update prev node.

//                explore children:
                if (cur.left  != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }

            isEvenlvl = !isEvenlvl; //nice way to flip boolean.
        }

//        time:  O1n
//        space: On/2 - Q hold all node in level. at worst case - perfect tree the last level will be that size.
        return true;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    private List<Integer> prev = new ArrayList<>();
    public boolean isEvenOddTree_DFS(TreeNode root) {
        TreeNode cur = root;

//        time: O1n: DFS traverse each node once.
//        space:Oh - 1h-for recursion call stack - the deepest recursion == tree lvl.
//        h==height of tree:
//        at worst case, degenerate tree == O1n.
//        at best case, balance tree == O log n.
        return dfs(cur, 0);
    }

    private boolean dfs(TreeNode cur, int level) {
        // Base case, an empty tree is "Even-Odd" valid.
        if (cur == null) {
            return true;
        }

        //constraint: if both cur.even && lvl pair or cur.pair && lvl pair - not valid!!!
        if (cur.val % 2 == level % 2) {
            return false;
        }

        //when reach new lvl - add matching new node to prevList.
        if (this.prev.size() <= level) {
            this.prev.add(0);
        }

//        check 2nd validation constraint - increase/decreasing nodes.
        if (this.prev.get(level) != 0 && //if its first element at cur_lvl - cannot fail for being increase/decrease - so wont enter loop.
                                   ((level % 2 == 0 && cur.val <= this.prev.get(level)) || //for even lvl we want increase cur.val, if decrease return false.
                                    (level % 2 == 1 && cur.val >= this.prev.get(level)))) {//for odd  lvl we want decrease cur.val, if increase return false.
            return false;
        }

        this.prev.set(level, cur.val); //update prev with cur.val - must be only after use cur.val!

//        explore children:
        return dfs(cur.left, level + 1) && dfs(cur.right, level + 1);
    }
}