package dsa.dataStructures.tree.balancedTree;

import dsa.dataStructures.tree.GenericNode;

public class RedBlackTree<T extends Comparable<T>> extends BalancedTree<T, RedBlackTree.RedBlackNode<T>> {
    void leftRotation(RedBlackNode<T> node) {
        RedBlackNode<T> y = node.getRight();
        node.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(node);
        }
        y.setParent(node.getParent());
        if (node.getParent() == null) {
            this.root = y;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(y);
        } else {
            node.getParent().setRight(y);
        }
        y.setLeft(node);
        node.setParent(y);
    }

    void rightRotation(RedBlackNode<T> node) {
        RedBlackNode<T> y = node.getLeft();
        node.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(node);
        }
        y.setParent(node.getParent());
        if (node.getParent() == null) {
            this.root = y;
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(y);
        } else {
            node.getParent().setLeft(y);
        }
        y.setRight(node);
        node.setParent(y);
    }

    // todo the code is similar for these two methods, merge it
    @Override
    public void add(T element) {
        RedBlackNode<T> node = new RedBlackNode<>(element);

        if (root == null) {
            root = node;
            node.setBlack(true);
            size++;
            return;
        }

        RedBlackNode<T> thisNode = null;
        RedBlackNode<T> nextNode = root;

        while (nextNode != null) {
            thisNode = nextNode;
            int comparison = element.compareTo(nextNode.getValue());
            if (comparison < 0) {
                nextNode = nextNode.getLeft();
            } else if (comparison > 0) {
                nextNode = nextNode.getRight();
            } else {
                // if the node exists in the tree do nothing
                return;
            }
        }

        size++;
        if (element.compareTo(thisNode.getValue()) < 0) {
            thisNode.setLeft(node);
        } else {
            thisNode.setRight(node);
        }

        balanceAfterInsert(node);
    }

    @Override
    public void addOrReplace(T element) {
        RedBlackNode<T> node = new RedBlackNode<>(element);

        if (root == null) {
            root = node;
            size++;
            return;
        }

        RedBlackNode<T> thisNode = null;
        RedBlackNode<T> nextNode = root;

        while (nextNode != null) {
            thisNode = nextNode;
            int comparison = element.compareTo(nextNode.getValue());
            if (comparison < 0) {
                nextNode = nextNode.getLeft();
            } else if (comparison > 0) {
                nextNode = nextNode.getRight();
            } else {
                // if the node exists in the tree replace the contents with new ones
                thisNode.setValue(element);
                return;
            }
        }

        size++;
        if (element.compareTo(thisNode.getValue()) < 0) {
            thisNode.setLeft(node);
        } else {
            thisNode.setRight(node);
        }

        balanceAfterInsert(node);
    }

    // todo finish
    @Override
    protected RedBlackNode<T> removeNode(RedBlackNode<T> node) {
        boolean isBlack = node.isBlack();
        RedBlackNode<T> toBeReturnedNode = super.removeNode(node);
        // handle the deletion and re-balancing
        // if the node was black we perform te re-balancing(it being red means no re-balancing)
        if (isBlack) {

        }

        return toBeReturnedNode;
    }

    private void balanceAfterInsert(RedBlackNode<T> node) {
        RedBlackNode<T> u;
        // loop while the parent of the current node is RED
        while (!node.getParent().isBlack()) {
            // parent of the current node is the right child of the grandparent
            if (node.getParent() == node.getParent().getParent().getRight()) {
                // uncle is the left child of the grandparent
                u = node.getParent().getParent().getLeft();

                // uncle is black(null -> black)
                if (u == null || u.isBlack()) {
                    // node is the left child of its parent
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotation(node);
                    }
                    node.getParent().setBlack(true);
                    node.getParent().getParent().setBlack(false);
                    leftRotation(node.getParent().getParent());
                } else {
                    // uncle is red
                    u.setBlack(true);
                    node.getParent().setBlack(true);
                    node.getParent().getParent().setBlack(false);
                    node = node.getParent().getParent();
                }
                // parent of the current node is the left child of the grandparent
            } else {
                // uncle is the right child of the grandparent
                u = node.getParent().getParent().getRight();

                // uncle is black(null -> black)
                if (u == null || u.isBlack()) {
                    // node is the right child of its parent
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotation(node);
                    }
                    node.getParent().setBlack(true);
                    node.getParent().getParent().setBlack(false);
                    rightRotation(node.getParent().getParent());
                } else {
                    // uncle is red
                    u.setBlack(true);
                    node.getParent().setBlack(true);
                    node.getParent().getParent().setBlack(false);
                    node = node.getParent().getParent();
                }
            }
            // break the loop if root reached
            if (node == root) {
                break;
            }
        }
        root.setBlack(true);
    }

    protected static class RedBlackNode<T extends Comparable<T>> extends GenericNode<T, RedBlackNode<T>> {
        private boolean black;

        public RedBlackNode(T value) {
            super(value);
            black = false;
        }

        public boolean isBlack() {
            return black;
        }

        public void setBlack(boolean black) {
            this.black = black;
        }
    }
}
