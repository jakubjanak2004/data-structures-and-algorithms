package dsa.dataStructures.queue;

/**
 * A queue implementation based on a fixed-size circular array.
 *
 * <p>The {@code CircularArrayQueue} represents a First-In-First-Out (FIFO) data structure
 * using a static array where the head and tail indices wrap around the end of the array
 * when reaching the limit.
 *
 * <p>Compared to a linked list queue, this implementation is slightly more complicated
 * due to circular index handling, but offers constant-time operations without object allocation.
 *
 * <p>This queue has a fixed capacity determined at construction time.
 * No dynamic resizing is performed.
 *
 * @param <T> the type of elements stored in the queue
 */
public class CircularArrayQueue<T> implements Queue<T> {
    private static final int DEFAULT_SIZE = 16;
    private T[] values;
    private int currentSize = 0;
    private int head = 0;
    private int tail = -1;

    public CircularArrayQueue() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    public CircularArrayQueue(int size) {
        values = (T[]) new Object[size];
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == values.length;
    }

    @Override
    public void dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        currentSize--;
        head = increment(head);
    }

    @Override
    public T get() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return values[head];
    }

    @Override
    public void put(T t) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        tail = increment(tail);
        values[tail] = t;
        currentSize++;
    }

    /**
     * Circular index handling, when the end reached it returns 0.
     * @param x the index that wants to be incremeented
     * @return incremented index
     */
    private int increment(int x) {
        x++;
        if (x >= values.length) {
            return 0;
        }
        return x;
    }
}
