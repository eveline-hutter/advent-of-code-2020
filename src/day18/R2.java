package day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

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
        if (calculation.contains("+")) {
            String[] split = calculation.split(" ");
            StringBuilder newCalculationString = new StringBuilder();            
            for (int i = 1; i < split.length - 1; i += 2) {
                if (split[i].equals("+")) {
                    long result = Long.parseLong(split[i - 1]) + Long.parseLong(split[i + 1]);
                    split[i - 1] = "1";
                    split[i] = "*";
                    split[i + 1] = result + "";
                    for (int j = 0; j < split.length; j++) {
                        newCalculationString.append(split[j]).append(" ");
                    }
                    break;
                }
            }
            return calculate(newCalculationString.toString());
        } else {
            String[] split = calculation.split(" ");
            long result = Long.parseLong(split[0]);
            for (int i = 2; i < split.length; i += 2) {
                result *= Long.parseLong(split[i]);
            }
            return result;
        }        
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getHomeworkSum());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
