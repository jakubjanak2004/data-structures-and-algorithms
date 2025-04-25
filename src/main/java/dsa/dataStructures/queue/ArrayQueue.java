package dsa.dataStructures.queue;

public class ArrayQueue<T> implements Queue<T> {
    private T[] values;
    private int currentSize = 0;
    private int head = 0;
    private int tail = -1;

    public ArrayQueue(int size) {
        values = (T[]) new Object[size];
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public void dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        currentSize--;
        head = increment(head);
    }

    @Override
    public T get() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return values[head];
    }

    @Override
    public void put(T t) {
        if (currentSize == values.length) {
            throw new RuntimeException("Queue is full");
        }
        tail = increment(tail);
        values[tail] = t;
        currentSize++;
    }

    private int increment(int x) {
        x++;
        if (x >= values.length) {
            return 0;
        }
        return x;
    }
}
