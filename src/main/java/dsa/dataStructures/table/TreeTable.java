package dsa.dataStructures.table;

import dsa.dataStructures.set.sortedSet.treeSet.TreeSet;
import org.jetbrains.annotations.NotNull;

public class TreeTable<K extends Comparable<K>, V> implements Table<K, V> {
    private final TreeSet<TreePair<K, V>> treeSet;

    public TreeTable() {
        this.treeSet = new TreeSet<>();
    }

    public TreeTable(TreeSet<TreePair<K, V>> tree) {
        this.treeSet = tree;
    }

    @Override
    public int size() {
        return treeSet.size();
    }

    @Override
    public void put(K key, V value) {
        treeSet.addOrReplace(new TreePair<>(key, value));
    }

    @Override
    public void remove(K key) {
        treeSet.remove(new TreePair<>(key, null));
    }

    @Override
    public boolean containsKey(K key) {
        return treeSet.contains(new TreePair<>(key, null));
    }

    @Override
    public V get(K key) {
        TreePair<K, V> returnedPair = treeSet.get(new TreePair<>(key, null));
        return returnedPair == null ? null : returnedPair.getValue();
    }

    public static class TreePair<K extends Comparable<K>, V> extends Pair<K, V> implements Comparable<TreePair<K,V>> {

        public TreePair(K key, V value) {
            super(key, value);
        }

        @Override
        public int compareTo(@NotNull TreePair<K, V> o) {
            return this.key.compareTo(o.key);
        }
    }
}
