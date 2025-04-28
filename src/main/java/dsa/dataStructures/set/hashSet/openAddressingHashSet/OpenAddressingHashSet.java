package dsa.dataStructures.set.hashSet.openAddressingHashSet;

import dsa.dataStructures.set.hashSet.HashSet;
import dsa.dataStructures.set.hashSet.LineEntry;

import java.lang.reflect.Array;
import java.util.Iterator;

public abstract class OpenAddressingHashSet<T> extends HashSet<T> {
    protected LineEntry<T>[] values;

    public OpenAddressingHashSet() {
        values = (LineEntry<T>[]) Array.newInstance(LineEntry.class, HashSet.INITIAL_TABLE_CAPACITY);
    }

    public OpenAddressingHashSet(int initialCapacity) {
        values = (LineEntry<T>[]) Array.newInstance(LineEntry.class, initialCapacity);
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

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
