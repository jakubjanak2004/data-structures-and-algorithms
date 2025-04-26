package dsa.dataStructures.list;

// todo extends from java list
public abstract class LinkedList<T> {
    protected ListNode<T> head;
    protected ListNode<T> tail;
    protected ListNode<T> point;
    protected int len;

    public int length() {
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
            if (t == point.getValue()) {
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
            if (t == point.getValue()) {
                delete();
                break;
            }
            next();
        }
        point = oldPoint;
    }

    abstract public void insert(T t);

    abstract public void delete();

    abstract public T first();

    abstract public T last();

    abstract public void next();

    abstract public void prev();
}
