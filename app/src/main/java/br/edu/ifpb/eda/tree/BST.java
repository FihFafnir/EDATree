package br.edu.ifpb.eda.tree;

public class BST<T extends Comparable<T>> implements BinarySearchTree<T> {
    private BSTNode<T> root;

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
}
