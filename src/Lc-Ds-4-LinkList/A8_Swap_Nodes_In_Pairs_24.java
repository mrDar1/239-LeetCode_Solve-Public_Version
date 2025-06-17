/* 3 solution: (work in leetcode, here didnt adjust testing...)
 1st- recursion
 2nd- iterative from course-short but complicated - good for practicing pointers,
 3rd- iterative from solution more readable */
public class A8_Swap_Nodes_In_Pairs_24 {
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

        one.next = two;
        one.next.next = three;
        one.next.next.next = four;
        one.next.next.next.next = five;
        one.next.next.next.next.next = null;

        System.out.print("original: ");PrintList(one);
//        PrintList(swapPairs(one));
        PrintList(swapPairs_iterative_way_fromcourse(one));
//        PrintList(swapPairs_iterative_way_frompage(one));
    }

    public static void PrintList(ListNode head) {
        ListNode dummy = head;

        while ( dummy != null) {
            System.out.print(dummy.val);
            if (dummy.next != null) {
                System.out.print(", ");
            } else {
                System.out.print(".");
            }
            dummy = dummy.next;
        }
        System.out.println(" ");
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//   1st - using recursion:
    public static ListNode swapPairs(ListNode head) {
        // edge case - If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next; //note - the edge case must be before or wouldnt work!! since head.next may preduce null

        // Swapping
        firstNode.next  = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
//        time & space O1n
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//   2nd - LC solutions from course:
    public static ListNode swapPairs_iterative_way_fromcourse(ListNode head) {
        // edge case: linked list has 0 or 1 nodes, just return
        if (head == null || head.next == null) {
            return head;
        }

        ListNode new_head = head.next;     // Step 5 // ptr to the start of the LL to return at the end after swap! the 2nd in original link-list will be the first after swap!
        ListNode dummy = head;
        ListNode prev = null;           // Initialize for step 3

        while (dummy != null && dummy.next != null) {
            if (prev != null) {
                prev.next = dummy.next;  // Step 4
            }
            prev = dummy;                // Step 3
//            check:
            System.out.println("prev: " + prev.val + " dummy:" + dummy.val);

            // Step 2
            ListNode nextNode = dummy.next.next;
            dummy.next.next = dummy;      // Step 1

            dummy.next = nextNode;       // Step 6
            dummy = nextNode;            // Move to next pair (Step 3)

//            check:
            System.out.println("prev: " + prev.val + " dummy:" + dummy.val + " next: " + nextNode.val);
        }
//      time: On space: O1
        return new_head;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//    3rd - same as 2 different longer implement more readable
    public static ListNode swapPairs_iterative_way_frompage(ListNode head) {
        // Check edge case: linked list has 0 or 1 nodes, just return
        if (head == null || head.next == null) {
            return head;
        }

//        initialize:
        ListNode tempnode = new ListNode(-1); //temporary so we can change its pointer later, cannot be done with null
        tempnode.next = head;
        ListNode prevNode = tempnode;
        ListNode dummy = head;
        head = head.next; //to return pointer to head at end, head.next is the first after swap!

        while ((dummy != null) && (dummy.next != null)) {
            // Nodes to be swapped
            ListNode firstNode = dummy;
            ListNode secondNode = dummy.next;

            // Swapping
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // Reinitializing the head and prevNode for next swap
            prevNode = firstNode; //now after swap its the second node
            dummy = firstNode.next; // jump
        }
        // Return ptr to start of LL (note its the original 2nd object).
        return head;
    }
}