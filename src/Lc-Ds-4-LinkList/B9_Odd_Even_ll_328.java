/*problem:
* Sort LL: all odd number node front, only then even number odd.
* other than that order remain the same.*/

public class B9_Odd_Even_ll_328 {
    public static class ListNode {
        int val;
        ListNode next;

        //        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
//        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);

        one.next = two;
        one.next.next = three;
        one.next.next.next = four;
        one.next.next.next.next = five;
        one.next.next.next.next.next = six;
        one.next.next.next.next.next.next = null;

        PrintList(one);

        PrintList(oddEvenList(one));
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

    /* psudo:
    * work with several pointers: 1traverse with even' odd and even head (for later concatanating).
    * each time skip the next one. */
    public static ListNode oddEvenList(ListNode head) {

        if (head==null) return null; // edge case (must be before vars, else got problem

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenhead = head.next;

        while (even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
//        connect odd List to Even list (at this point,
//        both odd and even ptrs, wil be at last node and node.next points to null):
        odd.next = evenhead;

//        time: O1n
//        space: O1
        return head;
    }
}