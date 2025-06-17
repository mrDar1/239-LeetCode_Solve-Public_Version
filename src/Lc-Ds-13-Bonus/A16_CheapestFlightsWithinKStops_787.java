/*problem:
flights[][] == <from, to , price>, n==number of cities.
* return price for cheapest flight from "src" to "dst" with max k stops.*/
//graph format: 1st: array of edges.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.PriorityQueue;

public class A16_CheapestFlightsWithinKStops_787 {
    /*intuition:
     * 1st-build adjacency List - at it much easier to view graph that way. <key, List[<dest, cost>]>
     * use PQ: each time check if:
     *   1-prune that path - if cur steps > k || already found cheapest way to that city.
     *   2-if the destintion ciry reach - return sum of costs.
     *   3-if reach ciry with no flights==dead end - prune path.
     *   4-for next round - add all neighbors to PQ.
     *   5-update our number of stops in stops[] to know for next time*/

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        // Populate the adjacency list with edges and their respective costs
        for (int[] i : flights){
            adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new int[] { i[1], i[2] });

////            above "computeIfAbsent" could be replace with:
//              Check if the source node already exists in the map
//            if (!adj.containsKey(i[0])) {
//                // If not, create a new list for the source node
//                adj.put(i[0], new ArrayList<>());
//            }
//            // Add the destination node and the cost to the source node's list
//            adj.get(i[0]).add(new int[] { i[1], i[2] });
        }


        // Array to track the minimum number of stops to each node
        int[] stops = new int[n]; //
        Arrays.fill(stops, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); //min heap
        // {dist_from_src_node, node, number_of_stops_from_src_node}
        pq.offer(new int[] { 0, src, 0 });

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int dist = temp[0]; //represent the cost
            int node = temp[1]; //which city we are now.
            int steps = temp[2];// k count (constraint: cannot reach k stops!)

            // We have already encountered a path with a lower cost and fewer stops,
            // or the number of stops exceeds the limit.
            if (steps > stops[node] || steps > k + 1)
                continue;

            // Update the minimum stops to reach this node
            stops[node] = steps;

            // If the destination node is reached, return the cost
            if (node == dst)
                return dist;

            // Skip if there are no outgoing edges from the current node
            if (!adj.containsKey(node))
                continue;

            // Add neighboring nodes to the priority queue with updated distances and stops
            for (int[] a : adj.get(node)) {
                pq.offer(new int[] { dist + a[1], a[0], steps + 1 });
            }
        }
        // If the destination cannot be reached within k stops, return -1
        return -1;
//        E==number of flights, N==number of cities.
//        time:  O(N+E*K log(E*K)):
//        space: O(N+E*K)
    }
}
