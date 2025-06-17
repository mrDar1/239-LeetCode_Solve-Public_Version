/*problem:
* reverse a singly linked list*/

/*solutions:
* 1st-iterative
* 2nd-recursive - hard!! */

public class A7_Example1_Reverse_List {
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
        ListNode tail = new ListNode(-1);

        head.next = one;
        head.next.next = two;
        head.next.next.next = three;
        head.next.next.next.next = four;
        head.next.next.next.next.next = five;
        head.next.next.next.next.next.next = tail;
        head.next.next.next.next.next.next.next = null;

        PrintList(head);
        PrintList(ReverseList_recursive(head));
    }
    public static void PrintList(ListNode head){
        ListNode cur = head.next;

        for(  ; cur != null && cur.next != null ; cur = cur.next){
            System.out.print(cur.val);
            if(cur.next.next != null){
                System.out.print(", ");
            }else {
                System.out.print(".");
            }
        }
        System.out.println(" ");
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static ListNode ReverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null /* && cur.next != null -didnt work for me! sentinel didnt help.. */){
            ListNode nextnode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextnode;
        }
        return prev;
//        time: On space: O1
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    public static ListNode ReverseList_recursive(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode last = ReverseList_recursive(head.next);
        head.next.next = head;
        head.next = null;

//        time: On space: On-recursive call added until reach base case.
        return last;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static ListNode ReverseList_practice(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;

        while (cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}