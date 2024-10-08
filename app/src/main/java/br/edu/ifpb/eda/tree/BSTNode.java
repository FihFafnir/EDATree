package br.edu.ifpb.eda.tree;

public class BSTNode<T extends Comparable<T>> {
    protected BSTNode<T> left;
    protected BSTNode<T> right;
    protected T element;

    protected BSTNode(T element) {
        this.element = element;
        this.left = this.right = null;
    }

    @Override
    public String toString() {
        String leftString = left == null ? "*" : left.toString();
        String rightString = right == null ? "*" : right.toString();
        return element.toString() + "(" + leftString + ", " + rightString + ")";
    }
}
