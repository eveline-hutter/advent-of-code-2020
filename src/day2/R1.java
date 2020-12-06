package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static int getValidPasswords() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day2input.txt");
        List<String> input = Files.readAllLines(path);
        int validPasswords = 0;
        for (String line : input) {
            int[] minMaxCharPos = getMinMaxCharPos(line);  
            int min = minMaxCharPos[0];
            int max = minMaxCharPos[1];
            char pwChar = line.charAt(minMaxCharPos[2]);
            String password = line.substring(minMaxCharPos[2] + 3);
            int charCount = 0;
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == pwChar) {
                    charCount++;
                }
            }
            if (charCount >= min && charCount <= max) {
                validPasswords++;
            }
        }
        return validPasswords;
    }
    
    public static int[] getMinMaxCharPos(String string) {
        int[] minMax = new int[3];
        String substringMin = "";
        String substringMax = "";
        boolean minFinished = false;
        boolean maxFinished = false;
        int separatorMinMax = 0;
        while (!minFinished) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '-') {
                    minFinished = true;
                    separatorMinMax = i;
                    minMax[0] = Integer.parseInt(substringMin);
                    break;
                } else {
                    substringMin += string.charAt(i);
                }
            }
        }
        while (!maxFinished) {
            for (int i = separatorMinMax + 1; i < string.length(); i++) {
                if (string.charAt(i) == ' ') {
                    maxFinished = true;
                    minMax[1] = Integer.parseInt(substringMax);
                    minMax[2] = i + 1;  // position of required character in password
                    break;
                } else {
                    substringMax += string.charAt(i);
                }
            }
        }        
        return minMax;
    }
        
    public static void main(String[] args) {
        try {
            System.out.println(getValidPasswords());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
