package edu.unca.csci333;

import java.util.Random;

public class Main {
    private static final int BOUND = 50; // Upper bound for number generation
    private static final int NUM_ELEMENTS = 25; // max number of elements to insert during tests
    private static final int NUM_UNKNOWN = 5; // number of unknown elements to search for

    public static void main(String[] args) {
        Random rand = new Random();
        int[] known_elements = new int[NUM_ELEMENTS];
        int[] unknown_elements = new int[NUM_UNKNOWN];

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        for (int i = 0; i < NUM_UNKNOWN; i++) {
            int unknown = rand.nextInt(100);
            unknown_elements[i] = unknown + BOUND;
        }

        for (int i = 0; i < NUM_ELEMENTS; i++) {
            int known  = rand.nextInt(BOUND);
            tree.insert(known);
            known_elements[i] = known;
        }

        tree.preOrder();
        tree.inOrder();
        tree.postOrder();
        System.out.printf("Tree size: %d\n", tree.getSize());

        System.out.println("Searching for unknown elements...");
        for (int i : unknown_elements) {
            System.out.printf("Searching for: %d\n", i);
            System.out.println("Found: " + tree.search(i));
        }

        System.out.println("Searching for known elements...");
        for (int i = 0; i < NUM_UNKNOWN; i++) {
                System.out.printf("Searching for: %d\n", known_elements[i]);
                System.out.println("Found: " + tree.search(known_elements[i]));
        }

        System.out.println("Deleting some elements...");
        for (int i = 0; i < NUM_UNKNOWN; i++) {
            System.out.printf("Deleting element: %d\n", known_elements[i]);
            tree.delete(known_elements[i]);
            System.out.println("After delete:");
            tree.inOrder();
            System.out.printf("Tree size: %d\n", tree.getSize());
        }

        System.out.println("Selecting some elements by order statistic...");
        for (int i = 1; i < 6; i++) {
            System.out.printf("Selecting %dth order statistic.\n", i);
            System.out.printf("Result: %d\n", tree.select(i));
        }

        System.out.println("Finding the rank of some elements...");
        for (int i = 0; i < NUM_UNKNOWN; i++) {
            int randomIndex = rand.nextInt(NUM_ELEMENTS);
            int element = known_elements[randomIndex];
            System.out.printf("Finding rank of element: %d\n", element);
            System.out.printf("Result: %d\n", tree.rank(element));
        }

    }
}
