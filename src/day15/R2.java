package day15;

/**
 * class that only uses int arrays instead of Maps and Lists
 */
public class R2 {

    public static int get30000000thNo(int[] startingNumbers) {
        int[] memoryGameNumbers = new int[30000000];
        int[] lastOccurrences = new int[memoryGameNumbers.length];
        int[] secondLastOccurrences = new int[memoryGameNumbers.length];
        for (int i = 0; i < secondLastOccurrences.length; i++) {
            lastOccurrences[i] = -1;
            secondLastOccurrences[i] = -1;
        }
        // copy starting numbers into memory game 
        for (int i = 0; i < startingNumbers.length; i++) {
            int number = startingNumbers[i];
            memoryGameNumbers[i] = number;
            lastOccurrences[number] = i;
        }
        /**
         * memory game rules:
         * if number hasn't been spoken before: player says 0
         * if number has been spoken before: player says age of repeated number
         * (how many turns apart the number is from when it was previously spoken)
         */
        for (int i = startingNumbers.length; i < memoryGameNumbers.length; i++) {
            int numberToCheck = memoryGameNumbers[i - 1];
            int numberToWrite = 0;
            int lastOccurrence = secondLastOccurrences[numberToCheck];
            if (lastOccurrence == -1) {
                memoryGameNumbers[i] = 0;                
            } else {
                int age = i - 1 - lastOccurrence;
                memoryGameNumbers[i] = age;
                numberToWrite = age;
            }
            secondLastOccurrences[numberToWrite] = lastOccurrences[numberToWrite]; // copy second last occurrence 
            lastOccurrences[numberToWrite] = i; // add last occurrence 
        }
        return memoryGameNumbers[memoryGameNumbers.length - 1];
    }
    
    public static void main(String[] args) {
        System.out.println(get30000000thNo(new int[] {0, 3, 6}));
        System.out.println(get30000000thNo(new int[]{1, 3, 2}));
        System.out.println(get30000000thNo(new int[]{2, 1, 3}));
        System.out.println(get30000000thNo(new int[]{1, 2, 3}));
        System.out.println(get30000000thNo(new int[]{2, 3, 1}));
        System.out.println(get30000000thNo(new int[]{3, 2, 1}));
        System.out.println(get30000000thNo(new int[]{3, 1, 2}));
        System.out.println(get30000000thNo(new int[]{1, 0, 15, 2, 10, 13})); // puzzle input
    }

}
