package dsa.dataStructures.set;

public abstract class HashSet<T> implements Set<T> {
    public final static double GROW = 0.7;
    public final static double SHRINK = 0.3;
    public static final int INITIAL_TABLE_CAPACITY = 16;
    protected int numOfUniqueEntries;

    protected int hash(T element, int containerSize) {
        return Math.abs(element.hashCode()) % containerSize;
    }

    public final int size() {
        return numOfUniqueEntries;
    }

    abstract public T get(T element);

    abstract public void add(T element);

    abstract public void addOrReplace(T element);

    abstract public boolean contains(T element);

    abstract public void remove(T element);

    abstract protected void growValuesArray();

    abstract protected void shrinkValuesArray();
}
