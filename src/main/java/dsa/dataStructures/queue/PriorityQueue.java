package dsa.dataStructures.queue;

import java.util.NoSuchElementException;

public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] values;
    private int lastElementIndex = 0;

    @SuppressWarnings("unchecked assignment")
    public PriorityQueue() {
        values = (T[]) new Comparable[DEFAULT_SIZE];
    }

    @SuppressWarnings("unchecked assignment")
    public PriorityQueue(int size) {
        if (size >= DEFAULT_SIZE) {
            values = (T[]) new Comparable[size];
        } else {
            values = (T[]) new Comparable[DEFAULT_SIZE];
        }
    }

    @Override
    public boolean isEmpty() {
        return lastElementIndex == 0;
    }

    @Override
    public void dequeue() {
        values[0] = values[--lastElementIndex];
        heapifyDown(0);
    }

    @Override
    public T get() {
        if (isEmpty()) throw new NoSuchElementException("PriorityQueue is empty");
        return values[0];
    }

    @Override
    @SuppressWarnings("unchecked assignment")
    public void put(T t) {
        if (lastElementIndex == values.length) {
            T[] NewElements = (T[]) new Comparable[values.length * 2];
            System.arraycopy(values, 0, NewElements, 0, values.length);
            values = NewElements;
        }
        values[lastElementIndex] = t;
        heapifyUp(lastElementIndex);
        lastElementIndex++;
    }

    private void heapifyDown(int index) {
        int biggestIndex = index;
        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);
        if (leftChildIndex < lastElementIndex && values[leftChildIndex].compareTo(values[biggestIndex]) > 0)
            biggestIndex = leftChildIndex;
        if (rightChildIndex < lastElementIndex && values[rightChildIndex].compareTo(values[biggestIndex]) > 0)
            biggestIndex = rightChildIndex;
        if (biggestIndex != index) {
            T temp = values[index];
            values[index] = values[biggestIndex];
            values[biggestIndex] = temp;
            heapifyDown(biggestIndex);
        }
    }

    private void heapifyUp(int index) {
        int parentIndex = parent(index);
        if (parentIndex >= 0 && values[parentIndex].compareTo(values[index]) < 0) {
            T temp = values[index];
            values[index] = values[parentIndex];
            values[parentIndex] = temp;
            heapifyUp(parentIndex);
        }
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }
}
