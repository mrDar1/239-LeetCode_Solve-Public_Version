import java.util.*;

/* problem: given graph, directed acyclic graph (DAG)  - return all ways from start till end.
	graph format as - 2nd: adjacency list- most popular. do not require pre-precess.*/

/*solutions:
1st - DFS with global vars (use Stack for path, mostly for fun. can be done with linkList, ArrayList etc..)
2nd - DFS with global vars (use linkList for path, mostly for fun. can be done with Stack, ArrayList etc..)
3rd - DFS no global vars   (use ArrayList for path, mostly for fun. can be done with Stack, linkList etc..)
4th - top down Dynamic programing - this approach may not fit here the best way */


/*      complexity (for all solutions): long math! here is approximately:
        time: O2^n * n :
              2^(n-1) - at worst edge case: max possible path's at graph (with each new node, all paths could be created again  the newly-added node).
              1n - for each new path: of n nodes - number of nodes traversing are (n-2)==n.
        space: O 2n:
              O1n-each recursion call stack,
              O1n-store each "cur" path (do not count output solution, so when add to ans for big O, its free the space.)
              if do count output result:
              (2^(n-1))-1: if all possible paths are valid. */


class A4_AllPathsFromSourcetoTarget_797_global_vars_with_stack {
    private int targetNode;
    private int[][] graph;
    private List<List<Integer>> ans;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) { //start here
        this.graph = graph;
        this.targetNode = graph.length - 1; //work since its graph format as - 2nd: adjacency list
        this.ans = new ArrayList<List<Integer>>();

        Stack<Integer> path = new Stack<>();
        path.push(0);

        backtrack(0, path);
        return this.ans;
    }
    protected void backtrack(int currNode, Stack<Integer> curPath) {
//        base case - reach end successfully
        if (currNode == this.targetNode) {
            this.ans.add(new ArrayList<Integer>(curPath)); ////why use new? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

        for (int nextNode : this.graph[currNode]) {
            curPath.push(nextNode);
            this.backtrack(nextNode, curPath);
            curPath.pop();
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A4_AllPathsFromSourcetoTarget_797_global_vars_with_linkedlist {
    private int targetNode;
    private int[][] graph;
    private List<List<Integer>> ans;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) { //start here
        this.graph = graph;
        this.targetNode = graph.length - 1; //work since its graph format as - 2nd: adjacency list
        this.ans = new ArrayList<List<Integer>>();

        LinkedList<Integer> path = new LinkedList<Integer>();
        path.addLast(0);

        backtrack(0, path);
        return this.ans;
    }
    protected void backtrack(int currNode, LinkedList<Integer> curPath) {
//        base case - reach end successfully
        if (currNode == this.targetNode) {
            this.ans.add(new ArrayList<Integer>(curPath)); ////why use new? -when adding to ans, must create a copy of curr. because else would only use same reference to all next ans.add(curr)
            return;
        }

        for (int nextNode : this.graph[currNode]) {
            curPath.addLast(nextNode);
            this.backtrack(nextNode, curPath);
            curPath.removeLast();
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*intuition:
* problem: add all paths to reach last node.
* from each node, traverse all child nodes - if reach keep exploring may have more ways from that node.
* at next node repeat process again. */
class A4_AllPathsFromSourcetoTarget_797_no_global_vars{
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> curPath = new ArrayList<>();
        backtrack(0, graph ,ans, curPath);
        return ans;
    }

    private void backtrack(int i, int[][] graph, List<List<Integer>> ans , ArrayList<Integer> curPath) {
        int targetNode = graph.length - 1; //work since its graph format as - 2nd: adjacency list
        curPath.add(i);

//        base case - reach end successfully
        if (i == targetNode){
            ans.add(new ArrayList<>(curPath) );
            return;
        }

//        explore all child nodes from curNode
        for (int j : graph[i]){
            backtrack( j, graph, ans, curPath );
            curPath.remove(curPath.size() - 1);
        }
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class A4_AllPathsFromSourcetoTarget_797_Top_Down_Dynamic_programing {
    private int targetNode;
    private int[][] graph;
    private HashMap<Integer, List<List<Integer>>> memo;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) { //START HERE
        this.targetNode = graph.length - 1; //work since its graph format as - 2nd: adjacency list
        this.graph = graph;
        this.memo = new HashMap<>();

        return this.topDownDP(0);
    }

    protected List<List<Integer>> topDownDP(int currNode) {
        // 1st - check if contain at memoization.
        if (memo.containsKey(currNode))
            return memo.get(currNode);


        List<List<Integer>> ans = new ArrayList<>();

//        2nd - base case - reach end successfully
        if (currNode == this.targetNode) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(targetNode);
            ans.add(path);
            return ans;
        }

        // iterate through the paths starting from each neighbor.
        for (int nextNode : this.graph[currNode]) {
            for (List<Integer> path : topDownDP(nextNode)) {
                ArrayList<Integer> newPath = new ArrayList<>();
                newPath.add(currNode);
                newPath.addAll(path);
                ans.add(newPath);
            }
        }
        memo.put(currNode, ans);
        return ans;
    }
}