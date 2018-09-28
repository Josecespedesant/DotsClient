package linkedlist;

/**
 * Clase que representa un nodo parte de una lista enlazada.
 *
 * @author David Azofeifa Herrera.
 * @param <T>
 */
public class Node<T> {
    private T data;
    private Node next;

    public Node(T data) {
        this.data = data;
    }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }

    public Node getNext() { return next; }

    public void setNext(Node next) { this.next = next; }
}
