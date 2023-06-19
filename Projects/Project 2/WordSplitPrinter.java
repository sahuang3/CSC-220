package edu.csc220.recursion;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class WordSplitPrinter {
    /**
     * Finds and prints out every possible way to split the input String into individual, valid English words (including
     * if input is itself a single valid word). You must call printWordSplit below to actually print out the results.
     *
     * Remember to implement a helper method, and have findWordSplits call that helper method.
     */
    public void findWordSplits(String input, TreeSet<String> allWords) {
        // TODO: Implement this.
        // Need to implement and call a helper method
        findWordSplitHelper(input, allWords, new ArrayList<String>());
    }
        // Helper method for findWordSplits, need to add parameter ArrayList to run printWordSplit
    private void findWordSplitHelper(String input, TreeSet<String> allWords, ArrayList<String> actualWords) {
        // Base case: If input is empty, print out all of the words found
        if(input.isEmpty()){
            printWordSplit(actualWords);
        }
        // Simplify: Take the permutations that ONLY starts with letter "I" and checks if it's an actual word
        for(int i=0; i < input.length(); i++) { // Using for loop to take each letter of 'isawabus'
                String CurrentPermutation = input.substring(0, i+1); // Creating a new string to store and check if there is a word in the permutations from the first letter to i+1
                // Need to check if CurrentPermutation is in the list of words. If it is, we add to ArrayList
                if(allWords.contains(CurrentPermutation)){
                    ArrayList<String> storeActualWords = new ArrayList<>(actualWords); // Creating an ArrayList 'storeActualWords' to copy existing words from ArrayList 'actualWords'
                    storeActualWords.add(CurrentPermutation); // If the current permutation is a word, we add it to ArrayList 'storeActualWords'
                    String remainingWords = input.substring(i+1); // After adding to the ArrayList, we have to "remove" the word from the rest of the remaining letters
                    findWordSplitHelper(remainingWords, allWords, storeActualWords); // Then we use recursion for the remaining permutations
                }
            }
        }

    /**
     * Prints out a word split, i.e. one particular arrangement of words. You should use this once you've finished
     * exploring a potential word split and would like to print it out.
     */
    private void printWordSplit(ArrayList<String> words) {
        if (words.isEmpty()) {
            System.out.println("(empty word list)");
        } else {
            System.out.print(words.get(0));
            for (int i = 1; i < words.size(); i++) {
                System.out.print(" " + words.get(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TreeSet<String> dictionary = readWords();
        WordSplitPrinter wordSplitPrinter = new WordSplitPrinter();

        // Expected to print out:
        // i saw a bus
        // i saw ab us
        // is aw a bus
        // is aw ab us
        wordSplitPrinter.findWordSplits("isawabus", dictionary);
    }

    // Reads the "words.txt" file and returns the words in a TreeSet<String>. This is completely implemented for you!
    private static TreeSet<String> readWords() {
        TreeSet<String> allWords = new TreeSet<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("words.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                allWords.add(line.toLowerCase());
            }
            bufferedReader.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return allWords;
    }
}
