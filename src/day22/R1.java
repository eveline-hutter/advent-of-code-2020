package day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class R1 {

    public static long playPlayCards() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day22input.txt");
        List<String> input = Files.readAllLines(path);
        // separate card piles
        int separator = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals("")) {
                separator = i;
                break;
            }
        }
        List<Integer> p1cards = input.subList(1, separator).stream().map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> p2cards = input.subList(separator + 2, input.size()).stream().map(Integer::valueOf).collect(Collectors.toList());
        while ( !(p1cards.isEmpty() || p2cards.isEmpty()) ) {
            Integer cardP1 = p1cards.remove(0);
            Integer cardP2 = p2cards.remove(0);
            if (cardP1 > cardP2) {
                p1cards.add(cardP1);
                p1cards.add(cardP2);
            } else {
                p2cards.add(cardP2);
                p2cards.add(cardP1);
            }
        }
        return p1cards.isEmpty() ? getWinnersScore(p2cards) : getWinnersScore(p1cards);
    }

    public static long getWinnersScore(List<Integer> winnersCards) {
        long result = 0;
        int multiplier = winnersCards.size();
        for (Integer card : winnersCards) {
            result += multiplier * card;
            multiplier--;
        }
        return result;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(playPlayCards());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
