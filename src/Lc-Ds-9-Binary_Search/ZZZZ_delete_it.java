////problem: effort== |<cur location> - <next location>|
////find the smallest effort way to traverse from 0,0 till last place.
////can moove only left/right/up/down.
//
///*psudo:
//* 1-use left boundary==0
//* 2-right boundary==highest val at int[][]. how to find it? traverse all int[][]
//* 3-do a binary search: from each left til right:
//* if we succeed-then try again with smaller value - for 'effort'.
//* if we fail - try again with bigger value - for 'effort'
//* 4 at each mid-number we try to walk the path and check if its possible or not how we do it:
//*       1-use Pair class - to represent locations int[x][y]
//*       2-use seen[][] - on each location to check if saw it.
//*       3-mooving as in BFS-each time pop the last Pair.
//*       4-if we at last (int[m-1][n-1] - return true)
//*       5-for loop to all possible directions:
//*           1-if curr effort > given effort(=mid) break - find diffrent path.
//*              else: add to seen[][] + push value to next round.*/
//
//import java.util.Stack;
//public class A7_PathWithMinimumEffort_1631 {
//    int m;//represent rows len
//    int n;//represent column len
//    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//
//    public int minimumEffortPath(int[][] heights) {
//        m = heights.length;
//        n = heights[0].length;
//        int left = 0;
//
//        int right = 0;
//        for (int[] row: heights) {
//            for (int num : row) {
//                right = Math.max(right, num);
//            }
//        }
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//
//            if (check(mid, heights)) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//
//        return left;
//    }
//
//    public boolean check(int effort, int[][] heights) {
//        Stack<Pair> stack = new Stack<>();
//
//        boolean[][] seen = new boolean[m][n];
//        seen[0][0] = true;
//        stack.push(new Pair(0, 0));
//
//        while (!stack.empty()) {
//            Pair pair = stack.pop();
//            int row = pair.row;
//            int col = pair.col;
//
//            if (row == m - 1 && col == n - 1) {
//                return true;
//            }
//
//            for (int[] direction : directions) {
//                int nextRow = row + direction[0];
//                int nextCol = col + direction[1];
//
//                if (valid(nextRow, nextCol) && !seen[nextRow][nextCol]) {
//                    if (Math.abs(heights[nextRow][nextCol] - heights[row][col]) <= effort) {
//                        seen[nextRow][nextCol] = true;
//                        stack.push(new Pair(nextRow, nextCol));
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    public boolean valid(int row, int col) {
//        return 0 <= row && row < m && 0 <= col && col < n;
//    }
//}
//class Pair {
//    int row;
//    int col;
//    Pair(int row, int col) {
//        this.row = row;
//        this.col = col;
//    }
//}