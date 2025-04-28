package dsa.dataStructures.tree.balancedTree;

import dsa.dataStructures.tree.Node;

public class AVLTree<T extends Comparable<T>> extends BalancedTree<T> {
    @Override
    Node<T> leftRotation(Node<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        Node<T> rightChild = subtreeRoot.getRight();
        if (rightChild == null) return subtreeRoot;

        subtreeRoot.setRight(rightChild.getLeft());
        rightChild.setLeft(subtreeRoot);

        updateHeightForNode(subtreeRoot);
        updateHeightForNode(rightChild);

        return rightChild;
    }

    @Override
    Node<T> rightRotation(Node<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        Node<T> leftChild = subtreeRoot.getLeft();
        if (leftChild == null) return subtreeRoot;

        subtreeRoot.setLeft(leftChild.getRight());
        leftChild.setRight(subtreeRoot);

        updateHeightForNode(subtreeRoot);
        updateHeightForNode(leftChild);

        return leftChild;
    }

    @Override
    Node<T> leftRightRotation(Node<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        subtreeRoot.setLeft(leftRotation(subtreeRoot.getLeft()));
        return rightRotation(subtreeRoot);
    }

    @Override
    Node<T> rightLeftRotation(Node<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        subtreeRoot.setRight(rightRotation(subtreeRoot.getRight()));
        return leftRotation(subtreeRoot);
    }

    @Override
    public void add(T element) {
        root = addRecursive(root, element);
    }

    protected Node<T> addRecursive(Node<T> node, T element) {
        if (node == null) {
            size++;
            return new Node<>(element);
        }

        int comparison = element.compareTo(node.getValue());
        if (comparison < 0) {
            node.setLeft(addRecursive(node.getLeft(), element));
        } else if (comparison > 0) {
            node.setRight(addRecursive(node.getRight(), element));
        } else {
            return node;
        }

        return balanceGraph(node);
    }

    @Override
    public void addOrReplace(T element) {
        root = addOrReplaceRecursive(root, element);
    }

    protected Node<T> addOrReplaceRecursive(Node<T> node, T element) {
        if (node == null) {
            size++;
            return new Node<>(element);
        }

        int comparison = element.compareTo(node.getValue());
        if (comparison < 0) {
            node.setLeft(addOrReplaceRecursive(node.getLeft(), element));
        } else if (comparison > 0) {
            node.setRight(addOrReplaceRecursive(node.getRight(), element));
        } else {
            node.setValue(element);
            return node;
        }

        return balanceGraph(node);
    }

    @Override
    protected Node<T> removeRecursive(Node<T> node, T element) {
        node = super.removeRecursive(node, element);
        if (node == null) return null;
        return balanceGraph(node);
    }

    private int balance(Node<T> node) {
        return height(node.getLeft()) - height(node.getRight());
    }

    private Node<T> balanceGraph(Node<T> node) {
        updateHeightForNode(node);

        int balance = balance(node);

        if (balance >= 2) {
            if (balance(node.getLeft()) < 0) {
                node = leftRightRotation(node);
            } else {
                node = rightRotation(node);
            }
        }
        if (balance <= -2) {
            if (balance(node.getRight()) > 0) {
                node = rightLeftRotation(node);
            } else {
                node = leftRotation(node);
            }
        }
        return node;
    }

    /**
     * Returns the precomputed height of a given node.
     * <p>
     * The height value is stored within the {@code Node<T>} object and can be retrieved in constant time {@code O(1)}.
     * Originally, calculating the height involved a full recursive traversal down to the leaves:
     * <pre><code>
     * if (node == null) return -1;
     * return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
     * </code></pre>
     * Precomputing the height avoids repeated traversals and improves performance.
     *
     * @param node the node whose height is requested; may be {@code null}
     * @return the height of the node, or {@code -1} if the node is {@code null}
     */
    private int height(Node<T> node) {
        return node == null ? -1 : node.getHeight();
    }

    /**
     * Updates the stored height of a given node based on the heights of its child nodes.
     * <p>
     * This method should be called whenever the structure of the subtree rooted at the node changes
     * (e.g., after insertions or deletions) to ensure that the height information remains accurate.
     *
     * @param node the node whose height needs to be updated; if {@code null}, no action is taken
     */
    private void updateHeightForNode(Node<T> node) {
        if (node == null) return;
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }
}
