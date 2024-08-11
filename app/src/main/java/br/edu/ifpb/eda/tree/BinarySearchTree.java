package br.edu.ifpb.eda.tree;

import java.util.List;

public interface BinarySearchTree<T extends Comparable<T>> {

    public int size();

    public T minimum();

    public T maximum();

    public boolean search(T element);

    public void insert(T element);

    public void delete(T element);

    public List<T> preorder();

    public List<T> inorder();

    public List<T> postorder();
}
