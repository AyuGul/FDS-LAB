/**
 * Interface for a Queue data structure.
 *
 * @param <E> The type of elements stored in the Queue.
 */
interface Queue<E> {
    /**
     * Returns the number of elements in the Queue.
     *
     * @return The number of elements in the Queue.
     */
    int size();

    /**
     * Checks if the Queue is empty.
     *
     * @return True if the Queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Enqueues an element into the Queue.
     *
     * @param e The element to be enqueued.
     */
    void enqueue(E e);

    /**
     * Returns the element at the front of the Queue without removing it.
     *
     * @return The element at the front of the Queue, or null if the Queue is empty.
     */
    E first();

    /**
     * Dequeues and returns the element at the front of the Queue.
     *
     * @return The element at the front of the Queue, or null if the Queue is empty.
     */
    E dequeue();
}

/**
 * Implementation of a Queue using an array.
 *
 * @param <E> The type of elements stored in the Queue.
 */
public class arrayQueue<E> implements Queue<E> {
    private E[] data;
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    /**
     * Constructor for creating an ArrayQueue with a specified capacity.
     *
     * @param capacity The capacity of the ArrayQueue.
     */
    public ArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full. Cannot enqueue.");
        }
        rear = (rear + 1) % data.length;
        data[rear] = e;
        size++;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E out = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return out;
    }
}


class roundRobin {
    static class Task {
        private int timeRequired;
        private final String name;

        public Task(String n, int t) {
            timeRequired = t;
            name = n;
        }
        public void decrementTime(){
            timeRequired--;
        }

        public String getName() {
            return name;
        }

        public int getTimeRequired() {
            return timeRequired;
        }
    }
    private final arrayQueue<Task> schedule;
    public roundRobin(int n){
        schedule = new arrayQueue<>(n);
    }
    public void addTask(String n, int t){
        Task newest = new Task(n, t);
        schedule.enqueue(newest);
    }
    public void start() throws InterruptedException {
        while(!schedule.isEmpty()){
            Task work = schedule.dequeue();
            System.out.println("Executing task "+work.getName()+"...");
            work.decrementTime();
            Thread.sleep(1000);
            if(work.getTimeRequired()!=0) {
                schedule.enqueue(work);
            }
        }
        System.out.println("All tasks are completed!");
    }
}
