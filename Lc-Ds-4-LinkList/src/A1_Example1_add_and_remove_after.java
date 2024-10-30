public class A1_Example1_add_and_remove_after {
    public static class ListNode {
        int val;
        ListNode next;
        //ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        //ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args){

        ListNode head = new ListNode(-1); //head sentinel
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode tail = new ListNode(-1); //tail sentinel

        head.next = one;
        head.next.next = two;
        head.next.next.next = three;
        head.next.next.next.next = four;
        head.next.next.next.next.next = five;
        head.next.next.next.next.next.next = tail;
        head.next.next.next.next.next.next.next = null;

        PrintList(head);
        System.out.println("\ntill here first time before addinng:");
        AddAfter(three, 100);
        System.out.println("after addinng 100:");
        PrintList(head);
        DeleteAfter(three);
        System.out.println("\nafter remove number 4:");
        PrintList(head);
    }
    public static void PrintList(ListNode head){
        ListNode dummy = head.next; //skip head sentinel

        for( ; dummy.next != null ; dummy = dummy.next){
            if(dummy.next.next == null){
                System.out.print(dummy.val + ".");
            }else {
                System.out.print(dummy.val + ", ");
            }
        }
//        System.out.println(" ");
    }

    /* ************************************* */
    /* ************************************* */
    /* ************************************* */
    /* ************************************* */

    public static void AddAfter(ListNode prevNode, int val_to_add){
        ListNode node_to_add = new ListNode(val_to_add);
        node_to_add.next = prevNode.next;
        prevNode.next = node_to_add;
    }
    public static void DeleteAfter(ListNode NodeBeforeNodeToRemove){
        NodeBeforeNodeToRemove.next = NodeBeforeNodeToRemove.next.next;
    }
}
