package dsa.dataStructures.tree;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Optional;

// todo implement as a BTree
// todo when searching in the keys array use binary search(postgres uses binary search) or interpolation search
// todo maybe implement serialization as well for huge datasets
public class BTree<T extends Comparable<T>> implements Tree<T> {
    private Optional<BTreeNode<T>> rootOptional;
    private final int t;
    private int size;

    public BTree() {
        // setting the default t for testing
        t = 5;
        rootOptional = Optional.of(new BTreeNode<>(this, t, true));
    }

    public BTree(int t) {
        this.t = t;
        rootOptional = Optional.of(new BTreeNode<>(this, t, true));
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

    @Override
    public T min() {
        // todo
        return null;
    }

    @Override
    public T max() {
        // todo
        return null;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        // todo
        return null;
    }

    private static class BTreeNode<T extends Comparable<T>> {
        private final BTree<T> bTree;
        private T[] keys;
        private int numOfKeys = 0;
        private final int t;
        private BTreeNode<T>[] children;
        private boolean leaf;

        @SuppressWarnings("unchecked")
        public BTreeNode(BTree<T> bTree, int t, boolean leaf) {
            this.bTree = bTree;
            this.t = t;
            this.leaf = leaf;
            this.keys = (T[]) new Comparable[2 * t - 1];
            this.children = (BTreeNode<T>[]) Array.newInstance(BTreeNode.class, 2 * t);
        }

        public T get(T key) {
            int i = binarySearch(key);

            if (i < numOfKeys && keys[i].equals(key))
                return keys[i];

            if (leaf)
                return null;

            return children[i].get(key);
        }

        private int binarySearch(T key) {
            int low = 0;
            int high = numOfKeys - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                int cmp = key.compareTo(keys[mid]);

                if (cmp == 0) {
                    return mid; // key found at index mid
                } else if (cmp < 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return low; // key not found, return insertion point
        }

        public void add(T element) {
            if (numOfKeys == 2 * t - 1) {
                BTreeNode<T> newRoot = new BTreeNode<>(bTree, t, false);
                newRoot.children[0] = this;
                newRoot.splitChild(0, this);
                newRoot.insertNonFull(element);
                bTree.rootOptional = Optional.of(newRoot);
            } else {
                insertNonFull(element);
            }
        }

        public void addOrReplace(T key) {
            // todo
        }

        public void insertNonFull(T key) {
            int i = numOfKeys - 1;

            if (leaf) {
                // todo do not add the same element multiple times
                // todo maybe use some faster approach, binary search and system arraycopy
                while (i >= 0 && key.compareTo(keys[i]) < 0) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = key;
                numOfKeys++;
                bTree.size++;
            } else {
                i = binarySearch(key);
                // if we found a full node we split it
                if (children[i].numOfKeys == 2 * t - 1) {
                    splitChild(i, children[i]);
                    if (key.compareTo(keys[i]) > 0)
                        i++;
                }
                // insert recursively until leaf is found
                children[i].insertNonFull(key);
            }
        }

        public void remove(T key) {

        }

        public void splitChild(int i, BTreeNode<T> y) {
            BTreeNode<T> z = new BTreeNode<>(bTree, y.t, y.leaf);
            z.numOfKeys = t - 1;

            if (t - 1 >= 0) System.arraycopy(y.keys, t, z.keys, 0, t - 1);

            if (!y.leaf) {
                if (t >= 0) System.arraycopy(y.children, t, z.children, 0, t);
            }

            y.numOfKeys = t - 1;

            for (int j = numOfKeys; j >= i + 1; j--)
                children[j + 1] = children[j];

            children[i + 1] = z;

            for (int j = numOfKeys - 1; j >= i; j--)
                keys[j + 1] = keys[j];

            keys[i] = y.keys[t - 1];
            numOfKeys++;
        }
    }
}
