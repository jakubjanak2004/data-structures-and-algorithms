package dsa.dataStructures.table;

import dsa.dataStructures.set.sortedSet.TreeSet;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

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
    public V getValue(K key) {
        TreePair<K, V> returnedPair = treeSet.get(new TreePair<>(key, null));
        return returnedPair == null ? null : returnedPair.getValue();
    }

    @Override
    public K getKey(K key) {
        TreePair<K, V> returnedPair = treeSet.get(new TreePair<>(key, null));
        return returnedPair == null ? null : returnedPair.getKey();
    }

    @Override
    public Table<K, V> computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Pair<K, V> pair = treeSet.get(new TreePair<>(key, null));
        if (pair == null) {
            treeSet.add(new TreePair<>(key, mappingFunction.apply(key)));
        }
        return this;
    }

    @Override
    public Table<K, V> computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        // if pair found proceed
        Pair<K, V> pair = treeSet.get(new TreePair<>(key, null));
        if (pair == null) return this;
        // compute the newValue using passed function
        V newValue = remappingFunction.apply(key, pair.getValue());
        // if new Value is null remove the whole pair
        if (newValue == null) {
            treeSet.remove(new TreePair<>(key, null));
        } else {
            treeSet.addOrReplace(new TreePair<>(key, newValue));
        }
        return this;
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
