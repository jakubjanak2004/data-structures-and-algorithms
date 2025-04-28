package dsa.dataStructures.set.sortedSet;

import dsa.dataStructures.set.Set;

public abstract class SortedSet<T extends Comparable<T>> implements Set<T> {
    abstract public T min();
    abstract public T max();
}
