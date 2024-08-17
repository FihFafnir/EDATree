package br.edu.ifpb.eda;

import br.edu.ifpb.eda.tree.AVLTree;
import br.edu.ifpb.eda.tree.BST;
import br.edu.ifpb.eda.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        // AVLTree<Integer> bst = new AVLTree<>();
        BinarySearchTree<Integer> bst = new AVLTree<>();
        bst.insert(9);
        bst.insert(10);
        bst.insert(6);
        bst.insert(5);
        bst.insert(11);
        bst.insert(7);
        bst.insert(8);
        System.out.println(bst);
        // bst.delete(8);
        // bst.delete(9);
        System.out.println(bst);
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
