import java.util.HashSet;
import java.util.Set;

/* 2 solutions:
 1-classic slow & fast ptr,
 2-hash-bad, here for study.
*/
public class A3_Example2_Has_Cycle {
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
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);
        ListNode nine = new ListNode(9);
        ListNode tail = new ListNode(-1);

        head.next = one;
        head.next.next = two;
        head.next.next.next = three;
        head.next.next.next.next = four;
        head.next.next.next.next.next = five;
        head.next.next.next.next.next.next = six;
        head.next.next.next.next.next.next.next = seven;
        head.next.next.next.next.next.next.next.next = eight;
        head.next.next.next.next.next.next.next.next.next = nine;
//        crete cycle:
        head.next.next.next.next.next.next.next.next.next.next = four;

//        head.next.next.next.next.next.next.next.next.next.next = tail;
//        head.next.next.next.next.next.next.next.next.next.next.next = null;

        System.out.println("True for cycle False-for not: " + hasCycle(head) );
        System.out.println("True for cycle False-for not: " + hasCycle_using_hash(head) );
    }
    public static void PrintList(ListNode head){
       ListNode dummy = head.next;
        for (; dummy.next != null; dummy = dummy.next) {
            if (dummy.next.next == null) {
                System.out.print(dummy.val + ".");
            } else {
                System.out.print(dummy.val + ", ");
            }
        }
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
//        time: On space: O1
        return false;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static boolean hasCycle_using_hash(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        ListNode dummy = head;

        while (dummy != null) {
            if (seen.contains(dummy)) {
                return true;
            }
            seen.add(dummy);
            dummy = dummy.next;
        }
//        time: On space: On
        return false;
    }
}