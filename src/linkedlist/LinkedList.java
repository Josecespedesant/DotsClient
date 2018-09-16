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

    public int getLength() { return this.length; }

    public Node getHead() { return this.head; }

    public LinkedList() {
        this.head = null;
        this.length = 0;
    }

    /**
     * Creates Node instance with data and appends it to linked list
     *
     * @param data
     */
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

    /**
     * Searches for specified value on list.
     *
     * @param data
     * @return If data is found on list
     */
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

    /**
     * Deletes Node on list containing specified data.
     *
     * @param data
     */
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

    /**
     * Checks if accessing an n Node is possible by n < list length.
     *
     * @param pIndex
     * @return
     */
    public boolean indexExists(int pIndex){
        boolean indexAccessible = false;
        if(!isEmpty()){
            indexAccessible = (pIndex < this.length && pIndex >= 0);
        }
        return indexAccessible;
    }
    
    /**
     * 
     * @return
     */
    public boolean isEmpty(){
        return (this.head == null);
    }

}
