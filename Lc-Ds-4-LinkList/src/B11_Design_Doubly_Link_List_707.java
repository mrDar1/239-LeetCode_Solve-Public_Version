//not great - testing take too much time!!!
//look at down:
public class B11_Design_Doubly_Link_List_707{
    public static void main(String[] args) {
        MyDoublyLinkedList objdoublylinkedList = new MyDoublyLinkedList();

        // Add elements to the linked list
        objdoublylinkedList.addAtHead(1);
        objdoublylinkedList.addAtTail(2);
        objdoublylinkedList.addAtTail(3);
        objdoublylinkedList.addAtTail(4);
        objdoublylinkedList.addAtTail(5);

        System.out.println("full list (no sentinels):");
        objdoublylinkedList.PrintList(objdoublylinkedList.head);

        // Test various operations
        System.out.println("Value at index 2: " + objdoublylinkedList.get(2)); // Should return 3
        System.out.println("Value at index 5 (should be invalid): ");
        System.out.println( + objdoublylinkedList.get(5)); // Should return -1 because index is invalid

        System.out.println("delete index 2 (value 3)");
        objdoublylinkedList.deleteAtIndex(4); // Delete node at index 2 (value 3)

        System.out.println("full list (with -1 head sentinel and -2 tail sentinel):");
        objdoublylinkedList.PrintList(objdoublylinkedList.head);

        // Check the updated list
        System.out.println("Updated value at index 2: " + objdoublylinkedList.get(2)); // Should return 2
    }
}

/* **************************************** */
/* ************** start answer ************ */
/* **************************************** */

class DoublyListNode{
    int val;
    DoublyListNode next;
    DoublyListNode prev;
    DoublyListNode(int val){
        this.val = val;
    }
}

class MyDoublyLinkedList {
    int size;
    DoublyListNode head; //sentinel node as psudo head
    DoublyListNode tail; //sentinel node as psudo tail
    public MyDoublyLinkedList() {
        this.size = 0;
        this.head = new DoublyListNode(Integer.MIN_VALUE); //val == sign for head sentinel
        this.tail = new DoublyListNode(Integer.MAX_VALUE); //val == sign for tail sentinel
        this.head.next = tail;
        this.tail.prev = head;
    }

