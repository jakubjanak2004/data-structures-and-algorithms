package dsa.dataStructures.tree;

public final class UnbalancedBSTree<T extends Comparable<T>> extends BSTree<T, UnbalancedBSTree.UnbalancedBSNode<T>> {
    @Override
    public void add(T element) {
        if (root == null) {
            root = new UnbalancedBSNode<>(element);
            size++;
            return;
        }
        addRecursive(root, element);
    }

    private void addRecursive(UnbalancedBSNode<T> node, T element) {
        int comparison = element.compareTo(node.getValue());
        if (comparison < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new UnbalancedBSNode<>(element));
                size++;
            } else {
                addRecursive(node.getLeft(), element);
            }
        }
        if (comparison > 0) {
            if (node.getRight() == null) {
                node.setRight(new UnbalancedBSNode<>(element));
                size++;
            } else {
                addRecursive(node.getRight(), element);
            }
        }
    }

    @Override
    public void addOrReplace(T element) {
        if (root == null) {
            root = new UnbalancedBSNode<>(element);
            size++;
            return;
        }
        addOrReplaceRecursive(root, element);
    }

    private void addOrReplaceRecursive(UnbalancedBSNode<T> node, T element) {
        int comparison = element.compareTo(node.getValue());
        if (comparison == 0) {
            node.setValue(element);
            return;
        }
        if (comparison < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new UnbalancedBSNode<>(element));
                size++;
            } else {
                addOrReplaceRecursive(node.getLeft(), element);
            }
        }
        if (comparison > 0) {
            if (node.getRight() == null) {
                node.setRight(new UnbalancedBSNode<>(element));
                size++;
            } else {
                addOrReplaceRecursive(node.getRight(), element);
            }
        }
    }

    public static class UnbalancedBSNode<T extends Comparable<T>> extends GenericNode<T, UnbalancedBSNode<T>> {
        public UnbalancedBSNode(T value) {
            super(value);
        }
    }
}
