package day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class R2 {

    public static long getDeparture() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day16input.txt");
        List<String> input = Files.readAllLines(path);
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
        // find invalid tickets
        List<Integer> invalidTicketNumbers = new ArrayList<>();   
        List<String> validTickets = new ArrayList<>();
        for (int i = 0; i < nearbyTickets.size(); i++) {
            String[] fields = nearbyTickets.get(i).split(",");
            int[] fieldValues = new int[fields.length];
            for (int j = 0; j < fieldValues.length; j++) {
                fieldValues[j] = Integer.parseInt(fields[j]);
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
                        invalidTicketNumbers.add(i);
                    }
                    ticketValidated = true;
                }
            }
        }
        // extract valid tickets from all nearby tickets
        int invalidTicketCounter = 0;
        for (int i = 0; i < nearbyTickets.size(); i++) {
            if (invalidTicketCounter >= invalidTicketNumbers.size()) {
                validTickets.add(nearbyTickets.get(i));
            }
            else if (i != invalidTicketNumbers.get(invalidTicketCounter)) {
                validTickets.add(nearbyTickets.get(i));
            } else {
                invalidTicketCounter++;
            }            
        }
        int[] decodedFields = getDecodedFields(ruleRanges, validTickets);
        // find departure fields (first six fields on ticket)
        String myTicket = input.get(22);
        String[] myTicketFields = myTicket.split(",");
        int[] myTicketFieldValues = new int[myTicketFields.length];
        for (int i = 0; i < myTicketFieldValues.length; i++) {
            myTicketFieldValues[i] = Integer.parseInt(myTicketFields[i]);
        }
        long departure = 1;
        for (int i = 0; i < 6; i++) {
            departure *= myTicketFieldValues[decodedFields[i]];
        }
        return departure;
    }
    
    public static int[] getDecodedFields(int[][] ruleRanges, List<String> nearbyTickets) {
        boolean[][][] compliedRulesForFields = new boolean [nearbyTickets.size()][ruleRanges.length][ruleRanges.length]; 
        for (int i = 0; i < nearbyTickets.size(); i++) {
            String[] fields = nearbyTickets.get(i).split(",");
            int[] fieldValues = new int[fields.length];
            for (int j = 0; j < fieldValues.length; j++) {
                fieldValues[j] = Integer.parseInt(fields[j]);
            }
            // for each field: which rules are fulfilled?
            for (int j = 0; j < fieldValues.length; j++) {
                int value = fieldValues[j];
                for (int k = 0; k < ruleRanges.length; k++) {
                    if ( (value >= ruleRanges[k][0] && value <= ruleRanges[k][1]) ||
                         (value >= ruleRanges[k][2] && value <= ruleRanges[k][3])) {
                        compliedRulesForFields[i][j][k] = true;                        
                    }
                }
            }
        }
        return filterRules(compliedRulesForFields);
    }
    
    public static int[] filterRules (boolean[][][] rules) {
        // create two-dimensional array with rules for all fields and fill all with true
        boolean[][] rules2D = new boolean[rules[0].length][rules[0][0].length];
        for (int i = 0; i < rules2D.length; i++) {
            for (int j = 0; j < rules2D[0].length; j++) {
                rules2D[i][j] = true;
            }
        }
        // if on any ticket a rule is not fulfilled, the according field is set to "false"
        for (int i = 0; i < rules.length; i++) {
            for (int j = 0; j < rules[0].length; j++) {
                for (int k = 0; k < rules[0][0].length; k++) {
                    if (!rules[i][j][k]) {
                        rules2D[j][k] = false;
                    }
                }
            }
        }
        int[] fieldRuleMatch = new int[rules2D.length];
        for (int i = 0; i < rules2D.length; i++) {
            boolean ruleFound = false;
            while (!ruleFound) {
                for (int j = 0; j < rules2D.length; j++) {
                    int noOfFalse = 0;  // counts how many rules are not met for this field                
                    for (boolean rule : rules2D[j]) {
                        if (!rule) {
                            noOfFalse++;
                        }
                    }
                    if (noOfFalse == rules2D.length - 1) {
                        ruleFound = true;
                        int rulePos = getRulePosition(rules2D[j]);
                        fieldRuleMatch[j] = rulePos;
                        // set found rule position to false in all other fields
                        for (int k = 0; k < rules2D.length; k++) {
                            if (j != k) {
                                rules2D[k][rulePos] = false;
                            }
                        }
                    }
                }
            }
        }
        return changeFieldRuleToRuleFieldMatch(fieldRuleMatch); // switch positions of rules and fields
    }   
    
    public static int[] changeFieldRuleToRuleFieldMatch(int[] fieldRuleMatch) {
        int[] ruleFieldMatch = new int[fieldRuleMatch.length];
        for (int i = 0; i < ruleFieldMatch.length; i++) {
            ruleFieldMatch[fieldRuleMatch[i]] = i;
        }
        return ruleFieldMatch;
    }
    
    public static int getRulePosition(boolean[] field) {
        for (int i = 0; i < field.length; i++) {
            if (field[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public static void print2DArray(boolean[][] rules2D) {
        for (boolean[] field: rules2D) {
            int noOfFalse = 0;
            for (boolean rule : field) {
                if (!rule) {
                    noOfFalse++;
                }
            }
            System.out.print(" " + noOfFalse);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        try {
            System.out.println(getDeparture());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
