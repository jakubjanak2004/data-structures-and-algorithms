package dsa.dataStructures.set;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.linearLinkedList.SinglyLinkedList;

import java.util.ArrayList;

public class ChainedHashSet<T> extends HashSet<T> {
    private ArrayList<LinkedList<T>> buckets;

    public ChainedHashSet() {
        buckets = new ArrayList<>();
        for (int i = 0; i < HashSet.INITIAL_TABLE_CAPACITY; i++) {
            buckets.add(new SinglyLinkedList<>());
        }
    }

    @Override
    public T get(T element) {
        int index = hash(element, buckets.size());
        return buckets.get(index).get(element);
    }

    @Override
    public void add(T element) {
        int index = hash(element, buckets.size());
        if (!buckets.get(index).contains(element)) {
            buckets.get(index).insert(element);
            numOfUniqueEntries++;
        }
        if (((double) numOfUniqueEntries) >= GROW * buckets.size()) {
            growValuesArray();
        }
    }

    @Override
    public void addOrReplace(T element) {
        int index = hash(element, buckets.size());
        if (!buckets.get(index).contains(element)) {
            buckets.get(index).insert(element);
            numOfUniqueEntries++;
        } else {
            buckets.get(index).remove(element);
            buckets.get(index).insert(element);
        }
        if (((double) numOfUniqueEntries) >= GROW * buckets.size()) {
            growValuesArray();
        }
    }

    @Override
    public boolean contains(T element) {
        int index = hash(element, buckets.size());
        return buckets.get(index).contains(element);
    }

    @Override
    public void remove(T element) {
        int index = hash(element, buckets.size());
        if (buckets.get(index).contains(element)) {
            numOfUniqueEntries--;
            buckets.get(index).remove(element);
        }
        if (buckets.size() > HashSet.INITIAL_TABLE_CAPACITY && ((double) numOfUniqueEntries) <= SHRINK * buckets.size()) {
            shrinkValuesArray();
        }
    }

    @Override
    protected void growValuesArray() {
        ArrayList<LinkedList<T>> newBuckets = new ArrayList<>();
        for (int i = 0; i < buckets.size() * 2; i++) {
            newBuckets.add(new SinglyLinkedList<>());
        }
        rehash(newBuckets);
    }

    @Override
    protected void shrinkValuesArray() {
        ArrayList<LinkedList<T>> newBuckets = new ArrayList<>();
        for (int i = 0; i < buckets.size() / 2; i++) {
            newBuckets.add(new SinglyLinkedList<>());
        }
        rehash(newBuckets);
    }

    protected void rehash(ArrayList<LinkedList<T>> newBuckets) {
        for (LinkedList<T> bucket : buckets) {
            for (T element : bucket) {
                int index = hash(element, newBuckets.size());
                if (!newBuckets.get(index).contains(element)) {
                    newBuckets.get(index).insert(element);
                }
            }
        }
        buckets = newBuckets;
    }
}
