package dataStructures.set;

import org.jetbrains.annotations.NotNull;

/**
 * Dummy object to see if the add or replace method really replaces the object added for the second time with different value set.
 */
public class KeyValue implements Comparable<KeyValue> {
    int key;
    int value;

    public KeyValue(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(@NotNull KeyValue o) {
        return Integer.compare(this.key, o.key);
    }

    @Override
    public int hashCode() {
        return this.key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof KeyValue) {
            return this.key == ((KeyValue) obj).key;
        }
        return false;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}