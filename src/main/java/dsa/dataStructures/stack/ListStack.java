package dsa.dataStructures.stack;

import dsa.dataStructures.list.ListNode;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implements stack using a Singly Linked List. The biggest advantage is that here is no maximum capacity of this stack.
 * Top of stack is actually the first element in the Linked List.
 * @param <T> the types of elements stored in the ListStack
 */
public class ListStack<T extends Comparable<T>> implements Stack<T> {
    private ListNode<T> topOfStack;
    int length;

    @Override
    public boolean isEmpty() {
        return topOfStack == null;
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        topOfStack = topOfStack.getNextNode();
        length--;
    }

    @Override
    public T top() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return topOfStack.getValue();
    }

    @Override
    public void push(T t) {
        topOfStack = new ListNode<>(t, topOfStack);
        length++;
    }

    /**
     * When computing the length by iterating over all elements, the time complexity is {@code O(n)}.
     * <pre><code>
     * int count = 0;
     * ListNode<T> head = topOfStack;
     * while (head != null) {
     *     count++;
     *     head = head.getNextNode();
     * }
     * return count;
     * </code></pre>
     * This approach was simplified by holding the number of elements in a variable {@code int length;},
     * therefore reducing the time complexity to {@code O(1)}.
     *
     * @return the length of the stack
     */
    @Override
    public int length() {
        return length;
    }

    /**
     * Returns he maximal element in the stack, if there are multiple maximal elements only the first one will be returned.
     * The complexity is {@code O(n)}.
     * This method can also be optimised by storing the maximum element in a {@code T max;} field.
     * @return th maximal element
     */
    @Override
    public T max() {
        if (isEmpty()) {
            return null;
        }

        T max = topOfStack.getValue();
        ListNode<T> head = topOfStack.getNextNode();

        while (head != null) {
            T headValue = head.getValue();
            if (Objects.compare(headValue, max, Comparator.naturalOrder()) > 0) {
                max = headValue;
            }
            head = head.getNextNode();
        }
        return max;
    }
}
