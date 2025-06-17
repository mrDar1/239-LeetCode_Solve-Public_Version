import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayDeque;

/*problem:
* given 2 BST - convert to 1 combine increasing List */

/*solutions:
* 1st - convert to List use inorder-DFS, combine both in order matter self-implement.
* 2nd - convert to List use inorder-DFS, combine both with built-in function, then use build-in function Sort - good to work with Java library practice.
* 3rd - iterative only , use deque to store elements in increasing order. add to "ans" in sorted manner. */

public class B14_AllElementsinTwoBST_1305 {
    public static void main(String[] args) {
        // Example usage:
        // Create binary search trees
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);

        Solution_B14_AllElementsinTwoBST_1305 obj_1305 = new Solution_B14_AllElementsinTwoBST_1305();
        List<Integer> result = obj_1305.getAllElements(root1, root2);
        System.out.println(result);  // Output: [0, 1, 1, 2, 3, 4]
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_B14_AllElementsinTwoBST_1305 {
// 1st:
    /*psudo:
    populate List 1&2 using inorderDFS recursion.
    both list would be sorted due to property of BST.
    use algorithm to merge 2 sorted lists:
        if l1 < l2 - add l1.
        else - add l2.
        when first of them exhausted - add from other one till exhausted too. */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) { //START HERE
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        DFS(root1, l1);
        DFS(root2, l2);
        mergeLIst(l1, l2, ans);
//        n==len of Tree 1. m==len Tree 2.
//        time:  O2(n+m) -  1n+m-create both lists. 1n+m-create combine list.
//        space: O2(n+m) - same.
        return ans;
    }

    private void DFS(TreeNode root, List<Integer> topopulatelist) {
//        base case:
        if (root == null)
            return;

//        in-order DFS traversal:
        if (root.left != null) {
            DFS(root.left, topopulatelist);
        }
        topopulatelist.add(root.val);
        if (root.right != null) {
            DFS(root.right, topopulatelist);
        }
    }

    private void mergeLIst(List<Integer> l1, List<Integer> l2, List<Integer> ans) {
        int i1 = 0;
        int len1 = l1.size();
        int i2 = 0;
        int len2 = l2.size();

        while ( i1 < len1 && i2 < len2) {
            if (l1.get(i1) < l2.get(i2)){
                ans.add(l1.get(i1));
                ++i1;
            } else {
                ans.add(l2.get(i2));
                ++i2;
            }
        }
        while (i1 < len1){
            ans.add(l1.get(i1));
            ++i1;
        }
        while (i2 < len2){
            ans.add(l2.get(i2));
            ++i2;
        }
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

// 2nd:
/*psudo:
    populate list 1&2 using inorderdfs recursion.
    both list would be sorted due to property of bst.

    concatenating 2 Lists with stream.addAll().

    sort both with built-in sort method. */

//    complexity: same as 1st, with O n log n - for sort.
    public List<Integer> getAllElements_built_in_way(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

//        populate both Lists with inorder DFS:
        inorder(root1, l1);
        inorder(root2, l2);

        Stream.of(l1, l2).forEach(ans::addAll);
//        above line can replace with:
//        Stream.of(l1, l2).forEach(list -> ans.addAll(list)); // using lambda

//        can replace 5 above lines with follow one line:
//        Stream.of(inorder(root1, new ArrayList<>()), inorder(root2, new ArrayList<>())).forEach(ans::addAll);
        Collections.sort(ans);
        return ans;
    }

    public List<Integer> inorder(TreeNode root, List<Integer> arr) {
        if (root == null)
            return arr;

        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

// 3rd:
    /*intuition:
    * iterative once pass, use BST proberty:
    * go to the smallest element (==as left child as possible) at the way push all element to Deque.
    *
    * add to ans the smallest element of both Deque. then poll() it and keep to next iteration. */
    public List<Integer> getAllElements_use_deque(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList();
        ArrayDeque<TreeNode> deque1 = new ArrayDeque<>();
        ArrayDeque<TreeNode> deque2 = new ArrayDeque<>();

        while (root1 != null || root2 != null || !deque1.isEmpty() || !deque2.isEmpty()) {

//            push to Deque in sorted manner (head of Deque smallest),
//            doing that by traverse to smallest element in both tree's.
            while (root1 != null) {
                deque1.push(root1);
                root1 = root1.left;
            }
            while (root2 != null) {
                deque2.push(root2);
                root2 = root2.left;
            }

//            compare Deque1 && Deque2 - choose smallest element from both.
//            poll() smallest and add to ans. then push next element from relevant tree.
            if (deque2.isEmpty() || !deque1.isEmpty() && deque1.getFirst().val <= deque2.getFirst().val) { //deque1 element is smallest
                root1 = deque1.poll();
                ans.add(root1.val);
                root1 = root1.right;
            }
            else { // element is smallest at "deque2" poll it, add to ans and advance root to bigger val (by moove right)
                root2 = deque2.poll();
                ans.add(root2.val);
                root2 = root2.right;
            }
        }

//        n-tree 1, m-tree 2.
//        time:  O1(n+m) - traverse all nodes exactly once.
//        space: Oh1 +O h2 - will grow up to tree height.
//        at worst case - degenerate tree == O1n
//        at avg case - balance tree == O log n.
        return ans;
    }
}

