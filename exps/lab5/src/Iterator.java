package src;

import java.util.NoSuchElementException;
/**
 * An interface representing an iterator for a collection of elements.
 * It provides methods to check for the presence of the next element,
 * retrieve the next element, and remove the last retrieved element.
 *
 * @param <E> The type of elements that this iterator iterates over.
 */
public interface Iterator<E> {
    /**
     * Checks if there are more elements in the collection to be iterated.
     *
     * @return true if there are more elements, false otherwise.
     */
    public boolean hasNext();

    /**
     * Retrieves the next element from the collection.
     *
     * @return The next element in the iteration.
     * @throws NoSuchElementException if there are no more elements to iterate.
     */
    public E next() throws NoSuchElementException;

    /**
     * Removes the last retrieved element from the collection.
     *
     * @throws IllegalStateException if the remove operation is not supported
     *                               or if it's not allowed at the current state
     *                               of the iteration.
     */
    public void remove() throws IllegalStateException;
}
