import java.util.List;

/*problem:
given singly link list head, left, right - reverse only left-right and keep else same.
constarint: 1<=left<=right<=n - so no worry of some edge cases */

/*solutions:
* 1st-iterative
* 2nd-recursion LC
* 3rd-recursion some guy - much better! */

public class A10_Reverse_linked_list_2_92 {
    public static class ListNode {
        int val;
        ListNode next;
        //        ListNode() {}
        ListNode(int val) { this.val = val; }
//        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args){
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        one.next = two;
        one.next.next = three;
        one.next.next.next = four;
        one.next.next.next.next = five;
        one.next.next.next.next.next = null;

        PrintList(one);
        PrintList(reverseBetween(one, 2, 4));

    }
    public static void PrintList(ListNode head){
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

    /*psudo:
     * traverse list until find "one_before_left", and "node_left" (advancing right, left counters)
     * loop r times: "reverse".
     * connect "one_before_left" to "reverse_head" - 2 options from middle of list/from head.
     * connect the reverse part to continue the list.  */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode cur = head;
        ListNode prev = null;
        ListNode node_before_left = null; //use later to connect to reverse part.
        ListNode first_after_left = null;//use later to connect the reverse part.

        if(head == null) {return null;} //edge case.

//        advancing to left point.
        while (left > 1){
            prev = cur;
            cur = cur.next;
            --left;
            --right;
        }
//        save locations for later:
        node_before_left = prev;
        first_after_left = cur;

//        reverse:
        while (right > 0){
            ListNode next = cur.next;

            cur.next = prev;
            prev = cur;
            cur = next;

            --right;
        }

//        connect the reverse part (if its the head connect the head):
        if (node_before_left != null){
            node_before_left.next = prev; //note now prev is head of the reverse part.
        } else{
            head = prev;
        }

        first_after_left.next = cur; //at this point first_after_left is the tail of the reverse part - so we link it to 'cur' who is now the first after 'right'

        return head;
//        time: O1n - process each of the nodes at most once
//        space: O1n - we simply adjust some pointers in the original linked list
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*2nd - recursion
    intuition:
    * we dont swap node just theire values!*/

    // global vars, to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public ListNode reverseBetween_recursion(ListNode head, int left, int right) {//start here. original signature(note-change left, and right later)
        this.left = head;
        this.stop = false; //intialize with false, whenever it true - stop swaping nodes.
        this.recurseAndReverse(head, left, right);
        return head;
    }

    public void recurseAndReverse(ListNode right, int m, int n) {
        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (this.left == right || this.left == right.next ) { //<odd number for nodes, pair number of nodes.>
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!this.stop) {
            int t = this.left.val;
            this.left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next;
        }
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    3rd - some guy realy good!
    ListNode successor = null;
    public ListNode reverseBetween_better_recursion(ListNode head, int m, int n) {
        if (m == 1) {
            // You can also expand the code here to get rid of the helper function 'reverseN'
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            this.successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = this.successor;
        return last;
    }









    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /* practice again 1st way
    psudo:
    * traverse list until find "one_before_left", and "node_left" (advancing right, left counters)
    * loop r times: "reverse".
    * connect "one_before_left" to "reverse_head" - 2 options from middle of list/from head.
    * connect the reverse part to continue the list.  */
    public static ListNode reverseBetween_again_for_practice_delete(ListNode head, int left, int right) {
        ListNode cur = head;
        ListNode prev = null;
        ListNode one_before_left = null; //use later to connect to reverse part.
        ListNode node_left = null; //use later to connect to reverse part.

//        edge case:
        if (head == null){
            return null;
        }

//        find left_node and one_before_left:
        while (left > 1){ //stop at 1 so catch one_node_before_left
            prev = cur;
            cur = cur.next;
            left--;
            right--;
        }
        one_before_left = prev;
        node_left = cur;

//        traverse curr elements "right" times.
        while (right-- > 0){
            ListNode next = cur.next;

            cur.next = prev;
            prev = cur;
            cur = next;
        }

//        connect the first unreversed part to head of reverse part.
//        2 options: reverse is not from middle of list, reverse from head node.
        if (one_before_left != null){
            one_before_left.next = prev;
        }else {
            head = prev;
        }

        node_left.next = cur; //concatenate the last of reverse part to the original link list.
        return head;
    }
}