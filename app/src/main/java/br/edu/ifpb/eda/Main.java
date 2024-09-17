package br.edu.ifpb.eda;

import br.edu.ifpb.eda.tree.BTree;

public class Main {
    public static void main(String[] args) {
        BTree<Integer> b = new BTree<>(4);

        b.insert(1);
        b.insert(2);
        b.insert(3);
        b.insert(4);
        b.insert(5);
        b.insert(6);
        b.insert(7);
        b.insert(8);
        b.insert(9);
        b.insert(10);
        System.out.println(b);
        System.out.println(b.inorder());
        System.out.println(b.preorder());
        System.out.println(b.postorder());
    }
}
