package dsa.dataStructures.tree;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class BSTree<T extends Comparable<T>, N extends GenericNode<T, N>> implements GenericTree<T, N>, Tree<T>{
    protected N root;
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(T element) {
        N found = getNode(root, element);
        return found == null ? null : found.getValue();
    }

    protected N getNode(N node, T element) {
        if (node == null) return null;

        int comparison = element.compareTo(node.getValue());
        if (comparison == 0) return node;
        if (comparison < 0) return getNode(node.getLeft(), element);
        return getNode(node.getRight(), element);
    }

    @Override
    public boolean contains(T element) {
        return getNode(root, element) != null;
    }

    @Override
    public void remove(T element) {
        root = removeRecursive(root, element);
    }

    /**
     * Helper method for recursively removing an element from the tree.
     *
     * @param node    the current node
     * @param element the element to remove
     * @return the new root of the subtree
     */
    protected N removeRecursive(N node, T element) {
        if (node == null) return null;

        int comparison = element.compareTo(node.getValue());

        if (comparison < 0) {
            node.setLeft(removeRecursive(node.getLeft(), element));
        } else if (comparison > 0) {
            node.setRight(removeRecursive(node.getRight(), element));
        } else {
            return removeNode(node);
        }
        return node;
    }

    protected N removeNode(N node) {
        size--;
        // node to delete found
        if (node.getLeft() == null) return node.getRight();
        if (node.getRight() == null) return node.getLeft();

        // node with two children: get smallest from right subtree
        N minRight = treeMin(node.getRight());
        node.setValue(minRight.getValue());
        node.setRight(removeRecursive(node.getRight(), minRight.getValue()));
        return node;
    }

    @Override
    public T min() {
        N minNode = treeMin(root);
        return minNode == null ? null : minNode.getValue();
    }

    @Override
    public T max() {
        N maxNode = treeMax(root);
        return maxNode == null ? null : maxNode.getValue();
    }

    @Override
    public N treeMin(N node) {
        if (node == null) return null;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public N treeMax(N node) {
        if (node == null) return null;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    @Override
    public N treeSuccessor(N node) {
        if (node == null) return null;
        if (node.getRight() != null) return treeMin(node.getRight());
        N parentNode = node.getParent();
        while (parentNode != null && node == parentNode.getRight()) {
            node = parentNode;
            parentNode = node.getParent();
        }
        return parentNode;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BSTreeIterator();
    }

    private class BSTreeIterator implements Iterator<T> {
        N current = root;

        @Override
        public boolean hasNext() {
            return current != treeMax(root);
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException("No next element");
            }
            T nextNode = current.getValue();
            current = treeSuccessor(current);
            return nextNode;
        }
    }
}
