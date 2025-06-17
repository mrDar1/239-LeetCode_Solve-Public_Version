import java.util.ArrayList;
import java.util.List;
/*problem:
* return true if leaf val && order of 2 trees same.*/

/*solutions:
* 1st - convert 2trees to 2arr
* 2nd - same, but cooler writing (less recommend to use, but still pretty)*/
public class B2_LeafSimilarTree_872 {
    /*psudo:
    preorder DFS
    convert from tree to 2arrList of leaves values.
    compare List with .equal(). */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        return (list1.equals(list2));
    }

    private void dfs(TreeNode root, List<Integer> mylist) {
//        base case:
        if (root == null) {
            return;
        }
//        logic:
        if (root.left == null && root.right == null) {
            mylist.add(root.val);
        }

//        explore:
        dfs(root.left, mylist);
        dfs(root.right, mylist);
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class B2_LeafSimilarTree_872_second_time {
    /*psudo:
    preorder DFS
    convert from tree to 2arrList of leaves values.
    compare List with .equal(). */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        return (list1.equals(list2));
    }
    public void dfs(TreeNode node, List<Integer> mylist) {
        if (node != null) {

            if (node.left == null && node.right == null){
                mylist.add(node.val);
            }

            dfs(node.left,  mylist);
            dfs(node.right, mylist);
        }
    }
}