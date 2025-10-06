package GoogleClassroom.LIST;

public interface ListInterface<E> {
    // ========== BASIC OPERATIONS ==========
    /**
     * Returns the number of elements in the list
     * @return the size of the list
     */
    int getSize();

    /**
     * Checks if the list is empty
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Removes all elements from the list
     */
    void clear();

    // ========== SEARCH OPERATIONS ==========
    /**
     * Searches for an element in the list
     * @param data the data to search for
     * @return the index of the element, or -1 if not found
     */
    int search(E data);

    /**
     * Checks if the list contains a specific element
     * @param data the data to check for
     * @return true if found, false otherwise
     */
    boolean contains(E data);

    // ========== INSERT OPERATIONS ==========
    /**
     * Inserts an element at the end of the list
     * @param data the element to insert
     */
    void insert(E data);

    /**
     * Inserts an element at the beginning of the list
     * @param data the element to insert
     */
    void insertAtHead(E data);

    /**
     * Inserts an element at the end of the list
     * @param data the element to insert
     */
    void insertAtTail(E data);

    /**
     * Inserts an element at a specific index
     * @param index the position to insert at
     * @param data the element to insert
     * @throws IndexOutOfBoundsException if index is invalid
     */
    void insertAtIndex(int index, E data);

    // ========== DELETE OPERATIONS ==========
    /**
     * Removes the first element from the list
     * @return the removed element
     * @throws IllegalStateException if list is empty
     */
    E deleteHead();

    /**
     * Removes the last element from the list
     * @return the removed element
     * @throws IllegalStateException if list is empty
     */
    E deleteTail();

    /**
     * Removes the first occurrence of the specified element
     * @param data the element to remove
     * @return true if element was found and removed, false otherwise
     */
    boolean delete(E data);

    /**
     * Removes the element at the specified index
     * @param index the position to remove from
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    E deleteAtIndex(int index);

    // ========== GET OPERATIONS ==========
    /**
     * Gets the first element without removing it
     * @return the first element
     * @throws IllegalStateException if list is empty
     */
    E getHead();

    /**
     * Gets the last element without removing it
     * @return the last element
     * @throws IllegalStateException if list is empty
     */
    E getTail();

    /**
     * Gets the element at the specified index
     * @param index the position to get from
     * @return the element at the index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    E getElementAtIndex(int index);

    /**
     * Gets the first occurrence of the specified element
     * @param data the element to find
     * @return the element if found, null otherwise
     */
    E getElementAtData(E data);

    // ========== SET OPERATIONS ==========
    /**
     * Replaces the first element with the new data
     * @param data the new data
     * @throws IllegalStateException if list is empty
     */
    void setHead(E data);

    /**
     * Replaces the last element with the new data
     * @param data the new data
     * @throws IllegalStateException if list is empty
     */
    void setTail(E data);

    /**
     * Replaces the element at the specified index
     * @param index the position to replace
     * @param data the new data
     * @throws IndexOutOfBoundsException if index is invalid
     */
    void setElementAtIndex(int index, E data);

    // ========== UTILITY OPERATIONS ==========
    /**
     * Converts the list to an array
     * @return an array containing all elements
     */
    E[] toArray();

    /**
     * Returns a string representation of the list
     * @return string representation
     */
    String toString();
}
