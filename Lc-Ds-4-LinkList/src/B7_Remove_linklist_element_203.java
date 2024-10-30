//import java.util.*;
public class B7_Remove_linklist_element_203 {
    public static class ListNode {
        int val;
        ListNode next;

        //        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode six1 = new ListNode(6);
        ListNode six1again = new ListNode(6);
        ListNode six1againagain = new ListNode(6);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six2 = new ListNode(6);

        one.next = two;
        one.next.next = six1;
        one.next.next.next = six1again;
        one.next.next.next.next = six1againagain;
        one.next.next.next.next.next = three;
        one.next.next.next.next.next.next = four;
        one.next.next.next.next.next.next.next = five;
        one.next.next.next.next.next.next.next.next = six2;
        one.next.next.next.next.next.next.next.next.next = null;


        PrintList(one);
        PrintList(removeElements(one, 6));
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

    /*psudo: use sentinel, prev and cur.
    * each time advance cur and if == val then prev.next = cur.next */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(-1, head); // get rid of annoying edges
        ListNode prev = sentinel;
        ListNode cur = head;

        while (cur != null){
            if (cur.val == val){
                prev.next = cur.next;
            }else{
                prev = cur;
            }
            cur = cur.next;
        }
//        time: O1n
//        space: O1
        return sentinel.next;
    }
}