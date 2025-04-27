package dsa.dataStructures.Set;

import java.lang.reflect.Array;
import java.util.Objects;

public class LinearProbingHashSet<T> extends HashSet<T> {
    private final static double GROW = 0.7;
    private final static double SHRINK = 0.3;
    private LineEntry<T>[] values;

    public LinearProbingHashSet() {
        values = (LineEntry<T>[]) Array.newInstance(LineEntry.class, HashSet.INITIAL_TABLE_CAPACITY);
    }

    @Override
    public T get(T element) {
        int hash = hash(element, values.length);
        for (int i = 0; i < values.length; i++) {
            int index = (hash + i) % values.length;
            if (values[index] == null) return null;
            if (values[index].isDeleted()) continue;
            if (Objects.equals(values[index].getValue(), element)) return values[index].getValue();
        }
        return null;
    }

    @Override
    public void add(T element) {
        int hash = hash(element, values.length);
        for (int i = 0; i < values.length; i++) {
            int index = (hash + i) % values.length;
            if (values[index] == null) {
                values[index] = new LineEntry<>(element);
                numOfUniqueEntries++;
                break;
            }
            if (Objects.equals(values[index].getValue(), element)) {
                if (values[index].isDeleted()) {
                    values[index] = new LineEntry<>(element);
                    numOfUniqueEntries++;
                }
                break;
            }
        }
        if (((double) numOfUniqueEntries) >= GROW * values.length) {
            growValuesArray();
        }
    }

    private void growValuesArray() {
        LineEntry<T>[] newValues = (LineEntry<T>[]) Array.newInstance(LineEntry.class, values.length * 2);
        rehash(newValues);
    }

    private void shrinkValuesArray() {
        LineEntry<T>[] newValues = (LineEntry<T>[]) Array.newInstance(LineEntry.class, values.length / 2);
        rehash(newValues);
    }

    private void rehash(LineEntry<T>[] newValues) {
        for (LineEntry<T> lineEntry : values) {
            if (lineEntry == null || lineEntry.isDeleted()) continue;
            // loop from hash to hash - 1
            int hash = hash(lineEntry.getValue(), newValues.length);
            for (int i = 0; i < newValues.length; i++) {
                int index = (hash + i) % newValues.length;
                if (newValues[index] != null) continue;
                newValues[index] = lineEntry;
                break;
            }
        }
        values = newValues;
    }

    @Override
    public boolean contains(T element) {
        int hash = hash(element, values.length);
        for (int i = 0; i < values.length; i++) {
            int index = (hash + i) % values.length;
            if (values[index] == null) return false;
            if (values[index].isDeleted()) continue;
            if (Objects.equals(values[index].getValue(), element)) return true;
        }
        return false;
    }

    @Override
    public void remove(T element) {
        int hash = hash(element, values.length);
        for (int i = 0; i < values.length; i++) {
            int index = (hash + i) % values.length;
            if (values[index] == null) return;
            if (values[index].isDeleted()) continue;
            if (Objects.equals(values[index].getValue(), element)) {
                values[index].setDeleted(true);
                numOfUniqueEntries--;
            }
        }
        if (values.length > HashSet.INITIAL_TABLE_CAPACITY && ((double) numOfUniqueEntries) <= SHRINK * values.length) {
            shrinkValuesArray();
        }
    }
}
