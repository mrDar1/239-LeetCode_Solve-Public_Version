import java.util.ArrayList;
import java.util.List;
/*problem:
* is palindrom?*/

/*3 solutions:
* 1st - coppy to ArrayList and work with it - work but not valid solution.
* 2nd - reverse second half - my first solve believe its better.
* 3rd - same idea as 2, but keep duplicate till the end - its still work, but i like it less */

public class B5_Palindrome_linked_list_234 {
    public static class ListNode {
        int val;
        ListNode next;

        //        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
//        ListNode(int val, ListNode next) {
//            this.val = val;
//            this.next = next;
//        }
    }

    public static void main(String[] args) {

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(1);
//        ListNode four = new ListNode(1);
//        ListNode five = new ListNode(5);
//        ListNode six = new ListNode(6);

        one.next = two;
        one.next.next = three;
        one.next.next.next = null;
//        one.next.next.next.next = null;
//        one.next.next.next.next.next = null;
//        one.next.next.next.next.next = six;
//        one.next.next.next.next.next.next = null;

        PrintList(one);
//        System.out.println("using List - is the list palindrome?  " + isPalindrome(one));
        System.out.println("using Reverse - is the list palindrome?  " + isPalindrome_O1space_again(one));
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

    /*psudo: create copy in list format.
    * moove on both: lists from end, linked-list from start - if diff - return false,
    * when exhausted true */
    public static boolean isPalindrome(ListNode head) {
        List<Integer> mylist = new ArrayList<>();
        ListNode dummy = head;

        while (dummy != null){
            mylist.add(dummy.val);
            dummy = dummy.next;
        }
        dummy = head;
//        System.out.println(mylist);

        for (int i = mylist.size() - 1; i > 0 ; --i, dummy = dummy.next) {
//            System.out.println(dummy.val + " " + mylist.get(i));
            if (dummy.val != mylist.get(i)){
                return false;
            }
        }
//        time: O2n - create List and then compare.
//        space: O1n
        return true;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */


    /*psudo:
     * find middle (if got even number of nodes - return the first of the pair middle nodes),
     * reverse the second half of linked-list.
     * start traverse from start and from reversed list together, compare vals - if not same - return false.
     * at end - return both string to the original form.
     *
     * before reverse got some cool trick:
     * if we just did the reverse we got 2 linked-lists, but the first will be longer than the second by 1 node! its will create a duplicate of the first node of the second list! and again as last of first half of link-list!
     * to remove that duplicate value, use:
     * "make_it_finish_with_null" var
     *
     * note: at first, whenever found its not palindrome return false,
     * however to return the original link-list as it were use boolean vars */
    public static boolean isPalindrome_O1space(ListNode head) {

        if (head == null || head.next == null) return true; //edge case

        ListNode dummy = head; //use later to traverse from start of first half of link-list.
        boolean ispalindrome = true; // we cant just "return false" as we want to return the input link-list to same for. and only then return boolean value.

        ListNode middle = FindMiddle_first_of_pair(head);

//        trick to avoid duplicate node (remove the duplicate of the last node in first half of linked-list)
        ListNode last_node_of_first_half = middle;
        middle = middle.next; //advance middle to its desire location to start reversing.
        last_node_of_first_half.next = null; //terminates the duplicate node.

        middle = ReverseList(middle);

        ListNode middle_head_copy = middle; //save for connect later.
        while (middle != null) { // in linked-list with odd number of nodes, first half will be with the middle node, I.E - one node longer, therefore we need to use "middle != null" and not "dummy != null"
            if (dummy.val != middle.val){
                ispalindrome = false;
                break;
            }
            middle = middle.next;
            dummy = dummy.next;
        }

        last_node_of_first_half.next = reverseList(middle_head_copy);
        PrintList(head);
//        time: O3n: 1n-find middle, 0.5n-reverse half list, 1n-traverse both lists and compare, 0.5-reverse back second half to return to original form
//        space: O1
        return ispalindrome;
    }

    public static ListNode ReverseList(ListNode head) {
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
    public static ListNode FindMiddle_first_of_pair(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while ( fast.next != null && fast.next.next != null ){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static boolean isPalindrome_O1space_again(ListNode head) {
        if (head == null) return true; //edge case

        ListNode firstHalfEnd = FindMiddle_first_of_pairs(head); //if got 2 middle - will bring back the first one
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true; // we must use bool and not return false - since we want to prepare the list after reverse it.

        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        firstHalfEnd.next = reverseList(secondHalfStart);

//        time: O3n: 1st-find middle, 0.5n-2nd-reverse half list, 3rd-traverse both lists and compare, 0.5-4th-reverse again
//        space: O1
        return result;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    private static ListNode FindMiddle_first_of_pairs(ListNode head) { //if got 2 middle - will bring back the first one
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}

