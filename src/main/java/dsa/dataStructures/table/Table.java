package dsa.dataStructures.table;

public interface Table<K, V> {
    int size();
    void put(K key, V value);
    void remove(K key);
    boolean containsKey(K key);
    V get(K key);
}
