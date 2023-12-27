

import java.util.Iterator;


/**
 * A generic interface for a tree data structure, where each element is associated with a Position.
 * The Position represents a location in the tree.
 *
 * @param <E> The type of elements stored in the tree.
 */
public interface Tree<E> extends Iterable<E> {

    /**
     * Returns the root Position of the tree.
     *
     * @return The root Position of the tree.
     */
    Position<E> root();

    /**
     * Returns the parent Position of the specified Position in the tree.
     *
     * @param p The Position for which the parent Position is to be found.
     * @return The parent Position of the specified Position.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns an iterable collection of child Positions of the specified Position.
     *
     * @param p The Position for which child Positions are to be returned.
     * @return An iterable collection of child Positions.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the number of children of the specified Position.
     *
     * @param p The Position for which the number of children is to be determined.
     * @return The number of children of the specified Position.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    int numChildren(Position<E> p) throws IllegalArgumentException;

    /**
     * Checks if the specified Position is internal, meaning it has one or more children.
     *
     * @param p The Position to be checked.
     * @return true if the Position is internal, false otherwise.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Checks if the specified Position is external, meaning it has no children.
     *
     * @param p The Position to be checked.
     * @return true if the Position is external, false otherwise.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;

    /**
     * Checks if the specified Position is the root of the tree.
     *
     * @param p The Position to be checked.
     * @return true if the Position is the root, false otherwise.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the total number of elements in the tree.
     *
     * @return The total number of elements in the tree.
     */
    int size();

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns an iterator for the elements in the tree.
     *
     * @return An iterator for the elements in the tree.
     */
    @Override
    Iterator<E> iterator();

    /**
     * Returns an iterable collection of all Positions in the tree.
     *
     * @return An iterable collection of all Positions in the tree.
     */
    Iterable<Position<E>> positions();
}
