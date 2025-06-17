public class B10_Design_Singly_Link_List_707{
    public static void main(String[] args) { //long testing:
        MySinglyLinkedList objsinglylinkedList = new MySinglyLinkedList();

        objsinglylinkedList.addAtHead(3);
//        System.out.println(singlylinkedListobj.get(0));
        objsinglylinkedList.addAtHead(2);
//        System.out.println(singlylinkedListobj.get(0));
        objsinglylinkedList.addAtHead(1);
//        System.out.println(singlylinkedListobj.get(0));

        // Add elements using addAtTail
        objsinglylinkedList.addAtTail(4);
        objsinglylinkedList.addAtTail(5);
        System.out.println("the entire list (should be 1-5):");
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("get 5th (4) element: "+objsinglylinkedList.get(4));
        System.out.println("size is: " + objsinglylinkedList.size);
//
        System.out.println("delete middle-3 value (2) element:");
        objsinglylinkedList.deleteAtIndex(2);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("size is: " + objsinglylinkedList.size);
//
        System.out.println("delete illegal element (5):");
        objsinglylinkedList.deleteAtIndex(5);
        System.out.println("size is: " + objsinglylinkedList.size);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
//
        System.out.println("delete last (3) element:");
        objsinglylinkedList.deleteAtIndex(3);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("size is: " + objsinglylinkedList.size);

        System.out.println("delete first (0) element:");
        objsinglylinkedList.deleteAtIndex(0);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("size is: " + objsinglylinkedList.size);

        System.out.println("delete first (0) element:");
        objsinglylinkedList.deleteAtIndex(0);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("size is: " + objsinglylinkedList.size);

        System.out.println("delete first (0) element:");
        objsinglylinkedList.deleteAtIndex(0);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("size is: " + objsinglylinkedList.size);


        System.out.println("delete first (0) element:");
        objsinglylinkedList.deleteAtIndex(0);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("size is: " + objsinglylinkedList.size);


        System.out.println("delete first (0) element:");
        objsinglylinkedList.deleteAtIndex(0);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("size is: " + objsinglylinkedList.size);


        System.out.println("delete first (0) element:");
        objsinglylinkedList.deleteAtIndex(0);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
        System.out.println("size is: " + objsinglylinkedList.size);

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("enter new value list:");
        objsinglylinkedList.addAtTail(1);
        objsinglylinkedList.addAtTail(2);
        objsinglylinkedList.addAtTail(3);
        objsinglylinkedList.addAtTail(4);
        objsinglylinkedList.addAtTail(5);
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
//
//
        objsinglylinkedList.addAtIndex(2, 100);
        objsinglylinkedList.addAtIndex(3, 101);
        objsinglylinkedList.addAtIndex(4, 102);
//        //        debug check:
        objsinglylinkedList.PrintList(objsinglylinkedList.headptr.next);
//
        // Test various operations
        int valueAtIndex2 = objsinglylinkedList.get(2); // Should return 3
        int valueAtIndex5 = objsinglylinkedList.get(5); // Should return -1 because index is invalid

        System.out.println("Value at index 2: " + valueAtIndex2);
        System.out.println("Value at index 5: " + valueAtIndex5);
    }
}

/* **************************************** */
/* ************** start answer ************ */
/* **************************************** */

class MySinglyListNode{
    int val;
    MySinglyListNode next;
    MySinglyListNode(int val){
        this.val = val;
    }
    MySinglyListNode(int val, MySinglyListNode next){
        this.val = val;
        this.next = next;
    }
}

class MySinglyLinkedList {
    int size;
    MySinglyListNode headptr;

    public MySinglyLinkedList() {//constructor - each time we call "class MySinglyLinkedList" will use to create obj:
        size = 0;
        headptr = new MySinglyListNode(-1, null);
    }

    public void PrintList(MySinglyListNode head) {
        MySinglyListNode current = head;

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

    public int get(int index) { //will return the index - with human count (start from 1 and not 0).
//        edge case:
        if (index >= size || index < 0) {
            return -2;
        }

        MySinglyListNode dummy = headptr;

        for (int i = 0; i < index + 1; i++) { // +1 cause got sentinel node.
            dummy = dummy.next;
        }
        return dummy.val;
    }
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            System.out.println("cannot add element - index bigger than size of list");
            return;
        }

        //Constraint - if index smaller than 0 - add at head.
        if (index < 0) {
            index = 0;
        }

        ++size;
        MySinglyListNode pred = headptr;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }

        MySinglyListNode toadd = new MySinglyListNode(val);
        toadd.next = pred.next;
        pred.next = toadd;
    }

    public void deleteAtIndex ( int index){
//        edge case:
        if (index < 0 || index >= size) {
            System.out.println("index illegal - bigger than list size or smaller than 0");
            return;
        }

        --size;
        MySinglyListNode pred = headptr;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;
    }
}
