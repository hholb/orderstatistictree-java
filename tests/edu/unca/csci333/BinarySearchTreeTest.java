package edu.unca.csci333;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    public void test_Constructor() {
        BinarySearchTree t = new BinarySearchTree();
        assert  t.getRoot() == null;
        assert t.getSize() == 0;
    }

    @Test
    public void test_Search() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        for (int i = 5; i > 0; i--) {
            tree.insert(i);
        }

        assert tree.search(4).compareTo(4) == 0;
        assert tree.search(3).compareTo(3) == 0;
    }

    @Test
    public void test_Minimum() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        for (int i = 5; i > 0; i--) {
           tree.insert(i);
        }

        assert tree.minimum().compareTo(1) == 0;
    }

    @Test
    public void test_Maximum() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        for (int i = 0; i <= 5; i++) {
            tree.insert(i);
        }

        assert tree.maximum().compareTo(5) == 0;
    }

    @Test
    public void test_Successor() {
        // TODO
    }

    @Test
    public void test_Predecessor() {
        // TODO
    }

    @Test
    public void test_Insert() {
        BinarySearchTree tree = new BinarySearchTree();
        assert tree.getSize() == 0;
        tree.insert(1);
        assert tree.getSize() == 1;
        assert tree.maximum().compareTo(1) == 0;
    }

    @Test
    public void test_Delete() {
        BinarySearchTree tree = new BinarySearchTree<Integer>();
        for (int i = 0; i <= 5; i++) {
            tree.insert(i);
        }
        assert tree.getSize() == 6;
        tree.delete(4);
        assert tree.getSize() == 5;
    }

    @Test
    public void test_preOrder() {
        // TODO
    }

    @Test
    public void test_inOrder() {
        // TODO
    }

    @Test
    public void test_postOrder() {
        // TODO
    }

    @Test
    public void test_Select() {
        // TODO
    }

    @Test
    public void test_Rank() {
        // TODO
    }
}