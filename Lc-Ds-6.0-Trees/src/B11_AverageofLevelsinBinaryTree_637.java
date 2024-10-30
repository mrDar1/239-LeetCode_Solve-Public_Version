import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*problem:
* return List of avg of all nodes inside each lvl */

/*2 solutions:
1st - BFS.
2nd - preorder-DFS, with global vars */

public class B11_AverageofLevelsinBinaryTree_637 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double>  avg = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while ( !q.isEmpty()){
            int levelsize = q.size();
            long cur_lvl_sum = 0;

            for (int i = 0; i < levelsize; i++) {
                TreeNode cur = q.poll();
                cur_lvl_sum += cur.val;

                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }

            avg.add((double) cur_lvl_sum / levelsize);
        }
//        time: O1n
//        space:On : n/2-q can grow at size of biggest node level - which at worst case of perfect tree is the leaf lvl == On/2.
        return avg;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public List < Double > averageOfLevels_use_DFS_Recursion(TreeNode root) {
        List <Double> ans = new ArrayList <> ();      //at first it sum_of_each_lvl, only later would be avg.
        List <Integer> lvl_size = new ArrayList <> ();//size of each lvl: [<number of nodes at lvl 0>, <number of nodes at lvl 1>....... ,<number of nodes at lvl n>]
        DFS(root, 0, ans, lvl_size); //populate sum_of_each_lvl and count nodes to know size of each lvl.

        // so far "ans" is List of sum_of_each_row, now we make it average - for each lvl_sum we divide with lvl_size
        for (int i = 0; i < ans.size(); i++){
            ans.set(i, ans.get(i) / lvl_size.get(i));
        }

//        time: On: O1-DFS to build sum of each lvl + count size of each lvl. Oh - traverse List and convert to avg.
//        space:Oh - 1h-for recursion call stack - the deepest recursion == tree lvl. 1h-to store List of each lvl sum.
//        h==height of tree:
//        at worst case, degenerate tree == O1n.
//        at best case, balance tree == O log n.
        return ans;
    }

    public void DFS(TreeNode root, int curlvl, List <Double> ans, List <Integer> lvl_size) {
//        base case:
        if (root == null)
            return;

//        logic:
        if (curlvl >= ans.size()){//reach new lvl - so add new element for this lvl.
            ans.add( (double)root.val);
            lvl_size.add(1); //initialize count to 1 - as we just added 1 new node.
        }else {//already been at that lvl - so add cur.val to sum and increase lvl_size by 1.
            ans.set(curlvl, ans.get(curlvl) + root.val);
            lvl_size.set(curlvl, lvl_size.get(curlvl) + 1);
        }

//        explore children:
        DFS(root.left,  curlvl + 1, ans, lvl_size);
        DFS(root.right, curlvl + 1, ans, lvl_size);
    }
}