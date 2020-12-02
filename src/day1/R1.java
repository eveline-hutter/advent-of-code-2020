package day1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.*;

public class R1 {

    public int getPair() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day1input.txt");
        List<String> input = Files.readAllLines(path);
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                int first = Integer.parseInt(input.get(i));
                int second = Integer.parseInt(input.get(j));
                if ( (first + second) == 2020) {
                    System.out.println("Pair found! First: " + first + " (" + i + ") | Second: " + second + " (" + j + ")");
                    return first * second;
                }
            }
        }
        return -1;
    }    

    public static void main(String[] args) {
        R1 r1 = new R1();
        int solution = 0;
        try {
            solution = r1.getPair();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(solution);
    }
}
