package edu.unca.csci333;

public class BSTNode<T extends Comparable<T>> {
    private final T data;
    private BSTNode<T> p;
    private BSTNode<T> left;
    private BSTNode<T> right;
    private int size;

    public BSTNode(T data) {
       this.data = data;
       this.p = null;
       this.left = null;
       this.right = null;
       this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T getData() {
        return this.data;
    }

    public BSTNode<T> getP() {
        return p;
    }

    public void setP(BSTNode<T> p) {
        this.p = p;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
}
