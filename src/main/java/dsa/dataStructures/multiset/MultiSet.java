package dsa.dataStructures.multiset;

import dsa.dataStructures.table.HashTable;
import dsa.dataStructures.table.Table;

public class MultiSet<T> {
    private final Table<T, Integer> countTable;
    private int length = 0;

    public MultiSet() {
        this.countTable = new HashTable<>();
    }

    public MultiSet(Table<T, Integer> countTable) {
        this.countTable = countTable;
    }

    public int size() {
        return length;
    }

    public void put(T element) {
        // if present the counter is increased, if not the element is added with counter set to 1
        countTable
                .computeIfPresent(element, (k, v) -> v + 1)
                .computeIfAbsent(element, k -> 1);
        length++;
    }

    public void remove(T element) {
        countTable
                .computeIfPresent(element, (k, v) -> {
                    length--;
                    // decrementing the counter
                    if (v > 1) {
                        return v - 1;
                    }
                    // pair removed from the countTable completely
                    return null;
                });
    }

    public T get(T element) {
        return countTable.getKey(element);
    }

    public boolean contains(T element) {
        return countTable.containsKey(element);
    }

    public int count(T element) {
        Integer count = countTable.getValue(element);
        if (count == null) return 0;
        return count;
    }
}
