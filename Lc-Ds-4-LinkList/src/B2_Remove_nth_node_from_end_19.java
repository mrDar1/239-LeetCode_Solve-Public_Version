//import java.util.*;

/* solutions:
1st - 2 pass: first pass count nodes, second traverse num_of_nodes/2 - 1 and delete it.
2nd - 1 pass - use fast and slow ptr */

public class B2_Remove_nth_node_from_end_19 {
    public static class ListNode {
        int val;
        ListNode next;
        //        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(-1);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        head.next = one;
        head.next.next = two;
//        head.next.next.next = null;
        head.next.next.next = three;
        head.next.next.next.next = four;
        head.next.next.next.next.next = five;
        head.next.next.next.next.next.next = null;

        PrintList(one);
        System.out.println("after removing the Nth number:");
        PrintList(removeNthFromEnd_onepass(one, 5));
    }

    public static void PrintList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(", ");
            } else {
                System.out.print(".");
            }
            current = current.next;
        }
        System.out.println(" ");
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    * traverse list and count nodes.
    * then traverse again with c-n+1 and delete it*/
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(Integer.MAX_VALUE, head); //using sentinel will help us avoid some edge cases like delete itself.
        ListNode dummy = head;
        int length = 0; //count length of LL.

        while (dummy != null) {
            ++length;
            dummy = dummy.next;
        }
        dummy = sentinel;
        length = length - n;

        while(length > 0){
            --length;
            dummy = dummy.next;
        }
        dummy.next = dummy.next.next;

//        time: O2n - first find length, 2nd-delete.
//        space: O1
        return sentinel.next; //if we delete the first element - that way will return null in much more elegant way.
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo for 1 pass:
     * traverse list with fast and slow ptr: fast is faster in n times!
     * when fast at end - use slow to delete! */
    public static ListNode removeNthFromEnd_onepass(ListNode head, int n) {
        ListNode sentinel = new ListNode(Integer.MAX_VALUE, head); //using sentinel will help us avoid some edge cases, like delete itself.
        ListNode fast = sentinel;
        ListNode slow = sentinel;

//        advance fast n times.
        for (int n_coppy = n; n_coppy > 0; n_coppy--) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

//        time: O1n - each node travserse at most 1.
//        space: O1
        return sentinel.next;
    }
}