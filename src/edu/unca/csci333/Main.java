package edu.unca.csci333;

import java.util.Random;

public class Main {
    private static final int NUM_ELEMENTS = 25; // max number of elements to insert during tests
    private static final int NUM_UNKNOWN = 5; // number of unknown elements to search for

    public static void main(String[] args) {
        Random rand = new Random();
        int[] known_elements = new int[NUM_ELEMENTS];
        int[] unknown_elements = new int[NUM_UNKNOWN];

        BinarySearchTree tree = new BinarySearchTree();

        for (int i = 0; i < NUM_UNKNOWN; i++) {
            int unknown = rand.nextInt(100);
            unknown_elements[i] = unknown;
        }

        for (int i = 0; i < NUM_ELEMENTS; i++) {
            int known  = rand.nextInt(10);
            tree.insert(known);
            known_elements[i] = known;
        }

        tree.preOrder();
        tree.postOrder();
        System.out.printf("Tree size: %d\n", tree.getSize());

        System.out.println("Searching for unknown elements...");
        for (int i : unknown_elements) {
            System.out.printf("Searching for: %d\n", i);
            System.out.println("Found: " + tree.search(i));
        }

        System.out.println("Searching for known elements...");
        for (int i : known_elements) {
            if (i < NUM_UNKNOWN) {
                System.out.printf("Searching for: %d\n", i);
                System.out.println("Found: " + tree.search(i));
            }
            else break;
        }

        System.out.println("Deleting some elements...");
        for (int i : known_elements) {
            if (i < NUM_UNKNOWN) {
                System.out.printf("Deleting element: %d\n", i);
                tree.delete(i);
                known_elements[i] = 0;
            }
            else break;
        }

        System.out.println("Selecting some elements by order statistic...");
        for (int i = 1; i < 6; i++) {
            System.out.printf("Selecting %dth order statistic.\n", i);
            System.out.printf("Result: %d\n", tree.select(i));
        }

        System.out.println("Finding the rank of some elements...");
        for (int i = 1; i < 6; i++) {
            System.out.printf("Finding element with rank: %d\n", i);
            System.out.printf("Result: %d\n", tree.rank(known_elements[i]));
        }
    }
}
