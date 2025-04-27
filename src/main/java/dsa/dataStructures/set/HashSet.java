package dsa.dataStructures.set;

public abstract class HashSet<T> {
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

    // todo will be used in HashTable to replace old element quickly without the contains check
//    abstract public void addOrReplace(T element);

    abstract public boolean contains(T element);

    abstract public void remove(T element);
}
