package dsa.dataStructures.table;

import java.util.Objects;

public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(key);
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this)
            return true;

        return o instanceof Pair<?, ?> element
                && Objects.equals(key, element.getKey());
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
