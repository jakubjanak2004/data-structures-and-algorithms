package dsa.dataStructures.queue;

/**
 * A generic Queue interface representing a First-In-First-Out (FIFO) data structure.
 *
 * @param <T> the type of elements stored in the queue
 */
public interface Queue<T> {
    boolean isEmpty();

    /**
     * Removes the front element from the queue.
     *
     * <p>If the queue is empty, this method throws RuntimeException</p>
     */
    void dequeue();
    T get();
    void put(T t);
}
