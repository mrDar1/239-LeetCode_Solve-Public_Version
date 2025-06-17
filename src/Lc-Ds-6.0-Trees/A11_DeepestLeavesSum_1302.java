import java.util.*;
/*problem:
 return only deepest level leaves sum!!*/

/*solutions:
* 1st - BFS, save each level sum at stack, at end peek() that stack.
* 2nd - DFS postorder, no global var, with helper method
*
* didnt like those 2, here only for study.
* 3rd - BFS - like 1 but in worse way - each time sum cur lvl - then delete it and start another.
* 4th - BFS - traverse all nodes, skip each lvl until reach last - only then doing sum. */

//here LC suck... didnt use them at all.
public class A11_DeepestLeavesSum_1302 {
    /*psudo:
    * traverse use BFS
    * using stack to hold the sum of each lvl, at end return stack.peek() */
    public int deepestLeavesSum(TreeNode root) {
        //        edge case:
        if (root == null) {
            return 0;
        }

        Stack <Integer> st = new Stack<>(); //hold sum of each level.
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int levelsize = q.size(); //q.size() update constantly so create copy for each level.
            //each time we get here - we start new lvl of depth,
            // so initilize current lvl sum to 0.
            int sum = 0;

            while (levelsize > 0) {
                --levelsize;

                TreeNode node = q.poll();
                sum += node.val;


                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
//            each time we get here - is only after we iterate entire row - and get the sum of all row!
            st.push(sum);
        }
//        time: O1n
//        space: On: On-stack; 0.5n - size of Q, at worst case perfect tree the last level can only have n/2 nodes at max.
//        while (!st.empty()){ //debug:
//            System.out.println(st.pop());
//        }
        return st.peek();
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A11_DeepestLeavesSum_1302_DFS {
    /*intuition (similar to A10_FindLargestNodeEachRow_515_DFS):
    * traverse tree with DFS:
    * each time reach new depth - simply add it.
    * each time reach depth that been travel before - update sum with last+cur.
    * at last - return the last element from ans (which represent last level sum). */

    public int deepestLeavesSum(TreeNode root) {
        List<Integer> ans = new ArrayList<>(); // hold sum of each row.
        DFS(root, 0 , ans);
        return ans.get(ans.size() - 1);
    }

    private void DFS(TreeNode root, int leveldepth, List<Integer> ans) {
//        base case:
        if (root == null){
            return;
        }

        if (leveldepth == ans.size()){
            ans.add(root.val);
        } else {
            ans.set(leveldepth, (ans.get(leveldepth) + root.val));
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

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//class A11_DeepestLeavesSum_1302_iterative_BFS {
//    class Pair {
//        TreeNode node;
//        int depth;
//        Pair(TreeNode node, int depth) {
//            this.node = node;
//            this.depth = depth;
//        }
//    }
//
//    public int deepestLeavesSum(TreeNode root) {
//        int deepestSum = 0;
//        int depth = 0;
//        int currDepth;
//        Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
//        queue.offer(new Pair(root, 0));
//
//        while (!queue.isEmpty()) {
//            Pair<TreeNode, Integer> p = queue.poll();
//            root = p.node; //for here "pair" for other - p.getKey();
//            currDepth = p.depth; //for here "pair" for other - p.getValue();
//
//            if (root.left == null && root.right == null) {
//                // if this leaf is the deepest one seen so far
//                if (depth < currDepth) {
//                    deepestSum = root.val;      // start new sum
//                    depth = currDepth;          // note new depth
//                } else if (depth == currDepth) {
//                    // if there were already leaves at this depth
//                    deepestSum += root.val;     // update existing sum
//                }
//            } else {
//                if (root.left != null) {
//                    queue.offer(new Pair(root.left, currDepth + 1));
//                }
//                if (root.right != null) {
//                    queue.offer(new Pair(root.right, currDepth + 1));
//                }
//            }
//        }
////        time: O1n
////        space: O0.5n - size of Q, at worst case perfect tree the last level can only have n/2 nodes at max.
//        return deepestSum;
//    }
//}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A11_DeepestLeavesSum_1302_iterative_BFS_optimized {
    public int deepestLeavesSum(TreeNode root) {
        ArrayDeque<TreeNode> currLevel = new ArrayDeque<>();
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque<>();
        nextLevel.offer(root);

//        traverse level until reach deepest level.
        while (!nextLevel.isEmpty()) {
            // prepare for the next level
            currLevel = nextLevel.clone();
            nextLevel.clear();

            for (TreeNode node: currLevel) {
                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) {
                    nextLevel.offer(node.left);
                }
                if (node.right != null) {
                    nextLevel.offer(node.right);
                }
            }
        }

//        after reach deepest level, here sum up its nodes:
        int deepestSum = 0;
        for (TreeNode node: currLevel) {
            deepestSum += node.val;
        }
//        time: O1n
//        space: O0.5n - size of Q, at worst case perfect tree the last level can only have n/2 nodes at max.
        return deepestSum;
    }
}