package day23;

import java.math.BigInteger;

public class R2 {

    public static void playCrabCups() {
        // start order example: 3 8 9 1 2 5 4 6 7 (start = 3)
        // int[] cups = {0, 2, 5, 8, 6, 4, 7, 3, 9, 1};
        // start order puzzle input: 5 8 3 9 7 6 2 4 1 (start = 5)
        // create array where each cup (index) knows its successor
        int[] cups = {0, 5, 4, 9, 1, 8, 2, 6, 3, 7};
        int start = 5;
        // expand to 1 million cups (don't count the first)
        int mio = 1000000;
        int[] millionCups = new int[mio + 1];
        // copy array and find out which cup points to start cup
        int last = 0;
        for (int i = 0; i < cups.length; i++) {
            millionCups[i] = cups[i];
            if (millionCups[i] == start) {
                last = i;
            }
        }
        millionCups[last] = cups.length;
        millionCups[millionCups.length - 1] = start;
        for (int i = millionCups[last]; i < millionCups.length - 1; i++) {
            millionCups[i] = i + 1;
        }
        int[] next3Cups = new int[3];
        for (int i = 0; i < 10 * mio; i++) {
            next3Cups[0] = millionCups[start];
            next3Cups[1] = millionCups[next3Cups[0]];
            next3Cups[2] = millionCups[next3Cups[1]];
            int destination = start == 1 ? mio : start - 1;
            boolean destinationInRemovedCups = true;
            while (destinationInRemovedCups) {
                boolean removed = false;
                for (int nextCup : next3Cups) {
                    if (destination == nextCup) {
                        destination = destination == 1 ? mio : destination - 1;
                        removed = true;
                        break;
                    }
                }
                destinationInRemovedCups = removed;
            }
            /* change pointers:
             * - from start to next after the three removed cups
             * - from last of three removed cups to the one after the destination cup
             * - from the destination cup to the first of the three removed cups
             */
            millionCups[start] = millionCups[next3Cups[2]];
            millionCups[next3Cups[2]] = millionCups[destination];
            millionCups[destination] = next3Cups[0];
            start = millionCups[start];
        }
        int firstCup = millionCups[1];
        int secondCup = millionCups[millionCups[1]];
        BigInteger result = BigInteger.valueOf(firstCup).multiply(BigInteger.valueOf(secondCup));
        System.out.println("first cup after cup 1 is " + firstCup);
        System.out.println("second cup after cup 1 is " + secondCup);
        System.out.println("multiplied result is " + result);
    }

    public static void main(String[] args) {
        playCrabCups();
    }

}
