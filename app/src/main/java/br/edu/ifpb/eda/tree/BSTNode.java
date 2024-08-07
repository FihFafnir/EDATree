package br.edu.ifpb.eda.tree;

public class BSTNode<T extends Comparable<T>> {
    protected BSTNode<T> left;
    protected BSTNode<T> right;
    protected BSTNode<T> parent;
    protected T element;

    protected BSTNode(T element) {
        this.element = element;
        this.left = this.right = null;
        this.parent = this;
    }

    protected BSTNode(T element, BSTNode<T> parent) {
        this.element = element;
        this.left = this.right = null;
        this.parent = parent;
    }
}
