/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
import java.util.LinkedList;
import java.util.Queue;

/* problem:
send us "ping" in increasing numbers.
each time we want to count how many requests we get in last 3000 time only!
Note, the sequence of calls is ever-increasing, and we are given the call one at a time. */

/*2 solutions:
so we need to remove element from head. how will we do it? using Queue! (implement by link list)

1st - using Q, each time pass 3000 poll.
2nd - use linked-list, same idea different DS. */

public class A6_NumberOfRecentCalls_933 {
    public static void main(String[] args)
    {
        int[] arr = {0,1,100,3001,3002};
        Solution_NumberOfRecentCalls_933 Obj_933 = new Solution_NumberOfRecentCalls_933();

        System.out.println("first way:");
        for(int i : arr){
            System.out.print(Obj_933.ping(i) + ", ");
        }

        System.out.println(" ");
        System.out.println("second way:");
        Solution_NumberOfRecentCalls_933_secondway Obj_933_2 = new Solution_NumberOfRecentCalls_933_secondway();
        for (int i : arr){
            System.out.print(Obj_933_2.ping(i) + ", ");
        }

    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class Solution_NumberOfRecentCalls_933 {
    Queue<Integer> queue;
    public Solution_NumberOfRecentCalls_933() {
        this.queue = new LinkedList<>(); // can use here - "queue = new LinkedList<>();" - but not mandatory.
    }

    public int ping(int t){
        while (!queue.isEmpty() && queue.peek() < t - 3000){
            queue.poll(); //remove from head.
        }

        queue.offer(t); // append at tail.
        return queue.size();
        //time:   O1 max 3000 calls in each 3000 time.
        // space: O1 (max-3000)
    }
}

/* ************************************* */
/* ************************************* */
/* ************************************* */
/* ************************************* */


class Solution_NumberOfRecentCalls_933_secondway {
    LinkedList<Integer> slideWindow;

    public Solution_NumberOfRecentCalls_933_secondway() {
        this.slideWindow = new LinkedList<Integer>();
    }

    public int ping(int t) {
        this.slideWindow.addLast(t);

        // step 2). invalidate the outdated pings
        while (this.slideWindow.getFirst() < t - 3000){
            this.slideWindow.removeFirst();
        }

        //time:   O1 max 3000 calls in each 3000 time.
        // space: O1 (max-3000)
        return this.slideWindow.size();
    }
}
