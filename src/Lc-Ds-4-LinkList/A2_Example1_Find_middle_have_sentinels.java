/* note about list with equal number of nodes:
if we have 2 middle, as in 6 nodes, the middle are both 2,3 nodes - what do we choose?
- here use 2 different method - first will return only the 2 node, second return only the 3 node.

what happen when the middle is unique? as in 5 nodes so the middle is just the 3 node!!
both will return it no problem!!
play with it!
 */

/*note about sentinel:
 * when use/not use sentinel - only the printlist change however find_middle remain the same!! */

public class A2_Example1_Find_middle_have_sentinels {
    public static class ListNode {
        int val;
        ListNode next;
        //ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        //ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-1); //head sentinel
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode tail = new ListNode(-1); //tail sentinel

        head.next = one;
        head.next.next = two;
        head.next.next.next = three;
        head.next.next.next.next = four;
        head.next.next.next.next.next = five;
        head.next.next.next.next.next.next = six;
//        head.next.next.next.next.next.next.next = tail;
//        head.next.next.next.next.next.next.next.next = null;
        head.next.next.next.next.next.next = tail;
        head.next.next.next.next.next.next.next = null;

        PrintList(head);
        System.out.println("\nmiddle:" + FindMiddle_first_of_pair(head).val);
        System.out.println("\nmiddle:" + FindMiddle_second_of_pair(head).val);
    }

    public static void PrintList(ListNode head) {
        ListNode dummy = head.next;

        for (; dummy.next != null; dummy = dummy.next) {
            if (dummy.next.next == null) {
                System.out.print(dummy.val + ".");
            } else {
                System.out.print(dummy.val + ", ");
            }
        }
        System.out.println(" ");
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static ListNode FindMiddle_first_of_pair(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while ( fast.next != null && fast.next.next != null ){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static ListNode FindMiddle_second_of_pair(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while ( fast != null && fast.next != null ){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}

