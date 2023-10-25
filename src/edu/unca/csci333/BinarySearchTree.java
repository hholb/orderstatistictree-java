package edu.unca.csci333;

public class BinarySearchTree<T extends Comparable<T>> {
    private int size;
    private BSTNode<T> root;

    public BinarySearchTree() {
        this.size = 0;
        this.root = null;
    }

    public int getSize() {
        return this.size;
    }

    public BSTNode<T> getRoot() {
        return this.root;
    }

    public void insert(T item) {
        this.insert(this.root, item);
    }

    private void insert(BSTNode<T> node, T item) {
        // if tree is size 0, set the root to a new node
        if (this.size == 0) {
            this.root = new BSTNode<T>(item);
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
        BSTNode<T> newNode = new BSTNode<T>(item);
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


    public void delete(T item) {
        BSTNode<T> node = search(this.root, item);
        if (node == null) return;
        delete(node);
    }

    public void delete(BSTNode<T> node) {
        BSTNode<T> newNode = minimum(node.getRight());
        if (node.getLeft() == null) {
            transplant(node, node.getRight());
            newNode = node.getP();
        } else if (node.getRight() == null) {
            transplant(node, node.getLeft());
            newNode = node.getP();
        } else {
            BSTNode<T> successor = minimum(node.getRight());
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
        this.size--;

        while (newNode != null) {
            int size = 1;
            if (newNode.getLeft() != null) size += newNode.getLeft().getSize();
            if (newNode.getRight() != null) size += newNode.getRight().getSize();
           newNode.setSize(size);
           newNode = newNode.getP();
        }
    }

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

    public T predecessor (T item) {
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

    public T select(int order_statistic) {
        BSTNode<T> result = select(this.root, order_statistic);
        return result.getData();
    }

    private BSTNode<T> select(BSTNode<T> node, int order_statistic) {
        if (node != null) {
            int leftSize = (node.getLeft() != null) ? node.getLeft().getSize() : 0;
            int rank = leftSize + 1;
            if (order_statistic == rank)
                return node;
            else if (order_statistic < rank) {
                return select(node.getLeft(), order_statistic);
            } else
                return select(node.getRight(), order_statistic - rank);
        }
        return null;
    }


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

    public T minimum() {
        return minimum(this.root).getData();
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
