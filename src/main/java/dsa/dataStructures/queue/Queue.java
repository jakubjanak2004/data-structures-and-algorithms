package dsa.dataStructures.queue;

public interface Queue<T> {
    boolean isEmpty();
    void dequeue();
    T get();
    void put(T t);
}
