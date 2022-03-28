package linkedlist;

/**
 * @author yusufu
 */
public class LinkedList1Test {

    public static void main(String[] args)
    {
        LinkedList1 list = new LinkedList1();

        list = list.insert(list, 1);
        list = list.insert(list, 2);
        list = list.insert(list, 3);
        list = list.insert(list, 4);
        list = list.insert(list, 5);
        list = list.insert(list, 6);
        list = list.insert(list, 7);
        list = list.insert(list, 8);

        list.printList(list);
    }
}
