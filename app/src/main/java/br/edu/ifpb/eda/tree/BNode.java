package br.edu.ifpb.eda.tree;

import java.util.List;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
    protected List<T> keys;
    protected List<BNode<T>> children;
    protected BNode<T> parent;

    protected BNode() {
        this.keys = new LinkedList<>();
        this.children = new LinkedList<>();
        this.parent = null;
    }

    protected BNode(List<T> keys) {
        this.keys = new LinkedList<>(keys);
        this.children = new LinkedList<>();
    }

    protected boolean isLeaf() {
        return children.isEmpty();
    }

    protected int getIndex(T key) {
        int index = 0;
        while (index < keys.size() && key.compareTo(keys.get(index)) > 0)
            index++;
        return index;
    }

    protected int getParentIndex() {
        if (parent == null)
            return -1;
        return parent.children.indexOf(this);
    }

    protected BNode<T> minimum() {
        if (isLeaf())
            return this;
        return children.getFirst().minimum();
    }

    protected BNode<T> maximum() {
        if (isLeaf())
            return this;
        return children.getLast().maximum();
    }

    protected BNode<T> predecessor(int index) {
        if (index < 0 || index >= keys.size())
            return null;
        return children.get(index).maximum();
    }

    protected BNode<T> successor(int index) {
        if (index < 0 || index >= keys.size())
            return null;
        return children.get(index + 1).minimum();
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", keys.toString(), children.toString());
    }
}
