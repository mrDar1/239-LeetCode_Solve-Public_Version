import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*problem:
* traverse tree with BST, each node.val add to list */

/*solutions:
1st - classic BST traversal
2nd - recursive with globals vars
3rd - recursive no globals. */

public class B9_BinaryTreeLevelOrderTraversal_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

//        edge case:
        if (root == null){
            return ans;
        }

        while (!queue.isEmpty()){
            int levelsize = queue.size(); //as allways - q.size() will update so create copy for later loop.
            List<Integer> innerList = new ArrayList<>(); //constraint: for each level - its oun innerList.

            for (int i = 0; i < levelsize; i++) {
                TreeNode cur = queue.poll();
                innerList.add(cur.val);


                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }
            ans.add(innerList);
        }
//        time: O1n
//        space: On/2== On : not include ans output. each q and innerList can grow up to the size of level.
//                  at worst case - perfect tree - will grow to n/2.
        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B9_BinaryTreeLevelOrderTraversal_102_DFS_for_fun {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        DFS(root, 0);
        return ans;
    }

    private void DFS(TreeNode root, int curlevel) {
//        base case:
        if (root == null){
            return;
        }

//        logic - each time reach a new level - allocate space for new innerList.
//        remember ans.size() - will only count number of innerList - no matter how many elements inside them.
//        so, ans.size()==level of tree at all time.
        if (curlevel == this.ans.size()){
            ans.add(new ArrayList<>());
        }

//        logic - add cur.val to it rightful/matching inner ArrayList.
        ans.get(curlevel).add(root.val);

//        keep explore children:
        DFS(root.left,  curlevel + 1);
        DFS(root.right, curlevel + 1);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B9_BinaryTreeLevelOrderTraversal_102_DFS_for_no_globals {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        return DFS(root, 0, ans);
    }

    private List<List<Integer>> DFS(TreeNode root, int curlevel, List<List<Integer>> ans) {
//        base case:
        if (root == null){
            return ans;
        }

//        at each new level - allocate space at ansList<<>>
        if (curlevel == ans.size()){
            ans.add(new ArrayList<>());
        }

//        logic - add cur.val to it rightful/matching inner ArrayList.
        ans.get(curlevel).add(root.val);

//        keep explore children:
        DFS(root.left,  curlevel + 1, ans);
        DFS(root.right, curlevel + 1, ans);

        return ans;
    }
}