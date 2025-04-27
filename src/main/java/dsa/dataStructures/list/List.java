package dsa.dataStructures.list;

public interface List<T> extends Iterable<T>{
    int size();

    boolean empty();

    T read();

    boolean contains(T t);

    void remove(T t);

    T get(T t);

    void insert(T t);

    void delete();

    T first();

    T last();

    void next();

    void prev();
}
