package dsa.dataStructures;

import dsa.dataStructures.table.Table;

// todo should behave like a Table<K, Integer> when if there is a duplicate being added it increments the integer
public class MultiSet<K> {
    private final Table<K, Integer> countTable;

    public MultiSet(Table<K, Integer> countTable) {
        this.countTable = countTable;
    }
}
