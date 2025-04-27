package dsa.dataStructures.queue;

import dsa.dataStructures.list.ListNode;

/**
 * A queue implementation based on a singly linked list.
 *
 * <p>The {@code ListQueue} class represents a First-In-First-Out (FIFO) data structure
 * where elements are inserted at the rear (tail) and removed from the front (head).
 *
 * <p>This implementation is simpler and more flexible than array-based queues like
 * {@code CircularArrayQueue}, as it does not require manual resizing or index wrapping.
 *
 * @param <T> the type of elements held in this queue
 */
public class ListQueue<T> implements Queue<T> {
    private ListNode<T> head;
    private ListNode<T> tail;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        head = head.getNextNode();
    }

    @Override
    public T get() {
        if (head != null) {
            return head.getValue();
        }
        return null;
    }

    @Override
    public void put(T t) {
        if (isEmpty()) {
            tail = head = new ListNode<>(t);
        } else {
            tail.setNextNode(new ListNode<>(t));
            tail = tail.getNextNode();
        }
    }
}
