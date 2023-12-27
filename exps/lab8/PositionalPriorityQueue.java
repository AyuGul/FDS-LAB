import java.util.NoSuchElementException;

/**
 * A priority queue implemented using a positional list, where each element has a unique position
 * within the list.
 *
 * @param <K> the type of keys stored in the priority queue
 * @param <V> the type of values stored in the priority queue
 */
public class PositionalPriorityQueue<K extends Comparable<K>, V> {
    /**
     * The underlying positional list to store the entries of the priority queue.
     */
    private PositionalList<PositionalPriorityQueueEntry<K, V>> data;

    /**
     * Constructs an empty positional priority queue using a positional linked list.
     */
    public PositionalPriorityQueue() {
        data = new PositionalLinkedList<>();
    }

    /**
     * Inserts a new entry with the specified key and value into the priority queue.
     *
     * @param key   the key of the entry
     * @param value the value associated with the key
     * @return the position of the newly inserted entry
     */
    public Position<PositionalPriorityQueueEntry<K, V>> insert(K key, V value) {
        PositionalPriorityQueueEntry<K, V> entry = new PositionalPriorityQueueEntry<>(key, value, null);
        Position<PositionalPriorityQueueEntry<K, V>> current = data.first();
        while (current != null && key.compareTo(current.getElement().getKey()) >= 0) {
            current = data.after(current);
        }
        if (current == null) {
            return data.addLast(entry);
        } else {
            return data.before(current, entry);
        }
    }


    /**
     * Retrieves the entry with the minimum key from the priority queue.
     *
     * @return the entry with the minimum key
     * @throws NoSuchElementException if the priority queue is empty
     */
    public PositionalPriorityQueueEntry<K, V> min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        Position<PositionalPriorityQueueEntry<K, V>> minPosition = data.first();
        return minPosition.getElement();
    }

    /**
     * Removes and returns the entry with the minimum key from the priority queue.
     *
     * @return the entry with the minimum key
     * @throws NoSuchElementException if the priority queue is empty
     */
    public PositionalPriorityQueueEntry<K, V> removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        Position<PositionalPriorityQueueEntry<K, V>> minPosition = findMinPosition();
        PositionalPriorityQueueEntry<K, V> minEntry = minPosition.getElement();
        data.remove(minPosition);
        return minEntry;
    }

    private Position<PositionalPriorityQueueEntry<K, V>> findMinPosition() {
        Position<PositionalPriorityQueueEntry<K, V>> minPosition = data.first();
        for (Position<PositionalPriorityQueueEntry<K, V>> position : data.positions()) {
            if (position.getElement().getKey().compareTo(minPosition.getElement().getKey()) < 0) {
                minPosition = position;
            }
        }
        return minPosition;
    }


    /**
     * Returns the number of entries in the priority queue.
     *
     * @return the size of the priority queue
     */
    public int size() {
        return data.size();
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Displays the contents of the priority queue, showing key-value pairs in parentheses.
     */
    public void display() {
        for (Position<PositionalPriorityQueueEntry<K, V>> position : data.positions()) {
            PositionalPriorityQueueEntry<K, V> entry = position.getElement();
            System.out.println("(" + entry.getKey() + ", " + entry.getValue() + ")");
        }
    }
}