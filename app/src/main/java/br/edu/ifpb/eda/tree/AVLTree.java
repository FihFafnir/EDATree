package br.edu.ifpb.eda.tree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private AVLNode<T> root;
    private int size;

    private AVLNode<T> getParent(T element, AVLNode<T> root) {
        if (root == null)
            return null;

        int comparison = element.compareTo(root.element);
        if (comparison < 0 && root.left.element != element)
            return getParent(element, root.left);
        if (comparison > 0 && root.right.element != element)
            return getParent(element, root.right);
        return root;
    }

    private AVLNode<T> getParent(T element) {
        return getParent(element, root);
    }

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

    private T minimum(AVLNode<T> root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root.element;
        return minimum(root.left);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T minimum() {
        return minimum(root);
    }

    private T maximum(AVLNode<T> root) {
        if (root == null)
            return null;
        if (root.right == null)
            return root.element;
        return maximum(root.right);
    }

    @Override
    public T maximum() {
        return maximum(root);
    }

    @Override
    public boolean search(T element) {
        return get(element) != null;
    }

    private AVLNode<T> rotateToLeft(AVLNode<T> node) {
        AVLNode<T> tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;

        node.updateHeight();
        tmp.updateHeight();

        return tmp;
    }

    private AVLNode<T> rotateToRight(AVLNode<T> node) {
        AVLNode<T> tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;

        node.updateHeight();
        tmp.updateHeight();

        return tmp;
    }

    private AVLNode<T> rotateToLeftAndRight(AVLNode<T> node) {
        node.left = rotateToLeft(node.left);
        return rotateToRight(node);
    }

    private AVLNode<T> rotateToRightAndLeft(AVLNode<T> node) {
        node.right = rotateToRight(node.right);
        return rotateToLeft(node);
    }

    private AVLNode<T> balance(AVLNode<T> root) {
        AVLNode<T> currentRoot = root;
        if (root.getBalancingFactor() > 1) {
            currentRoot = (root.left.getBalancingFactor() > 0)
                    ? rotateToRight(root)
                    : rotateToLeftAndRight(root);
        } else if (root.getBalancingFactor() < -1) {
            currentRoot = (root.right.getBalancingFactor() < 0)
                    ? rotateToLeft(root)
                    : rotateToRightAndLeft(root);
        }
        return currentRoot;
    }

    private void insert(AVLNode<T> node, AVLNode<T> root) {
        int comparison = node.element.compareTo(root.element);

        if (comparison <= 0) {
            if (root.left == null)
                root.left = node;
            else
                insert(node, root.left);
        } else {
            if (root.right == null)
                root.right = node;
            else
                insert(node, root.right);
        }

        root.updateHeight();
        if (root.left != null)
            root.left = balance(root.left);
        if (root.right != null)
            root.right = balance(root.right);
    }

    @Override
    public void insert(T element) {
        this.size++;
        AVLNode<T> node = new AVLNode<T>(element);

        if (root == null)
            root = node;
        else
            insert(node, root);

        this.root = balance(root);
    }

    private T predecessor(AVLNode<T> root) {
        return maximum(root.left);
    }

    private T successor(AVLNode<T> root) {
        return minimum(root.right);
    }

    @Override
    public void delete(T element) {
        // TODO
        this.size--;

        AVLNode<T> parent = getParent(element);
        AVLNode<T> node = element.compareTo(parent.element) <= 0 ? parent.left : parent.right;

        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            if (element.compareTo(parent.element) <= 0)
                parent.left = null;
            else
                parent.right = null;
            // parent.updateHeight();
            return;
        }

        T tmp = node.left != null ? predecessor(node) : successor(node);
        delete(tmp);
        node.element = tmp;
        // node.updateHeight();
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
