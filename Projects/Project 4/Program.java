package edu.csc220.linkedlist;

import java.util.*;

public class Program {
    /**
     * insertInSortedOrder assumes that the singly linked list starting at head is already arranged in increasing sorted
     * order, alphabetically. It modifies the list by inserting a new node containing newElement in the correct place so
     * that the list remains in sorted order. The method should return the head node of the modified list.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public SingleNode insertInSortedOrder(SingleNode head, String newElement) {
        // TODO: Implement.
        // Want to insert 'Amelia' in between 'Alice' and 'Bob'. Since we need to compare 2 strings, we should make a 'prev' node
        SingleNode prev = null; // To keep track so we don't overstep a node when we insert
        SingleNode curr = head;
        while (curr != null){ // While loop when curr is not empty
            if(newElement.compareTo(curr.getElement()) < 0){ // Checking to see if newElement is smaller than the current String curr.
                break; // Once we found the correct position of where newElement should be inserted, we break out of the while loop
            }
            // Make is so our curr goes to the next node while keeping a previous version
            prev = curr;
            curr = curr.getNext();
        }
        // When we found the correct location of where the new node should go
        SingleNode newNode = new SingleNode(newElement); // Creating a new node that stores newElement
        newNode.setNext(curr); // Setting the newElement's next node's destination to curr (curr = Bob)
        prev.setNext(newNode); // Setting the newElement's prev node's destination, which is to this newNode
        return head;
    }

    /**
     * removeAllWithLength should modify the singly linked list starting at head by removing all Strings whose length
     * is equal to the length parameter. The method should return the head node of the modified list.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public SingleNode removeAllWithLength(SingleNode head, int length) {
        // TODO: Implement.
        // We want to remove all Elements with the given target length
        SingleNode prev = null; // Since we're removing things in the middle of the list, we would want to keep a prev node
        SingleNode curr = head;
        while (curr!= null){ // While loop when curr is not empty
            if(curr.getElement().length() == length){ // Checking to see if the length of curr is equal to the given target length
                // If curr length = target length, we want to remove that element (skipping that element)
                prev.setNext(curr.getNext()); // Setting the prev node's destination to the current node's next location (skipping curr)
                curr = curr.getNext(); // We're skipping curr, so we set curr to the node after it
                continue; // Since we're removing more than 1 element, we can't have break here, so we have to use continue to keep going
            }
            // Make it so our curr goes to the next node while keeping a previous copy
            prev = curr;
            curr = curr.getNext();
        }
        return head;
    }

    /**
     * insertInSortedOrder assumes that the doubly linked list starting at head is already arranged in increasing sorted
     * order, alphabetically. It modifies the list by inserting a new node containing newElement in the correct place so
     * that the list remains in sorted order.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public DoubleNode insertInSortedOrder(DoubleNode head, String newElement) {
        // TODO: Implement.
        // We want to insert 'Amelia' in between 'Alice' and 'Bob'. Need to change Amelia setNext/Prev + Alice setNext + Bob setPrev
        DoubleNode prev = null;
        DoubleNode curr = head;
        while (curr != null){ // While loop when curr is not empty
            if(newElement.compareTo(curr.getElement()) < 0){ // Checking to see if newElement is less than the current String curr.
                break; // Once we found the correct position of where newElement should be inserted, we break out of the while loop
            }
            // Make is so our curr goes to the next node while keeping a previous version
            prev = curr;
            curr = curr.getNext();
        }
        // Testing/Debugging:
        // curr = Alice, prev = null. [newElement.compareTo(curr.getElement()) < 0] is false, so prev = Alice, curr = Bob.
        // curr = Bob, prev = Alice. [newElement.compareTo(curr.getElement()) < 0] is true, newElement is between Alice and Bob.
        // What it should be: (Alice, Amelia, Bob, Carlos)
        // Now we need to change the 4 things (Amelia setNext/Prev, Alice setNext, Bob setPrev)

        // When we found the correct location of where the new node should go. Need to change Amelia setNext/Prev + Alice setNext + Bob setPrev
        DoubleNode newNode = new DoubleNode(newElement); // Creating a new double node that stores newElement
        newNode.setPrev(prev); // Setting Amelia's Prev to Alice
        newNode.setNext(curr); // Setting Amelia's Next to Bob
        prev.setNext(newNode); // Setting Alice's Next to Amelia
        curr.setPrev(newNode); // Setting Bob's Prev to Amelia
        return head;
    }


    public static void main(String[] args) {
        Program program = new Program();

        System.out.print("Checking insertInSortedOrder for singly linked list... ");
        SingleNode ll1 = createSinglyLinkedList("Alice", "Bob", "Carlos");
        ll1 = program.insertInSortedOrder(ll1, "Amelia");
        verify(ll1, "Alice", "Amelia", "Bob", "Carlos");
        System.out.println("Done!");

        System.out.print("Checking removeAllWithLength for singly linked list... ");
        SingleNode ll2 = createSinglyLinkedList("salad", "carrot", "apple", "celery", "crouton", "banana");
        ll2 = program.removeAllWithLength(ll2, 6);
        verify(ll2, "salad", "apple", "crouton");
        System.out.println("Done!");


        System.out.print("Checking insertInSortedOrder for doubly linked list... ");
        DoubleNode ll3 = createDoublyLinkedList("Alice", "Bob", "Carlos");
        ll3 = program.insertInSortedOrder(ll3, "Amelia");
        verify(ll3, "Alice", "Amelia", "Bob", "Carlos");
        System.out.println("Done!");
    }

    /** You SHOULD NOT call this method in your implementation. */
    private static SingleNode createSinglyLinkedList(String... elements) {
        SingleNode head = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            SingleNode node = new SingleNode(elements[i]);
            node.setNext(head);
            head = node;
        }
        return head;
    }

    /** You SHOULD NOT call this method in your implementation. */
    private static DoubleNode createDoublyLinkedList(String... elements) {
        DoubleNode head = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            DoubleNode node = new DoubleNode(elements[i]);
            if (head != null) {
                node.setNext(head);
                head.setPrev(node);
            }
            head = node;
        }
        return head;
    }

    private static void verify(SingleNode head, String... expected) {
        HashSet<SingleNode> nodesSeen = new HashSet<>();
        ArrayList<String> elements = new ArrayList<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list! We've already seen '%s' before.",
                                head.getElement()));
            }
            nodesSeen.add(head);
            elements.add(head.getElement());
            head = head.getNext();
        }

        List<String> expectedElements = Arrays.asList(expected);
        if (!expectedElements.equals(elements)) {
            throw new RuntimeException(
                    String.format("\nExpected: %s\nActual:   %s", expectedElements, elements));
        }
    }

    private static void verify(DoubleNode head, String... expected) {
        HashSet<DoubleNode> nodesSeen = new HashSet<>();
        ArrayList<String> elements = new ArrayList<>();

        DoubleNode tail = null;
        while (head != null) {
            if (nodesSeen.contains(head)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list (forward direction)! We've already seen '%s' before.",
                                head.getElement()));
            }
            nodesSeen.add(head);
            elements.add(head.getElement());
            tail = head;
            head = head.getNext();
        }

        nodesSeen.clear();
        ArrayList<String> reverseElements = new ArrayList<>();
        while (tail != null) {
            if (nodesSeen.contains(tail)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list (reverse direction)! We've already seen '%s' before.",
                                tail.getElement()));
            }
            nodesSeen.add(tail);
            reverseElements.add(tail.getElement());
            tail = tail.getPrev();
        }

        List<String> expectedElements = Arrays.asList(expected);
        if (!expectedElements.equals(elements)) {
            throw new RuntimeException(
                    String.format("\nExpected: %s\nActual:   %s", expectedElements, elements));
        }

        Collections.reverse(expectedElements);
        if (!expectedElements.equals(reverseElements)) {
            throw new RuntimeException(
                    String.format(
                            "\nExpected (reversed): %s\nActual (reversed):   %s", expectedElements, reverseElements));
        }
    }
}
