package edu.csc220.collections;

import javax.swing.text.StyleContext;
import java.lang.reflect.Array;
import java.util.*;

public class Program {
    private void cancelingWords(ArrayList<String> words) {
        // TODO: Implement.
        // First we need to create a Stack
        Stack<String> wordStack = new Stack<>();
        // Then we need to add each word in ArrayList to the Stack
        for(String word: words) // For-each word in words list, we add to the Stack
            if(!wordStack.contains(word) || !word.equals(wordStack.peek())) { // If the word is NOT a duplicate OR adjacent pairs, add to Stack
                wordStack.push(word);
            } else { // Otherwise, if it's an adjacent pair, it will be removed from the Stack
                wordStack.pop();
            }
        for (String word : wordStack) { // Lastly print out whatever was left in the Stack
            System.out.println(word);
        }
    }

    private void shuffleLine(Queue<String> line, String newPersonInFront) {
        // TODO: Implement. Modify the line Queue directly.
        // If Given Name is not newPersonInFront, we want to cycle through the queue
        while(!newPersonInFront.equals(line.peek())){ // We want to loop until Given Name is the newPersonInFront, so we use while loop here
                line.offer(line.peek()); // We want to add the first person to the back of the queue until Given Name is newPersonInFront
                line.poll(); // We're removing the first name in list, so we can "cycle" through the queue, after adding to the back of the queue.
        }
    }

    private void printExtraNames(ArrayList<String> names) {
        // TODO: Implement.
        // First we need to create a Set
        TreeSet<String> NameSet = new TreeSet<>();
        for (String name : names) { // For-each name in the names list, we add to the Set
                if(!NameSet.contains(name)){ // This adds the "original" words
                    NameSet.add(name);
                } else { // This prints out the duplicate words as it goes through the list
                    System.out.println(name);
                }
        }
    }

    private void populateNameMap(ArrayList<String> names, Map<String, String> nameMap) {
        // TODO: Implement. Add any new entries to the nameMap parameter directly.
        // First we need to make a Map that will display the First Letter of the Name and their Name
        TreeMap<String, String> mapNames = new TreeMap<>();
        for(String name: names) { // For-each name in ArrayList names, we add to the Map
            String str = name; // Need to initialize String str = name, to get its first letter for each name in ArrayList names
            String firstLetter = str.substring(0,1); // To get the first letter of the name
            if (!mapNames.containsKey(firstLetter)) { // If the first letter is not a keyword in the map, we add both the first letter and name
                mapNames.put(firstLetter, name);
            } else { // If it does contain the first letter, we add the name to the same key
                String oldName= mapNames.get(firstLetter); // Since a Map only REPLACES the value, we need to combine Strings to add it to a list
                String newName= oldName + ", " +name;
                mapNames.put(firstLetter, newName);
            }
        }
        System.out.println(mapNames);
    }

    public static void main(String[] args) {
        Program program = new Program();
        program.testCancelingWords();
        program.testShuffleLine();
        program.testPrintExtras();
        program.testPopulateNameMap();
    }

    private void testCancelingWords() {
        ArrayList<String> words = new ArrayList<>();
        words.add("happy");
        words.add("sad");
        words.add("cat");
        words.add("cat");
        words.add("dog");
        words.add("dog");
        words.add("sad");

        System.out.println("Testing cancelingWords...");
        cancelingWords(words);
        System.out.println();
    }

    private void testShuffleLine() {
        Queue<String> line = new LinkedList<>();
        line.add("Troy");
        line.add("Annie");
        line.add("Britta");
        line.add("Abed");
        line.add("Shirley");
        line.add("Jeff");
        line.add("Pierce");

        System.out.println("Testing shuffleLine...");
        shuffleLine(line, "Abed");
        System.out.println("Queue is now: " + line);
        System.out.println();
    }

    private void testPrintExtras() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Troy");
        names.add("Annie");
        names.add("Britta");
        names.add("Jeff");
        names.add("Abed");
        names.add("Shirley");
        names.add("Abed");
        names.add("Jeff");
        names.add("Pierce");
        names.add("Abed");
        names.add("Troy");

        System.out.println("Testing printExtraNames...");
        printExtraNames(names);
        System.out.println();
    }

    private void testPopulateNameMap() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Troy");
        names.add("Annie");
        names.add("Britta");
        names.add("Abed");
        names.add("Shirley");
        names.add("Jeff");
        names.add("Pierce");
        names.add("Ben");
        names.add("Alf");
        TreeMap<String, String> nameMap = new TreeMap<>();

        System.out.println("Testing populateNameMap...");
        populateNameMap(names, nameMap);
        for (Map.Entry<String, String> entry: nameMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
