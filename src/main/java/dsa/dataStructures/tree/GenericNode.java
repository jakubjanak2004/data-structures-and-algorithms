package dsa.dataStructures.tree;

public abstract class GenericNode<T extends Comparable<T>, N extends GenericNode<T, N>> {
    private N parent;
    private N left;
    private N right;
    private T value;
    // todo move into avl node
    private int height;

    public GenericNode(T value) {
        this.value = value;
    }

    public N getParent() {
        return parent;
    }

    public void setParent(N parent) {
        this.parent = parent;
    }

    public N getLeft() {
        return left;
    }

    public void setLeft(N left) {
        this.left = left;
        if (left == null) return;
        left.setParent((N) this);
    }

    public N getRight() {
        return right;
    }

    public void setRight(N right) {
        this.right = right;
        if (right == null) return;
        right.setParent((N) this);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
