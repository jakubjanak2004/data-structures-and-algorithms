package dsa.dataStructures.table;

import dsa.dataStructures.set.hashSet.HashSet;

public class HashTable<K, V> {
    private final HashSet<Pair<K, V>> hashSet;

    public HashTable(HashSet<Pair<K, V>> hashSet) {
        this.hashSet = hashSet;
    }

    public int size() {
        return hashSet.size();
    }

    // insert
    public void put(K key, V value) {
        hashSet.addOrReplace(new Pair<>(key, value));
    }

    // delete
    public void remove(K key) {
        hashSet.remove(new Pair<>(key, null));
    }

    public boolean containsKey(K key) {
        return hashSet.contains(new Pair<>(key, null));
    }

    public V get(K key) {
        Pair<K, V> returnedPair = hashSet.get(new Pair<>(key, null));
        return returnedPair == null ? null : returnedPair.getValue();
    }
}