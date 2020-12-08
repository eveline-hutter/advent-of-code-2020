package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class R1 {

    public static int getNoOfBagColours() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day7input.txt");
        List<String> input = Files.readAllLines(path);
        List<String> bagsWithShinyGold = getOuterBags(input, "shiny gold");
        List<String> outerBags = getAllOuterBags(input, new ArrayList<String>(), bagsWithShinyGold);
        // remove duplicates
        return outerBags.size();
    }

    public static List<String> getAllOuterBags (List<String> input, List<String> outerBags, List<String> addList) {
        // check if addList only contains duplicates of outerBags
        int sizeBefore = outerBags.size();
        outerBags.addAll(addList);
        outerBags = outerBags.stream().distinct().collect(Collectors.toList());
        int sizeAfter = outerBags.size();
        if (sizeBefore == sizeAfter) {
            return outerBags;
        } else {
            List<String> newToAdd = new ArrayList<>();
            for (String bag : addList) {
                newToAdd.addAll(getOuterBags(input, bag));
            }
            return getAllOuterBags(input, outerBags, newToAdd).stream().distinct().collect(Collectors.toList());
        }
    }
    
    public static List<String> getOuterBags (List<String> input, String bagColour) {
        List<String> outerBags = new ArrayList<>();
        for (String rule : input) {
            String[] splitStrings = rule.split("contain");
            String outerBag = splitStrings[0].split("bags")[0];
            String[] innerBags = splitStrings[1].split(",");
            for (String innerBag : innerBags) {
                if (innerBag.contains(bagColour)) {
                    outerBags.add(outerBag.trim());
                } 
            }
        }
        return outerBags;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getNoOfBagColours());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
