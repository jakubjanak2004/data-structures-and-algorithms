package dsa.dataStructures.tree.balancedTree;

import dsa.dataStructures.tree.Node;

public class AVLTree<T extends Comparable<T>> extends BalancedTree<T>{
    @Override
    Node<T> leftRotation(Node<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        Node<T> p1 = subtreeRoot.getRight();
        if (p1 == null) return subtreeRoot;
        subtreeRoot.setRight(p1.getLeft());
        p1.setLeft(subtreeRoot);
        return p1;
    }

    @Override
    Node<T> rightRotation(Node<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        Node<T> p1 = subtreeRoot.getLeft();
        if (p1 == null) return subtreeRoot;
        subtreeRoot.setLeft(p1.getRight());
        p1.setRight(subtreeRoot);
        return p1;
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

    // todo implement add or replace
    @Override
    public void addOrReplace(T element) {

    }

    // todo implement add or replace recursive
    protected void addOrReplaceRecursive(Node<T> node, T element) {

    }

    // todo maybe abstract the method as it is duplicate
    @Override
    protected Node<T> removeRecursive(Node<T> node, T element) {
        // todo DUPLICATE START
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
        // todo DUPLICATE END
        return balanceGraph(node);
    }

    private int balance(Node<T> node) {
        return height(node.getLeft()) - height(node.getRight());
    }

    private Node<T> balanceGraph(Node<T> node) {
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

    // todo add height to the Node<T> for efficiency
    // todo very inefficient
    private int height(Node<T> node) {
        if (node == null) return -1;
        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }
}
