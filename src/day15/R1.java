package day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R1 {

    public static int get2020thNo(int[] startingNumbers) {
        int[] memoryGameNumbers = new int[2020];
        Map<Integer, List<Integer>> noOccurrences = new HashMap<>();
        // copy starting numbers into memory game 
        for (int i = 0; i < startingNumbers.length; i++) {
            int number = startingNumbers[i];
            memoryGameNumbers[i] = number;
            noOccurrences.put(number, new ArrayList<>());
            noOccurrences.get(number).add(0, i); // add last occurrence at first position
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
            if (noOccurrences.get(numberToCheck).size() == 1) {
                memoryGameNumbers[i] = 0;                
            } else {
                int lastOccurrence = noOccurrences.get(numberToCheck).get(1);
                int age = i - 1 - lastOccurrence;
                memoryGameNumbers[i] = age;
                numberToWrite = age;
            }
            if (!noOccurrences.containsKey(numberToWrite)) {
                noOccurrences.put(numberToWrite, new ArrayList<>());
            }
            noOccurrences.get(numberToWrite).add(0, i); // add last occurrence at first position
        }
        return memoryGameNumbers[memoryGameNumbers.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(get2020thNo(new int[]{0, 3, 6}));
        System.out.println(get2020thNo(new int[]{1, 3, 2}));
        System.out.println(get2020thNo(new int[]{2, 1, 3}));
        System.out.println(get2020thNo(new int[]{1, 2, 3}));
        System.out.println(get2020thNo(new int[]{2, 3, 1}));
        System.out.println(get2020thNo(new int[]{3, 2, 1}));
        System.out.println(get2020thNo(new int[]{3, 1, 2}));
        System.out.println(get2020thNo(new int[]{1, 0, 15, 2, 10, 13})); // puzzle input
    }

}
