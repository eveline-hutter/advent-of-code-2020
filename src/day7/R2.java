package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

    public static int getRequiredNoOfBags() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day7input.txt");
        List<String> input = Files.readAllLines(path);
        return getAllInnerBags(input, getInnerBags(input, "shiny gold")) - 1; // don't count the shiny gold bag itself
    }
    
    public static int getAllInnerBags(List<String> input, String[] innerBags) {
        if (innerBags[0].contains("no other")) {
            return 1;
        } else {
            int sum = 0;
            for (String bag : innerBags) {
                int multiplier = Character.getNumericValue(bag.charAt(1));
                int end = bag.length() - 2;
                for (int i = 5; i < end; i++) {
                    if (bag.charAt(i) == 'b' && bag.charAt(i + 1) == 'a' && bag.charAt(i + 2) == 'g') {
                        end = i - 1;
                    }
                }
                String searchString = bag.substring(3, end);
                sum += multiplier * (getAllInnerBags(input, getInnerBags(input, searchString)));
            }
            return sum + 1;
        }
    }

    public static String[] getInnerBags(List<String> input, String searchString) {
        for (String rule : input) {
            String[] splitStrings = rule.split("contain");
            String outerBag = splitStrings[0].split("bags")[0];
            if (outerBag.contains(searchString)) {
                return splitStrings[1].split(",");
            } 
        }
        return null;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getRequiredNoOfBags());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
