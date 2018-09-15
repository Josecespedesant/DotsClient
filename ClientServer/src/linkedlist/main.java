package linkedlist;

public class main {
    public static void main(String[] args) {
        LinkedList test = new LinkedList();
        test.append(4);
        test.append("hola, donde está la biblioteca?");
        test.append(7);
        test.append(9);
        System.out.println("List length: " + test.getLength());

        Node temp = test.getHead();
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }

        test.delete(5);
        System.out.println("List length: " + test.getLength());
    }
}
