package day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class R2 {

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
            System.out.println("p1: " + p1cards + "   p2: " + p2cards);
            Integer cardP1 = p1cards.remove(0);
            Integer cardP2 = p2cards.remove(0);
            boolean p1Wins = false;
            // check if stacks are big enough
            if (! (p1cards.size() >= cardP1 && p2cards.size() >= cardP2) ) {
                p1Wins = cardP1 > cardP2;
            } else {
                Map<List<Integer>, List<List<Integer>>> memory = new HashMap<>();
                List<Integer> p1cardsCopy = deepCopyList(p1cards.subList(0, cardP1));
                List<Integer> p2cardsCops = deepCopyList(p2cards.subList(0, cardP2));
                p1Wins = playRecursiveCrab(p1cardsCopy, p2cardsCops, memory);
            }            
            if (p1Wins) {
                p1cards.add(cardP1);
                p1cards.add(cardP2);
            } else {
                p2cards.add(cardP2);
                p2cards.add(cardP1);
            }
        }
        return p1cards.isEmpty() ? getWinnersScore(p2cards) : getWinnersScore(p1cards);
    }

    public static boolean playRecursiveCrab(List<Integer> p1cards, List<Integer> p2cards, Map<List<Integer>, List<List<Integer>>> memory) {
        while (! (p1cards.isEmpty() || p2cards.isEmpty())) {
            // check if card stacks are same as some time before 
            if (cardStacksSameAsBefore(p1cards, p2cards, memory)) {
                return true;
            }
            // add list combinations to memory
            boolean alreadyAdded = false;
            for (Map.Entry<List<Integer>, List<List<Integer>>> entry : memory.entrySet())
            {
                List<Integer> key = entry.getKey();
                List<List<Integer>> value = entry.getValue();
                if (key.equals(p1cards)) {
                    value.add(p2cards);
                    alreadyAdded = true;
                }
            }
            if (!alreadyAdded) {
                List<List<Integer>> p1List = new ArrayList<>();
                p1List.add(deepCopyList(p2cards));
                memory.put(deepCopyList(p1cards), p1List);
            } 
            // play recursive crab
            Integer cardP1 = p1cards.remove(0);
            Integer cardP2 = p2cards.remove(0);
            boolean p1Wins = false;
            // check if stacks are big enough
            if (! (p1cards.size() >= cardP1 && p2cards.size() >= cardP2) ) {
                p1Wins = cardP1 > cardP2;
            } else {                
                List<Integer> p1cardsCopy = deepCopyList(p1cards.subList(0, cardP1));
                List<Integer> p2cardsCopy = deepCopyList(p2cards.subList(0, cardP2));
                p1Wins = playRecursiveCrab(p1cardsCopy, p2cardsCopy, memory);                
            }
            if (p1Wins) {
                p1cards.add(cardP1);
                p1cards.add(cardP2);
            } else {
                p2cards.add(cardP2);
                p2cards.add(cardP1);
            }
        }
        return !p1cards.isEmpty();
    }
    
    public static List<Integer> deepCopyList(List<Integer> original) {
        List<Integer> deepCopy = new ArrayList<>();
        for (Integer entry : original) {
            deepCopy.add(entry);
        }
        return deepCopy;
    }
    
    public static boolean cardStacksSameAsBefore(List<Integer> p1cards, List<Integer> p2cards, Map<List<Integer>, List<List<Integer>>> memory) {
        for (Map.Entry<List<Integer>, List<List<Integer>>> entry : memory.entrySet()) {
            List<Integer> key = entry.getKey();
            List<List<Integer>> values = entry.getValue();
            if (key.equals(p1cards)) {
                for (List<Integer> value : values) {
                    if (value.equals(p2cards)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
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
