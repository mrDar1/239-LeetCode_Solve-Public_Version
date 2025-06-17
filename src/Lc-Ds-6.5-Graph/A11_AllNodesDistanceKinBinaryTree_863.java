import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
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

public class A11_AllNodesDistanceKinBinaryTree_863 {
    /*PSUDO:
    * 1-change the Tree into undirected graph- do so with DFS(can do it with BFS DFS more elegant).
    * 2-use simple BFS to find nodes at distance K: starting from target, and after reach K nodes, return nodes in Q.
    * 3-using hashmap to remember parents*/
    Map<TreeNode, TreeNode> parents = new HashMap<>(); //<node, parent> keep track of parent after convert into undirect graph.

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {//start here
        dfs(root, null);//convert into undirect graph.
        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        int distance = 0;


        while (!queue.isEmpty() && distance < k) {
            int currentLength = queue.size();

            for (int i = 0; i < currentLength; i++) {
                TreeNode node = queue.poll();

                for (TreeNode neighbor: new TreeNode[]{node.left, node.right, parents.get(node)}) {
                    if (neighbor != null && !seen.contains(neighbor)) {
                        seen.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }

            distance++;
        }

        List<Integer> ans = new ArrayList<>();//convert Linklist into List format. as required.
        for (TreeNode node: queue) {
            ans.add(node.val);
        }

        return ans;
    }

    public void dfs(TreeNode node, TreeNode parent) {//elegant function to change tree into undirected graph.
        if (node == null) {
            return;
        }

        parents.put(node, parent);//first time we put null cause root got no parent.
        dfs(node.left, node);
        dfs(node.right, node);
    }
}