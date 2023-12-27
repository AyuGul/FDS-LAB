/**
 * A positional list interface that represents a collection of elements
 * where each element has a unique position within the list.
 *
 * @param <E> the type of elements stored in the positional list
 */
public interface PositionalList<E> {
    /**
     * Adds a new element to the front of the list.
     *
     * @param e the element to be added
     * @return the position of the newly added element
     */
    Position<E> addFirst(E e);

    /**
     * Adds a new element to the end of the list.
     *
     * @param e the element to be added
     * @return the position of the newly added element
     */
    Position<E> addLast(E e);

    /**
     * Adds a new element before the specified position.
     *
     * @param p the position before which the element is added
     * @param e the element to be added
     * @return the position of the newly added element
     */
    Position<E> before(Position<E> p, E e);

    /**
     * Adds a new element after the specified position.
     *
     * @param p the position after which the element is added
     * @param e the element to be added
     * @return the position of the newly added element
     */
    Position<E> after(Position<E> p, E e);

    /**
     * Replaces the element at the specified position with the given element.
     *
     * @param p the position of the element to be replaced
     * @param e the new element
     * @return the old element
     */
    E set(Position<E> p, E e);

    /**
     * Removes the element at the specified position.
     *
     * @param p the position of the element to be removed
     * @return the removed element
     */
    E remove(Position<E> p);

    /**
     * Returns the first position in the list.
     *
     * @return the first position or null if the list is empty
     */
    Position<E> first();

    /**
     * Returns the last position in the list.
     *
     * @return the last position or null if the list is empty
     */
    Position<E> last();

    /**
     * Returns the position before the specified position.
     *
     * @param p the position to find the predecessor of
     * @return the position before p or null if p is the first position
     */
    Position<E> before(Position<E> p);

    /**
     * Returns the position after the specified position.
     *
     * @param p the position to find the successor of
     * @return the position after p or null if p is the last position
     */
    Position<E> after(Position<E> p);

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    int size();

    /**
     * Returns an iterable collection of positions in the list.
     *
     * @return an iterable collection of positions
     */
    Iterable<Position<E>> positions();
}
