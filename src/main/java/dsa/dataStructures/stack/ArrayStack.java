package dsa.dataStructures.stack;

public class ArrayStack<T> implements Stack<T> {
    private final T[] values;
    private int topOfStack = -1;

    public ArrayStack(int size) {
        values = (T[]) new Object[size];
    }

    @Override
    public boolean isEmpty() {
        return topOfStack == -1;
    }

    public boolean isFull() {
        return topOfStack == values.length - 1;
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        values[topOfStack] = null;
        topOfStack--;
    }

    @Override
    public T top() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return values[topOfStack];
    }

    @Override
    public void push(T element) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }
        topOfStack++;
        values[topOfStack] = element;
    }

    @Override
    public int length() {
        return topOfStack + 1;
    }

    public int max() {
        return values.length;
    }
}