    public void PrintList(DoublyListNode head) {
        DoublyListNode current = head.next;

        while (current != tail) {
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

    public int get(int index) {
        if (index < 0 || index >= size){
            System.out.println("invalid index! smaller than 0 or biger than size");
            return Integer.MIN_VALUE;
        }

        DoublyListNode dummy = null;
//         effective way: run from head/tail so instead O1n will get O0.5n at max.
        if (size / 2 > index){
            dummy = head;
            for (int i = 0; i < index + 1; i++) { // why +1? cause got dummy
                dummy = dummy.next;
            }
        }else {
            dummy = tail;
            for (int i = size - 1 ; i > 0; i--) {
                dummy = dummy.prev;
            }
        }
        return dummy.val;
    }

    public void addAtHead(int val) {
        DoublyListNode toadd = new DoublyListNode(val);
        ++size;

        DoublyListNode pred = head;
        DoublyListNode succ = head.next;
        toadd.prev = pred;
        toadd.next = succ;
        pred.next = toadd;
        succ.prev = toadd;

//        my first try(same principle but less elegant):
//        toadd.prev = head;
//        toadd.next = head.next;
//        head.next = toadd;
//        toadd.next.prev = toadd;
    }

    public void addAtTail(int val) {
        DoublyListNode succ = tail;
        DoublyListNode pred = tail.prev;
        DoublyListNode toadd = new DoublyListNode(val);

        ++size;
        toadd.next = succ;
        toadd.prev = pred;
        succ.prev = toadd;
        pred.next = toadd;
    }

    public void addAtIndex(int index, int val) {
//        edge case:
        if (index >= size){
            return;
        }

//       problem Constraints:
        if (index < 0){
            index = 0;
        }


        DoublyListNode toadd = new DoublyListNode(val);
        DoublyListNode dummy = null;
        DoublyListNode pred = null;
        DoublyListNode succ = null;

//         effective way: run from head/tail so instead O1n will get O0.5n at max.
        if (size / 2 > index){
            pred = head;
            for (int i = 0; i < index ; i++) { // why here we dont have +1???
                pred = pred.next;
                succ = pred.next;
            }
        }else {
            succ = tail;
            for (int i = size ; i > 0; i--) {
                succ = succ.prev;
                pred = succ.prev;
            }
        }

        ++size;
        toadd.next = succ;
        toadd.prev = pred;
        pred.next = toadd;
        succ.prev = toadd;
    }

    public void deleteAtIndex(int index) {

//        edge case:
        if (index < 0 || index >= size) {
            return;
        }

        DoublyListNode dummy;

        if (size / 2 > index){
            dummy = head;
            for (int i = 0; i < index + 1; i++) { // why +1? cause got dummy
                dummy = dummy.next;
            }
        }else {
            dummy = tail;
            for (int i = size - 1 ; i > 0; i--) {
                dummy = dummy.prev;
            }
        }
        dummy.next.prev = dummy.prev;
        dummy.prev.next = dummy.next;
    }
}
////look again at the the way to start from head/tail
//public class Design_Doubly_Link_List_707 {
//    public static void main(String[] args) {
//        MyLinkedList linkedList = new MyLinkedList();
//
//        // Add elements to the linked list
//        linkedList.addAtHead(1);
//        linkedList.addAtTail(2);
//        linkedList.addAtTail(3);
//        linkedList.addAtTail(4);
//        linkedList.addAtTail(5);
//
//        // Test various operations
//        int valueAtIndex2 = linkedList.get(2); // Should return 3
//        int valueAtIndex5 = linkedList.get(5); // Should return -1 because index is invalid
//
//        System.out.println("Value at index 2: " + valueAtIndex2);
//        System.out.println("Value at index 5: " + valueAtIndex5);
//
//        linkedList.deleteAtIndex(2); // Delete node at index 2 (value 3)
//
//        // Check the updated list
//        int newValueAtIndex2 = linkedList.get(2); // Should return 4
//        System.out.println("Updated value at index 2: " + newValueAtIndex2);
//    }
//}
//
//
//
//
//
//class ListNode{
//    int val;
//    ListNode next;
//    ListNode prev;
//    ListNode(int x) { val = x; }
//}
//
//class MyLinkedList {
//    int size;
//    ListNode head; //sentinel node as psudo head
//    ListNode tail; //sentinel node as psudo tail
//    public MyLinkedList(){
//        size = 0;
//        head = new ListNode(-1); //val== sign for head sentinel
//        tail = new ListNode(-2); //val== sign for tail sentinel
//        head.next = tail;
//        tail.prev = head;
//    }
//
//    public int get(int index) {
//        if (index < 0 || index >= size) return -1; //edge case
//
//        ListNode curr = null;
//
//
////         effective way: run from head/tail so instead O1n will get O0.5n at max.
//        if (index + 1 < size - index){
//            curr = head;
//
//            for (int i = 0; i < index + 1; ++i) {
//                curr = curr.next;
//            }
//        }else {
//            curr = tail;
//
//            for(int i = 0; i < size - index; ++i){
//                curr = curr.prev;
//            }
//        }
//
//        return curr.val;
//    }
//
//    public void addAtHead(int val) {
//        ListNode pred = head;       // predecessor
//        ListNode succ = head.next;   // successor
//        ListNode to_add = new ListNode(val);
//
//
//        ++size;
//        to_add.next = succ;
//        to_add.prev = pred;
//        pred.next = to_add;
//        succ.prev = to_add;
//    }
//
//    public void addAtTail(int val) {
//        ListNode pred = tail.prev;       // predecessor
//        ListNode succ = tail;   // successor
//        ListNode to_add = new ListNode(val);
//
//        ++size;
//        to_add.next = succ;
//        to_add.prev = pred;
//        pred.next = to_add;
//        succ.prev = to_add;
//    }
//
//    public void addAtIndex(int index, int val) {
//        ListNode to_add = new ListNode(val);
//        ListNode pred;
//        ListNode succ;
//
//        if(index > size) return; //edge case.
//        if(index < 0) index = 0; // edge case - question constrains rule.
//
//
//        if( (size - index) < (size / 2) ){ //traverse from head
//            pred = head;
//
//            for(int i = 0 ; i < index ; ++i){
//                pred = pred.next;
//            }
//            succ = pred.next;
//
//        }else { //traverse from tail.
//            succ = tail;
//            for(int i = 0 ; i < size - index ; ++i){
//                succ = succ.prev;
//            }
//            pred = succ.prev;
//        }
//
//        ++size;
//        to_add.next = succ;
//        to_add.prev = pred;
//        succ.prev = to_add;
//        pred.next = to_add;
//    }
//
//    public void deleteAtIndex(int index) {
//        if (index >= size || index < 0) return;
//
//        ListNode pred;
//        ListNode succ;
//
//        if(size - index > index){ //start from head;
//            pred = head;
//            for(int i = 0 ; i < index ; ++i){
//                pred = pred.next;
//            }
//            succ = pred.next.next;
//        }else{//start from tail.
//            succ = tail;
//            for(int i = 0 ; i < size - index - 1 ; ++i){
//                succ = succ.prev;
//            }
//            pred = succ.prev.prev;
//        }
//
//        --size;
//        pred.next = succ;
//        succ.prev = pred;
//    }
//}