/*problem:
* times[][]==<source, dest, weight of edge>
* n-number of nodes in graph
* k-the n'th node start send from.
* return-how long till reach all nodes., -1 if not possible.
*
* graph format: 1st: array of edges */

/*use ans from lcds, work at lcpage. but not here in intelij caues didnt have Pair, used GPT for that and testing.*/

//didnt get that so much....
import java.util.*;

public class A15_NetworkDelayTime_743 {
    public static void main(String[] args) {
        Solution_A15_NetworkDelayTime_743 obj_743 = new Solution_A15_NetworkDelayTime_743();

        // Test case 1
        int[][] times1 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n1 = 4;
        int k1 = 2;
        System.out.println("Test case 1: " + (obj_743.networkDelayTime(times1, n1, k1) == 2 ? "Passed" : "Failed"));

        // Test case 2
        int[][] times2 = {{1, 2, 1}};
        int n2 = 2;
        int k2 = 1;
        System.out.println("Test case 2: " + (obj_743.networkDelayTime(times2, n2, k2) == 1 ? "Passed" : "Failed"));

        // Test case 3
        int[][] times3 = {{1, 2, 1}};
        int n3 = 2;
        int k3 = 2;
        System.out.println("Test case 3: " + (obj_743.networkDelayTime(times3, n3, k3) == -1 ? "Passed" : "Failed"));

        // Test case 4
        int[][] times4 = {{1, 2, 1}, {2, 3, 2}, {1, 3, 4}};
        int n4 = 3;
        int k4 = 1;
        System.out.println("Test case 4: " + (obj_743.networkDelayTime(times4, n4, k4) == 3 ? "Passed" : "Failed"));
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

//generic class so that it can be used with any types of objects.
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
//Note: the nodes are labeled 1-indexed. When we build the graph, we will subtract 1 from each node to make them 0-indexed.
/*u==start node
* v==dest node
* w==weight*/
class Solution_A15_NetworkDelayTime_743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int[] edge: times) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            int w = edge[2];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new Pair(v, w));
        }

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);

        Queue<Pair<Integer, Integer>> heap = new PriorityQueue<Pair<Integer,Integer>>(Comparator.comparing(Pair::getKey));
        heap.add(new Pair(0, k - 1));
        distances[k - 1] = 0;

        while (!heap.isEmpty()) {
            Pair<Integer, Integer> curr = heap.remove();
            int currDist = curr.getKey();
            int node = curr.getValue();

            if (currDist > distances[node] || !graph.containsKey(node)) {
                continue;
            }

            for (Pair<Integer, Integer> edge: graph.get(node)) {
                int nei = edge.getKey();
                int weight = edge.getValue();
                int dist = currDist + weight;

                if (dist < distances[nei]) {
                    distances[nei] = dist;
                    heap.add(new Pair(dist, nei));
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, distances[i]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}


