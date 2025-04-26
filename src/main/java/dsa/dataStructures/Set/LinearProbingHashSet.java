package dsa.dataStructures.Set;

import java.lang.reflect.Array;

public class LinearProbingHashSet<T> extends HashSet<T> {
    private final static double GROW = 0.7;
    private final static double SHRINK = 0.3;
    private LineEntry<T>[] values;
    private int numOfEntries;

    public LinearProbingHashSet() {
        values = (LineEntry<T>[]) Array.newInstance(LineEntry.class, HashSet.INITIAL_TABLE_CAPACITY);
    }

    @Override
    public void add(T element) {
        int hash = hash(element, values.length);
        for (int i = 0; i < values.length; i++) {
            int index = (hash + i) % values.length;
            if (values[index] == null) {
                values[index] = new LineEntry<>(element);
                numOfEntries++;
                break;
            }
            if (values[index].getValue() == element) {
                if (values[index].isDeleted()) {
                    values[index].setDeleted(false);
                    numOfEntries++;
                }
                break;
            }
        }
        if (((double) numOfEntries) >= GROW * values.length) {
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
            if (values[index].getValue() == element) return true;
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
            if (values[index].getValue() == element) {
                values[index].setDeleted(true);
                numOfEntries--;
            }
        }
        if (values.length > HashSet.INITIAL_TABLE_CAPACITY && ((double) numOfEntries) <= SHRINK * values.length) {
            shrinkValuesArray();
        }
    }
}
