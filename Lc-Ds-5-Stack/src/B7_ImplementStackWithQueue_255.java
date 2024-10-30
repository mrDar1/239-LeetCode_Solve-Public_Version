import java.util.LinkedList;
import java.util.Queue;

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
/*problem:
* implement stack using Queue.
* constraint: All the calls to pop and top are valid == no pop/top on empty stack!*/

/*solutions:
1st - each push - as regular q, and save the last-enter-element for top(), when pop() - moove all to other q - exept for the last element and poll it.
2nd - when push - poll q to itself - so the last to enter is first to exit! all other the same. */
public class B7_ImplementStackWithQueue_255 {
    public static void main(String[] args) {
        MyStack_255 obj_255 = new MyStack_255();
        obj_255.push(1);
        obj_255.push(2);
        System.out.println("Top element: " + obj_255.top()); // Output: 2
        System.out.println("Popped element: " + obj_255.pop()); // Output: 2
        System.out.println("Is stack empty? " + obj_255.empty()); // Output: false
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */

/*

Approach #2 (Two Queues, push - On, pop O1
add to q2 and save as top.
empty q1 inside q2.
to avoid re copy all - we now swap q1 and q2.

Approach #3 (One Queue, push - On, pop O1
*/


class MyStack_255 {
/*psudo:
psudo: when push keep topelment for later fast access.
when pop - moove all q1 to q2 exept for last - then remove that last.
to avoid re copy all - swap q1 and q2. */

//    1st: (Two Queues, push - O1, pop - On
//    Queue<Integer> q1;
//    Queue<Integer> q2;
//    int topelement;
//    public MyStack_255() {
//        q1 = new LinkedList<>();
//        q2 = new LinkedList<>();
//        topelement = Integer.MIN_VALUE;
//    }
//
//    public void push(int x) {
//        q1.offer(x);
//        topelement = x;
//    }
//
//    public boolean empty() {
//        return q1.isEmpty();
//    }
//
//    public int pop() {
//        //moove all to q2, each time update topelement - when at last is the right one:
//        while ( q1.size() > 1){
//            topelement = q1.poll();
//            q2.offer(topelement);
//        }
//        int poped_obj = q1.poll(); //constraint: no pop/peek at empty.
//
//        //to save time - do not copy again - just swap names q1 &q2:
//        Queue<Integer> temp = q1;
//        q1 = q2;
//        q2 = temp;
//
//        return poped_obj;
//    }
//
//    public int top() {
//        return topelement;
//    }

/*intuition:
* has single q:
* each push - we q.offer
* then we empty q into himself - what cause him to be like stack.
* cost 2n+1 - but then all other command as stack O1
*
* all methods work same as regular stack. the magic happens at push():
* each push use q.offer(q.poll()) - so the pushed element become the first to pop!*/
//    3rd: (One Queue, push - On, pop O1
    Queue<Integer> q = new LinkedList<>();
    public MyStack_255() {    }

    public void push(int x) {
        q.offer(x);

        for (int i = 0; i < q.size()  - 1; i++) {
            q.offer(q.poll());
        }
    }

    public int pop() {
        return q.poll();  //constraint: no pop/peek at empty.
    }

    public int top() {
        return q.peek();  //constraint: no pop/peek at empty.
    }

    public boolean empty() {
        return q.isEmpty();
    }
}

