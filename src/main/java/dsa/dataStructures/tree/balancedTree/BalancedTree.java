package dsa.dataStructures.tree.balancedTree;

import dsa.dataStructures.tree.BSTree;
import dsa.dataStructures.tree.Node;

public abstract class BalancedTree<T extends Comparable<T>> extends BSTree<T> {
    abstract Node<T> leftRotation(Node<T> subtreeNode);
    abstract Node<T> rightRotation(Node<T> subtreeNode);
    abstract Node<T> leftRightRotation(Node<T> subtreeNode);
    abstract Node<T> rightLeftRotation(Node<T> subtreeNode);
}
