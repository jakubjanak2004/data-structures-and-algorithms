package dsa.dataStructures.set;

/**
 * A Set is a non-linear (associative) container.
 * The order of elements cannot be guaranteed in general.
 * For a SortedSet, the order is determined by the mutual comparison of the keys.
 *
 * @param <T> the type of elements maintained by set
 */
public interface Set<T> extends Iterable<T> {
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
