import java.util.Stack;

//instruction:
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

/*problem:
* implement min stack - regular stack but with 1 unique: getMin==show min element so far.
* constraint: must use O1! so cant use regular heap).
* constraint: Methods pop, top and getMin operations will always be called on non-empty stacks. */

/*3 solution: - best like first 2.
1st: using stack<int[]>
2nd: using 2 simple stacks
3rd: using 2 stack<int[]> - didnt like here for study.
4th: as 3, improved space
GA! */


public class B6_MinStackImplement_155 {
    public static void main(String[] args){
        MinStack obj_155 = new MinStack();

        obj_155.push(-2);
        obj_155.push(0);
        obj_155.push(-3);
        System.out.println("get min: " + obj_155.getMin());
        obj_155.pop();
        System.out.println("top: " + obj_155.top());
        System.out.println("get min: " + obj_155.getMin());
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

class MinStack {
/*intuition:
* use stack DS. all remain the same.
* but, whenever push - also add the min value so far so can extract it easily.*/
    //1st: Stack of pairs <cur val/ Minimum val so far>
    Stack<int[]> st = new Stack<>(); //int[] == pair:<number, Minimum val so fa>
    public MinStack() {   }//nothing to do in that constructor.. give as signature

    public void push(int val) { //asign <cur value, min value so far>
        if (st.empty()){
            /* If the stack is empty, then the min value
         * must just be the first value we add. */
            st.push(new int[] {val,  val} );
        }else {
            int t = Math.min(val, st.peek()[1]);
            st.push(new int[]{val, t});
        }
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek()[0];
    }

    public int getMin() {
        return st.peek()[1];
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

/*intuition:
* 2 regular stack: the second allways show the min value so far. each pop and push update.*/
// 2nd: Two Stacks - 2nd stack is for min values only.
//    Stack <Integer> st_main = new Stack<>(); //hold all numbers
//    Stack <Integer> st_minvalues = new Stack<>(); //hold only min values
//
//    public MinStack() {    }//nothing to do in that constructor.. give as signature
//
//    public void push(int val) {
//        st_main.push(val);
//        if (st_minvalues.empty() || val <= st_minvalues.peek()){
//            st_minvalues.push(val);
//        }
//    }
//
//    public void pop() {
//        int t = st_main.pop();
//        if (st_minvalues.peek().equals(t)){
//            st_minvalues.pop();
//        }
//    }
//
//    public int top() {
//        return st_main.peek();
//    }
//
//    public int getMin() {
//        return st_minvalues.peek();
//    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    // 3rd: Improved Two Stacks
////    class MinStack {
//
//        private Stack<Integer> stack = new Stack<>();
//        private Stack<int[]> minStack = new Stack<>(); //int[] == <value, number of occurences>
//
//        public MinStack() { }
//
//        public void push(int x) {
//
//            // always put the number onto the main stack.
//            stack.push(x);
//
//            // If the min stack is empty, or this number is smaller than
//            // the top of the min stack, put it on with a count of 1.
//            if (minStack.isEmpty() || x < minStack.peek()[0]) {
//                minStack.push(new int[]{x, 1});
//            }
//
//            // Else if this number is equal to what's currently at the top
//            // of the min stack, then increment the count at the top by 1.
//            else if (x == minStack.peek()[0]) {
//                minStack.peek()[1]++;
//            }
//        }
//
//        public void pop() {
//
//            // If the top of min stack is the same as the top of stack
//            // then we need to decrement the count at the top by 1.
//            if (stack.peek().equals(minStack.peek()[0])) {
//                minStack.peek()[1]--;
//            }
//
//            // If the count at the top of min stack is now 0, then remove
//            // that value as we're done with it.
//            if (minStack.peek()[1] == 0) {
//                minStack.pop();
//            }
//
//            // And like before, pop the top of the main stack.
//            stack.pop();
//        }
//
//
//        public int top() {
//            return stack.peek();
//        }
//
//
//        public int getMin() {
//            return minStack.peek()[0];
//        }
//    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

////     Approach 3: Improved Two Stacks - require less space.
//    private Stack<Integer> stack = new Stack<>();
//    private Stack<int[]> minStack = new Stack<>(); //int[] == <value, number of occurences>
//    public MinStack() { }
//
//    public void push(int x) {
//        // We always put the number onto the main stack.
//        stack.push(x);
//
//        // If the min stack is empty, or this number is smaller than
//        // the top of the min stack, put it on with a count of 1.
//        if (minStack.isEmpty() || x < minStack.peek()[0]) {
//            minStack.push(new int[]{x, 1});
//        }
//        // Else if this number is equal to what's currently at the top
//        // of the min stack, then increment the count at the top by 1.
//        else if (x == minStack.peek()[0]) {
//            minStack.peek()[1]++;
//        }
//    }
//
//    public void pop() {
//        // If the top of min stack is the same as the top of stack
//        // then we need to decrement the count at the top by 1.
//        if (stack.peek().equals(minStack.peek()[0])) {
//            minStack.peek()[1]--;
//        }
//        // If the count at the top of min stack is now 0, then remove
//        // that value as we're done with it.
//        if (minStack.peek()[1] == 0) {
//            minStack.pop();
//        }
//        // And like before, pop the top of the main stack.
//        stack.pop();
//    }
//
//    public int top() {
//        return stack.peek();
//    }
//
//    public int getMin() {
//        return minStack.peek()[0];
//    }
}