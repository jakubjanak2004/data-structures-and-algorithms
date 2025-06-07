package dsa.dataStructures.tree;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Optional;

// todo implement and remove abstract
// todo implement as a BTree
public class BTree<T extends Comparable<T>> implements Tree<T> {
    private Optional<BTreeNode<T>> rootOptional = Optional.empty();
    private final int t;
    private int size;

    public BTree(int t) {
        this.t = t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(T element) {
        return rootOptional.map(root -> root.get(element)).orElse(null);
    }

    @Override
    public void add(T element) {
        rootOptional.ifPresent(root -> root.add(element));
    }

    @Override
    public void addOrReplace(T element) {
        rootOptional.ifPresent(root -> root.addOrReplace(element));
    }

    @Override
    public boolean contains(T element) {
        return rootOptional.map(root -> root.get(element) != null).orElse(false);
    }

    @Override
    public void remove(T element) {
        rootOptional.ifPresent(root -> root.remove(element));
    }

    // todo: decide if leave here or move to BSTree
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Node<T> treeMin(Node<T> node) {
        return null;
    }

    @Override
    public T min() {
        return null;
    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public Node<T> treeMax(Node<T> node) {
        return null;
    }

    @Override
    public Node<T> treeSuccessor(Node<T> node) {
        return null;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @NotNull
    @Override
    public Iterator<T> iterator() {
        // todo
        return null;
    }

    private static class BTreeNode<T extends Comparable<T>> {
        private T[] keys;
        private final int t;
        private BTreeNode<T>[] children;
        private int n;
        private boolean leaf;

        @SuppressWarnings("unchecked")
        public BTreeNode(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.keys = (T[]) new Object[2 * t - 1];
            this.children = (BTreeNode<T>[]) Array.newInstance(BTreeNode.class, 2 * t);
            n = 0;
        }

        public T get(T key) {
            for (int i = 0; i < n; i++) {
                if (key == keys[i]) return keys[i];
                if (key.compareTo(keys[i]) < 0) {
                    children[i].get(key);
                }
            }
            return null;
        }

        public void add(T key) {

        }

        public void addOrReplace(T key) {

        }

        public void remove(T key) {

        }

        public void splitChild(int i, BTreeNode<T> node) {

        }
    }
}
