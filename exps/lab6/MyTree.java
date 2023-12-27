import java.util.*;

/**
 * A generic tree data structure that implements the Tree interface.
 *
 * @param <E> The type of elements stored in the tree.
 */
public class MyTree<E> extends AbstractTree<E> {

      /**
     * Inner Node class representing a Position with an element, parent, and children.
     */
    private static class Node<E> implements Position<E> {
        private final E element;
        private final Node<E> parent;
        private final LinkedList<Node<E>> children;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
            this.children = new LinkedList<>();
        }

        @Override
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public LinkedList<Node<E>> getChildren() {

            return children;
        }

        public void addChild(Node<E> child) {
            children.add(child);
        }


    }
    private final Node<E> root;
    private int size;
    /**
     * Constructs a generic tree with the specified root element.
     *
     * @param rootElement The root element of the tree.
     */

    public MyTree(E rootElement) {
        root = new Node<>(rootElement, null);
        size = 1;
    }

    /**
     * Returns the root Position of the tree.
     *
     * @return The root Position of the tree.
     */
    @Override
    public Position<E> root() {
        return root;
    }

     /**
     * Validates and returns a Node object from a given Position.
     *
     * @param p The Position to be validated.
     * @return The corresponding Node object.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid Position");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == null&&p!=root()) {
            throw new IllegalArgumentException("Node not in tree any more");
        }
        return node;
    }

      /**
     * Adds a child with the given element to the specified parent Position.
     *
     * @param parentPosition The parent Position to which the child is added.
     * @param childElement   The element of the child.
     * @return The Position of the newly added child.
     */

    public Position<E> addChild(Position<E> parentPosition, E childElement) {
        Node<E> parent = validate(parentPosition);
        Node<E> child = new Node<>(childElement, parent);
        parent.addChild(child);
        size++;
        return child;
    }


     /**
     * Returns the parent Position of the specified Position.
     *
     * @param p The Position for which the parent Position is to be found.
     * @return The parent Position of the specified Position.
     * @throws IllegalArgumentException if the input Position is invalid.
     */

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }
     /**
     * Returns an iterable collection of child Positions of the specified Position.
     *
     * @param p The Position for which child Positions are to be returned.
     * @return An iterable collection of child Positions.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return new LinkedList<>(node.getChildren());
    }

    /**
     * Returns the number of children of the specified Position.
     *
     * @param p The Position for which the number of children is to be determined.
     * @return The number of children of the specified Position.
     * @throws IllegalArgumentException if the input Position is invalid.
     */
    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getChildren().size();
    }
    /**
     * Returns the total number of elements in the tree.
     *
     * @return The total number of elements in the tree.
     */
    @Override
    public int size() {
        return size;
    }
     /**
     * Returns an iterator for the elements in the tree.
     *
     * @return An iterator for the elements in the tree.
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

     /**
     * Returns an iterable collection of all Positions in the tree in a pre-order traversal.
     *
     * @return An iterable collection of all Positions in the tree.
     */
    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> positions = new LinkedList<>();
        preorderPositions(root, positions);
        return positions;
    }
    // Helper method for in-order traversal of positions
    private void preorderPositions(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> child : children(p)) {
            preorderPositions(child, snapshot);
        }
    }

    /**
     * Returns an iterator that provides sibling Positions of the specified Position.
     *
     * @param position The Position for which sibling Positions are to be found.
     * @return An iterator of sibling Positions.
     */
    public Iterator<Position<E>> findSiblings(Position<E> position) {
        Node<E> currentNode = validate(position);

        // Find the parent of the given position
        Node<E> parent = currentNode.getParent();

        if (parent != null) {
            List<Position<E>> siblings = new LinkedList<>();
            // Iterate through the children of the parent and add siblings to the list
            for (Node<E> child : parent.getChildren()) {
                if (child != currentNode) {
                    siblings.add(child);
                }
            }
            return siblings.iterator();
        }

        // If there are no siblings, return an empty iterator
        return Collections.emptyIterator();
    }
//    /**
//     * Returns an iterator that provides leaf Positions of the tree.
//     *
//     * @return An iterator of leaf Positions in the tree.
//     */
//    public Iterator<Position<E>> listLeaves() {
//        List<Position<E>> leaves = new LinkedList<>();
//        listLeaves(root);
//        return leaves.iterator();
//    }
       /**
     * Returns a list that provides leaf Positions of the tree.
     *
     * @param position The current Position in the tree.
     */
       public List<Position<E>> listLeaves(Position<E> position) {
           List<Position<E>> leaves = new LinkedList<>();

           if (isExternal(position)) {
               leaves.add(position);
           } else {
               for (Position<E> child : children(position)) {
                   leaves.addAll(listLeaves(child)); // Collect results from the recursive call
               }
           }

           return leaves;
       }



