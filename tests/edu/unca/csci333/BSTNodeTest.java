package edu.unca.csci333;

import org.junit.jupiter.api.Test;

class BSTNodeTest {

    @Test
    public void test_Constructor() {
        int i = 5;
        BSTNode node = new BSTNode(i);
        assert node.getData().compareTo(i) == 0;
        assert node.getP() == null;
        assert node.getLeft() == null;
        assert node.getRight() == null;
    }

    @Test
    public void test_Getters_Setters() {
        int i = 5;
        BSTNode node = new BSTNode(i);

        BSTNode parent = new BSTNode(i);
        node.setP(parent);
        node.setLeft(parent);
        node.setRight(parent);

        assert node.getP() == parent;
        assert node.getLeft() == parent;
        assert node.getRight() == parent;
    }
}