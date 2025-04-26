package dsa.dataStructures.queue;

import dsa.dataStructures.list.ListNode;

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
