/**
 * An entry in a positional priority queue, holding a key, a value, and its position in the queue.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class PositionalPriorityQueueEntry<K, V> {
    /**
     * The key of the entry.
     */
    private K key;

    /**
     * The value associated with the key.
     */
    private V value;

    /**
     * The position of the entry in the priority queue.
     */
    private Position<PositionalPriorityQueueEntry<K, V>> position;

    /**
     * Constructs a PositionalPriorityQueueEntry with the specified key, value, and position.
     *
     * @param key      the key of the entry
     * @param value    the value associated with the key
     * @param position the position of the entry in the priority queue
     */
    public PositionalPriorityQueueEntry(K key, V value, Position<PositionalPriorityQueueEntry<K, V>> position) {
        this.key = key;
        this.value = value;
        this.position = position;
    }

    /**
     * Retrieves the key of the entry.
     *
     * @return the key of the entry
     */
    public K getKey() {
        return key;
    }

    /**
     * Retrieves the value associated with the key.
     *
     * @return the value associated with the key
     */
    public V getValue() {
        return value;
    }

    /**
     * Retrieves the position of the entry in the priority queue.
     *
     * @return the position of the entry
     */
    public Position<PositionalPriorityQueueEntry<K, V>> getPosition() {
        return position;
    }

    /**
     * Sets the position of the entry in the priority queue.
     *
     * @param position the new position of the entry
     */
    public void setPosition(Position<PositionalPriorityQueueEntry<K, V>> position) {
        this.position = position;
    }
}
