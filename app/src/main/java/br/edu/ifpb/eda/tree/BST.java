package br.edu.ifpb.eda.tree;

import java.util.ArrayList;
import java.util.List;

public class BST<T extends Comparable<T>> implements BinarySearchTree<T> {
    private BSTNode<T> root;
    private int size;

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

    private T minimum(BSTNode<T> root) {
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

    private T maximum(BSTNode<T> root) {
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

    private void insert(T element, BSTNode<T> root) {
        int comparison = element.compareTo(root.element);
        if (comparison <= 0)
            if (root.left == null)
                root.left = new BSTNode<T>(element, root);
            else
                insert(element, root.left);
        else if (root.right == null)
            root.right = new BSTNode<T>(element, root);
        else
            insert(element, root.right);
    }

    @Override
    public void insert(T element) {
        this.size++;
        if (root == null)
            root = new BSTNode<T>(element);
        else
            insert(element, root);
    }

    private T predecessor(BSTNode<T> root) {
        return maximum(root.left);
    }

    private T successor(BSTNode<T> root) {
        return minimum(root.right);
    }

    @Override
    public void delete(T element) {
        this.size--;

        BSTNode<T> node = get(element);

        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            int comparison = element.compareTo(node.parent.element);
            if (comparison <= 0)
                node.parent.left = null;
            else
                node.parent.right = null;
            return;
        }

        T tmp = node.left != null ? predecessor(node) : successor(node);
        delete(tmp);
        node.element = tmp;
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
}
