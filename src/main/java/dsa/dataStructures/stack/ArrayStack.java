package dsa.dataStructures.stack;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implements stack using an array of values. You need to provide the size of the stack at the beginning.
 * @param <T> the types of elements stored in the ArrayStack
 */
public class ArrayStack<T extends Comparable<T>> implements Stack<T> {
    private static final int DEFAULT_SIZE = 16;
    private final T[] values;
    private int topOfStack = -1;

    public ArrayStack() {
        values = (T[]) new Comparable[DEFAULT_SIZE];
    }

    public ArrayStack(int size) {
        values = (T[]) new Comparable[size];
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

        T max = values[0];
        for (int i = 1; i < topOfStack; i++) {
            if (Objects.compare(values[i], max, Comparator.naturalOrder()) > 0) {
                max = values[i];
            }
        }
        return max;
    }
}
