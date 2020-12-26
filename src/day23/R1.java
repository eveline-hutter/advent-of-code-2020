package day23;

public class R1 {

    public static void playCrabCups() {
        // start order example: 3 8 9 1 2 5 4 6 7 (start = 3)
        // start order puzzle input: 5 8 3 9 7 6 2 4 1 (start = 5)
        // create array where each cup (index) knows its successor
        int[] cups = {0, 5, 4, 9, 1, 8, 2, 6, 3, 7};
        int start = 5;
        int[] next3Cups = new int[3];
        for (int i = 0; i < 100; i++) {
            next3Cups[0] = cups[start];
            next3Cups[1] = cups[next3Cups[0]];
            next3Cups[2] = cups[next3Cups[1]];
            int destination = start == 1 ? 9 : start - 1;
            boolean destinationInRemovedCups = true;
            while (destinationInRemovedCups) {
                boolean removed = false;
                for (int nextCup : next3Cups) {
                    if (destination == nextCup) {
                        destination = destination == 1 ? 9 : destination - 1;
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
            cups[start] = cups[next3Cups[2]];
            cups[next3Cups[2]] = cups[destination];
            cups[destination] = next3Cups[0];
            start = cups[start];
        }
        // print order after cup 1
        int nextCup = 1;
        for (int i = 0; i < 8; i++) {
            System.out.print(cups[nextCup]);
            nextCup = cups[nextCup];
        }
    }
    
    public static void printOrder(int[] cups, int currentCup) {
        int i = 0;
        while (i < 9) {
            System.out.print(currentCup);
            currentCup = cups[currentCup];
            i++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        playCrabCups();
    }
}
