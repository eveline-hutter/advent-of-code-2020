package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static int getFirstInvalidNumber() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day9input.txt");
        List<String> input = Files.readAllLines(path);
        for (int i = 25; i < input.size(); i++) {
            // get last 25 numbers in an array
            long[] last25 = new long[25];
            for (int j = 25; j > 0; j--) {
                last25[25 - j] = Long.parseLong(input.get(i - j));
            }
            // check if number is the sum of two of the last 25 numbers
            boolean sumFound = false;
            long toFind = Long.parseLong(input.get(i));
            for (long no : last25) {
                for (long no2 : last25) {
                    if ( (no + no2) == toFind ) {
                        sumFound = true;
                        break;
                    }
                }
            }
            if (!sumFound) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        try {
            Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day9input.txt");
            List<String> input = Files.readAllLines(path);            
            System.out.println(input.get(getFirstInvalidNumber()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
