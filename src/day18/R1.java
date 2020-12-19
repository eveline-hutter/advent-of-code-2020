package day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static long getHomeworkSum() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day18input.txt");
        List<String> input = Files.readAllLines(path);
        long sum = 0;
        for (String calculation : input) {
            sum += getResult(calculation);
        }
        return sum;
    }

    public static long getResult(String calculation) {
        if (calculation.contains("(")) {
            int end = 0;
            while (calculation.charAt(end) != ')') {
                end++;
            }        
            int start = end - 1;            
            while (calculation.charAt(start) != '(') {
                start--;
            }           
            String subCalculation = calculation.substring(start + 1, end);
            long calculatedSub = calculate(subCalculation);
            String calculationWithRemovedBrackets = calculation.substring(0, start) + calculatedSub + calculation.substring(end + 1);
            return getResult(calculationWithRemovedBrackets);
        } else {
            return calculate(calculation);
        }
    }
    
    public static long calculate(String calculation) {
        String[] split = calculation.split(" ");
        long result = Long.parseLong(split[0]);
        for (int i = 1; i < split.length - 1; i += 2) {
            if (split[i].equals("+")) {
                result += Long.parseLong(split[i + 1]);
            } else {
                result *= Long.parseLong(split[i + 1]);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getHomeworkSum());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
