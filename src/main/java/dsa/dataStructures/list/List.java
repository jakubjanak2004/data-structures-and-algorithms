package dsa.dataStructures.list;

public interface List<T> {
    int length();
    boolean empty();
    T read();
    void insert(T t);
    void delete();
    T first();
    T last();
    void next();
    void prev();
}
