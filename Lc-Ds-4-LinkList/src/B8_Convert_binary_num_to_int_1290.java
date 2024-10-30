/*problem:
* given binary LL - return the decimal number it represent.
* the MSB is at the head*/

/*several solutions:
* 1 - my first regular math formula to binary and reversal
* 2 - use different Math formula to convert to binary.
* 3 - same idead as 2, BitWise */

public class B8_Convert_binary_num_to_int_1290 {
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
        ListNode two = new ListNode(1);
        ListNode three = new ListNode(0);
        ListNode four = new ListNode(1);

        one.next = two;
        one.next.next = three;
        one.next.next.next = four;
        one.next.next.next.next = null;
//        one.next.next.next.next.next = one;
//        one.next.next.next.next.next.next = one;

        System.out.println("the original list:");
        PrintList(one);

        System.out.println("the number as decimal - from my method: ");
        System.out.println(getDecimalValue(one));

        System.out.println("the number as decimal - from LC - solution 1: ");
        System.out.println(getDecimalValue_more_elegant_math_formula(one));

        System.out.println("the number as decimal - from LC - solution 2 - bitwise: ");
        System.out.println(getDecimalValue_Bitwise(one));

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

    /*psudo - my way - classic solution:
    reverse the list and then use the classic math function
    reverse again to return the original LL unchanged. */
    public static int getDecimalValue(ListNode head) {
        ListNode dummy = head;
        int sum = 0;

//        edge case:
        if (head == null){return -1;}

        dummy = reverselist(dummy);
        head = dummy; //update for reverse again.
//        PrintList(dummy);

        for (int i = 0; dummy != null ; dummy = dummy.next, ++i) {
            sum += ((int)Math.pow(2,i) * dummy.val);
        }

        //reverse again so can use that list again.
        reverselist(head);


//        time: O2n - 1st reverse list, 2nd-convert to binary.
//        space: O1
        return sum;
    }
    public static ListNode reverselist(ListNode head) {
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

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

//LC  math formula to binary - i didnt know
    public static int getDecimalValue_more_elegant_math_formula(ListNode head) {
        int num = head.val;

        while (head.next != null) {
            num = (num * 2) + head.next.val;
            head = head.next;
        }
//        time:On space: O1
        return num;
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static int getDecimalValue_Bitwise(ListNode head) {
        int num = head.val;

        while (head.next != null) {
            num = (num << 1) | head.next.val;
            head = head.next;
        }
//        time:On space: O1
        return num;
    }
}