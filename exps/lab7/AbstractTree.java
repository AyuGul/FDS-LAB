
/**
 * An abstract class that provides common implementations for certain methods of the Tree interface.
 *
 * @param <E> The type of elements stored in the tree.
 */
public abstract class AbstractTree<E> implements Tree<E> {

    /**
     * Checks if the specified Position is external, meaning it has no children.
     *
     * @param p The Position to be checked.
     * @return true if the Position is external (has no children), false otherwise.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    /**
     * Checks if the specified Position is internal, meaning it has one or more children.
     *
     * @param p The Position to be checked.
     * @return true if the Position is internal (has children), false otherwise.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    /**
     * Checks if the specified Position is the root of the tree.
     *
     * @param p The Position to be checked.
     * @return true if the Position is the root of the tree, false otherwise.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    /**
     * Checks if the tree is empty, i.e., it contains no elements.
     *
     * @return true if the tree is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
