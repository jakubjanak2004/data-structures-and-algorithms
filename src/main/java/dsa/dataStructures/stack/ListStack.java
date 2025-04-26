package dsa.dataStructures.stack;

import dsa.dataStructures.list.ListNode;

public class ListStack<T> implements Stack<T> {
    private ListNode<T> topOfStack;

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
    }

    @Override
    public int length() {
        int count = 0;
        ListNode<T> head = topOfStack;
        while (head != null) {
            count++;
            head = head.getNextNode();
        }
        return count;
    }
}
