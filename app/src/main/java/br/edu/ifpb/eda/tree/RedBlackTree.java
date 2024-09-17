package br.edu.ifpb.eda.tree;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private RedBlackNode<T> root;
    private int size = 0;

    private RedBlackNode<T> get(T element, RedBlackNode<T> root) {
        if (root == null)
            return null;

        int comparison = element.compareTo(root.element);
        if (comparison < 0)
            return get(element, root.left);
        if (comparison > 0)
            return get(element, root.right);
        return root;
    }

    private RedBlackNode<T> get(T element) {
        return get(element, root);
    }

    private RedBlackNode<T> minimum(RedBlackNode<T> root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        return minimum(root.left);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T minimum() {
        return minimum(root).element;
    }

    private RedBlackNode<T> maximum(RedBlackNode<T> root) {
        if (root == null)
            return null;
        if (root.right == null)
            return root;
        return maximum(root.right);
    }

    @Override
    public T maximum() {
        return maximum(root).element;
    }

    @Override
    public boolean search(T element) {
        return get(element) != null;
    }

    private void rotateToLeft(RedBlackNode<T> root) {
        RedBlackNode<T> parent = root.parent;
        RedBlackNode<T> right = root.right;

        if (right.left == null)
            root.right = null;
        else
            right.left.setParent(root);
        root.setParent(right);
        right.setParent(parent);

        if (right.parent == null)
            this.root = right;
    }

    private void rotateToRight(RedBlackNode<T> root) {
        RedBlackNode<T> parent = root.parent;
        RedBlackNode<T> left = root.left;

        if (left.right == null)
            root.left = left.right;
        else
            left.right.setParent(root);
        root.setParent(left);
        left.setParent(parent);

        if (left.parent == null)
            this.root = left;
    }

    private void insertBalance(RedBlackNode<T> root) {
        if (root.parent == null || root.parent.isBlack())
            return;

        RedBlackNode<T> parent = root.parent;
        RedBlackNode<T> grand = parent.parent;
        RedBlackNode<T> uncle = parent.getBrother();

        if (uncle != null && uncle.isRed()) {
            uncle.color = Color.BLACK;
        } else if (root.parent.isLeftChild()) {
            if (root.isRightChild()) {
                rotateToLeft(parent);
                parent = root;
            }

            rotateToRight(grand);
        } else {
            if (root.isLeftChild()) {
                rotateToRight(parent);
                parent = root;
            }

            rotateToLeft(grand);
        }

        parent.color = Color.BLACK;
        grand.color = Color.RED;
        insertBalance(grand);
    }

    @Override
    public void insert(T element) {
        RedBlackNode<T> current = root;
        RedBlackNode<T> parent = null;
        int comparison = 0;

        while (current != null) {
            parent = current;
            comparison = element.compareTo(parent.element);
            current = comparison > 0 ? parent.right : parent.left;
        }

        RedBlackNode<T> node = new RedBlackNode<T>(element, parent);
        if (parent == null)
            this.root = node;
        else if (comparison > 0)
            parent.right = node;
        else
            parent.left = node;

        insertBalance(node);

        this.size++;
        this.root.color = Color.BLACK;
    }

    @Override
    public void delete(T element) {
        // TODO: Muitos casos para corrigir as propriedades da rubro negra. :)
    }

    private List<T> preorder(RedBlackNode<T> root, List<T> elements) {
        elements.addLast(root.element);
        if (root.left != null)
            preorder(root.left, elements);
        if (root.right != null)
            preorder(root.right, elements);
        return elements;
    }

    @Override
    public List<T> preorder() {
        return preorder(root, new ArrayList<T>(size));
    }

    private List<T> inorder(RedBlackNode<T> root, List<T> elements) {
        if (root.left != null)
            inorder(root.left, elements);
        elements.addLast(root.element);
        if (root.right != null)
            inorder(root.right, elements);
        return elements;
    }

    @Override
    public List<T> inorder() {
        return inorder(root, new ArrayList<T>(size));
    }

    private List<T> postorder(RedBlackNode<T> root, List<T> elements) {
        if (root.left != null)
            postorder(root.left, elements);
        if (root.right != null)
            postorder(root.right, elements);
        elements.addLast(root.element);
        return elements;
    }

    @Override
    public List<T> postorder() {
        return postorder(root, new ArrayList<T>(size));
    }

    @Override
    public String toString() {
        if (root == null)
            return "";
        return root.toString();
    }

}
