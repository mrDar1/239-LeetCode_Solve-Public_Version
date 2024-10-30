/*problem:
* swap values of  the k'th node and k'th node from end
* note - no need to swap nodes just the values!!
* the list is 1-indexed
*
* constraint: 1 <= k <= n <= 10^5 - so no enoying edge cases!*/

/*solutions:
* 1st-2 passes - traverse to discover length and again to find 2 nodes to swap.
* 2nd-1 pass use fast and slow ptr
* 3rd-again like 2, for practice.*/
public class B4_Swap_nodes_in_linklist_1721 {
    public static class ListNode {
        int val;
        ListNode next;

        //                ListNode() {}
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
//        ListNode three = new ListNode(3);
//        ListNode four = new ListNode(4);
//        ListNode five = new ListNode(5);

        one.next = two;
        one.next.next = null;
//        one.next.next = three;
//        one.next.next.next = four;
//        one.next.next.next.next = five;
//        one.next.next.next.next.next = null;

        PrintList(one);

        System.out.println("after swap:");
//        PrintList(swapNodes(one, 1));
        PrintList(swapNodes_onepass(one, 1));
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

    /*psudo: traverse ll count length and find left.
     * then, traverse again and put ptr to right.
     * third do the swap*/
    public static ListNode swapNodes(ListNode head, int k) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode dummy = head;
        ListNode left = sentinel;
        ListNode right = sentinel;
        int len = 0;

//        find len of LL and left node:
        while (dummy != null) {
            ++len;
            if (len == k) {
                left = dummy;
            }
            dummy = dummy.next;
        }
//        dummy = head; //initialize to zero here no need cause dont use - but its good practice, i belive.

//        find right node:
        for (int i = 0; i <= len - k; i++) {
            right = right.next;
        }

        Swap2NodesValues(left, right);
//        time: O2n - 1st-find len and left node, 2nd-find right node.
//        space: O1.
        return sentinel.next;
    }

    public static void Swap2NodesValues(ListNode left, ListNode right) {
        int t = left.val;
        left.val = right.val;
        right.val = t;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo: travese using fast and slow ptr to find locations of left and right.
    * then do the swap.*/
    public static ListNode swapNodes_onepass(ListNode head, int k) {
        ListNode dummy = head;
        ListNode left = null;
        ListNode right = null;
        int len = 0;

        while (dummy != null){
            ++len;
            if (right != null){ //note this have to be first! else it would advance right too much!!
                right = right.next;
            }
            if (len == k){
                left = dummy;
                right = head;
            }
            dummy = dummy.next;
        }
        Swap2NodesValues(left, right);
//        time O1n space: O1
        return head;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */






/*psudo:
* traverse "fast" k times until reach left_node_to_swap and save ptr to it.
* now find right_node_to_swap, just keep advancing fast and slow ptr together - when fast reach end - slow
* will be at spot.
*
* swap val.*/
    public ListNode swapNodes_second_time_practice(ListNode head, int k) {
        int k_coppy = k;
        ListNode fast = head;
        ListNode slow = head;
        ListNode left_node_to_swap  = null;
        ListNode right_node_to_swap = null;
        int t = 0; // temp for swap

        if (head == null ){return head;}

        while (k_coppy > 1){ // its 1 and not 0 as its 1-index!!
            fast = fast.next;
            k_coppy--;
        }

        left_node_to_swap = fast;

        while (fast.next != null){ // why fast.next and not just fast? -as it 1-index not 0!!
            fast = fast.next;
            slow = slow.next;
        }

        right_node_to_swap = slow;

//        swap:
        t = left_node_to_swap.val;
        left_node_to_swap.val = right_node_to_swap.val;
        right_node_to_swap.val = t;

//        time:
//        space:
        return head;
    }
}