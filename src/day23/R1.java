package day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class R1 {

    public static void playCrabCups() {
        List<Integer> cups = new ArrayList<>(Arrays.asList(3, 8, 9, 1, 2, 5, 4, 6, 7));
        int currentCupPos = 0;
        int noOfCups = cups.size();
        for (int i = 0; i < 10; i++) {
            int currentCup = cups.get(currentCupPos);
            int threeCups1 = currentCupPos == noOfCups - 1 ? 0 : currentCupPos + 1;
            int threeCups2 = threeCups1 == noOfCups - 1 ? 0 : threeCups1 + 1;
            int threeCups3 = threeCups2 == noOfCups - 1 ? 0 : threeCups2 + 1;
            List<Integer> threeCups = new ArrayList<>();
            threeCups.add(cups.get(threeCups1));
            threeCups.add(cups.get(threeCups2));
            threeCups.add(cups.get(threeCups3));
            cups.removeAll(threeCups);
            int destinationCup = currentCup - 1;
            boolean destinationRemoved = threeCups.contains(Integer.valueOf(destinationCup));
            while (destinationRemoved) {
                destinationCup = destinationCup == 1 ? 9 : destinationCup - 1;
                destinationRemoved = threeCups.contains(Integer.valueOf(destinationCup));
            }
            int destinationCupPos = cups.indexOf(Integer.valueOf(destinationCup));
            cups.addAll(destinationCupPos + 1, threeCups);
            System.out.println(cups);
            currentCupPos = currentCupPos == cups.size() - 1 ? 0 : currentCupPos + 1;
        }
    }

    public static void main(String[] args) {
        playCrabCups();
    }
}
