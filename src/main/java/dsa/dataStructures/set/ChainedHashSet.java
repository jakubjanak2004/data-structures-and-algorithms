package dsa.dataStructures.set;

import dsa.dataStructures.list.LinkedList;
import dsa.dataStructures.list.SinglyLinkedList;

import java.util.ArrayList;

// todo implement rehash for when the link lists become too big
public class ChainedHashSet<T> extends HashSet<T> {
    private final ArrayList<LinkedList<T>> buckets;

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
    }
}
