package dsa.dataStructures.stack;

public interface Stack<T> {
    boolean isEmpty();
    void pop();
    T top();
    void push(T t);
    int length();
}
