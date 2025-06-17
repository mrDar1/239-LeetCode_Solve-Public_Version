/* problem:
delete all duplicates!
given sorted array! */
public class A6_Remove_Duplicates_83 {
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
        ListNode one1 = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode three1 = new ListNode(3);
        ListNode tail = new ListNode(-1);

        head.next = one;
        head.next.next = one1;
        head.next.next.next = two;
        head.next.next.next.next = three;
        head.next.next.next.next.next = three1;
        head.next.next.next.next.next.next = tail;
        head.next.next.next.next.next.next.next = null;

        PrintList(head);
        PrintList(deleteDuplicates(head));
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

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = head;
//      i got dummy, but if not it would look like that:
//      while(dummy != null && dummy.next != null){
        while(dummy.next != null && dummy.next.next != null){
            if(dummy.val == dummy.next.val){
                dummy.next = dummy.next.next;
            }else{
                dummy = dummy.next;
            }
        }
//        time: On space: O1
        return head;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */














    /*psudo:
    * traverse with dummy until end of ll,
    * if val==next.val - delete it and "continue".
    * else advance dummy */
    public static ListNode deleteDuplicates_practice_again(ListNode head) {
        ListNode dummy = head;

        while (dummy != null && dummy.next!=null){
            if (dummy.val == dummy.next.val){
                dummy.next = dummy.next.next;
            }else {
                dummy = dummy.next;
            }
        }
        return head;
    }
}
