package edu.csc220.recursion;

public class LetterFinder {
    /**
     * Returns the character in the input String which represents the letter that appears earliest in the alphabet. You
     * can assume that input is not an empty String, and that it only contains lower-case alphabetic letters.
     */
    public char findEarliestLetter(String input) {
        // TODO: Implement this. Replace this placeholder return statement with your own.

        // Base case: If the input length = 1, return back the letter
        if(input.length()==1){
            return input.charAt(0);
        }
        // Simplify: take the first letter of the string and comparing the findEarliestLetter with the rest of the string
        char firstLetter = input.charAt(0); // Takes the first letter of the string
        String restOfString = input.substring(1); // Takes the rest of the string, excluding the first letter
        char earliestLetterInOrder = findEarliestLetter(restOfString); // Simplify even further, pool -> p & ool, ool-> o & ol, ol-> o & l, l -> l
        if (firstLetter < earliestLetterInOrder){ // If the firstLetter is earlier than the first letter of the shortened string, return the firstLetter.
            return firstLetter;
        }
        return earliestLetterInOrder;
    }

    public static void main(String[] args) {
        LetterFinder letterFinder = new LetterFinder();

        // Should print out "pool: l".
        System.out.println("pool: " + letterFinder.findEarliestLetter("pool"));

        // Should print out "czar: a".
        System.out.println("czar: " + letterFinder.findEarliestLetter("czar"));

        // Should print out "alpine: a".
        System.out.println("alpine: " + letterFinder.findEarliestLetter("alpine"));
    }
}
