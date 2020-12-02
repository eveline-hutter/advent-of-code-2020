package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

    public int getValidPasswords() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day2input.txt");
        List<String> input = Files.readAllLines(path);
        int validPasswords = 0;
        for (String line : input) {
            int[] minMaxCharPos = getMinMaxCharPos(line);
            int min = minMaxCharPos[0] - 1; // no index zero
            int max = minMaxCharPos[1] - 1; // no index zero
            char pwChar = line.charAt(minMaxCharPos[2]);
            String password = line.substring(minMaxCharPos[2] + 3);
            boolean atPos1 = false;
            boolean atPos2 = false;
            if (password.charAt(min) == pwChar) {
                atPos1 = true;
            }
            if (password.charAt(max) == pwChar) {
                atPos2 = true;
            }
            if ( (atPos1 && !atPos2) || (!atPos1 && atPos2) ) {
                validPasswords++;
            }
        }
        return validPasswords;
    }
    
    public int[] getMinMaxCharPos(String string) {
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
        R2 r2 = new R2();
        int solution = 0;
        try {
            solution = r2.getValidPasswords();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(solution);
    }
}
