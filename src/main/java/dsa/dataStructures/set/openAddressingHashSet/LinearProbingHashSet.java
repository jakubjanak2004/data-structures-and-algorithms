package dsa.dataStructures.set.openAddressingHashSet;

import dsa.dataStructures.set.LineEntry;

import java.util.Objects;

public class LinearProbingHashSet<T> extends OpenAddressingHashSet<T> {
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
                    values[index].setDeleted(false);
                    numOfUniqueEntries++;
                }
                break;
            }
        }
        super.add(element);
    }

    @Override
    public void addOrReplace(T element) {
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
                    numOfUniqueEntries++;
                }
                values[index] = new LineEntry<>(element);
                break;
            }
        }
        super.add(element);
    }

    @Override
    protected void rehash(LineEntry<T>[] newValues) {
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
        super.remove(element);
    }
}
