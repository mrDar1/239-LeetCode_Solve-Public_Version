/*problem:
* delete just middle node - if got 2 nodes for middle - remove only 2 */

/* solutions:
1st-2 pass: first pass count nodes, second travese num_of_nodes/2 - 1 and delete it.
2nd - 1 pass - use fast and slow ptr */
public class B1_Delete_middle_Of_Linked_List_2095 {
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
        ListNode head = new ListNode(-1);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);

        head.next = one;
        head.next.next = two;
        head.next.next.next = three;
        head.next.next.next.next = four;
        head.next.next.next.next.next = five;
        head.next.next.next.next.next.next = six;
        head.next.next.next.next.next.next.next = null;

        PrintList(one);
        System.out.println("the new list with no middle - (each time use only one!):");
//        PrintList(deleteMiddle(one));
        PrintList(deleteMiddle_one_pass(one));
    }

    public static void PrintList(ListNode head) {
//        ListNode current = head.next; //skip sentinel
        ListNode current = head; //skip sentinel

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
     iterate all node and compute middle, traverse again middle - 1 time and delete. */
    public static ListNode deleteMiddle(ListNode head) {
        ListNode dummy = head;
        int c = 0; //count number of nodes

//        edge cases:
        if (dummy == null || dummy.next == null) {
            return null;
        }
//      count number of nodes in list.
        while (dummy != null){
            ++c;
            dummy = dummy.next;
        }

        dummy = head;
        c = (c / 2) - 1; // will now be the middle number. -1 because we want the node before middle!

//        find location of one_node_before_middle.
        while (c > 0){
            dummy = dummy.next;
            --c;
        }
        dummy.next = dummy.next.next; //delete middle

//        time: O1.5n: 1st find length, 0.5n-delete middle.
//        space: O1.
        return head;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
     * find 1-before-middle by doing regular way and start with fast=fast.next.next*/
    public static ListNode deleteMiddle_one_pass(ListNode head) {
//        edge case:
        if (head == null || head.next == null){
            return null;
        }
//        no need for that line to work!!!! it will  stop at loop condition!
//        if (head.next.next == null){
//            head.next = null;
//            return head;
//        }

        ListNode fast = head.next.next;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
//        time: O1n
//        space: O1.
        return head;
    }
}