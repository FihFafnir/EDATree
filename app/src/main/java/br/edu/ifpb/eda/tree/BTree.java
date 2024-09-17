package br.edu.ifpb.eda.tree;

import java.util.ArrayList;
import java.util.List;

public class BTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private BNode<T> root = new BNode<>();
    private int size = 0;
    private int order;

    public BTree(int order) {
        this.order = order;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T minimum() {
        return root.minimum().keys.getFirst();
    }

    @Override
    public T maximum() {
        return root.maximum().keys.getLast();
    }

    private BNode<T> get(BNode<T> root, T element) {
        int index = root.getIndex(element);
        if (index < root.keys.size() && element.compareTo(root.keys.get(index)) == 0)
            return root;
        if (root.isLeaf())
            return null;
        return get(root.children.get(index), element);
    }

    private boolean search(BNode<T> root, T element) {
        int index = root.getIndex(element);
        if (index < root.keys.size() && element.compareTo(root.keys.get(index)) == 0)
            return true;
        if (root.isLeaf())
            return false;
        return search(root.children.get(index), element);
    }

    @Override
    public boolean search(T element) {
        return search(root, element);
    }

    private void split(BNode<T> node) {
        int medianIndex = (node.keys.size() - 1) >> 1;
        int parentIndex = node.getParentIndex();

        BNode<T> right = new BNode<>(node.keys.subList(medianIndex + 1,
                node.keys.size()));
        BNode<T> parent;

        if (parentIndex < 0) {
            this.root = parent = new BNode<>(node.keys.subList(medianIndex, medianIndex + 1));
            parent.children.add(node);
            parent.children.add(right);
        } else {
            parent = node.parent;
            parent.keys.add(parentIndex, node.keys.get(medianIndex));
            parent.children.add(parentIndex + 1, right);
        }

        node.keys = node.keys.subList(0, medianIndex);
        node.parent = right.parent = parent;

        if (!node.isLeaf()) {
            right.children.addAll(node.children.subList(medianIndex + 1,
                    node.children.size()));
            node.children = node.children.subList(0, medianIndex + 1);
        }

        if (parent.keys.size() >= order)
            split(parent);
    }

    @Override
    public void insert(T element) {
        this.size++;

        BNode<T> current = this.root;
        int index = current.getIndex(element);

        while (!current.isLeaf()) {
            current = current.children.get(index);
            index = current.getIndex(element);
        }

        current.keys.add(index, element);
        if (current.keys.size() >= order)
            split(current);
    }

    private void rotateLeft(BNode<T> parent, int index) {
        if (index >= parent.keys.size())
            return;

        BNode<T> left = parent.children.get(index);
        BNode<T> right = parent.children.get(index + 1);

        left.keys.addLast(parent.keys.remove(index));
        parent.keys.add(index, right.keys.removeFirst());

        if (!left.isLeaf()) {
            left.children.addLast(right.children.removeFirst());
            left.children.getFirst().parent = left;
        }
    }

    private void rotateRight(BNode<T> parent, int index) {
        if (index < 0)
            return;

        BNode<T> left = parent.children.get(index - 1);
        BNode<T> right = parent.children.get(index);

        right.keys.addFirst(parent.keys.remove(index - 1));
        parent.keys.add(index - 1, left.keys.removeLast());

        if (!right.isLeaf()) {
            right.children.addFirst(left.children.removeLast());
            right.children.getFirst().parent = right;
        }
    }

    private void merge(BNode<T> parent, int index) {
        BNode<T> child = parent.children.get(index);
        BNode<T> removed = parent.children.remove(index + 1);

        child.keys.addLast(parent.keys.remove(index));
        child.keys.addAll(removed.keys);

        if (!removed.isLeaf())
            child.children.addAll(removed.children);
        child.children.forEach((node) -> node.parent = child);

        if (this.root != parent)
            balance(parent);
        else if (parent.keys.size() == 0) {
            this.root = child;
            this.root.parent = null;
        }
    }

    private void balance(BNode<T> node) {
        int min = ((int) Math.ceil(order * 0.5)) - 1;
        if (node.parent == null || node.keys.size() >= min)
            return;

        int parentIndex = node.getParentIndex();
        if (parentIndex > 0 && node.parent.children.get(parentIndex - 1).keys.size() > min)
            rotateRight(node.parent, parentIndex);
        else if (parentIndex < node.parent.children.size() - 1
                && node.parent.children.get(parentIndex + 1).keys.size() > min)
            rotateLeft(node.parent, parentIndex);
        else if (parentIndex < node.parent.keys.size())
            merge(node.parent, parentIndex);
        else
            merge(node.parent, parentIndex - 1);
    }

    @Override
    public void delete(T element) {
        this.size--;

        BNode<T> node = get(root, element);
        int index = node.getIndex(element);

        if (!node.isLeaf()) {
            BNode<T> predecessor = node.predecessor(index);
            node.keys.set(index, predecessor.keys.removeLast());
            balance(predecessor);
        } else {
            node.keys.remove(index);
            balance(node);
        }
    }

    private List<T> preorder(BNode<T> root, List<T> elements) {
        elements.addAll(root.keys);
        if (!root.isLeaf())
            for (BNode<T> node : root.children)
                preorder(node, elements);

        return elements;
    }

    @Override
    public List<T> preorder() {
        return preorder(root, new ArrayList<T>(size));
    }

    private List<T> inorder(BNode<T> root, List<T> elements) {
        if (root.isLeaf())
            elements.addAll(root.keys);

        for (int i = 0; i < root.children.size(); i++) {
            inorder(root.children.get(i), elements);
            if (i < root.keys.size())
                elements.add(root.keys.get(i));
        }

        return elements;
    }

    @Override
    public List<T> inorder() {
        return inorder(root, new ArrayList<T>(size));
    }

    private List<T> postorder(BNode<T> root, List<T> elements) {
        if (!root.isLeaf())
            for (BNode<T> node : root.children)
                postorder(node, elements);
        elements.addAll(root.keys);

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
