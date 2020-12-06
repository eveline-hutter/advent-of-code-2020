package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R2 {

    public static int getNoOfTrees(int right, int down) throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day3input.txt");
        List<String> input = Files.readAllLines(path);
        int checkpoint = right;
        int noOfTrees = 0;
        for (int i = (0 + down); i < input.size(); i+= down) {
            if (input.get(i).charAt(checkpoint) == '#') {
                noOfTrees++;
            }
            checkpoint = (checkpoint + right) % input.get(0).length();
        }
        return noOfTrees;
    }

    public static void main(String[] args) {
        try {
            long solution = 1;
            solution *= getNoOfTrees(1, 1);
            solution *= getNoOfTrees(3, 1);
            solution *= getNoOfTrees(5, 1);
            solution *= getNoOfTrees(7, 1);
            solution *= getNoOfTrees(1, 2);
            System.out.println(solution);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
