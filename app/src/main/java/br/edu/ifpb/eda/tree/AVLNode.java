package br.edu.ifpb.eda.tree;

public class AVLNode<T extends Comparable<T>> {
    protected AVLNode<T> left;
    protected AVLNode<T> right;
    protected T element;
    protected int height;

    protected AVLNode(T element) {
        this.element = element;
        this.left = this.right = null;
        this.height = 0;
    }

    protected int getBalancingFactor() {
        int leftHeight = this.left == null ? -1 : this.left.height;
        int rightHeight = this.right == null ? -1 : this.right.height;
        return leftHeight - rightHeight;
    }

    protected void updateHeight() {
        this.height = Math.max(left == null ? -1 : left.height, right == null ? -1 : right.height) + 1;
    }

    @Override
    public String toString() {
        String leftString = left == null ? "*" : left.toString();
        String rightString = right == null ? "*" : right.toString();
        return element.toString() + "(" + leftString + ", " + rightString + ")";
    }

}
