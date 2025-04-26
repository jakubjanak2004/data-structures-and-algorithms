package dsa.dataStructures.list;

public interface List<T> {
    int length();
    boolean empty();
    boolean full();
    T read();
    void insert(T t);
    void delete();
    void first();
    void last();
    void next();
    void prev();
}