//    /**
//     * Returns an iterator that provides internal node Positions of the tree.
//     *
//     * @return An iterator of internal node Positions in the tree.
//     */
//    public Iterator<Position<E>> listInternalNodes() {
//        List<Position<E>> internalNodes = new LinkedList<>();
//        listInternal(root, internalNodes);
//        return internalNodes.iterator();
//    }

    /**
     * Helper method for finding and collecting internal node Positions in a subtree.
     *
     * @param position      The current Position in the tree.
     */

    public List<Position<E>> listInternal(Position<E> position) {
        List<Position<E>> internalNodes = new LinkedList<>();
        if (isInternal(position)) {
            internalNodes.add(position);
        }
        for (Position<E> child : children(position)) {
            internalNodes.addAll(listInternal(child));
        }
        return internalNodes;
    }

    /**
     * Calculates and returns the height of the tree, which is the maximum depth.
     *
     * @return The height of the tree.
     */
    public int findHeight(Position<E> p) {
        int h=0;
        for(Position<E> c:children(p)){
            h=Math.max(h,1+findHeight(c));
        }
        return h;
    }


    /**
     * Finds and returns the depth of a specific Position within the tree.
     *
     * @param target The Position for which depth is to be found.
     * @return The depth of the target Position within the tree.
     */
    public int findDepth(Position<E> target) {
        if(isRoot(target)){
            return 0;
        }
        else{
            return 1 + findDepth(parent(target));
        }
    }

    /**
     * Finds and returns a path from the root to a specific Position.
     *
     * @param currentPosition   The current Position while traversing the tree.
     * @param target           The target Position for which the path is to be found.
     * @param pathElements     A list to collect the elements along the path.
     * @return true if the target Position is found, false otherwise.
     */
    private boolean findPath(Position<E> currentPosition, Position<E> target, List<E> pathElements) {
        pathElements.add(currentPosition.getElement());

        if (currentPosition == target) {
            return true;  // Node found
        }

        for (Position<E> child : children(currentPosition)) {
            if (findPath(child, target, pathElements)) {
                return true;  // Node found in this subtree
            }
        }

        pathElements.remove(pathElements.size() - 1);
        return false;  // Node not found in this subtree
    }

     /**
     * Finds and returns a path from the root to a specific Position, where elements are joined with the specified delimiter.
     *
     * @param target    The Position to which the path is to be found.
     * @param delimiter The delimiter to join elements in the path.
     * @return A string representing the path from the root to the target Position.
     */
    public String findPath(Position<E> target, String delimiter) {
        List<E> pathElements = new LinkedList<>();
        findPath(root, target, pathElements);

        StringBuilder pathBuilder = new StringBuilder();
        for (E element : pathElements) {
            if (pathBuilder.length() > 0) {
                pathBuilder.append(delimiter);
            }
            pathBuilder.append(element);
        }

        return pathBuilder.toString();
    }

    /**
     * Returns an iterator that provides Edge objects representing connections between Positions in the tree.
     *
     * @return An iterator of Edge objects.
     */
    public Iterator<Edge<E>> listEdges() {
        List<Edge<E>> edges = new LinkedList<>();
        listEdges(root, edges);
        return edges.iterator();
    }

    /**
     * Helper method for recursively collecting Edge objects in the tree.
     *
     * @param position The current Position in the tree.
     * @param edges    A list to collect Edge objects.
     */
    private void listEdges(Position<E> position, List<Edge<E>> edges) {
        for (Position<E> child : children(position)) {
            edges.add(new Edge<>(position, child));
            listEdges(child, edges);
        }
    }



    // Inner Node class

    // Inner iterator class
    /**
     * Inner iterator class for iterating through elements in the tree.
     */

    private class ElementIterator implements Iterator<E> {
        private final Iterator<Position<E>> positionIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }
    }

    public void displaySubtree(MyTree<Integer> tree, Position<Integer> position, int depth) {
        Node<Integer> node = tree.validate(position);
        System.out.println("  ".repeat(depth) + "└─ " + node.getElement()); // Display with proper indentation


        for (Position<Integer> child : tree.children(position)) {
            displaySubtree(tree, child, depth + 1);
        }
    }

}

