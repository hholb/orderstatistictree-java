package edu.unca.csci333;

public class BSTNode<T extends Comparable<T>> {
    private T data;
    private BSTNode p;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(T data) {
       this.data = data;
       this.p = null;
       this.left = null;
       this.right = null;
    }

    public T getData() {
        return this.data;
    }

    public BSTNode getP() {
        return p;
    }

    public void setP(BSTNode p) {
        this.p = p;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
}
