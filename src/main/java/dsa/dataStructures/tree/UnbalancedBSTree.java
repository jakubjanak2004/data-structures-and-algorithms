package dsa.dataStructures.tree;

public class UnbalancedBSTree<T extends Comparable<T>> extends BSTree<T> {
    @Override
    public void add(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return;
        }
        addRecursive(root, element);
    }

    protected void addRecursive(Node<T> node, T element) {
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

    protected void addOrReplaceRecursive(Node<T> node, T element) {
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
}
