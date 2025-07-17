package dsa.dataStructures.tree;

public interface GenericTree<T extends Comparable<T>, N extends GenericNode<T, N>> {
    N treeMin(N node);
    N treeMax(N node);
    N treeSuccessor(N node);
}
