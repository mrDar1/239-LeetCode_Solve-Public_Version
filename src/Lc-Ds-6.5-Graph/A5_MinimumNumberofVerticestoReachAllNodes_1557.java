import java.util.ArrayList;
import java.util.List;
//input as 1st: array of edges - directed.
//constraint - acyclic so much easier!
//given n - number of vertices(=nodes)
public class A5_MinimumNumberofVerticestoReachAllNodes_1557 {
    /*psudo - count how many indegree(=ptr to that node) each vertice has.
    * if got 0 - we must include it. */
    public List<Integer> findSmallestSetOfVertices_LCDS(int n, List<List<Integer>> edges) {

        // Calculate the indegree of each vertex by counting incoming edges.
        int[] indegree = new int[n];
        for (List<Integer> edge: edges) {
            // Increment the indegree of the vertex pointed to by the second element of the edge pair - remember the input way - "1st: array of edges"
            indegree[edge.get(1)]++;
        }

//        iterate on all vertices - if got 0 indegree add them to ans.
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                ans.add(i);
            }
        }
//        complexity ??? think its time: On+e, and O1n space.
        return ans;
    }



    public List<Integer> findSmallestSetOfVertices_LCPage(int n, List<List<Integer>> edges) {
        // List to signify if the vertex(=node) has an incoming edge or not.
        boolean[] isIncomingEdgeExists = new boolean[n];
        for (List<Integer> edge : edges) {
            isIncomingEdgeExists[edge.get(1)] = true;
        }

        List<Integer> requiredNodes = new ArrayList();
        for (int i = 0; i < n; i++) {
            // Store all the nodes that don't have an incoming edge.
            if (!isIncomingEdgeExists[i]) {
                requiredNodes.add(i);
            }
        }
//      time: O(1n+e)- We first iterate over every one of E edges to mark the vertex in the list, then we iterate over the vertices to see if it's required or not.
//      space:O1n-only to store the list. output list does not count.
        return requiredNodes;
    }
}
