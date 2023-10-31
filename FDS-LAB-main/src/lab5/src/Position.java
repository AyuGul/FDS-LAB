package src;

/**
 * An interface representing a position within a data structure that stores an element.
 *
 * @param <E> The type of element stored at this position.
 */
public interface Position<E> {
    /**
     * Returns the element stored at this position.
     *
     * @return The element stored at this position.
     * @throws IllegalStateException if the position is no longer valid or accessible.
     */
    E getElement() throws IllegalStateException;
}
