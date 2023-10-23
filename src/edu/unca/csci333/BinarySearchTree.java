package edu.unca.csci333;

public class BinarySearchTree<T extends Comparable<T>> {
    private int size;
    private BSTNode root;

    public BinarySearchTree() {
        this.size = 0;
        this.root = null;
    }

    public int getSize() {
        return this.size;
    }

    public BSTNode getRoot() {
        return this.root;
    }

    public void insert(T item) {
        // TODO
    }

    public void preOrder() {
        // TODO
    }

    public void postOrder() {
        // TODO
    }

    public T search(T item) {
        // TODO
        return null;
    }


    public void delete(T item) {
        // TODO
    }

    public T select(int order_statistic) {
        // TODO
        return null;
    }


    public int rank(T element) {
        // TODO
        return 0;
    }
}
