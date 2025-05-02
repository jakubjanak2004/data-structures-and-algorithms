package dsa.dataStructures.table;

import dsa.dataStructures.tree.Tree;
import dsa.dataStructures.tree.balancedTree.AVLTree;
import org.jetbrains.annotations.NotNull;

public class TreeTable<K extends Comparable<K>, V> implements Table<K, V> {
    private final Tree<TreePair<K, V>> tree;

    public TreeTable() {
        this.tree = new AVLTree<>();
    }

    public TreeTable(Tree<TreePair<K, V>> tree) {
        this.tree = tree;
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public void put(K key, V value) {
        tree.addOrReplace(new TreePair<>(key, value));
    }

    @Override
    public void remove(K key) {
        tree.remove(new TreePair<>(key, null));
    }

    @Override
    public boolean containsKey(K key) {
        return tree.contains(new TreePair<>(key, null));
    }

    @Override
    public V get(K key) {
        TreePair<K, V> returnedPair = tree.get(new TreePair<>(key, null));
        return returnedPair == null ? null : returnedPair.getValue();
    }

    public static class TreePair<K extends Comparable<K>, V> extends Pair<K, V> implements Comparable<TreePair<K,V>> {

        public TreePair(K key, V value) {
            super(key, value);
        }

        @Override
        public int compareTo(@NotNull TreePair<K, V> o) {
            return this.key.compareTo(o.key);
        }
    }
}
