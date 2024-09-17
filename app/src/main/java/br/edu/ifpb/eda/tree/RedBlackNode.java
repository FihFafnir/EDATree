package br.edu.ifpb.eda.tree;

enum Color {
    BLACK("\u001B[30m"), RED("\u001B[31m"), DOUBLE_BLACK("DB"), RESET("\u001B[0m");

    private final String representation;

    private Color(String representation) {
        this.representation = representation;
    }

    public static String getColoredString(String string, Color color) {
        return color.toString() + string + Color.RESET;
    }

    @Override
    public String toString() {
        return representation;
    }
};

public class RedBlackNode<T extends Comparable<T>> {
    protected RedBlackNode<T> left;
    protected RedBlackNode<T> right;
    protected RedBlackNode<T> parent;
    protected Color color;
    protected T element;

    protected RedBlackNode(T element) {
        this.element = element;
        this.left = this.right = this.parent = null;
        this.color = Color.RED;
    }

    protected RedBlackNode(T element, RedBlackNode<T> parent) {
        this.element = element;
        this.left = this.right = null;
        this.color = Color.RED;

        this.setParent(parent);
    }

    protected void setParent(RedBlackNode<T> root) {
        this.parent = root;
        if (root == null)
            return;
        if (isLeftChild())
            root.left = this;
        else
            root.right = this;
    }

    protected boolean isRed() {
        return this.color.equals(Color.RED);
    }

    protected boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    protected boolean isRightChild() {
        if (this.parent == null)
            return false;
        return this.element.compareTo(parent.element) > 0;
    }

    protected boolean isLeftChild() {
        return !isRightChild();
    }

    protected RedBlackNode<T> getBrother() {
        if (this.parent == null)
            return null;
        return isRightChild() ? this.parent.left : this.parent.right;
    }

    @Override
    public String toString() {
        String nil = Color.getColoredString("*", Color.BLACK);
        String leftString = left == null ? nil : left.toString();
        String rightString = right == null ? nil : right.toString();

        return String.format("%s(%s, %s)", Color.getColoredString(element.toString(), color), leftString,
                rightString);
    }

}
