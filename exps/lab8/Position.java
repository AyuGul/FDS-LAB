/**
 * A position in a data structure, holding an element.
 *
 * @param <E> the type of element stored in the position
 */
public interface Position<E> {
    /**
     * Retrieves the element stored in this position.
     *
     * @return the element stored in the position
     * @throws IllegalStateException if the position is no longer valid
     */
    E getElement() throws IllegalStateException;
}
