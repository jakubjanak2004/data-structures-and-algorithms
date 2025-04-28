package dsa.dataStructures.tree;

public interface Tree<T extends Comparable<T>> extends Iterable<T> {
    int size();
    T get(T element);
    void add(T element);
    void addOrReplace(T element);
    boolean contains(T element);
    void remove(T element);
    Node<T> treeMin(Node<T> node);
    T min();
    T max();
    Node<T> treeMax(Node<T> node);
    Node<T> treeSuccessor(Node<T> node);
}
