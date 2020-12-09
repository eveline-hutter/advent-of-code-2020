package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

    public static boolean getContiguousSet() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day9input.txt");
        List<String> input = Files.readAllLines(path);
        int pos = R1.getFirstInvalidNumber();
        long requiredSum = Long.parseLong(input.get(pos));
        long sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < pos - 1; i++) {
            int no = Integer.parseInt(input.get(i));
            sum += no;
            min = Math.min(min, no);
            max = Math.max(max, no);
            for (int j = i + 1; j < pos; j++) {
                int no2 = Integer.parseInt(input.get(j));
                sum += no2;
                min = Math.min(min, no2);
                max = Math.max(max, no2);
                if (sum == requiredSum) {
                    System.out.println("min: " + min + " max: " + max + " sum: " + (min + max));
                    System.out.println("i = " + i + " j = " + j);
                    return true;
                } else if (sum > requiredSum) {
                    // set back variables
                    sum = 0;
                    min = Integer.MAX_VALUE;
                    max = Integer.MIN_VALUE;
                    break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            getContiguousSet();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
