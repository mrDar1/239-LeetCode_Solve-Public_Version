import java.util.*;
/*problem:
* return the value in the BST that is closest to "target" (float type)
* If there are multiple answers, print the smallest.
* note - return closest node value!! not smallest different!*/

/*several solutions:
* 1st - my intuitive solution - using recursion and 2 global vars.
* 2nd - convert to Sorted_arr (with inorder DFS), then work with arr.
* 3rd - convert to Sorted_arr (with inorder DFS), then adjust(with override anonymous function) built-in-function, to return closet element.
* 4th - iterative - when target in range that "pred" smaller and "cur" bigger - can stop.
* 5th - iterative - 4th optimized */

public class A17_ClosestBSTval_270 {
    /*psudo: use global var to store maxsofar, and closesetnode.
     * each time check curr and update maxsofar if needed */
//    my first intuitive way:
    int closestnode = Integer.MAX_VALUE; //node to return at end.
    double maxsofar = Integer.MAX_VALUE; //var to do calculates.

    public int closestValue_my_intuitive_approach(TreeNode root, double target) {
        InorderDfs(root, target);

//    time & space: Oh - h=height.
//    at worst case degenerate tree - O1n,
//    in best case: balance BST - O logn - in both stop when reach leaf, so do not traverse entire tree!
        return closestnode;
    }

    public void InorderDfs(TreeNode root, double target) {
        if (root == null) {
            return;
        }

        if (root.val > target) {
            InorderDfs(root.left, target);
        }

        if (Math.abs(target - root.val) < maxsofar) {
            maxsofar = Math.abs(target - root.val);
            closestnode = root.val;
        }

        if (root.val < target) {
            InorderDfs(root.right, target);
        }
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    2nd:
/*psudo:
* convert to sorted arr, with inorder recurion.
* then, traverse list */
    public int closestValue_convert_to_sorted_arrList(TreeNode root, double target) {
        List<Integer> nums = new ArrayList<>();  //List to store tree in.
        PopulateSortedList(root, nums);          //Populate the list at sorted manner.
        int closest = findClosest(nums, target); //Find the closest value in the list to the target

//        time:  O2n - O1n-convert to arr. O1n-traverse all arr and find closest.
//        space: O1n - arr to copy tree.
        return closest;
    }
    private void PopulateSortedList(TreeNode root, List<Integer> nums) {
        if (root == null)
            return;

        PopulateSortedList(root.left, nums);
        nums.add(root.val);
        PopulateSortedList(root.right, nums);
    }

    private int findClosest(List<Integer> nums, double target) {
        int closest = nums.get(0); //closest node. at first initialize to first element.
        double minDiff = Math.abs(nums.get(0) - target);

        for (int i = 1; i < nums.size(); i++) {
            int current = nums.get(i);
            double diff = Math.abs(current - target);

            if (diff < minDiff) {
                minDiff = diff;
                closest = current;
            }
        }
        return closest;
    }

    /* **************************** */
    /* **************************** */
    /* **************************** */
    /* **************************** */

//    3rd:
/*  The simplest approach (3 lines in Python) is to build inorder traversal arr and then find the
    closest element in a sorted arrayList with built-in function 'min'.
    at java, we adjust regular compare method. */

/*psudo:
convert to sorted arr, with inorder recurion.
adjust built-in comparator */
    public int closestValue_adjust_comparator(TreeNode root, double target) {
        List<Integer> nums = new ArrayList();
        PopulateSortedList_2(root, nums); //populate arrayList nums.

        return Collections.min(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
            }
        });
    }
    public void PopulateSortedList_2(TreeNode root, List<Integer> nums) {
        if (root == null) return;

        PopulateSortedList_2(root.left, nums);
        nums.add(root.val);
        PopulateSortedList_2(root.right, nums);
    }
//    exactly like 2nd!! as before we implement ourselves and here adjust built-in function.
//        time:  O2n - O1n-convert to arr. O1n-traverse all arr and find closest.
//        space: O1n - arr to copy tree.

    /* **************************** */
    /* **************************** */
    /* **************************** */
    /* **************************** */

//    4th.
/*psudo:
* traverse at iterative manner, when reach point: pred < "target" < cur - compare both and return
we can assure ther's no answer other than that */

    public int closestValue_2_approach(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<>();
        long pred = Long.MIN_VALUE;

        while ( root != null || !stack.isEmpty()){ //until reach smallest value.

            while (root != null){//until get to leaf - go left most, as in regular DFS.
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (pred <= target && target < root.val){
               return Math.abs(pred - target) <= Math.abs(root.val - target) ? (int)pred : root.val;
            }

            pred = root.val;
            root = root.right;
        }
//        time: O 1h - at worst case traverse each node once. if the tree is balanced - O log n.
//              in big O notation its same but - unlike all other solution so far, when find answer in range - stop traverse - so much faster!
//        space: O h - size of stack as deep as tree goes.
        return (int)pred; //if got so far-didn't find close number - so return biggest number in Tree.
    }

    /* **************************** */
    /* **************************** */
    /* **************************** */
    /* **************************** */

//    5th
    public int closestValue_3_approach(TreeNode root, double target) {
        TreeNode node = root; // changeable copy of root. so always keep ptr to root.
        int closest = root.val; //closest node value to target.

        while (node != null){

            if (Math.abs(node.val - target) < Math.abs(closest - target) ||
                Math.abs(node.val - target) == Math.abs(closest - target) && node.val < closest ){//constraint: at several ans - return smallest.
                closest = node.val;
            }

            node = (target < node.val) ? node.left : node.right;
        }
//        time: O h - since go from root to a leaf.
//                  in big O notation its same but -  less faster than 4 - as dont "return" when reach ans, but keep going till reach closest leaf. so its similar to before slightly less fast.
//        space: O1
        return closest;
    }
}