package dsa.dataStructures.stack;

/**
 * A generic Stack interface representing a Last-In-First-Out (LIFO) data structure.
 * Only the last inserted element is accessible.
 *
 * @param <T> the type of elements stored in the stack
 */
public interface Stack<T extends Comparable<T>> {
    boolean isEmpty();
    void pop();
    T top();
    void push(T t);
    int length();
    T max();
}