/**
 * Interface for a Stack data structure.
 *
 * @param <E> The type of elements stored in the Stack.
 */
interface Stack<E> {
    /**
     * Returns the number of elements in the Stack.
     *
     * @return The number of elements in the Stack.
     */
    int size();

    /**
     * Checks if the Stack is empty.
     *
     * @return True if the Stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Pushes an element onto the Stack.
     *
     * @param e The element to be pushed onto the Stack.
     */
    void push(E e);

    /**
     * Returns the element at the top of the Stack without removing it.
     *
     * @return The element at the top of the Stack, or null if the Stack is empty.
     */
    E top();

    /**
     * Removes and returns the element at the top of the Stack.
     *
     * @return The element at the top of the Stack, or null if the Stack is empty.
     */
    E pop();
}

/**
 * Implementation of a Stack using an array.
 *
 * @param <E> The type of elements stored in the Stack.
 */
public class arrayStack<E> implements Stack<E> {
    private E[] data;
    private int t = -1;

    /**
     * Constructor for creating an arrayStack with a specified capacity.
     *
     * @param capacity The capacity of the arrayStack.
     */
    public arrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return t + 1;
    }

    public boolean isEmpty() {
        return t == -1;
    }

    public void push(E e) {
        if (t + 1 == data.length) {
            throw new IllegalStateException("Stack is full. Cannot push.");
        }
        if (e == null) {
            throw new IllegalArgumentException("Cannot push null onto the stack.");
        }
        data[++t] = e;
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E out = data[t];
        data[t] = null;
        t--;
        return out;

    }
}
