package src;

import java.util.*;

/**
 * An implementation of the PositionList interface that uses a doubly linked list structure
 * to store elements and their positions. This allows for efficient insertion and removal
 * of elements at specified positions within the list.
 *
 * @param <E> The type of elements stored in the PositionalList.
 */
public class PositionalList<E> implements PositionList<E> {

    /**
     * Inner class representing a node in the doubly linked list, implementing the Position interface.
     *
     * @param <E> The type of elements stored in the node.
     */
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        /**
         * Constructs a node with the given element, previous node, and next node.
         *
         * @param e The element to be stored in the node.
         * @param p The previous node in the list.
         * @param n The next node in the list.
         */
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        /**
         * Returns the element stored at this position.
         *
         * @return The stored element.
         * @throws IllegalStateException if the position is no longer valid.
         */
        public E getElement() throws IllegalStateException {
            if (next == null) throw new IllegalStateException("Position no longer valid");
            return element;
        }

        /**
         * Returns the previous node in the list.
         *
         * @return The previous node in the list.
         */
        public Node<E> getPrev() {
            return prev;
        }

        /**
         * Returns the next node in the list.
         *
         * @return The next node in the list.
         */
        public Node<E> getNext() {
            return next;
        }

        /**
         * Sets the element stored in the node.
         *
         * @param e The new element to be stored.
         */
        public void setElement(E e) {
            element = e;
        }

        /**
         * Sets the previous node in the list.
         *
         * @param p The new previous node.
         */
        public void setPrev(Node<E> p) {
            prev = p;
        }

        /**
         * Sets the next node in the list.
         *
         * @param n The new next node.
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size = 0;

    /**
     * Constructs an empty PositionalList with sentinel nodes.
     */
    public PositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Validates a position, ensuring that it is a valid PositionalList Node and still accessible.
     *
     * @param p The position to be validated.
     * @return The validated Node representing the position.
     * @throws IllegalArgumentException if the position is not a valid PositionalList Node or is no longer valid.
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof PositionalList.Node)) throw new IllegalArgumentException("Invalid Position");
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null) {
            throw new IllegalArgumentException("p is not a valid position anymore");
        }
        return node;
    }

    /**
     * Converts a Node back into a Position if it is not a sentinel node (header or trailer).
     * This prevents exposing the user to the sentinel nodes.
     *
     * @param node The node to be converted into a Position.
     * @return The Position representation of the node or null for sentinel nodes.
     */
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) return null; // do not expose user to the sentinels
        return node;
    }

    /**
     * Returns the number of elements in the PositionalList.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the PositionalList is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the position of the first element in the PositionalList.
     *
     * @return The position of the first element or null if the list is empty.
     */
    public Position<E> first() {
        return position(header.getNext());
    }

    /**
     * Returns the position of the last element in the PositionalList.
     *
     * @return The position of the last element or null if the list is empty.
     */
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    /**
     * Returns the position of the element before the given position.
     *
     * @param p The position for which the preceding element is desired.
     * @return The position of the element before the given position or null if p is the first element.
     * @throws IllegalArgumentException if the position is not valid.
     */
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /**
     * Returns the position of the element after the given position.
     *
     * @param p The position for which the following element is desired.
     * @return The position of the element after the given position or null if p is the last element.
     * @throws IllegalArgumentException if the position is not valid.
     */
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }
    /**
     * Inserts a new element with the specified value between two existing nodes in the PositionalList.
     *
     * @param e   The value of the new element to be inserted.
     * @param pre The node that will precede the new element.
     * @param nex The node that will follow the new element.
     * @return The position of the newly added element.
     */
    private Position<E> addBetween(E e, Node<E> pre, Node<E> nex) {
        Node<E> newest = new Node<>(e, pre, nex);
        pre.setNext(newest);
        nex.setPrev(newest);
        size++;
        return newest;
    }


    /**
     * Adds a new element with the specified value to the PositionalList as the first element.
     *
     * @param e The value of the element to be added.
     * @return The position of the newly added element.
     */
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    /**
     * Adds a new element with the specified value to the PositionalList as the last element.
     *
     * @param e The value of the element to be added.
     * @return The position of the newly added element.
     */
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    /**
     * Adds a new element with the specified value before the given position.
     *
     * @param p The position before which the new element will be added.
     * @param e The value of the element to be added.
     * @return The position of the newly added element.
     * @throws IllegalArgumentException if the position is not valid.
     */
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /**
     * Adds a new element with the specified value after the given position.
     *
     * @param p The position after which the new element will be added.
     * @param e The value of the element to be added.
     * @return The position of the newly added element.
     * @throws IllegalArgumentException if the position is not valid.
     */
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    /**
     * Replaces the element at the specified position with the new element value.
     *
     * @param p The position of the element to be replaced.
     * @param e The new value of the element.
     * @return The previous element's value that was replaced.
     * @throws IllegalArgumentException if the position is not valid.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E ans = node.getElement();
        node.setElement(e);
        return ans;
    }

    /**
     * Removes the element at the specified position from the PositionalList.
     *
     * @param p The position of the element to be removed.
     * @return The value of the removed element.
     * @throws IllegalArgumentException if the position is not valid.
     */
    public E remove(Position<E> p) {
        Node<E> node = validate(p);
        Node<E> pre = node.getPrev();
        Node<E> nex = node.getNext();
        pre.setNext(nex);
        nex.setPrev(pre);
        E ans;
        size--;
        ans = node.getElement();
        node.setElement(null);     // help with garbage collection
        node.setNext(null);        // and convention for defunct node
        node.setPrev(null);
        return ans;
    }



    /**
     * An iterator for traversing the positions within the PositionalList.
     */
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor;
        private Position<E> recent;

        /**
         * Constructs a PositionIterator starting from the first position in the list.
         */
        public PositionIterator(){
            cursor = first();
            recent = null;
        }

        /**
         * Checks if there are more positions to iterate.
         *
         * @return true if there are more positions, false otherwise.
         */
        public boolean hasNext() {
            return (cursor != null);
        }

        /**
         * Retrieves the next position in the iteration.
         *
         * @return The next position in the iteration.
         * @throws NoSuchElementException if there are no more positions to iterate.
         */
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        /**
         * Removes the last retrieved position from the list.
         *
         * @throws IllegalStateException if there is nothing to remove.
         */
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            PositionalList.this.remove(recent);
            recent = null;
        }
    }

    /**
     * Returns an iterator for traversing the positions within the PositionalList.
     *
     * @return An iterator for positions in the list.
     */
    public Iterator<Position<E>> iterator() {
        return new PositionIterator();
    }
    /**
     * A private iterable class that provides an iterator for positions (Position<E>) in the list.
     */
    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    /**
     * Returns an iterable for the positions in the linked list.
     *
     * @return An iterable for the positions in the list.
     */
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }


    /**
     * A private iterator class that iterates over the elements stored in the positions of the linked list.
     */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();

        /**
         * Checks if there is a next element in the list.
         *
         * @return true if there is a next element, false otherwise.
         */
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        /**
         * Returns the next element in the list.
         *
         * @return The next element.
         */
        public E next() {
            return posIterator.next().getElement();
        }

        /**
         * Removes the last element returned by the iterator.
         */
        public void remove() {
            posIterator.remove();
        }
    }


}

