import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
//input - 3rd: adjacency matrix
//got here 2 solution both of same idea!
//1st-from LC course rape it to use with hashmap graph
//2nd-from page no graph and much more logic to me - like it best!

public class A1_NumberofProvinces_547 {
//    Lc Ds - solution.
    /*psudo: first build hashmap graph: <node, list of neighbours>,
    * then, scan each node: if seen before - change it's label to "seen".
    * and count how many unrelated nodes we got == "number of connected component" */
    Map<Integer, List<Integer>> graph = new HashMap<>(); //<node, list of neighbours>
    boolean[] seen;

    public int findCircleNum(int[][] isConnected) {
        // build the graph
        int n = isConnected.length;
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>());
            }
            for (int j = i + 1; j < n; j++) {
                if (!graph.containsKey(j)) {
                    graph.put(j, new ArrayList<>());
                }
                if (isConnected[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        seen = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                // add all nodes of a connected component to the set
                ans++;
                seen[i] = true;
                dfs(i);
            }
        }

//        time: O(n^2)==O(n+e)- n-number of nodes, e-number of edges. go on all of them, at worst case, each node
//        connect to all other nodes its On^2.
//        at each node we have for loop to search for all neighbors.
//        space: O(n+e) - for recursion, for seen[], and for graph hashmap.
//        In the time complexity, we always iterated over the entire matrix to build the graph,
//        but in terms of space complexity, the hash map only grows if the edges actually exist (so its not On^2).
        return ans;
    }

//    auxilary function - takes node and scan for all it's outdegree.
    public void dfs(int node) {
        for (int neighbor: graph.get(node)) {
            if (!seen[neighbor]) {
                seen[neighbor] = true;
                dfs(neighbor);
            }
        }
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//LC 2nd solution - from page:
    public void dfs_from_page(int node, int[][] isConnected, boolean[] visit) {
        visit[node] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[node][i] == 1 && !visit[i]) {
                dfs_from_page(i, isConnected, visit);
            }
        }
    }
    public int findCircleNum_from_page(int[][] isConnected) {
        int n = isConnected.length;
        int numberOfComponents = 0;
        boolean[] visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                numberOfComponents++;
                dfs_from_page(i, isConnected, visit);
            }
        }

        return numberOfComponents;
    }
}
