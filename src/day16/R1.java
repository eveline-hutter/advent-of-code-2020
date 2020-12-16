package day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static int getTicketScanningErrorRate() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day16input.txt");
        List<String> input = Files.readAllLines(path);
        int errorRate = 0;
        List<String> rules = input.subList(0, 20);
        int[][] ruleRanges = new int[20][4];
        for (int i = 0; i < rules.size(); i++) {
            String[] split = rules.get(i).split(": | or "); // split[1] = first range, split[2] = second range
            String[] splitRange1 = split[1].split("-");
            String[] splitRange2 = split[2].split("-");
            ruleRanges[i][0] = Integer.parseInt(splitRange1[0]);
            ruleRanges[i][1] = Integer.parseInt(splitRange1[1]);
            ruleRanges[i][2] = Integer.parseInt(splitRange2[0]);
            ruleRanges[i][3] = Integer.parseInt(splitRange2[1]);
        }
        List<String> nearbyTickets = input.subList(25, input.size());
        for (String ticket : nearbyTickets) {
            String[] fields = ticket.split(",");
            int[] fieldValues = new int[fields.length];
            for (int i = 0; i < fieldValues.length; i++) {
                fieldValues[i] = Integer.parseInt(fields[i]);
            }
            boolean ticketValidated = false;
            while (!ticketValidated) {
                for (int value : fieldValues) {
                    boolean valueValidated = false;
                    while (!valueValidated) {
                        for (int[] range : ruleRanges) {
                            if ( (value >= range[0] && value <= range[1]) || (value >= range[2] && value <= range[3])) {
                                valueValidated = true;
                                break;
                            }
                        }
                        break;
                    }
                    if (!valueValidated) {
                        errorRate += value;
                    }
                    ticketValidated = true;
                }
            }            
        }
        return errorRate;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getTicketScanningErrorRate());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
