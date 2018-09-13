package linkedlist;

/**
 * Simple linked list.
 *
 * @author David Azofeifa Herrera
 * @param <T>
 */
public class LinkedList < T > {
    private Node head;
    private int length;

    public int getLength() {
        return this.length;
    }
    public Node getHead() {
        return this.head;
    }

    public LinkedList() {
        this.head = null;
        this.length = 0;
    }

    public void append(T data) {
        if (this.head == null) {
            this.head = new Node(data);
        } else {
            Node temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node(data));
        }
        this.length++;
    }

    public boolean check(T data) {
        boolean dataIsFound = false;
        Node temp = this.head;
        while (temp != null && temp.getData() != data) {
            temp = temp.getNext();
        }
        if (temp.getData() == data) {
            dataIsFound = true;
        }

        return dataIsFound;
    }

    public void delete(T data) {
        if (this.head.getData() == data) {
            this.head = this.head.getNext();
            this.length--;
        } else {
            Node temp = this.head;
            Node prev = this.head;
            while (temp.getNext() != null && temp.getData() != data) {
                prev = temp;
                temp = temp.getNext();
            }
            if (temp.getData() == data) {
                if (temp.getNext() == null) {
                    prev.setNext(null);
                } else {
                    prev.setNext(temp.getNext());
                }
                this.length--;
            }
        }
    }

    public boolean isEmpty(){
        return (this.head == null);
    }

    public boolean indexExists(int pIndex){
        if(!isEmpty()){
            return indexExists_aux(pIndex);
        } else {
            System.out.println("List is empty.");
            return false;
        }
    }
    private boolean indexExists_aux(int pIndex){
        return (pIndex < this.length && pIndex >= 0);
    }

}
