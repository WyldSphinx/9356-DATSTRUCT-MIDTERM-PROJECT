package GoogleClassroom.LIST;

public class DoublyLinkedList<E> implements ListInterface<E> {
    private DoublyLinkedNode<E> head;
    private DoublyLinkedNode<E> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int search(E data) {
        DoublyLinkedNode<E> current = head;
        int index = 0;

        while (current != null) {
            if (current.getData().equals(data)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public boolean contains(E data) {
        return search(data) != -1;
    }

    @Override
    public void insert(E data) {
        insertAtTail(data);
    }

    @Override
    public void insertAtHead(E data) {
        DoublyLinkedNode<E> newNode = new DoublyLinkedNode<>(data);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    @Override
    public void insertAtTail(E data) {
        DoublyLinkedNode<E> newNode = new DoublyLinkedNode<>(data);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }

    @Override
    public void insertAtIndex(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == 0) {
            insertAtHead(data);
        } else if (index == size) {
            insertAtTail(data);
        } else {
            DoublyLinkedNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }

            DoublyLinkedNode<E> newNode = new DoublyLinkedNode<>(data);
            newNode.setNext(current);
            newNode.setPrevious(current.getPrevious());
            current.getPrevious().setNext(newNode);
            current.setPrevious(newNode);
            size++;
        }
    }

    @Override
    public E deleteHead() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot delete from empty list");
        }

        E data = head.getData();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
        return data;
    }

    @Override
    public E deleteTail() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot delete from empty list");
        }

        E data = tail.getData();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        size--;
        return data;
    }

    @Override
    public boolean delete(E data) {
        DoublyLinkedNode<E> current = head;

        while (current != null) {
            if (current.getData().equals(data)) {
                if (current == head) {
                    deleteHead();
                } else if (current == tail) {
                    deleteTail();
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                    size--;
                }
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public E deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == 0) {
            return deleteHead();
        } else if (index == size - 1) {
            return deleteTail();
        } else {
            DoublyLinkedNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }

            E data = current.getData();
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            size--;
            return data;
        }
    }

    @Override
    public E getHead() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot get head from empty list");
        }
        return head.getData();
    }

    @Override
    public E getTail() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot get tail from empty list");
        }
        return tail.getData();
    }

    @Override
    public E getElementAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        DoublyLinkedNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public E getElementAtData(E data) {
        DoublyLinkedNode<E> current = head;

        while (current != null) {
            if (current.getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void setHead(E data) {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot set head of empty list");
        }
        head.setData(data);
    }

    @Override
    public void setTail(E data) {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot set tail of empty list");
        }
        tail.setData(data);
    }

    @Override
    public void setElementAtIndex(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        DoublyLinkedNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(data);
    }

    @Override
    public E[] toArray() {
        @SuppressWarnings("unchecked")
        E[] array = (E[]) new Object[size];
        DoublyLinkedNode<E> current = head;

        for (int i = 0; i < size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        DoublyLinkedNode<E> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }
}
