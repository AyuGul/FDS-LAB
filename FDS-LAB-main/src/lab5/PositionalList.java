public interface Position<E> {
    /**
     * Returns the element stored at this position.
     *
     * @return the stored element
     * @throws IllegalStateException if position no longer valid
     */
    E getElement() throws IllegalStateException;
}

/**
 * An interface for positional lists.
 */
public interface PositionalList<E> {

    /**
     * Returns the number of elements in the list.
     */
    int size();

    /**
     * Tests whether the list is empty.
     */
    boolean isEmpty();

    /**
     * Returns the first Position in the list (or null, if empty).
     */
    Position<E> first();

    /**
     * Returns the last Position in the list (or null, if empty).
     */
    Position<E> last();

    /**
     * Returns the Position immediately before Position p (or null, if p is first).
     */
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the Position immediately after Position p (or null, if p is last).
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**
     * Inserts element e at the front of the list and returns its new Position.
     */
    Position<E> addFirst(E e);

    /**
     * Inserts element e at the back of the list and returns its new Position.
     */
    Position<E> addLast(E e);

    /**
     * Inserts element e immediately before Position p and returns its new Position.
     */
    Position<E> addBefore(Position<E> p, E e)
            throws IllegalArgumentException;

    /**
     * Inserts element e immediately after Position p and returns its new Position.
     */
    Position<E> addAfter(Position<E> p, E e)
            throws IllegalArgumentException;

    /**
     * Replaces the element stored at Position p and returns the replaced element.
     */
    E set(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Removes the element stored at Position p and returns it (invalidating p).
     */
    E remove(Position<E> p) throws IllegalArgumentException;
}

public class PostionalList<E> implements PostionList<E> {
    private static class Node<E> implements Postion<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() throws IllegalStateException {
            if (next == null)
                throw new IllegalStateException("Postion no longer valid");
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }
        private Node<E> header;
        private Node<E> trailer;
        private int size = 0;

        public PostionalList() {
            header = new Node<>(null, null, null);
            trailer = new Node<>(null, header, null);
            header.setNext(trailer);
        }

        private Node<E> validate(Node<E> p) throws IllegalArgumentException {
            if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid Postion");
            Node<E> node = (Node<E>) p;
            if (node.getNext() == null) {
                throw new IllegalArgumentException("p is not a valid postion anymore");
            }
            return node;
        }

        private Position<E> position(Node<E> node) {
            if (node == header || node == trailer)
                return null; // do not expose user to the sentinels
            return node;
        }

        public int size(){
            return size;
        }

        public boolean isEmpty(){
            return size==0;
        }

        public Position<E> first(){
            return postion(header.getNext());
        }

        public Position<E> last(){
            return postion(trailer.getPrev());
        }

        public Position<E> before(Position<E> p) throws IllegalArgumentException{
            Node<E> node =validate(p);
            return position(node.getPrev());
        }

        public Position<E> after(Position<E> p) throws IllegalArgumentException{
            Node<E> node =validate(p);
            return position(node.getNext());
        }

        private Position<E> addBetween(E e, Node<E> pre, Node<E> nex){
            Node<E> newest = new Node<>(e,pre,nex);
            pre.setNext(newest);
            nex.setPrev(newest);
            size++;
            return newest;
        }

        public Position<E> addFirst(E e){
            return addBetween(e, header, header.getNext());
        }

        public Position<E> addLast(E e){
            return addBetween(e, trailer.getPrev(),trailer );
        }

        public Position<E> addBefore(Postion<E> p, E e) throws IllegalArgumentException{
            Node<E> node = validate(p);
            return addBetween(e, node.getPrev(),node)
        }

        public Position<E> addAfter(Postion<E> p, E e) throws IllegalArgumentException{
            Node<E> node = validate(p);
            return addBetween(e, node,node.getNext())
        }

        public E set(Position<E> p, E e) throws IllegalArgumentException{
            Node<E> node = validate(p);
            ans = node.getElement();
            node.setElement(e);
            return ans;
        }

        public E remove(Position<E> p){
            Node<E> node = validate(p);
            Node<E> pre  = node.getPrev();
            Node<E> nex = node.getNext();
            pre.setNext(nex);
            nex.setPrev(pre);
            ans = node.getElement();
            size--;
            node = null;
            return ans;
        }


}