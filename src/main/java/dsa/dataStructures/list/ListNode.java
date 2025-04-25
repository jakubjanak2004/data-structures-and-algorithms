package dsa.dataStructures.list;

public class ListNode<T> {
    private T value;
    private ListNode<T> nextNode;

    public ListNode(T value) {
        this.value = value;
    }

    public ListNode(T value, ListNode<T> nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ListNode<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(ListNode<T> nextNode) {
        this.nextNode = nextNode;
    }
}
