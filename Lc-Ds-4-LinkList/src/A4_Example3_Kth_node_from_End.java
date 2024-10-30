/*problem:
given head node and number k:
return k nodes from end.*/

    public class A4_Example3_Kth_node_from_End {
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
//        head.next.next.next.next.next.next = tail;
//        head.next.next.next.next.next.next.next = null;

        PrintList(head);
        System.out.println(Kth_Node_From_End(head, 2).val);

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

    /*psudo:
    advance fast ptr k times,
    then traverse list and advance both - when reach end - return slow */
    public static ListNode Kth_Node_From_End(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
//        usually this enough:
//            while (fast != null) {
//        but since we have sentinel - bit diffrent.
        while ( fast.next != null && fast.next.next != null ){
            fast = fast.next;
            slow = slow.next;
        }
//        time: On+k: k-advance k ptr; n-reach end
//        space: O1
        return slow;
    }
}