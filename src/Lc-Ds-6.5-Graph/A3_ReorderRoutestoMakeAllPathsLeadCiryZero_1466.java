import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

//didnt get that one too..

//all are directed edges.
//input form - 1st: array of edges
public class A3_ReorderRoutestoMakeAllPathsLeadCiryZero_1466 {
    Set<String> roads = new HashSet<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Set<Integer> seen = new HashSet<>();

    public int minReorder(int n, int[][] connections) { // start here
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] connection: connections) {
            int x = connection[0];
            int y = connection[1];

            graph.get(x).add(y);
            graph.get(y).add(x);
            roads.add(convertToHash(x, y));
        }

        seen.add(0);
        return dfs(0);
    }

    public int dfs(int node) {
        int ans = 0;
        for (int neighbor: graph.get(node)) {
            if (!seen.contains(neighbor)) {
                if (roads.contains(convertToHash(node, neighbor))) {
                    ans++;
                }

                seen.add(neighbor);
                ans += dfs(neighbor);
            }
        }

        return ans;
    }

    public String convertToHash(int row, int col) {
        return String.valueOf(row) + "," + String.valueOf(col);
    }
}