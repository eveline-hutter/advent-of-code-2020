package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class R1 {

    public int getNoOfTrees() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day3input.txt");
        List<String> input = Files.readAllLines(path);
        int x = input.size();
        int y = input.get(0).length();
        char[][] array = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                array[i][j] = input.get(i).charAt(j);
            }
        }
        int checkpoint = 3;
        int noOfTrees = 0;
        for (int i = 1; i < x; i++) {
            if (array[i][checkpoint] == '#') {
                noOfTrees++;
            }
            checkpoint = (checkpoint + 3) % y;
        }
        return noOfTrees;
    }

    public static void main(String[] args) {
        R1 r1 = new R1();
        int solution = 0;
        try {
            solution = r1.getNoOfTrees();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(solution);
    }
}
