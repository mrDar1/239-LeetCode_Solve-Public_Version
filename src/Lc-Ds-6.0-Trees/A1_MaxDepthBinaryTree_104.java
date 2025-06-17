import java.util.Stack;
/*problem:
* find the depth of the tree.
* note: at this specific problem - root depth ==1, usual its 0!!*/

/*solutions:
* 1st - recursion
* 2nd - iterative
* even though iterative complicated is same at time & space!*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    //    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class A1_MaxDepthBinaryTree_104 {
    public static void main(String[] args) {
//        same nodes as graph in question:
        TreeNode three = new TreeNode(3);
        TreeNode nine = new TreeNode(9);
        TreeNode twenty = new TreeNode(20);
        TreeNode fifteen = new TreeNode(15);
        TreeNode seven = new TreeNode(7);
//        TreeNode onehundred = new TreeNode(100);

        three.left = nine;
        three.right = twenty;

        twenty.left = fifteen;
        twenty.right = seven;
//        seven.right = onehundred;

        Solution_MaxDepthBinaryTree_104 obj_104 = new Solution_MaxDepthBinaryTree_104();
        System.out.println("using recursion:");
        System.out.println(obj_104.maxDepth(three));

        System.out.println("using iterative way:");
        System.out.println(obj_104.maxDepth_Iterative(three));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//recursion
class Solution_MaxDepthBinaryTree_104 {
    //    recursion way (postorder traversal):
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
//        time: O1n  (if there were another k work inside each node then time-complex be: O(n*k) because each node traverse once)
//        space: O1n - worst case - linear like singly link list. in best case - "complete" tree (0n/2 children per node) will be O(n log n)
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//      iterative way (preorder DFS - in iterative way inorder and postorder are harder to implement):
    class Pair {
        TreeNode node;
        int depth;
        Pair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    public int maxDepth_Iterative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1)); // why 1 when we know root level is 0? specif problem constraint.

        while (!stack.empty()) {
            Pair pair = stack.pop();
            TreeNode node = pair.node;
            int depth = pair.depth;

            ans = Math.max(ans, depth);
            if (node.left != null) {
                stack.push(new Pair(node.left, depth + 1)); //note since we push node.left first next pop() right will be before left! here is matter not.
            }
            if (node.right != null) {
                stack.push(new Pair(node.right, depth + 1));
            }
        }
//        complexity: exactly like recursion!
        return ans;
    }
}