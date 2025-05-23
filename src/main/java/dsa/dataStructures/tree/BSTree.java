package dsa.dataStructures.tree;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class BSTree<T extends Comparable<T>> implements Tree<T> {
    protected Node<T> root;
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(T element) {
        Node<T> found = getNode(root, element);
        return found == null ? null : found.getValue();
    }

    private Node<T> getNode(Node<T> node, T element) {
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
    protected Node<T> removeRecursive(Node<T> node, T element) {
        if (node == null) return null;

        int comparison = element.compareTo(node.getValue());

        if (comparison < 0) {
            node.setLeft(removeRecursive(node.getLeft(), element));
        } else if (comparison > 0) {
            node.setRight(removeRecursive(node.getRight(), element));
        } else {
            size--;
            // node to delete found
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            // node with two children: get smallest from right subtree
            Node<T> minRight = treeMin(node.getRight());
            node.setValue(minRight.getValue());
            node.setRight(removeRecursive(node.getRight(), minRight.getValue()));
        }
        return node;
    }

    @Override
    public T min() {
        Node<T> minNode = treeMin(root);
        return minNode == null ? null : minNode.getValue();
    }

    @Override
    public T max() {
        Node<T> maxNode = treeMax(root);
        return maxNode == null ? null : maxNode.getValue();
    }

    @Override
    public Node<T> treeMin(Node<T> node) {
        if (node == null) return null;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public Node<T> treeMax(Node<T> node) {
        if (node == null) return null;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    @Override
    public Node<T> treeSuccessor(Node<T> node) {
        if (node == null) return null;
        if (node.getRight() != null) return treeMin(node.getRight());
        Node<T> parentNode = node.getParent();
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
        Node<T> current = root;

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
