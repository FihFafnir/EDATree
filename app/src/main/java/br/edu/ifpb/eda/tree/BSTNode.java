package br.edu.ifpb.eda.tree;

public class BSTNode<T extends Comparable<T>> {
    protected BSTNode<T> left;
    protected BSTNode<T> right;
    protected T element;

    protected BSTNode(T element) {
        this.element = element;
        this.left = this.right = null;
    }

}
