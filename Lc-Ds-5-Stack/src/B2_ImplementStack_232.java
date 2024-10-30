import java.util.Stack;

/**
 //example of how to run the testing:
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

/*problem:
*  Implement Queue using 2 Stacks */

/* 2 solution
 1st- my first intuitive way - work but high complexity.
 2nd- better complexity - do not flip the main stack each time - only when necessary. */

public class B2_ImplementStack_232 {
    public static void main(String[] args){

        MyQueue obj_232 = new MyQueue();
        System.out.println( "is MyQueue empty? " +  obj_232.empty() );
        obj_232.push(1);
        obj_232.push(2);
        obj_232.push(3);
        System.out.println("the first element:" + obj_232.peek());
        System.out.println("popping element:");
        System.out.println(obj_232.pop());
        System.out.println(obj_232.pop());
        System.out.println(obj_232.pop());
        System.out.println( "is MyQueue empty? " +  obj_232.empty() );
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

// my first time solution - pass LC:
/*intuition:
* always work at main stack, whenever require to work as in q - for first element
* reverse anything to st2, pop/peek and restore order to s1. */
class MyQueue {
    Stack<Integer> stm; //main stack - work here.
    Stack<Integer> st2; //second stack help to reverse
    int size;
    public MyQueue() {
        size = 0;
        stm = new Stack<>();
        st2 = new Stack<>();
    }

    public void push(int x) {
        stm.push(x);
        ++size;
    }

    public int pop() {
        int t = 0;
        if (stm.empty()){
            return -1;
        }else {
            --size;
        }

        while ( !stm.empty()){
            st2.push(stm.pop());
        }

        t = st2.pop();

        while ( !st2.empty()){
            stm.push(st2.pop());
        }

        return t;
    }

    public int peek() {
        int t = 0;

        while ( !stm.empty()){
            st2.push(stm.pop());
        }
        t = st2.peek();

        while ( !st2.empty()){
            stm.push(st2.pop());
        }

        return t;
    }

    public boolean empty() {
        return (size==0);
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

// LC solution
/*short intuition:
* allways keep "front" element.
* s1 - is the main stack.
* s2 - is the reverse so if its with element - first pop/peek from it. */
//class MyQueue {
//    private Stack<Integer> s1 = new Stack<>();
//    private Stack<Integer> s2 = new Stack<>();
//    private int front;
//
//
//    // Push element x to the back of queue.
//    public void push(int x) {
//        if (s1.isEmpty()) {
//            front = x;
//        }
//        s1.push(x);
//    }
//
//    public int pop() {
//        if (s2.isEmpty()) {
//            while (!s1.isEmpty()){
//                s2.push(s1.pop());
//            }
//        }
//        return s2.pop();
//    }
//
//
//    // Return whether the queue is empty.
//    public boolean empty() {
//        return s1.isEmpty() && s2.isEmpty();
//    }
//
//    // Get the front element.
//    public int peek() {
//        if (!s2.isEmpty()) {
//            return s2.peek();
//        }
//        return front;
//    }
}