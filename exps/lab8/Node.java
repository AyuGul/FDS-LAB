/**
 * A node class that implements the Position interface.
 *
 * @param <E> the type of elements held in the node
 */
public class Node<E> implements Position<E> {
    /**
     * The element stored in the node.
     */
    E element;

    /**
     * Reference to the previous node.
     */
    private Node<E> prev;

    /**
     * Reference to the next node.
     */
    private Node<E> next;

    /**
     * Constructs a node with the specified element, previous node, and next node.
     *
     * @param e the element to be stored in the node
     * @param p the previous node in the sequence
     * @param n the next node in the sequence
     */
    public Node(E e, Node<E> p, Node<E> n) {
        element = e;
        prev = p;
        next = n;
    }

    /**
     * Retrieves the element stored in the node.
     *
     * @return the element stored in the node
     * @throws IllegalStateException if the node's next reference is null
     */
    public E getElement() throws IllegalStateException {
        if (next == null) {
            throw new IllegalStateException("Position no longer valid");
        }
        return element;
    }

    /**
     * Retrieves the previous node in the sequence.
     *
     * @return the previous node
     */
    public Node<E> getPrev() {
        return prev;
    }

    /**
     * Retrieves the next node in the sequence.
     *
     * @return the next node
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * Sets the previous node reference.
     *
     * @param p the node to set as the previous node
     */
    public void setPrev(Node<E> p) {
        prev = p;
    }

    /**
     * Sets the next node reference.
     *
     * @param n the node to set as the next node
     */
    public void setNext(Node<E> n) {
        next = n;
    }
}
