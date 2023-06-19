package edu.csc220.trees;

import java.util.ArrayList;

public class CountingBST {
    /**
     * Inserts the element into the counting binary search tree starting at the given root. If there is already a node
     * in the tree containing element, its count should be incremented.
     */
    public Node add(Node root, String element) {
        // TODO: Implement.
        // First initialize 'curr' and 'prev'-to make sure not to overstep
        Node prev = null;
        Node curr = root;
        // Listing all possible cases
        while (curr != null) { // When list is not an empty set
            if (element == curr.getElement()){ // Base Case: if root == element, return root
                curr.incrementCount(); // If element is already in tree, increase count by 1
                return root;
            } else if (element.compareTo(curr.getElement()) < 0){ // If element < root, update prev to curr and set Left side of curr
                prev = curr;
                curr = curr.getLeft();
            } else{ // If element > root, update prev to curr and set Right side of curr
                prev = curr;
                curr = curr.getRight();
            }
        }
        // Setting Prev Node's Left or Right for the Node with Element
        Node newNode = new Node(element); // Creating a new Node to store element
        if (element.compareTo(prev.getElement()) < 0){ // If element < prev, set the prev node's left side to newNode
            prev.setLeft(newNode);
        } else { // If element > prev, set the prev node's right side to newNode
            prev.setRight(newNode);
        }
        return root;
    }

    /** Returns the number of occurrences of element in the counting binary search tree starting at the given root. */
    public int getCount(Node root, String element) {
        // TODO: Implement.
        // Need to check if tree contains element
        Node curr = root;
        while (curr != null){ // While list is not an empty set
            if (element == curr.getElement()){ // If element == curr, return the count of curr
                return curr.getCount();
            } else if (element.compareTo(curr.getElement()) < 0){ // If element < root, search on the left side of tree
                curr = curr.getLeft();
            } else{ // If element > root, search on the right side of tree
                curr = curr.getRight();
            }
        }
        return 0; // If element is not in tree, return 0
    }

    public void run() {
        System.out.println("Printouts for insert:");

        // First, we'll set up the tree with individual words with no duplicates.
        Node root = new Node("mango");
        root = add(root, "falafel");
        root = add(root, "venison");
        root = add(root, "kale");
        root = add(root, "turkey");
        root = add(root, "zucchini");
        root = add(root, "cheese");
        root = add(root, "pierogi");
        root = add(root, "baklava");
        root = add(root, "udon");
        root = add(root, "ramen");

        // The first printout should list the above words in alphabetical order.
        print(root);

        // Next, we'll add duplicates to the tree.
        root = add(root, "turkey");
        root = add(root, "udon");
        root = add(root, "turkey");
        root = add(root, "pierogi");
        root = add(root, "turkey");

        // The second printout should still be in alphabetical order, but with two copies of each of "udon" and
        // "pierogi", and four copies of "turkey".
        print(root);

        System.out.println();
        System.out.println("Printouts for getCount:");

        // Check some specific counts. "baklava" should have 1, "turkey" should have 4, and "pastrami" should have 0.
        System.out.println("baklava: " + getCount(root, "baklava"));
        System.out.println("turkey: " + getCount(root, "turkey"));
        System.out.println("pastrami: " + getCount(root, "pastrami"));
    }

    /**
     * Prints out the contents of the tree whose root is the provided node. This method will attempt to print the
     * elements in alphabetical order, and will include the count of each word as well.
     */
    private void print(Node root) {
        ArrayList<NodeInfo> elements = new ArrayList<>();
        addToList(root, elements);
        System.out.println(elements);
    }

    /** A helper method that print uses to collect all of the node information from the tree into a list. */
    private void addToList(Node root, ArrayList<NodeInfo> elements) {
        if (root != null) {
            addToList(root.getLeft(), elements);
            elements.add(new NodeInfo(root.getElement(), root.getCount()));
            addToList(root.getRight(), elements);
        }
    }

    /**
     * This class is only used in printing out a tree's contents, used by the print method above. You can completely
     * ignore this.
     */
    private static class NodeInfo {
        private final String element;
        private final int count;

        NodeInfo(String element, int count) {
            this.element = element;
            this.count = count;
        }

        @Override
        public String toString() {
            return String.format("%s: %d", element, count);
        }
    }

    public static void main(String[] args) {
        CountingBST bst = new CountingBST();
        bst.run();
    }
}
