package dsa.dataStructures.set.sortedSet.treeSet;

import dsa.dataStructures.set.sortedSet.SortedSet;
import dsa.dataStructures.tree.Tree;
import dsa.dataStructures.tree.UnbalancedBSTree;
import dsa.dataStructures.tree.balancedTree.AVLTree;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class TreeSet<T extends Comparable<T>> extends SortedSet<T>{
    private final Tree<T> tree;

    public TreeSet() {
        this.tree = new AVLTree<>();
    }

    public TreeSet(Tree<T> tree) {
        this.tree = tree;
    }

    @Override
    public T min() {
        return tree.min();
    }

    @Override
    public T max() {
        return tree.max();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public T get(T element) {
        return tree.get(element);
    }

    @Override
    public void add(T element) {
        tree.add(element);
    }

    @Override
    public void addOrReplace(T element) {
        tree.addOrReplace(element);
    }

    @Override
    public boolean contains(T element) {
        return tree.contains(element);
    }

    @Override
    public void remove(T element) {
        tree.remove(element);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return tree.iterator();
    }
}
