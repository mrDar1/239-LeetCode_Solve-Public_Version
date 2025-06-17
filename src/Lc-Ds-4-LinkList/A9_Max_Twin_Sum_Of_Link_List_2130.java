import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*problem:
* return the max twin sum of the singly link list.
* twin sum = first and last, first+1 and last-1, first+2 and last-2, and so on.....

* constraint: the number of nodes is always even! */

/*solution:
1st - easiest solution using array - no good for for interview.
2nd - use stack - my first way.
2.5 - same idea - LC did it more elegant. use more space but less time.
3rd - reverse half list, then traverse with 2 ptrs ---- only valid solution for interview!!! */
public class A9_Max_Twin_Sum_Of_Link_List_2130 {
    public static class ListNode {
        int val;
        ListNode next;
        //        ListNode() {}
        ListNode(int val) { this.val = val; }
//        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static void main(String[] args){
        ListNode one = new ListNode(0);
        ListNode two = new ListNode(5);
        ListNode three = new ListNode(4);
        ListNode four = new ListNode(2);
        ListNode five = new ListNode(1);
        ListNode six = new ListNode(100);

        one.next = two;
        one.next.next = three;
        one.next.next.next = four;
        one.next.next.next.next = five;
        one.next.next.next.next.next = six;
        one.next.next.next.next.next.next = null;

//        head.next.next.next.next.next.next = tail;
//        head.next.next.next.next.next.next.next = null;

        PrintList(one);
//        System.out.println(pairSum_1_ArrayList(one));
//        System.out.println(pairSum_2nd_use_stack_myimplement(one));
        System.out.println(pairSum_2nd_approach_Lc_implement(one));
//        System.out.println(pairSum_3rd_approach_reverse_half(one));
    }
    public static void PrintList (ListNode head){
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

    /* 1st psudo:
     * traverse list , push each value to List and work with List */
    public static int pairSum_1_ArrayList(ListNode head) {
        int l = 0;
        int r = Integer.MIN_VALUE; // cannot initialize with relevant value.
        int max = Integer.MIN_VALUE;
        ListNode dummy = head;
        List<Integer> list = new ArrayList<>();

        while(dummy != null){
            list.add(dummy.val);
            dummy = dummy.next;
        }
        r = list.size() - 1;

        while ( r > l){
            max = Math.max(max, list.get(r--) + list.get(l++));
//            debugging:
//            System.out.println(list.get(r) + list.get(l));
        }
//        time: O2n - 1st build List, 2nd-traverse the list
//        space: On - List.
        return max;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /* 2nd psudo:
     * find one node before middle, from there store at stack.
     * traverse again from start - each time find max from start to middle. */
    public static int pairSum_2nd_use_stack_myimplement(ListNode head) {
        ListNode dummy = head;
        int maxPair = Integer.MIN_VALUE;
        Stack<Integer> mystack = new Stack<>();
        ListNode middle = find_2nd_node_of_middle(head);

//        fill stack from middle.
        while (middle != null){
            mystack.push(middle.val);
            middle = middle.next;
        }

        while (!mystack.empty()){ // could do: while(dummy.next != null) // its same length
            maxPair = Math.max(maxPair, mystack.pop() + dummy.val);
            dummy = dummy.next;
        }

//        time: O2.5n - 1st find middle, 0.5n - populate stack, 1n-traverse list  and stack together.
//        space: O0.5n - stack.
        return maxPair;
    }

    /*auxilary method to find middle (if got pair return the first - here for practice): */
    public static ListNode find_1st_node_of_middle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    /*auxilary method to find middle (if got pair return the second): */
    public static ListNode find_2nd_node_of_middle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    * copy link list into stack.
    * iterate link list again (using dummy): find max of stack.pop() and dummy*/
    public static int pairSum_2nd_approach_Lc_implement(ListNode head) {
        int maximumSum = 0;
        int count = 0;
        int stsize = Integer.MIN_VALUE;
        ListNode dummy = head;
        Stack<Integer> st = new Stack<>();

//        filling stack st.
        while (dummy != null) {
            st.push(dummy.val);
            dummy = dummy.next;
        }

        stsize = st.size();
        dummy = head;

        while (count < stsize / 2) {
            maximumSum = Math.max(maximumSum, dummy.val + st.pop());
            dummy = dummy.next;
            count++;
        }
//        time: O2n - fill stack, 2n-traverse list and stack together.
//        space: O1n-stack - filling the entire stack!
        return maximumSum;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*3rd psudo:
    find one node before middle,
    traverse second half of list.
    traverse with 2 pointer: one from start 2nd from middle and find max sum */
    public static int pairSum_3rd_approach_reverse_half(ListNode head) {
        int max = Integer.MIN_VALUE;
        ListNode dummy = head;

        ListNode second_half_of_list =  reverse_from_middle(find_2nd_node_of_middle(dummy));
        dummy = head;
//        PrintList(head);
//        PrintList(second_half_of_list);

        while (second_half_of_list != null && dummy != null){
            max = Math.max(max, second_half_of_list.val + dummy.val);
            second_half_of_list = second_half_of_list.next;
            dummy = dummy.next;
        }
//        time: O2.5n: O1n-find middle; 0.5n = reverse second half of list; O1n-traverse first and second half of list find max
//        space: O1 - split list in two but same space.
        return max;
    }
    public static ListNode reverse_from_middle(ListNode head) {
//        System.out.println("head is: " + head.val);
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    /*psudo:
    * find middle (the first of the pair)
    * traverse the second half of the list
    * use to ptrs from start and middle to compare */
    public static int pairSum_3rd_approach_reverse_half_solve_again_for_practice_delete_when_finish(ListNode head) {
        ListNode dummy = head;
        int maxtwin = Integer.MIN_VALUE;
        ListNode middle = find_1st_node_of_middle_another_method_for_practice(head);
        ListNode head_second_part = reverse_from_middle_another_for_practice(middle);

        ListNode dummy2 = head_second_part; // just for readability
        while (dummy != null && dummy.next!=null){ //could be that, same len: (dummy2 != null&& dummy2.next!=null)
            maxtwin = Math.max(maxtwin, dummy.val+ dummy2.val);

            dummy = dummy.next;
            dummy2 = dummy2.next;
        }

        return maxtwin;
    }

    private static ListNode reverse_from_middle_another_for_practice(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null){
            ListNode next = cur.next;

            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private static ListNode find_1st_node_of_middle_another_method_for_practice(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
