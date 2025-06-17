import java.util.*;
//didnt get that one...

//    input "1st: array of edges:" undirected.
//    n==vertices(=nodes)
public class A6_FindifPathExistsinGraph_1971 {

    //    LC page - solution 2 - DFS recursion way.
    public boolean validPath_recursion(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] seen = new boolean[n];

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph.computeIfAbsent(a, val -> new ArrayList<Integer>()).add(b);
            graph.computeIfAbsent(b, val -> new ArrayList<Integer>()).add(a);
        }

        return dfs(graph, seen, source, destination);
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] seen, int currNode, int destination) {
        if (currNode == destination) {
            return true;
        }
        if (!seen[currNode]) {
            seen[currNode] = true;

            for (int nextNode : graph.get(currNode)) {
                if (dfs(graph, seen, nextNode, destination)) {
                    return true;
                }
            }
        }
        return false;
    }



    //    LC page - solution 3 - DFS iterate way.
    public boolean validPath_iterative(int n, int[][] edges, int source, int destination) {
        // Store all edges according to nodes in 'graph'.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            graph.computeIfAbsent(a, val -> new ArrayList<Integer>()).add(b);
            graph.computeIfAbsent(b, val -> new ArrayList<Integer>()).add(a);
        }

        // Start from source node, add it to stack.
        boolean[] seen = new boolean[n];
        seen[source] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(source);

        while (!stack.isEmpty()) {
            int currNode = stack.pop();
            if (currNode == destination) {
                return true;
            }
            // Add all unvisited neighbors of the current node to stack'
            // and mark it as visited.
            for (int nextNode : graph.get(currNode)) {
                if (!seen[nextNode]) {
                    seen[nextNode] = true;
                    stack.push(nextNode);
                }
            }
        }

        return false;
    }
}




//// my first fail attempt:
///*psudo:
// * we got number of all vertices==n, so just traverse with DFS and return if size equal*/
//Set<Integer> seen = new HashSet<>();
//public boolean validPath(int n, int[][] edges, int source, int destination) {
//    dfs(edges, source);
//    return (seen.size() == n);
//}
//private void dfs(int[][] edges, int source) {
//    for (int i : edges[source]){
//        if ( !seen.contains(i)){
//            seen.add(i);
//            dfs(edges,i);
//        }
//    }
//}