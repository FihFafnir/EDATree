package br.edu.ifpb.eda.tree;

import java.util.ArrayList;
import java.util.List;

public class BST<T extends Comparable<T>> implements BinarySearchTree<T> {
    private BSTNode<T> root;
    private int size = 0;

    private BSTNode<T> get(T element, BSTNode<T> root) {
        if (root == null)
            return null;

        int comparison = element.compareTo(root.element);
        if (comparison < 0)
            return get(element, root.left);
        if (comparison > 0)
            return get(element, root.right);
        return root;
    }

    private BSTNode<T> get(T element) {
        return get(element, root);
    }

    private BSTNode<T> minimum(BSTNode<T> root) {
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

    private BSTNode<T> maximum(BSTNode<T> root) {
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

    private BSTNode<T> insert(BSTNode<T> root, T element) {
        if (root == null)
            return new BSTNode<T>(element);

        int comparison = element.compareTo(root.element);
        if (comparison <= 0)
            root.left = insert(root.left, element);
        else
            root.right = insert(root.right, element);
        return root;
    }

    @Override
    public void insert(T element) {
        this.size++;
        this.root = insert(root, element);
    }

    private BSTNode<T> predecessor(BSTNode<T> root) {
        if (root == null)
            return null;
        return maximum(root.left);
    }

    private BSTNode<T> successor(BSTNode<T> root) {
        if (root == null)
            return null;
        return minimum(root.right);
    }

    private BSTNode<T> delete(BSTNode<T> root, T element) {
        if (root == null || element == null)
            return null;

        int comparison = element.compareTo(root.element);
        if (comparison == 0) {
            BSTNode<T> substitute = root.left != null ? predecessor(root) : successor(root);

            if (substitute != null) {
                if (substitute.element.compareTo(root.element) <= 0)
                    root.left = delete(root.left, substitute.element);
                else
                    root.right = delete(root.right, substitute.element);
                substitute.left = root.left;
                substitute.right = root.right;
            }

            return substitute;
        }

        if (comparison < 0)
            root.left = delete(root.left, element);
        else
            root.right = delete(root.right, element);
        return root;
    }

    @Override
    public void delete(T element) {
        this.size--;
        this.root = delete(root, element);
    }

    private List<T> preorder(BSTNode<T> root, List<T> elements) {
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

    private List<T> inorder(BSTNode<T> root, List<T> elements) {
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

    private List<T> postorder(BSTNode<T> root, List<T> elements) {
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
