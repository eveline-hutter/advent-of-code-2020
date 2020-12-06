package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class R1 {

    public static int getNoOfTrees() throws IOException {
        Path path = Paths.get("D:\\Documents\\GitHub\\advent-of-code-2020\\data\\day3input.txt");
        List<String> input = Files.readAllLines(path);
        int checkpoint = 3;
        int noOfTrees = 0;
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i).charAt(checkpoint) == '#') {
                noOfTrees++;
            }
            checkpoint = (checkpoint + 3) % input.get(0).length();
        }
        return noOfTrees;
    }

    public static void main(String[] args) {
        try {
            System.out.println(getNoOfTrees());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
