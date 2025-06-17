import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*problem:
* in BST, return Min val of |nod1.val - node2.val| of any given nodes */

/*intuition for all solutions:
* 1st-use property of inorder DFS in BST: will handle  nodes in sorted order
* 2nd-to find Min difference on any node - we simply check adjacent nodes - as they will produce Min val.*/

/* 3 solution:
 1st-DFS with global var. convert to list, work on list.
 2nd-DFS with global var. traverse BST in sorted manner so calculate global inside each recursion call.
 3rd - iterative way here just to learn. as iterative inorder is harder than
  preorder iterative way. same time & space complexity as solution 1*/

public class A14_MinAbsDiffBST_530 {
    public static void main(String[] args) {
        Solution_A14_MinAbsDiffBST_530 obj_530 = new Solution_A14_MinAbsDiffBST_530();

        // Example 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        System.out.println("Example 1:");
        System.out.println("Input: [4,2,6,1,3]");
        System.out.println("Output: " + new Solution_A14_MinAbsDiffBST_530().getMinimumDifference_DFS_cast_to_list(root1));
        System.out.println("Output: " + obj_530.getMinimumDifference_DFS_cast_to_list(root1));

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(48);
        root2.right.left = new TreeNode(12);
        root2.right.right = new TreeNode(49);
        System.out.println("\nExample 2:");
        System.out.println("Input: [1,0,48,null,null,12,49]");
        System.out.println("Output: " + new Solution_A14_MinAbsDiffBST_530().getMinimumDifference_DFS_cast_to_list(root2));
        System.out.println("Output: " + obj_530.getMinimumDifference_DFS_cast_to_list(root2));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_A14_MinAbsDiffBST_530{
//1st way:
    /*psudo:
    *  1st: traverse BST inorder DFS, store at global List.
    *  next traverse List and find min difference at each 2 adjacent nodes. */
    List <Integer> sortedList = new ArrayList<>();

    public int getMinimumDifference_DFS_cast_to_list(TreeNode root) { // START HERE.
        BuildList_Inorder_DFS(root);
//        time: O2n:  1n-traverse for create sorted list. O1n-for check min value
//        space: O2n: 1n-for recursion frames - same as tree height (at linen tree is O1n). O1n-for sortedList.
        return FindSmallestDiffAtList(sortedList);
    }

    private void BuildList_Inorder_DFS(TreeNode root) {//use property of InorderDFS-traverse from small to big.
        if (null == root){
            return;
        }

        BuildList_Inorder_DFS(root.left);
        sortedList.add(root.val);
        BuildList_Inorder_DFS(root.right);
    }

    private int FindSmallestDiffAtList(List<Integer> mylist) {
        int smallestdif = Integer.MAX_VALUE;

        for (int i = 1; i < mylist.size(); i++) {
            smallestdif = Math.min(smallestdif, Math.abs(mylist.get(i-1) - mylist.get(i)));
        }
        return smallestdif;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    2nd way:
/*psudo inorder DFS:
* global vars: prevNode and minDifference
* each time update globals.*/
    int minDifference = Integer.MAX_VALUE;
    TreeNode prevNode = null;

    int getMinimumDifference_just_one_var(TreeNode root) { // START HERE
        inorderTraversal(root);
//        time: O1n
//        space: O1n - at worst case, as in, degenerate/skewed tree (like link list), will be O1n.
//        but in the average case we greatly improve since BST property! O(log n)
        return minDifference;
    }

    void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left);
        // Find the difference with the previous value if it is there.
        if (prevNode != null) {
            minDifference = Math.min(minDifference, node.val - prevNode.val);
        }
        prevNode = node;
        inorderTraversal(node.right);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//  3rd way - iterative:
    public int getMinimumDifference_IteravtiveWay(TreeNode root) {

        int ans = Integer.MAX_VALUE;
        List<Integer> values = iterativeInorder(root);

        for (int i = 1; i < values.size(); i++) {
            ans = Math.min(ans, values.get(i) - values.get(i - 1));
        }
//        time and space complexity are the same as solution 1! its here basically to show how longer it is with no recursion!!!
//        time: O2n - like recursion
//        space: On - like recursion.
        return ans;
    }
    public List<Integer> iterativeInorder(TreeNode root) {

        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer>  values = new ArrayList<>();

//        as in recursion - use (iterative) DFS method.
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
//          if not null == there are more left child to visits and so push curr.left.
                stack.push(curr);
                curr = curr.left;
            } else {
//          if does null== there are no more left child to visits and so go on and push curr.right.
                curr = stack.pop();
                values.add(curr.val);
                curr = curr.right;
            }
        }
        return values;
    }
}