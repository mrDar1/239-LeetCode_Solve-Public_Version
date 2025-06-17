import java.util.Stack;
/*problem:
given the root of a binary tree,
Return the number of good nodes.
good node == if in the path from root to node, there are no nodes with a value greater than the highest value
 wee seen so far (so from root.left and root.right both highest value so far is root.val!
  its not highest value at Tree just highest value at subtree from root) */

/* 4 solutions:
1st-recursion ,with global vars - more readable.
2nd-recursion, no global vars   - less readable but important! postorder.
3rd-recursion, no global vars   - less readable but important! preorder.
4th-iterative use Pair class.
    all with time & space O1n*/

public class A3_CountGoodNodeInBinaryTree_1448 {
    public static void main(String[] args) {
        Solution_A3_CountGoodNodeInBinaryTree_1448_recursion_use_global_var obj_1448 = new Solution_A3_CountGoodNodeInBinaryTree_1448_recursion_use_global_var();
        //        examples as given in LC:
        // Example 1:
        // Input: root = [3,1,4,3,null,1,5]
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(5);
        System.out.println(obj_1448.goodNodes(root1)); // Output: 4
        System.out.println(obj_1448.goodNodes(root1)); // Output: 4

        // Example 2:
        // Input: root = [3,3,null,4,2]
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.left.right.left = new TreeNode(2);
        System.out.println(obj_1448.goodNodes(root2)); // Output: 3
        System.out.println(obj_1448.goodNodes(root2)); // Output: 3

        // Example 3:
        // Input: root = [1]
        TreeNode root3 = new TreeNode(1);
        System.out.println(obj_1448.goodNodes(root3)); // Output: 1
        System.out.println(obj_1448.goodNodes(root3)); // Output: 1
    }
}
// no need to write again - we have at A1_104 file.
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//
//    TreeNode(int val) {
//        this.val = val;
//    }
//}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//    1st - use global var.
class Solution_A3_CountGoodNodeInBinaryTree_1448_recursion_use_global_var {
    private int numGoodNodes = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE); //since we have not seen any,  Integer.MIN_VALUE==the highest value we seen so far.
        return this.numGoodNodes;
    }

    private void dfs(TreeNode node, int maxSoFar) {
        if (maxSoFar <= node.val) {
            this.numGoodNodes++;
        }

        if (node.left != null) {
            dfs(node.left, Math.max(node.val, maxSoFar)); //the Math.max - update the maxSoFar for each subtree.
        }

        if (node.right != null) {
            dfs(node.right, Math.max(node.val, maxSoFar));
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd-recursion, no global vars   - less readable but important! postorder.
class Solution_A3_CountGoodNodeInBinaryTree_1448_recursion_no_global_var {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE); //since we have not seen any,  Integer.MIN_VALUE==the highest value we seen so far.
    }

    public int dfs(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }

        int left  = dfs(node.left,  Math.max(maxSoFar, node.val));
        int right = dfs(node.right, Math.max(maxSoFar, node.val));

        int ans = left + right;

        if (node.val >= maxSoFar) {
            ++ans;
        }

        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd-recursion, no global vars   - less readable but important! preorder.
class Solution_A3_CountGoodNodeInBinaryTree_1448_recursion_GPT_way {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }

        int ans = 0;

        if (node.val >= maxSoFar) {
            ans++;
        }

        maxSoFar = Math.max(maxSoFar, node.val);

        ans += dfs(node.left, maxSoFar);
        ans += dfs(node.right, maxSoFar);

        return ans;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//4th-iterative use Pair class.
class Solution_A3_CountGoodNodeInBinaryTree_1448_iterative_way {
    //    iterative way:
    class Pair {
        TreeNode node;
        int maxSoFar;

        Pair(TreeNode node, int maxSoFar) {
            this.node = node;
            this.maxSoFar = maxSoFar;
        }
    }
    public int goodNodes(TreeNode root) {
        int ans = 0;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, Integer.MIN_VALUE));

        while (!stack.empty()) {
            Pair pair = stack.pop();
            TreeNode node = pair.node;
            int maxSoFar = pair.maxSoFar;

            if (node.val >= maxSoFar) {
                ans++;
            }

            if (node.left != null) {
                stack.push(new Pair(node.left, Math.max(maxSoFar, node.val)));
            }
            if (node.right != null) {
                stack.push(new Pair(node.right, Math.max(maxSoFar, node.val)));
            }
        }
        return ans;
    }
}