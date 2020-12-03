package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class R2 {

    public int getNoOfTrees(int right, int down) throws IOException {
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
        int checkpoint = right;
        int noOfTrees = 0;
        for (int i = (0 + down); i < x; i+= down) {
            if (array[i][checkpoint] == '#') {
                noOfTrees++;
            }
            checkpoint = (checkpoint + right) % y;
        }
        return noOfTrees;
    }

    public static void main(String[] args) {
        R2 r2 = new R2();
        long solution = 1;
        try {
            solution *= r2.getNoOfTrees(1, 1);
            solution *= r2.getNoOfTrees(3, 1);
            solution *= r2.getNoOfTrees(5, 1);
            solution *= r2.getNoOfTrees(7, 1);
            solution *= r2.getNoOfTrees(1, 2);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(solution);
    }
}
