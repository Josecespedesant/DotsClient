package linkedlist;

public class main {
	
    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<Integer>();
        test.append(4);
        test.append(7);
        test.append(9);
        System.out.println("List length: " + test.getLength());

        Node<Integer> temp = test.getHead();
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }

        test.delete(5);
        System.out.println("List length: " + test.getLength());
    }
}
