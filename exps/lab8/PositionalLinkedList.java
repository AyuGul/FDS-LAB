import java.util.ArrayList;
import java.util.List;

/**
 * A linked list implementation of the PositionalList interface.
 *
 * @param <E> the type of elements held in the list
 */
class PositionalLinkedList<E> implements PositionalList<E> {
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    /**
     * Constructs an empty PositionalLinkedList with dummy header and trailer nodes.
     */
    public PositionalLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid position");
        }
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null) {
            throw new IllegalArgumentException("Position no longer valid");
        }
        return node;
    }

    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }

    /**
     * Adds a new element to the front of the list.
     *
     * @param e the element to add
     * @return the position of the newly added element
     */
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    /**
     * Adds a new element to the end of the list.
     *
     * @param e the element to add
     * @return the position of the newly added element
     */
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    /**
     * Adds a new element before the specified position.
     *
     * @param p the position before which the element is added
     * @param e the element to add
     * @return the position of the newly added element
     * @throws IllegalArgumentException if the position is invalid
     */
    public Position<E> before(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /**
     * Adds a new element after the specified position.
     *
     * @param p the position after which the element is added
     * @param e the element to add
     * @return the position of the newly added element
     * @throws IllegalArgumentException if the position is invalid
     */
    public Position<E> after(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newNode = new Node<>(e, pred, succ);
        pred.setNext(newNode);
        succ.setPrev(newNode);
        size++;
        return newNode;
    }

    /**
     * Replaces the element at the specified position with the given element.
     *
     * @param p the position of the element to replace
     * @param e the new element
     * @return the old element
     * @throws IllegalArgumentException if the position is invalid
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E oldElement = node.getElement();
        node.element = e;
        return oldElement;
    }

    /**
     * Removes the element at the specified position.
     *
     * @param p the position of the element to remove
     * @return the removed element
     * @throws IllegalArgumentException if the position is invalid
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> pred = node.getPrev();
        Node<E> succ = node.getNext();
        pred.setNext(succ);
        succ.setPrev(pred);
        size--;
        E oldElement = node.getElement();
        node.setPrev(null);
        node.setNext(null);
        return oldElement;
    }

    /**
     * Returns the first position in the list.
     *
     * @return the first position or null if the list is empty
     */
    public Position<E> first() {
        return position(header.getNext());
    }

    /**
     * Returns the last position in the list.
     *
     * @return the last position or null if the list is empty
     */
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    /**
     * Returns the position before the specified position.
     *
     * @param p the position to find the predecessor of
     * @return the position before p or null if p is the first position
     * @throws IllegalArgumentException if the position is invalid
     */
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /**
     * Returns the position after the specified position.
     *
     * @param p the position to find the successor of
     * @return the position after p or null if p is the last position
     * @throws IllegalArgumentException if the position is invalid
     */
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterable collection of positions in the list.
     *
     * @return an iterable collection of positions
     */
    public Iterable<Position<E>> positions() {
        List<Position<E>> positionList = new ArrayList<>();
        Position<E> current = first();
        while (current != null) {
            positionList.add(current);
            current = after(current);
        }
        return positionList;
    }
}
