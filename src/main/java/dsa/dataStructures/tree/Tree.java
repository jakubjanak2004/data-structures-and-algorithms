package dsa.dataStructures.tree;

// todo we are using find in many method maybe abstract that and the sub methods will use predefined function performOnFound();
public interface Tree<T extends Comparable<T>> extends Iterable<T>{
    int size();

    T get(T element);

    void add(T element);

    // todo, the logic is the very similar to add, addOrReplace will define a function that gets called when the add finishes, it gets null or the found element whose value gets replaced for new one
    void addOrReplace(T element);

    boolean contains(T element);

    void remove(T element);

    T min();

    T max();
}

