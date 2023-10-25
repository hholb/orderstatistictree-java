// Hayden Holbrook
// CSCI-333 Fall 2023
// October 25, 2023
// Homework 6 Order Statistic Tree
package edu.unca.csci333;

import java.util.Random;

/**
 * Creates a BST and runs through some of its functionality.
 */
public class Main {
    private static final int BOUND = 50; // Upper bound for number generation
    private static final int NUM_ELEMENTS = 25; // max number of elements to insert during tests
    private static final int NUM_UNKNOWN = 5; // number of unknown elements to search for

    public static void main(String[] args) {
        Random rand = new Random();
        int[] knownElements = new int[NUM_ELEMENTS];
        int[] unknownElements = new int[NUM_UNKNOWN];

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        for (int i = 0; i < NUM_UNKNOWN; i++) {
            int unknown = rand.nextInt(100);
            unknownElements[i] = unknown + BOUND;
        }

        for (int i = 0; i < NUM_ELEMENTS; i++) {
            int known = rand.nextInt(BOUND);
            tree.insert(known);
            knownElements[i] = known;
        }

        tree.preOrder();
        tree.inOrder();
        tree.postOrder();
        System.out.printf("Tree size: %d%n", tree.getSize());

        System.out.println("Searching for unknown elements...");
        for (int i : unknownElements) {
            System.out.printf("Searching for: %d%n", i);
            System.out.println("Found: " + tree.search(i));
        }

        System.out.println("Searching for known elements...");
        for (int i = 0; i < NUM_UNKNOWN; i++) {
            System.out.printf("Searching for: %d%n", knownElements[i]);
            System.out.println("Found: " + tree.search(knownElements[i]));
        }

        System.out.println("Deleting some elements...");
        for (int i = 0; i < NUM_UNKNOWN; i++) {
            System.out.printf("Deleting element: %d%n", knownElements[i]);
            tree.delete(knownElements[i]);
            System.out.println("After delete:");
            tree.inOrder();
            System.out.printf("Tree size: %d%n", tree.getSize());
        }

        System.out.println("Selecting some elements by order statistic...");
        for (int i = 1; i < 6; i++) {
            System.out.printf("Selecting %dth order statistic.%n", i);
            System.out.printf("Result: %d%n", tree.select(i));
        }

        System.out.println("Finding the rank of some elements...");
        for (int i = 0; i < NUM_UNKNOWN; i++) {
            int randomIndex = rand.nextInt(NUM_ELEMENTS);
            int element = knownElements[randomIndex];
            System.out.printf("Finding rank of element: %d%n", element);
            System.out.printf("Result: %d%n", tree.rank(element));
        }

    }
}
