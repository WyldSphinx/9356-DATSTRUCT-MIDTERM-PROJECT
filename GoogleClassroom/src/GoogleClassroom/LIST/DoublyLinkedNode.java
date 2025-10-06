package GoogleClassroom.LIST;

public class DoublyLinkedNode<E> {
    private E data;
    private DoublyLinkedNode<E> next;
    private DoublyLinkedNode<E> previous;

    public DoublyLinkedNode(E data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public DoublyLinkedNode(E data, DoublyLinkedNode<E> next, DoublyLinkedNode<E> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    // Getters
    public E getData() {
        return data;
    }

    public DoublyLinkedNode<E> getNext() {
        return next;
    }

    public DoublyLinkedNode<E> getPrevious() {
        return previous;
    }

    // Setters
    public void setData(E data) {
        this.data = data;
    }

    public void setNext(DoublyLinkedNode<E> next) {
        this.next = next;
    }

    public void setPrevious(DoublyLinkedNode<E> previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "null";
    }
}
