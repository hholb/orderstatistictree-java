// Hayden Holbrook
// CSCI-333 Fall 2023
// October 25, 2023
// Homework 6 Order Statistic Tree
package edu.unca.csci333;

/**
 * A Binary Search Tree with the ability to calculate order-statistic and rank
 * values for the stored data.
 *
 * @param <T> type of element to store in the tree. T must extend Comparable<T>
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private int size;
    private BSTNode<T> root;

    /**
     * Creates an empty BinarySearchTree.
     */
    public BinarySearchTree() {
        this.size = 0;
        this.root = null;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns a handle to the root node of the tree, null if the tree is empty.
     *
     * @return root of the tree, null if tree is empty
     */
    public BSTNode<T> getRoot() {
        return this.root;
    }

    /**
     * Inserts the given item into the tree.
     *
     * @param item to be inserted.
     */
    public void insert(T item) {
        this.insert(this.root, item);
    }

    private void insert(BSTNode<T> node, T item) {
        // if tree is size 0, set the root to a new node
        if (this.size == 0) {
            this.root = new BSTNode<>(item);
            this.size++;
            return;
        }

        BSTNode<T> parent = null;
        BSTNode<T> currentNode = node;

        // walk the tree
        while (currentNode != null) {
            if (currentNode.getData().compareTo(item) < 0) { // go right
                parent = currentNode;
                currentNode = currentNode.getRight();
            } else { // go left
                parent = currentNode;
                currentNode = currentNode.getLeft();
            }
        }

        // insert the new node
        BSTNode<T> newNode = new BSTNode<>(item);
        newNode.setP(parent);
        if (parent != null) {
            if (parent.getData().compareTo(item) < 0) { // go right
                parent.setRight(newNode);
            } else { // go left
                parent.setLeft(newNode);
            }
        }
        this.size++;

        while (newNode != null) {
            newNode.setSize(newNode.getSize() + 1);
            newNode = newNode.getP();
        }
    }

    /**
     * Performs a pre-order traversal of the tree printing the value of each
     * node as it is encountered.
     */
    public void preOrder() {
        System.out.print("Pre-order: ");
        preOrder(this.root);
        System.out.print("\n");
    }

    private void preOrder(BSTNode<T> node) {
        if (node != null) {
            System.out.print(node.getData().toString() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    /**
     * Performs a post-order traversal of the tree printing the value of each
     * node as it is encountered.
     */
    public void postOrder() {
        System.out.print("Post-order: ");
        postOrder(this.root);
        System.out.print("\n");
    }

    private void postOrder(BSTNode<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getData().toString() + " ");
        }
    }

    /**
     * Performs an in-order traversal of the tree printing the value of each
     * node as it is encountered.
     */
    public void inOrder() {
        System.out.print("In-order: ");
        inOrder(this.root);
        System.out.print("\n");
    }

    private void inOrder(BSTNode<T> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getData().toString() + " ");
            inOrder(node.getRight());
        }
    }

    /**
     * Search for the given item in the tree
     *
     * @param item item to be searched for
     * @return the item if it is present in the tree, otherwise null
     */
    public T search(T item) {
        BSTNode<T> node = search(this.root, item);
        if (node != null) {
            return node.getData();
        }
        return null;
    }

    private BSTNode<T> search(BSTNode<T> node, T item) {
        while (node != null && !item.equals(node.getData())) {
            if (item.compareTo(node.getData()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return node;
    }


    /**
     * Deletes the given item from the tree.
     *
     * @param item to be deleted.
     */
    public void delete(T item) {
        BSTNode<T> node = search(this.root, item);
        if (node == null) return;
        delete(node);
    }

    private void delete(BSTNode<T> node) {
        BSTNode<T> newNode = minimum(node.getRight());
        if (node.getLeft() == null) {
            transplant(node, node.getRight());
            newNode = node.getP();
        } else if (node.getRight() == null) {
            transplant(node, node.getLeft());
            newNode = node.getP();
        } else {
            BSTNode<T> successor = minimum(node.getRight());
            if (successor != null) {
                if (successor.getP() != node) {
                    newNode = successor.getP();
                    transplant(successor, successor.getRight());
                    successor.setRight(node.getRight());
                    successor.getRight().setP(successor);
                }
                transplant(node, successor);
                successor.setLeft(node.getLeft());
                successor.getLeft().setP(successor);
            }
        }
        this.size--;

        while (newNode != null) {
            int newSize = 1;
            if (newNode.getLeft() != null) newSize += newNode.getLeft().getSize();
            if (newNode.getRight() != null) newSize += newNode.getRight().getSize();
            newNode.setSize(newSize);
            newNode = newNode.getP();
        }
    }

    /**
     * Returns the next item, in sorted order, form the given item in the tree.
     *
     * @param item to find the successor of
     * @return the successor of item, null if item has no successor
     */
    public T successor(T item) {
        BSTNode<T> result = search(this.root, item);
        if (result != null) {
            BSTNode<T> successor = successor(result);
            if (successor != null) return successor.getData();
        }
        return null;
    }

    private BSTNode<T> successor(BSTNode<T> node) {
        if (node.getRight() != null) {
            return minimum(node.getRight());
        }
        while (node.getP() != null && node == node.getP().getRight()) {
            node = node.getP();
        }
        return node.getP();
    }

    /**
     * Returns the previous item, in sorted order, form the given item in the tree.
     *
     * @param item to find the predecessor of
     * @return the predecessor of item, null if item has no predecessor
     */
    public T predecessor(T item) {
        BSTNode<T> result = search(this.root, item);
        if (result != null) {
            BSTNode<T> predecessor = predecessor(result);
            if (predecessor != null) return predecessor.getData();
        }
        return null;
    }

    private BSTNode<T> predecessor(BSTNode<T> node) {
        if (node.getLeft() != null) {
            return maximum(node.getLeft());
        }
        while (node.getP() != null && node == node.getP().getLeft()) {
            node = node.getP();
        }
        return node.getP();
    }

    /**
     * Returns the element with the given order-statistic.
     *
     * @param orderStatistic order-statistic to find within the tree.
     * @return the item with the given order-statistic, null no item has the given order-statistic.
     */
    public T select(int orderStatistic) {
        BSTNode<T> result = select(this.root, orderStatistic);

        return (result != null) ? result.getData() : null;
    }

    private BSTNode<T> select(BSTNode<T> node, int orderStatistic) {
        if (node != null) {
            int leftSize = (node.getLeft() != null) ? node.getLeft().getSize() : 0;
            int rank = leftSize + 1;
            if (orderStatistic == rank)
                return node;
            else if (orderStatistic < rank) {
                return select(node.getLeft(), orderStatistic);
            } else
                return select(node.getRight(), orderStatistic - rank);
        }
        return null;
    }


    /**
     * Finds the rank of the given element as compared to other elements in the tree.
     *
     * @param element to find the rank of.
     * @return the rank of the given element, -1 if the given element is not in the tree.
     */
    public int rank(T element) {
        BSTNode<T> node = search(this.root, element);
        if (node == null) {
            return -1;
        }
        return rank(node);
    }

    private int rank(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }

        int r = 1 + ((node.getLeft() != null) ? node.getLeft().getSize() : 0);
        BSTNode<T> currentNode = node;

        while (currentNode != this.root) {
            if (currentNode == currentNode.getP().getRight()) {
                int left = (currentNode.getLeft() != null) ? currentNode.getLeft().getSize() : 0;
                r = r + left + 1;
            }
            currentNode = currentNode.getP();
        }
        return r;
    }


    /**
     * Returns the maximum value contained in the tree.
     *
     * @return the maximum value contained in the tree.
     */
    public T maximum() {
        return maximum(this.root).getData();
    }

    private BSTNode<T> maximum(BSTNode<T> node) {
        BSTNode<T> currentNode = node;
        while (currentNode.getRight() != null) {
            currentNode = currentNode.getRight();
        }
        return currentNode;
    }

    /**
     * Returns the minimum value contained in the tree.
     *
     * @return the minimum value contained in the tree.
     */
    public T minimum() {
        BSTNode<T> result = minimum(this.root);
        return (result != null) ? result.getData() : null;
    }

    private BSTNode<T> minimum(BSTNode<T> node) {
        BSTNode<T> currentNode = node;
        if (currentNode != null) {
            while (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }
        }
        return currentNode;
    }

    private void transplant(BSTNode<T> u, BSTNode<T> v) {
        if (u.getP() == null) { // u was the root, make v the root
            this.root = v;
        } else if (u == u.getP().getLeft()) { // if u was a left child
            u.getP().setLeft(v); // v is the new left child
        } else { // u was a right child
            u.getP().setRight(v); // v is the new right child
        }
        if (v != null) {
            v.setP(u.getP()); // set v's parent as u's old parent
        }
    }
}
