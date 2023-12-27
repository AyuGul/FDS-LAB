

/**
 * Represents an edge in a tree data structure connecting a parent Position to a child Position.
 *
 * @param <E> The type of elements stored in the tree.
 */
public class Edge<E> {

    private Position<E> parent;
    private Position<E> child;

    /**
     * Constructs an Edge object that connects a parent Position to a child Position.
     *
     * @param parent The parent Position of the edge.
     * @param child  The child Position of the edge.
     */
    public Edge(Position<E> parent, Position<E> child) {
        this.parent = parent;
        this.child = child;
    }

    /**
     * Returns the parent Position of the edge.
     *
     * @return The parent Position of the edge.
     */
    public Position<E> getParent() {
        return parent;
    }

    /**
     * Returns the child Position of the edge.
     *
     * @return The child Position of the edge.
     */
    public Position<E> getChild() {
        return child;
    }
}

