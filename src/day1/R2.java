package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

    public static int getTriplet() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day1input.txt");
        List<String> input = Files.readAllLines(path);
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                for (int k = j + 1; k < input.size(); k++) {
                    int first = Integer.parseInt(input.get(i));
                    int second = Integer.parseInt(input.get(j));
                    int third = Integer.parseInt(input.get(k));
                    if ( (first + second + third) == 2020) {
                        System.out.println("Triplet found! First: " + first + " (" + i + ") | Second: "
                                + second + " (" + j + ") | Third: " + third + " (" + k + ")");
                        return first * second * third;
                    }
                }                
            }
        }
        return -1;
    }    

    public static void main(String[] args) {
        try {
            System.out.println(getTriplet());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
