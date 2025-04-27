package dsa.dataStructures.set;

public interface Set<T> {
    int size();

    T get(T element);

    void add(T element);

    void addOrReplace(T element);

    boolean contains(T element);

    void remove(T element);

    // todo implement set methods
//    Set<T> union(Set<T> other);
//
//    Set<T> intersection(Set<T> other);
//
//    Set<T> difference(Set<T> other);
}
