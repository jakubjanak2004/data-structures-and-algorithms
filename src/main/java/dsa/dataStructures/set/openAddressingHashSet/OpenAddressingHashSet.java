package dsa.dataStructures.set.openAddressingHashSet;

import dsa.dataStructures.set.HashSet;
import dsa.dataStructures.set.LineEntry;

import java.lang.reflect.Array;

public abstract class OpenAddressingHashSet<T> extends HashSet<T> {
    public final static double GROW = 0.7;
    public final static double SHRINK = 0.3;
    protected LineEntry<T>[] values;

    public OpenAddressingHashSet() {
        values = (LineEntry<T>[]) Array.newInstance(LineEntry.class, HashSet.INITIAL_TABLE_CAPACITY);
    }

    protected void growValuesArray() {
        LineEntry<T>[] newValues = (LineEntry<T>[]) Array.newInstance(LineEntry.class, values.length * 2);
        rehash(newValues);
    }

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

    public void remove(T element) {
        if (values.length > HashSet.INITIAL_TABLE_CAPACITY && ((double) numOfUniqueEntries) <= SHRINK * values.length) {
            shrinkValuesArray();
        }
    }

    abstract void rehash(LineEntry<T>[] newValues);
}
