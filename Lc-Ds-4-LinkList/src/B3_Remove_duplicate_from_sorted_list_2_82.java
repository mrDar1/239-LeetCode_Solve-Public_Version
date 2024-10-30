/*problem:
* remove all duplicates nodes
* note: not jut the duplicates but also itselft!
* example: 1,2,2,3 -> 1,3*/

public class B3_Remove_duplicate_from_sorted_list_2_82 {
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
        ListNode head = new ListNode(-1);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three1 = new ListNode(3);
        ListNode three2 = new ListNode(3);
        ListNode four1 = new ListNode(4);
        ListNode four2 = new ListNode(4);
        ListNode five = new ListNode(5);

        head.next = one;
        head.next.next = two;
        head.next.next.next = three1;
        head.next.next.next.next = three2;
        head.next.next.next.next.next = four1;
        head.next.next.next.next.next.next = four2;
        head.next.next.next.next.next.next.next = five;

        PrintList(one);
        PrintList(deleteDuplicates(one));
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

    /*psudo: traverse with prev and cur.
    * if cur.val==cur.next.val - while:
    * cur.val==cur.next.val
    * cur = cur.next
    * when finish - prev advance all and its the new cur.*/
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head); //using sentinel will remove a lot of edge cases handling.
        ListNode prev = sentinel;
        ListNode cur = head;

        while (cur != null){
            if (cur.next!=null && cur.val==cur.next.val){
                while (cur.next!=null && cur.val==cur.next.val) {
                    cur = cur.next;
                }
                prev.next = cur.next;
            }
            else {
                prev = prev.next;
            }

            cur = cur.next;
        }

//        time: O1n
//        space: O1
        return sentinel.next;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */




    /*psudo: traverse with prev and cur.
     * if cur.val==cur.next.val - while:
     * cur.val==cur.next.val
     * cur = cur.next
     * when finish - prev advance all and its the new cur.*/
    public static ListNode deleteDuplicates_practice_again(ListNode head) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head); //will save edge cases - as in delete first element, or empty list.
        ListNode prev = sentinel;
        ListNode cur = head;

        while (cur != null && cur.next != null){

            if (cur.val == cur.next.val){
                while (cur.next != null && cur.val == cur.next.val){
                    cur.next = cur.next.next;
                }
                prev.next = cur.next;
            }else {
                prev = prev.next;
            }
            cur = cur.next;
        }
//        time: O1n
//        space: O1
        return sentinel.next; //will save edge cases - as in delete first element, or empty list.
    }
}