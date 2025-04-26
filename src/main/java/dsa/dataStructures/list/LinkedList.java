package dsa.dataStructures.list;

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
    abstract public void insert(T t);
    abstract public void delete();
    abstract public T first();
    abstract public T last();
    abstract public void next();
    abstract public void prev();
}
