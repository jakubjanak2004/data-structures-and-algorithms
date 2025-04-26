package dsa.dataStructures.Set;

public abstract class HashSet<T> {
    public static final int INITIAL_TABLE_CAPACITY = 16;

    protected int hash(T element, int containerSize) {
        return Math.abs(element.hashCode()) % containerSize;
    }

    abstract protected void add(T element);

    abstract protected boolean contains(T element);

    abstract protected void remove(T element);
}
