// Hayden Holbrook
// CSCI-333 Fall 2023
// October 25, 2023
// Homework 6 Order Statistic Tree
package edu.unca.csci333;

/**
 * A node in a Binary Search Tree.
 * Augmented with a size field to facilitate order-statistic queries.
 *
 * @param <T> the type of item stored within the tree. Must extend Comparable<T>.
 */
public class BSTNode<T extends Comparable<T>> {
    private final T data;
    private BSTNode<T> p;
    private BSTNode<T> left;
    private BSTNode<T> right;
    private int size;

    /**
     * Creates a new Node with the given T element as its data field.
     * The parent and both children pointers are initialized to null.
     *
     * @param data item the node will hold
     */
    public BSTNode(T data) {
        this.data = data;
        this.p = null;
        this.left = null;
        this.right = null;
        this.size = 0;
    }

    /**
     * Returns the size of this node's subtree.
     *
     * @return int size of this node's subtree.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the internal size field.
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Retuns the internal data element
     *
     * @return T data element
     */
    public T getData() {
        return this.data;
    }

    /**
     * Returns a handle to this node's parent.
     *
     * @return a handle to this node's parent.
     */
    public BSTNode<T> getP() {
        return p;
    }

    /**
     * Sets this nodes parent to the given node.
     *
     * @param p node to set as this node's parent
     */
    public void setP(BSTNode<T> p) {
        this.p = p;
    }

    /**
     * Returns a handle to this node's left child.
     *
     * @return a handle to this node's left child.
     */
    public BSTNode<T> getLeft() {
        return left;
    }

    /**
     * Sets this nodes left child to the given node.
     *
     * @param left node to set as this node's left child
     */
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    /**
     * Returns a handle to this node's right child.
     *
     * @return a handle to this node's right child.
     */
    public BSTNode<T> getRight() {
        return right;
    }

    /**
     * Sets this nodes right child to the given node.
     *
     * @param right node to set as this node's right child
     */
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
}
