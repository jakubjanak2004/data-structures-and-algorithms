package dsa.dataStructures.set.openAddressingHashSet;

import dsa.dataStructures.set.HashSet;
import dsa.dataStructures.set.LineEntry;

import java.lang.reflect.Array;

public abstract class OpenAddressingHashSet<T> extends HashSet<T> {
    protected LineEntry<T>[] values;

    public OpenAddressingHashSet() {
        values = (LineEntry<T>[]) Array.newInstance(LineEntry.class, HashSet.INITIAL_TABLE_CAPACITY);
    }

    @Override
    protected void growValuesArray() {
        LineEntry<T>[] newValues = (LineEntry<T>[]) Array.newInstance(LineEntry.class, values.length * 2);
        rehash(newValues);
    }

    @Override
    protected void shrinkValuesArray() {
        LineEntry<T>[] newValues = (LineEntry<T>[]) Array.newInstance(LineEntry.class, values.length / 2);
        rehash(newValues);
    }

    @Override
    public void add(T element) {
        if (((double) numOfUniqueEntries) >= GROW * values.length) {
            growValuesArray();
        }
    }

    abstract protected void rehash(LineEntry<T>[] newValues);

    public void remove(T element) {
        if (values.length > HashSet.INITIAL_TABLE_CAPACITY && ((double) numOfUniqueEntries) <= SHRINK * values.length) {
            shrinkValuesArray();
        }
    }
}
