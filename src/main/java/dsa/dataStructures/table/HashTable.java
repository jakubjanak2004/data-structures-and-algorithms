package dsa.dataStructures.table;

import dsa.dataStructures.set.hashSet.HashSet;
import dsa.dataStructures.set.hashSet.openAddressingHashSet.DoubleHashingHashSet;

import java.util.function.BiFunction;
import java.util.function.Function;

public class HashTable<K, V> implements Table<K, V> {
    private final HashSet<Pair<K, V>> hashSet;

    public HashTable() {
        this.hashSet = new DoubleHashingHashSet<>();
    }

    public HashTable(HashSet<Pair<K, V>> hashSet) {
        this.hashSet = hashSet;
    }

    @Override
    public int size() {
        return hashSet.size();
    }

    @Override
    public void put(K key, V value) {
        hashSet.addOrReplace(new Pair<>(key, value));
    }

    @Override
    public void remove(K key) {
        hashSet.remove(new Pair<>(key, null));
    }

    @Override
    public boolean containsKey(K key) {
        return hashSet.contains(new Pair<>(key, null));
    }

    @Override
    public V getValue(K key) {
        Pair<K, V> returnedPair = hashSet.get(new Pair<>(key, null));
        return returnedPair == null ? null : returnedPair.getValue();
    }

    @Override
    public K getKey(K key) {
        Pair<K, V> returnedPair = hashSet.get(new Pair<>(key, null));
        return returnedPair == null ? null : returnedPair.getKey();
    }

    @Override
    public Table<K, V> computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Pair<K, V> pair = hashSet.get(new Pair<>(key, null));
        if (pair == null) {
            hashSet.add(new Pair<>(key, mappingFunction.apply(key)));
        }
        return this;
    }

    @Override
    public Table<K, V> computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        // if pair found proceed
        Pair<K, V> pair = hashSet.get(new Pair<>(key, null));
        if (pair == null) return this;
        // compute the newValue using passed function
        V newValue = remappingFunction.apply(key, pair.getValue());
        // if new Value is null remove the whole pair
        if (newValue == null) {
            hashSet.remove(new Pair<>(key, null));
        } else {
            hashSet.addOrReplace(new Pair<>(key, newValue));
        }
        return this;
    }
}