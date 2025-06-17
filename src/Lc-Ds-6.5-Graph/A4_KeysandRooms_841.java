import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/*3 solution: 1-LCds recursion, 2-Lcds-iterates stack, 3-Lc page - iterate stack*/
//input form 2nd: array of adjancy Lists
// the first 2 - are my practice self implement.

public class A4_KeysandRooms_841 {
//    my implement - recursion that update global vars.
    Set<Integer> seenset = new HashSet<>();
    public boolean canVisitAllRooms_myimplement_recursion(List<List<Integer>> rooms) {
        seenset.add(0);
        mydfs(rooms, 0);//dfs to explore all rooms which we got keys from current room.
        return seenset.size() == rooms.size();
    }
    private void mydfs(List<List<Integer>> rooms, int n) {
        for (int neighbor : rooms.get(n)){ //for each room - rum on all the keys in it / run on each internal arr.
            if ( !seenset.contains(neighbor)){
                seenset.add(neighbor);
                mydfs(rooms, neighbor);
            }
        }
    }


    public boolean canVisitAllRooms_myimplement_iteration(List<List<Integer>> rooms) {
        Set<Integer> iterateseenset = new HashSet<>();
        Stack<Integer> st = new Stack<>(); //rum on all the keys at current room.
        st.push(0);
        iterateseenset.add(0);

        while ( !st.isEmpty()){
            int node = st.pop();

            for (int i : rooms.get(node)){
                if ( !iterateseenset.contains(i)){
                    iterateseenset.add(i);
                    st.push(i);
                }
            }
        }
        return iterateseenset.size() == rooms.size();
    }




    //    LCds first solution - recursion DFS
    Set<Integer> seen = new HashSet<>();
    public boolean canVisitAllRooms_recursion(List<List<Integer>> rooms) {
        seen.add(0);
        dfs(0, rooms);
        return seen.size() == rooms.size();
//        time: O(1n+e) iterates once on every node, e-number of nodes to visit from each node.
//        space: O2n - 1-to store seen set. 2-recursion call stack
        }

    public void dfs(int node, List<List<Integer>>rooms) {
        for (int neighbor: rooms.get(node)) {
            if (!seen.contains(neighbor)) {
                seen.add(neighbor);
                dfs(neighbor, rooms);
            }
        }
    }


//    Lc-ds course iterate way.
    public boolean canVisitAllRooms_iterate_way(List<List<Integer>> rooms) {
        Set<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        seen.add(0);
        stack.push(0);

        while (!stack.empty()) {
            int node = stack.pop();

            for (int neighbor: rooms.get(node)) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
//        complexity - same as above.
//        time: O(1n+e) iterates once on every node, e-number of nodes to visit from each node.
//        space: O2n - 1-to store seen set. 2-stack.
        return seen.size() == rooms.size();
    }


// 3rd solution - iterate stack from page.
    public boolean canVisitAllRooms_page_solution(List<List<Integer>> rooms) {
        boolean[] seen = new boolean[rooms.size()];
        seen[0] = true;
        Stack<Integer> stack = new Stack();
        stack.push(0);

        //At the beginning, we have a todo list "stack" of keys to use.
        //'seen' represents at some point we have entered this room.
        while (!stack.isEmpty()) { // While we have keys...
            int node = stack.pop(); // Get the next key 'node'

            for (int nei: rooms.get(node)) // For every key in room # 'node'...
                if (!seen[nei]) { // ...that hasn't been used yet
                    seen[nei] = true; // mark that we've entered the room
                    stack.push(nei); // add the key to the todo list
                }
        }

        for (boolean v: seen)  // if any room hasn't been visited, return false
            if (!v) return false;
        return true;
    }
}
