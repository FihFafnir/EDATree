package br.edu.ifpb.eda;

import br.edu.ifpb.eda.tree.BST;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.insert(8);
        bst.insert(6);
        bst.insert(7);
        bst.insert(3);
        bst.insert(4);
        bst.insert(10);
        bst.delete(3);
        System.out.println(bst.minimum());
        System.out.println(bst.maximum());
    }
}
