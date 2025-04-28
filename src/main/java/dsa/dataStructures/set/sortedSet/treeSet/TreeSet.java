package dsa.dataStructures.set.sortedSet.treeSet;

import dsa.dataStructures.set.sortedSet.SortedSet;

public class TreeSet<T extends Comparable<T>> extends SortedSet<T> {
    private Node<T> root;
    private int size;

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
    public void add(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return;
        }
        addRecursive(root, element);
    }

    /**
     * Helper method for recursively inserting an element into the tree.
     *
     * @param node    the current node
     * @param element the element to insert
     */
    private void addRecursive(Node<T> node, T element) {
        int comparison = element.compareTo(node.getValue());
        if (comparison < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(element));
                size++;
            } else {
                addRecursive(node.getLeft(), element);
            }
        }
        if (comparison > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(element));
                size++;
            } else {
                addRecursive(node.getRight(), element);
            }
        }
    }

    @Override
    public void addOrReplace(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return;
        }
        addOrReplaceRecursive(root, element);
    }

    /**
     * Helper method for recursively adding or replacing an element in the tree.
     *
     * @param node    the current node
     * @param element the element to add or replace
     */
    private void addOrReplaceRecursive(Node<T> node, T element) {
        int comparison = element.compareTo(node.getValue());
        if (comparison == 0) {
            node.setValue(element);
            return;
        }
        if (comparison < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(element));
                size++;
            } else {
                addOrReplaceRecursive(node.getLeft(), element);
            }
        }
        if (comparison > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(element));
                size++;
            } else {
                addOrReplaceRecursive(node.getRight(), element);
            }
        }
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
    private Node<T> removeRecursive(Node<T> node, T element) {
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
            Node<T> minRight = findMin(node.getRight());
            node.setValue(minRight.getValue());
            node.setRight(removeRecursive(node.getRight(), minRight.getValue()));
        }
        return node;
    }

    /**
     * Finds the node with the smallest value in a subtree.
     *
     * @param node the root of the subtree
     * @return the node with the smallest value
     */
    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
}
