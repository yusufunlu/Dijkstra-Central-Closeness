package linkedlist;

/**
 * @author yusufu
 */
class LinkedList1 {
    Node head;

    class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
        }
    }

    public LinkedList1 insert(LinkedList1 list, int data) {
        Node newNode = new Node(data);
        newNode.next = null;

        if(list.head == null) {
            list.head = newNode;
        } else {
            Node current = list.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        return list;
    }

    public void printList(LinkedList1 list) {

        Node current = list.head;
        int i = 0;
        while (current.next!=null){
            i++;
            System.out.printf("Node %s data %d \n", i,current.data);
            current = current.next;
        }
    }


}