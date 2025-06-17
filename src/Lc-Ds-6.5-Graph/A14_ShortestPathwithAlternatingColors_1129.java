import java.util.*;
class State_c {
    int node;
    int color;
    int steps;
    State_c(int node, int color, int steps) {
        this.node = node;
        this.color = color;
        this.steps = steps;
    }
}

public class A14_ShortestPathwithAlternatingColors_1129 {
    int RED = 0;
    int BLUE = 1;
    Map<Integer, Map<Integer, List<Integer>>> graph = new HashMap<>();

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        graph.put(RED, new HashMap<>());
        graph.put(BLUE, new HashMap<>());

        addToGraph(RED, redEdges, n);
        addToGraph(BLUE, blueEdges, n);

        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        Queue<State_c> queue = new LinkedList<>();
        queue.add(new State_c(0, RED, 0));
        queue.add(new State_c(0, BLUE, 0));

        boolean[][] seen = new boolean[n][2];
        seen[0][RED] = true;
        seen[0][BLUE] = true;

        while (!queue.isEmpty()) {
            State_c curstate = queue.remove();
            int node = curstate.node, color = curstate.color, steps = curstate.steps;
            ans[node] = Math.min(ans[node], steps);

            for (int neighbor: graph.get(color).get(node)) {
                if (!seen[neighbor][1 - color]) {
                    seen[neighbor][1 - color] = true;
                    queue.add(new State_c(neighbor, 1 - color, steps + 1));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (ans[i] == Integer.MAX_VALUE) {
                ans[i] = -1;
            }
        }
//        time ans space: O(1n + 1e) - e=edges.
        return ans;
    }
    public void addToGraph(int color, int[][] edges, int n) {
        for (int i = 0; i < n; i++) {
            graph.get(color).put(i, new ArrayList<>());
        }

        for (int[] edge: edges) {
            int x = edge[0], y = edge[1];
            graph.get(color).get(x).add(y);
        }
    }
}
