package br.edu.ifpb.eda.tree;

public interface BinarySearchTree<T extends Comparable<T>> {

    public T minimum();

    public T maximum();

    public boolean search(T element);

    public void insert(T element);

    public void delete(T element);
}
