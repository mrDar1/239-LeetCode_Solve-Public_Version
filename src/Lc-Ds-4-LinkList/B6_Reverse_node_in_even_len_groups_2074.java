
/*problem:
* divide to groups: 1 node, 2 node, 3 node ,4 node etc...
* each second group reverse: 2,6,8 and etc... and groups: 1,3,5 - do not reverse!
* if at end remain some nodes - reverse only if teire even number.*/

public class B6_Reverse_node_in_even_len_groups_2074 {
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
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);
        ListNode nine = new ListNode(9);
        ListNode ten = new ListNode(10);
        ListNode eleven = new ListNode(11);
        ListNode twelve = new ListNode(12);

        one.next = two;
        one.next.next = three;
        one.next.next.next = four;
        one.next.next.next.next = five;
        one.next.next.next.next.next = six;
        one.next.next.next.next.next.next = seven;
        one.next.next.next.next.next.next.next = eight;
        one.next.next.next.next.next.next.next.next = nine;
        one.next.next.next.next.next.next.next.next.next = ten;
        one.next.next.next.next.next.next.next.next.next.next = eleven;
        one.next.next.next.next.next.next.next.next.next.next.next = twelve;
        one.next.next.next.next.next.next.next.next.next.next.next.next = null;

        PrintList(one);
        ListNode temp = reverseEvenLengthGroups(one);
        PrintList(temp);
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

    /*intuition:
    * make counter for the group number, and accordingly count number of nodes at that group,
    * whenever the group is even - advance dummy ptr as the numbers of node inside that group, and continue to next group.
    * whenever the group number of nodes is odd then:
    * 1. reverse all node at that group and concatenate to regular LL. */
    public static ListNode reverseEvenLengthGroups(ListNode head) {
        // edge case
        if (head == null || head.next == null || head.next.next == null) return head;

        ListNode dummy = head;
        int group_num = 1;

        while (dummy != null && dummy.next != null) {
            group_num++;

            //count number of nodes at current group
            int countNodes_atgroup = 0;
            for (ListNode temp = dummy.next ; temp != null && countNodes_atgroup < group_num ; temp = temp.next) {
                countNodes_atgroup++;
            }

            // If the number of nodes in the group is even, reverse the group, else skip
            if (countNodes_atgroup % 2 == 0) {
                ListNode curr = dummy.next;
                ListNode prev = null;
                ListNode nextnode = null;

                for (int i = 0; i < countNodes_atgroup; i++) {
                    nextnode = curr.next;

                    curr.next = prev;
                    prev = curr;
                    curr = nextnode;
                }

                // Update pointers to reverse the group
                ListNode tail = dummy.next;
                tail.next = curr;
                dummy.next = prev;
                dummy = tail;
            } else {
                // If the number of nodes in the group is odd, skip the group
                for (int i = 0; i < countNodes_atgroup; i++)
                    dummy = dummy.next;
            }
        }
//        time:
//        space:
        return head;
    }
    public static ListNode ReverseList(ListNode head){ //didnt get how to use it here.
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
