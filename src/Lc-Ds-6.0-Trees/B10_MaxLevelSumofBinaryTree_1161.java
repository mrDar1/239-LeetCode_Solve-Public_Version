import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*problem:
* for each level - sum all nodes values.
* return the index number of level with max sum, if there are several lvl with same sum - return smallest.
* constraint: root lvl is 1! not regular 0... */

/* solutions:
1st - BFS.
2nd - convert to List with preorderDFS. wotk with List. */

public class B10_MaxLevelSumofBinaryTree_1161 {
    public int maxLevelSum(TreeNode root) {
        int max_lvl_sum = Integer.MIN_VALUE;
        int index_level_of_max_sum = 1;//we need to return not sum - but index of the level.  why initialize to 1? -constraint first level is 1, for case of root==null.
        int curlevel = 0; // how come initialize to 0 when constraint first lvl 1? -when start each lvl loop first we ++.
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            int levelsize = q.size();
            int cur_lvl_sum = 0;
            ++curlevel;

            for (int i = 0 ; i < levelsize ; ++i){
                TreeNode cur = q.poll();
                cur_lvl_sum += cur.val;

//                explore children:
                if (cur.left  != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }

            if (max_lvl_sum < cur_lvl_sum){
                max_lvl_sum = cur_lvl_sum;
                index_level_of_max_sum = curlevel;
            }
        }
//      time: O1n
//        space: On/2== On : not include ans output. each q and innerList can grow up to the size of level.
//                  at worst case - perfect tree - will grow to n/2.
        return index_level_of_max_sum;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo - convert to List using preorder-DFS then traverse List.*/
    public int maxLevelSum_DFS_recursion_way(TreeNode root) {
        int max_lvl_sum = Integer.MIN_VALUE;
        int index_level_of_max_sum = 0;

        List<Integer> List_of_lvl_sum = new ArrayList<>();
        dfs(root, 0, List_of_lvl_sum); //populate the list with sum of each lvl. use preorder-DFS.

        for (int i = 0; i < List_of_lvl_sum.size(); i++) {
            if (max_lvl_sum < List_of_lvl_sum.get(i)) {
                max_lvl_sum = List_of_lvl_sum.get(i);
                index_level_of_max_sum = i + 1;
            }
        }
//        time: On: O1n-DFS to populate list with all lvl sums. Oh-traverse this list and find max.
//        space:O2h - 1h-for recursion call stack - the deepest recursion == tree lvl. 1h-to store List of each lvl sum.
//        h==height of tree:
//        at worst case, degenerate tree == O1n.
//        at best case, balance tree == O log n.
        return index_level_of_max_sum;
    }

    public void dfs(TreeNode node, int level, List<Integer> sumOfNodesAtLevel) {
//        base case:
        if (node == null) {
            return;
        }

//        logic:
        if (sumOfNodesAtLevel.size() == level) {// when reach new lvl - add to List new element with cur.val
            sumOfNodesAtLevel.add(node.val);
        } else {    //if already reach that lvl - add to lvlsum cur.val.
            sumOfNodesAtLevel.set(level, sumOfNodesAtLevel.get(level) + node.val);
        }

//        explore children:
        dfs(node.left,  level + 1, sumOfNodesAtLevel);
        dfs(node.right, level + 1, sumOfNodesAtLevel);
    }
}