package br.edu.ifpb.eda;

import br.edu.ifpb.eda.tree.AVLTree;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> bst = new AVLTree<>();
        bst.insert(8);
        System.out.println(bst);
        bst.insert(6);
        System.out.println(bst);
        // bst.insert(5);
        bst.insert(7);
        System.out.println(bst);
        bst.insert(10);
        System.out.println(bst);
        bst.insert(9);
        System.out.println(bst);
        // bst.insert(3);
        // bst.insert(4);
        // bst.insert(10);
        // System.out.println(bst.size());
        // System.out.println(bst.minimum());
        // System.out.println(bst.maximum());
        // System.out.println(bst.preorder());
        // System.out.println(bst.inorder());
        // System.out.println(bst.postorder());

        // bst.delete(3);
        // System.out.println(bst.size());
        // System.out.println(bst.minimum());
        // System.out.println(bst.maximum());
        // System.out.println(bst.preorder());
        // System.out.println(bst.inorder());
        // System.out.println(bst.postorder());
    }
}
