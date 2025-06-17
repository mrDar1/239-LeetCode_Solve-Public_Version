import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

/*problem:
* add to List all nodes - in zig-zag traversal (start from left-to-right,
* at next level right-to-left, and so on.)*/

/*solutions:
* 1st - postorder DFS, no global vars.
* 2nd - my first intuitive way - was very hard to debug and find bug until work! not recommend.. (work but not good practice), try to traverse in zig-zag way.
* 3rd - same as 2nd - but much elegant! here, traverse regularly, but the way added nodes to inner list change so much simpler.
* 4th - copy that, bad way! its work, but use unnecessary Linklist learn what not to do... */

public class A12_BinaryZigzagTrave_103 {
    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        A12_BinaryZigzagTrave_103_postorder_DFS obj_103 = new A12_BinaryZigzagTrave_103_postorder_DFS();
//        Solution_BinaryZigzagTrave_103_BFS_first_time_solve_hard_debugg obj_103 = new Solution_BinaryZigzagTrave_103_BFS_first_time_solve_hard_debugg();
//        Solution_BinaryZigzagTrave_103_BFS_2nd_time_solve obj_103 = new Solution_BinaryZigzagTrave_103_BFS_2nd_time_solve();

        System.out.println("solution:");
        List<List<Integer>> result1 = obj_103.zigzagLevelOrder(root);
        myprint(result1);
    }
    public static void myprint( List<List<Integer>> result){
        // Print the result
        for (List<Integer> level : result) {
            System.out.println(level);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A12_BinaryZigzagTrave_103_postorder_DFS {
    /* psudo:
     * once again - use property that level == ans.size()
     * each time reach to a new level - add new level List, and add that to ans List.
     * if its odd  number - add at last  index in "level List"
     * if its Pair number - add at first index in "level List"
     * from there, we explore children in regular matter.*/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

//        base case:
        if (root == null) {
            return ans;
        }

        DFS(root, 0, ans);
        return ans;
    }

    private void DFS(TreeNode node, int depth, List<List<Integer>> ans) {
        if (depth >= ans.size()) { //remember - will only give size of outer List no matter how many elements inside each iternal List.
            LinkedList<Integer> level_list = new LinkedList<>();
            level_list.add(node.val);
            ans.add(level_list);
        } else {
            if (depth % 2 == 0){
                ans.get(depth).add(node.val);//get the right Internal list for that level - and there add to last
            }
            else {
                ans.get(depth).add(0, node.val);//get the right Internal list for that level - and there add to first
            }
        }


        if (node.left  != null){
            DFS(node.left,  depth + 1, ans);
        }
        if (node.right != null){
            DFS(node.right, depth + 1, ans);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_BinaryZigzagTrave_103_BFS_first_time_solve_hard_debugg {
    /*psudo:
     * traverse tree with Q BFS, but this time use DQ, so can traverse each level in opposite direction.
     * each lvl - count++.
     * each lvl % 2 == 0 go from left to right, else go from right to left */

    public List<List<Integer>> zigzagLevelOrder_first_time_solve(TreeNode root) {
//        edge case:
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> deq = new LinkedList<>(); //deq==double ended Q.
        deq.offer(root);
        int countlvl = 0;

        while (!deq.isEmpty()) {
            int lvlsize = deq.size(); //as always - deq.size() updated, so use copy for each lvl.
            ++countlvl;
            List<Integer> level_list = new ArrayList<>(); //each round create new one. later to add to ans.

            for (int i = 0; i < lvlsize; i++) {
                TreeNode node;
                if (countlvl % 2 != 0) { //if pair: left-to-right - so add to last
                    node = deq.poll();
                    level_list.add(node.val);

                    if (node.left != null) {
                        deq.offer(node.left);
                    }
                    if (node.right != null) {
                        deq.offer(node.right);
                    }
                } else {            // if Odd: right-to-left - so add to start
                    node = deq.pollLast();
                    level_list.add(node.val);

//                    after a lot of debugging this is the mistake!! must start with right and only then left!
                    if (node.right != null) {
                        deq.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deq.offerFirst(node.left);
                    }
                }
            }
            ans.add(level_list);
        }
//        time: O1n.
//        space: On: 0.5n - size of Q, at worst case perfect tree the last level can only have n/2 nodes at max.
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_BinaryZigzagTrave_103_BFS_2nd_time_solve {
    /*psudo BFS:
    * this time do not use (% 2 == 0), instead use boolean "leftToRight" that toggle at end of each lvl.
    * this time, do not try to traverse tree in different matter!!! just change the way add elements to inner List.
    * that way, less room for mistakes! much simpler */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

//      edge case - empty root:
        if (root == null) {
            return ans;
        }

        boolean leftToRight = true; //constraint: at start, traverse from left.
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelSize = q.size(); //as always in BFS, q.size() update, so create copy of it.
            List<Integer> level_list = new ArrayList<>(); //each lvl its own inner List

            for (int i = 0 ; i < levelSize ; i++) {
                TreeNode node = q.poll();

                if (leftToRight) {
                    level_list.add(node.val);       //add at tail
                } else {
                    level_list.add(0, node.val); // add at start
                }

//                explore children - regular way:
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            ans.add(level_list);
            leftToRight = !leftToRight; // toggle flag for next level
        }
//        time: O1n.
//        space: On: 0.5n - size of Q, at worst case perfect tree the last level can only have n/2 nodes at max.
        return ans;
    }
 }

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//  LC approach:
class Solution_BinaryZigzagTrave_103_BFS_LC_solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // ede case - empty root
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        boolean is_order_left = true;
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        LinkedList<Integer> level_list = new LinkedList<Integer>();
        LinkedList<TreeNode> level_q = new LinkedList<TreeNode>();
        level_q.addLast(root);
        level_q.addLast(null);


        while (!level_q.isEmpty()) {
            TreeNode curr_node = level_q.pollFirst();

            if (curr_node != null) {
                if (is_order_left)
                    level_list.addLast(curr_node.val);
                else
                    level_list.addFirst(curr_node.val);

                if (curr_node.left != null)
                    level_q.addLast(curr_node.left);
                if (curr_node.right != null)
                    level_q.addLast(curr_node.right);

            } else {
                // we finish the scan of one level
                results.add(level_list);
                level_list = new LinkedList<Integer>();
                // prepare for the next level
                if (!level_q.isEmpty())
                    level_q.addLast(null);

//                change the flag after each iteration
                is_order_left = !is_order_left;
            }
        }
//        time: O1n
//        space: On
        return results;
    }
}