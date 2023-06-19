package edu.csc220.recursion;

import java.util.*;

public class WordCounter {
    /** Returns the number of Strings in words whose length is targetLength. */
    public int countWordsWithLength(ArrayList<String> words, int targetLength) {
        // TODO: Implement this. Replace this placeholder return statement with your own.
        // Base case: If length of the list is empty, return 0
        if(words.isEmpty()) {
            return 0;
        }
        // Simplify: Take the first word out of the list and check how many letters it has. If the # of letter = target length, add 1 to the count.
        String firstWord = words.remove(0); // Removing the first word of the ArrayList out of the list.
        int totalNumber = countWordsWithLength(words, targetLength); // Simplifying even further, removing the first word each time until base case is met.
        if (firstWord.length() == targetLength){ // If the first word of the list matches the target length we add 1 to the total count.
            totalNumber += 1;
        }
        return totalNumber;
    }

    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();

        // Base word list. In case countWordsWithLength modifies the list, we'll make a copy of this before passing it
        // into the method.
        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("apple");
        wordList.add("banana");
        wordList.add("cat");
        wordList.add("dog");
        wordList.add("eclair");
        wordList.add("falafel");
        wordList.add("gum");

        // Should print out "3 word(s) of length 3"
        ArrayList<String> wordListCopyA = new ArrayList<>(wordList);
        System.out.println(wordCounter.countWordsWithLength(wordListCopyA, 3) + " word(s) of length 3");

        // Should print out "1 word(s) of length 5"
        ArrayList<String> wordListCopyB = new ArrayList<>(wordList);
        System.out.println(wordCounter.countWordsWithLength(wordListCopyB, 5) + " word(s) of length 5");

        // Should print out "2 word(s) of length 6"
        ArrayList<String> wordListCopyC = new ArrayList<>(wordList);
        System.out.println(wordCounter.countWordsWithLength(wordListCopyC, 6) + " word(s) of length 6");

        // Should print out "0 word(s) of length 4"
        ArrayList<String> wordListCopyD = new ArrayList<>(wordList);
        System.out.println(wordCounter.countWordsWithLength(wordListCopyD, 4) + " word(s) of length 4");
    }
}
