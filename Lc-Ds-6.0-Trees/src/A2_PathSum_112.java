import java.util.Stack;

/*problem:
* Given the root of a binary tree and an integer "targetSum",
* return true if the tree has a root-to-leaf path such that adding up all
* the values along the path equals targetSum.*/

/*4 solutions: 2 from course, 2 from-page solution:
1st: recursion   - use helper method & global vars.
2nd: recursion   - change original value - like it more.
3rd: iterative   - 2 stack, less elegant, simpler to understand - didnt like it
4th: iterative   - 1 stack, with Pair - more elegant, like it more.
   time and space - O1n for all - different ways but same... */


// no need to write again - we have at A1_104 file.
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    //    TreeNode() {}
//    TreeNode(int val) { this.val = val; }
////    TreeNode(int val, TreeNode left, TreeNode right) {
////        this.val = val;
////        this.left = left;
////        this.right = right;
////    }
//}


/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//    1st: recursion   - use helper method & global vars.
public class A2_PathSum_112 {
    /*psudo: create global var - so can check if equal at helper method.
     * base case if null return.
     * update curval with curnode.
     * if at leaf check if curval==target.
     * return node.left and right*/
    int target;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        this.target = targetSum;
        return Dfs(root, 0);
    }

    public boolean Dfs(TreeNode node, int curval) {
        if (node == null) {
            return false;
        }

        curval += node.val;

        if (node.left == null && node.right == null) { //if leaf.
            return (curval == target);
        }

        return (Dfs(node.left, curval) || Dfs(node.right, curval));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//2nd: recursion   - change original value - like it more.
class A2_PathSum_112_recursion_no_helper_method {
    /*psudo: base case - null==return.
     * reduce targetsum.
     * if leaft - check if sum==0.
     * continue to more children - left or right. || cause if one is good then its good.*/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        targetSum -= root.val;

        if (root.left == null && root.right == null) { //if its leaf
            return (targetSum == 0);
        }

//fail optimize try - dont work with negative numbers so dont use it.
//        if (targetSum < 0){
//            return false;
//        }
        return (hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//3rd: iterative   - 2 stack, less elegant, simpler to understand
class A2_PathSum_112_iterative_no_pair {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        Stack<TreeNode> node_stack = new Stack<>();
        Stack<Integer>  sum_stack = new Stack<>();

        node_stack.add(root);
        sum_stack.add(sum - root.val);

        while ( !node_stack.isEmpty() ) {
            TreeNode node = node_stack.pop();
            int curr_sum  = sum_stack.pop();
            if ((node.right == null) && (node.left == null) && (curr_sum == 0))
                return true;

            if (node.right != null) {
                node_stack.add(node.right);
                sum_stack.add(curr_sum - node.right.val);
            }
            if (node.left != null) {
                node_stack.add(node.left);
                sum_stack.add(curr_sum - node.left.val);
            }
        }
        return false;
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//4th: iterative   - 1 stack, with Pair - more elegant.
class A2_PathSum_112_iterative_with_pair {
    class Pair {
        TreeNode node;
        int curr; //current sum

        Pair(TreeNode node, int curr) {
            this.node = node;
            this.curr = curr;
        }
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 0));

        while (!stack.empty()) {
            Pair pair = stack.pop();
            TreeNode node = pair.node;
            int curr = pair.curr;

            if (node.left == null && node.right == null) { //==its leaf
                if ((curr + node.val) == targetSum) {
                    return true;
                }
            }

            curr += node.val; //add curSum so far with curNode
            if (node.left != null) {
                stack.push(new Pair(node.left, curr));
            }
            if (node.right != null) {
                stack.push(new Pair(node.right, curr));
            }
        }
        return false;
    }
}