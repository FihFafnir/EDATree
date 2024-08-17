package br.edu.ifpb.eda.tree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private AVLNode<T> root;
    private int size = 0;

    private AVLNode<T> get(T element, AVLNode<T> root) {
        if (root == null)
            return null;

        int comparison = element.compareTo(root.element);
        if (comparison < 0)
            return get(element, root.left);
        if (comparison > 0)
            return get(element, root.right);
        return root;
    }

    private AVLNode<T> get(T element) {
        return get(element, root);
    }

    private AVLNode<T> minimum(AVLNode<T> root) {
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

    private AVLNode<T> maximum(AVLNode<T> root) {
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

    private AVLNode<T> rotateToLeft(AVLNode<T> root) {
        AVLNode<T> tmp = root.right;
        root.right = tmp.left;
        tmp.left = root;

        root.updateHeight();
        tmp.updateHeight();

        return tmp;
    }

    private AVLNode<T> rotateToRight(AVLNode<T> root) {
        if (root.left.element.compareTo(root.element) == 0)
            return root;
        AVLNode<T> tmp = root.left;
        root.left = tmp.right;
        tmp.right = root;

        root.updateHeight();
        tmp.updateHeight();

        return tmp;
    }

    private AVLNode<T> rotateToLeftAndRight(AVLNode<T> root) {
        root.left = rotateToLeft(root.left);
        return rotateToRight(root);
    }

    private AVLNode<T> rotateToRightAndLeft(AVLNode<T> root) {
        root.right = rotateToRight(root.right);
        return rotateToLeft(root);
    }

    private AVLNode<T> balance(AVLNode<T> root) {
        if (root == null)
            return null;
        if (root.getBalancingFactor() > 1)
            return (root.left.getBalancingFactor() > 0)
                    ? rotateToRight(root)
                    : rotateToLeftAndRight(root);
        if (root.getBalancingFactor() < -1)
            return (root.right.getBalancingFactor() < 0)
                    ? rotateToLeft(root)
                    : rotateToRightAndLeft(root);
        return root;
    }

    private AVLNode<T> insert(AVLNode<T> root, T element) {
        if (root == null)
            return new AVLNode<T>(element);

        if (element.compareTo(root.element) <= 0)
            root.left = insert(root.left, element);
        else
            root.right = insert(root.right, element);

        root.updateHeight();
        return balance(root);
    }

    @Override
    public void insert(T element) {
        this.size++;
        this.root = insert(root, element);
    }

    private AVLNode<T> predecessor(AVLNode<T> root) {
        return maximum(root.left);
    }

    private AVLNode<T> successor(AVLNode<T> root) {
        return minimum(root.right);
    }

    private AVLNode<T> delete(AVLNode<T> root, T element) {
        if (root == null || element == null)
            return null;

        int comparison = element.compareTo(root.element);
        if (comparison == 0) {
            AVLNode<T> substitute = root.left != null ? predecessor(root) : successor(root);

            if (substitute != null) {
                if (substitute.element.compareTo(root.element) <= 0)
                    root.left = delete(root.left, substitute.element);
                else
                    root.right = delete(root.right, substitute.element);
                substitute.left = root.left;
                substitute.right = root.right;
                substitute.updateHeight();
            }

            return balance(substitute);
        }

        if (comparison < 0)
            root.left = delete(root.left, element);
        else
            root.right = delete(root.right, element);

        root.updateHeight();
        return balance(root);
    }

    @Override
    public void delete(T element) {
        this.size--;
        this.root = delete(root, element);
    }

    private List<T> preorder(AVLNode<T> root, List<T> elements) {
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

    private List<T> inorder(AVLNode<T> root, List<T> elements) {
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

    private List<T> postorder(AVLNode<T> root, List<T> elements) {
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
        return root.toString();
    }

}
