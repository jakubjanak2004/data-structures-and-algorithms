package dsa.dataStructures.tree.balancedTree;

import dsa.dataStructures.tree.GenericNode;

/**
 * Implementation of an AVL Tree, a type of self-balancing binary search tree.
 * <p>
 * An AVL Tree maintains the property that for every node, the heights of the left and right subtrees differ by at most one.
 * When an insertion or deletion causes this balance property to be violated, rotations are performed to restore balance.
 * <p>
 * The tree supports automatic balancing via single or double rotations based on the imbalance type:
 * <table border="1">
 *   <caption>Rotation Cases</caption>
 *   <tr><th>Imbalance Case</th><th>Rotation Needed</th><th>Description</th></tr>
 *   <tr><td>Left-Left (LL)</td><td>Single Right Rotation</td><td>New node inserted into left subtree of left child.</td></tr>
 *   <tr><td>Left-Right (LR)</td><td>Left-Right Double Rotation</td><td>New node inserted into right subtree of left child.</td></tr>
 *   <tr><td>Right-Right (RR)</td><td>Single Left Rotation</td><td>New node inserted into right subtree of right child.</td></tr>
 *   <tr><td>Right-Left (RL)</td><td>Right-Left Double Rotation</td><td>New node inserted into left subtree of right child.</td></tr>
 * </table>
 * <p>
 * This implementation assumes that elements stored in the tree are {@link Comparable} so they can be ordered.
 * <p>
 * {@link AVLTree} is usually little faster for searches than {@link RedBlackTree}, that is usually faster for inserts and deletes.
 *
 * @param <T> the type of elements stored in the tree; must be comparable to itself
 */
public class AVLTree<T extends Comparable<T>> extends BalancedTree<T, AVLTree.AVLNode<T>> {
    AVLNode<T> leftRotation(AVLNode<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        AVLNode<T> rightChild = subtreeRoot.getRight();
        if (rightChild == null) return subtreeRoot;

        subtreeRoot.setRight(rightChild.getLeft());
        rightChild.setLeft(subtreeRoot);

        updateHeightForNode(subtreeRoot);
        updateHeightForNode(rightChild);

        return rightChild;
    }

    AVLNode<T> rightRotation(AVLNode<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        AVLNode<T> leftChild = subtreeRoot.getLeft();
        if (leftChild == null) return subtreeRoot;

        subtreeRoot.setLeft(leftChild.getRight());
        leftChild.setRight(subtreeRoot);

        updateHeightForNode(subtreeRoot);
        updateHeightForNode(leftChild);

        return leftChild;
    }

    AVLNode<T> leftRightRotation(AVLNode<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        subtreeRoot.setLeft(leftRotation(subtreeRoot.getLeft()));
        return rightRotation(subtreeRoot);
    }

    AVLNode<T> rightLeftRotation(AVLNode<T> subtreeRoot) {
        if (subtreeRoot == null) return null;
        subtreeRoot.setRight(rightRotation(subtreeRoot.getRight()));
        return leftRotation(subtreeRoot);
    }

    @Override
    public void add(T element) {
        root = addRecursive(root, element);
    }

    protected AVLNode<T> addRecursive(AVLNode<T> node, T element) {
        if (node == null) {
            size++;
            return new AVLNode<>(element);
        }

        int comparison = element.compareTo(node.getValue());
        if (comparison < 0) {
            node.setLeft(addRecursive(node.getLeft(), element));
        } else if (comparison > 0) {
            node.setRight(addRecursive(node.getRight(), element));
        } else {
            return node;
        }

        return balanceSubtree(node);
    }

    @Override
    public void addOrReplace(T element) {
        root = addOrReplaceRecursive(root, element);
    }

    protected AVLNode<T> addOrReplaceRecursive(AVLNode<T> node, T element) {
        if (node == null) {
            size++;
            return new AVLNode<>(element);
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

        return balanceSubtree(node);
    }

    @Override
    protected AVLNode<T> removeRecursive(AVLNode<T> node, T element) {
        AVLNode<T> returnedNode = super.removeRecursive(node, element);
        if (returnedNode == null) return null;
        return balanceSubtree(returnedNode);
    }

    private int balance(AVLNode<T> node) {
        return height(node.getLeft()) - height(node.getRight());
    }

    /**
     * Balances the subtree rooted at the given node if it violates the AVL balance condition.
     * <p>
     * This method first updates the height of the given node and then checks its balance factor.
     * Based on the balance factor and the balance of its child nodes, it determines which rotation
     * (single or double) is necessary to restore the AVL property.
     * <p>
     * The method chooses the appropriate rotation according to these rules:
     * <ul>
     *   <li><b>Left-Left (LL) case</b>: Perform a single right rotation.</li>
     *   <li><b>Left-Right (LR) case</b>: Perform a left rotation on the left child, then a right rotation.</li>
     *   <li><b>Right-Right (RR) case</b>: Perform a single left rotation.</li>
     *   <li><b>Right-Left (RL) case</b>: Perform a right rotation on the right child, then a left rotation.</li>
     * </ul>
     *
     * @param node the root of the subtree to balance; may be {@code null}
     * @return the new root of the balanced subtree (after any necessary rotations)
     */
    private AVLNode<T> balanceSubtree(AVLNode<T> node) {
        updateHeightForNode(node);

        int balance = balance(node);

        // Left-... case
        if (balance >= 2) {
            if (balance(node.getLeft()) < 0) {
                // Left-Right case
                node = leftRightRotation(node);
            } else {
                // Left-Left case
                node = rightRotation(node);
            }
        }
        // Right-... case
        if (balance <= -2) {
            if (balance(node.getRight()) > 0) {
                // Right-Left case
                node = rightLeftRotation(node);
            } else {
                // Right-Right case
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
    private int height(AVLNode<T> node) {
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
    private void updateHeightForNode(AVLNode<T> node) {
        if (node == null) return;
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }

    protected static class AVLNode<T extends Comparable<T>> extends GenericNode<T, AVLNode<T>> {
        public AVLNode(T value) {
            super(value);
        }
    }
}
