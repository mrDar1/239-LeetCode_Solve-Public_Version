import java.util.Stack;

public class A12_OnLineStockSpan_901 {
    public static void main(String[] args) {
        // Test cases
        Solution_stockSpanner_901 obj_901  = new Solution_stockSpanner_901();
        System.out.println(obj_901.next(100)); // return 1
        System.out.println(obj_901.next(80));  // return 1
        System.out.println(obj_901.next(60));  // return 1
        System.out.println(obj_901.next(70));  // return 2
        System.out.println(obj_901.next(60));  // return 1
        System.out.println(obj_901.next(75));  // return 4
        System.out.println(obj_901.next(85));  // return 6
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class Solution_stockSpanner_901 {
    Stack<int[]> stack; // Stack to store price-span pairs <value, number from last <= occurence>
    public Solution_stockSpanner_901() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1; // Default span is 1

        // Check if the stack is not empty and the previous prices are less than or equal to the current price
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1]; // Increment the span by the span of the previous price
        }

        stack.push(new int[]{price, span}); // Push the current price and its span onto the stack

//      time: each call to next O1.
//        space: O1n
        return span; // Return the calculated span for the current price
    }
}