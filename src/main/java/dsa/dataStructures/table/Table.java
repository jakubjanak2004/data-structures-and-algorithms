package dsa.dataStructures.table;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Table<K, V> {
    int size();
    void put(K key, V value);
    void remove(K key);
    boolean containsKey(K key);
    V getValue(K key);
    K getKey(K key);
    Table<K, V> computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);
    Table<K, V> computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    class Pair<K, V> {
        protected final K key;
        protected final V value;

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

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
    }
}
