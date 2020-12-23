package day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class R1 {

    public static int getValidMessages() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day19input.txt");
        List<String> input = Files.readAllLines(path);
        int divider = -1;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals("")) {
                divider = i;
            }
        }
        List<String> rules = input.subList(0, divider);
        List<String> messages = input.subList(divider + 1, input.size());
        // delete leading numbers
        for (int i = 0; i < rules.size(); i++) {
            rules.set(i, rules.get(i).replaceAll("[0-9]*: ", ""));
        }
        String[] evaluatedRules = new String[rules.size()];
        String[] subrules = new String[rules.size()];
        for (int i = 0; i < rules.size(); i++) {
            String rule = rules.get(i);
            if (rule.charAt(0) == '"') {
                evaluatedRules[i] = rule.replaceAll("\"", "");
            } else {
                subrules[i] = rules.get(i);
            }
        }
        System.out.println(Arrays.toString(evaluatedRules));
        System.out.println(Arrays.toString(subrules));
        while (!allSubrulesEvaluated(subrules)) {
            for (int i = 0; i < subrules.length; i++) {
                String rule = subrules[i];
                if (rule != null) {
                    String[] options = rule.split(" \\| ");
                    StringBuilder combinations = new StringBuilder();                    
                    for (String option : options) {
                        String[] splitSubrules = option.split(" ");
                        for (int j = 0; j <splitSubrules.length; j++) {
                            int neededInnerRule = Integer.parseInt(splitSubrules[j]);
                            String rulesToAppend = evaluatedRules[neededInnerRule];
                            if (rulesToAppend == null) {
                                combinations = null;
                                break;
                            } else {
                                combinations.append(rulesToAppend);                                
                            }
                        }                        
                        if (combinations == null) {
                            break;
                        } else {
                            combinations.append(" ");
                        }
                    }
                    if (combinations != null) {
                        evaluatedRules[i] = combinations.toString();
                        subrules[i] = null;
                    }                
                }
            }
            System.out.println(Arrays.toString(evaluatedRules));
            System.out.println(Arrays.toString(subrules));
        }        
        return 0;        
    }

    public static boolean allSubrulesEvaluated(String[] subrules) {
        boolean allEntriesNull = true;
        for (String rule : subrules) {
            if (rule != null) {
                allEntriesNull = false;
                break;
            }
        }
        return allEntriesNull;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(getValidMessages());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
