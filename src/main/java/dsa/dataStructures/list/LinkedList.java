package dsa.dataStructures.list;

import java.util.Objects;

public abstract class LinkedList<T> implements List<T>, Iterable<T> {
    protected ListNode<T> head;
    protected ListNode<T> tail;
    protected ListNode<T> point;
    protected int len;

    public int size() {
        return len;
    }

    public boolean empty() {
        return head == null;
    }

    public T read() {
        if (point != null) {
            return point.getValue();
        }
        return null;
    }

    public boolean contains(T t) {
        ListNode<T> oldPoint = point;
        first();
        boolean wasFound = false;
        while (point != null) {
            if (Objects.equals(t, point.getValue())) {
                wasFound = true;
                break;
            }
            next();
        }
        point = oldPoint;
        return wasFound;
    }

    public void remove(T t) {
        ListNode<T> oldPoint = point;
        first();
        while (point != null) {
            if (Objects.equals(t, point.getValue())) {
                delete();
                break;
            }
            next();
        }
        point = oldPoint;
    }

    public T get(T t) {
        T value = null;
        ListNode<T> oldPoint = point;
        first();
        while (point != null) {
            if (Objects.equals(t, point.getValue())) {
                value = point.getValue();
                break;
            }
            next();
        }
        point = oldPoint;
        return value;
    }
}
