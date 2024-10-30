/*problem:
 given 2 middle - return the second! */
public class A5_Middle_Of_List_876 {
    public static class ListNode {
        int val;
        ListNode next;
        //        ListNode() {}
        ListNode(int val) { this.val = val; }
//        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args){

        ListNode head = new ListNode(-1);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode tail = new ListNode(-1);

        head.next = one;
        head.next.next = two;
        head.next.next.next = three;
        head.next.next.next.next = four;
        head.next.next.next.next.next = five;
        head.next.next.next.next.next.next = six;
        head.next.next.next.next.next.next.next = tail;
        head.next.next.next.next.next.next.next.next = null;

        PrintList(head);
        System.out.println( "the middle node is:" + MiddleNode(head).val );
    }
    public static void PrintList(ListNode head){
        ListNode dummy = head.next;
        for (; dummy.next != null; dummy = dummy.next) {
            if (dummy.next.next == null) {
                System.out.print(dummy.val + ".\n");
            } else {
                System.out.print(dummy.val + ", ");
            }
        }
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static ListNode MiddleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while ( fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
//        time: On. n=given nodes in link list
//        space: O1
        return slow;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static ListNode MiddleNode_practice_again(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}